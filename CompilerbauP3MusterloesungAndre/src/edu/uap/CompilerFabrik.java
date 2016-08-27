package edu.uap;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Vector;

import edu.uap.Instruction;

import edu.uap.nodes.*;

public class CompilerFabrik {
	//Aufgabe 1a)
	public static void flachklopfen(Node klopfknoten) {
		String k = klopfknoten.getType();
		for (int i = 0; i < klopfknoten.getChildren().size(); i++) {
			Node kind = klopfknoten.getChildren().get(i);
			if (k.equals("SEMI") || k.equals("DEF") || k.equals("ARGS") || k.equals("PARAMS")) {
				// Falls ein doppelter Knoten gefunden wird soll er entfernt und
				// dessen Kinder an seiner Stelle eingefügt werden.
				if (kind.getType() == k) {
					System.out.println("Doppelter Knoten vom Typ " + k + " gefunden.");
					klopfknoten.getChildren().addAll(i + 1, kind.getChildren()); // fügt die Kinder hinter dem zu löschenden Element ein...
					klopfknoten.getChildren().remove(i); // ...und löscht es
					i--; // der Index muss durch das Löschen des Kindes korrigiert werden
				}
			}

		}
		for (int i = 0; i < klopfknoten.getChildren().size(); i++) { // rekursiver Aufruf der Funktion, falls das Kind nicht entfernt wird
			Node kind = klopfknoten.getChildren().get(i);
			flachklopfen(kind);
		}
	}
	
	//Aufgabe 1b)
	static int top = 0; //gibt die erste freie Stelle in der HashMap an
	static int nl =0; //Das nesting Level startet bei 0 und erhöht sich bei jedem Funktionsaufruf
	
	static HashMap<String, AddressPair> rho = new HashMap<String, AddressPair>(); 	//Speicher fuer Variablen und Labels
	static LinkedList<HashMap<String, AddressPair>> speicher = new LinkedList<HashMap<String, AddressPair>>();
	
	//Initialisiert den Speicher mit Nesting Level 0
	static {
		speicher.add(rho);
	}

	
	public static Vector<Instruction> code(ReadNode read)	{
		//Hilfsvariablen
		DefNode id = (DefNode) read.getChildren().get(0);
		AddressPair idSpeicherinhalt = rho.get(id.getAttribute().toString());
		int k = (Integer) idSpeicherinhalt.loc;
		int nl1 = idSpeicherinhalt.nl;
		
		//Instructionerzeugung
		Vector<Instruction> tramCode = new Vector<Instruction>();
		tramCode.add(new Instruction(Instruction.LOAD,k, nl - nl1));
		return tramCode;
	}
	
	//Hier muss noch das Speichern der Variable in der HashMap mit rein
	public static Vector<Instruction> code(AssignNode asignNode)	{
		//Erstellung von Hilfsvariablen zur besseren Codelesbarkeit  
		Node E = asignNode.getChildren().get(1);
		IDNode ID = (IDNode) asignNode.getChildren().get(0);
		
		//k und nl1 ergeben sich aus roh(id)
		int nl1=rho.get(ID.getAttribute()).nl;
		int k = (Integer) rho.get(ID.getAttribute()).loc;
		
		//Instructioneugung
		Vector<Instruction> tramCode = code(E);//E ist im Moment noch vom Typ Node -> eine spezielle Methode wird nicht angewendet
		tramCode.add(new Instruction((Instruction.STORE),k, nl- nl1));
		tramCode.add(new Instruction((Instruction.LOAD),k, nl- nl1));	
		return tramCode;
	}
	
	public static Vector<Instruction> code (ConstNode con)	{
		//Hilfsvariablen
		Integer konstantenWert = Integer.decode(con.getAttribute().toString());
		
		//Codeerzeugung
		Vector<Instruction> tramCode = new Vector<Instruction>();
		tramCode.add(new Instruction(Instruction.CONST, konstantenWert));
		return tramCode;
	}
}

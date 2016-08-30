package edu.uap;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.ListIterator;
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
	static int labelCount = 0;
	static int top = 0; //gibt die erste freie Stelle in der HashMap an
	//static int nl =0; //Das nesting Level startet bei 0 und erhöht sich bei jedem Funktionsaufruf
	
	static HashMap<String, AddressPair> rho = new HashMap<String, AddressPair>(); 	//Speicher fuer Variablen und Labels
	static LinkedList<HashMap<String, AddressPair>> speicher = new LinkedList<HashMap<String, AddressPair>>();
	
	//Initialisiert den Speicher mit Nesting Level 0 -> 
	static {
		speicher.add(rho);	//enthält pro Nesting Level eine HashMap
	}
	
	
	//Da die code Funktionsaufrufe immer mit dem Typ Node erfolgen gibt es eine Weiterleitung je nach tatsächlichem Tyo
	public static Vector<Instruction> code (Node node, int nl)	{
		Vector<Instruction> tramCode = new Vector<Instruction>();
		switch (node.getType())	{
		case "ARGS":
			tramCode.addAll(code((ReadNode) node, nl));
			break;
		case "ASSIGN":
			tramCode.addAll(code((AssignNode) node, nl));
			break;
		case "CONST":	
			tramCode.addAll( code((ConstNode) node, nl));
			break;
		case "BODY":
			tramCode.addAll(code((BodyNode) node, nl));
			break;	
		case "CALL":
			tramCode.addAll(code((CallNode) node, nl));
			break;		
		case "COND":
			tramCode.addAll(code((CondNode) node, nl));
			break;		
		case "DEF":
			tramCode.addAll(code((DefNode) node, nl));
			break;		
		case "ELSE":
			tramCode.addAll(code((ElseNode) node, nl));
			break;		
		case "EXPR":
			tramCode.addAll(code((ExprNode) node, nl));
			break;		
		case "FUNC":
			tramCode.addAll(code((FuncNode) node, nl));
			break;		
		case "ID":
			tramCode.addAll(code((IDNode) node, nl));
			break;		
		case "IF":
			tramCode.addAll(code((IfNode) node, nl));
			break;		
		case "LET":
			tramCode.addAll(code((LetNode) node, nl));
			break;		
		case "OP":
			tramCode.addAll(code((OpNode) node, nl));
			break;		
		case "PARAMS":
			tramCode.addAll(code((ParamsNode) node, nl));
			break;		
		case "PAR":
			tramCode.addAll(code((ParNode) node, nl));
			break;		
		case "READ":
			tramCode.addAll(code((ReadNode) node, nl));
			break;		
		case "SEMI":
			tramCode.addAll(code((SemiNode) node, nl));
			break;		
		case "THEN":
			tramCode.addAll(code((ThenNode) node, nl));
			break;		
		}
		return tramCode;
	}
	
	//Sucht im Speicher nach dem key in allen kleineren Nesting Leveln
	private static AddressPair speicherSuche(String key, int startNL)	{
		int i = startNL;
		AddressPair idSpeicherinhalt = speicher.get(i).get(key);		
		while(idSpeicherinhalt == null && i>0)	{
			idSpeicherinhalt = speicher.get(i).get(key);
		}
		return idSpeicherinhalt;
	}
	
	public static Vector<Instruction> code(ReadNode read, int nl)	{
		//Hilfsvariablen
		DefNode id = (DefNode) read.getChildren().get(0);
		
		//Sucht im Speicher nach der ID in allen kleineren Nesting Leveln
		AddressPair idSpeicherinhalt = speicherSuche(id.getAttribute().toString(),nl);	//evtl. Fehlermeldung einbauen, wenn null zurückgeliefert wird

		int k = (Integer) idSpeicherinhalt.loc;
		int nl1 = idSpeicherinhalt.nl;
		
		//Instructionerzeugung
		Vector<Instruction> tramCode = new Vector<Instruction>();
		tramCode.add(new Instruction(Instruction.LOAD,k, nl - nl1));
		return tramCode;
	}
	

	public static Vector<Instruction> code(AssignNode asignNode, int nl)	{
		//Erstellung von Hilfsvariablen zur besseren Codelesbarkeit  
		Node E = asignNode.getChildren().get(1);
		IDNode ID = (IDNode) asignNode.getChildren().get(0);

		//Suche die ID im Speicher
		AddressPair variable = speicherSuche(ID.getAttribute().toString(), nl);
		
		
		//k und nl1 ergeben sich aus roh(id)
		int nl1=variable.nl;
		int k = (Integer) variable.loc;
		
		//Instructioneugung
		Vector<Instruction> tramCode = code(E, nl);
		tramCode.add(new Instruction((Instruction.STORE),k, nl- nl1));
		tramCode.add(new Instruction((Instruction.LOAD),k, nl- nl1));	
		return tramCode;
	}
	
	public static Vector<Instruction> code (OpNode opNode, int nl)	{
		Vector<Instruction> tramCode = new Vector<Instruction>();
		tramCode.addAll(code(opNode.getChildren().get(0), nl));
		tramCode.addAll(code(opNode.getChildren().get(1), nl));
		
		// Instruktionsaufruf je nachdem, welche Operation der Knoten darstellt
		int instruktionsNummer =0;
		switch (opNode.getAttribute().toString())	{
		case "==":
			instruktionsNummer = Instruction.EQ;
			break;
		case "!=":
			instruktionsNummer = Instruction.NEQ;
			break;
		case "&lt":
			instruktionsNummer = Instruction.LT;
			break;
		case "&gt":
			instruktionsNummer = Instruction.GT;
			break;
		case "+":
			instruktionsNummer = Instruction.ADD;
			break;
		case "-":
			instruktionsNummer = Instruction.SUB;
			break;
		case "*":
			instruktionsNummer = Instruction.MUL;
			break;
		case "/":
			instruktionsNummer = Instruction.DIV;
			break;
		}
		tramCode.add(new Instruction(instruktionsNummer));
		return tramCode;
		
		
	}
	
	public static Vector<Instruction> code (ConstNode con, int nl)	{
		//Hilfsvariablen
		Integer konstantenWert = Integer.decode(con.getAttribute().toString());
		
		//Codeerzeugung
		Vector<Instruction> tramCode = new Vector<Instruction>();
		tramCode.add(new Instruction(Instruction.CONST, konstantenWert));
		return tramCode;
	}
	
	//Durch das flachklopfen hat jeder Semi Node beliebig viele Kinder
	public static Vector<Instruction> code (SemiNode semi, int nl)	{
		Vector<Instruction> tramCode = new Vector<Instruction>();
		for(int i =0; i<semi.getChildren().size()-1; i++)	{
			tramCode.addAll(code(semi.getChildren().get(i), nl));
			//***Pop Instruction einfügen ****tramCode.add(Instruction.)
		}
		//füge für das letzte Kind des Semi Knoten den Code manuell hinzu, weil darauf kein pop folgt
		tramCode.addAll(code(semi.getChildren().get(semi.getChildren().size()), nl));
		return tramCode;
	}
	
	private static void addLabel(int nl)	{
		rho.put(Integer.toString(labelCount), new AddressPair(new TramLabel(-1),nl));
		labelCount++;
	}
	
	public static Vector<Instruction> code(IfNode ifNode, int nl)	{
		Vector<Instruction> tramCode = new Vector<Instruction>();	
		Vector<Instruction> e1 = code(ifNode.getChildren().get(0), nl);
		Vector<Instruction> e2 = code(ifNode.getChildren().get(1), nl);
		Vector<Instruction> e3 = code(ifNode.getChildren().get(2), nl);
		
		tramCode.addAll(e1);
		//Es wird ein neues Label erstellt zu dem gesprungen werden soll, wenn if(e1) true ergibt
		//Die Adresse steht zu Anfang auf -1 -> später muss man die Instructions durchlaufen bis zum
		//...dazugehörenden Else Node -> das ist die SprungAdresse
					//rho.put(Integer.toString(labelCount), new AddressPair(new TramLabel(-1),nl));	//l1
					//labelCount++;
		addLabel(nl);	//l1
		int label1 = labelCount;
		tramCode.add(new Instruction(Instruction.TRAMLABELCALLER, label1, Instruction.IFZERO, label1));	//Hinter label1 steht der später einzusetzende Maschinenbefehl
		//tramCode.add(new Instruction(Instruction.IFZERO, label1));	//in LabelCount steht der Key zum Label in der HashMap
		tramCode.addAll(e2);
		addLabel(nl);
		int label2 = labelCount;
		tramCode.add(new Instruction(Instruction.TRAMLABELCALLER, label2, Instruction.GOTO, label2));
		tramCode.add(new Instruction(Instruction.TRAMLABEL, label1));
		tramCode.addAll(e3);
		tramCode.add(new Instruction(Instruction.TRAMLABEL, label2));
		tramCode.add(new Instruction(Instruction.NOP));

		return tramCode;
	}
	
	//Muss ich hier das Nesting Level erhöhen?
	public static Vector<Instruction> code(CallNode call, int nl)	{
		nl++;	//ein Call Node erhöht das NestingLevel
		
		Vector<Instruction> tramCode = new Vector<Instruction>();
		
		//Alle Kinder ab index 1 sind Funktionsparameter und werden zuerst in Code übersetzt
		for(int i=1; i<call.getChildren().size();i++)	{
			tramCode.addAll(code(call.getChildren().get(i), nl));
		}
		
		//Hilfsvariablen
		Integer idName = Integer.decode(call.getChildren().get(0).getAttribute().toString());
		int anzahlFunktionsparameter = call.getChildren().size() -1; //Da das erste Kind der IDNode ist
		AddressPair idSpeicherInhalt = speicherSuche(idName.toString(), nl);
		int nestingLevelDifferenz = nl - idSpeicherInhalt.nl;
		Instruction invokeInstruction = new Instruction(Instruction.INVOKE, anzahlFunktionsparameter, idName, nestingLevelDifferenz);	//idName muss später mit der Instruktionsnummer dieses Labels ersetzt werden
				
		tramCode.add(new Instruction(Instruction.TRAMLABELCALLER, idName,invokeInstruction));
		//nl++;	//???? Nur die Funktionsdefinitionen bekommen bei der Definition ein höheres Nesting Level??
		return tramCode;
	}
	
	public static Vector<Instruction> code(DefNode defNode, int nl)	{
		Vector<Instruction> tramCode = new Vector<Instruction>();
		//Durch das Flachklopfen sind alle Kinder Func Nodes
		//for(int i =0; i< )
		ListIterator<Node> iterator = defNode.getChildren().listIterator();
		while(iterator.hasNext())	{
			tramCode.addAll(code(iterator.next(), nl));
		}
		return tramCode;
	}
	
	public static Vector<Instruction> code(LetNode letNode, int nl)	{
		HashMap<String, AddressPair> rho2 = elab_def()
	}
	
	
	public static Vector<Instruction> code(FuncNode funcNode, int nl)	{
		Vector<Instruction> tramCode = new Vector<Instruction>();
		addLabel(nl);
		int label = labelCount;
		
		//Speichere die FunktionsID in der Hashmap mit dem Label und dem Nesting Level:
		
		
		tramCode.add(new Instruction(Instruction.TRAMLABEL, label));
		
	}
	
	
	
}

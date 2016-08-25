package edu.uap;
import edu.uap.nodes.*;


public class CompilerFabrik {
	public static void flachklopfen(Node klopfknoten)	{
		String k = klopfknoten.getType();
		if (k.equals("SEMI") || k.equals("DEF") || k.equals("ARGS") || k.equals("PARAMS"))	{
			for(int i = 0; i< klopfknoten.getChildren().size();i++ )	{
				Node kind = klopfknoten.getChildren().get(i);
				//Falls ein doppelter Knoten gefunden wird soll er entfernt und dessen Kinder an seiner Stelle eingef�gt werden.
				if(kind.getType() == k)	{
					System.out.println("Doppelter Knoten vom Typ " +k+ " gefunden.");
					klopfknoten.getChildren().addAll(i+1,kind.getChildren());	//f�gt die Kinder hinter dem zu l�schenden Element ein...
					klopfknoten.getChildren().remove(i);										//...und l�scht es
				}
			}
		}
	}
}

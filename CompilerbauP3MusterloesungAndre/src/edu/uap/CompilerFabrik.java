package edu.uap;

import edu.uap.nodes.*;

public class CompilerFabrik {
	public static void flachklopfen(Node klopfknoten) {
		String k = klopfknoten.getType();
		for (int i = 0; i < klopfknoten.getChildren().size(); i++) {
			Node kind = klopfknoten.getChildren().get(i);
			if (k.equals("SEMI") || k.equals("DEF") || k.equals("ARGS") || k.equals("PARAMS")) {
				// Falls ein doppelter Knoten gefunden wird soll er entfernt und
				// dessen Kinder an seiner Stelle eingef�gt werden.
				if (kind.getType() == k) {
					System.out.println("Doppelter Knoten vom Typ " + k + " gefunden.");
					klopfknoten.getChildren().addAll(i + 1, kind.getChildren()); // f�gt
																					// die
																					// Kinder
																					// hinter
																					// dem
																					// zu
																					// l�schenden
																					// Element
																					// ein...
					klopfknoten.getChildren().remove(i); // ...und l�scht es
					i--; // der Index muss durch das L�schen des Kindes
							// korrigiert werden
				}
			}

		}
		for (int i = 0; i < klopfknoten.getChildren().size(); i++) { // rekursiver
																		// Aufruf
																		// der
																		// Funktion,
																		// falls
																		// das
																		// Kind
																		// nicht
			// entfernt wird
			Node kind = klopfknoten.getChildren().get(i);
			flachklopfen(kind);
		}
	}
}

package edu.uap.tram;

import edu.uap.Instruction;

public class Testklasse {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		abstractMachine abstractM = new abstractMachine();
		
		try {
			
		
		//Je nach Parameter in args[0] wird das jeweilige Programm aus der Klasse Instruction geladen
		switch (args[0]) {

		case "1":
			abstractM = new abstractMachine(0, 0, 1);
			abstractM.inititalize(Instruction.program1);
			break;
		case "2":
			abstractM = new abstractMachine(0, 0, 0);
			abstractM.inititalize(Instruction.program2);
			break;
		case "3":
			abstractM = new abstractMachine(0, 0, -1);
			abstractM.inititalize(Instruction.program3);
			break;
		case "4":
			abstractM = new abstractMachine(0, 0, -1);
			abstractM.inititalize(Instruction.program4);
			break;
		
		case "fak":
			abstractM = new abstractMachine(0,0,1);
			abstractM.inititalize(Instruction.fakultaetsProgramm);
			break;
		 
		default:
			abstractM = new abstractMachine();
			System.out.println("In Argument 0 wurde kein gültiges Programm aus der Klasse Instruction adressiert.");
		}
		//args[1] bestimmt, ob der debuger angeschaltet werden soll
		if (args.length > 1 && args[1].equals("-d"))	{
			abstractM.debug();			
		}
		}
		catch (Exception x)	{ 
			System.out.println("Bitte geben sie als Argument 1, die Nummer des Programms ein das sie starten möchten. Mit Argument zwei können sie außerdem den Debug Modus einschalten.");
			System.out.print(x.getMessage());
			
		}
		

		System.out.print("Die virtuelle Maschine wurde erzeugt.");
		abstractM.execute();
		
	}

}

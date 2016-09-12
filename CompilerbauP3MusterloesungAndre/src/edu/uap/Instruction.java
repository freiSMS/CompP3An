package edu.uap;
/**
 *  Instructions for TRAM 2016
 */


public class Instruction
{
	public TramLabel label;
	
	public int opcode;
	public Integer arg1;
	public Integer arg2;
	public Integer arg3;
	public Instruction arg4;
	public String key;
	public Integer erstatzStelle;

	public final static int CONST = 1;
	public final static int LOAD = 2;
	public final static int STORE = 3;
	public final static int ADD = 4;
	public final static int SUB = 5;
	public final static int MUL = 6;
	public final static int DIV = 7;
	public final static int LT = 8;
	public final static int GT = 9;
	public final static int EQ = 10;
	public final static int NEQ = 11;
	public final static int IFZERO = 12;
	public final static int GOTO = 13;
	public final static int HALT = 14;
	public final static int NOP = 15;
	public final static int INVOKE = 16;
	public final static int RETURN = 17;

	//PseudoInstruktion für TramLabel -> wird später wieder entfernt
	public final static int TRAMLABEL = 18;	//args1 gibt die Nummer des Labels an das im Speicher die Instruktionsnummer benötigt
	public final static int TRAMLABELCALLER = 19; //arg1 gibt die Nummer des Labels an das gecallt wird
	// -> im ersten Schritt werden alle Instuktionen durchlaufen und immer hochgezählt, wenn die Instruction kein
	// TramLAbelCaller ist. Bei jedem TramLabel wird zuerst im Speicher rho dem Label, welches in args1 gespeichert ist
	//der aktuelle Count zugeordnet und die PseudoLabel- Operation entfernt.
	//Im zweiten Schritt werden alle die Instruktionen erneut durchlaufen. Bei jedem TramLabelCaller
	// wird im Speicher nach dem in Args1 gespeicherte Label gesucht und bei der folgenden Instruction 
	// anstatt der dort stehenden Sprungadresse -1 eingefügt. Dann wird der TramLabelCaller entfernt.
	
	public final static int LAZYRETURN = 20;
	public final static int LAZY = 21;
	
	

	//Zusatzkonstruktor für funcNodes (hier sind Strings und nicht int die keys
	public Instruction(int opcode, String key)	{
		this.opcode = opcode;
		this.key = key;
		
	}

	//Zusatzkonstruktor für Variablen (hier sind Strings und nicht int die keys
	public Instruction(int opcode, String key, Instruction ins)	{
		this.opcode = opcode;
		this.key = key;
		this.arg4 = ins;
		
	}
	
	//Zusatzkonstruktor für die Invoke Instruktion
	public Instruction(int opcode, String key,Instruction invoke, int einsetzStelle)	{
		this(opcode);
		this.key = key;
		this.arg4 = invoke;
		this.erstatzStelle = einsetzStelle;
	}
	
	
	
	//Zusatzkonstruktor für die Invoke Instruktion
	public Instruction(int opcode, Integer arg1,Instruction invoke, int einsetzStelle)	{
		this(opcode, arg1);
		this.arg4 = invoke;
		this.erstatzStelle = einsetzStelle;
	}
	
	// Die Pseudo Instruction TramCaller enthält bei Funktionen die Position an der später der Wert einzusetzen ist
	public Instruction(int opcode, Integer arg1, Integer arg2, Integer arg3, Integer ersatzStelle)
	{
		this(opcode, arg1, arg2, arg3);

		this.erstatzStelle = ersatzStelle;
	}
	
	
	
	
	
	public Instruction(int opcode, Integer arg1, Integer arg2, Integer arg3)
	{
		this(opcode, arg1, arg2);
		this.arg3 = arg3;
	}


	public Instruction(int opcode, Integer arg1, Integer arg2)
	{
		this(opcode, arg1);
		this.arg2 = arg2;
	}


	public Instruction(int opcode, Integer arg1)
	{
		this(opcode);
		this.arg1 = arg1;
	}


	public Instruction(int opcode)
	{
		this.opcode = opcode;
	}


	public int getArg1()
	{
		return arg1;
	}


	public void setArg1(Integer arg1)
	{
		this.arg1 = arg1;
	}


	public int getArg2()
	{
		return arg2;
	}


	public void setArg2(Integer arg2)
	{
		this.arg2 = arg2;
	}


	public int getArg3()
	{
		return arg3;
	}


	public void setArg3(Integer arg3)
	{
		this.arg3 = arg3;
	}


	public int getOpcode()
	{
		return opcode;
	}


	public void setOpcode(int opcode)
	{
		this.opcode = opcode;
	}


	@Override
	public String toString()
	{
		String retStr = "";

		switch (opcode)
		{
			case Instruction.CONST:
				retStr += "CONST";
				break;
			case Instruction.LOAD:
				retStr += "LOAD";
				break;
			case Instruction.STORE:
				retStr += "STORE";
				break;
			case Instruction.ADD:
				retStr += "ADD";
				break;
			case Instruction.SUB:
				retStr += "SUB";
				break;
			case Instruction.MUL:
				retStr += "MUL";
				break;
			case Instruction.DIV:
				retStr += "DIV";
				break;
			case Instruction.LT:
				retStr += "LT";
				break;
			case Instruction.GT:
				retStr += "GT";
				break;
			case Instruction.EQ:
				retStr += "EQ";
				break;
			case Instruction.NEQ:
				retStr += "NEQ";
				break;
			case Instruction.IFZERO:
				retStr += "IFZERO";
				break;
			case Instruction.GOTO:
				retStr += "GOTO";
				break;
			case Instruction.HALT:
				retStr += "HALT";
				break;
			case Instruction.NOP:
				retStr += "NOP";
				break;
			case Instruction.INVOKE:
				retStr += "INVOKE";
				break;
			case Instruction.RETURN:
				retStr += "RETURN";
				break;
			case Instruction.TRAMLABEL:
				retStr +="TRAMLABEL";
				break;
			case Instruction.TRAMLABELCALLER:
				retStr += "TRAMLABELCALLER";
				break;
			case Instruction.LAZY:
				retStr += "LAZY";
				break;
			case Instruction.LAZYRETURN:
				retStr += "LAZYRETURN";
				break;
			default:
				retStr += "ERROR";
		}

		if (arg1 != null)
		{
			retStr += " " + arg1;

			if (arg2 != null)
			{
				retStr += " " + arg2;

				if (arg3 != null)
				{
					retStr += " " + arg3;
				}
			}
		}
		if(key!= null)	{
			retStr +=" key = " + key;
		}
		if (erstatzStelle !=null)	{
			retStr +="  ersatzStelle = " + erstatzStelle;
		}
		if(arg4!= null)	{
			retStr += "  " + arg4.toString();
		}
		

		return retStr;
	}


	/***********************************************
	 * Sample Programs
	 ***********************************************/

	/**
	 * Quellkode: y = x*3+5*2 
	 * Annahmen: Variable x durch Kellerzelle 0 und Variable y durch Kellerzelle 1 implementiert, 
	 *           sowie PP=0, FP=0 und TOP=1.
	 */
	public static Instruction[] program1 = new Instruction[] {
			new Instruction(Instruction.CONST, 6), // value for x
			new Instruction(Instruction.STORE, 0, 0), // store x
			new Instruction(Instruction.LOAD, 0, 0), 
			new Instruction(Instruction.CONST, 3),
			new Instruction(Instruction.MUL), 
			new Instruction(Instruction.CONST, 5),
			new Instruction(Instruction.CONST, 2), 
			new Instruction(Instruction.MUL),
			new Instruction(Instruction.ADD), 
			new Instruction(Instruction.STORE, 1, 0),
			new Instruction(Instruction.HALT) };

	/**
	 * Quellkode: x=10; if(x == 0) 100 else 200; 3 
	 * Annahmen: Variable x durch Kellerzelle 0 implementiert, sowie PP=0, FP=0 und TOP=0.
	 */
	public static Instruction[] program2 = new Instruction[] { 
			new Instruction(Instruction.CONST, 10),
			new Instruction(Instruction.STORE, 0, 0),
			new Instruction(Instruction.LOAD, 0, 0),
			new Instruction(Instruction.IFZERO, 6), // --> iftrue
			new Instruction(Instruction.CONST, 200),
			new Instruction(Instruction.GOTO, 7), // --> goto
			// iftrue
			new Instruction(Instruction.CONST, 100),
			// goto
			new Instruction(Instruction.NOP), 
			new Instruction(Instruction.CONST, 3),
			new Instruction(Instruction.HALT) };

	/**
	 * Quellkode: let square(x) { x*x } 
	 *            in square(10) 
	 * Annahmen: Das Argument von square wird durch Kellerzelle 0 repraesentiert, sowie PP=0, FP=0 
	 *           und TOP=-1
	 */
	public static Instruction[] program3 = new Instruction[] {
			new Instruction(Instruction.CONST, 10),
			new Instruction(Instruction.INVOKE, 1, 3, 0), // --> square
			// return
			new Instruction(Instruction.HALT),
			// square
			new Instruction(Instruction.LOAD, 0, 0), 
			new Instruction(Instruction.LOAD, 0, 0),
			new Instruction(Instruction.MUL), 
			new Instruction(Instruction.RETURN) // --> return
	};

	/**
	 * Quellkode: let wrapper(number, threshold) { 
	 *                  let square(x) { 
	 *                        if (x*x > threshold) x 
	 *                        else x*x 
	 *                      }
	 *                  in square(number) 
	 *                } 
	 *            in wrapper(4, 10) 
	 * Annahmen: Die Argumente von wrapper werden durch die Kellerzellen 0 und 1 repraesentiert, 
	 *           sowie PP=0, FP=0 und TOP=-1
	 */
	public static Instruction[] program4 = new Instruction[] {
			new Instruction(Instruction.CONST, 4),
			new Instruction(Instruction.CONST, 10),
			new Instruction(Instruction.INVOKE, 2, 4, 0), // --> wrapper
			// return wrapper
			new Instruction(Instruction.HALT),
			// wrapper
			new Instruction(Instruction.LOAD, 0, 0),
			new Instruction(Instruction.INVOKE, 1, 7, 0), // --> square
			// return square
			new Instruction(Instruction.RETURN),
			// square
			new Instruction(Instruction.LOAD, 0, 0), 
			new Instruction(Instruction.LOAD, 0, 0),
			new Instruction(Instruction.MUL), 
			new Instruction(Instruction.LOAD, 1, 1),
			new Instruction(Instruction.GT), 
			new Instruction(Instruction.IFZERO, 15),
			new Instruction(Instruction.LOAD, 0, 0),
			new Instruction(Instruction.RETURN), // --> return square
			new Instruction(Instruction.LOAD, 0, 0), 
			new Instruction(Instruction.LOAD, 0, 0),
			new Instruction(Instruction.MUL), 
			new Instruction(Instruction.RETURN) // --> return square
	};
	
	// Intruction [] Programm fuer Aufgabe 3. 
	//Annahme: Die Zahl aus der die Fakultaet berechnet werden soll, steht in Kellerzelle 0. Als Beispielrechnung wurde x = 4 angenommen.
	//Das Ergebis wird durch Kellerzelle 1 repräsentiert.
		//Der Algorithmus geht so vor, dass die Fakultätsfunktion rekursiv aufgerufen wird (jeweils mit x-1) bis ein Aufruf für x=2 erfolgt.
		//Die Funktion liefert das Ergebnis 2 zurück, woraufhin die rekursiv wartenden Funktion nacheinader ihre Multiplikationsoperationen ausführen können.
	public static Instruction[] fakultaetsProgramm = new Instruction[]	{
			new Instruction(Instruction.CONST, 4), //0
			new Instruction(Instruction.STORE , 0, 0),	//1
			new Instruction(Instruction.LOAD, 0, 0), //2
			new Instruction(Instruction.INVOKE, 1,6,0),	//3
			new Instruction(Instruction.STORE, 1, 0),	//4
			new Instruction(Instruction.HALT),	//5
			
			new Instruction(Instruction.LOAD, 0 ,0),	//6
			new Instruction(Instruction.CONST, 1),	//7
			new Instruction(Instruction.SUB),	//8
			new Instruction(Instruction.CONST, 1),	//9
			new Instruction(Instruction.EQ),	//10
			new Instruction(Instruction.IFZERO, 14),	//11
				new Instruction(Instruction.CONST, 1),	//12
				new Instruction(Instruction.GOTO, 18),	//13
			new Instruction(Instruction.LOAD, 0, 0),	//14
			new Instruction(Instruction.CONST, 1),	//15
			new Instruction(Instruction.SUB),	//16
			new Instruction(Instruction.INVOKE,1,6,0),	//17
			new Instruction(Instruction.LOAD, 0, 0),	//18
			new Instruction(Instruction.MUL),	//19
			new Instruction(Instruction.RETURN)	//20
	};
	
	

}
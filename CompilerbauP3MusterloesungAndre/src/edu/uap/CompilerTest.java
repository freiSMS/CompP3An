/* CompilerTest class for TRIPLA 2015 */

package edu.uap;

import java.io.BufferedWriter;
import edu.uap.CompilerFabrik;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Vector;

import edu.uap.nodes.Node;

public class CompilerTest
{
	public static void main(String[] args)
	{
		PrintWriter pw = null;
		Node ast;

		try
		{
			parser triplaParser = new parser(new Lexer(new FileReader("beispiel01")));
			ast = ((Node) (triplaParser.parse().value));
			CompilerFabrik.flachklopfen(ast);
			System.out.println("geklopft");

			pw = new PrintWriter(new BufferedWriter(new FileWriter(new File("ast.xml"))));

			pw.println("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>");
			pw.print(ast.toString());
			System.out.println("\"ast.xml\" file created");
			
			HashMap<String, AddressPair> rho = new HashMap<String, AddressPair>();
			Vector<Instruction> programm = CompilerFabrik.code(ast, 0, rho);
			
			
			Iterator<Instruction> itr = programm.iterator();
			while (itr.hasNext()){
				System.out.println(itr.next().toString());
			}
			
			System.out.println("\n Beginne mit dem ersetzen der TramLabel. \n");
			CompilerFabrik.replaceTramLabels(programm);
			
			itr = programm.iterator();
			while (itr.hasNext()){
				System.out.println(itr.next().toString());
			}
		}
		
		
		catch (FileNotFoundException e)
		{
			System.err.println(e.getMessage());
		}
		catch (IOException e)
		{
			System.err.println(e.getMessage());
		}
		catch (Exception e)
		{
			System.err.println(e.getMessage());
		}
		finally
		{
			if (pw != null)
			{
				pw.close();
			}
		}
	}
}

/* CompilerTest class for TRIPLA 2015 */

package edu.uap;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

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

			pw = new PrintWriter(new BufferedWriter(new FileWriter(new File("ast.xml"))));

			pw.println("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>");
			pw.print(ast.toString());
			System.out.println("\"ast.xml\" file created");

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

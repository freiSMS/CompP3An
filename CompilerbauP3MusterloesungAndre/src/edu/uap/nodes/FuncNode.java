
package edu.uap.nodes;

import java.util.HashMap;

import edu.uap.AddressPair;

public class FuncNode extends Node
{
    public FuncNode()
    {
        super("FUNC");
    }
    
    //Funktion, welche den Speicher des Funktionsaufruf berechnet und zurückgibt
    public HashMap<String, AddressPair> elab_def(HashMap<String, AddressPair> rho, int nl)	{
    	//....
    	return rho;
    }
}

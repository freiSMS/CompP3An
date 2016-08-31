
package edu.uap.nodes;

import java.util.HashMap;

import edu.uap.AddressPair;
import edu.uap.CompilerFabrik;
import edu.uap.TramLabel;

public class FuncNode extends Node
{
    public FuncNode()
    {
        super("FUNC");
    }
    
    //Funktion, welche den Speicher des Funktionsaufruf berechnet und zurückgibt
    public HashMap<String, AddressPair> elab_def(HashMap<String, AddressPair> rho, int nl)	{
    	//....
    	//CompilerFabrik.addLabel(nl, rho);		//ist das richtig? hier ist keine zahl der key
    	//int label = CompilerFabrik.labelCount;
    	
    	IDNode d1 = (IDNode) this.getChildren().get(0);
    	rho.put(d1.getAttribute().toString(), new AddressPair(new TramLabel(-1),nl ));	//Für jedes dieser Kinder muss eine PseudoInstruktion eingefügt werden
    	return rho;
    }
}


package edu.uap.nodes;

import java.util.HashMap;

import edu.uap.AddressPair;

public class DefNode extends Node
{
    public DefNode()
    {
        super("DEF");
    }
    
    public HashMap<String, AddressPair> elab_def(HashMap<String, AddressPair> rho, int nl)	{
    	//....
    	HashMap<String,AddressPair> rho2 = (HashMap<String, AddressPair>) rho.clone();
    	for(int i=0; i< this.getChildren().size(); i++)	{
    		//das nestingLEvel steigt bei jedem func knoten? -> nl + (i+1) ist das nesting level?
    		rho2 = ((FuncNode)this.getChildren().get(i)).elab_def(rho2, nl);
    	}
    	
    	return rho2;
    }
}

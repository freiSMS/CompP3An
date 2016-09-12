package edu.uap.tram;

import java.util.ArrayList;

public class Stack<LazyObject> extends ArrayList<LazyObject> {
	//Legt das Objekt an der bestimmten Position ab bzw. legt eine neue Position an, wenn diese noch nicht besteht (da die Maschine top immer nur in Einserschritten erhöht ist nur ein add nötig)
	public void push(int position, LazyObject lz){
		if(position >= this.size())	{
			this.add(lz);
			
		}
		else if(position < this.size())	{
			//System.out.println("Das ist was schief gelaufen.");
			this.set(position, lz);
		}
	}
}

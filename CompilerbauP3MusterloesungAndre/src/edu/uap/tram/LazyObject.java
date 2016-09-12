package edu.uap.tram;

public class LazyObject {
	public int val;
	public String tag;

	public LazyObject(int val, String tag) {
		this.val = val;
		this.tag = tag;
	}
	
	@Override
	public String toString()	{
		return ("[ " + val+ ", "+ tag +" ]");
	}
}

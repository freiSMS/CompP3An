package edu.uap;

public class TramLabel {
	int address;
	
	@Override
	public boolean equals(Object tl)	{
		if(tl instanceof Integer)	{
			return false;
		}
		
		//ansonsten werden zwei tramLabel verglichen
		else if(this.address == ((TramLabel) tl).address)	{
			return true;
		}
		else	{
			return false;
		}
	}
}

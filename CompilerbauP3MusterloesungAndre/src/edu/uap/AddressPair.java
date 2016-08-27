package edu.uap;

public class AddressPair {
	Object loc;
	int nl;
	
	@Override
	public boolean equals(Object vergleichspaar)	{
		AddressPair vergl = (AddressPair) vergleichspaar;
		
		//Fall1: this.loc ist ein TramLabel
		if(this.loc instanceof TramLabel)	{
			TramLabel tramloc = (TramLabel) this.loc;
			if(tramloc.equals(vergl.loc) && (this.nl ==(vergl.nl)))	{
				return true;
			}
			else	{
				return false;
			}
		}
		
		//Fall2: this.loc ist vom typ int
		else if(((Integer)this.loc).equals(vergl.loc) && (this.nl ==(vergl.nl)))	{
			return true;
		}
		else	{
			return false;
		}
	}
}

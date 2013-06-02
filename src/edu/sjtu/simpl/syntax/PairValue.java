package edu.sjtu.simpl.syntax;

public class PairValue extends Value{
	public Value e1;
	public Value e2;
	
	public String toString(){
		return "(" + e1.toString() + ", " + e2.toString() + ")";
	}

	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof PairValue))
				return false;
		return e1.equals(((PairValue)obj).e1)&&e2.equals(((PairValue)obj).e2);
	}
	
	
}
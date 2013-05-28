package edu.sjtu.simpl.syntax;

import edu.sjtu.simpl.type.Type;
import edu.sjtu.simpl.util.Log;

public class First extends Expression{
	public Expression e;
	
	public String toString(){
		return "<" +"fst " + e.toString()+">";
	}

	@Override
	public Type getType() {
		if(e instanceof Pair)
		{
			return ((Pair)e).e1.getType();
		}
		
		Log.error("Type Error on First!");
		return null;
	}
	
	
}
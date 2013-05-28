package edu.sjtu.simpl.syntax;

import edu.sjtu.simpl.type.Type;

public class Bracket extends Expression{
	public Expression e;
	
	public String toString(){
		return "<" +"(" + e.toString() + ")"+">";
	}

	@Override
	public Type getType() {
		return e.getType();
	}
	
	
}
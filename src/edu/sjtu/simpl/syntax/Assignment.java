package edu.sjtu.simpl.syntax;

import edu.sjtu.simpl.type.Type;
import edu.sjtu.simpl.type.UnitType;

public class Assignment extends Expression{
	public Expression var;
	public Expression val;
	
	public String toString(){
		return "<" +var.toString() + " := " + val.toString() +">";
	}

	@Override
	public Type getType() {
		return new UnitType();
	}
	
	
}
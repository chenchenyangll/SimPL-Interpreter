package edu.sjtu.simpl.syntax;

import edu.sjtu.simpl.type.BoolType;
import edu.sjtu.simpl.type.Type;

public class BoolValue extends Value{
	public boolean value;

	public String toString(){
		if(value)
			return "true";
		else
			return "false";
	}

	@Override
	public Type getType() {
		return new BoolType();
	}
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof BoolValue))
			return false;
		return value == ((BoolValue)obj).value;
	}
}
package edu.sjtu.simpl.syntax;


public class BoolValue extends Value{
	public boolean value;

	public String toString(){
		if(value)
			return "true";
		else
			return "false";
	}
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof BoolValue))
			return false;
		return value == ((BoolValue)obj).value;
	}
}
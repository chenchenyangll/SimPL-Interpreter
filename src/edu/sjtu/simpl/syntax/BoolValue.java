package edu.sjtu.simpl.syntax;

public class BoolValue extends Value{
	public boolean value;

	public String toString(){
		if(value)
			return "true";
		else
			return "false";
	}
}
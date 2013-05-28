package edu.sjtu.simpl.syntax;

public class IntValue extends Value{
	public boolean isUndef = false;
	public int value;

	public String toString(){
		if(isUndef)
			return "undef";
		else
			return "<" +String.valueOf(value)+">";
	}
}
package edu.sjtu.simpl.syntax;

public class IntValue extends Value{
	public boolean isUndef = false;
	public int value;

	public String toString(){
		if(isUndef)
			return "undef";
		else
			return String.valueOf(value);
	}

	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof IntValue))
			return false;
		return value == ((IntValue)obj).value;
	}
}
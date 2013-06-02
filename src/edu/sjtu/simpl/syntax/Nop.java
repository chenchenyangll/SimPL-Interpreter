package edu.sjtu.simpl.syntax;

public class Nop extends Value{
	public String toString(){
		return "()";
	}

	@Override
	public boolean equals(Object obj) {
		return  (obj instanceof Nop);
	}
	
	
}
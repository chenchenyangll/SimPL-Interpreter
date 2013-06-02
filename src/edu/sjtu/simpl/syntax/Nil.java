package edu.sjtu.simpl.syntax;

public class Nil extends Value{
	public String toString(){
		return "nil";
	}
	
	@Override
	public boolean equals(Object obj) {
		return  (obj instanceof Nil);
	}
}
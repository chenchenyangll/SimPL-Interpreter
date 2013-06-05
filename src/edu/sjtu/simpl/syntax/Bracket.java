package edu.sjtu.simpl.syntax;


public class Bracket extends Expression{
	public Expression e;
	
	public String toString(){
		return "(" + e.toString() + ")";
	}

	
	
}
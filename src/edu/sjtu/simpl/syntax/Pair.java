package edu.sjtu.simpl.syntax;

public class Pair extends Expression{
	public Expression e1;
	public Expression e2;
	
	public String toString(){
		return "(" + e1.toString() + ", " + e2.toString() + ")";
	}
}
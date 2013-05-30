package edu.sjtu.simpl.syntax;

public class Tail extends Expression{
	public Expression e;	
	
	public String toString(){
		return "tail " + e.toString();
	}
}
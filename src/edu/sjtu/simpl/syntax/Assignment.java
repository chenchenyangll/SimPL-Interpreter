package edu.sjtu.simpl.syntax;

public class Assignment extends Expression{
	public Expression var;
	public Expression val;
	
	public String toString(){
		return var.toString() + " := " + val.toString();
	}	
}
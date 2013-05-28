package edu.sjtu.simpl.syntax;

public class LetInEnd extends Expression{
	public Variable x;
	public Expression definition;
	public Expression body;
	
	public String toString(){
		return "<" +"let " + x.toString() + " = " + definition.toString() + " in " + body.toString() + " end"+">";
	}
}
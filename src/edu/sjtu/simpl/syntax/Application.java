package edu.sjtu.simpl.syntax;


public class Application extends Expression{
	
	public Expression func;
	public Expression param;

	public String toString(){
		return "<" +"(" + func.toString() + " " + param.toString() + ")" +">";
	}
}
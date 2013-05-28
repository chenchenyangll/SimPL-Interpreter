package edu.sjtu.simpl.syntax;

public class WhileDoEnd extends Expression{
	public Expression condition;
	public Expression body;
	
	public String toString(){
		return "<" +"while " + condition.toString() + " do " + body.toString() + " end"+">";
	}
}

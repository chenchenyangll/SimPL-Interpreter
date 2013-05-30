package edu.sjtu.simpl.syntax;

public class IfThenElse extends Expression{
	public Expression condition;
	public Expression thenClause;
	public Expression elseClause;
	
	public String toString(){
		return "if " + condition.toString() + " then " + thenClause.toString() + " else " + elseClause.toString();
	}
}
package edu.sjtu.simpl.syntax;

public class Second extends Expression{
	public Expression e;
	
	public String toString(){
		return "<" +"snd " + e.toString()+">";
	}
}
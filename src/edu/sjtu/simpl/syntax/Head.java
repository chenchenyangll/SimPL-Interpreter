package edu.sjtu.simpl.syntax;

public class Head extends Expression{
	public Expression e;
	
	public String toString(){
		return "<" +"head " + e.toString()+">";
	}
}
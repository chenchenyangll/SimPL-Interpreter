package edu.sjtu.simpl.syntax;

public class AnonymousFunction extends Value{
	public Variable arg;
	public Expression body;
	
	public String toString(){
		return "fun " + arg.toString() + " -> " + body.toString();
	}
}
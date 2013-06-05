package edu.sjtu.simpl.syntax;

public class AnonymousFunction extends Value{
	public Variable arg;
	public Expression body;
	
	public AnonymousFunction()
	{
		super();
	}
	
	public String toString(){
		return "fun " + arg.toString() + " -> " + body.toString();
	}

	@Override
	public boolean equals(Object obj) {
		return false;
	}
}
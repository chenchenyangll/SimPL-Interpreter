package edu.sjtu.simpl.syntax;

import edu.sjtu.simpl.type.FunctionType;
import edu.sjtu.simpl.type.Type;

public class AnonymousFunction extends Value{
	

	public Variable arg;
	public Expression body;
	
	public String toString(){
		return "<" + "fun " + arg.toString() + " -> " + body.toString() +">";
	}
	
	@Override
	public Type getType() {
		Type argType = arg.getType();
		Type bodyType = body.getType();
		
		FunctionType t = new FunctionType();
		t.argType = argType;
		t.bodyType = bodyType;
		return t;
	}
	
}
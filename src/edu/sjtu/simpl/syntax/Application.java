package edu.sjtu.simpl.syntax;

import edu.sjtu.simpl.type.FunctionType;
import edu.sjtu.simpl.type.Type;
import edu.sjtu.simpl.util.Log;

public class Application extends Expression{
	
	public Expression func;
	public Expression param;

	public String toString(){
		return "<" +"(" + func.toString() + " " + param.toString() + ")" +">";
	}
	
	@Override
	public Type getType() {
		FunctionType t1 = (FunctionType) func.getType();
		Type t2 = param.getType();
		
		if(!t1.argType.equals(t2))
		{
			Log.log("Type Error!");
			return null;
		}
		
		return t2;
		
	}
}
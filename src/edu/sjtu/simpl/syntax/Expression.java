package edu.sjtu.simpl.syntax;

import edu.sjtu.simpl.type.Type;


public class Expression{
	public Type type;
	
	public Expression()
	{
		type = null;
	}
	
	public void setType(Type t)
	{
		type = t;
	}
	
	public Type getType()
	{
		return type;
	}
}
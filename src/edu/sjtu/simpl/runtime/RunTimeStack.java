package edu.sjtu.simpl.runtime;

import java.util.HashMap;

import edu.sjtu.simpl.syntax.Value;

public class RunTimeStack{
	private HashMap<String, Value> stackFrame;
	
	private RunTimeStack()
	{
		stackFrame = new  HashMap<String, Value>();
	}
	
	private static RunTimeStack rtStack = null;
	public RunTimeStack getInstance()
	{
		if(rtStack == null)
		{
			rtStack = new RunTimeStack();
		}
		
		return rtStack;
	}
	
	public void putPara(String id, Value v)
	{
		stackFrame.put(id, v);
	}
	
	public Value getpara(String id)
	{
		return stackFrame.get(id);
	}
}

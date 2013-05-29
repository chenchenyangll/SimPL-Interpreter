package edu.sjtu.simpl.runtime;

public class RunTimeStack {
	private static State state = null;
	
	private RunTimeStack()
	{
		state = new State(); 
	}
}

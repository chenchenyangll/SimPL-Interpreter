package edu.sjtu.simpl.runtime;

import java.util.HashMap;

public class State {
	private HashMap<String,Integer> env;
	
	public State(){
		env = new HashMap<String,Integer>();
	}
	
	public void put(String id, Integer addr)
	{
		env.put(id, addr);
	}
	
}

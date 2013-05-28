package edu.sjtu.simpl.runtime;

import java.util.HashMap;

import edu.sjtu.simpl.syntax.Value;

public class Memory {
	private HashMap<Integer,Value> m;
	private int lastAddr = 0;
	
	private Memory mem = null;
	private Memory(){}

	Memory getInstance()
	{
		if( mem == null )
		{
			mem = new Memory();
		}
		
		return mem;
	}
	
	private int getFreeAddr()
	{
		return ++lastAddr;
	}
	
	public int allocate(Value v)
	{
		int addr = getFreeAddr();
		m.put(addr, v);
		return addr;
	}
	
	public void dallocate(int addr)
	{
		m.remove(addr);
	}
	
}

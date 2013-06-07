package edu.sjtu.simpl.validate;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

import edu.sjtu.simpl.type.BoolType;
import edu.sjtu.simpl.type.IntType;
import edu.sjtu.simpl.type.Type;

public class TypeMap {
	private HashMap<String,Type> typeMap;
	
	private TypeMap outerBlockTypeMap;
	
	public TypeMap()
	{
		typeMap = new HashMap<String,Type>();
		outerBlockTypeMap = null;
	}
	
	public TypeMap onion(TypeMap onion)
	{
		onion.outerBlockTypeMap = this;
		return onion;
	}
	
	public Type get(String id)
	{
		TypeMap tm = this;
		while(tm != null)
		{
			Type t = tm.typeMap.get(id);
			if(t == null)
				tm = tm.outerBlockTypeMap;
			else
				return t;
		}
		
		return null;
	}
	
	public boolean contains(String id)
	{
		TypeMap tm = this;
		while(tm != null)
		{
			if( tm.typeMap.containsKey(id))
			{
				return true;
			}
			else
			{
				tm = tm.outerBlockTypeMap;
			}
		}
		
		return false;
	}
	
	public void put(String id,Type type)
	{
		this.typeMap.put(id, type);
	}
	
	//exit a block, erase the inner typemap
	public TypeMap exitBlock()
	{
		return this.outerBlockTypeMap;
	}
	
	public void set(String id, Type type)
	{
		TypeMap tm = this;
		while(tm != null)
		{
			Type t = tm.typeMap.get(id);
			if(t == null)
				tm = tm.outerBlockTypeMap;
			else
				break;
		}
		
		//if some outer block typemap contains id
		if(tm != null)
		{
			tm.typeMap.put(id, type);
		}
		else
		{
			this.typeMap.put(id, type);
		}
	}
	
	public void print()
	{
	    System.out.println(this.toString());
	}
	
	public String toString()
	{
		 String typeMsg = "{";
		    
		    TypeMap tm = this;
		    while(tm != null)
		    {
		    	Set set =tm.typeMap.entrySet();
		    	Iterator it=set.iterator();
		    	while(it.hasNext())
		    	{
		    		Entry<String, Type>  entry=(Entry<String, Type>) it.next();
		    		typeMsg += "<"+entry.getKey()+","+entry.getValue().toString()+">,";
		    	}
		    	
		    	tm = tm.outerBlockTypeMap;
		    }
		    typeMsg += "}";
		    
		    return typeMsg;
	}
	
	public static void main(String args[])
	{
		TypeMap tm1 = new TypeMap();
		tm1.put("x", new BoolType());
		tm1.put("y", new IntType());
		
		tm1.print();
		
		TypeMap tm2 = new TypeMap();
		tm2.put("z", new IntType());
		
		tm2.print();
		
		tm1 = tm1.onion(tm2);
		
		tm1.print();
	}
	
}

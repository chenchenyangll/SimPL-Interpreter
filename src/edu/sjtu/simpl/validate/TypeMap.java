package edu.sjtu.simpl.validate;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

import edu.sjtu.simpl.syntax.Expression;
import edu.sjtu.simpl.type.BoolType;
import edu.sjtu.simpl.type.IntType;
import edu.sjtu.simpl.type.Type;

public class TypeMap {
	private HashMap<String,Type> typeMap;
	
	public TypeMap()
	{
		typeMap = new HashMap<String,Type>();
	}
	
	public void put(String id, Type type)
	{
		this.typeMap.put(id, type);
	}
	
	public Type get(String id)
	{
		return typeMap.get(id);
	}
	
	public boolean contains(String id)
	{
		return typeMap.containsKey(id);
	}
	
	public TypeMap onion(TypeMap dest)
	{
		TypeMap tm = new TypeMap();
		//copy origin
		Set set =this.typeMap.entrySet();
	    Iterator it=set.iterator();
	    while(it.hasNext())
	    {
	    	Entry<String, Type>  entry=(Entry<String, Type>) it.next();
	    	tm.put(entry.getKey(),entry.getValue());
	    }
	    
	    //onion with origin
	    set =dest.typeMap.entrySet();
	    it=set.iterator();
	    while(it.hasNext())
	    {
	    	Entry<String, Type>  entry=(Entry<String, Type>) it.next();
	    	tm.put(entry.getKey(),entry.getValue());
	    }
	    
	    return tm;
	}
	
	public void print()
	{
		Set set =this.typeMap.entrySet();
	    Iterator it=set.iterator();
	    String typeMsg = "{";
	    while(it.hasNext())
	    {
	    	Entry<String, Type>  entry=(Entry<String, Type>) it.next();
	    	typeMsg += "<"+entry.getKey()+","+entry.getValue().toString()+">,";
	    }
	    typeMsg += "}";
	    System.out.println(typeMsg);
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

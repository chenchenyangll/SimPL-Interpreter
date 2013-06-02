package edu.sjtu.simpl.syntax;

public class ListValue extends Value{
	public Value head;
	public Value tail;
	
	public String toString(){
		return head.toString() + "::" + tail.toString();
	}
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof ListValue))
			return false;
		return head.equals(((ListValue)obj).head)&&tail.equals(((ListValue)obj).tail);
	}
}
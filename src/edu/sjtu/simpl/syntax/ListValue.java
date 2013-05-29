package edu.sjtu.simpl.syntax;

public class ListValue extends Value{
	public Value head;
	public Value tail;
	
	public String toString(){
		return "[" + head.toString() + ", " + tail.toString() + "]";
	}
}
package edu.sjtu.simpl.syntax;

public class List extends Expression{
	public Expression head;
	public Expression tail;	
	
	public String toString(){
		return  head.toString() + "::" + tail.toString();
	}
}
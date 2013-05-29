package edu.sjtu.simpl.syntax;

public class PairValue extends Value{
	public Value e1;
	public Value e2;
	
	public String toString(){
		return "<" +"(" + e1.toString() + ", " + e2.toString() + ")"+">";
	}
}
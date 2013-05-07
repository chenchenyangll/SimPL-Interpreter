package edu.sjtu.simpl.ui;

import edu.sjtu.simpl.grammar.SimPL;
import edu.sjtu.simpl.grammar.SimpleNode;

public class Console {
	public static void main(String args [])
	{
	  System.out.println("Reading from standard input...");
	  //System.out.print("Enter an expression like \"1+(2+3)*var;\" :");
	  new SimPL(System.in);
	 
		while (true) {
			SimPL.ReInit(System.in);
			try {
				System.out.print("SimPL> ");
				SimpleNode n = SimPL.Program();
				n.dump("");
				System.out.println("-------");
				
			} catch (Exception e) {
				System.out.println("Syntax Error!");
				System.out.println(e.getMessage());
			}
		}

	}
}



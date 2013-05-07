package edu.sjtu.simpl.ui;

import edu.sjtu.simpl.grammar.SimPL;
import edu.sjtu.simpl.grammar.SimpleNode;

public class Console {
	public static void main(String args [])
	{
	  System.out.println("Reading from standard input...");
	  //System.out.print("Enter an expression like \"1+(2+3)*var;\" :");
	  new SimPL(System.in);
	  try
	  {
		while(true)
		{
			System.out.println("SimPL> ");
			SimpleNode n = SimPL.Program();
			n.dump("");
			System.out.println("-------");
			SimPL.ReInit(System.in);
		}
	  }
	  catch (Exception e)
	  {
	    System.out.println("Oops.");
	    System.out.println(e.getMessage());
	  }
	}
}



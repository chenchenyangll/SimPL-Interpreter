package edu.sjtu.simpl.ui;

import visitor.SyntaxVisitor;
import edu.sjtu.simpl.grammar.SimPL;
import edu.sjtu.simpl.grammar.SimpleNode;
import edu.sjtu.simpl.syntax.Expression;

public class Console2 {
	public static void main(String args [])
	{
	  System.out.println("Reading from standard input...");
	  //System.out.print("Enter an expression like \"1+(2+3)*var;\" :");
	  SimPL parser = new SimPL(System.in);
	 
		while (true) {
			parser.ReInit(System.in);
			try {
				System.out.print("SimPL> ");
				SimpleNode n = parser.Program();
				n.dump("---");
				System.out.println("-------");
				
				SyntaxVisitor visitor = new SyntaxVisitor();
				Expression e = (Expression) n.jjtAccept(visitor, null);
				System.out.println(e.toString());
				
			} catch (Exception e) {
				System.out.println("Syntax Error!");
				System.out.println(e.getMessage());
			}
		}

	}
}



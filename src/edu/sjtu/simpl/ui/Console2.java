package edu.sjtu.simpl.ui;

import edu.sjtu.simpl.grammar.SimPL;
import edu.sjtu.simpl.grammar.SimpleNode;
import edu.sjtu.simpl.syntax.Expression;
import edu.sjtu.simpl.visitor.SyntaxVisitor;

public class Console2 {
	public static void main(String args [])
	{
	  System.out.println("Reading from standard input...");
	  //System.out.print("Enter an expression like \"1+(2+3)*var;\" :");
	  SimPL parser = new SimPL(System.in);
	 
		while (true) {
			SimpleNode n;
			parser.ReInit(System.in);
			
			try {
				System.out.print("SimPL> ");
				n = parser.Program();
				n.dump("---");
				System.out.println("-------");
				
				try{
					SyntaxVisitor visitor = new SyntaxVisitor();
					Expression e = (Expression) n.jjtAccept(visitor, null);
					System.out.println(e.toString());
				}
				catch (Exception e)
				{
					System.out.println("visitor error!");
					e.printStackTrace();
					
				}
				
			} catch (Exception e) {
				System.out.println("Syntax Error!");
				e.printStackTrace();
			}
			
			
			
		}

	}
}



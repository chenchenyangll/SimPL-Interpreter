package edu.sjtu.simpl.ui;

import edu.sjtu.simpl.grammar.SimPL;
import edu.sjtu.simpl.grammar.SimpleNode;
import edu.sjtu.simpl.syntax.Expression;
import edu.sjtu.simpl.validate.CompileTimeValidator;
import edu.sjtu.simpl.validate.TypeMap;
import edu.sjtu.simpl.visitor.SyntaxVisitor;

public class Console3 {
	public static void main(String args [])
	{
	  System.out.println("Reading from standard input...");
	  //System.out.print("Enter an expression like \"1+(2+3)*var;\" :");
	  SimPL parser = new SimPL(System.in);
	 
		while (true) {
			SimpleNode n = null;
			parser.ReInit(System.in);
			
			try {
				System.out.print("SimPL> ");
				n = parser.Program();
				n.dump("---");
				System.out.println("-------");
				
			} catch (Exception e) {
				System.out.println("Syntax Error!");
				e.printStackTrace();
				continue;
			}
			
			Expression root = null;
			try{
				SyntaxVisitor visitor = new SyntaxVisitor();
				root = (Expression) n.jjtAccept(visitor, null);
				System.out.println(root.toString());
			}
			catch (Exception e)
			{
				System.out.println("visitor error!");
				e.printStackTrace();
				continue;
			}
			
			CompileTimeValidator validator = new CompileTimeValidator();
			
			validator.V(root, new TypeMap());
		}

	}
}



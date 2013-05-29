package edu.sjtu.simpl.ui;

import edu.sjtu.simpl.grammar.SimPL;
import edu.sjtu.simpl.grammar.SimpleNode;
import edu.sjtu.simpl.runtime.Executor;
import edu.sjtu.simpl.runtime.Memory;
import edu.sjtu.simpl.runtime.State;
import edu.sjtu.simpl.syntax.Expression;
import edu.sjtu.simpl.type.Type;
import edu.sjtu.simpl.util.Log;
import edu.sjtu.simpl.validate.ComplilerValidator;
import edu.sjtu.simpl.validate.TypeMap;
import edu.sjtu.simpl.visitor.SyntaxVisitor;

public class Console4 {
	public static void main(String args [])
	{
	  System.out.println("Reading from standard input...");
	  SimPL parser = new SimPL(System.in);
	 
		while (true) {
			SimpleNode n = null;
			parser.ReInit(System.in);
			
			try {
				System.out.print("SimPL> ");
				n = parser.Program();
				
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
			Log.debug("..................complier time........................");
			
			try{
				ComplilerValidator validator = new ComplilerValidator();
				
				Type t = validator.V(root, new TypeMap());
				if(t!=null)
					Log.info(t.toString());
			}
			catch(Exception e)
			{
				e.printStackTrace();
				continue;
			}
			Log.debug(".................run time.........................");
			Executor exe = new Executor();
			State state = new State();
			Log.rslt(exe.M(root, state).toString());
			Memory.getInstance().clean();
		}

	}
}



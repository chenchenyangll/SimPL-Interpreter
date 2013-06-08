package edu.sjtu.simpl.ui;

import edu.sjtu.simpl.exception.SimPLTypeException;
import edu.sjtu.simpl.exception.SimPLRuntimeException;
import edu.sjtu.simpl.grammar.SimPL;
import edu.sjtu.simpl.grammar.SimpleNode;
import edu.sjtu.simpl.runtime.Executor;
import edu.sjtu.simpl.runtime.Memory;
import edu.sjtu.simpl.runtime.RunTimeState;
import edu.sjtu.simpl.runtime.StateFrame;
import edu.sjtu.simpl.syntax.Expression;
import edu.sjtu.simpl.syntax.Value;
import edu.sjtu.simpl.type.Type;
import edu.sjtu.simpl.util.Log;
import edu.sjtu.simpl.validate.ComplilerValidator;
import edu.sjtu.simpl.validate.TypeMap;
import edu.sjtu.simpl.visitor.SyntaxVisitor;


public class Console4 {
	public static void main(String args [])
	{
	  SimPL parser = new SimPL(System.in);
	 
		while (true) {
			SimpleNode n = null;
			parser.ReInit(System.in);
			
			try {
				System.out.print("SimPL> ");
				n = parser.Program();
				
			} catch (Throwable e) {
				//System.out.println("Syntax Error!");
				//e.printStackTrace();
				//break;
			}
			
			if(n == null)
			{
				System.out.println("SimPL> Syntax Error!");
				continue;
			}
			
			Expression root = null;
			try{
				SyntaxVisitor visitor = new SyntaxVisitor();
				root = (Expression) n.jjtAccept(visitor, null);
				//System.out.println(root.toString());
			}
			catch (Exception e)
			{
				//System.out.println("visitor error!");
				//e.printStackTrace();
				continue;
			}
			//Log.debug("..................complier time........................");
		
			
			Value v = null;
			//Log.debug(".................run time.........................");
				try
				{
					Executor exe = new Executor();
					RunTimeState state = new RunTimeState();
					v = exe.M(root, state);
					Memory.getInstance().clean();
				}
				catch(SimPLTypeException e)
				{
					System.out.println("SimPL> Type Error:"+e.getMessage());
				}
				catch(SimPLRuntimeException e)
				{
					System.out.println("SimPL> runtime error:"+e.getMessage());
				}
			
			if( v != null)
			{
				System.out.println("SimPL> "+v.toString());
			}
		}

	}
}



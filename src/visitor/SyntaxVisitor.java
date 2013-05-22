package visitor;

import java.lang.reflect.InvocationTargetException;

import edu.sjtu.simpl.grammar.ASTAdditiveExp;
import edu.sjtu.simpl.grammar.ASTAndOp;
import edu.sjtu.simpl.grammar.ASTAnonymousFun;
import edu.sjtu.simpl.grammar.ASTApplicationTail;
import edu.sjtu.simpl.grammar.ASTAssignment;
import edu.sjtu.simpl.grammar.ASTBiggerOp;
import edu.sjtu.simpl.grammar.ASTBoolValue;
import edu.sjtu.simpl.grammar.ASTBrackedTail;
import edu.sjtu.simpl.grammar.ASTConditionalExp;
import edu.sjtu.simpl.grammar.ASTDivdeOp;
import edu.sjtu.simpl.grammar.ASTEqualOp;
import edu.sjtu.simpl.grammar.ASTExpTerm;
import edu.sjtu.simpl.grammar.ASTExpression;
import edu.sjtu.simpl.grammar.ASTFstExp;
import edu.sjtu.simpl.grammar.ASTHeadExp;
import edu.sjtu.simpl.grammar.ASTIdentifier;
import edu.sjtu.simpl.grammar.ASTIntValue;
import edu.sjtu.simpl.grammar.ASTInteger;
import edu.sjtu.simpl.grammar.ASTLeftParenthness;
import edu.sjtu.simpl.grammar.ASTLessOp;
import edu.sjtu.simpl.grammar.ASTLetExp;
import edu.sjtu.simpl.grammar.ASTList;
import edu.sjtu.simpl.grammar.ASTMinusOp;
import edu.sjtu.simpl.grammar.ASTMultiveExp;
import edu.sjtu.simpl.grammar.ASTNegationOp;
import edu.sjtu.simpl.grammar.ASTNilValue;
import edu.sjtu.simpl.grammar.ASTNopValue;
import edu.sjtu.simpl.grammar.ASTNotOp;
import edu.sjtu.simpl.grammar.ASTOrOp;
import edu.sjtu.simpl.grammar.ASTPairTail;
import edu.sjtu.simpl.grammar.ASTPlusOp;
import edu.sjtu.simpl.grammar.ASTProgram;
import edu.sjtu.simpl.grammar.ASTSequence;
import edu.sjtu.simpl.grammar.ASTSndExp;
import edu.sjtu.simpl.grammar.ASTTailExp;
import edu.sjtu.simpl.grammar.ASTTimeOp;
import edu.sjtu.simpl.grammar.ASTUopExp;
import edu.sjtu.simpl.grammar.ASTValue;
import edu.sjtu.simpl.grammar.ASTWhileLoopExp;
import edu.sjtu.simpl.grammar.SimPLVisitor;
import edu.sjtu.simpl.grammar.SimpleNode;
import edu.sjtu.simpl.syntax.Expression;
import edu.sjtu.simpl.syntax.Sequence;
import edu.sjtu.simpl.syntax.Variable;

public class SyntaxVisitor implements SimPLVisitor {

	@Override
	public Object visit(SimpleNode node, Object data) {
		try {
			Class[] cargs = new Class[2];
			cargs[0] = node.getClass();
			cargs[1] = Object.class;
			
			Object[] args = new Object[2];
			args[0] = node;
			args[1] = data;
			return SyntaxVisitor.class.getMethod("visit", cargs).invoke(this, args);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Object visit(ASTProgram node, Object data) {
		int ccount = node.jjtGetNumChildren();
		if( ccount != 1 )
		{
			return null;
		}
		
		SimpleNode expr = (SimpleNode) node.jjtGetChild(0);
		
		return expr.jjtAccept(this, data);
	}

	@Override
	public Object visit(ASTExpression node, Object data) {
		System.out.println("visit Expression() called...");
		int ccount = node.jjtGetNumChildren();
		if( ccount != 1 )
		{
			return null;
		}
		SimpleNode expr = (SimpleNode) node.jjtGetChild(0);
		
		return expr.jjtAccept(this, null);
	}

	@Override
	public Object visit(ASTSequence node, Object data) {
		System.out.println("visit Sequence() called...");
		Sequence sequenceRoot = new Sequence();
		
		
		SimpleNode exprNode = (SimpleNode) node.jjtGetChild(0);
		Expression expr = (Expression) exprNode.jjtAccept(this, null);
		
		SimpleNode exprNode2 = (SimpleNode) node.jjtGetChild(1);
		Expression expr2 = (Expression) exprNode2.jjtAccept(this, null);
		sequenceRoot.e1 = expr;
		sequenceRoot.e2 = expr2;
		
		//handle the rest suquence
		for(int i = 2;i < node.jjtGetNumChildren();i++)
		{
			SimpleNode eNode = (SimpleNode) node.jjtGetChild(i);
			Expression e = (Expression) eNode.jjtAccept(this, null);
			
			Sequence seq = new Sequence();
			seq.e1 = sequenceRoot;
			seq.e2 = e;
			sequenceRoot = seq;
		}
		
		return sequenceRoot;
	}

	@Override
	public Object visit(ASTAnonymousFun node, Object data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(ASTAssignment node, Object data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(ASTList node, Object data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(ASTAdditiveExp node, Object data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(ASTMultiveExp node, Object data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(ASTExpTerm node, Object data) {
		System.out.println("visit ExprTerm() called...");
		int ccount = node.jjtGetNumChildren();
		if( ccount != 1 )
		{
			return null;
		}
		SimpleNode expr = (SimpleNode) node.jjtGetChild(0);
		
		return expr.jjtAccept(this, null);
	}

	@Override
	public Object visit(ASTLeftParenthness node, Object data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(ASTBrackedTail node, Object data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(ASTPairTail node, Object data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(ASTApplicationTail node, Object data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(ASTUopExp node, Object data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(ASTConditionalExp node, Object data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(ASTLetExp node, Object data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(ASTWhileLoopExp node, Object data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(ASTFstExp node, Object data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(ASTSndExp node, Object data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(ASTHeadExp node, Object data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(ASTTailExp node, Object data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(ASTIdentifier node, Object data) {
		System.out.println("visit Variable() called...");
		Variable v = new Variable();
		v.name = (String) node.jjtGetValue();
		return v;
	}

	@Override
	public Object visit(ASTInteger node, Object data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(ASTValue node, Object data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(ASTIntValue node, Object data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(ASTBoolValue node, Object data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(ASTNilValue node, Object data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(ASTNopValue node, Object data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(ASTAndOp node, Object data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(ASTOrOp node, Object data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(ASTBiggerOp node, Object data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(ASTLessOp node, Object data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(ASTEqualOp node, Object data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(ASTPlusOp node, Object data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(ASTMinusOp node, Object data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(ASTTimeOp node, Object data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(ASTDivdeOp node, Object data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(ASTNegationOp node, Object data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(ASTNotOp node, Object data) {
		// TODO Auto-generated method stub
		return null;
	}

}

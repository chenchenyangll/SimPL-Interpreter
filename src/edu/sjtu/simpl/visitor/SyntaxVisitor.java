package edu.sjtu.simpl.visitor;

import java.lang.reflect.InvocationTargetException;

import edu.sjtu.simpl.grammar.ASTAdditiveExp;
import edu.sjtu.simpl.grammar.ASTAndOp;
import edu.sjtu.simpl.grammar.ASTAndOrExpr;
import edu.sjtu.simpl.grammar.ASTAnonymousFun;
import edu.sjtu.simpl.grammar.ASTApplicationTail;
import edu.sjtu.simpl.grammar.ASTAssignment;
import edu.sjtu.simpl.grammar.ASTBiggerOp;
import edu.sjtu.simpl.grammar.ASTBoolValue;
import edu.sjtu.simpl.grammar.ASTBracketTail;
import edu.sjtu.simpl.grammar.ASTConditionalExp;
import edu.sjtu.simpl.grammar.ASTDevideOp;
import edu.sjtu.simpl.grammar.ASTELBExpr;
import edu.sjtu.simpl.grammar.ASTEqualOp;
import edu.sjtu.simpl.grammar.ASTExpTerm;
import edu.sjtu.simpl.grammar.ASTExpression;
import edu.sjtu.simpl.grammar.ASTFstExp;
import edu.sjtu.simpl.grammar.ASTHeadExp;
import edu.sjtu.simpl.grammar.ASTIdentifier;
import edu.sjtu.simpl.grammar.ASTIntValue;
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
import edu.sjtu.simpl.grammar.ASTTimesOp;
import edu.sjtu.simpl.grammar.ASTUopExp;
import edu.sjtu.simpl.grammar.ASTValue;
import edu.sjtu.simpl.grammar.ASTWhileLoopExp;
import edu.sjtu.simpl.grammar.SimPLVisitor;
import edu.sjtu.simpl.grammar.SimpleNode;
import edu.sjtu.simpl.syntax.AnonymousFunction;
import edu.sjtu.simpl.syntax.Application;
import edu.sjtu.simpl.syntax.Assignment;
import edu.sjtu.simpl.syntax.BinaryOperation;
import edu.sjtu.simpl.syntax.BoolValue;
import edu.sjtu.simpl.syntax.Bracket;
import edu.sjtu.simpl.syntax.Expression;
import edu.sjtu.simpl.syntax.First;
import edu.sjtu.simpl.syntax.Head;
import edu.sjtu.simpl.syntax.IfThenElse;
import edu.sjtu.simpl.syntax.IntValue;
import edu.sjtu.simpl.syntax.LetInEnd;
import edu.sjtu.simpl.syntax.List;
import edu.sjtu.simpl.syntax.Nil;
import edu.sjtu.simpl.syntax.Nop;
import edu.sjtu.simpl.syntax.Pair;
import edu.sjtu.simpl.syntax.Second;
import edu.sjtu.simpl.syntax.Sequence;
import edu.sjtu.simpl.syntax.Tail;
import edu.sjtu.simpl.syntax.UnaryOperation;
import edu.sjtu.simpl.syntax.Variable;
import edu.sjtu.simpl.syntax.WhileDoEnd;
import edu.sjtu.simpl.util.Log;

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
		Log.log("visit Expression() called...");
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
		Log.log("visit Sequence() called...");
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
		int ccount = node.jjtGetNumChildren();
		if( ccount != 2 )
		{
			Log.log("visitor anonymousefun, child count error!");
			return null;
		}
		
		AnonymousFunction af = new AnonymousFunction();
		//get id
		SimpleNode nid = (SimpleNode) node.jjtGetChild(0);
		Variable id = (Variable) nid.jjtAccept(this, data);
		
		//get body
		SimpleNode nbody = (SimpleNode) node.jjtGetChild(1);
		Expression body = (Expression) nbody.jjtAccept(this, data);
		
		af.arg = id;
		af.body = body;
		
		return af;
	}

	@Override
	public Object visit(ASTAssignment node, Object data) {
		Log.log("visit ASTAssignment() called...");
		Assignment assignRoot = new Assignment();
		
		SimpleNode exprNode = (SimpleNode) node.jjtGetChild(0);
		Expression expr = (Expression) exprNode.jjtAccept(this, null);
		
		SimpleNode exprNode2 = (SimpleNode) node.jjtGetChild(1);
		Expression expr2 = (Expression) exprNode2.jjtAccept(this, null);
		assignRoot.var = expr;
		assignRoot.val = expr2;
		
		Assignment rightMost = assignRoot;
		for(int i = 2;i < node.jjtGetNumChildren(); i++)
		{
			SimpleNode n = (SimpleNode) node.jjtGetChild(i);
			Assignment newAssign = new Assignment();
			newAssign.var = rightMost.val;
			newAssign.val = (Expression) n.jjtAccept(this, data);
			rightMost.val = newAssign;
			rightMost = newAssign;
		}
		return assignRoot;
	}

	@Override
	public Object visit(ASTList node, Object data) {
		Log.log("visit ASTList() called...");
		List listRoot = new List();
		
		SimpleNode exprNode = (SimpleNode) node.jjtGetChild(0);
		Expression expr = (Expression) exprNode.jjtAccept(this, null);
		
		SimpleNode exprNode2 = (SimpleNode) node.jjtGetChild(1);
		Expression expr2 = (Expression) exprNode2.jjtAccept(this, null);
		listRoot.head = expr;
		listRoot.tail = expr2;
		
		List rightMost = listRoot;
		for(int i = 2;i < node.jjtGetNumChildren(); i++)
		{
			SimpleNode n = (SimpleNode) node.jjtGetChild(i);
			List newList = new List();
			newList.head = rightMost.tail;
			newList.tail = (Expression) n.jjtAccept(this, data);
			rightMost.tail = newList;
			rightMost = newList;
		}
		return listRoot;
	}

	@Override
	public Object visit(ASTAdditiveExp node, Object data) {
		Log.log("visit ASTAdditiveExp() called...");
		return visitBop(node,data);
	}
	
	private Object visitBop(SimpleNode node,Object data)
	{
		BinaryOperation bopExprRoot = new BinaryOperation();
		
		//first Expr
		SimpleNode exprNode = (SimpleNode) node.jjtGetChild(0);
		Expression expr = (Expression) exprNode.jjtAccept(this, null);
		
		//op
		SimpleNode op = (SimpleNode) node.jjtGetChild(1);
		setOpType(bopExprRoot,op);
		
		SimpleNode exprNode2 = (SimpleNode) node.jjtGetChild(2);
		Expression expr2 = (Expression) exprNode2.jjtAccept(this, null);
		
		bopExprRoot.e1 = expr;
		bopExprRoot.e2 = expr2;
		
		
		//handle the rest suquence
		for(int i = 3;i < node.jjtGetNumChildren();i+=2)
		{
			BinaryOperation bExpr = new BinaryOperation();
			SimpleNode optype = (SimpleNode) node.jjtGetChild(i);
			
			SimpleNode exprRNode = (SimpleNode) node.jjtGetChild(i+1);
			Expression exprR = (Expression) exprRNode.jjtAccept(this, data);
			
			bExpr.e1 = bopExprRoot;
			setOpType(bExpr,optype);
			bExpr.e2 = exprR;
			
			bopExprRoot = bExpr;
		}
		
		return bopExprRoot;
	}
	
	private void setOpType(BinaryOperation bop, SimpleNode op)
	{
		if( op instanceof ASTPlusOp)
		{
			bop.setOperator("+");
		}
		else if( op instanceof ASTMinusOp)
		{
			bop.setOperator("-");
		}
		else if( op instanceof ASTTimesOp)
		{
			bop.setOperator("*");
		}
		else if( op instanceof ASTDevideOp)
		{
			bop.setOperator("/");
		}
		else if( op instanceof ASTAndOp)
		{
			bop.setOperator("and");
		}
		else if( op instanceof ASTOrOp)
		{
			bop.setOperator("or");
		}
		else if( op instanceof ASTBiggerOp)
		{
			bop.setOperator(">");
		}
		else if( op instanceof ASTLessOp)
		{
			bop.setOperator("<");
		}
		else if( op instanceof ASTEqualOp)
		{
			bop.setOperator("=");
		}
	}

	@Override
	public Object visit(ASTMultiveExp node, Object data) {
		Log.log("visit ASTMultiveExp() called...");
		return visitBop(node,data);
	}
	@Override
	public Object visit(ASTAndOrExpr node, Object data) {
		Log.log("visit ASTAndOrExpr() called...");
		return visitBop(node,data);
	}

	@Override
	public Object visit(ASTELBExpr node, Object data) {
		Log.log("visit ASTELBExpr() called...");
		return visitBop(node,data);
	}

	@Override
	public Object visit(ASTExpTerm node, Object data) {
		Log.log("visit ExprTerm() called...");
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
		Log.log("visit ASTLeftParenthness() called...");
		int ccount = node.jjtGetNumChildren();
		if( ccount != 2 )
		{
			return null;
		}
		
		SimpleNode eNode1 = (SimpleNode) node.jjtGetChild(0);
		Expression e1 = (Expression) eNode1.jjtAccept(this, data);
		
		SimpleNode eNode2 = (SimpleNode) node.jjtGetChild(1);
		if(eNode2 instanceof ASTPairTail)
		{
			Expression e2 = (Expression) eNode2.jjtAccept(this, data);
			Pair pair = new Pair();
			pair.e1 = e1;
			pair.e2 = e2;
			return pair;
		}
		else if(eNode2 instanceof ASTApplicationTail)
		{
			Expression e2 = (Expression) eNode2.jjtAccept(this, data);
			Application app = new Application();
			app.func = e1;
			app.param = e2;
			return app;
		}
		else if(eNode2 instanceof ASTBracketTail)
		{
			Bracket b = new Bracket();
			b.e = e1;
			return b;
		}
		return null;
	}

	@Override
	public Object visit(ASTBracketTail node, Object data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(ASTPairTail node, Object data) {
		Log.log("visit ASTPairTail() called...");
		return node.jjtGetChild(0).jjtAccept(this, data);
	}

	@Override
	public Object visit(ASTApplicationTail node, Object data) {
		Log.log("visit ASTApplicationTail() called...");
		return node.jjtGetChild(0).jjtAccept(this, data);
	}

	@Override
	public Object visit(ASTUopExp node, Object data) {
		Log.log("visit uop() called...");
		
		int ccount = node.jjtGetNumChildren();
		if( ccount != 2 )
		{
			return null;
		}
		
		UnaryOperation uopExpr = new UnaryOperation();
		
		SimpleNode opNode = (SimpleNode) node.jjtGetChild(0);
		if(opNode instanceof ASTNotOp)
		{
			uopExpr.setOpType("not");
		}
		else if(opNode instanceof ASTNegationOp)
		{
			uopExpr.setOpType("~");
		}
		
		SimpleNode expNode = (SimpleNode) node.jjtGetChild(1);
		uopExpr.e = (Expression) expNode.jjtAccept(this, data);
		
		return uopExpr;
	}

	@Override
	public Object visit(ASTConditionalExp node, Object data) {
		int ccount = node.jjtGetNumChildren();
		if( ccount != 3 )
		{
			return null;
		}
		
		IfThenElse conExpr = new IfThenElse();
		
		Expression e1 = (Expression) node.jjtGetChild(0).jjtAccept(this, data);
		Expression e2 = (Expression) node.jjtGetChild(1).jjtAccept(this, data);
		Expression e3 = (Expression) node.jjtGetChild(2).jjtAccept(this, data);
		conExpr.condition = e1;
		conExpr.thenClause = e2;
		conExpr.elseClause = e3;
		
		return conExpr;
	}

	@Override
	public Object visit(ASTLetExp node, Object data) {
		int ccount = node.jjtGetNumChildren();
		if( ccount != 3 )
		{
			return null;
		}
		
		LetInEnd letExpr = new LetInEnd();
		
		Expression e1 = (Expression) node.jjtGetChild(0).jjtAccept(this, data);
		Expression e2 = (Expression) node.jjtGetChild(1).jjtAccept(this, data);
		Expression e3 = (Expression) node.jjtGetChild(2).jjtAccept(this, data);
		letExpr.x = (Variable) e1;
		letExpr.definition = e2;
		letExpr.body = e3;
		
		return letExpr;
	}

	@Override
	public Object visit(ASTWhileLoopExp node, Object data) {
		WhileDoEnd wde = new WhileDoEnd();
		
		//e1:condition
		SimpleNode cn = (SimpleNode) node.jjtGetChild(0);
		Expression conExpr = (Expression) cn.jjtAccept(this, data);
		
		SimpleNode dn = (SimpleNode) node.jjtGetChild(1);
		Expression dExpr = (Expression) dn.jjtAccept(this, data);
		
		wde.condition = conExpr;
		wde.body = dExpr;
		
		return wde;
	}

	@Override
	public Object visit(ASTFstExp node, Object data) {
		First e = new First();
		Expression body = (Expression) node.jjtGetChild(0).jjtAccept(this, data);
		e.e = body;
		return e;
	}

	@Override
	public Object visit(ASTSndExp node, Object data) {
		Second e = new Second();
		Expression body = (Expression) node.jjtGetChild(0).jjtAccept(this, data);
		e.e = body;
		return e;
	}

	@Override
	public Object visit(ASTHeadExp node, Object data) {
		Head headExpr = new Head();
		
		Expression body = (Expression) node.jjtGetChild(0).jjtAccept(this, data);
		headExpr.e = body;
		return headExpr;
	}

	@Override
	public Object visit(ASTTailExp node, Object data) {
		Tail tailExpr = new Tail();
		
		Expression body = (Expression) node.jjtGetChild(0).jjtAccept(this, data);
		tailExpr.e = body;
		return tailExpr;
	}

	@Override
	public Object visit(ASTIdentifier node, Object data) {
		Log.log("visit Variable() called...");
		Variable v = new Variable();
		v.name = (String) node.jjtGetValue();
		return v;
	}

	@Override
	public Object visit(ASTValue node, Object data) {
		Log.log("visit ASTValue called..");
		return node.jjtGetChild(0).jjtAccept(this, data);
	}

	@Override
	public Object visit(ASTIntValue node, Object data) {
		IntValue v = new IntValue();
		v.value = Integer.valueOf((String)node.jjtGetValue());
		return v;
	}

	@Override
	public Object visit(ASTBoolValue node, Object data) {
		Log.log("visit BoolValue called.");
		BoolValue v = new BoolValue();
		String bv = (String) node.jjtGetValue();
		if(bv.equals("true"))
		{
			v.value = true;
		}
		else if(bv.equals("false"))
		{
			v.value = false;
		}
		return v;
	}

	@Override
	public Object visit(ASTNilValue node, Object data) {
		Nil v = new Nil();
		return v;
	}

	@Override
	public Object visit(ASTNopValue node, Object data) {
		Nop v = new Nop();
		return v;
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
	public Object visit(ASTNegationOp node, Object data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(ASTNotOp node, Object data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(ASTTimesOp node, Object data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(ASTDevideOp node, Object data) {
		// TODO Auto-generated method stub
		return null;
	}

}

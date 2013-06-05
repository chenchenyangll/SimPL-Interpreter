package edu.sjtu.simpl.runtime;

import java.lang.reflect.InvocationTargetException;

import edu.sjtu.simpl.runtime.Memory;
import edu.sjtu.simpl.runtime.RunTimeState;
import edu.sjtu.simpl.runtime.StateFrame;
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
import edu.sjtu.simpl.syntax.ListValue;
import edu.sjtu.simpl.syntax.Nil;
import edu.sjtu.simpl.syntax.Nop;
import edu.sjtu.simpl.syntax.Pair;
import edu.sjtu.simpl.syntax.PairValue;
import edu.sjtu.simpl.syntax.Second;
import edu.sjtu.simpl.syntax.Sequence;
import edu.sjtu.simpl.syntax.Tail;
import edu.sjtu.simpl.syntax.UnaryOperation;
import edu.sjtu.simpl.syntax.Value;
import edu.sjtu.simpl.syntax.Variable;
import edu.sjtu.simpl.syntax.WhileDoEnd;
import edu.sjtu.simpl.util.Log;


public class Executor implements IExecutor{

	public Value do_m(Expression e, RunTimeState state)
	{
		Class[] cargs = new Class[2];
		cargs[0] = e.getClass();
		cargs[1] = state.getClass();
		
		Object[] args = new Object[2];
		args[0] = e;
		args[1] = state;
		try {
			return (Value) Executor.class.getMethod("M", cargs).invoke(this, args);
		} catch (IllegalAccessException e1) {
			
			e1.printStackTrace();
			System.exit(0);
		} catch (IllegalArgumentException e1) {
			e1.printStackTrace();
			System.exit(0);
		} catch (InvocationTargetException e1) {
			e1.printStackTrace();
			System.exit(0);
		} catch (NoSuchMethodException e1) {
			e1.printStackTrace();
			System.exit(0);
		} catch (SecurityException e1) {
			e1.printStackTrace();
			System.exit(0);
		}
		return null;
	}

	@Override
	public Value M(Expression e, RunTimeState state) {
		//Log.debug("M Expression called,state is:"+state.toString());
		return do_m(e,state);
	}

	@Override
	public Value M(Value v, RunTimeState state) {
		//Log.debug("M Value called,state is:"+state.toString());
		return (Value) do_m(v,state);
	}

	@Override
	public Value M(Pair pair, RunTimeState state) {
		//Log.debug("M Pair called,state is:"+state.toString());
		PairValue pv = new PairValue();
		pv.e1 = (Value) M(pair.e1,state);
		pv.e2 = (Value) M(pair.e2,state);
		return pv;
	}

	@Override
	public Value M(List list, RunTimeState state) {
		//Log.debug("M List called,state is:"+state.toString());
		ListValue lv = new ListValue();
		lv.head = (Value) M(list.head,state);
		lv.tail = (Value) M(list.tail,state);
		return lv;
	}

	@Override
	public Value M(AnonymousFunction fun, RunTimeState state) {
		//Log.debug("M AnonymousFunction called,state is:"+state.toString());
		return fun;
	}

	@Override
	public Value M(Application app, RunTimeState state) {
		//Log.debug("M Application called,state is:"+state.toString());
		Value para = (Value) M(app.param,state);
		AnonymousFunction fun = (AnonymousFunction) M(app.func, state);
		Value rslt = functionCall(fun, para, state);
		return rslt;
	}

	@Override
	public Value M(Assignment assign, RunTimeState state) {
		//Log.debug("M Assignment called,state is:"+state.toString());
		Variable var = (Variable) assign.var;
		Value v = (Value) M(assign.val, state);
		Memory.getInstance().set(state.get(var.name), v);
		return new Nop();
	}

	@Override
	public Value M(BinaryOperation bop, RunTimeState state) {
		//Log.debug("M BinaryOperation called,state is:"+state.toString());
		
		return applyBop(bop,state);
	}
	
	public Value applyBop(BinaryOperation bop, RunTimeState state)
	{
		String op = bop.getOperator();
		if(op.equals("+"))
		{
			IntValue v1 = (IntValue) M(bop.e1,state);
			IntValue v2 = (IntValue) M(bop.e2,state);
			IntValue v = new IntValue();
			v.value = v1.value+v2.value;
			return v;
		}
		else if(op.equals("-"))
		{
			IntValue v1 = (IntValue) M(bop.e1,state);
			IntValue v2 = (IntValue) M(bop.e2,state);
			IntValue v = new IntValue();
			v.value = v1.value-v2.value;
			return v;
		}
		else if(op.equals("*"))
		{
			IntValue v1 = (IntValue) M(bop.e1,state);
			IntValue v2 = (IntValue) M(bop.e2,state);
			IntValue v = new IntValue();
			v.value = v1.value*v2.value;
			return v;
		}
		else if(op.equals("/"))
		{
			IntValue v1 = (IntValue) M(bop.e1,state);
			IntValue v2 = (IntValue) M(bop.e2,state);
			IntValue v = new IntValue();
			if(v2.value == 0)
			{
				Log.error(bop.e2.toString()+" can not be 0");
				v = null;
			}
			else
			{
				v.value = v1.value/v2.value;
			}
			
			return v;
		}
		else if(op.equals("="))
		{
			Value v1 = (Value) M(bop.e1,state);
			Value v2 = (Value) M(bop.e2,state);
			BoolValue v = new BoolValue();
			v.value = (v1.equals(v2));
			return v;
		}
		else if(op.equals(">"))
		{
			IntValue v1 = (IntValue) M(bop.e1,state);
			IntValue v2 = (IntValue) M(bop.e2,state);
			BoolValue v = new BoolValue();
			v.value = (v1.value > v2.value);
			return v;
		}
		else if(op.equals("<"))
		{
			IntValue v1 = (IntValue) M(bop.e1,state);
			IntValue v2 = (IntValue) M(bop.e2,state);
			BoolValue v = new BoolValue();
			v.value = (v1.value < v2.value);
			return v;
		}
		else if(op.equals("and"))
		{
			BoolValue v1 = (BoolValue) M(bop.e1,state);
			BoolValue v2 = (BoolValue) M(bop.e2,state);
			BoolValue v = new BoolValue();
			v.value = (v1.value&&v2.value);
			return v;
		}
		else if(op.equals("or"))
		{
			BoolValue v1 = (BoolValue) M(bop.e1,state);
			BoolValue v2 = (BoolValue) M(bop.e2,state);
			BoolValue v = new BoolValue();
			v.value = (v1.value||v2.value);
			return v;
		}
		
		return null;
		
	}

	@Override
	public Value M(BoolValue bv, RunTimeState state) {
		//Log.debug("M BoolValue called,state is:"+state.toString());
		return bv;
	}

	@Override
	public Value M(Bracket brket, RunTimeState state) {
		//Log.debug("M Bracket called,state is:"+state.toString());
		return (Value) M(brket.e,state);
	}

	@Override
	public Value M(First fst, RunTimeState state) {
		//Log.debug("M First called,state is:"+state.toString());
		PairValue pv = (PairValue) M(fst.e, state);
		return M(pv.e1,state);
	}

	@Override
	public Value M(Head head, RunTimeState state) {
		//Log.debug("M Head called,state is:"+state.toString());
		Value headBody =  M(head.e,state);
		if(headBody instanceof Nil)
		{
			return new Nil();
		}
		else if(headBody instanceof ListValue)
		{
			return M(((ListValue)headBody).head,state);
		}
		
		Log.error("should not reach here..");
		return null;
	}

	@Override
	public Value M(IfThenElse ite, RunTimeState state) {
		//Log.debug("M IfThenElse called,state is:"+state.toString());
		BoolValue bv = (BoolValue) M(ite.condition,state);
		if(bv.value == true)
			return (Value) M(ite.thenClause,state);
		else
			return (Value) M(ite.elseClause, state);
	}

	@Override
	public Value M(IntValue intValue, RunTimeState state) {
		//Log.debug("M IntValue called,state is:"+state.toString());
		return intValue;
	}

	
	@Override
	public Value M(LetInEnd letin, RunTimeState state) {
		//Log.debug("M LetInEnd called,state is:"+state.toString());
		
		StateFrame nst = new StateFrame();
		// prefetch definition type
		//Log.debug("........M definition start...");
		Value v = (Value) M(letin.definition, state);
		//Log.debug("........M definition end...");
		//Log.debug(v.toString());
		Integer addr = Memory.getInstance().allocate(v);
		nst.put(letin.x.name, addr);
		state.popin(nst);
		
		//execute in
		Value vrst = (Value) M(letin.body,state);
		state.popout();
		return vrst;
	}

	@Override
	public Value M(Nil nil, RunTimeState state) {
		//Log.debug("M Nil called,state is:"+state.toString());
		return new Nil();
	}

	@Override
	public Value M(Nop nop, RunTimeState state) {
		//Log.debug("M Nop called,state is:"+state.toString());
		return new Nop();
	}

	@Override
	public Value M(Second scd, RunTimeState state) {
		//Log.debug("M Second called,state is:"+state.toString());
		PairValue pv = (PairValue) M(scd.e, state);
		return M(pv.e2, state);
	}

	@Override
	public Value M(Sequence seq, RunTimeState state) {
		//Log.debug("M Sequence called,state is:"+state.toString());
		M(seq.e1,state);
		
		return (Value) M(seq.e2, state);
	}

	@Override
	public Value M(Tail tail, RunTimeState state) {
		//Log.debug("M Tail called,state is:"+state.toString());
		Value tailBody =  M(tail.e,state);
		if(tailBody instanceof Nil)
		{
			return new Nil();
		}
		else if(tailBody instanceof ListValue)
		{
			return M(((ListValue)tailBody).tail,state);
		}
		
		Log.error("should not reach here..");
		return null;
	}

	@Override
	public Value M(UnaryOperation uop, RunTimeState state) {
		//Log.debug("M UnaryOperation called,state is:"+state.toString());
		String op = uop.getOperator();
		if(op.equals("~"))
		{
			IntValue v = (IntValue) M(uop.e,state);
			v.value = (-v.value);
			return v;
		}
		else if(op.equals("not"))
		{
			BoolValue v = (BoolValue) M(uop.e, state);
			v.value = (!v.value);
			return v;
		}
		return null;
	}

	@Override
	public Value M(Variable var, RunTimeState state) {
		//Log.debug("M Variable called,state is:"+state.toString());
		return (Value) do_m(getVarValue(var.name,state),state);
	}

	@Override
	public Value M(WhileDoEnd wde, RunTimeState state) {
		//Log.debug("M WhileDoEnd called,state is:"+state.toString());
		
		BoolValue bv = (BoolValue) M(wde.condition, state);
		while(bv.value == true)
		{
			M(wde.body,state);
			bv = (BoolValue) M(wde.condition, state);
		}
		return new Nop();
	}
	
	private Value getVarValue(String id, RunTimeState state)
	{
		Integer addr = state.get(id);
		if(addr == null)
			return null;
		else
		{
			return Memory.getInstance().getValue(addr);
		}
	}

	@Override
	public Value functionCall(AnonymousFunction fun, Value para, RunTimeState state) {
		//Log.debug("call "+fun.toString()+",state is:"+state.toString()+",para:"+para.toString());
		StateFrame nst = new StateFrame();
		int addr = Memory.getInstance().allocate(para);
		nst.put(fun.arg.name, addr);
		state.popin(nst);
		Value rst = (Value) M(fun.body, state);
		//to check if the rst is AnomymousFunction
		if(rst instanceof AnonymousFunction)
		{
			//Log.debug("rst is fun:"+rst.toString());
			StateFrame sf = new StateFrame();
			sf.put(fun.arg.name, addr);
			rst = (AnonymousFunction) Var2Val(rst, sf);
			//Log.debug("after var2val:"+rst.toString());
		}
		state.popout();
		return rst;
	}
	
	@Override
	public Value M(PairValue pair, RunTimeState state) {
		//Log.debug("M PairValue called,state is:"+state.toString());
		return pair;
	}

	@Override
	public Value M(ListValue list, RunTimeState state) {
		//Log.debug("M ListValue called,state is:"+state.toString());
		return list;
	}

	
	////////////////////********Var2Val*******//////////////////////
	
	public Expression do_transfer(Expression e, StateFrame state)
	{
		Class[] cargs = new Class[2];
		cargs[0] = e.getClass();
		cargs[1] = state.getClass();
		
		Object[] args = new Object[2];
		args[0] = e;
		args[1] = state;
		try {
			return (Expression) Executor.class.getMethod("Var2Val", cargs).invoke(this, args);
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalArgumentException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InvocationTargetException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (NoSuchMethodException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SecurityException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return null;
	}
	
	
	@Override
	public Expression Var2Val(Expression e, StateFrame state) {
		return do_transfer(e, state);
	}

	@Override
	public Expression Var2Val(Value v, StateFrame state) {
		return do_transfer(v, state);
	}

	@Override
	public Expression Var2Val(Pair pair, StateFrame state) {
		//Log.debug("Var2Val Pair called,state is "+state.toString());
		Expression e1 = Var2Val(pair.e1, state);
		Expression e2 = Var2Val(pair.e2, state);
		
		Pair p = new Pair();
		p.e1 = e1;
		p.e2 = e2;
		return p;
	}

	@Override
	public Expression Var2Val(PairValue pair, StateFrame state) {
		//Log.debug("Var2Val PairValue called,state is "+state.toString());
		return pair;
	}

	@Override
	public Expression Var2Val(List list, StateFrame state) {
		//Log.debug("Var2Val List called,state is "+state.toString());
		Expression head = Var2Val(list.head, state);
		Expression tail = Var2Val(list.tail, state);
		
		List l = new List();
		l.head = head;
		l.tail = tail;
		return l;
	}

	@Override
	public Expression Var2Val(ListValue list, StateFrame state) {
		//Log.debug("Var2Val ListValue called,state is "+state.toString());
		return list;
	}

	@Override
	public Expression Var2Val(AnonymousFunction fun, StateFrame state) {
		//Log.debug("Var2Val AnonymousFunction called,state is "+state.toString());
		Variable v = fun.arg;
		Expression body = Var2Val(fun.body, state);
		
		AnonymousFunction f = new AnonymousFunction();
		f.arg = v;
		f.body = body;
		return f;
	}

	@Override
	public Expression Var2Val(Application app, StateFrame state) {
		//Log.debug("Var2Val Application called,state is "+state.toString());
		Expression func = Var2Val(app.func, state);
		Expression param = Var2Val(app.param, state);
		
		Application  ap = new Application();
		ap.func = func;
		ap.param = param;
		return ap;
	}

	@Override
	public Expression Var2Val(Assignment assign, StateFrame state) {
		//Log.debug("Var2Val Assignment called,state is "+state.toString());
		Expression var = Var2Val(assign.var, state);
		Expression val = Var2Val(assign.val, state);
		
		Assignment as = new Assignment();
		as.var = var;
		as.val = val;
		return as;
	}

	@Override
	public Expression Var2Val(BinaryOperation bop, StateFrame state) {
		//Log.debug("Var2Val BinaryOperation called,state is "+state.toString());
		Expression e1 = Var2Val(bop.e1, state);
		Expression e2 = Var2Val(bop.e2, state);
		
		BinaryOperation binaryOp = new BinaryOperation();
		binaryOp.e1 = e1;
		binaryOp.e2 = e2;
		binaryOp.op = bop.op;
		return binaryOp;
	}

	@Override
	public Expression Var2Val(BoolValue bv, StateFrame state) {
		//Log.debug("Var2Val BoolValue called,state is "+state.toString());
		return bv;
	}

	@Override
	public Expression Var2Val(Bracket brket, StateFrame state) {
		//Log.debug("Var2Val PairValue called,state is "+state.toString());
		Expression e = Var2Val(brket.e, state);
		Bracket b = new Bracket();
		b.e = e;
		return b;
	}

	@Override
	public Expression Var2Val(First fst, StateFrame state) {
		//Log.debug("Var2Val First called,state is "+state.toString());
		Expression e = Var2Val(fst.e, state);
		First f = new First();
		f.e = e;
		return f;
	}

	@Override
	public Expression Var2Val(Head head, StateFrame state) {
		//Log.debug("Var2Val Head called,state is "+state.toString());
		Expression e = Var2Val(head.e, state);
		
		Head h = new Head();
		h.e = e;
		return h;
	}

	@Override
	public Expression Var2Val(IfThenElse ite, StateFrame state) {
		//Log.debug("Var2Val IfThenElse called,state is "+state.toString());
		
		Expression conExpr = Var2Val(ite.condition, state);
		Expression thenExpr = Var2Val(ite.thenClause, state);
		Expression elseExpr = Var2Val(ite.elseClause, state);
		
		IfThenElse i = new IfThenElse();
		i.condition = conExpr;
		i.elseClause = elseExpr;
		i.thenClause = thenExpr;
		
		return i;
	}

	@Override
	public Expression Var2Val(IntValue intValue, StateFrame state) {
		//Log.debug("Var2Val IntValue called,state is "+state.toString());
		return intValue;
	}

	@Override
	public Expression Var2Val(LetInEnd letin, StateFrame state) {
		//Log.debug("Var2Val LetInEnd called,state is "+state.toString());
		Variable x = letin.x;
		Expression def = Var2Val(letin.definition, state);
		Expression body = Var2Val(letin.body, state);
		
		LetInEnd lie = new LetInEnd();
		lie.x = x;
		lie.body = body;
		lie.definition = def;
		return lie;
	}

	@Override
	public Expression Var2Val(Nil nil, StateFrame state) {
		//Log.debug("Var2Val Nil called,state is "+state.toString());
		return nil;
	}

	@Override
	public Expression Var2Val(Nop nop, StateFrame state) {
		//Log.debug("Var2Val Nop called,state is "+state.toString());
		return nop;
	}

	@Override
	public Expression Var2Val(Second scd, StateFrame state) {
		//Log.debug("Var2Val Second called,state is "+state.toString());
		Expression e = Var2Val(scd.e, state);
		Second s = new Second();
		s.e = e;
		return s;
	}

	@Override
	public Expression Var2Val(Sequence seq, StateFrame state) {
		//Log.debug("Var2Val Sequence called,state is "+state.toString());
		Expression e1 = Var2Val(seq.e1, state);
		Expression e2 = Var2Val(seq.e2, state);
		
		Sequence s = new Sequence();
		s.e2 = e2;
		s.e1 = e1;
		return s;
	}

	@Override
	public Expression Var2Val(Tail tail, StateFrame state) {
		//Log.debug("Var2Val Tail called,state is "+state.toString());
		Expression e = Var2Val(tail.e, state);
		
		Tail t = new Tail();
		t.e = e;
		return t;
	}

	@Override
	public Expression Var2Val(UnaryOperation uop, StateFrame state) {
		//Log.debug("Var2Val UnaryOperation called,state is "+state.toString());
		Expression e = Var2Val(uop.e, state);
		
		UnaryOperation u = new UnaryOperation();
		u.e = e;
		u.op = uop.op;
		return u;
	}

	@Override
	public Expression Var2Val(Variable var, StateFrame state) {
		//Log.debug("Var2Val Variable called,state is "+state.toString());
		if(!state.contains(var.name))
		{
			return var;
		}
		Integer addr = state.get(var.name);
		
		if(!Memory.getInstance().contains(addr))
		{
			return var;
		}
		
		Value v = Memory.getInstance().getValue(addr);
		if(v == null || v instanceof AnonymousFunction)
		{
			return var;
		}
		else
		{
			return v;
		}
	}

	@Override
	public Expression Var2Val(WhileDoEnd wde, StateFrame state) {
		//Log.debug("Var2Val WhileDoEnd called,state is "+state.toString());
		Expression cond = Var2Val(wde.condition, state);
		Expression body = Var2Val(wde.body, state);
		
		WhileDoEnd w = new WhileDoEnd();
		w.condition = cond;
		w.body = body;
		return w;
	}

	
	
}

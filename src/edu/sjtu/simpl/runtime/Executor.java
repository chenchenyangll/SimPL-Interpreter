package edu.sjtu.simpl.runtime;

import java.lang.reflect.InvocationTargetException;

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
import edu.sjtu.simpl.validate.ComplilerValidator;


public class Executor implements IExecutor{

	public Object do_m(Expression e, State state)
	{
		Class[] cargs = new Class[2];
		cargs[0] = e.getClass();
		cargs[1] = state.getClass();
		
		Object[] args = new Object[2];
		args[0] = e;
		args[1] = state;
		try {
			return Executor.class.getMethod("M", cargs).invoke(this, args);
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
	public Object M(Expression e, State state) {
		Log.debug("M Expression called,state is:"+state.toString());
		return do_m(e,state);
	}

	@Override
	public Value M(Value v, State state) {
		Log.debug("M Value called,state is:"+state.toString());
		return (Value) do_m(v,state);
	}

	@Override
	public Value M(Pair pair, State state) {
		Log.debug("M Pair called,state is:"+state.toString());
		PairValue pv = new PairValue();
		pv.e1 = (Value) M(pair.e1,state);
		pv.e2 = (Value) M(pair.e2,state);
		return pv;
	}

	@Override
	public Value M(List list, State state) {
		Log.debug("M List called,state is:"+state.toString());
		ListValue lv = new ListValue();
		lv.head = (Value) M(list.head,state);
		lv.tail = (Value) M(list.tail,state);
		return lv;
	}

	@Override
	public Value M(AnonymousFunction fun, State state) {
		Log.debug("M AnonymousFunction called,state is:"+state.toString());
		return fun;
	}

	@Override
	public Value M(Application app, State state) {
		Log.debug("M Application called,state is:"+state.toString());
		AnonymousFunction fun = (AnonymousFunction) M(app.func, state);
		Value para = (Value) M(app.param,state);
		return functionCall(fun, para, state);
	}

	@Override
	public Value M(Assignment assign, State state) {
		Log.debug("M Assignment called,state is:"+state.toString());
		Variable var = (Variable) assign.var;
		Value v = (Value) M(assign.val, state);
		Memory.getInstance().set(state.get(var.name), v);
		return new Nop();
	}

	@Override
	public Value M(BinaryOperation bop, State state) {
		Log.debug("M BinaryOperation called,state is:"+state.toString());
		
		return applyBop(bop,state);
	}
	
	public Value applyBop(BinaryOperation bop, State state)
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
				Log.error(bop.e2.toString()+"can not be 0");
				v.value=0;
			}
			else
			{
				v.value = v1.value/v2.value;
			}
			
			return v;
		}
		else if(op.equals("="))
		{
			IntValue v1 = (IntValue) M(bop.e1,state);
			IntValue v2 = (IntValue) M(bop.e2,state);
			BoolValue v = new BoolValue();
			v.value = (v1.value == v2.value);
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
	public Value M(BoolValue bv, State state) {
		Log.debug("M BoolValue called,state is:"+state.toString());
		return bv;
	}

	@Override
	public Value M(Bracket brket, State state) {
		Log.debug("M Bracket called,state is:"+state.toString());
		return (Value) M(brket.e,state);
	}

	@Override
	public Value M(First fst, State state) {
		Log.debug("M First called,state is:"+state.toString());
		PairValue pv = (PairValue) M(fst.e, state);
		return M(pv.e1,state);
	}

	@Override
	public Value M(Head head, State state) {
		Log.debug("M Head called,state is:"+state.toString());
		ListValue lv = (ListValue) M(head.e,state);
		return M(lv.head,state);
	}

	@Override
	public Value M(IfThenElse ite, State state) {
		Log.debug("M IfThenElse called,state is:"+state.toString());
		BoolValue bv = (BoolValue) M(ite.condition,state);
		if(bv.value == true)
			return (Value) M(ite.thenClause,state);
		else
			return (Value) M(ite.elseClause, state);
	}

	@Override
	public Value M(IntValue intValue, State state) {
		Log.debug("M IntValue called,state is:"+state.toString());
		return intValue;
	}

	
	@Override
	public Value M(LetInEnd letin, State state) {
		Log.debug("M LetInEnd called,state is:"+state.toString());
		
		State nst = new State();
		// prefetch definition type
		Log.debug("........M definition start...");
		Value v = (Value) M(letin.definition, state);
		Log.debug("........M definition end...");
		Log.debug(v.toString());
		Integer addr = Memory.getInstance().allocate(v);
		nst.put(letin.x.name, addr);
		nst = state.onion(nst);
		
		//execute in
		Value vrst = (Value) M(letin.body,nst);
		return vrst;
	}

	@Override
	public Value M(Nil nil, State state) {
		Log.debug("M Nil called,state is:"+state.toString());
		return new Nil();
	}

	@Override
	public Value M(Nop nop, State state) {
		Log.debug("M Nop called,state is:"+state.toString());
		return new Nop();
	}

	@Override
	public Value M(Second scd, State state) {
		Log.debug("M Second called,state is:"+state.toString());
		PairValue pv = (PairValue) M(scd.e, state);
		return M(pv.e2, state);
	}

	@Override
	public Value M(Sequence seq, State state) {
		Log.debug("M Sequence called,state is:"+state.toString());
		M(seq.e1,state);
		
		return (Value) M(seq.e2, state);
	}

	@Override
	public Value M(Tail tail, State state) {
		Log.debug("M Tail called,state is:"+state.toString());
		ListValue lv = (ListValue) M(tail.e, state);
		return M(lv.tail,state);
	}

	@Override
	public Value M(UnaryOperation uop, State state) {
		Log.debug("M UnaryOperation called,state is:"+state.toString());
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
	public Value M(Variable var, State state) {
		Log.debug("M Variable called,state is:"+state.toString());
		return (Value) do_m(getVarValue(var.name,state),state);
	}

	@Override
	public Value M(WhileDoEnd wde, State state) {
		Log.debug("M WhileDoEnd called,state is:"+state.toString());
		BoolValue bv = (BoolValue) M(wde.condition, state);
		
		if(bv.value == true)
		{
			M(wde.body,state);
		}
		return new Nop();
	}
	
	private Value getVarValue(String id, State state)
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
	public Value functionCall(AnonymousFunction fun, Value para, State state) {
		Log.debug("functionCall called,state is:"+state.toString()+",para:"+para.toString());
		State nst = new State();
		int addr = Memory.getInstance().allocate(para);
		nst.put(fun.arg.name, addr);
		nst = state.onion(nst);
		return (Value) M(fun.body, nst);
	}

	@Override
	public Value M(PairValue pair, State state) {
		Log.debug("M PairValue called,state is:"+state.toString());
		return pair;
	}

	@Override
	public Value M(ListValue list, State state) {
		Log.debug("M ListValue called,state is:"+state.toString());
		return list;
	}
	
}

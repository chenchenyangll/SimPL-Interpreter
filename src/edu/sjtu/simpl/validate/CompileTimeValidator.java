package edu.sjtu.simpl.validate;

import java.lang.reflect.InvocationTargetException;

import edu.sjtu.simpl.syntax.AnonymousFunction;
import edu.sjtu.simpl.syntax.Application;
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
import edu.sjtu.simpl.syntax.Pair;
import edu.sjtu.simpl.syntax.Second;
import edu.sjtu.simpl.syntax.Sequence;
import edu.sjtu.simpl.syntax.Tail;
import edu.sjtu.simpl.syntax.UnaryOperation;
import edu.sjtu.simpl.syntax.Value;
import edu.sjtu.simpl.syntax.Variable;
import edu.sjtu.simpl.syntax.WhileDoEnd;
import edu.sjtu.simpl.type.IntType;
import edu.sjtu.simpl.type.Type;
import edu.sjtu.simpl.util.Log;

//do validate(type checking, null reference..)
public class CompileTimeValidator implements IValidator{

	public boolean validate(Expression e, TypeMap tm)
	{
		Class[] cargs = new Class[2];
		cargs[0] = e.getClass();
		cargs[1] = tm.getClass();
		
		Object[] args = new Object[2];
		args[0] = e;
		args[1] = tm;
		try {
			return (boolean) CompileTimeValidator.class.getMethod("V", cargs).invoke(this, args);
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
		
		return false;
	}
	@Override
	public boolean V(Expression e, TypeMap tm) {
		Log.log("validate Expression called..");
		return validate(e, tm);
	}

	@Override
	public boolean V(AnonymousFunction fun, TypeMap tm) {
		Log.log("validate AnonymousFunction called..");
		return false;
	}

	@Override
	public boolean V(Application app, TypeMap tm) {
		Log.log("validate Application called..");
		return false;
	}

	@Override
	public boolean V(BinaryOperation bop, TypeMap tm) {
		Log.log("validate BinaryOperation called..");
		
		if(bop.getOperator().equals("+")
				||bop.getOperator().equals("-")
				||bop.getOperator().equals("*")
				||bop.getOperator().equals("/")
				||bop.getOperator().equals(">")
				||bop.getOperator().equals("<")
				)
		{
			return VIntOp(bop, tm);
		}
		else if(bop.getOperator().equals("and")
				||bop.getOperator().equals("or"))
		{
			return VBoolOp(bop, tm);
		}
		
		return  false;
	}
	
	//check all variable declared and type is int
	private boolean VIntOp(BinaryOperation bop, TypeMap tm)
	{
		Log.log("validate VIntBOp called..");
		return VIntOpItem(bop.e1,tm)&&VIntOpItem(bop.e2,tm);
	}
	
	private boolean VIntOpItem(Expression e, TypeMap tm)
	{
		if(e instanceof Value)
		{
			if(!(e instanceof IntValue))
			{
				Log.log(e.toString()+"type error, expected 'int'");
				return false;
			}
			
			return true;
		}
		else if(e instanceof Variable)
		{
			if(!(tm.contains(((Variable) e).name)))
			{
				Log.log(e.toString()+" not declared。。");
				return false;
			}
			else if(!(tm.get(((Variable) e).name).equals(Type.INT)))
			{
				Log.log(e.toString()+"type error, expected 'int',"+tm.get(((Variable) e).name).toString()+" given。。");
				return false;
			}
			
			return true;
			
		}
		
		return V(e,tm)&&getType(e,tm).equals(Type.INT);
	}
	
	private boolean VBoolOp(BinaryOperation bop, TypeMap tm)
	{
		Log.log("validate VBoolBOp called..");
		return VBoolOpItem(bop.e1,tm)&&VIntOpItem(bop.e2,tm);
	}	
	
	private boolean VBoolOpItem(Expression e, TypeMap tm)
	{
		if(e instanceof Value)
		{
			if(!(e instanceof BoolValue))
			{
				return false;
			}
		}
		else if(e instanceof Variable)
		{
			if(!(tm.contains(((Variable) e).name)))
			{
				Log.log(e.toString()+" not declared。。");
				return false;
			}
			else if(!(tm.get(((Variable) e).name).equals(Type.BOOL)))
			{
				Log.log(e.toString()+"type error, expected 'bool',"+tm.get(((Variable) e).name).toString()+" given。。");
				return false;
			}
		}
		
		return V(e,tm)&&getType(e,tm).equals(Type.BOOL);
	}
	

	@Override
	public boolean V(BoolValue bv, TypeMap tm) {
		Log.log("validate BoolValue called..");
		return false;
	}

	@Override
	public boolean V(Bracket brket, TypeMap tm) {
		Log.log("validate Bracket called..");
		return false;
	}

	@Override
	public boolean V(First fst, TypeMap tm) {
		Log.log("validate First called..");
		return false;
	}

	@Override
	public boolean V(Head head, TypeMap tm) {
		Log.log("validate Head called..");
		return false;
	}

	@Override
	public boolean V(IfThenElse ite, TypeMap tm) {
		Log.log("validate IfThenElse called..");
		return false;
	}

	@Override
	public boolean V(IntValue intValue, TypeMap tm) {
		Log.log("validate IntValue called..");
		return true;
	}

	@Override
	public boolean V(LetInEnd letin, TypeMap tm) {
		Log.log("validate LetInEnd called..");
		
		if(!V(letin.definition,tm))
		{
			return false;
		}
		
		String var = letin.x.name;
		Type t = getType(letin.definition,tm);
		TypeMap blockTm = new TypeMap();
		blockTm.put(var, t);
		blockTm = tm.onion(blockTm);
		return V(letin.body, blockTm);
	}

	@Override
	public boolean V(List list, TypeMap tm) {
		Log.log("validate List called..");
		return false;
	}

	@Override
	public boolean V(ListValue listValue, TypeMap tm) {
		Log.log("validate ListValue called..");
		return false;
	}

	@Override
	public boolean V(Nil nil, TypeMap tm) {
		Log.log("validate Nil called..");
		return false;
	}

	@Override
	public boolean V(Pair pair, TypeMap tm) {
		Log.log("validate Pair called..");
		return false;
	}

	@Override
	public boolean V(Second scd, TypeMap tm) {
		Log.log("validate Second called..");
		return false;
	}

	@Override
	public boolean V(Sequence seq, TypeMap tm) {
		Log.log("validate Sequence called..");
		return false;
	}

	@Override
	public boolean V(Tail tail, TypeMap tm) {
		Log.log("validate Tail called..");
		return false;
	}

	@Override
	public boolean V(UnaryOperation uop, TypeMap tm) {
		Log.log("validate UnaryOperation called..");
		return false;
	}

	@Override
	public boolean V(Value v, TypeMap tm) {
		Log.log("validate Value called..");
		return false;
	}

	@Override
	public boolean V(Variable var, TypeMap tm) {
		Log.log("validate Variable called..");
		return true;
	}

	@Override
	public boolean V(WhileDoEnd wde, TypeMap tm) {
		Log.log("validate WhileDoEnd called..");
		return false;
	}
	
	
	
	
	public Type getType(Expression e, TypeMap tm)
	{
		Class[] cargs = new Class[2];
		cargs[0] = e.getClass();
		cargs[1] = tm.getClass();
		
		Object[] args = new Object[2];
		args[0] = e;
		args[1] = tm;
		try {
			return (Type) CompileTimeValidator.class.getMethod("typeOf", cargs).invoke(this, args);
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
	// get Type
	@Override
	public Type typeOf(Expression e, TypeMap tm) {
		Log.log("typeof Expression called...");
		return getType(e,tm);
	}
	@Override
	public Type typeOf(AnonymousFunction fun, TypeMap tm) {
		Log.log("typeof AnonymousFunction called...");
		return null;
	}
	@Override
	public Type typeOf(Application app, TypeMap tm) {
		Log.log("typeof Application called...");
		return null;
	}
	@Override
	public Type typeOf(BinaryOperation bop, TypeMap tm) {
		Log.log("typeof BinaryOperation called...");
		return null;
	}
	@Override
	public Type typeOf(BoolValue bv, TypeMap tm) {
		Log.log("typeof BoolValue called...");
		return null;
	}
	@Override
	public Type typeOf(Bracket brket, TypeMap tm) {
		Log.log("typeof Bracket called...");
		return null;
	}
	@Override
	public Type typeOf(First fst, TypeMap tm) {
		Log.log("typeof First called...");
		return null;
	}
	@Override
	public Type typeOf(Head head, TypeMap tm) {
		Log.log("typeof Head called...");
		return null;
	}
	@Override
	public Type typeOf(IfThenElse ite, TypeMap tm) {
		Log.log("typeof IfThenElse called...");
		return null;
	}
	@Override
	public Type typeOf(IntValue intValue, TypeMap tm) {
		Log.log("typeof IntValue called...");
		return new IntType();
	}
	@Override
	public Type typeOf(LetInEnd letin, TypeMap tm) {
		Log.log("typeof LetInEnd called...");
		return null;
	}
	@Override
	public Type typeOf(List list, TypeMap tm) {
		Log.log("typeof List called...");
		return null;
	}
	@Override
	public Type typeOf(ListValue listValue, TypeMap tm) {
		Log.log("typeof ListValue called...");
		return null;
	}
	@Override
	public Type typeOf(Nil nil, TypeMap tm) {
		Log.log("typeof Nil called...");
		return null;
	}
	@Override
	public Type typeOf(Pair pair, TypeMap tm) {
		Log.log("typeof Pair called...");
		return null;
	}
	@Override
	public Type typeOf(Second scd, TypeMap tm) {
		Log.log("typeof Second called...");
		return null;
	}
	@Override
	public Type typeOf(Sequence seq, TypeMap tm) {
		Log.log("typeof Sequence called...");
		return null;
	}
	@Override
	public Type typeOf(Tail tail, TypeMap tm) {
		Log.log("typeof Tail called...");
		return null;
	}
	@Override
	public Type typeOf(UnaryOperation uop, TypeMap tm) {
		Log.log("typeof UnaryOperation called...");
		return null;
	}
	@Override
	public Type typeOf(Value v, TypeMap tm) {
		Log.log("typeof Value called...");
		return null;
	}
	@Override
	public Type typeOf(Variable var, TypeMap tm) {
		Log.log("typeof Variable called...");
		return null;
	}
	@Override
	public Type typeOf(WhileDoEnd wde, TypeMap tm) {
		Log.log("typeof WhileDoEnd called...");
		return null;
	}
	
	
}

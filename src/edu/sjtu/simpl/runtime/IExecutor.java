package edu.sjtu.simpl.runtime;

import edu.sjtu.simpl.exception.SimPLRuntimeException;
import edu.sjtu.simpl.exception.SimPLTypeException;
import edu.sjtu.simpl.runtime.RunTimeState;
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

public interface IExecutor {
	
	public Value M( Expression e, RunTimeState state) throws SimPLRuntimeException, SimPLTypeException;
	public Value M( Value v, RunTimeState state) throws SimPLRuntimeException, SimPLTypeException;
	public Value M( Pair pair, RunTimeState state) throws SimPLRuntimeException, SimPLTypeException;
	public Value M( PairValue pair, RunTimeState state) throws SimPLRuntimeException, SimPLTypeException;	
	public Value M( List list, RunTimeState state) throws SimPLTypeException, SimPLRuntimeException;
	public Value M( ListValue list, RunTimeState state) throws SimPLRuntimeException, SimPLTypeException;
	public Value M( AnonymousFunction fun, RunTimeState state) throws SimPLRuntimeException, SimPLTypeException;
	public Value M( Application app, RunTimeState state) throws SimPLRuntimeException, SimPLTypeException;
	public Value M( Assignment assign, RunTimeState state) throws SimPLRuntimeException, SimPLTypeException;
	public Value M( BinaryOperation bop, RunTimeState state) throws SimPLTypeException, SimPLRuntimeException;
	public Value M( BoolValue bv, RunTimeState state) throws SimPLRuntimeException, SimPLTypeException;
	public Value M( Bracket brket, RunTimeState state) throws SimPLRuntimeException, SimPLTypeException;
	public Value M( First fst, RunTimeState state) throws SimPLRuntimeException, SimPLTypeException;
	public Value M( Head head, RunTimeState state) throws SimPLRuntimeException, SimPLTypeException;
	public Value M( IfThenElse ite, RunTimeState state) throws SimPLRuntimeException, SimPLTypeException;
	public Value M( IntValue intValue, RunTimeState state) throws SimPLRuntimeException, SimPLTypeException;
	public Value M( LetInEnd letin, RunTimeState state) throws SimPLRuntimeException, SimPLTypeException;
	public Value M( Nil nil, RunTimeState state) throws SimPLRuntimeException, SimPLTypeException;
	public Value M( Nop nop, RunTimeState state) throws SimPLRuntimeException, SimPLTypeException;
	public Value M( Second scd, RunTimeState state) throws SimPLRuntimeException, SimPLTypeException;
	public Value M( Sequence seq, RunTimeState state) throws SimPLRuntimeException, SimPLTypeException;
	public Value M( Tail tail, RunTimeState state) throws SimPLRuntimeException, SimPLTypeException;
	public Value M( UnaryOperation uop, RunTimeState state) throws SimPLRuntimeException, SimPLTypeException;
	public Value M( Variable var, RunTimeState state) throws SimPLRuntimeException, SimPLTypeException;
	public Value M( WhileDoEnd wde, RunTimeState state) throws SimPLRuntimeException, SimPLTypeException;
	
	public Value functionCall(AnonymousFunction fun, Value para, RunTimeState state) throws SimPLRuntimeException, SimPLTypeException;
	
	
	public Expression Var2Val( Expression e, StateFrame state);
	public Expression Var2Val( Value v, StateFrame state);
	public Expression Var2Val( Pair pair, StateFrame state);
	public Expression Var2Val( PairValue pair, StateFrame state);	
	public Expression Var2Val( List list, StateFrame state);
	public Expression Var2Val( ListValue list, StateFrame state);
	public Expression Var2Val( AnonymousFunction fun, StateFrame state);
	public Expression Var2Val( Application app, StateFrame state);
	public Expression Var2Val( Assignment assign, StateFrame state);
	public Expression Var2Val( BinaryOperation bop, StateFrame state);
	public Expression Var2Val( BoolValue bv, StateFrame state);
	public Expression Var2Val( Bracket brket, StateFrame state);
	public Expression Var2Val( First fst, StateFrame state);
	public Expression Var2Val( Head head, StateFrame state);
	public Expression Var2Val( IfThenElse ite, StateFrame state);
	public Expression Var2Val( IntValue intValue, StateFrame state);
	public Expression Var2Val( LetInEnd letin, StateFrame state);
	public Expression Var2Val( Nil nil, StateFrame state);
	public Expression Var2Val( Nop nop, StateFrame state);
	public Expression Var2Val( Second scd, StateFrame state);
	public Expression Var2Val( Sequence seq, StateFrame state);
	public Expression Var2Val( Tail tail, StateFrame state);
	public Expression Var2Val( UnaryOperation uop, StateFrame state);
	public Expression Var2Val( Variable var, StateFrame state);
	public Expression Var2Val( WhileDoEnd wde, StateFrame state);
}

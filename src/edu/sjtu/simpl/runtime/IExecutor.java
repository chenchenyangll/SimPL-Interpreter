package edu.sjtu.simpl.runtime;

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
	
	public Object M( Expression e, RunTimeState state);
	public Value M( Value v, RunTimeState state);
	public Value M( Pair pair, RunTimeState state);
	public Value M( PairValue pair, RunTimeState state);	
	public Value M( List list, RunTimeState state);
	public Value M( ListValue list, RunTimeState state);
	public Value M( AnonymousFunction fun, RunTimeState state);
	public Value M( Application app, RunTimeState state);
	public Value M( Assignment assign, RunTimeState state);
	public Value M( BinaryOperation bop, RunTimeState state);
	public Value M( BoolValue bv, RunTimeState state);
	public Value M( Bracket brket, RunTimeState state);
	public Value M( First fst, RunTimeState state);
	public Value M( Head head, RunTimeState state);
	public Value M( IfThenElse ite, RunTimeState state);
	public Value M( IntValue intValue, RunTimeState state);
	public Value M( LetInEnd letin, RunTimeState state);
	public Value M( Nil nil, RunTimeState state);
	public Value M( Nop nop, RunTimeState state);
	public Value M( Second scd, RunTimeState state);
	public Value M( Sequence seq, RunTimeState state);
	public Value M( Tail tail, RunTimeState state);
	public Value M( UnaryOperation uop, RunTimeState state);
	public Value M( Variable var, RunTimeState state);
	public Value M( WhileDoEnd wde, RunTimeState state);
	
	Value functionCall(AnonymousFunction fun, Value para, RunTimeState state);
}

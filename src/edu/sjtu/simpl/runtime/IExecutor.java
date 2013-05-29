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
	
	public Object M( Expression e, State state);
	public Value M( Value v, State state);
	public Value M( Pair pair, State state);
	public Value M( PairValue pair, State state);	
	public Value M( List list, State state);
	public Value M( ListValue list, State state);
	public Value M( AnonymousFunction fun, State state);
	public Value M( Application app, State state);
	public Value M( Assignment assign, State state);
	public Value M( BinaryOperation bop, State state);
	public Value M( BoolValue bv, State state);
	public Value M( Bracket brket, State state);
	public Value M( First fst, State state);
	public Value M( Head head, State state);
	public Value M( IfThenElse ite, State state);
	public Value M( IntValue intValue, State state);
	public Value M( LetInEnd letin, State state);
	public Value M( Nil nil, State state);
	public Value M( Nop nop, State state);
	public Value M( Second scd, State state);
	public Value M( Sequence seq, State state);
	public Value M( Tail tail, State state);
	public Value M( UnaryOperation uop, State state);
	public Value M( Variable var, State state);
	public Value M( WhileDoEnd wde, State state);
	
	Value functionCall(AnonymousFunction fun, Value para, State state);
}

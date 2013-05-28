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
import edu.sjtu.simpl.syntax.Nil;
import edu.sjtu.simpl.syntax.Nop;
import edu.sjtu.simpl.syntax.Pair;
import edu.sjtu.simpl.syntax.Second;
import edu.sjtu.simpl.syntax.Sequence;
import edu.sjtu.simpl.syntax.Tail;
import edu.sjtu.simpl.syntax.UnaryOperation;
import edu.sjtu.simpl.syntax.Value;
import edu.sjtu.simpl.syntax.Variable;
import edu.sjtu.simpl.syntax.WhileDoEnd;

public interface IExecutor {
	
	public State M(Expression e, State state);
	public State M(Value v, State state);
	public State M(Pair pair, State state);
	public State M(List list, State state);
	public State M( AnonymousFunction fun, State state);
	public State M( Application app, State state);
	public State M( Assignment assign, State state);
	public State M( BinaryOperation bop, State state);
	public State M( BoolValue bv, State state);
	public State M( Bracket brket, State state);
	public State M( First fst, State state);
	public State M( Head head, State state);
	public State M( IfThenElse ite, State state);
	public State M( IntValue intValue, State state);
	public State M( LetInEnd letin, State state);
	public State M( Nil nil, State state);
	public State M( Nop nop, State state);
	public State M( Second scd, State state);
	public State M( Sequence seq, State state);
	public State M( Tail tail, State state);
	public State M( UnaryOperation uop, State state);
	public State M( Variable var, State state);
	public State M( WhileDoEnd wde, State state);
}

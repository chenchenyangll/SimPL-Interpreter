package backup;

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
import edu.sjtu.simpl.syntax.Second;
import edu.sjtu.simpl.syntax.Sequence;
import edu.sjtu.simpl.syntax.Tail;
import edu.sjtu.simpl.syntax.UnaryOperation;
import edu.sjtu.simpl.syntax.Value;
import edu.sjtu.simpl.syntax.Variable;
import edu.sjtu.simpl.syntax.WhileDoEnd;
import edu.sjtu.simpl.type.Type;
import edu.sjtu.simpl.validate.TypeMap;

public interface IValidator {
	public boolean V( Expression e, TypeMap tm);
	public boolean V( AnonymousFunction fun, TypeMap tm);
	public boolean V( Application app, TypeMap tm);
	public boolean V( BinaryOperation bop, TypeMap tm);
	public boolean V( BoolValue bv, TypeMap tm);
	public boolean V( Bracket brket, TypeMap tm);
	public boolean V( First fst, TypeMap tm);
	public boolean V( Head head, TypeMap tm);
	public boolean V( IfThenElse ite, TypeMap tm);
	public boolean V( IntValue intValue, TypeMap tm);
	public boolean V( LetInEnd letin, TypeMap tm);
	public boolean V( List list, TypeMap tm);
	public boolean V( ListValue listValue, TypeMap tm);
	public boolean V( Nil nil, TypeMap tm);
	public boolean V( Pair pair, TypeMap tm);
	public boolean V( Second scd, TypeMap tm);
	public boolean V( Sequence seq, TypeMap tm);
	public boolean V( Tail tail, TypeMap tm);
	public boolean V( UnaryOperation uop, TypeMap tm);
	public boolean V( Value v, TypeMap tm);
	public boolean V( Variable var, TypeMap tm);
	public boolean V( WhileDoEnd wde, TypeMap tm);
	
	
	public Type typeOf( Expression e, TypeMap tm);
	public Type typeOf( AnonymousFunction fun, TypeMap tm);
	public Type typeOf( Application app, TypeMap tm);
	public Type typeOf( BinaryOperation bop, TypeMap tm);
	public Type typeOf( BoolValue bv, TypeMap tm);
	public Type typeOf( Bracket brket, TypeMap tm);
	public Type typeOf( First fst, TypeMap tm);
	public Type typeOf( Head head, TypeMap tm);
	public Type typeOf( IfThenElse ite, TypeMap tm);
	public Type typeOf( IntValue intValue, TypeMap tm);
	public Type typeOf( LetInEnd letin, TypeMap tm);
	public Type typeOf( List list, TypeMap tm);
	public Type typeOf( ListValue listValue, TypeMap tm);
	public Type typeOf( Nil nil, TypeMap tm);
	public Type typeOf( Pair pair, TypeMap tm);
	public Type typeOf( Second scd, TypeMap tm);
	public Type typeOf( Sequence seq, TypeMap tm);
	public Type typeOf( Tail tail, TypeMap tm);
	public Type typeOf( UnaryOperation uop, TypeMap tm);
	public Type typeOf( Value v, TypeMap tm);
	public Type typeOf( Variable var, TypeMap tm);
	public Type typeOf( WhileDoEnd wde, TypeMap tm);
	
}

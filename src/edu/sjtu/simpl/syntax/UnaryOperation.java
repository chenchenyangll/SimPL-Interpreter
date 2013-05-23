package edu.sjtu.simpl.syntax;

public class UnaryOperation extends Expression{
	enum UnaryOperator{
		not, negative
	}
	
	public Expression e;
	public UnaryOperator op;

	public String toString(){
		String operator = "";
		switch(op){
		case not:
			operator = "not "; break;
		case negative:
			operator = "~ "; break;
		}
		return operator + e.toString();
	}
	
	public void setOpType(String uop)
	{
		if(uop.equals("~"))
		{
			op = UnaryOperator.negative;
		}
		else if(uop.equals("not"))
		{
			op = UnaryOperator.not;
		}
	}
}
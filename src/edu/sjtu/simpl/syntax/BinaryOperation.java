package edu.sjtu.simpl.syntax;

import edu.sjtu.simpl.type.IntType;
import edu.sjtu.simpl.type.Type;
import edu.sjtu.simpl.util.Log;

public class BinaryOperation extends Expression{
	public enum BinaryOperator{
		plus, minus, times, devide, biggerThan, lessThan, equal, and, or
	}
	
	public Expression e1;
	public Expression e2;
	public BinaryOperator op;
	
	public String toString(){
		String operator = "";
		switch(this.op){
		case plus:
			operator = "+"; break;
		case minus:
			operator = "-"; break;
		case times:
			operator = "*"; break;
		case devide:
			operator = "/"; break;
		case biggerThan:
			operator = ">"; break;
		case lessThan:
			operator = "<"; break;
		case equal:
			operator = "="; break;
		case and:
			operator = "and"; break;
		case or:
			operator = "or"; break;
		}
		return e1.toString() + " " + operator + " " + e2.toString();
	}
	
	public void setOperator(String op)
	{
		if(op.equals("+"))
			this.op = BinaryOperator.plus;
		else if(op.equals("-"))
		{
			this.op = BinaryOperator.minus;
		}
		else if(op.equals("*"))
		{
			this.op = BinaryOperator.times;
		}
		else if(op.equals("/"))
		{
			this.op = BinaryOperator.devide;
		}
		else if(op.equals("="))
		{
			this.op = BinaryOperator.equal;
		}
		else if(op.equals(">"))
		{
			this.op = BinaryOperator.biggerThan;
		}
		else if(op.equals("<"))
		{
			this.op = BinaryOperator.lessThan;
		}
		else if(op.equals("and"))
		{
			this.op = BinaryOperator.and;
		}
		else if(op.equals("or"))
		{
			this.op = BinaryOperator.or;
		}
	}
	
	public String getOperator()
	{
		String operator = "";
		switch(this.op){
		case plus:
			operator = "+"; break;
		case minus:
			operator = "-"; break;
		case times:
			operator = "*"; break;
		case devide:
			operator = "/"; break;
		case biggerThan:
			operator = ">"; break;
		case lessThan:
			operator = "<"; break;
		case equal:
			operator = "="; break;
		case and:
			operator = "and"; break;
		case or:
			operator = "or"; break;
		}
		return operator;
	}

	@Override
	public Type getType() {
		if(e1.getType().getTypeId() == Type.ID_INT&&e2.getType().getTypeId() == Type.ID_INT)
		{
			return new IntType();
		}
		
		Log.error("Type Error!");
		return null;
	}
	
	
	
}
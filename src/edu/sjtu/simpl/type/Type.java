package edu.sjtu.simpl.type;

public class Type {
	public static final int ID_BOOL = 1;
	public static final int ID_INT = 2;
	public static final int ID_PAIR = 3;
	public static final int ID_LIST = 4;
	public static final int ID_FUN = 5;
	public static final int ID_UNIT = 6;
	public static final int ID_UNKNOWN = 7;
	
	public static final Type BOOL = new BoolType();
	public static final Type INT = new IntType();
	public static final Type PAIR = new PairType();
	public static final Type LIST = new ListType();
	public static final Type FUN = new FunctionType();
	public static final Type UNIT = new UnitType();
	
	public int getTypeId()
	{
		return ID_UNKNOWN;
	}

	@Override
	public String toString() {
		return "unknown";
	}
	
	
	
}

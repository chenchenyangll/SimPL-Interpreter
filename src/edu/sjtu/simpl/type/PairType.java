package edu.sjtu.simpl.type;

public class PairType extends Type{
	
	public Type t1;
	public Type t2;
	
	@Override
	public int getTypeId() {
		return Type.ID_PAIR;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof PairType))
			return false;
		return ((PairType)obj).t1.equals(this.t1)&&((PairType)obj).t2.equals(this.t2);
	}
	
	@Override
	public String toString() {
		return "("+t1.toString()+","+t2.toString()+")";
	}
}

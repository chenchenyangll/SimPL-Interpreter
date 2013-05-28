package edu.sjtu.simpl.type;

public class FunctionType extends Type{
	public Type argType;
	public Type bodyType;
	
	@Override
	public int getTypeId() {
		return Type.ID_FUN;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof FunctionType))
			return false;
		return ((FunctionType)obj).argType.equals(this.argType)&&((FunctionType)obj).bodyType.equals(this.bodyType);
	}
	
	@Override
	public String toString() {
		return "("+argType.toString()+" "+bodyType.toString()+")";
	}
}



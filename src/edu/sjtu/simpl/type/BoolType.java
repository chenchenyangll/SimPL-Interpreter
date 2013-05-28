package edu.sjtu.simpl.type;

public class BoolType extends Type{
	@Override
	public int getTypeId() {
		return Type.ID_BOOL;
	}
	
	@Override
	public boolean equals(Object obj) {
		return (obj instanceof BoolType);
	}
	
	@Override
	public String toString() {
		return "bool";
	}
}

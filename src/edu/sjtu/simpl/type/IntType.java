package edu.sjtu.simpl.type;

public class IntType extends Type{
	@Override
	public int getTypeId() {
		return Type.ID_INT;
	}
	
	@Override
	public boolean equals(Object obj) {
		return (obj instanceof IntType);
	}
	
	@Override
	public String toString() {
		return "int";
	}
}

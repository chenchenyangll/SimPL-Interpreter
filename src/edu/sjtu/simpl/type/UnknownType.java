package edu.sjtu.simpl.type;

public class UnknownType extends Type{

	@Override
	public int getTypeId() {
		// TODO Auto-generated method stub
		return Type.ID_UNKNOWN;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "unknown";
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return obj instanceof Type;
	}
	
	
	
	

}

package edu.sjtu.simpl.type;

public class UnitType extends Type {

	@Override
	public int getTypeId() {
		return Type.ID_UNIT;
	}
	
	@Override
	public boolean equals(Object obj) {
		return (obj instanceof UnitType);
	}

	@Override
	public String toString() {
		return "unit";
	}
	
	

}

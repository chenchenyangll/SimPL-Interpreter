package edu.sjtu.simpl.type;

public class ListType extends Type{
	public Type itemType;
	
	@Override
	public int getTypeId() {
		return Type.ID_LIST;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof ListType))
			return false;
		return ((ListType)obj).itemType.equals(this.itemType);
	}
	
	@Override
	public String toString() {
		return "list["+itemType.toString()+"]";
	}
}

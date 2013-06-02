package edu.sjtu.simpl.type;

public class ListType extends Type{
	private Type itemType;
	public boolean isNil;
	
	public ListType()
	{
		itemType = Type.UNKNOWN;
		isNil = false;
	}
	
	@Override
	public int getTypeId() {
		return Type.ID_LIST;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof ListType))
			return false;
		if(((ListType)obj).isNil)
		{
			return true;
		}
		ListType lobj = (ListType)obj;
		if(lobj.getItemType().equals(Type.UNKNOWN))
		{
			return true;
		}
		if(this.getItemType().equals(Type.UNKNOWN))
		{
			return true;
		}
		return (lobj.itemType.equals(this.itemType));
	}
	
	public void setIsNil(boolean isNil)
	{
		this.isNil = isNil;
	}
	
	@Override
	public String toString() {
		if(isNil)
		{
			return "list nil";
		}
		else if(itemType == null)
		{
			return "list unknown";
		}
		
		return "list["+itemType.toString()+"]";
	}

	public Type getItemType() {
		if(isNil)
		{
			return Type.UNKNOWN;
		}
		else if( itemType != null)
		{
			return itemType;
		}
		else
			return Type.UNKNOWN;
	}

	public void setItemType(Type itemType) {
		this.itemType = itemType;
	}
	
	
}

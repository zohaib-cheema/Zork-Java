public class Item{
private String name;
private String type;
private String description;

public Item(String itemName, String itemType, String itemDescription)
{
this.name= itemName;
this.type = itemType;
this.description = itemDescription;
}

public String getName()
{
return name;

}

public String getType()
{
return type;

}

public String getDescription()
{
return description;

}

public void setName(String newName)
{
name = newName;

}

public void setType(String newType)

{
type = newType;

}

public void setDescription(String newDescription)
{
description = newDescription;

}

public String toString()
{
String output = name+ " ["+ type +"] :" + " " + description;

return output;
}
}

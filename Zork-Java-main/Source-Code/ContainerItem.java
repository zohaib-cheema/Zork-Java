import java.util.ArrayList;

public class ContainerItem extends Item{

private ArrayList<Item> items;    

public ContainerItem(String pName, String pType, String pDescription){
super(pName, pType, pDescription);
items = new ArrayList<Item>();
}

public void addItem(Item newItem){
items.add(newItem);
}

public boolean hasItem(String pName){
for(int i =0;i<items.size();i++){
if(items.get(i).getName().compareToIgnoreCase(pName)==0){
return true;
}
}
return false;
}

public Item removeItem(String pName){

for(int i =0;i<items.size();i++){
if(items.get(i).getName().compareToIgnoreCase(pName)==0){
return items.remove(i);
}
}
return null; 
}

@Override
public String toString(){
System.out.println(getName() + " [" + getType() + "]: " + getDescription() + ": ");

for(int i =0; i<items.size();i++){
System.out.println("+ " + items.get(i).getName());
}
return "";
}

}



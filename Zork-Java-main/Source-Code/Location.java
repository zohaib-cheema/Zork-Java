import java.util.ArrayList;
import java.util.HashMap;

public class Location {
    
private String name;
private String description;
private ArrayList<Item> itemList; 
private HashMap<String,Location> connections;

public Location(String name, String description){
this.name = name;
this.description = description;
itemList = new ArrayList<Item>();
connections = new HashMap<String,Location>();
}

public String getName(){
return name;
}

public String getDescription(){
    return description;
}

public void setName(String pName){
    name = pName;;
}

public void setDescription(String pDescription){
    description = pDescription;
}

public void addItem(Item pItem){
    itemList.add(pItem);
}

public boolean hasItem(String itemName){
    for(int i=0;i<itemList.size();i++){
    if(itemList.get(i).getName().compareToIgnoreCase(itemName)==0){
        return true;
    }
    }
    return false;
}

public Item getItem(String searchItem){
        for(int i=0;i<itemList.size();i++){
            if(itemList.get(i).getName().compareToIgnoreCase(searchItem)==0){
                return itemList.get(i);
            }
        }
   return null;
}

public Item getItem(int index){
   if(index>=0 && index<itemList.size()){
    return itemList.get(index);
   }
   return null;
}

public int numItems(){
    int size = itemList.size();
    return size;
}

public Item removeItem(String removeName){
    for(int i =0;i<itemList.size();i++){
        if(itemList.get(i).getName().compareToIgnoreCase(removeName)==0){
            return itemList.remove(i);
        }
    }
    return null;
}

public void connect(String direction, Location name){
connections.put(direction, name);
}

public boolean canMove(String directionName){
if(connections.containsKey(directionName)){
    return true;
}
return false;
}

public Location getLocation(String direction){
if(canMove(direction)){
    return connections.get(direction);
}
return null;
}

}



import java.util.Scanner;
public class Driver{

public static Location currLocation;
public static ContainerItem myInventory;

public static void createWorld(){
Location kitchen = new Location("Kitchen","A dark kitchen whose lights are flickering currently has the following items: ");
kitchen.addItem(new Item("Knife","Weapon","Used to cut objects"));
kitchen.addItem(new Item("Turkey","Food","Raw Turkey Meat"));
kitchen.addItem(new Item("Plate","Utensil","Used to place food"));
kitchen.addItem(new Item("Cup","Utensil","Used to contain fluids"));

Location hallway = new Location("hallway","area for the whole house");
hallway.addItem(new Item("Projector","Device","Projectory device used to watch movies or TV"));
hallway.addItem(new Item("Couch","Furniture","Use to sit with friends and family"));
hallway.addItem(new Item("Chandelier","Light","Used to illuminate the hallway"));
hallway.addItem(new Item("Novels","Book","Piece of text that portrays a story"));

Location bedroom = new Location("bedroom","a place to sleep and dress-up");
bedroom.addItem(new Item("Lamp","Device","Projectory device used to watch movies or TV"));
bedroom.addItem(new Item("Seat","Furniture","Use to sit with friends and family"));
bedroom.addItem(new Item("TV","Light","Used to illuminate the hallway"));
bedroom.addItem(new Item("Advil","Medicine","Pill to cure physical pain"));

Location playroom = new Location("playroom","an area to enjoy through physical activites");
playroom.addItem(new Item("Playstation","Gaming device","Projectory device used to watch movies or TV"));
playroom.addItem(new Item("Pool","Recreational","Use to sit with friends and family"));
playroom.addItem(new Item("Foosball","Game","Table soccer game that is played by two players"));
playroom.addItem(new Item("Speakers ","Audio device","Tabel soccer game that is played by two players"));

ContainerItem chest = new ContainerItem("Chest","Container","The chest has the ability to contain other items");
chest.addItem(new Item("Gold","Jewel","A precious stone"));
chest.addItem(new Item("Silver","Jewel","A precious stone"));
kitchen.addItem(chest);

ContainerItem vault = new ContainerItem("Vault","Container","This vault has the ability to contain expensive items");
vault.addItem(new Item("Cash","Currency","Currency used to purchase anything"));
vault.addItem(new Item("Diamond","Jewel","A carbon pressured stone"));
hallway.addItem(vault);

ContainerItem desk = new ContainerItem("Desk","Table","The desk has the ability to place items on top of it");
desk.addItem(new Item("Pen","Writing Utensil","Item used to write on paper"));
desk.addItem(new Item("CS Fundamentals","Book","Text to learn CS topics"));
bedroom.addItem(desk);

kitchen.connect("south", bedroom);
kitchen.connect("east", hallway);
bedroom.connect("north",kitchen);
bedroom.connect("east",playroom);
playroom.connect("north",hallway);
playroom.connect("west",bedroom);
hallway.connect("west",kitchen);
hallway.connect("south",playroom);

//pointing to hallway location to start
currLocation = hallway;
}

//for the help command
public static void helpMethod(){
    System.out.println("Useful commands:");
    System.out.println("    look: outputs the current lcoation, its description and the names of all the items present at the location");
    System.out.println("    examine 'item name': outputs the the type and description of the requested item at the current location");
    System.out.println("    go 'direction': moves from the current location in this direction to the new location");
    System.out.println("    inventory: outputs the names of all items present in your inventory");
    System.out.println("    take 'item': picks up the entered item from the current location and adds it to your inventory");
    System.out.println("    drop 'item': removes the entered item from your inventory and adds it to the current location");
    System.out.println("    quit: you exit the game");
    System.out.println("    help: you get a list of all possible commands");
    System.out.println("    take 'item name' from 'container name': picks up the entered item from the entered container item in the current location and adds it to your inventory");
    System.out.println("    put 'item name' in 'container name': picks up the entered item from your inventory and adds it to the the entered container item in the current location");

}


public static void main(String[] args){

myInventory = new ContainerItem("Inventory","Container","A backpack that stores");

currLocation = new Location("Kitchen","A dark kitchen whose lights are flickering currently has the following items: ");
currLocation.addItem(new Item("Knife","Weapon","Used to cut objects"));
currLocation.addItem(new Item("Turkey","Food","Raw Turkey Meat"));
currLocation.addItem(new Item("Plate","Utensil","Used to place food"));

createWorld();

System.out.println("The 'help' command will provide you with a list of useful commands."); 
Scanner sc = new Scanner(System.in);
int i = 0;
while(i==0){
System.out.println("Enter your command: "); 
String input = sc.nextLine();
String[] commands = input.split(" ");

switch(commands[0]){
    case "quit":
        i++;
        break;
    case "look":
        System.out.println(currLocation.getName() + " - " + currLocation.getDescription());
        for(int j=0;j<currLocation.numItems();j++){
            System.out.println("+ " + currLocation.getItem(j).getName());
        }
        break;
    case "examine":
        if(commands.length>1){
        if(currLocation.hasItem(commands[1])){
            System.out.println(currLocation.getItem(commands[1]).toString());
        }
        else{
            System.out.println("This item does not exist at this location");
        }
        }
        else{
            System.out.println("Please input an item to examine");
        }
        break;
    case "go":
        if(commands.length>1){  
            String lowCaseDirection = commands[1].toLowerCase();
            if((lowCaseDirection.compareTo("south")==0) || (lowCaseDirection.compareTo("north")==0) || (lowCaseDirection.compareTo("west")==0) || (lowCaseDirection.compareTo("east")==0)){
            if(currLocation.canMove(lowCaseDirection)){
                currLocation = currLocation.getLocation(lowCaseDirection);
            }
            else{
                System.out.println("No location exists at this direction to your current location");
            }
            }
            else{
                System.out.println("You have entered an invalid direction, please choose from (west,east,south,north)");
            }
            }
            else{
                System.out.println("Please input a direction to move in (west,east,south,north)");
            }
            break;
    case "inventory":
        System.out.print(myInventory.toString());
        break;

    case "take":
        if(commands.length>1){
            if(commands.length==2){
                if(currLocation.hasItem(commands[1])){
                myInventory.addItem(currLocation.removeItem(commands[1]));
                }
                    else{
                    System.out.println("Cannot find that item in the current location");
                    }
            }
            if(commands.length>2 && (commands[2].compareToIgnoreCase("from")==0)){      
                if(currLocation.hasItem(commands[3])){ 
                    Item searchItem = currLocation.getItem(commands[3]);
                    if (searchItem instanceof ContainerItem){
                    ContainerItem conItem = (ContainerItem) searchItem;
                    if(conItem.hasItem(commands[1])){
                    myInventory.addItem(conItem.removeItem(commands[1]));
                    }
                    else{
                        System.out.println("That item to requested to take does not exist in this container item.");
                    }
                    }
                    else{
                        System.out.println("That item to take from is not a container item, It can not store other items.");
                    }
                }
                else{
                    System.out.println("Cannot find that Container Item in the current location");
                    }
                }
                else if(commands.length>2 && !(commands[2].compareToIgnoreCase("from")==0)){
                    System.out.println("I don’t know how to do that, please try again with correct spellings and spacings.");
                }
        }
        else{
            System.out.println("Please input an item to add to your inventory");
        }
        break;

    case "put":
        if(commands.length>2 && (commands[2].compareToIgnoreCase("in")==0)){      
            if(myInventory.hasItem(commands[1])){
            if(currLocation.hasItem(commands[3])){ 
                Item searchItem = currLocation.getItem(commands[3]);
                if (searchItem instanceof ContainerItem){
                ContainerItem conItem = (ContainerItem) searchItem;
                conItem.addItem(myInventory.removeItem(commands[1]));
                }   
                else{
                    System.out.println("That item to take from is not a container item, It can not store other items.");
                }
            }
            else{
                System.out.println("This Container Item does not exit at the current location");
                }
        }
        else{
            System.out.println("This Item does not exit in your inventory");
            }    
            }
            else{
                System.out.println("I don’t know how to do that, please try again with correct spellings and spacings.");
            }
            break;

    case "drop":
        if(commands.length>1){
            if(myInventory.hasItem(commands[1])){
            currLocation.addItem(myInventory.removeItem(commands[1]));
            }
        else{
        System.out.println("Cannot find that item in your inventory");
        }
        }
        else{
            System.out.println("Please input an item to drop from your inventory");
        }
    break;
    case "help":
        helpMethod();
    break;

    default:
        System.out.println("I don’t know how to do that");
}
}
sc.close();
}     
}

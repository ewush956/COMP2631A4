package assignment4_COMP2631;

import java.util.Arrays;
import java.util.NoSuchElementException;
public class Main {

    public static void main(String[] args) {
        // Create the family tree
        Person me = new Person("Evan", 1999, false, "");
        FamilyTree<Person> myFamilyTree = new FamilyTree<>(me);

        // Add family members
        Person dad = new Person("Marlon", 1960, false, "Evan");
        Person mom = new Person("Crissy", 1975, true, "Evan");
        Person dadsDad = new Person("Wally", 1931, false, "Marlon"); 
        Person dadsMom = new Person("Maureen", 1933, true, "Marlon");
        Person momsDad = new Person("Arthur", 1930, false, "Crissy");
        Person momsMom = new Person("Dianne", 1932, true, "Crissy");
        
        // -- Initial Size (Just me) --
        System.out.println("\nInitial Tree Size: " + myFamilyTree.size());

        System.out.println("\nBuilding Tree");
        System.out.println("Original Tree (Pre-Order keys): " + Arrays.toString(myFamilyTree.preOrder()));
        myFamilyTree = myFamilyTree.put("2", dad);
        
        System.out.println("Original Tree (Pre-Order keys): " + Arrays.toString(myFamilyTree.preOrder()));
        myFamilyTree = myFamilyTree.put("7", momsMom);
        
        System.out.println("Original Tree (Pre-Order keys): " + Arrays.toString(myFamilyTree.preOrder()));
        myFamilyTree = myFamilyTree.put("6", mom);
        
        System.out.println("Original Tree (Pre-Order keys): " + Arrays.toString(myFamilyTree.preOrder()));
        myFamilyTree = myFamilyTree.put("1", dadsDad);
        
        System.out.println("Original Tree (Pre-Order keys): " + Arrays.toString(myFamilyTree.preOrder()));
        myFamilyTree = myFamilyTree.put("3", dadsMom);
        
        System.out.println("Original Tree (Pre-Order keys): " + Arrays.toString(myFamilyTree.preOrder()));
        myFamilyTree = myFamilyTree.put("5", momsDad);

        // Print the initial tree
        System.out.println("\nInitial Keys (In-Order):  " + Arrays.toString(myFamilyTree.keys()));
        System.out.println("Initial Keys (Pre-Order): " + Arrays.toString(myFamilyTree.preOrder()));

        // Make copy that removes Marlon (dad)
        System.out.println("\nRemoving 'Marlon'...");
        FamilyTree <Person> treeCopy = myFamilyTree.remove("2");
        System.out.println("TreeCopy after removal (Pre-Order keys): " + Arrays.toString(treeCopy .preOrder()));
        System.out.println("Original after removal (Pre-Order keys): " + Arrays.toString(myFamilyTree.preOrder()));
        // -- Demonstrating get method and immutability --
        if (myFamilyTree.contains("2")) {
        	System.out.println("\nMarlon is in the original tree");
        	if (!treeCopy.contains("2")) {
        		System.out.println("Marlon was successfully removed from copyTree");
        	}  	
        }
        else {
        	System.out.println("Something has gone horribly wrong and the world is probably ending");
        }
        System.out.println("\nAttemping to insert pre-existing key '6' into myFamilyTree: ");
        System.out.println("Original Tree before inserting '6' (Pre-Order keys): " + Arrays.toString(myFamilyTree.preOrder()));

        myFamilyTree = myFamilyTree.put("6", mom);
        System.out.println("Original Tree after inserting '6'  (Pre-Order keys): " + Arrays.toString(myFamilyTree.preOrder()));
        
        // Print final tree sizes
        System.out.println("\nFinal Tree Size: " + myFamilyTree.size());

        // Print all family members
        printPersonsForKeys("Final Family Members", myFamilyTree, myFamilyTree.keys());
    }

    private static void printPersonsForKeys(String message, FamilyTree<Person> tree, String[] keys) {
        System.out.println("\n" + message);
        for (String key : keys) {
            try {
                Person person = tree.get(key);
                System.out.println("Key: " + key + " --> " + person);
            } catch (NoSuchElementException e) {
                System.out.println("No person found for key: " + key);
            }
        }
    }
}

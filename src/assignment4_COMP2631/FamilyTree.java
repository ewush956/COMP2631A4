package assignment4_COMP2631;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

//public final class FamilyTree implements ImmutableDictionary<FamilyTree> {
public final class FamilyTree<T> implements ImmutableDictionary<T> {
	
    private final Node<T> me;

    private static class Node<T> {
        final String key; // Unique identifier
        final T value;    // Generic type
        final Node<T> mother;
        final Node<T> father;

        Node(String key, T value, Node<T> mother, Node<T> father) {
            this.key = key;
            this.value = value;
            this.mother = mother;
            this.father = father;
        }
    }

    public FamilyTree(T value) {
        this.me = new Node<>("4", value, null, null);
    }

    private FamilyTree(Node<T> root) {
        this.me = root;
    }
    
    public FamilyTree<T> put(String key, T value) {
        return new FamilyTree<>(putHelper(me, key, value));
    }

    private Node<T> putHelper(Node<T> current, String key, T value) {
        if (current == null) {
            return new Node<>(key, value, null, null);
        }

        if (key.compareTo(current.key) < 0) {
            return new Node<>(current.key, current.value, putHelper(current.mother, key, value), current.father);
        } else if (key.compareTo(current.key) > 0) {
            return new Node<>(current.key, current.value, current.mother, putHelper(current.father, key, value));
        } else {
            // Replace the existing value
            return new Node<>(key, value, current.mother, current.father);
        }
    }

	@Override
	public int size() {
	    return sizeHelper(me);
	}

	private int sizeHelper(Node<T> node) {
	    if (node == null) {
	        return 0;
	    }
	    return 1 + sizeHelper(node.mother) + sizeHelper(node.father);
	}

	@Override
	public T get(String key) throws NoSuchElementException {
		//Take in key "1, 2, ..." and return associated person
		//Keys sorted in tree like this:
		//                      2
		//                     / \
		//                    1   3
	      Node<T> node = getNode(me, key);
	        if (node == null) {
	            throw new NoSuchElementException("No element found for key: " + key);
	        }
	        return node.value;
	    }

	    private Node<T> getNode(Node<T> current, String key) {
	        if (current == null) {
	            return null;
	        }

	        if (key.equals(current.key)) {
	            return current;
	        }

	        Node<T> leftResult = getNode(current.mother, key);
	        if (leftResult != null) {
	            return leftResult;
	        }

	        return getNode(current.father, key);
	    }

	@Override
    public boolean contains(String key) {
        return containsHelper(me, key);
    }
    private boolean containsHelper(Node<T> current, String key) {
        if (current == null) {
            return false;
        }

        if (key.equals(current.key)) {
            return true;
        }

        // Search in the left subtree (mother)
        boolean foundInMother = containsHelper(current.mother, key);
        if (foundInMother) {
            return true;
        }

        // Search in the right subtree (father)
        return containsHelper(current.father, key);
    }

	@Override
    public String[] keys() {
        List<String> keysList = new ArrayList<>();
        inOrderKeys(me, keysList);
        return keysList.toArray(new String[0]);
    }
    private void inOrderKeys(Node<T> node, List<String> keysList) {
        if (node == null) {
            return;
        }
        inOrderKeys(node.mother, keysList); // Traverse the left subtree
        keysList.add(node.key);             // Visit the node
        inOrderKeys(node.father, keysList); // Traverse the right subtree
    }

	@Override
    public String[] preOrder() {
        List<String> result = new ArrayList<>();
        preOrderHelper(me, result);
        return result.toArray(new String[0]);
    }
    private void preOrderHelper(Node<T> node, List<String> result) {
        if (node == null) {
            return;
        }
        result.add(node.key);           // Visit the node
        preOrderHelper(node.mother, result); // Traverse left subtree
        preOrderHelper(node.father, result); // Traverse right subtree
    }

	@Override
    public FamilyTree<T> remove(String key) {
        return new FamilyTree<>(copyTreeExcluding(me, key));
    }
    private Node<T> copyTreeExcluding(Node<T> current, String excludeKey) {
        if (current == null) {
            return null;
        }
        
        if (current.key.equals(excludeKey)) {
            // Skip this node, but continue copying its children
            return mergeTrees(copyTreeExcluding(current.mother, excludeKey), 
                              copyTreeExcluding(current.father, excludeKey));
        }
        // Copy this node and its children
        Node<T> newMother = copyTreeExcluding(current.mother, excludeKey);
        Node<T> newFather = copyTreeExcluding(current.father, excludeKey);
        return new Node<>(current.key, current.value, newMother, newFather);
    }
    private Node<T> mergeTrees(Node<T> mother, Node<T> father) {
        if (mother == null) {
            return father;
        }
        // Recreate the mother's tree, attaching the father tree at the rightmost leaf
        return new Node<>(mother.key, mother.value, mother.mother, attachToRightmost(mother.father, father));
    }
    private Node<T> attachToRightmost(Node<T> node, Node<T> subtreeToAttach) {
        if (node == null) {
            return subtreeToAttach;
        }
        // Recursively traverse to the rightmost node and attach the subtree there
        return new Node<>(node.key, node.value, node.mother, attachToRightmost(node.father, subtreeToAttach));
    }
}

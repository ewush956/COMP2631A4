package assignment4_COMP2631;

import java.util.NoSuchElementException;

/**
 * ImmutableDictionary interface that defines the operations for an immutable dictionary.
 * @param <T> The type of values stored in the dictionary.
 */
public interface ImmutableDictionary<T> {

    /**
     * Returns the number of keys that have an associated value.
     * @return the number of keys in this dictionary.
     */
    int size();

    /**
     * Returns a copy of this dictionary with the given value associated with the given key.
     * If the value is already associated with the key, returns this dictionary.
     * @param key The key with which the specified value is to be associated.
     * @param value The value to be associated with the specified key.
     * @return a new ImmutableDictionary instance with the added key-value pair.
     */
    ImmutableDictionary<T> put(String key, T value);

    /**
     * Returns the value associated with the given key.
     * @param key The key whose associated value is to be returned.
     * @return the value associated with the given key.
     * @throws NoSuchElementException if the key has no association.
     */
    T get(String key) throws NoSuchElementException;

    /**
     * Determines if the given key has an associated value in this dictionary.
     * @param key The key whose presence in this dictionary is to be tested.
     * @return true if this dictionary contains a mapping for the specified key.
     */
    boolean contains(String key);

    /**
     * Returns an array of all keys that have an associated value, in an in-order traversal.
     * If `null` has an associated value, it should be first in the array.
     * @return an array of keys in this dictionary.
     */
    String[] keys();

    /**
     * Returns an array of all keys that have an associated value, using a pre-order traversal of the tree.
     * @return an array of keys in pre-order traversal.
     */
    String[] preOrder();

    /**
     * Returns a copy of this dictionary with no associated value for the given key.
     * If the key does not have an associated value, returns this dictionary.
     * @param key The key whose mapping is to be removed from the dictionary.
     * @return a new ImmutableDictionary instance without the specified key-value pair.
     */
    ImmutableDictionary<T> remove(String key);
}
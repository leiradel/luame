package java.util;

public class Hashtable {
    /**
     * Constructs a new, empty hashtable with the specified initial
     *  capacity.
     * 
     * Parameters:initialCapacity - the initial capacity of the hashtable.
     * Throws:
     * IllegalArgumentException - if the initial capacity is less
     *              than zeroSince:
     *   JDK1.0
     */
    public Hashtable(int initialCapacity) {
        construct(initialCapacity);
    }

    private native void construct(int initialCapacity);

    /**
     * Returns the number of keys in this hashtable.
     * 
     * Returns:the number of keys in this hashtable.Since:
     *   JDK1.0
     */
    public int size();

    /**
     * Tests if this hashtable maps no keys to values.
     * 
     * Returns:true if this hashtable maps no keys to values;
     *           false otherwise.Since:
     *   JDK1.0
     */
    public boolean isEmpty();

    /**
     * Returns an enumeration of the keys in this hashtable.
     * 
     * Returns:an enumeration of the keys in this hashtable.Since:
     *   JDK1.0
     * See Also:Enumeration,
     * elements()
     */
    public Enumeration keys();

    /**
     * Returns an enumeration of the values in this hashtable.
     *  Use the Enumeration methods on the returned object to fetch the elements
     *  sequentially.
     * 
     * Returns:an enumeration of the values in this hashtable.Since:
     *   JDK1.0
     * See Also:Enumeration,
     * keys()
     */
    public Enumeration elements();

    /**
     * Tests if some key maps into the specified value in this hashtable.
     *  This operation is more expensive than the containsKey
     *  method.
     * 
     * Parameters:value - a value to search for.
     * Returns:true if some key maps to the
     *              value argument in this hashtable;
     *              false otherwise.
     * Throws:
     * NullPointerException - if the value is null.Since:
     *   JDK1.0
     * See Also:containsKey(java.lang.Object)
     */
    public boolean contains(Object value);

    /**
     * Tests if the specified object is a key in this hashtable.
     * 
     * Parameters:key - possible key.
     * Returns:true if the specified object is a key in this
     *           hashtable; false otherwise.Since:
     *   JDK1.0
     * See Also:contains(java.lang.Object)
     */
    public boolean containsKey(Object key);

    /**
     * Returns the value to which the specified key is mapped in this hashtable.
     * 
     * Parameters:key - a key in the hashtable.
     * Returns:the value to which the key is mapped in this hashtable;
     *           null if the key is not mapped to any value in
     *           this hashtable.Since:
     *   JDK1.0
     * See Also:put(java.lang.Object, java.lang.Object)
     */
    public Object get(Object key);

    /**
     * Rehashes the contents of the hashtable into a hashtable with a
     *  larger capacity. This method is called automatically when the
     *  number of keys in the hashtable exceeds this hashtable's capacity
     *  and load factor.
     * 
     * Since:
     *   JDK1.0
     */
    protected void rehash();

    /**
     * Maps the specified key to the specified
     *  value in this hashtable. Neither the key nor the
     *  value can be null.
     * 
     *  The value can be retrieved by calling the get method
     *  with a key that is equal to the original key.
     * 
     * Parameters:key - the hashtable key.value - the value.
     * Returns:the previous value of the specified key in this hashtable,
     *              or null if it did not have one.
     * Throws:
     * NullPointerException - if the key or value is
     *                null.Since:
     *   JDK1.0
     * See Also:Object.equals(java.lang.Object),
     * get(java.lang.Object)
     */
    public Object put(Object key,
                  Object value);

    /**
     * Removes the key (and its corresponding value) from this
     *  hashtable. This method does nothing if the key is not in the hashtable.
     * 
     * Parameters:key - the key that needs to be removed.
     * Returns:the value to which the key had been mapped in this hashtable,
     *           or null if the key did not have a mapping.Since:
     *   JDK1.0
     */
    public Object remove(Object key);

    /**
     * Clears this hashtable so that it contains no keys.
     * 
     * Since:
     *   JDK1.0
     */
    public void clear();

}

package java.util;

public class Vector {
    /**
     * The array buffer into which the components of the vector are
     *  stored. The capacity of the vector is the length of this array buffer.
     * 
     * Since:
     *   JDK1.0
     */
    protected Object[] elementData = ;

    /**
     * The number of valid components in the vector.
     * 
     * Since:
     *   JDK1.0
     */
    protected int elementCount = ;

    /**
     * The amount by which the capacity of the vector is automatically
     *  incremented when its size becomes greater than its capacity. If
     *  the capacity increment is 0, the capacity of the
     *  vector is doubled each time it needs to grow.
     * 
     * Since:
     *   JDK1.0
     */
    protected int capacityIncrement = ;

    /**
     * Constructs an empty vector with the specified initial capacity and
     *  capacity increment.
     * 
     * Parameters:initialCapacity - the initial capacity of the vector.capacityIncrement - the amount by which the capacity is
     *                               increased when the vector overflows.
     * Throws:
     * IllegalArgumentException - if the specified initial capacity
     *             is negative
     */
    public Vector(int initialCapacity,
              int capacityIncrement) {
        construct(initialCapacity, capacityIncrement);
    }

    private native void construct(int initialCapacity,
              int capacityIncrement);

    /**
     * Constructs an empty vector with the specified initial capacity.
     * 
     * Parameters:initialCapacity - the initial capacity of the vector.Since:
     *   JDK1.0
     */
    public Vector(int initialCapacity) {
        construct(initialCapacity);
    }

    private native void construct(int initialCapacity);

    /**
     * Copies the components of this vector into the specified array.
     *  The array must be big enough to hold all the objects in this  vector.
     * 
     * Parameters:anArray - the array into which the components get copied.Since:
     *   JDK1.0
     */
    public void copyInto(Object[] anArray);

    /**
     * Trims the capacity of this vector to be the vector's current
     *  size. An application can use this operation to minimize the
     *  storage of a vector.
     * 
     * Since:
     *   JDK1.0
     */
    public void trimToSize();

    /**
     * Increases the capacity of this vector, if necessary, to ensure
     *  that it can hold at least the number of components specified by
     *  the minimum capacity argument.
     * 
     * Parameters:minCapacity - the desired minimum capacity.Since:
     *   JDK1.0
     */
    public void ensureCapacity(int minCapacity);

    /**
     * Sets the size of this vector. If the new size is greater than the
     *  current size, new null items are added to the end of
     *  the vector. If the new size is less than the current size, all
     *  components at index newSize and greater are discarded.
     * 
     * Parameters:newSize - the new size of this vector.
     * Throws:
     * ArrayIndexOutOfBoundsException - if new size is negative.Since:
     *   JDK1.0
     */
    public void setSize(int newSize);

    /**
     * Returns the current capacity of this vector.
     * 
     * Returns:the current capacity of this vector.Since:
     *   JDK1.0
     */
    public int capacity();

    /**
     * Returns the number of components in this vector.
     * 
     * Returns:the number of components in this vector.Since:
     *   JDK1.0
     */
    public int size();

    /**
     * Tests if this vector has no components.
     * 
     * Returns:true if this vector has no components;
     *           false otherwise.Since:
     *   JDK1.0
     */
    public boolean isEmpty();

    /**
     * Returns an enumeration of the components of this vector.
     * 
     * Returns:an enumeration of the components of this vector.Since:
     *   JDK1.0
     * See Also:Enumeration
     */
    public Enumeration elements();

    /**
     * Tests if the specified object is a component in this vector.
     * 
     * Parameters:elem - an object.
     * Returns:true if the specified object is a component in
     *           this vector; false otherwise.Since:
     *   JDK1.0
     */
    public boolean contains(Object elem);

    /**
     * Searches for the first occurence of the given argument, testing
     *  for equality using the equals method.
     * 
     * Parameters:elem - an object.
     * Returns:the index of the first occurrence of the argument in this
     *           vector; returns -1 if the object is not found.Since:
     *   JDK1.0
     * See Also:Object.equals(java.lang.Object)
     */
    public int indexOf(Object elem);

    /**
     * Searches for the first occurence of the given argument, beginning
     *  the search at index, and testing for equality using
     *  the equals method.
     * 
     * Parameters:elem - an object.index - the index to start searching from.
     * Returns:the index of the first occurrence of the object argument in
     *           this vector at position index or later in the
     *           vector; returns -1 if the object is not found.Since:
     *   JDK1.0
     * See Also:Object.equals(java.lang.Object)
     */
    public int indexOf(Object elem,
                   int index);

    /**
     * Returns the index of the last occurrence of the specified object in
     *  this vector.
     * 
     * Parameters:elem - the desired component.
     * Returns:the index of the last occurrence of the specified object in
     *           this vector; returns -1 if the object is not found.Since:
     *   JDK1.0
     */
    public int lastIndexOf(Object elem);

    /**
     * Searches backwards for the specified object, starting from the
     *  specified index, and returns an index to it.
     * 
     * Parameters:elem - the desired component.index - the index to start searching from.
     * Returns:the index of the last occurrence of the specified object in this
     *           vector at position less than index in the vector;
     *           -1 if the object is not found.
     * Throws:
     * IndexOutOfBoundsException - if index is greater
     *              than or equal to the current size of this vector.Since:
     *   JDK1.0
     */
    public int lastIndexOf(Object elem,
                       int index);

    /**
     * Returns the component at the specified index.
     * 
     * Parameters:index - an index into this vector.
     * Returns:the component at the specified index.
     * Throws:
     * ArrayIndexOutOfBoundsException - if an invalid index was
     *              given.Since:
     *   JDK1.0
     */
    public Object elementAt(int index);

    /**
     * Returns the first component of this vector.
     * 
     * Returns:the first component of this vector.
     * Throws:
     * NoSuchElementException - if this vector has no components.Since:
     *   JDK1.0
     */
    public Object firstElement();

    /**
     * Returns the last component of the vector.
     * 
     * Returns:the last component of the vector, i.e., the component at index
     *           size()&nbsp;-&nbsp;1.
     * Throws:
     * NoSuchElementException - if this vector is empty.Since:
     *   JDK1.0
     */
    public Object lastElement();

    /**
     * Sets the component at the specified index of this
     *  vector to be the specified object. The previous component at that
     *  position is discarded.
     * 
     *  The index must be a value greater than or equal to 0
     *  and less than the current size of the vector.
     * 
     * Parameters:obj - what the component is to be set to.index - the specified index.
     * Throws:
     * ArrayIndexOutOfBoundsException - if the index was invalid.Since:
     *   JDK1.0
     * See Also:size()
     */
    public void setElementAt(Object obj,
                         int index);

    /**
     * Deletes the component at the specified index. Each component in
     *  this vector with an index greater or equal to the specified
     *  index is shifted downward to have an index one
     *  smaller than the value it had previously.
     * 
     *  The index must be a value greater than or equal to 0
     *  and less than the current size of the vector.
     * 
     * Parameters:index - the index of the object to remove.
     * Throws:
     * ArrayIndexOutOfBoundsException - if the index was invalid.Since:
     *   JDK1.0
     * See Also:size()
     */
    public void removeElementAt(int index);

    /**
     * Inserts the specified object as a component in this vector at the
     *  specified index. Each component in this vector with
     *  an index greater or equal to the specified index is
     *  shifted upward to have an index one greater than the value it had
     *  previously.
     * 
     *  The index must be a value greater than or equal to 0
     *  and less than or equal to the current size of the vector.
     * 
     * Parameters:obj - the component to insert.index - where to insert the new component.
     * Throws:
     * ArrayIndexOutOfBoundsException - if the index was invalid.Since:
     *   JDK1.0
     * See Also:size()
     */
    public void insertElementAt(Object obj,
                            int index);

    /**
     * Adds the specified component to the end of this vector,
     *  increasing its size by one. The capacity of this vector is
     *  increased if its size becomes greater than its capacity.
     * 
     * Parameters:obj - the component to be added.Since:
     *   JDK1.0
     */
    public void addElement(Object obj);

    /**
     * Removes the first occurrence of the argument from this vector. If
     *  the object is found in this vector, each component in the vector
     *  with an index greater or equal to the object's index is shifted
     *  downward to have an index one smaller than the value it had previously.
     * 
     * Parameters:obj - the component to be removed.
     * Returns:true if the argument was a component of this
     *           vector; false otherwise.Since:
     *   JDK1.0
     */
    public boolean removeElement(Object obj);

    /**
     * Removes all components from this vector and sets its size to zero.
     * 
     * Since:
     *   JDK1.0
     */
    public void removeAllElements();

}

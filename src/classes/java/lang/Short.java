package java.lang;

public class Short {
    /**
     * The minimum value a Short can have.
     * 
     * See Also:Constant Field Values
     */
    public static final short MIN_VALUE = ;

    /**
     * The maximum value a Short can have.
     * 
     * See Also:Constant Field Values
     */
    public static final short MAX_VALUE = ;

    /**
     * Constructs a Short object initialized to the specified short value.
     * 
     * Parameters:value - the initial value of the Short
     */
    public Short(short value) {
        construct(value);
    }

    private native void construct(short value);

    /**
     * Assuming the specified String represents a short, returns
     *  that short's value. Throws an exception if the String cannot
     *  be parsed as a short.  The radix is assumed to be 10.
     * 
     * Parameters:s - the String containing the short
     * Returns:The short value represented by the specified string
     * Throws:
     * NumberFormatException - If the string does not
     *                 contain a parsable short.
     */
    public static short parseShort(String s) throws NumberFormatException;

    /**
     * Assuming the specified String represents a short, returns
     *  that short's value in the radix specified by the second
     *  argument. Throws an exception if the String cannot
     *  be parsed as a short.
     * 
     * Parameters:s - the String containing the shortradix - the radix to be used
     * Returns:The short value represented by the specified string in
     *                 the specified radix.
     * Throws:
     * NumberFormatException - If the String does not
     *                 contain a parsable short.
     */
    public static short parseShort(String s, int radix) throws NumberFormatException;

    /**
     * Returns the value of this Short as a short.
     * 
     * Returns:the value of this Short as a short.
     */
    public short shortValue();

    /**
     * Returns a String object representing this Short's value.
     * 
     * Overrides:toString in class Object
     * 
     * Returns:a string representation of the object.
     */
    public String toString();

    /**
     * Returns a hashcode for this Short.
     * 
     * Overrides:hashCode in class Object
     * 
     * Returns:a hash code value for this object.See Also:Object.equals(java.lang.Object),
     * Hashtable
     */
    public int hashCode();

    /**
     * Compares this object to the specified object.
     * 
     * Overrides:equals in class Object
     * 
     * Parameters:obj - the object to compare with
     * Returns:true if the objects are the same; false otherwise.See Also:Boolean.hashCode(),
     * Hashtable
     */
    public boolean equals(Object obj);

}

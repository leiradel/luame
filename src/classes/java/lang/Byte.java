package java.lang;

public class Byte {
    /**
     * The minimum value a Byte can have.
     * 
     * See Also:Constant Field Values
     */
    public static final byte MIN_VALUE = ;

    /**
     * The maximum value a Byte can have.
     * 
     * See Also:Constant Field Values
     */
    public static final byte MAX_VALUE = ;

    /**
     * Constructs a Byte object initialized to the specified byte value.
     * 
     * Parameters:value - the initial value of the Byte
     */
    public Byte(byte value) {
        construct(value);
    }

    private native void construct(byte value);

    /**
     * Assuming the specified String represents a byte, returns
     *  that byte's value. Throws an exception if the String cannot
     *  be parsed as a byte.  The radix is assumed to be 10.
     * 
     * Parameters:s - the String containing the byte
     * Returns:the parsed value of the byte
     * Throws:
     * NumberFormatException - If the string does not
     *                 contain a parsable byte.
     */
    public static byte parseByte(String s) throws NumberFormatException;

    /**
     * Assuming the specified String represents a byte, returns
     *  that byte's value. Throws an exception if the String cannot
     *  be parsed as a byte.
     * 
     * Parameters:s - the String containing the byteradix - the radix to be used
     * Returns:the parsed value of the byte
     * Throws:
     * NumberFormatException - If the String does not
     *                 contain a parsable byte.
     */
    public static byte parseByte(String s, int radix) throws NumberFormatException;

    /**
     * Returns the value of this Byte as a byte.
     * 
     * Returns:the value of this Byte as a byte.
     */
    public byte byteValue();

    /**
     * Returns a String object representing this Byte's value.
     * 
     * Overrides:toString in class Object
     * 
     * Returns:a string representation of the object.
     */
    public String toString();

    /**
     * Returns a hashcode for this Byte.
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

package java.lang;

public class Boolean {
    /**
     * The Boolean object corresponding to the primitive
     *  value true.
     */
    public static final Boolean TRUE = ;

    /**
     * The Boolean object corresponding to the primitive
     *  value false.
     */
    public static final Boolean FALSE = ;

    /**
     * Returns the value of this Boolean object as a boolean
     *  primitive.
     * 
     * Returns:the primitive boolean value of this object.
     */
    public boolean booleanValue();

    /**
     * Returns a String object representing this Boolean's value.
     *  If this object represents the value true, a string equal
     *  to "true" is returned. Otherwise, a string equal to
     *  "false" is returned.
     * 
     * Overrides:toString in class Object
     * 
     * Returns:a string representation of this object.
     */
    public String toString();

    /**
     * Returns a hash code for this Boolean object.
     * 
     * Overrides:hashCode in class Object
     * 
     * Returns:the integer 1231 if this object represents
     *  true; returns the integer 1237 if this
     *  object represents false.See Also:Object.equals(java.lang.Object),
     * Hashtable
     */
    public int hashCode();

}

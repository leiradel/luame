package java.lang;

public class Long {
    /**
     * The smallest value of type long.
     * 
     * See Also:Constant Field Values
     */
    public static final long MIN_VALUE = ;

    /**
     * The largest value of type long.
     * 
     * See Also:Constant Field Values
     */
    public static final long MAX_VALUE = ;

    /**
     * Constructs a newly allocated Long object that
     *  represents the primitive long argument.
     * 
     * Parameters:value - the value to be represented by the
     *           Long object.
     */
    public Long(long value) {
        construct(value);
    }

    private native void construct(long value);

    /**
     * Creates a string representation of the first argument in the
     *  radix specified by the second argument.
     * 
     *  If the radix is smaller than Character.MIN_RADIX or
     *  larger than Character.MAX_RADIX, then the radix
     *  10 is used instead.
     * 
     *  If the first argument is negative, the first element of the
     *  result is the ASCII minus sign '-'
     *  ('&#92;u002d'. If the first argument is not negative,
     *  no sign character appears in the result.
     * 
     *  The remaining characters of the result represent the magnitude of
     *  the first argument. If the magnitude is zero, it is represented by
     *  a single zero character '0'
     *  ('&#92;u0030'); otherwise, the first character of the
     *  representation of the magnitude will not be the zero character.
     *  The following ASCII characters are used as digits:
     * 
     *    0123456789abcdefghijklmnopqrstuvwxyz
     * 
     *  These are '&#92;u0030' through '&#92;u0039'
     *  and '&#92;u0061' through '&#92;u007a'. If the
     *  radix is N, then the first N of these
     *  characters are used as radix-N digits in the order
     *  shown. Thus, the digits for hexadecimal (radix 16) are
     * 
     *  0123456789abcdef.
     * 
     * Parameters:i - a long.radix - the radix.
     * Returns:a string representation of the argument in the specified radix.See Also:Character.MAX_RADIX,
     * Character.MIN_RADIX
     */
    public static String toString(long i, int radix);

    /**
     * Returns a new String object representing the specified integer.
     *  The argument is converted to signed decimal representation and
     *  returned as a string, exactly as if the argument and the radix
     *  10 were given as arguments to the
     *  toString(long, int) method that takes two arguments.
     * 
     * Parameters:i - a long to be converted.
     * Returns:a string representation of the argument in base&nbsp;10.
     */
    public static String toString(long i);

    /**
     * Parses the string argument as a signed long in the
     *  radix specified by the second argument. The characters in the
     *  string must all be digits of the specified radix (as determined by
     *  whether Character.digit returns a
     *  nonnegative value), except that the first character may be an
     *  ASCII minus sign '-' ('&#92;u002d' to indicate
     *  a negative value. The resulting long value is returned.
     * 
     *  Note that neither L nor l is permitted to appear at
     *  the end of the string as a type indicator, as would be permitted in
     *  Java programming language source code - except that either L
     *  or l may appear as a digit for a radix greater than 22.
     * 
     *  An exception of type NumberFormatException is thrown if any of
     *  the following situations occurs:
     * 
     *  The first argument is null or is a string of length zero.
     *  The radix is either smaller than
     *      Character.MIN_RADIX or larger than
     *      Character.MAX_RADIX.
     *  The first character of the string is not a digit of the
     *      specified radix and is not a minus sign '-'
     *      ('&#92;u002d').
     *  The first character of the string is a minus sign and the
     *      string is of length 1.
     *  Any character of the string after the first is not a digit of
     *      the specified radix.
     *  The integer value represented by the string cannot be
     *      represented as a value of type long.
     * 
     *  Examples:
     * 
     *  parseLong("0", 10) returns 0L
     *  parseLong("473", 10) returns 473L
     *  parseLong("-0", 10) returns 0L
     *  parseLong("-FF", 16) returns -255L
     *  parseLong("1100110", 2) returns 102L
     *  parseLong("99", 8) throws a NumberFormatException
     *  parseLong("Hazelnut", 10) throws a NumberFormatException
     *  parseLong("Hazelnut", 36) returns 1356099454469L
     * 
     * Parameters:s - the String containing the
     *                      long.radix - the radix to be used.
     * Returns:the long represented by the string argument in
     *              the specified radix.
     * Throws:
     * NumberFormatException - if the string does not contain a
     *                                     parsable integer.
     */
    public static long parseLong(String s, int radix) throws NumberFormatException;

    /**
     * Parses the string argument as a signed decimal long.
     *  The characters in the string must all be decimal digits, except
     *  that the first character may be an ASCII minus sign
     *  '-' (&#92;u002d') to indicate a negative
     *  value. The resulting long value is returned, exactly as if the
     *  argument and the radix 10 were given as arguments to the
     *  parseLong(String, int) method that takes two arguments.
     * 
     *  Note that neither L nor l is permitted to appear
     *  at the end of the string as a type indicator, as would be permitted
     *  in Java programming language source code.
     * 
     * Parameters:s - a string.
     * Returns:the long represented by the argument in decimal.
     * Throws:
     * NumberFormatException - if the string does not contain a
     *                parsable long.
     */
    public static long parseLong(String s) throws NumberFormatException;

    /**
     * Returns the value of this Long as a long value.
     * 
     * Returns:the long value represented by this object.
     */
    public long longValue();

    /**
     * Returns the value of this Long as a float.
     * 
     * Returns:the long value represented by this object is
     *           converted to type float and the result of
     *           the conversion is returned.Since:
     *   CLDC 1.1
     */
    public float floatValue();

    /**
     * Returns the value of this Long as a double.
     * 
     * Returns:the long value represented by this object that
     *           is converted to type double and the result of
     *           the conversion is returned.Since:
     *   CLDC 1.1
     */
    public double doubleValue();

    /**
     * Returns a String object representing this Long's value.
     *  The long integer value represented by this Long object is converted
     *  to signed decimal representation and returned as a string, exactly
     *  as if the long value were given as an argument to the
     *  toString(long) method that takes one argument.
     * 
     * Overrides:toString in class Object
     * 
     * Returns:a string representation of this object in base&nbsp;10.
     */
    public String toString();

    /**
     * Computes a hashcode for this Long. The result is the exclusive
     *  OR of the two halves of the primitive long value
     *  represented by this Long object. That is, the hashcode
     *  is the value of the expression:
     * 
     *  (int)(this.longValue()^(this.longValue()>>>32))
     * 
     * Overrides:hashCode in class Object
     * 
     * Returns:a hash code value for this object.See Also:Object.equals(java.lang.Object),
     * Hashtable
     */
    public int hashCode();

    /**
     * Compares this object against the specified object.
     *  The result is true if and only if the argument is
     *  not null and is a Long object that
     *  contains the same long value as this object.
     * 
     * Overrides:equals in class Object
     * 
     * Parameters:obj - the object to compare with.
     * Returns:true if the objects are the same;
     *           false otherwise.See Also:Boolean.hashCode(),
     * Hashtable
     */
    public boolean equals(Object obj);

}

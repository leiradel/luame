package java.lang;

public class Integer {
    /**
     * The smallest value of type int. The constant
     *  value of this field is -2147483648.
     * 
     * See Also:Constant Field Values
     */
    public static final int MIN_VALUE = ;

    /**
     * The largest value of type int. The constant
     *  value of this field is 2147483647.
     * 
     * See Also:Constant Field Values
     */
    public static final int MAX_VALUE = ;

    /**
     * Constructs a newly allocated Integer object that
     *  represents the primitive int argument.
     * 
     * Parameters:value - the value to be represented by the Integer.
     */
    public Integer(int value) {
        construct(value);
    }

    private native void construct(int value);

    /**
     * Creates a string representation of the first argument in the
     *  radix specified by the second argument.
     * 
     *  If the radix is smaller than Character.MIN_RADIX or
     *  larger than Character.MAX_RADIX, then the radix
     *  10 is used instead.
     * 
     *  If the first argument is negative, the first element of the
     *  result is the ASCII minus character '-'
     *  ('&#92;u002d'). If the first
     *  argument is not negative, no sign character appears in the result.
     * 
     *  The remaining characters of the result represent the magnitude of
     *  the first argument. If the magnitude is zero, it is represented by
     *  a single zero character '0' ('&#92;u0030'); otherwise,
     *  the first character of the representation of the magnitude will
     *  not be the zero character.
     *  The following ASCII characters are used as digits:
     * 
     *    0123456789abcdefghijklmnopqrstuvwxyz
     * 
     *  These are '&#92;u0030' through '&#92;u0039' and
     *  '&#92;u0061' through '&#92;u007a'. If the
     *  radix is N, then the first N of these
     *  characters are used as radix-N digits in the order shown.
     *  Thus, the digits for hexadecimal (radix 16) are
     * 
     *  0123456789abcdef.
     * 
     * Parameters:i - an integer.radix - the radix.
     * Returns:a string representation of the argument in the specified radix.See Also:Character.MAX_RADIX,
     * Character.MIN_RADIX
     */
    public static String toString(int i, int radix);

    /**
     * Creates a string representation of the integer argument as an
     *  unsigned integer in base&nbsp;16.
     * 
     *  The unsigned integer value is the argument plus 232 if
     *  the argument is negative; otherwise, it is equal to the argument.
     *  This value is converted to a string of ASCII digits in hexadecimal
     *  (base&nbsp;16) with no extra leading 0s. If the
     *  unsigned magnitude is zero, it is represented by a single zero
     *  character '0' ('&#92;u0030'); otherwise, the first
     *  character of the representation of the unsigned magnitude will
     *  not be the zero character. The following characters are used as
     *  hexadecimal digits:
     * 
     *  0123456789abcdef
     * 
     *  These are the characters '&#92;u0030' through '&#92;u0039'
     *  and 'u\0039' through '&#92;u0066'.
     * 
     * Parameters:i - an integer.
     * Returns:the string representation of the unsigned integer value
     *           represented by the argument in hexadecimal (base&nbsp;16).Since:
     *   JDK1.0.2
     */
    public static String toHexString(int i);

    /**
     * Creates a string representation of the integer argument as an
     *  unsigned integer in base 8.
     * 
     *  The unsigned integer value is the argument plus 232 if
     *  the argument is negative; otherwise, it is equal to the argument.
     *  This value is converted to a string of ASCII digits in octal
     *  (base&nbsp;8) with no extra leading 0s.
     * 
     *  If the unsigned magnitude is zero, it is represented by a single
     *  zero character '0' ('&#92;u0030'); otherwise, the
     *  first character of the representation of the unsigned magnitude will
     *  not be the zero character. The octal digits are:
     * 
     *  01234567
     * 
     *  These are the characters '&#92;u0030' through '&#92;u0037'.
     * 
     * Parameters:i - an integer
     * Returns:the string representation of the unsigned integer value
     *           represented by the argument in octal (base&nbsp;8).Since:
     *   JDK1.0.2
     */
    public static String toOctalString(int i);

    /**
     * Creates a string representation of the integer argument as an
     *  unsigned integer in base&nbsp;2.
     * 
     *  The unsigned integer value is the argument plus 232if
     *  the argument is negative; otherwise it is equal to the argument.
     *  This value is converted to a string of ASCII digits in binary
     *  (base&nbsp;2) with no extra leading 0s.
     * 
     *  If the unsigned magnitude is zero, it is represented by a single
     *  zero character '0' ('&#92;u0030'); otherwise, the
     *  first character of the representation of the unsigned magnitude
     *  will not be the zero character. The characters '0'
     *  ('&#92;u0030') and '1' ('&#92;u0031') are used
     *  as binary digits.
     * 
     * Parameters:i - an integer.
     * Returns:the string representation of the unsigned integer value
     *           represented by the argument in binary (base&nbsp;2).Since:
     *   JDK1.0.2
     */
    public static String toBinaryString(int i);

    /**
     * Returns a new String object representing the specified integer. The
     *  argument is converted to signed decimal representation and returned
     *  as a string, exactly as if the argument and radix 10 were
     *  given as arguments to the toString(int, int) method.
     * 
     * Parameters:i - an integer to be converted.
     * Returns:a string representation of the argument in base&nbsp;10.
     */
    public static String toString(int i);

    /**
     * Parses the string argument as a signed integer in the radix
     *  specified by the second argument. The characters in the string
     *  must all be digits of the specified radix (as determined by
     *  whether Character.digit(char, int) returns a
     *  nonnegative value), except that the first character may be an
     *  ASCII minus sign '-' ('&#92;u002d') to
     *  indicate a negative value. The resulting integer value is returned.
     * 
     *  An exception of type NumberFormatException is thrown if any
     *  of the following situations occurs:
     * 
     *  The first argument is null or is a string of length zero.
     *  The radix is either smaller than
     *  Character.MIN_RADIX or
     *  larger than Character.MAX_RADIX.
     *  Any character of the string is not a digit of the specified radix,
     *  except that the first character may be a minus sign '-'
     *  ('&#92;u002d') provided that the string is longer than length 1.
     *  The integer value represented by the string is not a value of type
     *  int.
     * 
     *  Examples:
     * 
     *  parseInt("0", 10) returns 0
     *  parseInt("473", 10) returns 473
     *  parseInt("-0", 10) returns 0
     *  parseInt("-FF", 16) returns -255
     *  parseInt("1100110", 2) returns 102
     *  parseInt("2147483647", 10) returns 2147483647
     *  parseInt("-2147483648", 10) returns -2147483648
     *  parseInt("2147483648", 10) throws a NumberFormatException
     *  parseInt("99", 8) throws a NumberFormatException
     *  parseInt("Kona", 10) throws a NumberFormatException
     *  parseInt("Kona", 27) returns 411787
     * 
     * Parameters:s - the String containing the integer.radix - the radix to be used.
     * Returns:the integer represented by the string argument in the
     *              specified radix.
     * Throws:
     * NumberFormatException - if the string does not contain a
     *                parsable integer.
     */
    public static int parseInt(String s, int radix) throws NumberFormatException;

    /**
     * Parses the string argument as a signed decimal integer. The
     *  characters in the string must all be decimal digits, except that
     *  the first character may be an ASCII minus sign '-'
     *  ('&#92;u002d') to indicate a negative value. The resulting
     *  integer value is returned, exactly as if the argument and the radix
     *  10 were given as arguments to the
     *  parseInt(java.lang.String, int) method.
     * 
     * Parameters:s - a string.
     * Returns:the integer represented by the argument in decimal.
     * Throws:
     * NumberFormatException - if the string does not contain a
     *                parsable integer.
     */
    public static int parseInt(String s) throws NumberFormatException;

    /**
     * Returns a new Integer object initialized to the value of the
     *  specified String. The first argument is interpreted as representing
     *  a signed integer in the radix specified by the second argument,
     *  exactly as if the arguments were given to the
     *  parseInt(java.lang.String, int) method. The result is an
     *  Integer object that represents the integer value
     *  specified by the string.
     * 
     *  In other words, this method returns an Integer object
     *  equal to the value of:
     * 
     *  new Integer(Integer.parseInt(s, radix))
     * 
     * Parameters:s - the string to be parsed.radix - the radix of the integer represented by string
     *              s
     * Returns:a newly constructed Integer initialized to the
     *              value represented by the string argument in the specified
     *              radix.
     * Throws:
     * NumberFormatException - if the String cannot be
     *              parsed as an int.
     */
    public static Integer valueOf(String s, int radix) throws NumberFormatException;

    /**
     * Returns a new Integer object initialized to the value of the
     *  specified String. The argument is interpreted as representing a
     *  signed decimal integer, exactly as if the argument were given to
     *  the parseInt(java.lang.String) method. The result is an
     *  Integer object that represents the integer value specified
     *  by the string.
     * 
     *  In other words, this method returns an Integer object equal
     *  to the value of:
     * 
     *  new Integer(Integer.parseInt(s))
     * 
     * Parameters:s - the string to be parsed.
     * Returns:a newly constructed Integer initialized to the
     *              value represented by the string argument.
     * Throws:
     * NumberFormatException - if the string cannot be parsed
     *              as an integer.
     */
    public static Integer valueOf(String s) throws NumberFormatException;

    /**
     * Returns the value of this Integer as a byte.
     * 
     * Returns:the value of this Integer as a byte.Since:
     *   JDK1.1
     */
    public byte byteValue();

    /**
     * Returns the value of this Integer as a short.
     * 
     * Returns:the value of this Integer as a short.Since:
     *   JDK1.1
     */
    public short shortValue();

    /**
     * Returns the value of this Integer as an int.
     * 
     * Returns:the int value represented by this object.
     */
    public int intValue();

    /**
     * Returns the value of this Integer as a long.
     * 
     * Returns:the int value represented by this object that is
     *           converted to type long and the result of the
     *           conversion is returned.
     */
    public long longValue();

    /**
     * Returns the value of this Integer as a float.
     * 
     * Returns:the int value represented by this object is
     *           converted to type float and the result of the
     *           conversion is returned.Since:
     *   CLDC 1.1
     */
    public float floatValue();

    /**
     * Returns the value of this Integer as a double.
     * 
     * Returns:the int value represented by this object is
     *           converted to type double and the result of the
     *           conversion is returned.Since:
     *   CLDC 1.1
     */
    public double doubleValue();

    /**
     * Returns a String object representing this Integer's value. The
     *  value is converted to signed decimal representation and returned
     *  as a string, exactly as if the integer value were given as an
     *  argument to the toString(int) method.
     * 
     * Overrides:toString in class Object
     * 
     * Returns:a string representation of the value of this object in
     *           base&nbsp;10.
     */
    public String toString();

    /**
     * Returns a hashcode for this Integer.
     * 
     * Overrides:hashCode in class Object
     * 
     * Returns:a hash code value for this object, equal to the
     *           primitive int value represented by this
     *           Integer object.See Also:Object.equals(java.lang.Object),
     * Hashtable
     */
    public int hashCode();

    /**
     * Compares this object to the specified object.
     *  The result is true if and only if the argument is not
     *  null and is an Integer object that contains
     *  the same int value as this object.
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

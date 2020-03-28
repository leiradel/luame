package java.lang;

public class Double {
    /**
     * The positive infinity of type double.
     *  It is equal to the value returned by
     *  Double.longBitsToDouble(0x7ff0000000000000L).
     * 
     * See Also:Constant Field Values
     */
    public static final double POSITIVE_INFINITY = ;

    /**
     * The negative infinity of type double.
     *  It is equal to the value returned by
     *  Double.longBitsToDouble(0xfff0000000000000L).
     * 
     * See Also:Constant Field Values
     */
    public static final double NEGATIVE_INFINITY = ;

    /**
     * A Not-a-Number (NaN) value of type double.
     *  It is equal to the value returned by
     *  Double.longBitsToDouble(0x7ff8000000000000L).
     * 
     * See Also:Constant Field Values
     */
    public static final double NaN = ;

    /**
     * The largest positive finite value of type double.
     *  It is equal to the value returned by
     * 
     *  Double.longBitsToDouble(0x7fefffffffffffffL)
     * 
     * See Also:Constant Field Values
     */
    public static final double MAX_VALUE = ;

    /**
     * The smallest positive value of type double.
     *  It is equal to the value returned by
     *  Double.longBitsToDouble(0x1L).
     */
    public static final double MIN_VALUE = ;

    /**
     * Constructs a newly allocated Double object that
     *  represents the primitive double argument.
     * 
     * Parameters:value - the value to be represented by the Double.
     */
    public Double(double value) {
        construct(value);
    }

    private native void construct(double value);

    /**
     * Creates a string representation of the double
     *  argument. All characters mentioned below are ASCII characters.
     * 
     *  If the argument is NaN, the result is the string "NaN".
     *  Otherwise, the result is a string that represents the sign and
     *  magnitude (absolute value) of the argument. If the sign is negative,
     *  the first character of the result is '-'
     *  ('-'); if the sign is positive, no sign character
     *  appears in the result. As for the magnitude m:
     *  If m is infinity, it is represented by the characters
     *  "Infinity"; thus, positive infinity produces the result
     *  "Infinity" and negative infinity produces the result
     *  "-Infinity".
     *  If m is zero, it is represented by the characters
     *  "0.0"; thus, negative zero produces the result
     *  "-0.0" and positive zero produces the result
     *  "0.0".
     *  If m is greater than or equal to 10-3 but less
     *  than 107, then it is represented as the integer part of
     *  m, in decimal form with no leading zeroes, followed by
     *  '.' (.), followed by one or more decimal
     *  digits representing the fractional part of m.
     *  If m is less than 10-3 or not less than
     *  107, then it is represented in so-called "computerized
     *  scientific notation." Let n be the unique integer such that
     *  10n&lt;=m&lt;10n+1; then let a be
     *  the mathematically exact quotient of m and 10n so
     *  that 1&lt;=a&lt;10. The magnitude is then represented as the
     *  integer part of a, as a single decimal digit, followed
     *  by '.' (.), followed by decimal digits
     *  representing the fractional part of a, followed by the letter
     *  'E' (E), followed by a representation
     *  of n as a decimal integer, as produced by the method
     *  Integer.toString(int).
     * 
     *  How many digits must be printed for the fractional part of
     *  m or a? There must be at least one digit to represent
     *  the fractional part, and beyond that as many, but only as many, more
     *  digits as are needed to uniquely distinguish the argument value from
     *  adjacent values of type double. That is, suppose that
     *  x is the exact mathematical value represented by the decimal
     *  representation produced by this method for a finite nonzero argument
     *  d. Then d must be the double value nearest
     *  to x; or if two double values are equally close
     *  to x, then d must be one of them and the least
     *  significant bit of the significand of d must be 0.
     * 
     * Parameters:d - the double to be converted.
     * Returns:a string representation of the argument.
     */
    public static String toString(double d);

    /**
     * Returns a new Double object initialized to the value
     *  represented by the specified string. The string s is
     *  interpreted as the representation of a floating-point value and a
     *  Double object representing that value is created and
     *  returned.
     * 
     *  If s is null, then a
     *  NullPointerException is thrown.
     * 
     *  Leading and trailing whitespace characters in s are ignored. The rest
     *  of s should constitute a FloatValue as described
     *  by the lexical rule:
     * 
     *  FloatValue:
     * 
     *         Signopt FloatingPointLiteral
     * 
     *  where Sign and FloatingPointLiteral are as defined in
     *  Section 3.10.2 of the Java
     *  Language Specification. If it does not have the form of a
     *  FloatValue, then a NumberFormatException is
     *  thrown. Otherwise, it is regarded as representing an exact decimal
     *  value in the usual "computerized scientific notation"; this exact
     *  decimal value is then conceptually converted to an "infinitely
     *  precise" binary value that is then rounded to type double
     *  by the usual round-to-nearest rule of IEEE 754 floating-point
     *  arithmetic. Finally, a new object of class Double is
     *  created to represent the double value.
     * 
     * Parameters:s - the string to be parsed.
     * Returns:a newly constructed Double initialized to the
     *              value represented by the string argument.
     * Throws:
     * NumberFormatException - if the string does not contain a
     *                parsable number.
     */
    public static Double valueOf(String s) throws NumberFormatException;

    /**
     * Returns a new double initialized to the value represented by the
     *  specified String, as performed by the valueOf
     *  method of class Double.
     * 
     * Parameters:s - the string to be parsed.
     * Returns:the double value represented by the string argument.
     * Throws:
     * NumberFormatException - if the string does not contain a
     *                parsable double.Since:
     *   JDK1.2
     * See Also:valueOf(String)
     */
    public static double parseDouble(String s) throws NumberFormatException;

    /**
     * Returns true if the specified number is the special Not-a-Number (NaN)
     *  value.
     * 
     * Parameters:v - the value to be tested.
     * Returns:true if the value of the argument is NaN;
     *           false otherwise.
     */
    public static boolean isNaN(double v);

    /**
     * Returns true if the specified number is infinitely large in magnitude.
     * 
     * Parameters:v - the value to be tested.
     * Returns:true if the value of the argument is positive
     *           infinity or negative infinity; false otherwise.
     */
    public static boolean isInfinite(double v);

    /**
     * Returns true if this Double value is the special Not-a-Number (NaN)
     *  value.
     * 
     * Returns:true if the value represented by this object is
     *           NaN; false otherwise.
     */
    public boolean isNaN();

    /**
     * Returns true if this Double value is infinitely large in magnitude.
     * 
     * Returns:true if the value represented by this object is
     *           positive infinity or negative infinity;
     *           false otherwise.
     */
    public boolean isInfinite();

    /**
     * Returns a String representation of this Double object.
     *  The primitive double value represented by this
     *  object is converted to a string exactly as if by the method
     *  toString of one argument.
     * 
     * Overrides:toString in class Object
     * 
     * Returns:a String representation of this object.See Also:toString(double)
     */
    public String toString();

    /**
     * Returns the value of this Double as a byte (by casting to a byte).
     * 
     * Since:
     *   JDK1.1
     */
    public byte byteValue();

    /**
     * Returns the value of this Double as a short (by casting to a short).
     * 
     * Since:
     *   JDK1.1
     */
    public short shortValue();

    /**
     * Returns the integer value of this Double (by casting to an int).
     * 
     * Returns:the double value represented by this object is
     *           converted to type int and the result of the
     *           conversion is returned.
     */
    public int intValue();

    /**
     * Returns the long value of this Double (by casting to a long).
     * 
     * Returns:the double value represented by this object is
     *           converted to type long and the result of the
     *           conversion is returned.
     */
    public long longValue();

    /**
     * Returns the float value of this Double.
     * 
     * Returns:the double value represented by this object is
     *           converted to type float and the result of the
     *           conversion is returned.Since:
     *   JDK1.0
     */
    public float floatValue();

    /**
     * Returns the double value of this Double.
     * 
     * Returns:the double value represented by this object.
     */
    public double doubleValue();

    /**
     * Returns a hashcode for this Double object. The result
     *  is the exclusive OR of the two halves of the long integer bit
     *  representation, exactly as produced by the method
     *  doubleToLongBits(double), of the primitive
     *  double value represented by this Double
     *  object. That is, the hashcode is the value of the expression:
     * 
     *  (int)(v^(v>>>32))
     * 
     *  where v is defined by:
     * 
     *  long v = Double.doubleToLongBits(this.doubleValue());
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
     *  not null and is a Double object that
     *  represents a double that has the identical bit pattern to the bit
     *  pattern of the double represented by this object. For this purpose,
     *  two double values are considered to be the same if and
     *  only if the method doubleToLongBits(double) returns the same
     *  long value when applied to each.
     * 
     *  Note that in most cases, for two instances of class
     *  Double, d1 and d2, the
     *  value of d1.equals(d2) is true if and
     *  only if
     * 
     *    d1.doubleValue()&nbsp;== d2.doubleValue()
     * 
     *  also has the value true. However, there are two
     *  exceptions:
     * 
     *  If d1 and d2 both represent
     *      Double.NaN, then the equals method
     *      returns true, even though
     *      Double.NaN==Double.NaN has the value
     *      false.
     *  If d1 represents +0.0 while
     *      d2 represents -0.0, or vice versa,
     *      the equals test has the value false,
     *      even though +0.0==-0.0 has the value true.
     *      This allows hashtables to operate properly.
     * 
     * Overrides:equals in class Object
     * 
     * Parameters:obj - the object to compare with.
     * Returns:true if the objects are the same;
     *           false otherwise.See Also:Boolean.hashCode(),
     * Hashtable
     */
    public boolean equals(Object obj);

    /**
     * Returns a representation of the specified floating-point value
     *  according to the IEEE 754 floating-point "double
     *  format" bit layout.
     * 
     *  Bit 63 (the bit that is selected by the mask
     *  0x8000000000000000L) represents the sign of the
     *  floating-point number. Bits
     *  62-52 (the bits that are selected by the mask
     *  0x7ff0000000000000L) represent the exponent. Bits 51-0
     *  (the bits that are selected by the mask
     *  0x000fffffffffffffL) represent the significand
     *  (sometimes called the mantissa) of the floating-point number.
     * 
     *  If the argument is positive infinity, the result is
     *  0x7ff0000000000000L.
     * 
     *  If the argument is negative infinity, the result is
     *  0xfff0000000000000L.
     * 
     *  If the argument is NaN, the result is
     *  0x7ff8000000000000L.
     * 
     *  In all cases, the result is a long integer that, when
     *  given to the longBitsToDouble(long) method, will produce a
     *  floating-point value equal to the argument to
     *  doubleToLongBits.
     * 
     * Parameters:value - a double precision floating-point number.
     * Returns:the bits that represent the floating-point number.
     */
    public static long doubleToLongBits(double value);

    /**
     * Returns the double-float corresponding to a given bit representation.
     *  The argument is considered to be a representation of a
     *  floating-point value according to the IEEE 754 floating-point
     *  "double precision" bit layout. That floating-point
     *  value is returned as the result.
     * 
     *  If the argument is 0x7ff0000000000000L, the result
     *  is positive infinity.
     * 
     *  If the argument is 0xfff0000000000000L, the result
     *  is negative infinity.
     * 
     *  If the argument is any value in the range
     *  0x7ff0000000000001L through
     *  0x7fffffffffffffffL or in the range
     *  0xfff0000000000001L through
     *  0xffffffffffffffffL, the result is NaN. All IEEE 754
     *  NaN values of type double are, in effect, lumped together
     *  by the Java programming language into a single value called NaN.
     * 
     *  In all other cases, let s, e, and m be three
     *  values that can be computed from the argument:
     * 
     *  int s = ((bits >> 63) == 0) ? 1 : -1;
     *  int e = (int)((bits >> 52) & 0x7ffL);
     *  long m = (e == 0) ?
     *                  (bits & 0xfffffffffffffL)
     *  Then the floating-point result equals the value of the mathematical
     *  expression s&#183;m&#183;2e-1075.
     * 
     * Parameters:bits - any long integer.
     * Returns:the double floating-point value with the same
     *           bit pattern.
     */
    public static double longBitsToDouble(long bits);

}

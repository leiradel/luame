package java.lang;

public class Float {
    /**
     * The positive infinity of type float. It is equal
     *  to the value returned by
     *  Float.intBitsToFloat(0x7f800000).
     * 
     * See Also:Constant Field Values
     */
    public static final float POSITIVE_INFINITY = ;

    /**
     * The negative infinity of type float. It is equal
     *  to the value returned by
     *  Float.intBitsToFloat(0xff800000).
     * 
     * See Also:Constant Field Values
     */
    public static final float NEGATIVE_INFINITY = ;

    /**
     * The Not-a-Number (NaN) value of type float.
     *  It is equal to the value returned by
     *  Float.intBitsToFloat(0x7fc00000).
     * 
     * See Also:Constant Field Values
     */
    public static final float NaN = ;

    /**
     * The largest positive value of type float. It is
     *  equal to the value returned by
     *  Float.intBitsToFloat(0x7f7fffff).
     * 
     * See Also:Constant Field Values
     */
    public static final float MAX_VALUE = ;

    /**
     * The smallest positive value of type float. It
     *  is equal to the value returned by
     *  Float.intBitsToFloat(0x1).
     * 
     * See Also:Constant Field Values
     */
    public static final float MIN_VALUE = ;

    /**
     * Constructs a newly allocated Float object that
     *  represents the primitive float argument.
     * 
     * Parameters:value - the value to be represented by the Float.
     */
    public Float(float value) {
        construct(value);
    }

    private native void construct(float value);

    /**
     * Constructs a newly allocated Floatobject that
     *  represents the argument converted to type float.
     * 
     * Parameters:value - the value to be represented by the Float.
     */
    public Float(double value) {
        construct(value);
    }

    private native void construct(double value);

    /**
     * Returns a String representation for the specified float value.
     *  The argument is converted to a readable string format as follows.
     *  All characters and characters in strings mentioned below are ASCII
     *  characters.
     * 
     *  If the argument is NaN, the result is the string "NaN".
     *  Otherwise, the result is a string that represents the sign and
     *      magnitude (absolute value) of the argument. If the sign is
     *      negative, the first character of the result is '-'
     *      ('-'); if the sign is positive, no sign character
     *      appears in the result. As for the magnitude m:
     * 
     *  If m is infinity, it is represented by the characters
     *      "Infinity"; thus, positive infinity produces the result
     *      "Infinity" and negative infinity produces the result
     *      "-Infinity".
     *  If m is zero, it is represented by the characters
     *      "0.0"; thus, negative zero produces the result
     *      "-0.0" and positive zero produces the result
     *       "0.0".
     *   If m is greater than or equal to 10-3 but
     *       less than 107, then it is represented as the integer
     *       part of m, in decimal form with no leading zeroes,
     *       followed by '.' (.), followed by one or
     *       more decimal digits representing the fractional part of
     *       m.
     *   If m is less than 10-3 or not less than
     *       107, then it is represented in so-called "computerized
     *       scientific notation." Let n be the unique integer
     *       such that 10n&lt;=m&lt;1; then let
     *       a be the mathematically exact quotient of m
     *       and 10n so that 1&lt;a&lt10. The magnitude
     *       is then represented as the integer part of a, as a
     *       single decimal digit, followed by '.' (.),
     *       followed by decimal digits representing the fractional part of
     *       a, followed by the letter 'E'
     *       (E), followed by a representation of n
     *       as a decimal integer, as produced by the method
     *       Integer.toString(int) of one argument.
     * 
     *  How many digits must be printed for the fractional part of
     *  m or a? There must be at least one digit to
     *  represent the fractional part, and beyond that as many, but only as
     *  many, more digits as are needed to uniquely distinguish the argument
     *  value from adjacent values of type float. That is, suppose that
     *  x is the exact mathematical value represented by the
     *  decimal representation produced by this method for a finite nonzero
     *  argument f. Then f must be the float value
     *  nearest to x; or, if two float values are equally close to
     *  xthen f must be one of them and the least
     *  significant bit of the significand of f must be 0.
     * 
     * Parameters:f - the float to be converted.
     * Returns:a string representation of the argument.
     */
    public static String toString(float f);

    /**
     * Returns the floating point value represented by the specified String.
     *  The string s is interpreted as the representation of a
     *  floating-point value and a Float object representing that
     *  value is created and returned.
     * 
     *  If s is null, then a
     *  NullPointerException is thrown.
     * 
     *  Leading and trailing whitespace characters in s are ignored. The rest
     *  of s should constitute a FloatValue as described
     *  by the lexical syntax rules:
     * 
     *  FloatValue:
     * 
     *           Signopt FloatingPointLiteral
     * 
     *  where Sign, FloatingPointLiteral are as defined in
     *  Section 3.10.2 of the
     *  Java Language
     *  Specification. If it does not have the form of a FloatValue,
     *  then a NumberFormatException is thrown. Otherwise, it is
     *  regarded as representing an exact decimal value in the usual
     *  "computerized scientific notation"; this exact decimal value is then
     *  conceptually converted to an "infinitely precise" binary value that
     *  is then rounded to type float by the usual round-to-nearest rule of
     *  IEEE 754 floating-point arithmetic.
     * 
     * Parameters:s - the string to be parsed.
     * Returns:a newly constructed Float initialized to the
     *              value represented by the String argument.
     * Throws:
     * NumberFormatException - if the string does not contain a
     *                parsable number.
     */
    public static Float valueOf(String s) throws NumberFormatException;

    /**
     * Returns a new float initialized to the value represented by the
     *  specified String.
     * 
     * Parameters:s - the string to be parsed.
     * Returns:the float value represented by the string argument.
     * Throws:
     * NumberFormatException - if the string does not contain a
     *                parsable float.Since:
     *   JDK1.2
     */
    public static float parseFloat(String s) throws NumberFormatException;

    /**
     * Returns true if the specified number is the special Not-a-Number (NaN)
     *  value.
     * 
     * Parameters:v - the value to be tested.
     * Returns:true if the argument is NaN;
     *           false otherwise.
     */
    public static boolean isNaN(float v);

    /**
     * Returns true if the specified number is infinitely large in magnitude.
     * 
     * Parameters:v - the value to be tested.
     * Returns:true if the argument is positive infinity or
     *           negative infinity; false otherwise.
     */
    public static boolean isInfinite(float v);

    /**
     * Returns true if this Float value is Not-a-Number (NaN).
     * 
     * Returns:true if the value represented by this object is
     *           NaN; false otherwise.
     */
    public boolean isNaN();

    /**
     * Returns true if this Float value is infinitely large in magnitude.
     * 
     * Returns:true if the value represented by this object is
     *           positive infinity or negative infinity;
     *           false otherwise.
     */
    public boolean isInfinite();

    /**
     * Returns a String representation of this Float object.
     *  The primitive float value represented by this object
     *  is converted to a String exactly as if by the method
     *  toString of one argument.
     * 
     * Overrides:toString in class Object
     * 
     * Returns:a String representation of this object.See Also:toString(float)
     */
    public String toString();

    /**
     * Returns the value of this Float as a byte (by casting to a byte).
     * 
     * Since:
     *   JDK1.1
     */
    public byte byteValue();

    /**
     * Returns the value of this Float as a short (by casting to a short).
     * 
     * Since:
     *   JDK1.1
     */
    public short shortValue();

    /**
     * Returns the integer value of this Float (by casting to an int).
     * 
     * Returns:the float value represented by this object
     *           converted to type int and the result of the
     *           conversion is returned.
     */
    public int intValue();

    /**
     * Returns the long value of this Float (by casting to a long).
     * 
     * Returns:the float value represented by this object is
     *           converted to type long and the result of the
     *           conversion is returned.
     */
    public long longValue();

    /**
     * Returns the float value of this Float object.
     * 
     * Returns:the float value represented by this object.
     */
    public float floatValue();

    /**
     * Returns the double value of this Float object.
     * 
     * Returns:the float value represented by this
     *          object is converted to type double and the
     *          result of the conversion is returned.
     */
    public double doubleValue();

    /**
     * Returns a hashcode for this Float object. The result
     *  is the integer bit representation, exactly as produced
     *  by the method floatToIntBits(float), of the primitive float
     *  value represented by this Float object.
     * 
     * Overrides:hashCode in class Object
     * 
     * Returns:a hash code value for this object.See Also:Object.equals(java.lang.Object),
     * Hashtable
     */
    public int hashCode();

    /**
     * Compares this object against some other object.
     *  The result is true if and only if the argument is
     *  not null and is a Float object that
     *  represents a float that has the identical bit pattern
     *  to the bit pattern of the float represented by this
     *  object. For this purpose, two float values are considered to be
     *  the same if and only if the method floatToIntBits(float)
     *  returns the same int value when applied to each.
     * 
     *  Note that in most cases, for two instances of class
     *  Float, f1 and f2, the value
     *  of f1.equals(f2) is true if and only if
     * 
     *    f1.floatValue() == f2.floatValue()
     * 
     *  also has the value true. However, there are two exceptions:
     * 
     *  If f1 and f2 both represent
     *      Float.NaN, then the equals method returns
     *      true, even though Float.NaN==Float.NaN
     *      has the value false.
     *  If f1 represents +0.0f while
     *      f2 represents -0.0f, or vice versa,
     *      the equal test has the value false,
     *      even though 0.0f==-0.0f has the value true.
     * 
     *  This definition allows hashtables to operate properly.
     * 
     * Overrides:equals in class Object
     * 
     * Parameters:obj - the object to be compared
     * Returns:true if the objects are the same;
     *           false otherwise.See Also:floatToIntBits(float)
     */
    public boolean equals(Object obj);

    /**
     * Returns the bit representation of a single-float value.
     *  The result is a representation of the floating-point argument
     *  according to the IEEE 754 floating-point "single
     *  precision" bit layout.
     * 
     *  Bit 31 (the bit that is selected by the mask
     *  0x80000000) represents the sign of the floating-point
     *  number.
     *  Bits 30-23 (the bits that are selected by the mask
     *  0x7f800000) represent the exponent.
     *  Bits 22-0 (the bits that are selected by the mask
     *  0x007fffff) represent the significand (sometimes called
     *  the mantissa) of the floating-point number.
     *  If the argument is positive infinity, the result is
     *  0x7f800000.
     *  If the argument is negative infinity, the result is
     *  0xff800000.
     *  If the argument is NaN, the result is 0x7fc00000.
     * 
     *  In all cases, the result is an integer that, when given to the
     *  intBitsToFloat(int) method, will produce a floating-point
     *  value equal to the argument to floatToIntBits.
     * 
     * Parameters:value - a floating-point number.
     * Returns:the bits that represent the floating-point number.
     */
    public static int floatToIntBits(float value);

    /**
     * Returns the single-float corresponding to a given bit representation.
     *  The argument is considered to be a representation of a
     *  floating-point value according to the IEEE 754 floating-point
     *  "single precision" bit layout.
     * 
     *  If the argument is 0x7f800000, the result is positive
     *  infinity.
     * 
     *  If the argument is 0xff800000, the result is negative
     *  infinity.
     * 
     *  If the argument is any value in the range 0x7f800001
     *  through 0x7fffffff or in the range
     *  0xff800001 through 0xffffffff, the result is
     *  NaN. All IEEE 754 NaN values of type float are, in effect,
     *  lumped together by the Java programming language into a single
     *  float value called NaN.
     * 
     *  In all other cases, let s, e, and m be three
     *  values that can be computed from the argument:
     * 
     *  int s = ((bits >> 31) == 0) ? 1 : -1;
     *  int e = ((bits >> 23) & 0xff);
     *  int m = (e == 0) ?
     *                  (bits & 0x7fffff)
     *  Then the floating-point result equals the value of the mathematical
     *  expression s&#183;m&#183;2e-150.
     * 
     * Parameters:bits - an integer.
     * Returns:the single-format floating-point value with the same bit
     *           pattern.
     */
    public static float intBitsToFloat(int bits);

}

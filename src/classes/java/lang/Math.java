package java.lang;

public class Math {
    /**
     * The double value that is closer than any other to
     *  e, the base of the natural logarithms.
     * 
     * Since:
     *   CLDC 1.1
     * See Also:Constant Field Values
     */
    public static final double E = ;

    /**
     * The double value that is closer than any other to
     *  pi, the ratio of the circumference of a circle to its diameter.
     * 
     * Since:
     *   CLDC 1.1
     * See Also:Constant Field Values
     */
    public static final double PI = ;

    /**
     * Returns the trigonometric sine of an angle.  Special cases:
     *  If the argument is NaN or an infinity, then the
     *  result is NaN.
     *  If the argument is positive zero, then the result is
     *  positive zero; if the argument is negative zero, then the
     *  result is negative zero.
     * 
     * Parameters:a - an angle, in radians.
     * Returns:the sine of the argument.Since:
     *   CLDC 1.1
     */
    public static double sin(double a);

    /**
     * Returns the trigonometric cosine of an angle. Special case:
     *  If the argument is NaN or an infinity, then the
     *  result is NaN.
     * 
     * Parameters:a - an angle, in radians.
     * Returns:the cosine of the argument.Since:
     *   CLDC 1.1
     */
    public static double cos(double a);

    /**
     * Returns the trigonometric tangent of an angle.  Special cases:
     *  If the argument is NaN or an infinity, then the result
     *  is NaN.
     *  If the argument is positive zero, then the result is
     *  positive zero; if the argument is negative zero, then the
     *  result is negative zero
     * 
     * Parameters:a - an angle, in radians.
     * Returns:the tangent of the argument.Since:
     *   CLDC 1.1
     */
    public static double tan(double a);

    /**
     * Converts an angle measured in degrees to the equivalent angle
     *  measured in radians.
     * 
     * Parameters:angdeg - an angle, in degrees
     * Returns:the measurement of the angle angdeg
     *           in radians.Since:
     *   CLDC 1.1
     */
    public static double toRadians(double angdeg);

    /**
     * Converts an angle measured in radians to the equivalent angle
     *  measured in degrees.
     * 
     * Parameters:angrad - an angle, in radians
     * Returns:the measurement of the angle angrad
     *           in degrees.Since:
     *   CLDC 1.1
     */
    public static double toDegrees(double angrad);

    /**
     * Returns the correctly rounded positive square root of a
     *  double value.
     *  Special cases:
     *  If the argument is NaN or less than zero, then the result
     *  is NaN.
     *  If the argument is positive infinity, then the result is positive
     *  infinity.
     *  If the argument is positive zero or negative zero, then the
     *  result is the same as the argument.
     * 
     * Parameters:a - a double value.
     * Returns:the positive square root of a.
     *           If the argument is NaN or less than zero, the result is NaN.Since:
     *   CLDC 1.1
     */
    public static double sqrt(double a);

    /**
     * Returns the smallest (closest to negative infinity)
     *  double value that is not less than the argument and is
     *  equal to a mathematical integer. Special cases:
     *  If the argument value is already equal to a mathematical
     *  integer, then the result is the same as the argument.
     *  If the argument is NaN or an infinity or positive zero or negative
     *  zero, then the result is the same as the argument.
     *  If the argument value is less than zero but greater than -1.0,
     *  then the result is negative zero.
     *  Note that the value of Math.ceil(x) is exactly the
     *  value of -Math.floor(-x).
     * 
     * Parameters:a - a double value.
     *  a&nbsp;&rceil;.-->
     * Returns:the smallest (closest to negative infinity)
     *           double value that is not less than the argument
     *           and is equal to a mathematical integer.Since:
     *   CLDC 1.1
     */
    public static double ceil(double a);

    /**
     * Returns the largest (closest to positive infinity)
     *  double value that is not greater than the argument and
     *  is equal to a mathematical integer. Special cases:
     *  If the argument value is already equal to a mathematical
     *  integer, then the result is the same as the argument.
     *  If the argument is NaN or an infinity or positive zero or
     *  negative zero, then the result is the same as the argument.
     * 
     * Parameters:a - a double value.
     *  a&nbsp;&rfloor;.-->
     * Returns:the largest (closest to positive infinity)
     *           double value that is not greater than the argument
     *           and is equal to a mathematical integer.Since:
     *   CLDC 1.1
     */
    public static double floor(double a);

    /**
     * Returns the absolute value of an int value.
     *  If the argument is not negative, the argument is returned.
     *  If the argument is negative, the negation of the argument is returned.
     * 
     *  Note that if the argument is equal to the value of
     *  Integer.MIN_VALUE, the most negative representable
     *  int value, the result is that same value, which is
     *  negative.
     * 
     * Parameters:a - an int value.
     * Returns:the absolute value of the argument.See Also:Integer.MIN_VALUE
     */
    public static int abs(int a);

    /**
     * Returns the absolute value of a long value.
     *  If the argument is not negative, the argument is returned.
     *  If the argument is negative, the negation of the argument is returned.
     * 
     *  Note that if the argument is equal to the value of
     *  Long.MIN_VALUE, the most negative representable
     *  long value, the result is that same value, which is
     *  negative.
     * 
     * Parameters:a - a long value.
     * Returns:the absolute value of the argument.See Also:Long.MIN_VALUE
     */
    public static long abs(long a);

    /**
     * Returns the absolute value of a float value.
     *  If the argument is not negative, the argument is returned.
     *  If the argument is negative, the negation of the argument is returned.
     *  Special cases:
     *  If the argument is positive zero or negative zero, the
     *  result is positive zero.
     *  If the argument is infinite, the result is positive infinity.
     *  If the argument is NaN, the result is NaN.
     *  In other words, the result is equal to the value of the expression:
     *  Float.intBitsToFloat(0x7fffffff & Float.floatToIntBits(a))
     * 
     * Parameters:a - a float value.
     * Returns:the absolute value of the argument.Since:
     *   CLDC 1.1
     */
    public static float abs(float a);

    /**
     * Returns the absolute value of a double value.
     *  If the argument is not negative, the argument is returned.
     *  If the argument is negative, the negation of the argument is returned.
     *  Special cases:
     *  If the argument is positive zero or negative zero, the result
     *  is positive zero.
     *  If the argument is infinite, the result is positive infinity.
     *  If the argument is NaN, the result is NaN.
     *  In other words, the result is equal to the value of the expression:
     *  Double.longBitsToDouble((Double.doubleToLongBits(a)>>1)
     * 
     * Parameters:a - a double value.
     * Returns:the absolute value of the argument.Since:
     *   CLDC 1.1
     */
    public static double abs(double a);

    /**
     * Returns the greater of two int values. That is, the
     *  result is the argument closer to the value of
     *  Integer.MAX_VALUE. If the arguments have the same value,
     *  the result is that same value.
     * 
     * Parameters:a - an int value.b - an int value.
     * Returns:the larger of a and b.See Also:Long.MAX_VALUE
     */
    public static int max(int a, int b);

    /**
     * Returns the greater of two long values. That is, the
     *  result is the argument closer to the value of
     *  Long.MAX_VALUE. If the arguments have the same value,
     *  the result is that same value.
     * 
     * Parameters:a - a long value.b - a long value.
     * Returns:the larger of a and b.See Also:Long.MAX_VALUE
     */
    public static long max(long a, long b);

    /**
     * Returns the greater of two float values.  That is, the
     *  result is the argument closer to positive infinity. If the
     *  arguments have the same value, the result is that same value. If
     *  either value is NaN, then the result is NaN.
     *  Unlike the the numerical comparison operators, this method considers
     *  negative zero to be strictly smaller than positive zero. If one
     *  argument is positive zero and the other negative zero, the result
     *  is positive zero.
     * 
     * Parameters:a - a float value.b - a float value.
     * Returns:the larger of a and b.
     */
    public static float max(float a, float b);

    /**
     * Returns the greater of two double values.  That is, the
     *  result is the argument closer to positive infinity. If the
     *  arguments have the same value, the result is that same value. If
     *  either value is NaN, then the result is NaN.
     *  Unlike the the numerical comparison operators, this method considers
     *  negative zero to be strictly smaller than positive zero. If one
     *  argument is positive zero and the other negative zero, the result
     *  is positive zero.
     * 
     * Parameters:a - a double value.b - a double value.
     * Returns:the larger of a and b.
     */
    public static double max(double a, double b);

    /**
     * Returns the smaller of two int values. That is, the
     *  result the argument closer to the value of Integer.MIN_VALUE.
     *  If the arguments have the same value, the result is that same value.
     * 
     * Parameters:a - an int value.b - an int value.
     * Returns:the smaller of a and b.See Also:Long.MIN_VALUE
     */
    public static int min(int a, int b);

    /**
     * Returns the smaller of two long values. That is, the
     *  result is the argument closer to the value of
     *  Long.MIN_VALUE. If the arguments have the same value,
     *  the result is that same value.
     * 
     * Parameters:a - a long value.b - a long value.
     * Returns:the smaller of a and b.See Also:Long.MIN_VALUE
     */
    public static long min(long a, long b);

    /**
     * Returns the smaller of two float values.  That is, the
     *  result is the value closer to negative infinity. If the arguments
     *  have the same value, the result is that same value. If either value
     *  is NaN, then the result is NaN.  Unlike the
     *  the numerical comparison operators, this method considers negative zero
     *  to be strictly smaller than positive zero.  If one argument is
     *  positive zero and the other is negative zero, the result is negative
     *  zero.
     * 
     * Parameters:a - a float value.b - a float value.
     * Returns:the smaller of a and b.Since:
     *   CLDC 1.1
     */
    public static float min(float a, float b);

    /**
     * Returns the smaller of two double values.  That is, the
     *  result is the value closer to negative infinity. If the arguments have
     *  the same value, the result is that same value. If either value
     *  is NaN, then the result is NaN.  Unlike the
     *  the numerical comparison operators, this method considers negative zero
     *  to be strictly smaller than positive zero. If one argument is
     *  positive zero and the other is negative zero, the result is negative
     *  zero.
     * 
     * Parameters:a - a double value.b - a double value.
     * Returns:the smaller of a and b.Since:
     *   CLDC 1.1
     */
    public static double min(double a, double b);

}

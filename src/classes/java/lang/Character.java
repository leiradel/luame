package java.lang;

public class Character {
    /**
     * The minimum radix available for conversion to and from Strings.
     * 
     * See Also:Integer.toString(int, int),
     * Integer.valueOf(java.lang.String),
     * Constant Field Values
     */
    public static final int MIN_RADIX = ;

    /**
     * The maximum radix available for conversion to and from Strings.
     * 
     * See Also:Integer.toString(int, int),
     * Integer.valueOf(java.lang.String),
     * Constant Field Values
     */
    public static final int MAX_RADIX = ;

    /**
     * The constant value of this field is the smallest value of type
     *  char.
     * 
     * Since:
     *   JDK1.0.2
     * See Also:Constant Field Values
     */
    public static final char MIN_VALUE = ;

    /**
     * The constant value of this field is the largest value of type
     *  char.
     * 
     * Since:
     *   JDK1.0.2
     * See Also:Constant Field Values
     */
    public static final char MAX_VALUE = ;

    /**
     * Returns the value of this Character object.
     * 
     * Returns:the primitive char value represented by
     *           this object.
     */
    public char charValue();

    /**
     * Returns a hash code for this Character.
     * 
     * Overrides:hashCode in class Object
     * 
     * Returns:a hash code value for this object.See Also:Object.equals(java.lang.Object),
     * Hashtable
     */
    public int hashCode();

    /**
     * Compares this object against the specified object.
     *  The result is true if and only if the argument is not
     *  null and is a Character object that
     *  represents the same char value as this object.
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
     * Returns a String object representing this character's value.
     *  Converts this Character object to a string. The
     *  result is a string whose length is 1. The string's
     *  sole component is the primitive char value represented
     *  by this object.
     * 
     * Overrides:toString in class Object
     * 
     * Returns:a string representation of this object.
     */
    public String toString();

    /**
     * Determines if the specified character is a lowercase character.
     * 
     *  Note that by default CLDC only supports
     *  the ISO Latin-1 range of characters.
     * 
     *  Of the ISO Latin-1 characters (character codes 0x0000 through 0x00FF),
     *  the following are lowercase:
     * 
     *  a b c d e f g h i j k l m n o p q r s t u v w x y z
     *  &#92;u00DF &#92;u00E0 &#92;u00E1 &#92;u00E2 &#92;u00E3 &#92;u00E4 &#92;u00E5 &#92;u00E6 &#92;u00E7
     *  &#92;u00E8 &#92;u00E9 &#92;u00EA &#92;u00EB &#92;u00EC &#92;u00ED &#92;u00EE &#92;u00EF &#92;u00F0
     *  &#92;u00F1 &#92;u00F2 &#92;u00F3 &#92;u00F4 &#92;u00F5 &#92;u00F6 &#92;u00F8 &#92;u00F9 &#92;u00FA
     *  &#92;u00FB &#92;u00FC &#92;u00FD &#92;u00FE &#92;u00FF
     * 
     * Parameters:ch - the character to be tested.
     * Returns:true if the character is lowercase;
     *           false otherwise.Since:
     *   JDK1.0
     */
    public static boolean isLowerCase(char ch);

    /**
     * Determines if the specified character is an uppercase character.
     * 
     *  Note that by default CLDC only supports
     *  the ISO Latin-1 range of characters.
     * 
     *  Of the ISO Latin-1 characters (character codes 0x0000 through 0x00FF),
     *  the following are uppercase:
     * 
     *  A B C D E F G H I J K L M N O P Q R S T U V W X Y Z
     *  &#92;u00C0 &#92;u00C1 &#92;u00C2 &#92;u00C3 &#92;u00C4 &#92;u00C5 &#92;u00C6 &#92;u00C7
     *  &#92;u00C8 &#92;u00C9 &#92;u00CA &#92;u00CB &#92;u00CC &#92;u00CD &#92;u00CE &#92;u00CF &#92;u00D0
     *  &#92;u00D1 &#92;u00D2 &#92;u00D3 &#92;u00D4 &#92;u00D5 &#92;u00D6 &#92;u00D8 &#92;u00D9 &#92;u00DA
     *  &#92;u00DB &#92;u00DC &#92;u00DD &#92;u00DE
     * 
     * Parameters:ch - the character to be tested.
     * Returns:true if the character is uppercase;
     *           false otherwise.Since:
     *   1.0
     * See Also:isLowerCase(char),
     * toUpperCase(char)
     */
    public static boolean isUpperCase(char ch);

    /**
     * Determines if the specified character is a digit.
     * 
     * Parameters:ch - the character to be tested.
     * Returns:true if the character is a digit;
     *           false otherwise.Since:
     *   JDK1.0
     */
    public static boolean isDigit(char ch);

    /**
     * The given character is mapped to its lowercase equivalent; if the
     *  character has no lowercase equivalent, the character itself is
     *  returned.
     * 
     *  Note that by default CLDC only supports
     *  the ISO Latin-1 range of characters.
     * 
     * Parameters:ch - the character to be converted.
     * Returns:the lowercase equivalent of the character, if any;
     *           otherwise the character itself.Since:
     *   JDK1.0
     * See Also:isLowerCase(char),
     * isUpperCase(char),
     * toUpperCase(char)
     */
    public static char toLowerCase(char ch);

    /**
     * Converts the character argument to uppercase; if the
     *  character has no uppercase equivalent, the character itself is
     *  returned.
     * 
     *  Note that by default CLDC only supports
     *  the ISO Latin-1 range of characters.
     * 
     * Parameters:ch - the character to be converted.
     * Returns:the uppercase equivalent of the character, if any;
     *           otherwise the character itself.Since:
     *   JDK1.0
     * See Also:isLowerCase(char),
     * isUpperCase(char),
     * toLowerCase(char)
     */
    public static char toUpperCase(char ch);

}

package java.lang;

public abstract class String {
    private native void construct(byte[] bytes, int off, int len);
    private native void construct(byte[] bytes, int off, int len, String enc);
    private native void construct(char[] value, int offset, int count);

    // Initializes a newly created String object so that it represents an empty character sequence.
    public String() {
        construct(new byte[0], 0, 0);
    }

    // Construct a new String by converting the specified array of bytes using the platform's default character encoding.
    public String(byte[] bytes) {
        construct(bytes, 0, bytes.length);
    }

    // Construct a new String by converting the specified subarray of bytes using the platform's default character encoding.
    public String(byte[] bytes, int off, int len) {
        construct(bytes, off, len);
    }

    // Construct a new String by converting the specified subarray of bytes using the specified character encoding.
    public String(byte[] bytes, int off, int len, String enc) {
        construct(bytes, off, len, enc);
    }

    // Construct a new String by converting the specified array of bytes using the specified character encoding.
    public String(byte[] bytes, String enc) {
        construct(bytes, 0, bytes.length, enc);
    }

    // Allocates a new String so that it represents the sequence of characters currently contained in the character array argument.
    public String(char[] value) {
        construct(value, 0, value.length);
    }

    // Allocates a new String that contains characters from a subarray of the character array argument.
    public String(char[] value, int offset, int count) {
        construct(value, offset, count);
    }

    // Initializes a newly created String object so that it represents the same sequence of characters as the argument; in other words, the newly created string is a copy of the argument string.
    public String(String value) {
        this(value.toCharArray());
    }

    // Allocates a new string that contains the sequence of characters currently contained in the string buffer argument.
    public String(StringBuffer buffer) {
        this(buffer.toString().toCharArray());
    }

    // Returns the character at the specified index.
    public abstract char charAt(int index);

    // Compares two strings lexicographically.
    public abstract int compareTo(String anotherString);

    // Concatenates the specified string to the end of this string.
    public abstract String concat(String str);

    // Tests if this string ends with the specified suffix.
    public abstract boolean endsWith(String suffix);

    // Compares this string to the specified object.
    public abstract boolean equals(Object anObject);

    // Convert this String into bytes according to the platform's default character encoding, storing the result into a new byte array.
    public abstract byte[] getBytes();

    // Convert this String into bytes according to the specified character encoding, storing the result into a new byte array.
    public abstract byte[] getBytes(String enc);

    // Copies characters from this string into the destination character array.
    public abstract void getChars(int srcBegin, int srcEnd, char[] dst, int dstBegin);

    // Returns a hashcode for this string.
    public abstract int hashCode();

    // Returns the index within this string of the first occurrence of the specified character.
    public abstract int indexOf(int ch);

    // Returns the index within this string of the first occurrence of the specified character, starting the search at the specified index.
    public abstract int indexOf(int ch, int fromIndex);

    // Returns the index within this string of the first occurrence of the specified substring.
    public abstract int indexOf(String str);

    // Returns the index within this string of the first occurrence of the specified substring, starting at the specified index.
    public abstract int indexOf(String str, int fromIndex);

    // Returns the index within this string of the last occurrence of the specified character.
    public abstract int lastIndexOf(int ch);

    // Returns the index within this string of the last occurrence of the specified character, searching backward starting at the specified index.
    public abstract int lastIndexOf(int ch, int fromIndex);

    // Returns the length of this string.
    public abstract int length();

    // Tests if two string regions are equal.
    public abstract boolean regionMatches(boolean ignoreCase, int toffset, String other, int ooffset, int len);

    // Returns a new string resulting from replacing all occurrences of oldChar in this string with newChar.
    public abstract String replace(char oldChar, char newChar);

    // Tests if this string starts with the specified prefix.
    public abstract boolean startsWith(String prefix);

    // Tests if this string starts with the specified prefix beginning a specified index.
    public abstract boolean startsWith(String prefix, int toffset);

    // Returns a new string that is a substring of this string.
    public abstract String substring(int beginIndex);

    // Returns a new string that is a substring of this string.
    public abstract String substring(int beginIndex, int endIndex);

    // Converts this string to a new character array.
    public abstract char[] toCharArray();

    // Converts all of the characters in this String to lower case.
    public abstract String toLowerCase();

    // This object (which is already a string!) is itself returned.
    public abstract String toString();

    // Converts all of the characters in this String to upper case.
    public abstract String toUpperCase();

    // Removes white space from both ends of this string.
    public abstract String trim();

    // Returns the string representation of the boolean argument.
    public static native String valueOf(boolean b);

    // Returns the string representation of the char argument.
    public static native String valueOf(char c);

    // Returns the string representation of the char array argument.
    public static native String valueOf(char[] data);

    // Returns the string representation of a specific subarray of the char array argument.
    public static native String valueOf(char[] data, int offset, int count);

    // Returns the string representation of the int argument.
    public static native String valueOf(int i);

    // Returns the string representation of the long argument.
    public static native String valueOf(long l);

    // Returns the string representation of the Object argument.
    public static native String valueOf(Object obj);
}

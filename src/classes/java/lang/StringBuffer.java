package java.lang;

public abstract class StringBuffer {
    private char[] chars;
    private int length;

    // Constructs a string buffer with no characters in it and an initial capacity of 16 characters.
    public StringBuffer() {
        this(16);
    }

    // Constructs a string buffer with no characters in it and an initial capacity specified by the length argument.
    public StringBuffer(int length) {
        chars = new char[length];
        length = 0;
    }

    // Constructs a string buffer so that it represents the same sequence of characters as the string argument; in other words, the initial contents of the string buffer is a copy of the argument string.
    public StringBuffer(String str) {
        chars = str.toCharArray();
        length = chars.length;
    }

    // Appends the string representation of the boolean argument to the string buffer.
    public StringBuffer append(boolean b) {
        return insert(length, b);
    }

    // Appends the string representation of the char argument to this string buffer.
    public StringBuffer append(char c) {
        return insert(length, c);
    }

    // Appends the string representation of the char array argument to this string buffer.
    public StringBuffer append(char[] str) {
        return insert(length, str);
    }

    // Appends the string representation of a subarray of the char array argument to this string buffer.
    public StringBuffer append(char[] str, int offset, int len) {
        return insert(length, str, offset, len);
    }

    // Appends the string representation of the int argument to this string buffer.
    public StringBuffer append(int i) {
        return insert(length, i);
    }

    // Appends the string representation of the long argument to this string buffer.
    public StringBuffer append(long l) {
        return insert(length, l);
    }

    // Appends the string representation of the Object argument to this string buffer.
    public StringBuffer append(Object obj) {
        return insert(length, obj);
    }

    // Appends the string to this string buffer.
    public StringBuffer append(String str) {
        return insert(length, str);
    }

    // Returns the current capacity of the String buffer.
    public int capacity() {
        return chars.length;
    }

    // The specified character of the sequence currently represented by the string buffer, as indicated by the index argument, is returned.
    public char charAt(int index) {
        return chars[index];
    }

    // Removes the characters in a substring of this StringBuffer.
    public StringBuffer delete(int start, int end) {
        System.arraycopy(chars, end, chars, start, length - end);
        return this;
    }

    // Removes the character at the specified position in this StringBuffer (shortening the StringBuffer by one character).
    public StringBuffer deleteCharAt(int index) {
        return delete(index, index + 1);
    }

    // Ensures that the capacity of the buffer is at least equal to the specified minimum.
    public void ensureCapacity(int minimumCapacity) {
        int newCapacity = minimumCapacity - 1;
        newCapacity |= newCapacity >> 1;
        newCapacity |= newCapacity >> 2;
        newCapacity |= newCapacity >> 4;
        newCapacity |= newCapacity >> 8;
        newCapacity |= newCapacity >> 16;
        newCapacity++;

        char[] newChars = new char[newCapacity];
        System.arraycopy(chars, 0, newChars, 0, length);
        chars = newChars;
    }

    // Characters are copied from this string buffer into the destination character array dst.
    public void getChars(int srcBegin, int srcEnd, char[] dst, int dstBegin) {
        System.arraycopy(chars, srcBegin, dst, dstBegin, srcEnd - srcBegin);
    }

    // Inserts the string representation of the boolean argument into this string buffer.
    public StringBuffer insert(int offset, boolean b) {
        return insert(offset, String.valueOf(b));
    }

    // Inserts the string representation of the char argument into this string buffer.
    public StringBuffer insert(int offset, char c) {
        return insert(offset, String.valueOf(c));
    }

    // Inserts the string representation of the char array argument into this string buffer.
    public StringBuffer insert(int offset, char[] str) {
        return insert(offset, str, 0, str.length);
    }

    public StringBuffer insert(int offset, char[] str, int start, int len) {
        ensureCapacity(length + len);

        System.arraycopy(chars, offset, chars, offset + len, len);
        System.arraycopy(str, start, chars, offset, len);

        length += len;
        return this;
    }

    // Inserts the string representation of the second int argument into this string buffer.
    public StringBuffer insert(int offset, int i) {
        return insert(offset, String.valueOf(i));
    }

    // Inserts the string representation of the long argument into this string buffer.
    public StringBuffer insert(int offset, long l) {
        return insert(offset, String.valueOf(l));
    }

    // Inserts the string representation of the Object argument into this string buffer.
    public StringBuffer insert(int offset, Object obj) {
        return insert(offset, String.valueOf(obj));
    }

    // Inserts the string into this string buffer.
    public StringBuffer insert(int offset, String str) {
        return insert(offset, str.toCharArray());
    }

    // Returns the length (character count) of this string buffer.
    public int length() {
        return length;
    }

    // The character sequence contained in this string buffer is replaced by the reverse of the sequence.
    public StringBuffer reverse() {
        for (int i = 0; i < length / 2; i++) {
            int j = length - i - 1;
            char temp = chars[i];
            chars[i] = chars[j];
            chars[j] = temp;
        }

        return this;
    }

    // The character at the specified index of this string buffer is set to ch.
    public void setCharAt(int index, char ch) {
        chars[index] = ch;
    }

    // Sets the length of this String buffer.
    public void setLength(int newLength) {
        ensureCapacity(newLength);

        for (int i = length; i < newLength; i++) {
            chars[i] = 0;
        }

        length = newLength;
    }

    // Converts to a string representing the data in this string buffer.
    public String toString() {
        return new String(chars, 0, length);
    }
}

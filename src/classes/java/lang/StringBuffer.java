package java.lang;

public class StringBuffer {
    /**
     * Constructs a string buffer with no characters in it and an
     *  initial capacity of 16 characters.
     */
    public StringBuffer() {
        construct();
    }

    private native void construct();

    /**
     * Constructs a string buffer with no characters in it and an
     *  initial capacity specified by the length argument.
     * 
     * Parameters:length - the initial capacity.
     * Throws:
     * NegativeArraySizeException - if the length
     *                argument is less than 0.
     */
    public StringBuffer(int length) {
        construct(length);
    }

    private native void construct(int length);

    /**
     * Constructs a string buffer so that it represents the same
     *  sequence of characters as the string argument; in other
     *  words, the initial contents of the string buffer is a copy of the
     *  argument string. The initial capacity of the string buffer is
     *  16 plus the length of the string argument.
     * 
     * Parameters:str - the initial contents of the buffer.
     */
    public StringBuffer(String str) {
        construct(str);
    }

    private native void construct(String str);

    /**
     * Returns the length (character count) of this string buffer.
     * 
     * Returns:the length of the sequence of characters currently
     *           represented by this string buffer.
     */
    public int length();

    /**
     * Returns the current capacity of the String buffer. The capacity
     *  is the amount of storage available for newly inserted
     *  characters; beyond which an allocation will occur.
     * 
     * Returns:the current capacity of this string buffer.
     */
    public int capacity();

    /**
     * Ensures that the capacity of the buffer is at least equal to the
     *  specified minimum.
     *  If the current capacity of this string buffer is less than the
     *  argument, then a new internal buffer is allocated with greater
     *  capacity. The new capacity is the larger of:
     * 
     *  The minimumCapacity argument.
     *  Twice the old capacity, plus 2.
     * 
     *  If the minimumCapacity argument is nonpositive, this
     *  method takes no action and simply returns.
     * 
     * Parameters:minimumCapacity - the minimum desired capacity.
     */
    public void ensureCapacity(int minimumCapacity);

    /**
     * Sets the length of this string buffer.
     *  This string buffer is altered to represent a new character sequence
     *  whose length is specified by the argument. For every nonnegative
     *  index k less than newLength, the character at
     *  index k in the new character sequence is the same as the
     *  character at index k in the old sequence if k is less
     *  than the length of the old character sequence; otherwise, it is the
     *  null character ' '.
     * 
     *  In other words, if the newLength argument is less than
     *  the current length of the string buffer, the string buffer is
     *  truncated to contain exactly the number of characters given by the
     *  newLength argument.
     * 
     *  If the newLength argument is greater than or equal
     *  to the current length, sufficient null characters
     *  ('&#92;u0000') are appended to the string buffer so that
     *  length becomes the newLength argument.
     * 
     *  The newLength argument must be greater than or equal
     *  to 0.
     * 
     * Parameters:newLength - the new length of the buffer.
     * Throws:
     * IndexOutOfBoundsException - if the
     *                newLength argument is negative.See Also:length()
     */
    public void setLength(int newLength);

    /**
     * The specified character of the sequence currently represented by
     *  the string buffer, as indicated by the index argument,
     *  is returned. The first character of a string buffer is at index
     *  0, the next at index 1, and so on, for
     *  array indexing.
     * 
     *  The index argument must be greater than or equal to
     *  0, and less than the length of this string buffer.
     * 
     * Parameters:index - the index of the desired character.
     * Returns:the character at the specified index of this string buffer.
     * Throws:
     * IndexOutOfBoundsException - if index is
     *              negative or greater than or equal to length().See Also:length()
     */
    public char charAt(int index);

    /**
     * Characters are copied from this string buffer into the
     *  destination character array dst. The first character to
     *  be copied is at index srcBegin; the last character to
     *  be copied is at index srcEnd-1. The total number of
     *  characters to be copied is srcEnd-srcBegin. The
     *  characters are copied into the subarray of dst starting
     *  at index dstBegin and ending at index:
     * 
     *  dstbegin + (srcEnd-srcBegin) - 1
     * 
     * Parameters:srcBegin - start copying at this offset in the string buffer.srcEnd - stop copying at this offset in the string buffer.dst - the array to copy the data into.dstBegin - offset into dst.
     * Throws:
     * NullPointerException - if dst is
     *              null.
     * IndexOutOfBoundsException - if any of the following is true:
     * 
     *              srcBegin is negative
     *              dstBegin is negative
     *              the srcBegin argument is greater than
     *              the srcEnd argument.
     *              srcEnd is greater than
     *              this.length(), the current length of this
     *              string buffer.
     *              dstBegin+srcEnd-srcBegin is greater than
     *              dst.length
     */
    public void getChars(int srcBegin, int srcEnd, char[] dst, int dstBegin);

    /**
     * The character at the specified index of this string buffer is set
     *  to ch. The string buffer is altered to represent a new
     *  character sequence that is identical to the old character sequence,
     *  except that it contains the character ch at position
     *  index.
     * 
     *  The offset argument must be greater than or equal to
     *  0, and less than the length of this string buffer.
     * 
     * Parameters:index - the index of the character to modify.ch - the new character.
     * Throws:
     * IndexOutOfBoundsException - if index is
     *              negative or greater than or equal to length().See Also:length()
     */
    public void setCharAt(int index, char ch);

    /**
     * Appends the string representation of the Object
     *  argument to this string buffer.
     * 
     *  The argument is converted to a string as if by the method
     *  String.valueOf, and the characters of that
     *  string are then appended to this string buffer.
     * 
     * Parameters:obj - an Object.
     * Returns:a reference to this StringBuffer object.See Also:String.valueOf(java.lang.Object),
     * append(java.lang.String)
     */
    public StringBuffer append(Object obj);

    /**
     * Appends the string to this string buffer.
     * 
     *  The characters of the String argument are appended, in
     *  order, to the contents of this string buffer, increasing the
     *  length of this string buffer by the length of the argument.
     *  If str is null, then the four characters
     *  "null" are appended to this string buffer.
     * 
     *  Let n be the length of the old character sequence, the one
     *  contained in the string buffer just prior to execution of the
     *  append method. Then the character at index k in
     *  the new character sequence is equal to the character at index k
     *  in the old character sequence, if k is less than n;
     *  otherwise, it is equal to the character at index k-n in the
     *  argument str.
     * 
     * Parameters:str - a string.
     * Returns:a reference to this StringBuffer.
     */
    public StringBuffer append(String str);

    /**
     * Appends the string representation of the char array
     *  argument to this string buffer.
     * 
     *  The characters of the array argument are appended, in order, to
     *  the contents of this string buffer. The length of this string
     *  buffer increases by the length of the argument.
     * 
     *  The overall effect is exactly as if the argument were converted to
     *  a string by the method String.valueOf(char[]) and the
     *  characters of that string were then appended
     *  to this StringBuffer object.
     * 
     * Parameters:str - the characters to be appended.
     * Returns:a reference to this StringBuffer object.
     */
    public StringBuffer append(char[] str);

    /**
     * Appends the string representation of a subarray of the
     *  char array argument to this string buffer.
     * 
     *  Characters of the character array str, starting at
     *  index offset, are appended, in order, to the contents
     *  of this string buffer. The length of this string buffer increases
     *  by the value of len.
     * 
     *  The overall effect is exactly as if the arguments were converted to
     *  a string by the method String.valueOf(char[],int,int) and the
     *  characters of that string were then appended
     *  to this StringBuffer object.
     * 
     * Parameters:str - the characters to be appended.offset - the index of the first character to append.len - the number of characters to append.
     * Returns:a reference to this StringBuffer object.
     */
    public StringBuffer append(char[] str, int offset, int len);

    /**
     * Appends the string representation of the boolean
     *  argument to the string buffer.
     * 
     *  The argument is converted to a string as if by the method
     *  String.valueOf, and the characters of that
     *  string are then appended to this string buffer.
     * 
     * Parameters:b - a boolean.
     * Returns:a reference to this StringBuffer.See Also:String.valueOf(boolean),
     * append(java.lang.String)
     */
    public StringBuffer append(boolean b);

    /**
     * Appends the string representation of the char
     *  argument to this string buffer.
     * 
     *  The argument is appended to the contents of this string buffer.
     *  The length of this string buffer increases by 1.
     * 
     *  The overall effect is exactly as if the argument were converted to
     *  a string by the method String.valueOf(char) and the character
     *  in that string were then appended to this
     *  StringBuffer object.
     * 
     * Parameters:c - a char.
     * Returns:a reference to this StringBuffer object.
     */
    public StringBuffer append(char c);

    /**
     * Appends the string representation of the int
     *  argument to this string buffer.
     * 
     *  The argument is converted to a string as if by the method
     *  String.valueOf, and the characters of that
     *  string are then appended to this string buffer.
     * 
     * Parameters:i - an int.
     * Returns:a reference to this StringBuffer object.See Also:String.valueOf(int),
     * append(java.lang.String)
     */
    public StringBuffer append(int i);

    /**
     * Appends the string representation of the long
     *  argument to this string buffer.
     * 
     *  The argument is converted to a string as if by the method
     *  String.valueOf, and the characters of that
     *  string are then appended to this string buffer.
     * 
     * Parameters:l - a long.
     * Returns:a reference to this StringBuffer object.See Also:String.valueOf(long),
     * append(java.lang.String)
     */
    public StringBuffer append(long l);

    /**
     * Appends the string representation of the float
     *  argument to this string buffer.
     * 
     *  The argument is converted to a string as if by the method
     *  String.valueOf, and the characters of that
     *  string are then appended to this string buffer.
     * 
     * Parameters:f - a float.
     * Returns:a reference to this StringBuffer object.Since:
     *   CLDC 1.1
     * See Also:String.valueOf(float),
     * append(java.lang.String)
     */
    public StringBuffer append(float f);

    /**
     * Appends the string representation of the double
     *  argument to this string buffer.
     * 
     *  The argument is converted to a string as if by the method
     *  String.valueOf, and the characters of that
     *  string are then appended to this string buffer.
     * 
     * Parameters:d - a double.
     * Returns:a reference to this StringBuffer object.Since:
     *   CLDC 1.1
     * See Also:String.valueOf(double),
     * append(java.lang.String)
     */
    public StringBuffer append(double d);

    /**
     * Removes the characters in a substring of this StringBuffer.
     *  The substring begins at the specified start and extends to
     *  the character at index end - 1 or to the end of the
     *  StringBuffer if no such character exists. If
     *  start is equal to end, no changes are made.
     * 
     * Parameters:start - The beginning index, inclusive.end - The ending index, exclusive.
     * Returns:This string buffer.
     * Throws:
     * StringIndexOutOfBoundsException - if start
     *              is negative, greater than length(), or
     *              greater than end.Since:
     *   JDK1.2
     */
    public StringBuffer delete(int start, int end);

    /**
     * Removes the character at the specified position in this
     *  StringBuffer (shortening the StringBuffer
     *  by one character).
     * 
     * Parameters:index - Index of character to remove
     * Returns:This string buffer.
     * Throws:
     * StringIndexOutOfBoundsException - if the index
     *               is negative or greater than or equal to
     *               length().Since:
     *   JDK1.2
     */
    public StringBuffer deleteCharAt(int index);

    /**
     * Inserts the string representation of the Object
     *  argument into this string buffer.
     * 
     *  The second argument is converted to a string as if by the method
     *  String.valueOf, and the characters of that
     *  string are then inserted into this string buffer at the indicated
     *  offset.
     * 
     *  The offset argument must be greater than or equal to
     *  0, and less than or equal to the length of this
     *  string buffer.
     * 
     * Parameters:offset - the offset.obj - an Object.
     * Returns:a reference to this StringBuffer object.
     * Throws:
     * StringIndexOutOfBoundsException - if the offset is invalid.See Also:String.valueOf(java.lang.Object),
     * insert(int, java.lang.String),
     * length()
     */
    public StringBuffer insert(int offset, Object obj);

    /**
     * Inserts the string into this string buffer.
     * 
     *  The characters of the String argument are inserted, in
     *  order, into this string buffer at the indicated offset, moving up any
     *  characters originally above that position and increasing the length
     *  of this string buffer by the length of the argument. If
     *  str is null, then the four characters
     *  "null" are inserted into this string buffer.
     * 
     *  The character at index k in the new character sequence is
     *  equal to:
     * 
     *  the character at index k in the old character sequence, if
     *  k is less than offset
     *  the character at index k-offset in the
     *  argument str, if k is not less than
     *  offset but is less than offset+str.length()
     *  the character at index k-str.length() in the
     *  old character sequence, if k is not less than
     *  offset+str.length()
     * 
     *  The offset argument must be greater than or equal to
     *  0, and less than or equal to the length of this
     *  string buffer.
     * 
     * Parameters:offset - the offset.str - a string.
     * Returns:a reference to this StringBuffer object.
     * Throws:
     * StringIndexOutOfBoundsException - if the offset is invalid.See Also:length()
     */
    public StringBuffer insert(int offset, String str);

    /**
     * Inserts the string representation of the char array
     *  argument into this string buffer.
     * 
     *  The characters of the array argument are inserted into the
     *  contents of this string buffer at the position indicated by
     *  offset. The length of this string buffer increases by
     *  the length of the argument.
     * 
     *  The overall effect is exactly as if the argument were converted to
     *  a string by the method String.valueOf(char[]) and the
     *  characters of that string were then
     *  inserted into this
     *  StringBuffer  object at the position indicated by
     *  offset.
     * 
     * Parameters:offset - the offset.str - a character array.
     * Returns:a reference to this StringBuffer object.
     * Throws:
     * StringIndexOutOfBoundsException - if the offset is invalid.
     */
    public StringBuffer insert(int offset, char[] str);

    /**
     * Inserts the string representation of the boolean
     *  argument into this string buffer.
     * 
     *  The second argument is converted to a string as if by the method
     *  String.valueOf, and the characters of that
     *  string are then inserted into this string buffer at the indicated
     *  offset.
     * 
     *  The offset argument must be greater than or equal to
     *  0, and less than or equal to the length of this
     *  string buffer.
     * 
     * Parameters:offset - the offset.b - a boolean.
     * Returns:a reference to this StringBuffer object.
     * Throws:
     * StringIndexOutOfBoundsException - if the offset is invalid.See Also:String.valueOf(boolean),
     * insert(int, java.lang.String),
     * length()
     */
    public StringBuffer insert(int offset, boolean b);

    /**
     * Inserts the string representation of the char
     *  argument into this string buffer.
     * 
     *  The second argument is inserted into the contents of this string
     *  buffer at the position indicated by offset. The length
     *  of this string buffer increases by one.
     * 
     *  The overall effect is exactly as if the argument were converted to
     *  a string by the method String.valueOf(char) and the character
     *  in that string were then inserted into
     *  this StringBuffer object at the position indicated by
     *  offset.
     * 
     *  The offset argument must be greater than or equal to
     *  0, and less than or equal to the length of this
     *  string buffer.
     * 
     * Parameters:offset - the offset.c - a char.
     * Returns:a reference to this StringBuffer object.
     * Throws:
     * IndexOutOfBoundsException - if the offset is invalid.See Also:length()
     */
    public StringBuffer insert(int offset, char c);

    /**
     * Inserts the string representation of the second int
     *  argument into this string buffer.
     * 
     *  The second argument is converted to a string as if by the method
     *  String.valueOf, and the characters of that
     *  string are then inserted into this string buffer at the indicated
     *  offset.
     * 
     *  The offset argument must be greater than or equal to
     *  0, and less than or equal to the length of this
     *  string buffer.
     * 
     * Parameters:offset - the offset.i - an int.
     * Returns:a reference to this StringBuffer object.
     * Throws:
     * StringIndexOutOfBoundsException - if the offset is invalid.See Also:String.valueOf(int),
     * insert(int, java.lang.String),
     * length()
     */
    public StringBuffer insert(int offset, int i);

    /**
     * Inserts the string representation of the long
     *  argument into this string buffer.
     * 
     *  The second argument is converted to a string as if by the method
     *  String.valueOf, and the characters of that
     *  string are then inserted into this string buffer at the position
     *  indicated by offset.
     * 
     *  The offset argument must be greater than or equal to
     *  0, and less than or equal to the length of this
     *  string buffer.
     * 
     * Parameters:offset - the offset.l - a long.
     * Returns:a reference to this StringBuffer object.
     * Throws:
     * StringIndexOutOfBoundsException - if the offset is invalid.See Also:String.valueOf(long),
     * insert(int, java.lang.String),
     * length()
     */
    public StringBuffer insert(int offset, long l);

    /**
     * Inserts the string representation of the float
     *  argument into this string buffer.
     * 
     *  The second argument is converted to a string as if by the method
     *  String.valueOf, and the characters of that
     *  string are then inserted into this string buffer at the indicated
     *  offset.
     * 
     *  The offset argument must be greater than or equal to
     *  0, and less than or equal to the length of this
     *  string buffer.
     * 
     * Parameters:offset - the offset.f - a float.
     * Returns:a reference to this StringBuffer object.
     * Throws:
     * StringIndexOutOfBoundsException - if the offset is invalid.Since:
     *   CLDC 1.1
     * See Also:String.valueOf(float),
     * insert(int, java.lang.String),
     * length()
     */
    public StringBuffer insert(int offset, float f);

    /**
     * Inserts the string representation of the double
     *  argument into this string buffer.
     * 
     *  The second argument is converted to a string as if by the method
     *  String.valueOf, and the characters of that
     *  string are then inserted into this string buffer at the indicated
     *  offset.
     * 
     *  The offset argument must be greater than or equal to
     *  0, and less than or equal to the length of this
     *  string buffer.
     * 
     * Parameters:offset - the offset.d - a double.
     * Returns:a reference to this StringBuffer object.
     * Throws:
     * StringIndexOutOfBoundsException - if the offset is invalid.Since:
     *   CLDC 1.1
     * See Also:String.valueOf(double),
     * insert(int, java.lang.String),
     * length()
     */
    public StringBuffer insert(int offset, double d);

    /**
     * The character sequence contained in this string buffer is
     *  replaced by the reverse of the sequence.
     * 
     *  Let n be the length of the old character sequence, the one
     *  contained in the string buffer just prior to execution of the
     *  reverse method. Then the character at index k in
     *  the new character sequence is equal to the character at index
     *  n-k-1 in the old character sequence.
     * 
     * Returns:a reference to this StringBuffer object..Since:
     *   JDK1.0.2
     */
    public StringBuffer reverse();

    /**
     * Converts to a string representing the data in this string buffer.
     *  A new String object is allocated and initialized to
     *  contain the character sequence currently represented by this
     *  string buffer. This String is then returned. Subsequent
     *  changes to the string buffer do not affect the contents of the
     *  String.
     * 
     *  Implementation advice: This method can be coded so as to create a new
     *  String object without allocating new memory to hold a
     *  copy of the character sequence. Instead, the string can share the
     *  memory used by the string buffer. Any subsequent operation that alters
     *  the content or capacity of the string buffer must then make a copy of
     *  the internal buffer at that time. This strategy is effective for
     *  reducing the amount of memory allocated by a string concatenation
     *  operation when it is implemented using a string buffer.
     * 
     * Overrides:toString in class Object
     * 
     * Returns:a string representation of the string buffer.
     */
    public String toString();

}

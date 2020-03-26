package java.io;

public class PrintStream extends OutputStream {
    /**
     * Flush the stream.  This is done by writing any buffered output bytes to
     *  the underlying output stream and then flushing that stream.
     * 
     * Overrides:flush in class OutputStream
     * 
     * See Also:OutputStream.flush()
     */
    public void flush();

    /**
     * Close the stream.  This is done by flushing the stream and then closing
     *  the underlying output stream.
     * 
     * Overrides:close in class OutputStream
     * 
     * See Also:OutputStream.close()
     */
    public void close();

    /**
     * Flush the stream and check its error state.  The internal error state
     *  is set to true when the underlying output stream throws an
     *  IOException,
     *  and when the setError method is invoked.
     * 
     * Returns:True if and only if this stream has encountered an
     *          IOException, or the
     *          setError method has been invoked
     */
    public boolean checkError();

    /**
     * Set the error state of the stream to true.
     * 
     * Since:
     *   JDK1.1
     */
    protected void setError();

    /**
     * Write the specified byte to this stream.
     * 
     *   Note that the byte is written as given; to write a character that
     *  will be translated according to the platform's default character
     *  encoding, use the print(char) or println(char)
     *  methods.
     * 
     * Specified by:write in class OutputStream
     * 
     * Parameters:b - The byte to be writtenSee Also:print(char),
     * println(char)
     */
    public void write(int b);

    /**
     * Write len bytes from the specified byte array starting at
     *  offset off to this stream.
     * 
     *   Note that the bytes will be written as given; to write characters
     *  that will be translated according to the platform's default character
     *  encoding, use the print(char) or println(char)
     *  methods.
     * 
     * Overrides:write in class OutputStream
     * 
     * Parameters:buf - A byte arrayoff - Offset from which to start taking byteslen - Number of bytes to write
     */
    public void write(byte[] buf,
                  int off,
                  int len);

    /**
     * Print a boolean value.  The string produced by String.valueOf(boolean) is translated into bytes
     *  according to the platform's default character encoding, and these bytes
     *  are written in exactly the manner of the
     *  write(int) method.
     * 
     * Parameters:b - The boolean to be printed
     */
    public void print(boolean b);

    /**
     * Print a character.  The character is translated into one or more bytes
     *  according to the platform's default character encoding, and these bytes
     *  are written in exactly the manner of the
     *  write(int) method.
     * 
     * Parameters:c - The char to be printed
     */
    public void print(char c);

    /**
     * Print an integer.  The string produced by String.valueOf(int) is translated into bytes
     *  according to the platform's default character encoding, and these bytes
     *  are written in exactly the manner of the
     *  write(int) method.
     * 
     * Parameters:i - The int to be printedSee Also:Integer.toString(int)
     */
    public void print(int i);

    /**
     * Print a long integer.  The string produced by String.valueOf(long) is translated into bytes
     *  according to the platform's default character encoding, and these bytes
     *  are written in exactly the manner of the
     *  write(int) method.
     * 
     * Parameters:l - The long to be printedSee Also:Long.toString(long)
     */
    public void print(long l);

    /**
     * Print a floating point number.  The string produced by
     *  String.valueOf(float) is translated
     *  into bytes according to the platform's default character encoding,
     *  and these bytes are written in exactly the manner of the
     *  write(int) method.
     * 
     * Parameters:f - The float to be printedSince:
     *   CLDC 1.1
     * See Also:Float.toString(float)
     */
    public void print(float f);

    /**
     * Print a double-precision floating point number.  The string produced by
     *  String.valueOf(double) is translated
     *  into bytes according to the platform's default character encoding,
     *  and these bytes are written in exactly the manner of the
     *  write(int) method.
     * 
     * Parameters:d - The double to be printedSince:
     *   CLDC 1.1
     * See Also:Double.toString(double)
     */
    public void print(double d);

    /**
     * Print an array of characters.  The characters are converted into bytes
     *  according to the platform's default character encoding, and these bytes
     *  are written in exactly the manner of the
     *  write(int) method.
     * 
     * Parameters:s - The array of chars to be printed
     * Throws:
     * NullPointerException - If s is null
     */
    public void print(char[] s);

    /**
     * Print a string.  If the argument is null then the string
     *  "null" is printed.  Otherwise, the string's characters are
     *  converted into bytes according to the platform's default character
     *  encoding, and these bytes are written in exactly the manner of the
     *  write(int) method.
     * 
     * Parameters:s - The String to be printed
     */
    public void print(String s);

    /**
     * Print an object.  The string produced by the String.valueOf(Object) method is translated into bytes
     *  according to the platform's default character encoding, and these bytes
     *  are written in exactly the manner of the
     *  write(int) method.
     * 
     * Parameters:obj - The Object to be printedSee Also:Object.toString()
     */
    public void print(Object obj);

    /**
     * Terminate the current line by writing the line separator string.  The
     *  line separator string is defined by the system property
     *  line.separator, and is not necessarily a single newline
     *  character ('\n').
     */
    public void println();

    /**
     * Print a boolean and then terminate the line.  This method behaves as
     *  though it invokes print(boolean) and then
     *  println().
     * 
     * Parameters:x - The boolean to be printed
     */
    public void println(boolean x);

    /**
     * Print a character and then terminate the line.  This method behaves as
     *  though it invokes print(char) and then
     *  println().
     * 
     * Parameters:x - The char to be printed.
     */
    public void println(char x);

    /**
     * Print an integer and then terminate the line.  This method behaves as
     *  though it invokes print(int) and then
     *  println().
     * 
     * Parameters:x - The int to be printed.
     */
    public void println(int x);

    /**
     * Print a long and then terminate the line.  This method behaves as
     *  though it invokes print(long) and then
     *  println().
     * 
     * Parameters:x - The long to be printed.
     */
    public void println(long x);

    /**
     * Print a float and then terminate the line.  This method behaves as
     *  though it invokes print(float) and then
     *  println().
     * 
     * Parameters:x - The float to be printed.Since:
     *   CLDC 1.1
     */
    public void println(float x);

    /**
     * Print a double and then terminate the line.  This method behaves as
     *  though it invokes print(double) and then
     *  println().
     * 
     * Parameters:x - The double to be printed.Since:
     *   CLDC 1.1
     */
    public void println(double x);

    /**
     * Print an array of characters and then terminate the line.  This method
     *  behaves as though it invokes print(char[]) and
     *  then println().
     * 
     * Parameters:x - an array of chars to print.
     */
    public void println(char[] x);

    /**
     * Print a String and then terminate the line.  This method behaves as
     *  though it invokes print(String) and then
     *  println().
     * 
     * Parameters:x - The String to be printed.
     */
    public void println(String x);

}

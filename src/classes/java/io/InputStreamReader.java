package java.io;

public class InputStreamReader extends Reader {
    /**
     * Create an InputStreamReader that uses the default character encoding.
     * 
     * Parameters:is - An InputStream
     */
    public InputStreamReader(InputStream is) {
        construct(is);
    }

    private native void construct(InputStream is);

    /**
     * Read a single character.
     * 
     * Overrides:read in class Reader
     * 
     * Returns:The character read, or -1 if the end of the stream has
     *              been reached
     * Throws:
     * IOException - If an I/O error occurs
     */
    public int read()
         throws IOException;

    /**
     * Read characters into a portion of an array.
     * 
     * Specified by:read in class Reader
     * 
     * Parameters:cbuf - Destination bufferoff - Offset at which to start storing characterslen - Maximum number of characters to read
     * Returns:The number of characters read, or -1 if the end of
     *              the stream has been reached
     * Throws:
     * IOException - If an I/O error occurs
     */
    public int read(char[] cbuf,
                int off,
                int len)
         throws IOException;

    /**
     * Skip characters.
     * 
     * Overrides:skip in class Reader
     * 
     * Parameters:n - The number of characters to skip
     * Returns:The number of characters actually skipped
     * Throws:
     * IllegalArgumentException - If n is negative.
     * IOException - If an I/O error occurs
     */
    public long skip(long n)
          throws IOException;

    /**
     * Tell whether this stream is ready to be read.
     * 
     * Overrides:ready in class Reader
     * 
     * Returns:True if the next read() is guaranteed not to block for input,
     *  false otherwise.  Note that returning false does not guarantee that the
     *  next read will block.
     * Throws:
     * IOException - If an I/O error occurs
     */
    public boolean ready()
              throws IOException;

    /**
     * Tell whether this stream supports the mark() operation.
     * 
     * Overrides:markSupported in class Reader
     * 
     * Returns:true if and only if this stream supports the mark operation.
     */
    public boolean markSupported();

    /**
     * Mark the present position in the stream.
     * 
     * Overrides:mark in class Reader
     * 
     * Parameters:readAheadLimit - Limit on the number of characters that may be
     *                          read while still preserving the mark.  After
     *                          reading this many characters, attempting to
     *                          reset the stream may fail.
     * Throws:
     * IOException - If the stream does not support mark(),
     *                           or if some other I/O error occurs
     */
    public void mark(int readAheadLimit)
          throws IOException;

    /**
     * Reset the stream.
     * 
     * Overrides:reset in class Reader
     * 
     * Throws:
     * IOException - If an I/O error occurs
     */
    public void reset()
           throws IOException;

}

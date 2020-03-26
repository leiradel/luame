package java.io;

public class ByteArrayInputStream extends InputStream {
    /**
     * An array of bytes that was provided
     *  by the creator of the stream. Elements buf[0]
     *  through buf[count-1] are the
     *  only bytes that can ever be read from the
     *  stream;  element buf[pos] is
     *  the next byte to be read.
     */
    protected byte[] buf = ;

    /**
     * The index of the next character to read from the input stream buffer.
     *  This value should always be nonnegative
     *  and not larger than the value of count.
     *  The next byte to be read from the input stream buffer
     *  will be buf[pos].
     */
    protected int pos = ;

    /**
     * The currently marked position in the stream.
     *  ByteArrayInputStream objects are marked at position zero by
     *  default when constructed.  They may be marked at another
     *  position within the buffer by the mark() method.
     *  The current buffer position is set to this point by the
     *  reset() method.
     * 
     * Since:
     *   JDK1.1
     */
    protected int mark = ;

    /**
     * The index one greater than the last valid character in the input
     *  stream buffer.
     *  This value should always be nonnegative
     *  and not larger than the length of buf.
     *  It  is one greater than the position of
     *  the last byte within buf that
     *  can ever be read  from the input stream buffer.
     */
    protected int count = ;

    /**
     * Creates a ByteArrayInputStream
     *  so that it  uses buf as its
     *  buffer array.
     *  The buffer array is not copied.
     *  The initial value of pos
     *  is 0 and the initial value
     *  of  count is the length of
     *  buf.
     * 
     * Parameters:buf - the input buffer.
     */
    public ByteArrayInputStream(byte[] buf) {
        construct(buf);
    }

    private native void construct(byte[] buf);

    /**
     * Reads the next byte of data from this input stream. The value
     *  byte is returned as an int in the range
     *  0 to 255. If no byte is available
     *  because the end of the stream has been reached, the value
     *  -1 is returned.
     * 
     *  This read method
     *  cannot block.
     * 
     * Specified by:read in class InputStream
     * 
     * Returns:the next byte of data, or -1 if the end of the
     *           stream has been reached.
     */
    public int read();

    /**
     * Reads up to len bytes of data into an array of bytes
     *  from this input stream.
     *  If pos equals count,
     *  then -1 is returned to indicate
     *  end of file. Otherwise, the  number k
     *  of bytes read is equal to the smaller of
     *  len and count-pos.
     *  If k is positive, then bytes
     *  buf[pos] through buf[pos+k-1]
     *  are copied into b[off]  through
     *  b[off+k-1] in the manner performed
     *  by System.arraycopy. The
     *  value k is added into pos
     *  and k is returned.
     * 
     *  This read method cannot block.
     * 
     * Overrides:read in class InputStream
     * 
     * Parameters:b - the buffer into which the data is read.off - the start offset of the data.len - the maximum number of bytes read.
     * Returns:the total number of bytes read into the buffer, or
     *           -1 if there is no more data because the end of
     *           the stream has been reached.See Also:InputStream.read()
     */
    public int read(byte[] b,
                int off,
                int len);

    /**
     * Skips n bytes of input from this input stream. Fewer
     *  bytes might be skipped if the end of the input stream is reached.
     *  The actual number k
     *  of bytes to be skipped is equal to the smaller
     *  of n and  count-pos.
     *  The value k is added into pos
     *  and k is returned.
     * 
     * Overrides:skip in class InputStream
     * 
     * Parameters:n - the number of bytes to be skipped.
     * Returns:the actual number of bytes skipped.
     */
    public long skip(long n);

    /**
     * Returns the number of bytes that can be read from this input
     *  stream without blocking.
     *  The value returned is
     *  count&nbsp;- pos,
     *  which is the number of bytes remaining to be read from the input buffer.
     * 
     * Overrides:available in class InputStream
     * 
     * Returns:the number of bytes that can be read from the input stream
     *           without blocking.
     */
    public int available();

    /**
     * Tests if ByteArrayInputStream supports mark/reset.
     * 
     * Overrides:markSupported in class InputStream
     * 
     * Returns:true if this true type supports the mark and reset
     *           method; false otherwise.Since:
     *   JDK1.1
     * See Also:InputStream.mark(int),
     * InputStream.reset()
     */
    public boolean markSupported();

    /**
     * Set the current marked position in the stream.
     *  ByteArrayInputStream objects are marked at position zero by
     *  default when constructed.  They may be marked at another
     *  position within the buffer by this method.
     * 
     * Overrides:mark in class InputStream
     * 
     * Parameters:readAheadLimit - the maximum limit of bytes that can be read before
     *                       the mark position becomes invalid.Since:
     *   JDK1.1
     * See Also:InputStream.reset()
     */
    public void mark(int readAheadLimit);

    /**
     * Resets the buffer to the marked position.  The marked position
     *  is the beginning unless another position was marked.
     *  The value of pos is set to 0.
     * 
     * Overrides:reset in class InputStream
     * 
     * See Also:InputStream.mark(int),
     * IOException
     */
    public void reset();

}

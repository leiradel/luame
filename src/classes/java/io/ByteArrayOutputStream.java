package java.io;

public class ByteArrayOutputStream extends OutputStream {
    /**
     * The buffer where data is stored.
     */
    protected byte[] buf = ;

    /**
     * The number of valid bytes in the buffer.
     */
    protected int count = ;

    /**
     * Creates a new byte array output stream. The buffer capacity is
     *  initially 32 bytes, though its size increases if necessary.
     */
    public ByteArrayOutputStream() {
        construct();
    }

    private native void construct();

    /**
     * Writes the specified byte to this byte array output stream.
     * 
     * Specified by:write in class OutputStream
     * 
     * Parameters:b - the byte to be written.
     */
    public void write(int b);

    /**
     * Writes len bytes from the specified byte array
     *  starting at offset off to this byte array output stream.
     * 
     * Overrides:write in class OutputStream
     * 
     * Parameters:b - the data.off - the start offset in the data.len - the number of bytes to write.
     */
    public void write(byte[] b,
                  int off,
                  int len);

    /**
     * Resets the count field of this byte array output
     *  stream to zero, so that all currently accumulated output in the
     *  output stream is discarded. The output stream can be used again,
     *  reusing the already allocated buffer space.
     * 
     * See Also:ByteArrayInputStream.count
     */
    public void reset();

    /**
     * Creates a newly allocated byte array. Its size is the current
     *  size of this output stream and the valid contents of the buffer
     *  have been copied into it.
     * 
     * Returns:the current contents of this output stream, as a byte array.See Also:size()
     */
    public byte[] toByteArray();

    /**
     * Returns the current size of the buffer.
     * 
     * Returns:the value of the count field, which is the number
     *           of valid bytes in this output stream.See Also:count
     */
    public int size();

    /**
     * Converts the buffer's contents into a string, translating bytes into
     *  characters according to the platform's default character encoding.
     * 
     * Overrides:toString in class Object
     * 
     * Returns:String translated from the buffer's contents.Since:
     *   JDK1.1
     */
    public String toString();

}

package java.io;

public class Reader {
    /**
     * The object used to synchronize operations on this stream.  For
     *  efficiency, a character-stream object may use an object other than
     *  itself to protect critical sections.  A subclass should therefore use
     *  the object in this field rather than this or a synchronized
     *  method.
     */
    protected Object lock = ;

    /**
     * Create a new character-stream reader whose critical sections will
     *  synchronize on the reader itself.
     */
    protected Reader() {
        construct();
    }

    private native void construct();

    /**
     * Create a new character-stream reader whose critical sections will
     *  synchronize on the given object.
     * 
     * Parameters:lock - The Object to synchronize on.
     */
    protected Reader(Object lock) {
        construct(lock);
    }

    private native void construct(Object lock);

    /**
     * Read a single character.  This method will block until a character is
     *  available, an I/O error occurs, or the end of the stream is reached.
     * 
     *   Subclasses that intend to support efficient single-character input
     *  should override this method.
     * 
     * Returns:The character read, as an integer in the range 0 to 65535
     *              (0x00-0xffff), or -1 if the end of the stream has
     *              been reached
     * Throws:
     * IOException - If an I/O error occurs
     */
    public int read() throws IOException;

    /**
     * Read characters into an array.  This method will block until some input
     *  is available, an I/O error occurs, or the end of the stream is reached.
     * 
     * Parameters:cbuf - Destination buffer
     * Returns:The number of bytes read, or -1 if the end of the stream
     *               has been reached
     * Throws:
     * IOException - If an I/O error occurs
     */
    public int read(char[] cbuf) throws IOException;

    /**
     * Read characters into a portion of an array.  This method will block
     *  until some input is available, an I/O error occurs, or the end of the
     *  stream is reached.
     * 
     * Parameters:cbuf - Destination bufferoff - Offset at which to start storing characterslen - Maximum number of characters to read
     * Returns:The number of characters read, or -1 if the end of the
     *              stream has been reached
     * Throws:
     * IOException - If an I/O error occurs
     */
    public abstract int read(char[] cbuf, int off, int len) throws IOException;

    /**
     * Skip characters.  This method will block until some characters are
     *  available, an I/O error occurs, or the end of the stream is reached.
     * 
     * Parameters:n - The number of characters to skip
     * Returns:The number of characters actually skipped
     * Throws:
     * IllegalArgumentException - If n is negative.
     * IOException - If an I/O error occurs
     */
    public long skip(long n) throws IOException;

    /**
     * Tell whether this stream is ready to be read.
     * 
     * Returns:True if the next read() is guaranteed not to block for input,
     *  false otherwise.  Note that returning false does not guarantee that the
     *  next read will block.
     * Throws:
     * IOException - If an I/O error occurs
     */
    public boolean ready() throws IOException;

    /**
     * Tell whether this stream supports the mark() operation. The default
     *  implementation always returns false. Subclasses should override this
     *  method.
     * 
     * Returns:true if and only if this stream supports the mark operation.
     */
    public boolean markSupported();

    /**
     * Mark the present position in the stream.  Subsequent calls to reset()
     *  will attempt to reposition the stream to this point.  Not all
     *  character-input streams support the mark() operation.
     * 
     * Parameters:readAheadLimit - Limit on the number of characters that may be
     *                          read while still preserving the mark.  After
     *                          reading this many characters, attempting to
     *                          reset the stream may fail.
     * Throws:
     * IOException - If the stream does not support mark(),
     *                           or if some other I/O error occurs
     */
    public void mark(int readAheadLimit) throws IOException;

    /**
     * Reset the stream.  If the stream has been marked, then attempt to
     *  reposition it at the mark.  If the stream has not been marked, then
     *  attempt to reset it in some way appropriate to the particular stream,
     *  for example by repositioning it to its starting point.  Not all
     *  character-input streams support the reset() operation, and some support
     *  reset() without supporting mark().
     * 
     * Throws:
     * IOException - If the stream has not been marked,
     *                           or if the mark has been invalidated,
     *                           or if the stream does not support reset(),
     *                           or if some other I/O error occurs
     */
    public void reset() throws IOException;

    /**
     * Close the stream.  Once a stream has been closed, further read(),
     *  ready(), mark(), or reset() invocations will throw an IOException.
     *  Closing a previously-closed stream, however, has no effect.
     * 
     * Throws:
     * IOException - If an I/O error occurs
     */
    public abstract void close() throws IOException;

}

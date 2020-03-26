package java.io;

public class Writer {
    /**
     * The object used to synchronize operations on this stream.  For
     *  efficiency, a character-stream object may use an object other than
     *  itself to protect critical sections.  A subclass should therefore use
     *  the object in this field rather than this or a synchronized
     *  method.
     */
    protected Object lock = ;

    /**
     * Create a new character-stream writer whose critical sections will
     *  synchronize on the writer itself.
     */
    protected Writer() {
        construct();
    }

    private native void construct();

    /**
     * Write a single character.  The character to be written is contained in
     *  the 16 low-order bits of the given integer value; the 16 high-order bits
     *  are ignored.
     * 
     *   Subclasses that intend to support efficient single-character output
     *  should override this method.
     * 
     * Parameters:c - int specifying a character to be written.
     * Throws:
     * IOException - If an I/O error occurs
     */
    public void write(int c)
           throws IOException;

    /**
     * Write an array of characters.
     * 
     * Parameters:cbuf - Array of characters to be written
     * Throws:
     * IOException - If an I/O error occurs
     */
    public void write(char[] cbuf)
           throws IOException;

    /**
     * Write a portion of an array of characters.
     * 
     * Parameters:cbuf - Array of charactersoff - Offset from which to start writing characterslen - Number of characters to write
     * Throws:
     * IOException - If an I/O error occurs
     */
    public abstract void write(char[] cbuf,
                           int off,
                           int len)
                    throws IOException;

    /**
     * Write a string.
     * 
     * Parameters:str - String to be written
     * Throws:
     * IOException - If an I/O error occurs
     */
    public void write(String str)
           throws IOException;

    /**
     * Write a portion of a string.
     * 
     * Parameters:str - A Stringoff - Offset from which to start writing characterslen - Number of characters to write
     * Throws:
     * IOException - If an I/O error occurs
     */
    public void write(String str,
                  int off,
                  int len)
           throws IOException;

    /**
     * Flush the stream.  If the stream has saved any characters from the
     *  various write() methods in a buffer, write them immediately to their
     *  intended destination.  Then, if that destination is another character or
     *  byte stream, flush it.  Thus one flush() invocation will flush all the
     *  buffers in a chain of Writers and OutputStreams.
     * 
     * Throws:
     * IOException - If an I/O error occurs
     */
    public abstract void flush()
                    throws IOException;

}

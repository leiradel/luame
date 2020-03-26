package java.io;

public class OutputStream {
    /**
     * Writes the specified byte to this output stream. The general
     *  contract for write is that one byte is written
     *  to the output stream. The byte to be written is the eight
     *  low-order bits of the argument b. The 24
     *  high-order bits of b are ignored.
     * 
     *  Subclasses of OutputStream must provide an
     *  implementation for this method.
     * 
     * Parameters:b - the byte.
     * Throws:
     * IOException - if an I/O error occurs. In particular,
     *              an IOException may be thrown if the
     *              output stream has been closed.
     */
    public abstract void write(int b)
                    throws IOException;

    /**
     * Writes b.length bytes from the specified byte array
     *  to this output stream. The general contract for write(b)
     *  is that it should have exactly the same effect as the call
     *  write(b, 0, b.length).
     * 
     * Parameters:b - the data.
     * Throws:
     * IOException - if an I/O error occurs.See Also:write(byte[], int, int)
     */
    public void write(byte[] b)
           throws IOException;

    /**
     * Writes len bytes from the specified byte array
     *  starting at offset off to this output stream.
     *  The general contract for write(b, off, len) is that
     *  some of the bytes in the array b are written to the
     *  output stream in order; element b[off] is the first
     *  byte written and b[off+len-1] is the last byte written
     *  by this operation.
     * 
     *  The write method of OutputStream calls
     *  the write method of one argument on each of the bytes to be
     *  written out. Subclasses are encouraged to override this method and
     *  provide a more efficient implementation.
     * 
     *  If b is null, a
     *  NullPointerException is thrown.
     * 
     *  If off is negative, or len is negative, or
     *  off+len is greater than the length of the array
     *  b, then an IndexOutOfBoundsException is thrown.
     * 
     * Parameters:b - the data.off - the start offset in the data.len - the number of bytes to write.
     * Throws:
     * IOException - if an I/O error occurs. In particular,
     *              an IOException is thrown if the output
     *              stream is closed.
     */
    public void write(byte[] b,
                  int off,
                  int len)
           throws IOException;

    /**
     * Flushes this output stream and forces any buffered output bytes
     *  to be written out. The general contract of flush is
     *  that calling it is an indication that, if any bytes previously
     *  written have been buffered by the implementation of the output
     *  stream, such bytes should immediately be written to their
     *  intended destination.
     * 
     *  The flush method of OutputStream does nothing.
     * 
     * Throws:
     * IOException - if an I/O error occurs.
     */
    public void flush()
           throws IOException;

}

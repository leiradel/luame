package java.io;

public class DataInputStream {
    /**
     * The input stream.
     */
    protected InputStream in = ;

    /**
     * Creates a DataInputStream
     *  and saves its  argument, the input stream
     *  in, for later use.
     * 
     * Parameters:in - the input stream.
     */
    public DataInputStream(InputStream in) {
        construct(in);
    }

    private native void construct(InputStream in);

    /**
     * Reads the next byte of data from this input stream. The value
     *  byte is returned as an int in the range
     *  0 to 255. If no byte is available
     *  because the end of the stream has been reached, the value
     *  -1 is returned. This method blocks until input data
     *  is available, the end of the stream is detected, or an exception
     *  is thrown.
     * 
     *  This method
     *  simply performs in.read() and returns the result.
     * 
     * Specified by:read in class InputStream
     * 
     * Returns:the next byte of data, or -1 if the end of the
     *              stream is reached.
     * Throws:
     * IOException - if an I/O error occurs.
     */
    public int read() throws IOException;

    /**
     * See the general contract of the read
     *  method of DataInput.
     * 
     *  Bytes for this operation are read from the contained
     *  input stream.
     * 
     * Overrides:read in class InputStream
     * 
     * Parameters:b - the buffer into which the data is read.
     * Returns:the total number of bytes read into the buffer, or
     *              -1 if there is no more data because the end
     *              of the stream has been reached.
     * Throws:
     * IOException - if an I/O error occurs.See Also:InputStream.read(byte[], int, int)
     */
    public final int read(byte[] b) throws IOException;

    /**
     * Reads up to len bytes of data from this input stream
     *  into an array of bytes. This method blocks until some input is
     *  available.
     * 
     *  This method simply performs in.read(b, off, len)
     *  and returns the result.
     * 
     * Overrides:read in class InputStream
     * 
     * Parameters:b - the buffer into which the data is read.off - the start offset of the data.len - the maximum number of bytes read.
     * Returns:the total number of bytes read into the buffer, or
     *              -1 if there is no more data because the end of
     *              the stream has been reached.
     * Throws:
     * IOException - if an I/O error occurs.See Also:InputStream.read()
     */
    public final int read(byte[] b, int off, int len) throws IOException;

    /**
     * See the general contract of the readFully
     *  method of DataInput.
     * 
     *  Bytes for this operation are read from the contained
     *  input stream.
     * 
     * Specified by:readFully in interface DataInput
     * 
     * Parameters:b - the buffer into which the data is read.
     * Throws:
     * EOFException - if this input stream reaches the end before
     *                reading all the bytes.
     * IOException - if an I/O error occurs.
     */
    public final void readFully(byte[] b) throws IOException;

    /**
     * See the general contract of the readFully
     *  method of DataInput.
     * 
     *  Bytes for this operation are read from the contained
     *  input stream.
     * 
     * Specified by:readFully in interface DataInput
     * 
     * Parameters:b - the buffer into which the data is read.off - the start offset of the data.len - the number of bytes to read.
     * Throws:
     * EOFException - if this input stream reaches the end before
     *                reading all the bytes.
     * IOException - if an I/O error occurs.
     */
    public final void readFully(byte[] b, int off, int len) throws IOException;

    /**
     * See the general contract of the skipBytes
     *  method of DataInput.
     * 
     *  Bytes for this operation are read from the contained
     *  input stream.
     * 
     * Specified by:skipBytes in interface DataInput
     * 
     * Parameters:n - the number of bytes to be skipped.
     * Returns:the actual number of bytes skipped.
     * Throws:
     * IOException - if an I/O error occurs.
     */
    public final int skipBytes(int n) throws IOException;

    /**
     * See the general contract of the readBoolean
     *  method of DataInput.
     * 
     *  Bytes for this operation are read from the contained
     *  input stream.
     * 
     * Specified by:readBoolean in interface DataInput
     * 
     * Returns:the boolean value read.
     * Throws:
     * EOFException - if this input stream has reached the end.
     * IOException - if an I/O error occurs.
     */
    public final boolean readBoolean() throws IOException;

    /**
     * See the general contract of the readByte
     *  method of DataInput.
     * 
     *  Bytes for this operation are read from the contained
     *  input stream.
     * 
     * Specified by:readByte in interface DataInput
     * 
     * Returns:the next byte of this input stream as a signed 8-bit
     *              byte.
     * Throws:
     * EOFException - if this input stream has reached the end.
     * IOException - if an I/O error occurs.
     */
    public final byte readByte() throws IOException;

    /**
     * See the general contract of the readUnsignedByte
     *  method of DataInput.
     * 
     *  Bytes for this operation are read from the contained
     *  input stream.
     * 
     * Specified by:readUnsignedByte in interface DataInput
     * 
     * Returns:the next byte of this input stream, interpreted as an
     *              unsigned 8-bit number.
     * Throws:
     * EOFException - if this input stream has reached the end.
     * IOException - if an I/O error occurs.
     */
    public final int readUnsignedByte() throws IOException;

    /**
     * See the general contract of the readShort
     *  method of DataInput.
     * 
     *  Bytes for this operation are read from the contained
     *  input stream.
     * 
     * Specified by:readShort in interface DataInput
     * 
     * Returns:the next two bytes of this input stream, interpreted as a
     *              signed 16-bit number.
     * Throws:
     * EOFException - if this input stream reaches the end before
     *                reading two bytes.
     * IOException - if an I/O error occurs.
     */
    public final short readShort() throws IOException;

    /**
     * See the general contract of the readUnsignedShort
     *  method of DataInput.
     * 
     *  Bytes for this operation are read from the contained
     *  input stream.
     * 
     * Specified by:readUnsignedShort in interface DataInput
     * 
     * Returns:the next two bytes of this input stream, interpreted as an
     *              unsigned 16-bit integer.
     * Throws:
     * EOFException - if this input stream reaches the end before
     *                reading two bytes.
     * IOException - if an I/O error occurs.
     */
    public final int readUnsignedShort() throws IOException;

    /**
     * See the general contract of the readChar
     *  method of DataInput.
     * 
     *  Bytes for this operation are read from the contained
     *  input stream.
     * 
     * Specified by:readChar in interface DataInput
     * 
     * Returns:the next two bytes of this input stream as a Unicode
     *              character.
     * Throws:
     * EOFException - if this input stream reaches the end before
     *                reading two bytes.
     * IOException - if an I/O error occurs.
     */
    public final char readChar() throws IOException;

    /**
     * See the general contract of the readInt
     *  method of DataInput.
     * 
     *  Bytes for this operation are read from the contained
     *  input stream.
     * 
     * Specified by:readInt in interface DataInput
     * 
     * Returns:the next four bytes of this input stream, interpreted as an
     *              int.
     * Throws:
     * EOFException - if this input stream reaches the end before
     *                reading four bytes.
     * IOException - if an I/O error occurs.
     */
    public final int readInt() throws IOException;

    /**
     * See the general contract of the readLong
     *  method of DataInput.
     * 
     *  Bytes for this operation are read from the contained
     *  input stream.
     * 
     * Specified by:readLong in interface DataInput
     * 
     * Returns:the next eight bytes of this input stream, interpreted as a
     *              long.
     * Throws:
     * EOFException - if this input stream reaches the end before
     *                reading eight bytes.
     * IOException - if an I/O error occurs.
     */
    public final long readLong() throws IOException;

    /**
     * See the general contract of the readFloat
     *  method of DataInput.
     * 
     *  Bytes for this operation are read from the contained
     *  input stream.
     * 
     * Specified by:readFloat in interface DataInput
     * 
     * Returns:the next four bytes of this input stream, interpreted as a
     *              float.
     * Throws:
     * EOFException - if this input stream reaches the end before
     *                reading four bytes.
     * IOException - if an I/O error occurs.Since:
     *   CLDC 1.1
     * See Also:readInt(),
     * Float.intBitsToFloat(int)
     */
    public final float readFloat() throws IOException;

    /**
     * See the general contract of the readDouble
     *  method of DataInput.
     * 
     *  Bytes for this operation are read from the contained
     *  input stream.
     * 
     * Specified by:readDouble in interface DataInput
     * 
     * Returns:the next eight bytes of this input stream, interpreted as a
     *              double.
     * Throws:
     * EOFException - if this input stream reaches the end before
     *                reading eight bytes.
     * IOException - if an I/O error occurs.Since:
     *   CLDC 1.1
     * See Also:readLong(),
     * Double.longBitsToDouble(long)
     */
    public final double readDouble() throws IOException;

    /**
     * See the general contract of the readUTF
     *  method of DataInput.
     * 
     *  Bytes for this operation are read from the contained
     *  input stream.
     * 
     * Specified by:readUTF in interface DataInput
     * 
     * Returns:a Unicode string.
     * Throws:
     * EOFException - if this input stream reaches the end before
     *                reading all the bytes.
     * IOException - if an I/O error occurs.See Also:readUTF(java.io.DataInput)
     */
    public final String readUTF() throws IOException;

    /**
     * Reads from the
     *  stream in a representation
     *  of a Unicode  character string encoded in
     *  Java modified UTF-8 format; this string
     *  of characters  is then returned as a String.
     *  The details of the modified UTF-8 representation
     *  are  exactly the same as for the readUTF
     *  method of DataInput.
     * 
     * Parameters:in - a data input stream.
     * Returns:a Unicode string.
     * Throws:
     * EOFException - if the input stream reaches the end
     *              before all the bytes.
     * IOException - if an I/O error occurs.
     * UTFDataFormatException - if the bytes do not represent a
     *              valid UTF-8 encoding of a Unicode string.See Also:readUnsignedShort()
     */
    public static final String readUTF(DataInput in) throws IOException;

    /**
     * Skips over and discards n bytes of data from the
     *  input stream. The skip method may, for a variety of
     *  reasons, end up skipping over some smaller number of bytes,
     *  possibly 0. The actual number of bytes skipped is
     *  returned.
     * 
     *  This method
     *  simply performs in.skip(n).
     * 
     * Overrides:skip in class InputStream
     * 
     * Parameters:n - the number of bytes to be skipped.
     * Returns:the actual number of bytes skipped.
     * Throws:
     * IOException - if an I/O error occurs.
     */
    public long skip(long n) throws IOException;

    /**
     * Returns the number of bytes that can be read from this input
     *  stream without blocking.
     * 
     *  This method simply performs in.available() and
     *  returns the result.
     * 
     * Overrides:available in class InputStream
     * 
     * Returns:the number of bytes that can be read from the input stream
     *              without blocking.
     * Throws:
     * IOException - if an I/O error occurs.
     */
    public int available() throws IOException;

    /**
     * Closes this input stream and releases any system resources
     *  associated with the stream.
     *  This
     *  method simply performs in.close().
     * 
     * Overrides:close in class InputStream
     * 
     * Throws:
     * IOException - if an I/O error occurs.
     */
    public void close() throws IOException;

    /**
     * Marks the current position in this input stream. A subsequent
     *  call to the reset method repositions this stream at
     *  the last marked position so that subsequent reads re-read the same bytes.
     * 
     *  The readlimit argument tells this input stream to
     *  allow that many bytes to be read before the mark position gets
     *  invalidated.
     * 
     *  This method simply performs in.mark(readlimit).
     * 
     * Overrides:mark in class InputStream
     * 
     * Parameters:readlimit - the maximum limit of bytes that can be read before
     *                       the mark position becomes invalid.See Also:InputStream.reset()
     */
    public void mark(int readlimit);

    /**
     * Repositions this stream to the position at the time the
     *  mark method was last called on this input stream.
     * 
     *  This method
     *  simply performs in.reset().
     * 
     *  Stream marks are intended to be used in
     *  situations where you need to read ahead a little to see what's in
     *  the stream. Often this is most easily done by invoking some
     *  general parser. If the stream is of the type handled by the
     *  parse, it just chugs along happily. If the stream is not of
     *  that type, the parser should toss an exception when it fails.
     *  If this happens within readlimit bytes, it allows the outer
     *  code to reset the stream and try another parser.
     * 
     * Overrides:reset in class InputStream
     * 
     * Throws:
     * IOException - if the stream has not been marked or if the
     *              mark has been invalidated.See Also:InputStream.mark(int),
     * IOException
     */
    public void reset() throws IOException;

    /**
     * Tests if this input stream supports the mark
     *  and reset methods.
     *  This method
     *  simply performs in.markSupported().
     * 
     * Overrides:markSupported in class InputStream
     * 
     * Returns:true if this stream type supports the
     *           mark and reset method;
     *           false otherwise.See Also:InputStream.mark(int),
     * InputStream.reset()
     */
    public boolean markSupported();

}

package java.io;

public class DataOutputStream {
    /**
     * The output stream.
     */
    protected OutputStream out = ;

    /**
     * Creates a new data output stream to write data to the specified
     *  underlying output stream.
     * 
     * Parameters:out - the underlying output stream, to be saved for later
     *                 use.
     */
    public DataOutputStream(OutputStream out) {
        construct(out);
    }

    private native void construct(OutputStream out);

    /**
     * Writes the specified byte (the low eight bits of the argument
     *  b) to the underlying output stream.
     * 
     *  Implements the write method of OutputStream.
     * 
     * Specified by:write in interface DataOutputSpecified by:write in class OutputStream
     * 
     * Parameters:b - the byte to be written.
     * Throws:
     * IOException - if an I/O error occurs.
     */
    public void write(int b) throws IOException;

    /**
     * Writes len bytes from the specified byte array
     *  starting at offset off to the underlying output stream.
     * 
     * Specified by:write in interface DataOutputOverrides:write in class OutputStream
     * 
     * Parameters:b - the data.off - the start offset in the data.len - the number of bytes to write.
     * Throws:
     * IOException - if an I/O error occurs.
     */
    public void write(byte[] b, int off, int len) throws IOException;

    /**
     * Flushes this data output stream. This forces any buffered output
     *  bytes to be written out to the stream.
     * 
     *  The flush method of DataOutputStream
     *  calls the flush method of its underlying output stream.
     * 
     * Overrides:flush in class OutputStream
     * 
     * Throws:
     * IOException - if an I/O error occurs.
     */
    public void flush() throws IOException;

    /**
     * Closes this output stream and releases any system resources
     *  associated with the stream.
     * 
     *  The close method
     *  calls its flush method, and then calls the
     *  close method of its underlying output stream.
     * 
     * Overrides:close in class OutputStream
     * 
     * Throws:
     * IOException - if an I/O error occurs.
     */
    public void close() throws IOException;

    /**
     * Writes a boolean to the underlying output stream as
     *  a 1-byte value. The value true is written out as the
     *  value (byte)1; the value false is
     *  written out as the value (byte)0.
     * 
     * Specified by:writeBoolean in interface DataOutput
     * 
     * Parameters:v - a boolean value to be written.
     * Throws:
     * IOException - if an I/O error occurs.
     */
    public final void writeBoolean(boolean v) throws IOException;

    /**
     * Writes out a byte to the underlying output stream as
     *  a 1-byte value.
     * 
     * Specified by:writeByte in interface DataOutput
     * 
     * Parameters:v - a byte value to be written.
     * Throws:
     * IOException - if an I/O error occurs.
     */
    public final void writeByte(int v) throws IOException;

    /**
     * Writes a short to the underlying output stream as two
     *  bytes, high byte first.
     * 
     * Specified by:writeShort in interface DataOutput
     * 
     * Parameters:v - a short to be written.
     * Throws:
     * IOException - if an I/O error occurs.
     */
    public final void writeShort(int v) throws IOException;

    /**
     * Writes a char to the underlying output stream as a
     *  2-byte value, high byte first.
     * 
     * Specified by:writeChar in interface DataOutput
     * 
     * Parameters:v - a char value to be written.
     * Throws:
     * IOException - if an I/O error occurs.
     */
    public final void writeChar(int v) throws IOException;

    /**
     * Writes an int to the underlying output stream as four
     *  bytes, high byte first.
     * 
     * Specified by:writeInt in interface DataOutput
     * 
     * Parameters:v - an int to be written.
     * Throws:
     * IOException - if an I/O error occurs.
     */
    public final void writeInt(int v) throws IOException;

    /**
     * Writes a long to the underlying output stream as eight
     *  bytes, high byte first.
     * 
     * Specified by:writeLong in interface DataOutput
     * 
     * Parameters:v - a long to be written.
     * Throws:
     * IOException - if an I/O error occurs.
     */
    public final void writeLong(long v) throws IOException;

    /**
     * Converts the float argument to an int using the
     *  floatToIntBits method in class Float,
     *  and then writes that int value to the underlying
     *  output stream as a 4-byte quantity, high byte first.
     * 
     * Specified by:writeFloat in interface DataOutput
     * 
     * Parameters:v - a float value to be written.
     * Throws:
     * IOException - if an I/O error occurs.Since:
     *   CLDC 1.1
     * See Also:Float.floatToIntBits(float)
     */
    public final void writeFloat(float v) throws IOException;

    /**
     * Converts the double argument to a long using the
     *  doubleToLongBits method in class Double,
     *  and then writes that long value to the underlying
     *  output stream as an 8-byte quantity, high byte first.
     * 
     * Specified by:writeDouble in interface DataOutput
     * 
     * Parameters:v - a double value to be written.
     * Throws:
     * IOException - if an I/O error occurs.Since:
     *   CLDC 1.1
     * See Also:Double.doubleToLongBits(double)
     */
    public final void writeDouble(double v) throws IOException;

    /**
     * Writes a string to the underlying output stream as a sequence of
     *  characters. Each character is written to the data output stream as
     *  if by the writeChar method.
     * 
     * Specified by:writeChars in interface DataOutput
     * 
     * Parameters:s - a String value to be written.
     * Throws:
     * IOException - if an I/O error occurs.See Also:writeChar(int)
     */
    public final void writeChars(String s) throws IOException;

    /**
     * Writes a string to the underlying output stream using UTF-8
     *  encoding in a machine-independent manner.
     * 
     *  First, two bytes are written to the output stream as if by the
     *  writeShort method giving the number of bytes to
     *  follow. This value is the number of bytes actually written out,
     *  not the length of the string. Following the length, each character
     *  of the string is output, in sequence, using the UTF-8 encoding
     *  for the character.
     * 
     * Specified by:writeUTF in interface DataOutput
     * 
     * Parameters:str - a string to be written.
     * Throws:
     * IOException - if an I/O error occurs.
     */
    public final void writeUTF(String str) throws IOException;

}

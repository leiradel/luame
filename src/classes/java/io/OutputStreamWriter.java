package java.io;

public class OutputStreamWriter extends Writer {
    /**
     * Create an OutputStreamWriter that uses the default character encoding.
     * 
     * Parameters:os - An OutputStream
     */
    public OutputStreamWriter(OutputStream os) {
        construct(os);
    }

    private native void construct(OutputStream os);

    /**
     * Create an OutputStreamWriter that uses the named character encoding.
     * 
     * Parameters:os - An OutputStreamenc - The name of a supported
     * Throws:
     * UnsupportedEncodingException - If the named encoding is not supported
     */
    public OutputStreamWriter(OutputStream os, String enc) throws UnsupportedEncodingException {
        construct(os, enc);
    }

    private native void construct(OutputStream os, String enc);

    /**
     * Write a single character.
     * 
     * Overrides:write in class Writer
     * 
     * Parameters:c - int specifying a character to be written.
     * Throws:
     * IOException - If an I/O error occurs
     */
    public void write(int c) throws IOException;

    /**
     * Write a portion of an array of characters.
     * 
     * Specified by:write in class Writer
     * 
     * Parameters:cbuf - Buffer of characters to be writtenoff - Offset from which to start reading characterslen - Number of characters to be written
     * Throws:
     * IOException - If an I/O error occurs
     */
    public void write(char[] cbuf, int off, int len) throws IOException;

    /**
     * Write a portion of a string.
     * 
     * Overrides:write in class Writer
     * 
     * Parameters:str - String to be writtenoff - Offset from which to start reading characterslen - Number of characters to be written
     * Throws:
     * IOException - If an I/O error occurs
     */
    public void write(String str, int off, int len) throws IOException;

    /**
     * Flush the stream.
     * 
     * Specified by:flush in class Writer
     * 
     * Throws:
     * IOException - If an I/O error occurs
     */
    public void flush() throws IOException;

    /**
     * Close the stream.
     * 
     * Specified by:close in class Writer
     * 
     * Throws:
     * IOException - If an I/O error occurs
     */
    public void close() throws IOException;

}

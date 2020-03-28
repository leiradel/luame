package javax.microedition.io;

public class Connector {
    /**
     * Access mode READ.
     * 
     * See Also:Constant Field Values
     */
    public static final int READ = ;

    /**
     * Access mode WRITE.
     * 
     * See Also:Constant Field Values
     */
    public static final int WRITE = ;

    /**
     * Access mode READ_WRITE.
     * 
     * See Also:Constant Field Values
     */
    public static final int READ_WRITE = ;

    /**
     * Create and open a Connection.
     * 
     * Parameters:name - The URL for the connection.
     * Returns:A new Connection object.
     * Throws:
     * IllegalArgumentException - If a parameter is invalid.
     * ConnectionNotFoundException - If the target of the
     *    name cannot be found, or if the requested protocol type
     *    is not supported.
     * IOException - If some other kind of I/O error occurs.
     * SecurityException - May be thrown if access to the
     *    protocol handler is prohibited.
     */
    public static Connection open(String name) throws IOException;

    /**
     * Create and open a Connection.
     * 
     * Parameters:name - The URL for the connection.mode - The access mode.
     * Returns:A new Connection object.
     * Throws:
     * IllegalArgumentException - If a parameter is invalid.
     * ConnectionNotFoundException - If the target of the
     *    name cannot be found, or if the requested protocol type
     *    is not supported.
     * IOException - If some other kind of I/O error occurs.
     * SecurityException - May be thrown if access to the
     *    protocol handler is prohibited.
     */
    public static Connection open(String name, int mode) throws IOException;

    /**
     * Create and open a Connection.
     * 
     * Parameters:name - The URL for the connectionmode - The access modetimeouts - A flag to indicate that the caller
     *                          wants timeout exceptions
     * Returns:A new Connection object
     * Throws:
     * IllegalArgumentException - If a parameter is invalid.
     * ConnectionNotFoundException - If the target of the
     *    name cannot be found, or if the requested protocol type
     *    is not supported.
     * IOException - If some other kind of I/O error occurs.
     * SecurityException - May be thrown if access to the
     *    protocol handler is prohibited.
     */
    public static Connection open(String name, int mode, boolean timeouts) throws IOException;

    /**
     * Create and open a connection input stream.
     * 
     * Parameters:name - The URL for the connection.
     * Returns:A DataInputStream.
     * Throws:
     * IllegalArgumentException - If a parameter is invalid.
     * ConnectionNotFoundException - If the target of the
     *    name cannot be found, or if the requested protocol type
     *    is not supported.
     * IOException - If some other kind of I/O error occurs.
     * SecurityException - May be thrown if access to the
     *    protocol handler is prohibited.
     */
    public static DataInputStream openDataInputStream(String name) throws IOException;

    /**
     * Create and open a connection output stream.
     * 
     * Parameters:name - The URL for the connection.
     * Returns:A DataOutputStream.
     * Throws:
     * IllegalArgumentException - If a parameter is invalid.
     * ConnectionNotFoundException - If the target of the
     *    name cannot be found, or if the requested protocol type
     *    is not supported.
     * IOException - If some other kind of I/O error occurs.
     * SecurityException - May be thrown if access to the
     *    protocol handler is prohibited.
     */
    public static DataOutputStream openDataOutputStream(String name) throws IOException;

    /**
     * Create and open a connection input stream.
     * 
     * Parameters:name - The URL for the connection.
     * Returns:An InputStream.
     * Throws:
     * IllegalArgumentException - If a parameter is invalid.
     * ConnectionNotFoundException - If the target of the
     *    name cannot be found, or if the requested protocol type
     *    is not supported.
     * IOException - If some other kind of I/O error occurs.
     * SecurityException - May be thrown if access to the
     *    protocol handler is prohibited.
     */
    public static InputStream openInputStream(String name) throws IOException;

    /**
     * Create and open a connection output stream.
     * 
     * Parameters:name - The URL for the connection.
     * Returns:An OutputStream.
     * Throws:
     * IllegalArgumentException - If a parameter is invalid.
     * ConnectionNotFoundException - If the target of the
     *    name cannot be found, or if the requested protocol type
     *    is not supported.
     * IOException - If some other kind of I/O error occurs.
     * SecurityException - May be thrown if access to the
     *    protocol handler is prohibited.
     */
    public static OutputStream openOutputStream(String name) throws IOException;

}

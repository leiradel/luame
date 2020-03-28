package javax.microedition.io;

public class PushRegistry {
    /**
     * Register a dynamic connection with the
     *  application management software. Once registered,
     *  the dynamic connection acts just like a
     *  connection preallocated from the descriptor file.
     * 
     *   The arguments for the dynamic connection registration are the
     *  same as the Push Registration Attribute
     *  used for static registrations.
     * 
     *   If the connection or filter
     *  arguments are null,
     *  then an IllegalArgumentException will be thrown.
     *  If the midlet argument is null a
     *  ClassNotFoundException will be thrown.
     * 
     * Parameters:connection - generic connection protocol, host
     *               and port number
     *               (optional parameters may be included
     *               separated with semi-colons (;))midlet - class name of the MIDlet to be launched,
     *               when new external data is available.
     *  The named MIDlet MUST be registered in the
     *  descriptor file or the jar file manifest with a
     *  MIDlet-&lt;n&gt; record. This parameter has the same semantics
     *  as the MIDletClassName in the Push registration attribute
     *  defined above in the class description.filter - a connection URL string indicating which senders
     *               are allowed to cause the MIDlet to be launched
     * Throws:
     * IllegalArgumentException - if the connection string is not
     *               valid, or if the filter string is not valid
     * ConnectionNotFoundException - if the runtime system does not
     *               support push delivery for the requested
     *               connection protocol
     * IOException - if the connection is already
     *               registered or if there are insufficient resources
     *               to handle the registration request
     * ClassNotFoundException - if the MIDlet
     *  class name can not be found in the current MIDlet
     *  suite or if this class is not included in any of the
     *  MIDlet-&lt;n&gt; records in the descriptor file or the jar file
     *  manifest
     * SecurityException - if the MIDlet does not
     *               have permission to register a connectionSee Also:unregisterConnection(java.lang.String)
     */
    public static void registerConnection(String connection, String midlet, String filter) throws ClassNotFoundException, IOException;

    /**
     * Remove a dynamic connection registration.
     * 
     * Parameters:connection - generic connection protocol,
     *             host and port number
     * Returns:true if the unregistration was successful,
     *          false if the connection was not registered
     *          or if the connection argument was null
     * Throws:
     * SecurityException - if the connection was
     *             registered by another MIDlet
     *             suiteSee Also:registerConnection(java.lang.String, java.lang.String, java.lang.String)
     */
    public static boolean unregisterConnection(String connection);

    /**
     * Return a list of registered connections for the current
     *  MIDlet suite.
     * 
     * Parameters:available - if true, only return the list of
     *      connections with input available, otherwise return the
     *      complete list of registered connections for the current
     *      MIDlet suite
     * Returns:array of registered connection strings, where each connection
     *       is represented by the generic connection protocol,
     *        host and port number identification
     */
    public static String[] listConnections(boolean available);

    /**
     * Retrieve the registered MIDlet for a requested connection.
     * 
     * Parameters:connection - generic connection protocol, host
     *               and port number
     *               (optional parameters may be included
     *               separated with semi-colons (;))
     * Returns:class name of the MIDlet to be launched,
     *               when new external data is available, or
     *               null if the connection was not
     *               registered by the current MIDlet suite
     *               or if the connection argument was nullSee Also:registerConnection(java.lang.String, java.lang.String, java.lang.String)
     */
    public static String getMIDlet(String connection);

    /**
     * Retrieve the registered filter for a requested connection.
     * 
     * Parameters:connection - generic connection protocol, host
     *               and port number
     *               (optional parameters may be included
     *               separated with semi-colons (;))
     * Returns:a filter string indicating which senders
     *               are allowed to cause the MIDlet to be
     *               launched or null, if the connection was not
     *               registered by the current MIDlet suite
     *               or if the connection argument was nullSee Also:registerConnection(java.lang.String, java.lang.String, java.lang.String)
     */
    public static String getFilter(String connection);

    /**
     * Register a time to launch the specified application. The
     *  PushRegistry supports one outstanding wake up
     *  time per MIDlet in the current suite. An application
     *  is expected to use a TimerTask for notification
     *  of time based events while the application is running.
     *  If a wakeup time is already registered, the previous value will
     *  be returned, otherwise a zero is returned the first time the
     *  alarm is registered.
     * 
     * Parameters:midlet - class name of the MIDlet within the
     *                 current running MIDlet suite
     *                 to be launched,
     *                 when the alarm time has been reached.
     *  The named MIDlet MUST be registered in the
     *  descriptor file or the jar file manifest with a
     *  MIDlet-&lt;n&gt; record. This parameter has the same semantics
     *  as the MIDletClassName in the Push registration attribute
     *  defined above in the class description.time - time at which the MIDlet is to be executed
     *         in the format returned by Date.getTime()
     * Returns:the time at which the most recent execution of this
     *         MIDlet was scheduled to occur,
     *         in the format returned by Date.getTime()
     * Throws:
     * ConnectionNotFoundException - if the runtime system does not
     *               support alarm based application launch
     * ClassNotFoundException - if the MIDlet
     *  class name can not be found in the current MIDlet
     *  suite or if this class is not included in any of the
     *  MIDlet-&lt;n&gt; records in the descriptor file or the jar file
     *  manifest or if the midlet argument is
     *  null
     * SecurityException - if the MIDlet does not
     *               have permission to register an alarmSee Also:Date.getTime(),
     * Timer,
     * TimerTask
     */
    public static long registerAlarm(String midlet, long time) throws ClassNotFoundException, ConnectionNotFoundException;

}

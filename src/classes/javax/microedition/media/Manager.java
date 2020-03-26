package javax.microedition.media;

public class Manager {
    /**
     * The locator to create a tone Player
     *  to play back tone sequences. e.g.
     * 
     *  try {
     *      Player p = Manager.createPlayer(Manager.TONE_DEVICE_LOCATOR);
     *      p.realize();
     *      ToneControl tc = (ToneControl)p.getControl("ToneControl");
     *      tc.setSequence(mySequence);
     *      p.start();
     *  } catch (IOException ioe) {
     *  } catch (MediaException me) {}
     * 
     *  If a tone sequence is not set on the tone
     *  Player via its ToneControl,
     *  the Player does not carry any
     *  sequence.  getDuration returns 0 for this
     *  Player.
     * 
     *  The content type of the Player created from this
     *  locator is audio/x-tone-seq.
     * 
     *  A Player for this locator may not be supported
     *  for all implementations.
     * 
     *  Value "device://tone" is assigned to TONE_DEVICE_LOCATOR.
     * 
     * See Also:Constant Field Values
     */
    public static final String TONE_DEVICE_LOCATOR = ;

    /**
     * Return the list of supported content types for the given protocol.
     * 
     *  See content types for the syntax
     *  of the content types returned.
     *  See protocol name for the syntax
     *  of the protocol used.
     * 
     *  For example, if the given protocol
     *  is "http",
     *  then the supported content types that can be played back
     *  with the http protocol will be returned.
     * 
     *  If null is passed in as the protocol,
     *  all the supported content types for this implementation
     *  will be returned.  The returned array must be non-empty.
     * 
     *  If the given protocol is an invalid or
     *  unsupported protocol, then an empty array will be returned.
     * 
     * Parameters:protocol - The input protocol for the supported content types.
     * Returns:The list of supported content types for the given protocol.
     */
    public static String[] getSupportedContentTypes(String protocol);

    /**
     * Return the list of supported protocols given the content
     *  type.  The protocols are returned
     *  as strings which identify what locators can be used for creating
     *  Player's.
     * 
     *  See protocol name for the syntax
     *  of the protocols returned.
     *  See content types for the syntax
     *  of the content type used.
     * 
     *  For example, if the given content_type
     *  is "audio/x-wav", then the supported protocols
     *  that can be used to play back audio/x-wav
     *  will be returned.
     * 
     *  If null is passed in as the
     *  content_type,
     *  all the supported protocols for this implementation
     *  will be returned.  The returned array must be non-empty.
     * 
     *  If the given content_type is an invalid or
     *  unsupported content type, then an empty array will be returned.
     * 
     * Parameters:content_type - The content type for the supported protocols.
     * Returns:The list of supported protocols for the given content type.
     */
    public static String[] getSupportedProtocols(String content_type);

    /**
     * Create a Player from an input locator.
     * 
     * Parameters:locator - A locator string in URI syntax that describes
     *  the media content.
     * Returns:A new Player.
     * Throws:
     * IllegalArgumentException - Thrown if locator
     *  is null.
     * MediaException - Thrown if a Player cannot
     *  be created for the given locator.
     * IOException - Thrown if there was a problem connecting
     *  with the source pointed to by the locator.
     * SecurityException - Thrown if the caller does not
     *  have security permission to create the Player.
     */
    public static Player createPlayer(String locator)
                           throws IOException,
                                  MediaException;

    /**
     * Create a Player to play back media from an
     *  InputStream.
     * 
     *  The type argument
     *  specifies the content-type of the input media.  If
     *  null is given, Manager will
     *  attempt to determine the type.  However, since determining
     *  the media type is non-trivial for some media types, it
     *  may not be feasible in some cases.  The
     *  Manager may throw a MediaException
     *  to indicate that.
     * 
     * Parameters:stream - The InputStream that delivers the
     *  input media.type - The ContentType of the media.
     * Returns:A new Player.
     * Throws:
     * IllegalArgumentException - Thrown if stream
     *  is null.
     * MediaException - Thrown if a Player cannot
     *  be created for the given stream and type.
     * IOException - Thrown if there was a problem reading data
     *  from the InputStream.
     * SecurityException - Thrown if the caller does not
     *  have security permission to create the Player.
     */
    public static Player createPlayer(InputStream stream,
                                  String type)
                           throws IOException,
                                  MediaException;

}

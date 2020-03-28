package javax.microedition.lcdui;

public class AlertType {
    /**
     * An INFO AlertType typically
     *  provides non-threatening information to the
     *  user. For example, a simple splash screen might be an
     *  INFO AlertType.
     */
    public static final AlertType INFO = ;

    /**
     * A WARNING AlertType is a hint
     *  to warn the user of a potentially
     *  dangerous operation.
     *  For example, the warning message may contain the message, &quot;Warning:
     *  this operation will erase your data.&quot;
     */
    public static final AlertType WARNING = ;

    /**
     * An ERROR AlertType is a hint
     *  to alert the user to an erroneous operation.
     *  For example, an error alert might show the message,
     *  &quot;There is not enough room to install the application.&quot;
     */
    public static final AlertType ERROR = ;

    /**
     * An ALARM AlertType is a hint
     *  to alert the user to an event for which
     *  the user has previously requested to be notified.
     *  For example, the message might say, &quot;Staff meeting in five
     *  minutes.&quot;
     */
    public static final AlertType ALARM = ;

    /**
     * A CONFIRMATION AlertType is a
     *  hint to confirm user actions.
     *  For example, &quot;Saved!&quot; might be shown to indicate that a Save
     *  operation has completed.
     */
    public static final AlertType CONFIRMATION = ;

    /**
     * Protected constructor for subclasses.
     */
    protected AlertType() {
        construct();
    }

    private native void construct();

    /**
     * Alert the user by playing the sound for this
     *  AlertType.
     *  The AlertType instance is used as a hint by the device
     *  to generate an appropriate sound.  Instances other than
     *  those predefined above may be ignored.
     *  The actual sound made by the device,
     *  if any, is determined by the device. The device may
     *  ignore the request, use the same sound for
     *  several AlertTypes or use any other means
     *  suitable to alert
     *  the user.
     * 
     * Parameters:display - to which the AlertType's sound
     *  should be played.
     * Returns:true if the user was alerted,
     *  false otherwise.
     * Throws:
     * NullPointerException - if display is null
     */
    public boolean playSound(Display display);

}

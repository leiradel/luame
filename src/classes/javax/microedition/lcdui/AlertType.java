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

}

package javax.microedition.lcdui;

public class Alert extends Screen {
    /**
     * FOREVER indicates that an Alert is
     *  kept visible until the user
     *  dismisses it.  It is used as a value for the parameter to
     *  setTimeout()
     *  to indicate that the alert is modal.  Instead of waiting for a
     *  specified period of time, a modal Alert will wait
     *  for the user to take
     *  some explicit action, such as pressing a button, before proceeding to
     *  the next Displayable.
     * 
     *  Value -2 is assigned to FOREVER.
     * 
     * See Also:Constant Field Values
     */
    public static final int FOREVER = ;

    /**
     * A Command delivered to a listener to indicate that
     *  the Alert has been
     *  dismissed.  This Command is implicitly present an on
     *  Alert whenever
     *  there are no other Commands present.  The field values of
     *  DISMISS_COMMAND are as follows:
     * 
     *  label = &quot;&quot; (an empty string)
     *  type = Command.OK
     *  priority = 0
     * 
     *  The label value visible to the application must be as specified
     *  above.  However, the implementation may display
     *  DISMISS_COMMAND to the
     *  user using an implementation-specific label.
     * 
     *  Attempting to add or remove DISMISS_COMMAND
     *  from an Alert has no
     *  effect.  However, DISMISS_COMMAND is treated as an
     *  ordinary Command if
     *  it is used with other Displayable types.
     * 
     * Since:
     *   MIDP 2.0
     */
    public static final Command DISMISS_COMMAND = ;

    /**
     * Constructs a new, empty Alert object with the
     *  given title. If null is
     *  passed, the Alert will have no title.  Calling
     *  this constructor is
     *  equivalent to calling
     * 
     *     Alert(title, null, null, null)
     * 
     * Parameters:title - the title string, or nullSee Also:Alert(String, String, Image, AlertType)
     */
    public Alert(String title) {
        construct(title);
    }

    private native void construct(String title);

    /**
     * Gets the default time for showing an Alert.  This
     *  is either a
     *  positive value, which indicates a time in milliseconds, or the special
     *  value
     *  FOREVER,
     *  which indicates that Alerts are modal by default.  The
     *  value returned will vary across implementations and is presumably
     *  tailored to be suitable for each.
     * 
     * Returns:default timeout in milliseconds, or FOREVER
     */
    public int getDefaultTimeout();

    /**
     * Gets the time this Alert will be shown.  This is
     *  either a positive
     *  value, which indicates a time in milliseconds, or the special value
     *  FOREVER, which indicates that this
     *  Alert is modal.  This value is not
     *  necessarily the same value that might have been set by the
     *  application
     *  in a call to setTimeout(int).  In particular, if the
     *  Alert is made
     *  modal because its contents is large enough to scroll, the value
     *  returned by getTimeout will be FOREVER.
     * 
     * Returns:timeout in milliseconds, or FOREVERSee Also:setTimeout(int)
     */
    public int getTimeout();

    /**
     * Set the time for which the Alert is to be shown.
     *  This must either
     *  be a positive time value in milliseconds, or the special value
     *  FOREVER.
     * 
     * Parameters:time - timeout in milliseconds, or FOREVER
     * Throws:
     * IllegalArgumentException - if time is not positive and is
     *  not FOREVERSee Also:getTimeout()
     */
    public void setTimeout(int time);

    /**
     * Gets the type of the Alert.
     * 
     * Returns:a reference to an instance of AlertType,
     *  or null
     *  if the Alert
     *  has no specific typeSee Also:setType(javax.microedition.lcdui.AlertType)
     */
    public AlertType getType();

    /**
     * Sets the type of the Alert.
     *  The handling and behavior of specific AlertTypes
     *  is described in
     *  AlertType.
     * 
     * Parameters:type - an AlertType, or null if the
     *  Alert has no
     *  specific typeSee Also:getType()
     */
    public void setType(AlertType type);

    /**
     * Gets the text string used in the Alert.
     * 
     * Returns:the Alert's text string, or null
     *  if there is no textSee Also:setString(java.lang.String)
     */
    public String getString();

    /**
     * Sets the text string used in the Alert.
     * 
     *  If the Alert is visible on the display when its
     *  contents are updated
     *  through a call to setString, the display will be
     *  updated with the new
     *  contents as soon as it is feasible for the implementation to do so.
     * 
     * Parameters:str - the Alert's text string, or null
     *  if there is no textSee Also:getString()
     */
    public void setString(String str);

    /**
     * Gets the Image used in the Alert.
     * 
     * Returns:the Alert's image, or null
     *  if there is no imageSee Also:setImage(javax.microedition.lcdui.Image)
     */
    public Image getImage();

    /**
     * Sets the Image used in the Alert.
     *  The Image may be mutable or
     *  immutable.  If img is null, specifies
     *  that this Alert has no image.
     *  If img is mutable, the effect is as if a snapshot is taken
     *  of img's contents immediately prior to the call to
     *  setImage.  This
     *  snapshot is used whenever the contents of the
     *  Alert are to be
     *  displayed.  If img is already the
     *  Image of this Alert, the effect
     *  is as if a new snapshot of img's contents is taken.  Thus, after
     *  painting into a mutable image contained by an Alert, the
     *  application can call
     * 
     *     alert.setImage(alert.getImage());
     * 
     *  to refresh the Alert's snapshot of its
     *  Image.
     * 
     *  If the Alert is visible on the display when its
     *  contents are updated
     *  through a call to setImage, the display will be
     *  updated with the new
     *  snapshot as soon as it is feasible for the implementation to do so.
     * 
     * Parameters:img - the Alert's image, or null
     *  if there is no imageSee Also:getImage()
     */
    public void setImage(Image img);

    /**
     * Sets an activity indicator on this Alert.  The
     *  activity indicator is a
     *  Gauge object.  It must be in a restricted state in order for it
     *  to be used as the activity indicator for an Alert.
     *  The restrictions
     *  are listed above.  If the
     *  Gauge object
     *  violates any of these restrictions,
     *  IllegalArgumentException is thrown.
     * 
     *  If indicator is null, this removes any
     *  activity indicator present on this Alert.
     * 
     * Parameters:indicator - the activity indicator for this Alert,
     *  or null if
     *  there is to be none
     * Throws:
     * IllegalArgumentException - if indicator does not
     *  meet the restrictions for its use in an AlertSince:
     *   MIDP 2.0
     * See Also:getIndicator()
     */
    public void setIndicator(Gauge indicator);

    /**
     * Gets the activity indicator for this Alert.
     * 
     * Returns:a reference to this Alert's activity indicator,
     *  or null if
     *  there is noneSince:
     *   MIDP 2.0
     * See Also:setIndicator(javax.microedition.lcdui.Gauge)
     */
    public Gauge getIndicator();

    /**
     * Similar to Displayable.addCommand(javax.microedition.lcdui.Command), however when the
     *  application first adds a command to an Alert,
     *  DISMISS_COMMAND is implicitly removed.  Calling this
     *  method with DISMISS_COMMAND as the parameter has
     *  no effect.
     * 
     * Overrides:addCommand in class Displayable
     * 
     * Parameters:cmd - the command to be added
     * Throws:
     * NullPointerException - if cmd is null
     */
    public void addCommand(Command cmd);

    /**
     * Similar to Displayable.removeCommand(javax.microedition.lcdui.Command), however when the
     *  application removes the last command from an
     *  Alert, DISMISS_COMMAND is implicitly
     *  added.  Calling this method with DISMISS_COMMAND
     *  as the parameter has no effect.
     * 
     * Overrides:removeCommand in class Displayable
     * 
     * Parameters:cmd - the command to be removed
     */
    public void removeCommand(Command cmd);

}

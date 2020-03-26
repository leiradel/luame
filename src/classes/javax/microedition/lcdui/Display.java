package javax.microedition.lcdui;

public class Display {
    /**
     * Image type for List element image.
     * 
     *  The value of LIST_ELEMENT is 1.
     * 
     * Since:
     *   MIDP 2.0
     * See Also:getBestImageWidth(int imageType),
     * getBestImageHeight(int imageType),
     * Constant Field Values
     */
    public static final int LIST_ELEMENT = 1;

    /**
     * Image type for ChoiceGroup element image.
     * 
     *  The value of CHOICE_GROUP_ELEMENT is 2.
     * 
     * Since:
     *   MIDP 2.0
     * See Also:getBestImageWidth(int imageType),
     * getBestImageHeight(int imageType),
     * Constant Field Values
     */
    public static final int CHOICE_GROUP_ELEMENT = 2;

    /**
     * Image type for Alert image.
     * 
     *  The value of ALERT is 3.
     * 
     * Since:
     *   MIDP 2.0
     * See Also:getBestImageWidth(int imageType),
     * getBestImageHeight(int imageType),
     * Constant Field Values
     */
    public static final int ALERT = 3;

    /**
     * A color specifier for use with getColor.
     *  COLOR_BACKGROUND specifies the background color of
     *  the screen.
     *  The background color will always contrast with the foreground color.
     * 
     *  COLOR_BACKGROUND has the value 0.
     * 
     * Since:
     *   MIDP 2.0
     * See Also:getColor(int),
     * Constant Field Values
     */
    public static final int COLOR_BACKGROUND = 0;

    /**
     * A color specifier for use with getColor.
     *  COLOR_FOREGROUND specifies the foreground color,
     *  for text characters
     *  and simple graphics on the screen.  Static text or user-editable
     *  text should be drawn with the foreground color.  The foreground color
     *  will always constrast with background color.
     * 
     *   COLOR_FOREGROUND has the value 1.
     * 
     * Since:
     *   MIDP 2.0
     * See Also:getColor(int),
     * Constant Field Values
     */
    public static final int COLOR_FOREGROUND = 1;

    /**
     * A color specifier for use with getColor.
     *  COLOR_HIGHLIGHTED_BACKGROUND identifies the color for the
     *  focus, or focus highlight, when it is drawn as a
     *  filled in rectangle. The highlighted
     *  background will always constrast with the highlighted foreground.
     * 
     *  COLOR_HIGHLIGHTED_BACKGROUND has the value 2.
     * 
     * Since:
     *   MIDP 2.0
     * See Also:getColor(int),
     * Constant Field Values
     */
    public static final int COLOR_HIGHLIGHTED_BACKGROUND = 2;

    /**
     * A color specifier for use with getColor.
     *  COLOR_HIGHLIGHTED_FOREGROUND identifies the color for text
     *  characters and simple graphics when they are highlighted.
     *  Highlighted
     *  foreground is the color to be used to draw the highlighted text
     *  and graphics against the highlighted background.
     *  The highlighted foreground will always constrast with
     *  the highlighted background.
     * 
     *  COLOR_HIGHLIGHTED_FOREGROUND has the value 3.
     * 
     * Since:
     *   MIDP 2.0
     * See Also:getColor(int),
     * Constant Field Values
     */
    public static final int COLOR_HIGHLIGHTED_FOREGROUND = 3;

    /**
     * A color specifier for use with getColor.
     *  COLOR_BORDER identifies the color for boxes and borders
     *  when the object is to be drawn in a
     *  non-highlighted state.  The border color is intended to be used with
     *  the background color and will contrast with it.
     *  The application should draw its borders using the stroke style returned
     *  by getBorderStyle().
     * 
     *   COLOR_BORDER has the value 4.
     * 
     * Since:
     *   MIDP 2.0
     * See Also:getColor(int),
     * Constant Field Values
     */
    public static final int COLOR_BORDER = 4;

    /**
     * A color specifier for use with getColor.
     *  COLOR_HIGHLIGHTED_BORDER
     *  identifies the color for boxes and borders when the object is to be
     *  drawn in a highlighted state.  The highlighted border color is intended
     *  to be used with the background color (not the highlighted background
     *  color) and will contrast with it.  The application should draw its
     *  borders using the stroke style returned by getBorderStyle().
     * 
     *   COLOR_HIGHLIGHTED_BORDER has the value 5.
     * 
     * Since:
     *   MIDP 2.0
     * See Also:getColor(int),
     * Constant Field Values
     */
    public static final int COLOR_HIGHLIGHTED_BORDER = 5;

    /**
     * Gets the Display object that is unique to this
     *  MIDlet.
     * 
     * Parameters:m - MIDlet of the application
     * Returns:the display object that application can use for its user
     *  interface
     * Throws:
     * NullPointerException - if m is null
     */
    public static Display getDisplay(MIDlet m);

    /**
     * Returns one of the colors from the high level user interface
     *  color scheme, in the form 0x00RRGGBB based on the
     *  colorSpecifier passed in.
     * 
     * Parameters:colorSpecifier - the predefined color specifier;
     *   must be one of
     *   COLOR_BACKGROUND,
     *   COLOR_FOREGROUND,
     *   COLOR_HIGHLIGHTED_BACKGROUND,
     *   COLOR_HIGHLIGHTED_FOREGROUND,
     *   COLOR_BORDER, or
     *   COLOR_HIGHLIGHTED_BORDER
     * Returns:color in the form of 0x00RRGGBB
     * Throws:
     * IllegalArgumentException - if colorSpecifier
     *  is not a valid color specifierSince:
     *   MIDP 2.0
     */
    public int getColor(int colorSpecifier);

    /**
     * Returns the stroke style used for border drawing
     *  depending on the state of the component
     *  (highlighted/non-highlighted). For example, on a monochrome
     *  system, the border around a non-highlighted item might be
     *  drawn with a DOTTED stroke style while the border around a
     *  highlighted item might be drawn with a SOLID stroke style.
     * 
     * Parameters:highlighted - true if the border style being
     *  requested is for the
     *  highlighted state, false if the border style being
     *  requested is for the
     *  non-highlighted state
     * Returns:Graphics.DOTTED or Graphics.SOLIDSince:
     *   MIDP 2.0
     */
    public int getBorderStyle(boolean highlighted);

    /**
     * Gets information about color support of the device.
     * 
     * Returns:true if the display supports color,
     *  false otherwise
     */
    public boolean isColor();

    /**
     * Gets the number of colors (if isColor() is
     *  true)
     *  or graylevels (if isColor() is false)
     *  that can be
     *  represented on the device.
     *  Note that the number of colors for a black and white display is
     *  2.
     * 
     * Returns:number of colors
     */
    public int numColors();

    /**
     * Gets the number of alpha transparency levels supported by this
     *  implementation.  The minimum legal return value is
     *  2, which indicates
     *  support for full transparency and full opacity and no blending.  Return
     *  values greater than 2 indicate that alpha blending
     *  is supported.  For
     *  further information, see Alpha
     *  Processing.
     * 
     * Returns:number of alpha levels supportedSince:
     *   MIDP 2.0
     */
    public int numAlphaLevels();

    /**
     * Gets the current Displayable object for this
     *  MIDlet.  The
     *  Displayable object returned may not actually be
     *  visible on the display
     *  if the MIDlet is running in the background, or if
     *  the Displayable is
     *  obscured by a system screen.  The Displayable.isShown() method may be called to determine whether the
     *  Displayable is actually visible on the display.
     * 
     *   The value returned by getCurrent() may be
     *  null. This
     *  occurs after the application has been initialized but before the first
     *  call to setCurrent().
     * 
     * Returns:the MIDlet's current Displayable objectSee Also:setCurrent(javax.microedition.lcdui.Displayable)
     */
    public Displayable getCurrent();

    /**
     * Requests that a different Displayable object be
     *  made visible on the
     *  display.  The change will typically not take effect immediately.  It
     *  may be delayed so that it occurs between event delivery method
     *  calls, although it is not guaranteed to occur before the next event
     *  delivery method is called.  The setCurrent() method returns
     *  immediately, without waiting for the change to take place.  Because of
     *  this delay, a call to getCurrent() shortly after a
     *  call to setCurrent()
     *  is unlikely to return the value passed to setCurrent().
     * 
     *   Calls to setCurrent() are not queued.  A
     *  delayed request made by a
     *  setCurrent() call may be superseded by a subsequent call to
     *  setCurrent().  For example, if screen
     *  S1 is current, then
     * 
     *      d.setCurrent(S2);
     *      d.setCurrent(S3);
     * 
     *   may eventually result in S3 being made
     *  current, bypassing S2
     *  entirely.
     * 
     *   When a MIDlet application is first started,
     *  there is no current
     *  Displayable object.  It is the responsibility of
     *  the application to
     *  ensure that a Displayable is visible and can
     *  interact with the user at
     *  all times.  Therefore, the application should always call
     *  setCurrent()
     *  as part of its initialization.
     * 
     *   The application may pass null as the argument to
     *  setCurrent().  This does not have the effect of
     *  setting the current
     *  Displayable to null; instead, the
     *  current Displayable
     *  remains unchanged.  However, the application management software may
     *  interpret this call as a request from the application that it is
     *  requesting to be placed into the background.  Similarly, if the
     *  application is in the background, passing a non-null
     *  reference to setCurrent() may be interpreted by
     *  the application
     *  management software as a request that the application is
     *  requesting to be
     *  brought to the foreground.  The request should be considered to be made
     *  even if the current Displayable is passed to the
     *  setCurrent().  For
     *  example, the code
     * 
     *    d.setCurrent(d.getCurrent());
     * 
     *   generally will have no effect other than requesting that the
     *  application be brought to the foreground.  These are only requests,
     *  and there is no requirement that the application management
     *  software comply with these requests in a timely fashion if at all.
     * 
     *   If the Displayable passed to
     *  setCurrent() is an Alert, the previously current Displayable, if
     *  any, is restored after
     *  the Alert has been dismissed.  If there is a
     *  current Displayable, the
     *  effect is as if setCurrent(Alert, getCurrent())
     *  had been called.  Note
     *  that this will result in an exception being thrown if the current
     *  Displayable is already an alert.  If there is no
     *  current Displayable
     *  (which may occur at startup time) the implementation's previous state
     *  will be restored after the Alert has been
     *  dismissed.  The automatic
     *  restoration of the previous Displayable or the
     *  previous state occurs
     *  only when the Alert's default listener is present
     *  on the Alert when it
     *  is dismissed.  See Alert Commands and
     *  Listeners for details.
     * 
     *  To specify the
     *  Displayable to be shown after an
     *  Alert is dismissed, the application
     *  should use the setCurrent(Alert,
     *  Displayable) method.  If the application calls
     *  setCurrent() while an
     *  Alert is current, the Alert is
     *  removed from the display and any timer
     *  it may have set is cancelled.
     * 
     *   If the application calls setCurrent() while a
     *  system screen is
     *  active, the effect may be delayed until after the system screen is
     *  dismissed.  The implementation may choose to interpret
     *  setCurrent() in
     *  such a situation as a request to cancel the effect of the system
     *  screen, regardless of whether setCurrent() has
     *  been delayed.
     * 
     * Parameters:nextDisplayable - the Displayable requested
     *  to be made current;
     *  null is allowedSee Also:getCurrent()
     */
    public void setCurrent(Displayable nextDisplayable);

    /**
     * Requests that this Alert be made current, and that
     *  nextDisplayable be
     *  made current
     *  after the Alert is dismissed.  This call returns
     *  immediately regardless
     *  of the Alert's timeout value or whether it is a
     *  modal alert.  The
     *  nextDisplayable must not be an Alert,
     *  and it must not be null.
     * 
     *  The automatic advance to nextDisplayable occurs only
     *  when the Alert's default listener is present on
     *  the Alert when it
     *  is dismissed.  See Alert Commands and
     *  Listeners for details.
     * 
     *   In other respects, this method behaves identically to
     *  setCurrent(Displayable).
     * 
     * Parameters:alert - the alert to be shownnextDisplayable - the Displayable to be
     *  shown after this alert is  dismissed
     * Throws:
     * NullPointerException - if alert or
     *  nextDisplayable is null
     * IllegalArgumentException - if nextDisplayable
     *  is an AlertSee Also:Alert,
     * getCurrent()
     */
    public void setCurrent(Alert alert,
                       Displayable nextDisplayable);

    /**
     * Requests that the Displayable that contains this
     *  Item be made current,
     *  scrolls the Displayable so that this
     *  Item is visible, and possibly
     *  assigns the focus to this Item.  The containing
     *  Displayable is first
     *  made current as if setCurrent(Displayable) had been called.  When the containing
     *  Displayable becomes current, or if it is already
     *  current, it is
     *  scrolled if necessary so that the requested Item
     *  is made visible.
     *  Then, if the implementation supports the notion of input focus, and if
     *  the Item accepts the input focus, the input focus
     *  is assigned to the
     *  Item.
     * 
     *  This method always returns immediately, without waiting for the
     *  switching of the Displayable, the scrolling, and
     *  the assignment of
     *  input focus to take place.
     * 
     *  It is an error for the Item not to be contained
     *  within a container.
     *  It is also an error if the Item is contained
     *  within an Alert.
     * 
     * Parameters:item - the item that should be made visible
     * Throws:
     * IllegalStateException - if the item is not owned by a container
     * IllegalStateException - if the item is owned by an
     *  Alert
     * NullPointerException - if item is nullSince:
     *   MIDP 2.0
     */
    public void setCurrentItem(Item item);

    /**
     * Causes the Runnable object r to have
     *  its run() method
     *  called later, serialized with the event stream, soon after completion of
     *  the repaint cycle.  As noted in the
     *  Event Handling
     *  section of the package summary,
     *  the methods that deliver event notifications to the application
     *  are all called serially. The call to r.run() will
     *  be serialized along with
     *  the event calls into the application. The run()
     *  method will be called exactly once for each call to
     *  callSerially(). Calls to run() will
     *  occur in the order in which they were requested by calls to
     *  callSerially().
     * 
     *   If the current Displayable is a Canvas
     *  that has a repaint pending at the time of a call to
     *  callSerially(), the paint() method of the
     *  Canvas will be called and
     *  will return, and a buffer switch will occur (if double buffering is in
     *  effect), before the run() method of the
     *  Runnable is called.
     *  If the current Displayable contains one or more
     *  CustomItems that have repaints pending at the time
     *  of a call to callSerially(), the paint()
     *  methods of the CustomItems will be called and will
     *  return before the run() method of the
     *  Runnable is called.
     *  Calls to the
     *  run() method will occur in a timely fashion, but
     *  they are not guaranteed
     *  to occur immediately after the repaint cycle finishes, or even before
     *  the next event is delivered.
     * 
     *   The callSerially() method may be called from
     *  any thread. The call to
     *  the run() method will occur independently of the
     *  call to callSerially().
     *  In particular, callSerially() will never
     *  block waiting
     *  for r.run()
     *  to return.
     * 
     *   As with other callbacks, the call to r.run()
     *  must return quickly. If
     *  it is necessary to perform a long-running operation, it may be initiated
     *  from within the run() method. The operation itself
     *  should be performed
     *  within another thread, allowing run() to return.
     * 
     *   The callSerially() facility may be used by
     *  applications to run an
     *  animation that is properly synchronized with the repaint cycle. A
     *  typical application will set up a frame to be displayed and then call
     *  repaint().  The application must then wait until
     *  the frame is actually
     *  displayed, after which the setup for the next frame may occur.  The call
     *  to run() notifies the application that the
     *  previous frame has finished
     *  painting.  The example below shows callSerially()
     *  being used for this
     *  purpose.
     * 
     *      class Animation extends Canvas
     *          implements Runnable {
     * 
     *      // paint the current frame
     *      void paint(Graphics g) { ... }
     * 
     *         Display display; // the display for the application
     * 
     *         void paint(Graphics g) { ... } // paint the current frame
     * 
     *         void startAnimation() {
     *             // set up initial frame
     *             repaint();
     *             display.callSerially(this);
     *         }
     * 
     *         // called after previous repaint is finished
     *         void run() {
     *             if ( &#47;* there are more frames *&#47; ) {
     *                 // set up the next frame
     *                 repaint();
     *                 display.callSerially(this);
     *             }
     *         }
     *      }
     * 
     * Parameters:r - instance of interface Runnable to be called
     */
    public void callSerially(Runnable r);

    /**
     * Requests a flashing effect for the device's backlight.  The flashing
     *  effect is intended to be used to attract the user's attention or as a
     *  special effect for games.  Examples of flashing are cycling the
     *  backlight on and off or from dim to bright repeatedly.
     *  The return value indicates if the flashing of the backlight
     *  can be controlled by the application.
     * 
     *  The flashing effect occurs for the requested duration, or it is
     *  switched off if the requested duration is zero.  This method returns
     *  immediately; that is, it must not block the caller while the flashing
     *  effect is running.
     * 
     *  Calls to this method are honored only if the
     *  Display is in the
     *  foreground.  This method MUST perform no action
     *  and return false if the
     *  Display is in the background.
     * 
     *  The device MAY limit or override the duration. For devices
     *  that do not include a controllable backlight, calls to this
     *  method return false.
     * 
     * Parameters:duration - the number of milliseconds the backlight should be
     *  flashed, or zero if the flashing should be stopped
     * Returns:true if the backlight can be controlled
     *            by the application and this display is in the foreground,
     *           false otherwise
     * Throws:
     * IllegalArgumentException - if duration is negativeSince:
     *   MIDP 2.0
     */
    public boolean flashBacklight(int duration);

    /**
     * Requests operation of the device's vibrator.  The vibrator is
     *  intended to be used to attract the user's attention or as a
     *  special effect for games.  The return value indicates if the
     *  vibrator can be controlled by the application.
     * 
     *  This method switches on the vibrator for the requested
     *  duration, or switches it off if the requested duration is zero.
     *  If this method is called while the vibrator is still activated
     *  from a previous call, the request is interpreted as setting a
     *  new duration. It is not interpreted as adding additional time
     *  to the original request. This method returns immediately; that
     *  is, it must not block the caller while the vibrator is
     *  running.
     * 
     *  Calls to this method are honored only if the
     *  Display is in the foreground.  This method MUST
     *  perform no action and return false if the
     *  Display is in the background.
     * 
     *  The device MAY limit or override the duration.  For devices
     *  that do not include a controllable vibrator, calls to this
     *  method return false.
     * 
     * Parameters:duration - the number of milliseconds the vibrator should be run,
     *  or zero if the vibrator should be turned off
     * Returns:true if the vibrator can be controlled by the
     *            application and this display is in the foreground,
     *           false otherwise
     * Throws:
     * IllegalArgumentException - if duration is negativeSince:
     *   MIDP 2.0
     */
    public boolean vibrate(int duration);

    /**
     * Returns the best image width for a given image type.
     *  The image type must be one of
     *  LIST_ELEMENT,
     *  CHOICE_GROUP_ELEMENT, or
     *  ALERT.
     * 
     * Parameters:imageType - the image type
     * Returns:the best image width for the image type, may be zero if
     *  there is no best size; must not be negative
     * Throws:
     * IllegalArgumentException - if imageType is illegalSince:
     *   MIDP 2.0
     */
    public int getBestImageWidth(int imageType);

}

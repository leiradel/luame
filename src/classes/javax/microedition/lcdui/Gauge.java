package javax.microedition.lcdui;

public class Gauge extends Item {
    /**
     * A special value used for the maximum value in order to indicate that
     *  the Gauge has indefinite range.  This value may be
     *  used as the maxValue
     *  parameter to the constructor, the parameter passed to
     *  setMaxValue(), and
     *  as the return value of getMaxValue().
     * 
     *  The value of INDEFINITE is -1.
     * 
     * Since:
     *   MIDP 2.0
     * See Also:Constant Field Values
     */
    public static final int INDEFINITE = ;

    /**
     * The value representing the continuous-idle state of a
     *  non-interactive Gauge with indefinite range.  In
     *  the continuous-idle state, the gauge shows a graphic
     *  indicating that no work is in progress.
     * 
     *  This value has special meaning only for non-interactive
     *  gauges with indefinite range.  It is treated as an ordinary
     *  value for interactive gauges and for non-interactive gauges
     *  with definite range.
     * 
     *  The value of CONTINUOUS_IDLE is
     *  0.
     * 
     * Since:
     *   MIDP 2.0
     * See Also:Constant Field Values
     */
    public static final int CONTINUOUS_IDLE = 0;

    /**
     * The value representing the incremental-idle state of a
     *  non-interactive Gauge with indefinite range.  In
     *  the incremental-idle state, the gauge shows a graphic
     *  indicating that no work is in progress.
     * 
     *  This value has special meaning only for non-interactive
     *  gauges with indefinite range.  It is treated as an ordinary
     *  value for interactive gauges and for non-interactive gauges
     *  with definite range.
     * 
     *  The value of INCREMENTAL_IDLE is
     *  1.
     * 
     * Since:
     *   MIDP 2.0
     * See Also:Constant Field Values
     */
    public static final int INCREMENTAL_IDLE = 1;

    /**
     * The value representing the continuous-running state of a
     *  non-interactive Gauge with indefinite range.  In
     *  the continuous-running state, the gauge shows a
     *  continually-updating animation sequence that indicates that
     *  work is in progress.  Once the application sets a gauge into
     *  the continuous-running state, the animation should proceed
     *  without further requests from the application.
     * 
     *  This value has special meaning only for non-interactive
     *  gauges with indefinite range.  It is treated as an ordinary
     *  value for interactive gauges and for non-interactive gauges
     *  with definite range.
     * 
     *  The value of CONTINUOUS_RUNNING is
     *  2.
     * 
     * Since:
     *   MIDP 2.0
     * See Also:Constant Field Values
     */
    public static final int CONTINUOUS_RUNNING = 2;

    /**
     * The value representing the incremental-updating state of a
     *  non-interactive Gauge with indefinite range.  In
     *  the incremental-updating state, the gauge shows a graphic
     *  indicating that work is in progress, typically one frame of an
     *  animation sequence.  The graphic should be updated to the next
     *  frame in the sequence only when the application calls
     *  setValue(INCREMENTAL_UPDATING).
     * 
     *  This value has special meaning only for non-interactive
     *  gauges with indefinite range.  It is treated as an ordinary
     *  value for interactive gauges and for non-interactive gauges
     *  with definite range.
     * 
     *   The value of INCREMENTAL_UPDATING is
     *  3.
     * 
     * Since:
     *   MIDP 2.0
     * See Also:Constant Field Values
     */
    public static final int INCREMENTAL_UPDATING = 3;

    /**
     * Creates a new Gauge object with the given
     *  label, in interactive or non-interactive mode, with the given
     *  maximum and initial values.  In interactive mode (where
     *  interactive is true) the maximum
     *  value must be greater than zero, otherwise an exception is
     *  thrown.  In non-interactive mode (where
     *  interactive is false) the maximum
     *  value must be greater than zero or equal to the special value
     *  INDEFINITE, otherwise an exception is thrown.
     * 
     *  If the maximum value is greater than zero, the gauge has
     *  definite range.  In this case the initial value must be within
     *  the range zero to maxValue, inclusive.  If the
     *  initial value is less than zero, the value is set to zero.  If
     *  the initial value is greater than maxValue, it is
     *  set to maxValue.
     * 
     *  If interactive is false and the
     *  maximum value is INDEFINITE, this creates a
     *  non-interactive gauge with indefinite range. The initial value
     *  must be one of CONTINUOUS_IDLE,
     *  INCREMENTAL_IDLE, CONTINUOUS_RUNNING,
     *  or INCREMENTAL_UPDATING.
     * 
     * Parameters:label - the Gauge's labelinteractive - tells whether the user can change the valuemaxValue - the maximum value, or INDEFINITEinitialValue - the initial value in the range
     *  [0..maxValue], or one of CONTINUOUS_IDLE,
     *  INCREMENTAL_IDLE, CONTINUOUS_RUNNING,
     *  or INCREMENTAL_UPDATING if maxValue is
     *  INDEFINITE.
     * Throws:
     * IllegalArgumentException - if maxValue
     *  is not positive for interactive gauges
     * IllegalArgumentException - if maxValue is
     *  neither positive nor
     *  INDEFINITE for non-interactive gauges
     * IllegalArgumentException - if initialValue is not one of
     *  CONTINUOUS_IDLE, INCREMENTAL_IDLE,
     *  CONTINUOUS_RUNNING, or INCREMENTAL_UPDATING
     *  for a non-interactive gauge with indefinite rangeSee Also:INDEFINITE,
     * CONTINUOUS_IDLE,
     * INCREMENTAL_IDLE,
     * CONTINUOUS_RUNNING,
     * INCREMENTAL_UPDATING
     */
    public Gauge(String label, boolean interactive, int maxValue, int initialValue) {
        construct(label, interactive, maxValue, initialValue);
    }

    private native void construct(String label, boolean interactive, int maxValue, int initialValue);

    /**
     * Sets the label of the Item. If label
     *  is null, specifies that this item has no label.
     * 
     *  It is illegal to call this method if this Item
     *  is contained within  an Alert.
     * 
     * Overrides:setLabel in class Item
     * 
     * Parameters:label - the label string
     * Throws:
     * IllegalStateException - if this Item is contained
     *  within an AlertSee Also:Item.getLabel()
     */
    public void setLabel(String label);

    /**
     * Sets the layout directives for this item.
     * 
     *  It is illegal to call this method if this Item
     *  is contained within an Alert.
     * 
     * Overrides:setLayout in class Item
     * 
     * Parameters:layout - a combination of layout directive values for this item
     * Throws:
     * IllegalArgumentException - if the value of layout is not a valid
     *  combination of layout directives
     * IllegalStateException - if this Item is
     *  contained within an AlertSince:
     *   MIDP 2.0
     * See Also:Item.getLayout()
     */
    public void setLayout(int layout);

    /**
     * Adds a context sensitive Command to the item.
     *  The semantic type of
     *  Command should be ITEM. The implementation
     *  will present the command
     *  only when the the item is active, for example, highlighted.
     * 
     *  If the added command is already in the item (tested by comparing the
     *  object references), the method has no effect. If the item is
     *  actually visible on the display, and this call affects the set of
     *  visible commands, the implementation should update the display as soon
     *  as it is feasible to do so.
     * 
     *  It is illegal to call this method if this Item
     *  is contained within an Alert.
     * 
     * Overrides:addCommand in class Item
     * 
     * Parameters:cmd - the command to be added
     * Throws:
     * IllegalStateException - if this Item is contained
     *  within an Alert
     * NullPointerException - if cmd is nullSince:
     *   MIDP 2.0
     */
    public void addCommand(Command cmd);

    /**
     * Sets a listener for Commands to this Item,
     *  replacing any previous
     *  ItemCommandListener. A null reference
     *  is allowed and has the effect of
     *  removing any existing listener.
     * 
     *  It is illegal to call this method if this Item
     *  is contained within an Alert.
     * 
     * Overrides:setItemCommandListener in class Item
     * 
     * Parameters:l - the new listener, or null.
     * Throws:
     * IllegalStateException - if this Item is contained
     *  within an AlertSince:
     *   MIDP 2.0
     */
    public void setItemCommandListener(ItemCommandListener l);

    /**
     * Sets the preferred width and height for this Item.
     *  Values for width and height less than -1 are illegal.
     *  If the width is between zero and the minimum width, inclusive,
     *  the minimum width is used instead.
     *  If the height is between zero and the minimum height, inclusive,
     *  the minimum height is used instead.
     * 
     *  Supplying a width or height value greater than the minimum width or
     *  height locks that dimension to the supplied
     *  value.  The implementation may silently enforce a maximum dimension for
     *  an Item based on factors such as the screen size.
     *  Supplying a value of
     *  -1 for the width or height unlocks that dimension.
     *  See Item Sizes for a complete discussion.
     * 
     *  It is illegal to call this method if this Item
     *  is contained within  an Alert.
     * 
     * Overrides:setPreferredSize in class Item
     * 
     * Parameters:width - the value to which the width should be locked, or
     *  -1 to unlockheight - the value to which the height should be locked, or
     *  -1 to unlock
     * Throws:
     * IllegalArgumentException - if width or height is less than
     *  -1
     * IllegalStateException - if this Item is contained
     *  within an AlertSince:
     *   MIDP 2.0
     * See Also:Item.getPreferredHeight(),
     * Item.getPreferredWidth()
     */
    public void setPreferredSize(int width, int height);

    /**
     * Sets default Command for this Item.
     *  If the Item previously had a
     *  default Command, that Command
     *  is no longer the default, but it
     *  remains present on the Item.
     * 
     *  If not null, the Command object
     *  passed becomes the default Command
     *  for this Item.  If the Command object
     *  passed is not currently present
     *  on this Item, it is added as if addCommand(javax.microedition.lcdui.Command)
     *  had been called
     *  before it is made the default Command.
     * 
     *  If null is passed, the Item is set to
     *  have no default Command.
     *  The previous default Command, if any, remains present
     *  on the Item.
     * 
     *  It is illegal to call this method if this Item
     *  is contained within  an Alert.
     * 
     * Overrides:setDefaultCommand in class Item
     * 
     * Parameters:cmd - the command to be used as this Item's default
     *  Command, or null if there is to
     *  be no default command
     * Throws:
     * IllegalStateException - if this Item is contained
     *  within an AlertSince:
     *   MIDP 2.0
     */
    public void setDefaultCommand(Command cmd);

    /**
     * Sets the current value of this Gauge object.
     * 
     *  If the gauge is interactive, or if it is non-interactive with
     *  definite range, the following rules apply.  If the value is less than
     *  zero, zero is used. If the current value is greater than the maximum
     *  value, the current value is set to be equal to the maximum value.
     * 
     *   If this Gauge object is a non-interactive
     *  gauge with indefinite
     *  range, then value must be one of CONTINUOUS_IDLE,
     *  INCREMENTAL_IDLE, CONTINUOUS_RUNNING, or
     *  INCREMENTAL_UPDATING.
     *  Other values will cause an exception to be thrown.
     * 
     * Parameters:value - the new value
     * Throws:
     * IllegalArgumentException - if value is not one of
     *  CONTINUOUS_IDLE,  INCREMENTAL_IDLE,
     *  CONTINUOUS_RUNNING, or INCREMENTAL_UPDATING
     *  for non-interactive gauges with indefinite rangeSee Also:CONTINUOUS_IDLE,
     * INCREMENTAL_IDLE,
     * CONTINUOUS_RUNNING,
     * INCREMENTAL_UPDATING,
     * getValue()
     */
    public void setValue(int value);

    /**
     * Gets the current value of this Gauge object.
     * 
     *   If this Gauge object is a non-interactive
     *  gauge with indefinite
     *  range, the value returned will be one of CONTINUOUS_IDLE,
     *  INCREMENTAL_IDLE, CONTINUOUS_RUNNING, or
     *  INCREMENTAL_UPDATING.  Otherwise, it will be an integer
     *  between zero and the gauge's maximum value, inclusive.
     * 
     * Returns:current value of the GaugeSee Also:CONTINUOUS_IDLE,
     * INCREMENTAL_IDLE,
     * CONTINUOUS_RUNNING,
     * INCREMENTAL_UPDATING,
     * setValue(int)
     */
    public int getValue();

    /**
     * Sets the maximum value of this Gauge object.
     * 
     *  For interactive gauges, the new maximum value must be greater than
     *  zero, otherwise an exception is thrown.  For non-interactive gauges,
     *  the new maximum value must be greater than zero or equal to the special
     *  value INDEFINITE, otherwise an exception is thrown.
     * 
     *  If the new maximum value is greater than zero, this provides the
     *  gauge with a definite range.  If the gauge previously had a definite
     *  range, and if the current value is greater than new maximum value, the
     *  current value is set to be equal to the new maximum value.  If the
     *  gauge previously had a definite range, and if the current value is less
     *  than or equal to the new maximum value, the current value is left
     *  unchanged.
     * 
     *  If the new maximum value is greater than zero, and if the gauge had
     *  previously had indefinite range, this new maximum value provides it
     *  with a definite range.  Its graphical representation must change
     *  accordingly, the previous state of CONTINUOUS_IDLE,
     *  INCREMENTAL_IDLE, CONTINUOUS_RUNNING, or
     *  INCREMENTAL_UPDATING is ignored, and the current value
     *  is set to zero.
     * 
     *  If this gauge is non-interactive and the new maximum value is
     *  INDEFINITE, this gives the gauge indefinite range.
     *  If the gauge
     *  previously had a definite range, its graphical representation must
     *  change accordingly, the previous value is ignored, and the current
     *  state is set to CONTINUOUS_IDLE.  If the gauge previously
     *  had an indefinite range, setting the maximum value to
     *  INDEFINITE will have no effect.
     * 
     * Parameters:maxValue - the new maximum value
     * Throws:
     * IllegalArgumentException - if maxValue is invalidSee Also:INDEFINITE,
     * getMaxValue()
     */
    public void setMaxValue(int maxValue);

    /**
     * Gets the maximum value of this Gauge object.
     * 
     *  If this gauge is interactive, the maximum value will be a positive
     *  integer.  If this gauge is non-interactive, the maximum value will be a
     *  positive integer (indicating that the gauge has definite range)
     *  or the special value INDEFINITE (indicating that
     *  the gauge has
     *  indefinite range).
     * 
     * Returns:the maximum value of the Gauge, or
     *  INDEFINITESee Also:INDEFINITE,
     * setMaxValue(int)
     */
    public int getMaxValue();

    /**
     * Tells whether the user is allowed to change the value of the
     *  Gauge.
     * 
     * Returns:a boolean indicating whether the Gauge is
     *  interactive
     */
    public boolean isInteractive();

}

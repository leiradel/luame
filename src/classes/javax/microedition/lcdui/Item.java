package javax.microedition.lcdui;

public class Item {
    /**
     * A layout directive indicating that this Item
     *  should follow the default layout policy of its container.
     * 
     *  Value 0 is assigned to LAYOUT_DEFAULT.
     * 
     * Since:
     *   MIDP 2.0
     * See Also:Constant Field Values
     */
    public static final int LAYOUT_DEFAULT = ;

    /**
     * A layout directive indicating that this Item should have a
     *  left-aligned layout.
     * 
     *  Value 1 is assigned to LAYOUT_LEFT.
     * 
     * Since:
     *   MIDP 2.0
     * See Also:Constant Field Values
     */
    public static final int LAYOUT_LEFT = ;

    /**
     * A layout directive indicating that this Item should have a
     *  right-aligned layout.
     * 
     *  Value 2 is assigned to LAYOUT_RIGHT.
     * 
     * Since:
     *   MIDP 2.0
     * See Also:Constant Field Values
     */
    public static final int LAYOUT_RIGHT = ;

    /**
     * A layout directive indicating that this Item should have a
     *  horizontally centered layout.
     * 
     *  Value 3 is assigned to LAYOUT_CENTER.
     * 
     * Since:
     *   MIDP 2.0
     * See Also:Constant Field Values
     */
    public static final int LAYOUT_CENTER = ;

    /**
     * A layout directive indicating that this Item should have a
     *  top-aligned layout.
     * 
     *  Value 0x10 is assigned to LAYOUT_TOP.
     * 
     * Since:
     *   MIDP 2.0
     * See Also:Constant Field Values
     */
    public static final int LAYOUT_TOP = ;

    /**
     * A layout directive indicating that this Item should have a
     *  bottom-aligned layout.
     * 
     *  Value 0x20 is assigned to LAYOUT_BOTTOM.
     * 
     * Since:
     *   MIDP 2.0
     * See Also:Constant Field Values
     */
    public static final int LAYOUT_BOTTOM = ;

    /**
     * A layout directive indicating that this Item should have a
     *  vertically centered layout.
     * 
     *  Value 0x30 is assigned to
     *  LAYOUT_VCENTER.
     * 
     * Since:
     *   MIDP 2.0
     * See Also:Constant Field Values
     */
    public static final int LAYOUT_VCENTER = ;

    /**
     * A layout directive indicating that this Item
     *  should be placed at the beginning of a new line or row.
     * 
     *  Value 0x100 is assigned to
     *  LAYOUT_NEWLINE_BEFORE.
     * 
     * Since:
     *   MIDP 2.0
     * See Also:Constant Field Values
     */
    public static final int LAYOUT_NEWLINE_BEFORE = ;

    /**
     * A layout directive indicating that this Item
     *  should the last on its line or row, and that the next
     *  Item (if any) in the container
     *  should be placed on a new line or row.
     * 
     *  Value 0x200 is assigned to
     *  LAYOUT_NEWLINE_AFTER.
     * 
     * Since:
     *   MIDP 2.0
     * See Also:Constant Field Values
     */
    public static final int LAYOUT_NEWLINE_AFTER = ;

    /**
     * A layout directive indicating that this Item's
     *  width may be reduced to its minimum width.
     * 
     * Value 0x400 is assigned to LAYOUT_SHRINK
     * 
     * Since:
     *   MIDP 2.0
     * See Also:Constant Field Values
     */
    public static final int LAYOUT_SHRINK = ;

    /**
     * A layout directive indicating that this Item's
     *  width may be increased to fill available space.
     * 
     * Value 0x800 is assigned to LAYOUT_EXPAND.
     * 
     * Since:
     *   MIDP 2.0
     * See Also:Constant Field Values
     */
    public static final int LAYOUT_EXPAND = ;

    /**
     * A layout directive indicating that this Item's
     *  height may be reduced to its minimum height.
     * 
     *  Value 0x1000 is assigned to
     *  LAYOUT_VSHRINK.
     * 
     * Since:
     *   MIDP 2.0
     * See Also:Constant Field Values
     */
    public static final int LAYOUT_VSHRINK = ;

    /**
     * A layout directive indicating that this Item's
     *  height may be increased to fill available space.
     * 
     *  Value 0x2000 is assigned to
     *  LAYOUT_VEXPAND.
     * 
     * Since:
     *   MIDP 2.0
     * See Also:Constant Field Values
     */
    public static final int LAYOUT_VEXPAND = ;

    /**
     * A layout directive indicating that new MIDP 2.0 layout
     *  rules are in effect for this Item.  If this
     *  bit is clear, indicates that MIDP 1.0 layout behavior
     *  applies to this Item.
     * 
     *  Value 0x4000 is assigned to
     *  LAYOUT_2.
     * 
     * Since:
     *   MIDP 2.0
     * See Also:Constant Field Values
     */
    public static final int LAYOUT_2 = ;

    /**
     * An appearance mode value indicating that the Item is to have
     *  a normal appearance.
     * 
     *  Value 0 is assigned to PLAIN.
     * 
     * Since:
     *   MIDP 2.0
     * See Also:Constant Field Values
     */
    public static final int PLAIN = 0;

    /**
     * An appearance mode value indicating that the Item
     *  is to appear as a hyperlink.
     *  Value 1 is assigned to HYPERLINK.
     * 
     * Since:
     *   MIDP 2.0
     * See Also:Constant Field Values
     */
    public static final int HYPERLINK = 1;

    /**
     * An appearance mode value indicating that the Item
     *  is to appear as a button.
     *  Value 2 is assigned to BUTTON.
     * 
     * Since:
     *   MIDP 2.0
     * See Also:Constant Field Values
     */
    public static final int BUTTON = 2;

    /**
     * Sets the label of the Item. If label
     *  is null, specifies that this item has no label.
     * 
     *  It is illegal to call this method if this Item
     *  is contained within  an Alert.
     * 
     * Parameters:label - the label string
     * Throws:
     * IllegalStateException - if this Item is contained
     *  within an AlertSee Also:getLabel()
     */
    public void setLabel(String label);

    /**
     * Gets the label of this Item object.
     * 
     * Returns:the label stringSee Also:setLabel(java.lang.String)
     */
    public String getLabel();

    /**
     * Gets the layout directives used for placing the item.
     * 
     * Returns:a combination of layout directive valuesSince:
     *   MIDP 2.0
     * See Also:setLayout(int)
     */
    public int getLayout();

    /**
     * Sets the layout directives for this item.
     * 
     *  It is illegal to call this method if this Item
     *  is contained within an Alert.
     * 
     * Parameters:layout - a combination of layout directive values for this item
     * Throws:
     * IllegalArgumentException - if the value of layout is not a
     *  bit-wise OR combination of layout directives
     * IllegalStateException - if this Item is
     *  contained within an AlertSince:
     *   MIDP 2.0
     * See Also:getLayout()
     */
    public void setLayout(int layout);

    /**
     * Adds a context sensitive Command to the item.
     *  The semantic type of
     *  Command should be ITEM. The implementation
     *  will present the command
     *  only when the item is active, for example, highlighted.
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
     * Parameters:cmd - the command to be added
     * Throws:
     * IllegalStateException - if this Item is contained
     *  within an Alert
     * NullPointerException - if cmd is nullSince:
     *   MIDP 2.0
     */
    public void addCommand(Command cmd);

    /**
     * Removes the context sensitive command from item. If the command is not
     *  in the Item (tested by comparing the object references),
     *  the method has
     *  no effect. If the Item is actually visible on the display,
     *  and this  call
     *  affects the set of visible commands, the implementation should update
     *  the display as soon as it is feasible to do so.
     * 
     *  If the command to be removed happens to be the default command,
     *  the command is removed and the default command on this Item is
     *  set to null.
     * 
     *  The following code:
     * 
     *      // Command c is the default command on Item item
     *      item.removeCommand(c);
     * 
     *  is equivalent to the following code:
     * 
     *      // Command c is the default command on Item item
     *      item.setDefaultCommand(null);
     *      item.removeCommand(c);
     * 
     * Parameters:cmd - the command to be removedSince:
     *   MIDP 2.0
     */
    public void removeCommand(Command cmd);

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
     * Parameters:l - the new listener, or null.
     * Throws:
     * IllegalStateException - if this Item is contained
     *  within an AlertSince:
     *   MIDP 2.0
     */
    public void setItemCommandListener(ItemCommandListener l);

    /**
     * Gets the preferred width of this Item.
     *  If the application has locked
     *  the width to a specific value, this method returns that value.
     *  Otherwise, the return value is computed based on the
     *  Item's contents,
     *  possibly with respect to the Item's preferred height
     *  if it is locked.
     *  See Item Sizes for a complete discussion.
     * 
     * Returns:the preferred width of the ItemSince:
     *   MIDP 2.0
     * See Also:getPreferredHeight(),
     * setPreferredSize(int, int)
     */
    public int getPreferredWidth();

    /**
     * Gets the preferred height of this Item.
     *  If the application has locked
     *  the height to a specific value, this method returns that value.
     *  Otherwise, the return value is computed based on the
     *  Item's contents,
     *  possibly with respect to the Item's preferred
     *  width if it is locked.
     *  See Item Sizes for a complete discussion.
     * 
     * Returns:the preferred height of the ItemSince:
     *   MIDP 2.0
     * See Also:getPreferredWidth(),
     * setPreferredSize(int, int)
     */
    public int getPreferredHeight();

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
     * Parameters:width - the value to which the width should be locked, or
     *  -1 to unlockheight - the value to which the height should be locked, or
     *  -1 to unlock
     * Throws:
     * IllegalArgumentException - if width or height is less than
     *  -1
     * IllegalStateException - if this Item is contained
     *  within an AlertSince:
     *   MIDP 2.0
     * See Also:getPreferredHeight(),
     * getPreferredWidth()
     */
    public void setPreferredSize(int width,
                             int height);

    /**
     * Gets the minimum width for this Item.  This is a width
     *  at which the item can function and display its contents,
     *  though perhaps not optimally.
     *  See Item Sizes for a complete discussion.
     * 
     * Returns:the minimum width of the itemSince:
     *   MIDP 2.0
     */
    public int getMinimumWidth();

    /**
     * Gets the minimum height for this Item.  This is a height
     *  at which the item can function and display its contents,
     *  though perhaps not optimally.
     *  See Item Sizes for a complete discussion.
     * 
     * Returns:the minimum height of the itemSince:
     *   MIDP 2.0
     */
    public int getMinimumHeight();

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
     * Parameters:cmd - the command to be used as this Item's default
     *  Command, or null if there is to
     *  be no default command
     * Throws:
     * IllegalStateException - if this Item is contained
     *  within an AlertSince:
     *   MIDP 2.0
     */
    public void setDefaultCommand(Command cmd);

}

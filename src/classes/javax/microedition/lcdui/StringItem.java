package javax.microedition.lcdui;

public class StringItem extends Item {
    /**
     * Creates a new StringItem object.  Calling this
     *  constructor is equivalent to calling
     * 
     *      StringItem(label, text, PLAIN);
     * 
     * Parameters:label - the Item labeltext - the text contentsSee Also:StringItem(String, String, int)
     */
    public StringItem(String label, String text) {
        construct(label, text);
    }

    private native void construct(String label, String text);

    /**
     * Creates a new StringItem object with the given label,
     *  textual content, and appearance mode.
     *  Either label or text may be present or null.
     * 
     *  The appearanceMode parameter
     *  (see Appearance Modes)
     *  is a hint to the platform of the application's intended use
     *  for this StringItem.  To provide hyperlink- or
     *  button-like behavior,
     *  the application should associate a default Command with this
     *  StringItem and add an
     *  ItemCommandListener to this
     *  StringItem.
     * 
     *  Here is an example showing the use of a
     *  StringItem as a button:
     * 
     *      StringItem strItem =
     *          new StringItem("Default: ", "Set",
     *                         Item.BUTTON);
     *      strItem.setDefaultCommand(
     *          new Command("Set", Command.ITEM, 1);
     *      // icl is ItemCommandListener
     *      strItem.setItemCommandListener(icl);
     * 
     * Parameters:label - the StringItem's label, or null
     *  if no labeltext - the StringItem's text contents, or
     *  null if the contents are initially emptyappearanceMode - the appearance mode of the StringItem,
     *  one of Item.PLAIN, Item.HYPERLINK, or Item.BUTTON
     * Throws:
     * IllegalArgumentException - if appearanceMode invalidSince:
     *   MIDP 2.0
     */
    public StringItem(String label, String text, int appearanceMode) {
        construct(label, text, appearanceMode);
    }

    private native void construct(String label, String text, int appearanceMode);

    /**
     * Gets the text contents of the StringItem, or
     *  null if the StringItem is
     *  empty.
     * 
     * Returns:a string with the content of the itemSee Also:setText(java.lang.String)
     */
    public String getText();

    /**
     * Sets the text contents of the StringItem. If text
     *  is null,
     *  the StringItem
     *  is set to be empty.
     * 
     * Parameters:text - the new contentSee Also:getText()
     */
    public void setText(String text);

    /**
     * Returns the appearance mode of the StringItem.
     *  See Appearance Modes.
     * 
     * Returns:the appearance mode value,
     *  one of Item.PLAIN, Item.HYPERLINK, or Item.BUTTONSince:
     *   MIDP 2.0
     */
    public int getAppearanceMode();

    /**
     * Sets the application's preferred font for
     *  rendering this StringItem.
     *  The font is a hint, and the implementation may disregard
     *  the application's preferred font.
     * 
     *   The font parameter must be a valid Font
     *  object or null. If the font parameter is
     *  null, the implementation must use its default font
     *  to render the StringItem.
     * 
     * Parameters:font - the preferred font to use to render this
     *              StringItemSince:
     *   MIDP 2.0
     * See Also:getFont()
     */
    public void setFont(Font font);

    /**
     * Gets the application's preferred font for
     *  rendering this StringItem. The
     *  value returned is the font that had been set by the application,
     *  even if that value had been disregarded by the implementation.
     *  If no font had been set by the application, or if the application
     *  explicitly set the font to null, the value is the default
     *  font chosen by the implementation.
     * 
     * Returns:the preferred font to use to render this
     *          StringItemSince:
     *   MIDP 2.0
     * See Also:setFont(javax.microedition.lcdui.Font)
     */
    public Font getFont();

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

}

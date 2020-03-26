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
    public StringItem(String label,
                  String text) {
        construct(label, text);
    }

    private native void construct(String label,
                  String text);

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

}

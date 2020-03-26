package javax.microedition.lcdui;

public class ImageItem extends Item {
    /**
     * See Item.LAYOUT_DEFAULT.
     * 
     *  Value 0 is assigned to LAYOUT_DEFAULT.
     * 
     * See Also:Constant Field Values
     */
    public static final int LAYOUT_DEFAULT = ;

    /**
     * See Item.LAYOUT_LEFT.
     * 
     *  Value 1 is assigned to LAYOUT_LEFT.
     * 
     * See Also:Constant Field Values
     */
    public static final int LAYOUT_LEFT = ;

    /**
     * See Item.LAYOUT_RIGHT.
     * 
     *  Value 2 is assigned to LAYOUT_RIGHT.
     * 
     * See Also:Constant Field Values
     */
    public static final int LAYOUT_RIGHT = ;

    /**
     * See Item.LAYOUT_CENTER.
     * 
     *  Value 3 is assigned to LAYOUT_CENTER.
     * 
     * See Also:Constant Field Values
     */
    public static final int LAYOUT_CENTER = ;

    /**
     * See Item.LAYOUT_NEWLINE_BEFORE.
     * 
     *  Value 0x100 is assigned to
     *  LAYOUT_NEWLINE_BEFORE.
     * 
     * See Also:Constant Field Values
     */
    public static final int LAYOUT_NEWLINE_BEFORE = ;

    /**
     * See Item.LAYOUT_NEWLINE_AFTER.
     * 
     *  Value 0x200 is assigned to
     *  LAYOUT_NEWLINE_AFTER.
     * 
     * See Also:Constant Field Values
     */
    public static final int LAYOUT_NEWLINE_AFTER = ;

    /**
     * Creates a new ImageItem with the given label, image, layout
     *  directive, and alternate text string.  Calling this constructor is
     *  equivalent to calling
     * 
     *     ImageItem(label, image, layout, altText, PLAIN);
     * 
     * Parameters:label - the label stringimg - the image, can be mutable or immutablelayout - a combination of layout directivesaltText - the text that may be used in place of the image
     * Throws:
     * IllegalArgumentException - if the layout value is not
     *  a legal combination of directivesSee Also:ImageItem(String, Image, int, String, int)
     */
    public ImageItem(String label,
                 Image img,
                 int layout,
                 String altText) {
        construct(label, img, layout, altText);
    }

    private native void construct(String label,
                 Image img,
                 int layout,
                 String altText);

    /**
     * Gets the image contained within the ImageItem, or
     *  null if there is no
     *  contained image.
     * 
     * Returns:image used by the ImageItemSee Also:setImage(javax.microedition.lcdui.Image)
     */
    public Image getImage();

    /**
     * Sets the Image object contained within the
     *  ImageItem.  The image may be
     *  mutable or immutable.  If img is
     *  null, the ImageItem is set to be
     *  empty.  If img is mutable, the effect is as if a
     *  snapshot is taken of
     *  img's contents immediately prior to the call to
     *  setImage.  This
     *  snapshot is used whenever the contents of the
     *  ImageItem are to be
     *  displayed.  If img is already the
     *  Image of this ImageItem, the effect
     *  is as if a new snapshot of img's contents is taken.  Thus, after
     *  painting into a mutable image contained by an
     *  ImageItem, the
     *  application can call
     * 
     *     imageItem.setImage(imageItem.getImage());
     * 
     *  to refresh the ImageItem's snapshot of its Image.
     * 
     *  If the ImageItem is visible on the display when
     *  the snapshot is
     *  updated through a call to setImage, the display is
     *  updated with the new
     *  snapshot as soon as it is feasible for the implementation to so do.
     * 
     * Parameters:img - the Image for this
     *  ImageItem, or null if noneSee Also:getImage()
     */
    public void setImage(Image img);

    /**
     * Gets the text string to be used if the image exceeds the device's
     *  capacity to display it.
     * 
     * Returns:the alternate text value, or null if noneSee Also:setAltText(java.lang.String)
     */
    public String getAltText();

    /**
     * Sets the alternate text of the ImageItem, or
     *  null if no alternate text is provided.
     * 
     * Parameters:text - the new alternate textSee Also:getAltText()
     */
    public void setAltText(String text);

    /**
     * Gets the layout directives used for placing the image.
     * 
     * Overrides:getLayout in class Item
     * 
     * Returns:a combination of layout directive valuesSee Also:setLayout(int)
     */
    public int getLayout();

    /**
     * Sets the layout directives.
     * 
     * Overrides:setLayout in class Item
     * 
     * Parameters:layout - a combination of layout directive values
     * Throws:
     * IllegalArgumentException - if the value of layout
     *  is not a valid
     *  combination of layout directivesSee Also:getLayout()
     */
    public void setLayout(int layout);

}

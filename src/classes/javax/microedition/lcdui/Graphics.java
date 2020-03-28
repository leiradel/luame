package javax.microedition.lcdui;

public class Graphics {
    /**
     * Constant for centering text and images horizontally
     *  around the anchor point
     * 
     *  Value 1 is assigned to HCENTER.
     * 
     * See Also:Constant Field Values
     */
    public static final int HCENTER = ;

    /**
     * Constant for centering images vertically
     *  around the anchor point.
     * 
     *  Value 2 is assigned to VCENTER.
     * 
     * See Also:Constant Field Values
     */
    public static final int VCENTER = ;

    /**
     * Constant for positioning the anchor point of text and images
     *  to the left of the text or image.
     * 
     *  Value 4 is assigned to LEFT.
     * 
     * See Also:Constant Field Values
     */
    public static final int LEFT = ;

    /**
     * Constant for positioning the anchor point of text and images
     *  to the right of the text or image.
     * 
     *  Value 8 is assigned to RIGHT.
     * 
     * See Also:Constant Field Values
     */
    public static final int RIGHT = ;

    /**
     * Constant for positioning the anchor point of text and images
     *  above the text or image.
     * 
     *  Value 16 is assigned to TOP.
     * 
     * See Also:Constant Field Values
     */
    public static final int TOP = ;

    /**
     * Constant for positioning the anchor point of text and images
     *  below the text or image.
     * 
     *  Value 32 is assigned to BOTTOM.
     * 
     * See Also:Constant Field Values
     */
    public static final int BOTTOM = ;

    /**
     * Constant for positioning the anchor point at the baseline of text.
     * 
     *  Value 64 is assigned to BASELINE.
     * 
     * See Also:Constant Field Values
     */
    public static final int BASELINE = ;

    /**
     * Constant for the SOLID stroke style.
     * 
     *  Value 0 is assigned to SOLID.
     * 
     * See Also:Constant Field Values
     */
    public static final int SOLID = ;

    /**
     * Constant for the DOTTED stroke style.
     * 
     *  Value 1 is assigned to DOTTED.
     * 
     * See Also:Constant Field Values
     */
    public static final int DOTTED = ;

    /**
     * Translates the origin of the graphics context to the point
     *  (x, y) in the current coordinate system. All coordinates
     *  used in subsequent rendering operations on this graphics
     *  context will be relative to this new origin.
     * 
     *  The effect of calls to translate() are
     *  cumulative. For example, calling
     *  translate(1, 2) and then translate(3,
     *  4) results in a translation of
     *  (4, 6).
     * 
     *  The application can set an absolute origin (ax,
     *  ay) using the following
     *  technique:
     * 
     *  g.translate(ax - g.getTranslateX(), ay - g.getTranslateY())
     * 
     * Parameters:x - the x coordinate of the new translation originy - the y coordinate of the new translation originSee Also:getTranslateX(),
     * getTranslateY()
     */
    public void translate(int x, int y);

    /**
     * Gets the X coordinate of the translated origin of this graphics context.
     * 
     * Returns:X of current origin
     */
    public int getTranslateX();

    /**
     * Gets the Y coordinate of the translated origin of this graphics context.
     * 
     * Returns:Y of current origin
     */
    public int getTranslateY();

    /**
     * Gets the current color.
     * 
     * Returns:an integer in form 0x00RRGGBBSee Also:setColor(int, int, int)
     */
    public int getColor();

    /**
     * Gets the red component of the current color.
     * 
     * Returns:integer value in range 0-255See Also:setColor(int, int, int)
     */
    public int getRedComponent();

    /**
     * Gets the green component of the current color.
     * 
     * Returns:integer value in range 0-255See Also:setColor(int, int, int)
     */
    public int getGreenComponent();

    /**
     * Gets the blue component of the current color.
     * 
     * Returns:integer value in range 0-255See Also:setColor(int, int, int)
     */
    public int getBlueComponent();

    /**
     * Gets the current grayscale value of the color being used for rendering
     *  operations. If the color was set by
     *  setGrayScale(), that value is simply
     *  returned. If the color was set by one of the methods that allows setting
     *  of the red, green, and blue components, the value returned is
     *  computed from
     *  the RGB color components (possibly in a device-specific fashion)
     *  that best
     *  approximates the brightness of that color.
     * 
     * Returns:integer value in range 0-255See Also:setGrayScale(int)
     */
    public int getGrayScale();

    /**
     * Sets the current color to the specified RGB values. All subsequent
     *  rendering operations will use this specified color.
     * 
     * Parameters:red - the red component of the color being set in range
     *  0-255green - the green component of the color being set in range
     *  0-255blue - the blue component of the color being set in range
     *  0-255
     * Throws:
     * IllegalArgumentException - if any of the color components
     *  are outside of range 0-255See Also:getColor()
     */
    public void setColor(int red, int green, int blue);

    /**
     * Sets the current color to the specified RGB values. All subsequent
     *  rendering operations will use this specified color. The RGB value
     *  passed in is interpreted with the least significant eight bits
     *  giving the blue component, the next eight more significant bits
     *  giving the green component, and the next eight more significant
     *  bits giving the red component. That is to say, the color component
     *  is specified in the form of 0x00RRGGBB. The high
     *  order byte of
     *  this value is ignored.
     * 
     * Parameters:RGB - the color being setSee Also:getColor()
     */
    public void setColor(int RGB);

    /**
     * Sets the current grayscale to be used for all subsequent
     *  rendering operations. For monochrome displays, the behavior
     *  is clear. For color displays, this sets the color for all
     *  subsequent drawing operations to be a gray color equivalent
     *  to the value passed in. The value must be in the range
     *  0-255.
     * 
     * Parameters:value - the desired grayscale value
     * Throws:
     * IllegalArgumentException - if the gray value is out of rangeSee Also:getGrayScale()
     */
    public void setGrayScale(int value);

    /**
     * Gets the current font.
     * 
     * Returns:current fontSee Also:Font,
     * setFont(javax.microedition.lcdui.Font)
     */
    public Font getFont();

    /**
     * Sets the stroke style used for drawing lines, arcs, rectangles, and
     *  rounded rectangles.  This does not affect fill, text, and image
     *  operations.
     * 
     * Parameters:style - can be SOLID or DOTTED
     * Throws:
     * IllegalArgumentException - if the style is illegalSee Also:getStrokeStyle()
     */
    public void setStrokeStyle(int style);

    /**
     * Gets the stroke style used for drawing operations.
     * 
     * Returns:stroke style, SOLID or DOTTEDSee Also:setStrokeStyle(int)
     */
    public int getStrokeStyle();

    /**
     * Sets the font for all subsequent text rendering operations.  If font is
     *  null, it is equivalent to
     *  setFont(Font.getDefaultFont()).
     * 
     * Parameters:font - the specified fontSee Also:Font,
     * getFont(),
     * drawString(java.lang.String, int, int, int),
     * drawChars(char[], int, int, int, int, int)
     */
    public void setFont(Font font);

    /**
     * Gets the X offset of the current clipping area, relative
     *  to the coordinate system origin of this graphics context.
     *  Separating the getClip operation into two methods returning
     *  integers is more performance and memory efficient than one
     *  getClip() call returning an object.
     * 
     * Returns:X offset of the current clipping areaSee Also:clipRect(int, int, int, int),
     * setClip(int, int, int, int)
     */
    public int getClipX();

    /**
     * Gets the Y offset of the current clipping area, relative
     *  to the coordinate system origin of this graphics context.
     *  Separating the getClip operation into two methods returning
     *  integers is more performance and memory efficient than one
     *  getClip() call returning an object.
     * 
     * Returns:Y offset of the current clipping areaSee Also:clipRect(int, int, int, int),
     * setClip(int, int, int, int)
     */
    public int getClipY();

    /**
     * Gets the width of the current clipping area.
     * 
     * Returns:width of the current clipping area.See Also:clipRect(int, int, int, int),
     * setClip(int, int, int, int)
     */
    public int getClipWidth();

    /**
     * Gets the height of the current clipping area.
     * 
     * Returns:height of the current clipping area.See Also:clipRect(int, int, int, int),
     * setClip(int, int, int, int)
     */
    public int getClipHeight();

    /**
     * Intersects the current clip with the specified rectangle.
     *  The resulting clipping area is the intersection of the current
     *  clipping area and the specified rectangle.
     *  This method can only be used to make the current clip smaller.
     *  To set the current clip larger, use the setClip method.
     *  Rendering operations have no effect outside of the clipping area.
     * 
     * Parameters:x - the x coordinate of the rectangle to intersect the clip withy - the y coordinate of the rectangle to intersect the clip withwidth - the width of the rectangle to intersect the clip withheight - the height of the rectangle to intersect the clip withSee Also:setClip(int, int, int, int)
     */
    public void clipRect(int x, int y, int width, int height);

    /**
     * Sets the current clip to the rectangle specified by the
     *  given coordinates.
     *  Rendering operations have no effect outside of the clipping area.
     * 
     * Parameters:x - the x coordinate of the new clip rectangley - the y coordinate of the new clip rectanglewidth - the width of the new clip rectangleheight - the height of the new clip rectangleSee Also:clipRect(int, int, int, int)
     */
    public void setClip(int x, int y, int width, int height);

    /**
     * Draws a line between the coordinates (x1,y1) and
     *  (x2,y2) using
     *  the current color and stroke style.
     * 
     * Parameters:x1 - the x coordinate of the start of the liney1 - the y coordinate of the start of the linex2 - the x coordinate of the end of the liney2 - the y coordinate of the end of the line
     */
    public void drawLine(int x1, int y1, int x2, int y2);

    /**
     * Fills the specified rectangle with the current color.
     *  If either width or height is zero or less,
     *  nothing is drawn.
     * 
     * Parameters:x - the x coordinate of the rectangle to be filledy - the y coordinate of the rectangle to be filledwidth - the width of the rectangle to be filledheight - the height of the rectangle to be filledSee Also:drawRect(int, int, int, int)
     */
    public void fillRect(int x, int y, int width, int height);

    /**
     * Draws the outline of the specified rectangle using the current
     *  color and stroke style.
     *  The resulting rectangle will cover an area (width + 1)
     *  pixels wide by (height + 1) pixels tall.
     *  If either width or height is less than
     *  zero, nothing is drawn.
     * 
     * Parameters:x - the x coordinate of the rectangle to be drawny - the y coordinate of the rectangle to be drawnwidth - the width of the rectangle to be drawnheight - the height of the rectangle to be drawnSee Also:fillRect(int, int, int, int)
     */
    public void drawRect(int x, int y, int width, int height);

    /**
     * Draws the outline of the specified rounded corner rectangle
     *  using the current color and stroke style.
     *  The resulting rectangle will cover an area (width +
     *  1) pixels wide
     *  by (height + 1) pixels tall.
     *  If either width or height is less than
     *  zero, nothing is drawn.
     * 
     * Parameters:x - the x coordinate of the rectangle to be drawny - the y coordinate of the rectangle to be drawnwidth - the width of the rectangle to be drawnheight - the height of the rectangle to be drawnarcWidth - the horizontal diameter of the arc at the four cornersarcHeight - the vertical diameter of the arc at the four cornersSee Also:fillRoundRect(int, int, int, int, int, int)
     */
    public void drawRoundRect(int x, int y, int width, int height, int arcWidth, int arcHeight);

    /**
     * Fills the specified rounded corner rectangle with the current color.
     *  If either width or height is zero or less,
     *  nothing is drawn.
     * 
     * Parameters:x - the x coordinate of the rectangle to be filledy - the y coordinate of the rectangle to be filledwidth - the width of the rectangle to be filledheight - the height of the rectangle to be filledarcWidth - the horizontal diameter of the arc at the four
     *  cornersarcHeight - the vertical diameter of the arc at the four cornersSee Also:drawRoundRect(int, int, int, int, int, int)
     */
    public void fillRoundRect(int x, int y, int width, int height, int arcWidth, int arcHeight);

    /**
     * Fills a circular or elliptical arc covering the specified rectangle.
     * 
     *  The resulting arc begins at startAngle and extends
     *  for arcAngle degrees.
     *  Angles are interpreted such that 0 degrees
     *  is at the 3 o'clock position.
     *  A positive value indicates a counter-clockwise rotation
     *  while a negative value indicates a clockwise rotation.
     * 
     *  The center of the arc is the center of the rectangle whose origin
     *  is (x,&nbsp;y) and whose size is specified by the
     *  width and height arguments.
     * 
     *  If either width or height is zero or less,
     *  nothing is drawn.
     * 
     *   The filled region consists of the &quot;pie wedge&quot;
     *  region bounded
     *  by the arc
     *  segment as if drawn by drawArc(), the radius extending from
     *  the center to
     *  this arc at startAngle degrees, and radius extending
     *  from the
     *  center to this arc at startAngle + arcAngle degrees.
     * 
     *   The angles are specified relative to the non-square extents of
     *  the bounding rectangle such that 45 degrees always
     *  falls on the
     *  line from the center of the ellipse to the upper right corner of
     *  the bounding rectangle. As a result, if the bounding rectangle is
     *  noticeably longer in one axis than the other, the angles to the
     *  start and end of the arc segment will be skewed farther along the
     *  longer axis of the bounds.
     * 
     * Parameters:x - the x coordinate of the upper-left corner of
     *  the arc to be filled.y - the y coordinate of the upper-left corner of the
     *  arc to be filled.width - the width of the arc to be filledheight - the height of the arc to be filledstartAngle - the beginning angle.arcAngle - the angular extent of the arc,
     *  relative to the start angle.See Also:drawArc(int, int, int, int, int, int)
     */
    public void fillArc(int x, int y, int width, int height, int startAngle, int arcAngle);

    /**
     * Draws the outline of a circular or elliptical arc
     *  covering the specified rectangle,
     *  using the current color and stroke style.
     * 
     *  The resulting arc begins at startAngle and extends
     *  for arcAngle degrees, using the current color.
     *  Angles are interpreted such that 0&nbsp;degrees
     *  is at the 3&nbsp;o'clock position.
     *  A positive value indicates a counter-clockwise rotation
     *  while a negative value indicates a clockwise rotation.
     * 
     *  The center of the arc is the center of the rectangle whose origin
     *  is (x,&nbsp;y) and whose size is specified by the
     *  width and height arguments.
     * 
     *  The resulting arc covers an area
     *  width&nbsp;+&nbsp;1 pixels wide
     *  by height&nbsp;+&nbsp;1 pixels tall.
     *  If either width or height is less than zero,
     *  nothing is drawn.
     * 
     *   The angles are specified relative to the non-square extents of
     *  the bounding rectangle such that 45 degrees always
     *  falls on the
     *  line from the center of the ellipse to the upper right corner of
     *  the bounding rectangle. As a result, if the bounding rectangle is
     *  noticeably longer in one axis than the other, the angles to the
     *  start and end of the arc segment will be skewed farther along the
     *  longer axis of the bounds.
     * 
     * Parameters:x - the x coordinate of the upper-left corner
     *  of the arc to be drawny - the y coordinate of the upper-left corner
     *  of the arc to be drawnwidth - the width of the arc to be drawnheight - the height of the arc to be drawnstartAngle - the beginning anglearcAngle - the angular extent of the arc, relative to
     *  the start angleSee Also:fillArc(int, int, int, int, int, int)
     */
    public void drawArc(int x, int y, int width, int height, int startAngle, int arcAngle);

    /**
     * Draws the specified String using the current font and color.
     *  The x,y position is the position of the anchor point.
     *  See anchor points.
     * 
     * Parameters:str - the String to be drawnx - the x coordinate of the anchor pointy - the y coordinate of the anchor pointanchor - the anchor point for positioning the text
     * Throws:
     * NullPointerException - if str is null
     * IllegalArgumentException - if anchor is not a legal valueSee Also:drawChars(char[], int, int, int, int, int)
     */
    public void drawString(String str, int x, int y, int anchor);

    /**
     * Draws the specified String using the current font and color.
     *  The x,y position is the position of the anchor point.
     *  See anchor points.
     * 
     *  The offset and len parameters must
     *  specify a valid range of characters within
     *  the string str.
     *  The offset parameter must be within the
     *  range [0..(str.length())], inclusive.
     *  The len parameter
     *  must be a non-negative integer such that
     *  (offset + len) &lt;= str.length().
     * 
     * Parameters:str - the String to be drawnoffset - zero-based index of first character in the substringlen - length of the substringx - the x coordinate of the anchor pointy - the y coordinate of the anchor pointanchor - the anchor point for positioning the text
     * Throws:
     * StringIndexOutOfBoundsException - if offset
     *  and length do not specify
     *  a valid range within the String str
     * IllegalArgumentException - if anchor
     *  is not a legal value
     * NullPointerException - if str is nullSee Also:drawString(String, int, int, int).
     */
    public void drawSubstring(String str, int offset, int len, int x, int y, int anchor);

    /**
     * Draws the specified character using the current font and color.
     * 
     * Parameters:character - the character to be drawnx - the x coordinate of the anchor pointy - the y coordinate of the anchor pointanchor - the anchor point for positioning the text; see
     *  anchor points
     * Throws:
     * IllegalArgumentException - if anchor
     *  is not a legal valueSee Also:drawString(java.lang.String, int, int, int),
     * drawChars(char[], int, int, int, int, int)
     */
    public void drawChar(char character, int x, int y, int anchor);

    /**
     * Draws the specified characters using the current font and color.
     * 
     *  The offset and length parameters must
     *  specify a valid range of characters within
     *  the character array data.
     *  The offset parameter must be within the
     *  range [0..(data.length)], inclusive.
     *  The length parameter
     *  must be a non-negative integer such that
     *  (offset + length) &lt;= data.length.
     * 
     * Parameters:data - the array of characters to be drawnoffset - the start offset in the datalength - the number of characters to be drawnx - the x coordinate of the anchor pointy - the y coordinate of the anchor pointanchor - the anchor point for positioning the text; see
     *  anchor points
     * Throws:
     * ArrayIndexOutOfBoundsException - if offset
     *  and length
     *  do not specify a valid range within the data array
     * IllegalArgumentException - if anchor is not a legal value
     * NullPointerException - if data is nullSee Also:drawString(java.lang.String, int, int, int)
     */
    public void drawChars(char[] data, int offset, int length, int x, int y, int anchor);

    /**
     * Draws the specified image by using the anchor point.
     *  The image can be drawn in different positions relative to
     *  the anchor point by passing the appropriate position constants.
     *  See anchor points.
     * 
     *  If the source image contains transparent pixels, the corresponding
     *  pixels in the destination image must be left untouched.  If the source
     *  image contains partially transparent pixels, a compositing operation
     *  must be performed with the destination pixels, leaving all pixels of
     *  the destination image fully opaque.
     * 
     *  If img is the same as the destination of this Graphics
     *  object, the result is undefined.  For copying areas within an
     *  Image, copyArea should be used instead.
     * 
     * Parameters:img - the specified image to be drawnx - the x coordinate of the anchor pointy - the y coordinate of the anchor pointanchor - the anchor point for positioning the image
     * Throws:
     * IllegalArgumentException - if anchor
     *  is not a legal value
     * NullPointerException - if img is nullSee Also:Image
     */
    public void drawImage(Image img, int x, int y, int anchor);

    /**
     * Copies a region of the specified source image to a location within
     *  the destination, possibly transforming (rotating and reflecting)
     *  the image data using the chosen transform function.
     * 
     *  The destination, if it is an image, must not be the same image as
     *  the source image.  If it is, an exception is thrown.  This restriction
     *  is present in order to avoid ill-defined behaviors that might occur if
     *  overlapped, transformed copies were permitted.
     * 
     *  The transform function used must be one of the following, as defined
     *  in the Sprite class:
     * 
     *  Sprite.TRANS_NONE - causes the specified image
     *  region to be copied unchanged
     *  Sprite.TRANS_ROT90 - causes the specified image
     *  region to be rotated clockwise by 90 degrees.
     *  Sprite.TRANS_ROT180 - causes the specified image
     *  region to be rotated clockwise by 180 degrees.
     *  Sprite.TRANS_ROT270 - causes the specified image
     *  region to be rotated clockwise by 270 degrees.
     *  Sprite.TRANS_MIRROR - causes the specified image
     *  region to be reflected about its vertical center.
     *  Sprite.TRANS_MIRROR_ROT90 - causes the specified image
     *  region to be reflected about its vertical center and then rotated
     *  clockwise by 90 degrees.
     *  Sprite.TRANS_MIRROR_ROT180 - causes the specified image
     *  region to be reflected about its vertical center and then rotated
     *  clockwise by 180 degrees.
     *  Sprite.TRANS_MIRROR_ROT270 - causes the specified image
     *  region to be reflected about its vertical center and then rotated
     *  clockwise by 270 degrees.
     * 
     *  If the source region contains transparent pixels, the corresponding
     *  pixels in the destination region must be left untouched.  If the source
     *  region contains partially transparent pixels, a compositing operation
     *  must be performed with the destination pixels, leaving all pixels of
     *  the destination region fully opaque.
     * 
     *   The (x_src, y_src) coordinates are relative to
     *  the upper left
     *  corner of the source image.  The x_src,
     *  y_src, width, and height
     *  parameters specify a rectangular region of the source image.  It is
     *  illegal for this region to extend beyond the bounds of the source
     *  image.  This requires that:
     * 
     *    x_src &gt;= 0
     *    y_src &gt;= 0
     *    x_src + width &lt;= source width
     *    y_src + height &lt;= source height
     * 
     *  The (x_dest, y_dest) coordinates are relative to
     *  the coordinate
     *  system of this Graphics object.  It is legal for the destination
     *  area to extend beyond the bounds of the Graphics
     *  object.  Pixels
     *  outside of the bounds of the Graphics object will
     *  not be drawn.
     * 
     *  The transform is applied to the image data from the region of the
     *  source image, and the result is rendered with its anchor point
     *  positioned at location (x_dest, y_dest) in the
     *  destination.
     * 
     * Parameters:src - the source image to copy fromx_src - the x coordinate of the upper left corner of the region
     *  within the source image to copyy_src - the y coordinate of the upper left corner of the region
     *  within the source image to copywidth - the width of the region to copyheight - the height of the region to copytransform - the desired transformation for the selected region
     *  being copiedx_dest - the x coordinate of the anchor point in the
     *  destination drawing areay_dest - the y coordinate of the anchor point in the
     *  destination drawing areaanchor - the anchor point for positioning the region within
     *  the destination image
     * Throws:
     * IllegalArgumentException - if src is the
     *  same image as the
     *  destination of this Graphics object
     * NullPointerException - if src is null
     * IllegalArgumentException - if transform is invalid
     * IllegalArgumentException - if anchor is invalid
     * IllegalArgumentException - if the region to be copied exceeds
     *  the bounds of the source imageSince:
     *   MIDP 2.0
     */
    public void drawRegion(Image src, int x_src, int y_src, int width, int height, int transform, int x_dest, int y_dest, int anchor);

    /**
     * Copies the contents of a rectangular area
     *  (x_src, y_src, width, height) to a destination area,
     *  whose anchor point identified by anchor is located at
     *  (x_dest, y_dest).  The effect must be that the
     *  destination area
     *  contains an exact copy of the contents of the source area
     *  immediately prior to the invocation of this method.  This result must
     *  occur even if the source and destination areas overlap.
     * 
     *  The points (x_src, y_src) and (x_dest,
     *  y_dest) are both specified
     *  relative to the coordinate system of the Graphics
     *  object.  It is
     *  illegal for the source region to extend beyond the bounds of the
     *  graphic object.  This requires that:
     * 
     *    x_src + tx &gt;= 0
     *    y_src + ty &gt;= 0
     *    x_src + tx + width &lt;= width of Graphics object's destination
     *    y_src + ty + height &lt;= height of Graphics object's destination
     * 
     *  where tx and ty represent the X and Y
     *  coordinates of the translated origin of this graphics object, as
     *  returned by getTranslateX() and
     *  getTranslateY(), respectively.
     * 
     *  However, it is legal for the destination area to extend beyond
     *  the bounds of the Graphics object.  Pixels outside
     *  of the bounds of
     *  the Graphics object will not be drawn.
     * 
     *  The copyArea method is allowed on all
     *  Graphics objects except those
     *  whose destination is the actual display device.  This restriction is
     *  necessary because allowing a copyArea method on
     *  the display would
     *  adversely impact certain techniques for implementing
     *  double-buffering.
     * 
     *  Like other graphics operations, the copyArea
     *  method uses the Source
     *  Over Destination rule for combining pixels.  However, since it is
     *  defined only for mutable images, which can contain only fully opaque
     *  pixels, this is effectively the same as pixel replacement.
     * 
     * Parameters:x_src - the x coordinate of upper left corner of source areay_src - the y coordinate of upper left corner of source areawidth - the width of the source areaheight - the height of the source areax_dest - the x coordinate of the destination anchor pointy_dest - the y coordinate of the destination anchor pointanchor - the anchor point for positioning the region within
     *         the destination image
     * Throws:
     * IllegalStateException - if the destination of this
     *  Graphics object is the display device
     * IllegalArgumentException - if the region to be copied exceeds
     *  the bounds of the source imageSince:
     *   MIDP 2.0
     */
    public void copyArea(int x_src, int y_src, int width, int height, int x_dest, int y_dest, int anchor);

    /**
     * Fills the specified triangle will the current color.  The lines
     *  connecting each pair of points are included in the filled
     *  triangle.
     * 
     * Parameters:x1 - the x coordinate of the first vertex of the triangley1 - the y coordinate of the first vertex of the trianglex2 - the x coordinate of the second vertex of the triangley2 - the y coordinate of the second vertex of the trianglex3 - the x coordinate of the third vertex of the triangley3 - the y coordinate of the third vertex of the triangleSince:
     *   MIDP 2.0
     */
    public void fillTriangle(int x1, int y1, int x2, int y2, int x3, int y3);

    /**
     * Renders a series of device-independent RGB+transparency values in a
     *  specified region.  The values are stored in
     *  rgbData in a format
     *  with 24 bits of RGB and an eight-bit alpha value
     *  (0xAARRGGBB),
     *  with the first value stored at the specified offset.  The
     *  scanlength
     *  specifies the relative offset within the array between the
     *  corresponding pixels of consecutive rows.  Any value for
     *  scanlength is acceptable (even negative values)
     *  provided that all resulting references are within the
     *  bounds of the rgbData array. The ARGB data is
     *  rasterized horizontally from left to right within each row.
     *  The ARGB values are
     *  rendered in the region specified by x,
     *  y, width and height, and
     *  the operation is subject to the current clip region
     *  and translation for this Graphics object.
     * 
     *  Consider P(a,b) to be the value of the pixel
     *  located at column a and row b of the
     *  Image, where rows and columns are numbered downward from the
     *  top starting at zero, and columns are numbered rightward from
     *  the left starting at zero. This operation can then be defined
     *  as:
     * 
     *     P(a, b) = rgbData[offset + (a - x) + (b - y) * scanlength]
     * 
     *   for
     * 
     *      x &lt;= a &lt; x + width
     *      y &lt;= b &lt; y + height
     * 
     *   This capability is provided in the Graphics
     *  class so that it can be
     *  used to render both to the screen and to offscreen
     *  Image objects.  The
     *  ability to retrieve ARGB values is provided by the Image.getRGB(int[], int, int, int, int, int, int)
     *  method.
     * 
     *   If processAlpha is true, the
     *  high-order byte of the ARGB format
     *  specifies opacity; that is, 0x00RRGGBB specifies a
     *  fully transparent
     *  pixel and 0xFFRRGGBB specifies a fully opaque
     *  pixel.  Intermediate
     *  alpha values specify semitransparency.  If the implementation does not
     *  support alpha blending for image rendering operations, it must remove
     *  any semitransparency from the source data prior to performing any
     *  rendering.  (See Alpha Processing for
     *  further discussion.)
     *  If processAlpha is false, the alpha
     *  values are ignored and all pixels
     *  must be treated as completely opaque.
     * 
     *   The mapping from ARGB values to the device-dependent
     *  pixels is platform-specific and may require significant
     *  computation.
     * 
     * Parameters:rgbData - an array of ARGB values in the format
     *  0xAARRGGBBoffset - the array index of the first ARGB valuescanlength - the relative array offset between the
     *  corresponding pixels in consecutive rows in the
     *  rgbData arrayx - the horizontal location of the region to be renderedy - the vertical location of the region to be renderedwidth - the width of the region to be renderedheight - the height of the region to be renderedprocessAlpha - true if rgbData
     *  has an alpha channel,
     *  false if all pixels are fully opaque
     * Throws:
     * ArrayIndexOutOfBoundsException - if the requested operation
     *  will attempt to access an element of rgbData
     *  whose index is either negative or beyond its length
     * NullPointerException - if rgbData is nullSince:
     *   MIDP 2.0
     */
    public void drawRGB(int[] rgbData, int offset, int scanlength, int x, int y, int width, int height, boolean processAlpha);

    /**
     * Gets the color that will be displayed if the specified color
     *  is requested. This method enables the developer to check the
     *  manner in which RGB values are mapped to the set of distinct
     *  colors that the device can actually display. For example,
     *  with a monochrome device, this method will return either
     *  0xFFFFFF (white) or 0x000000 (black)
     *  depending on the brightness of the specified color.
     * 
     * Parameters:color - the desired color (in 0x00RRGGBB
     *  format, the high-order
     *  byte is ignored)
     * Returns:the corresponding color that will be displayed on the device's
     *  screen (in 0x00RRGGBB format)Since:
     *   MIDP 2.0
     */
    public int getDisplayColor(int color);

}

package javax.microedition.lcdui;

public class Image {
    /**
     * Creates a new, mutable image for off-screen drawing. Every pixel
     *  within the newly created image is white.  The width and height of the
     *  image must both be greater than zero.
     * 
     * Parameters:width - the width of the new image, in pixelsheight - the height of the new image, in pixels
     * Returns:the created image
     * Throws:
     * IllegalArgumentException - if either width or
     *  height is zero or less
     */
    public static Image createImage(int width, int height);

    /**
     * Creates an immutable image from a source image.
     *  If the source image is mutable, an immutable copy is created and
     *  returned.  If the source image is immutable, the implementation may
     *  simply return it without creating a new image.  If an immutable source
     *  image contains transparency information, this information is copied to
     *  the new image unchanged.
     * 
     *   This method is useful for placing the contents of mutable images
     *  into Choice objects.  The application can create
     *  an off-screen image
     *  using the
     *  createImage(w, h)
     *  method, draw into it using a Graphics object
     *  obtained with the
     *  getGraphics()
     *  method, and then create an immutable copy of it with this method.
     *  The immutable copy may then be placed into Choice
     *  objects.
     * 
     * Parameters:source - the source image to be copied
     * Returns:the new, immutable image
     * Throws:
     * NullPointerException - if source is null
     */
    public static Image createImage(Image source);

    /**
     * Creates an immutable image from decoded image data obtained from the
     *  named resource.  The name parameter is a resource name as defined by
     *  Class.getResourceAsStream(name).  The rules for resolving resource
     *  names are defined in the
     * 
     *  Application Resource Files section of the
     *  java.lang package documentation.
     * 
     * Parameters:name - the name of the resource containing the image data in one of
     *  the supported image formats
     * Returns:the created image
     * Throws:
     * NullPointerException - if name is null
     * IOException - if the resource does not exist,
     *  the data cannot
     *  be loaded, or the image data cannot be decoded
     */
    public static Image createImage(String name) throws IOException;

    /**
     * Creates an immutable image which is decoded from the data stored in
     *  the specified byte array at the specified offset and length. The data
     *  must be in a self-identifying image file format supported by the
     *  implementation, such as PNG.
     * 
     *  The imageoffset and imagelength
     *  parameters specify a range of
     *  data within the imageData byte array. The
     *  imageOffset parameter
     *  specifies the
     *  offset into the array of the first data byte to be used. It must
     *  therefore lie within the range
     *  [0..(imageData.length-1)]. The
     *  imageLength
     *  parameter specifies the number of data bytes to be used. It must be a
     *  positive integer and it must not cause the range to extend beyond
     *  the end
     *  of the array. That is, it must be true that
     *  imageOffset + imageLength &lt; imageData.length.
     * 
     *   This method is intended for use when loading an
     *  image from a variety of sources, such as from
     *  persistent storage or from the network.
     * 
     * Parameters:imageData - the array of image data in a supported image formatimageOffset - the offset of the start of the data in the arrayimageLength - the length of the data in the array
     * Returns:the created image
     * Throws:
     * ArrayIndexOutOfBoundsException - if imageOffset
     *  and imageLength
     *  specify an invalid range
     * NullPointerException - if imageData is
     *  null
     * IllegalArgumentException - if imageData is incorrectly
     *  formatted or otherwise cannot be decoded
     */
    public static Image createImage(byte[] imageData, int imageOffset, int imageLength);

    /**
     * Creates an immutable image using pixel data from the specified
     *  region of a source image, transformed as specified.
     * 
     *  The source image may be mutable or immutable.  For immutable source
     *  images, transparency information, if any, is copied to the new
     *  image unchanged.
     * 
     *  On some devices, pre-transformed images may render more quickly
     *  than images that are transformed on the fly using
     *  drawRegion.
     *  However, creating such images does consume additional heap space,
     *  so this technique should be applied only to images whose rendering
     *  speed is critical.
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
     *  The size of the returned image will be the size of the specified region
     *  with the transform applied.  For example, if the region is
     *  100&nbsp;x&nbsp;50 pixels and the transform is
     *  TRANS_ROT90, the
     *  returned image will be 50&nbsp;x&nbsp;100 pixels.
     * 
     *  Note: If all of the following conditions
     *  are met, this method may
     *  simply return the source Image without creating a
     *  new one:
     * 
     *  the source image is immutable;
     *  the region represents the entire source image; and
     *  the transform is TRANS_NONE.
     * 
     * Parameters:image - the source image to be copied fromx - the horizontal location of the region to be copiedy - the vertical location of the region to be copiedwidth - the width of the region to be copiedheight - the height of the region to be copiedtransform - the transform to be applied to the region
     * Returns:the new, immutable image
     * Throws:
     * NullPointerException - if image is null
     * IllegalArgumentException - if the region to be copied exceeds
     *  the bounds of the source image
     * IllegalArgumentException - if either width or
     *  height is zero or less
     * IllegalArgumentException - if the transform
     *  is not validSince:
     *   MIDP 2.0
     */
    public static Image createImage(Image image, int x, int y, int width, int height, int transform);

    /**
     * Creates a new Graphics object that renders to this
     *  image. This image
     *  must be
     *  mutable; it is illegal to call this method on an immutable image.
     *  The mutability of an image may be tested
     *  with the isMutable() method.
     * 
     *  The newly created Graphics object has the
     *  following properties:
     * 
     *  the destination is this Image object;
     *  the clip region encompasses the entire Image;
     *  the current color is black;
     *  the font is the same as the font returned by
     *  Font.getDefaultFont();
     *  the stroke style is SOLID; and
     * 
     *  the origin of the coordinate system is located at the upper-left
     *  corner of the Image.
     * 
     *  The lifetime of Graphics objects created using
     *  this method is
     *  indefinite.  They may be used at any time, by any thread.
     * 
     * Returns:a Graphics object with this image as its destination
     * Throws:
     * IllegalStateException - if the image is immutable
     */
    public Graphics getGraphics();

    /**
     * Gets the width of the image in pixels. The value returned
     *  must reflect the actual width of the image when rendered.
     * 
     * Returns:width of the image
     */
    public int getWidth();

    /**
     * Gets the height of the image in pixels. The value returned
     *  must reflect the actual height of the image when rendered.
     * 
     * Returns:height of the image
     */
    public int getHeight();

    /**
     * Check if this image is mutable. Mutable images can be modified by
     *  rendering to them through a Graphics object
     *  obtained from the
     *  getGraphics() method of this object.
     * 
     * Returns:true if the image is mutable,
     *  false otherwise
     */
    public boolean isMutable();

    /**
     * Creates an immutable image from decoded image data obtained from an
     *  InputStream.  This method blocks until all image data has
     *  been read and decoded.  After this method completes (whether by
     *  returning or by throwing an exception) the stream is left open and its
     *  current position is undefined.
     * 
     * Parameters:stream - the name of the resource containing the image data
     *  in one of the supported image formats
     * Returns:the created image
     * Throws:
     * NullPointerException - if stream is null
     * IOException - if an I/O error occurs, if the image data
     *  cannot be loaded, or if the image data cannot be decodedSince:
     *   MIDP 2.0
     */
    public static Image createImage(InputStream stream) throws IOException;

    /**
     * Creates an immutable image from a sequence of ARGB values, specified
     *  as 0xAARRGGBB.
     *  The ARGB data within the rgb array is arranged
     *  horizontally from left to right within each row,
     *  row by row from top to bottom.
     *  If processAlpha is true,
     *  the high-order byte specifies opacity; that is,
     *  0x00RRGGBB specifies
     *  a fully transparent pixel and 0xFFRRGGBB specifies
     *  a fully opaque
     *  pixel.  Intermediate alpha values specify semitransparency.  If the
     *  implementation does not support alpha blending for image rendering
     *  operations, it must replace any semitransparent pixels with fully
     *  transparent pixels.  (See Alpha Processing
     *  for further discussion.)  If processAlpha is
     *  false, the alpha values
     *  are ignored and all pixels must be treated as fully opaque.
     * 
     *  Consider P(a,b) to be the value of the pixel
     *  located at column a and row b of the
     *  Image, where rows and columns are numbered downward from the
     *  top starting at zero, and columns are numbered rightward from
     *  the left starting at zero. This operation can then be defined
     *  as:
     * 
     *     P(a, b) = rgb[a + b * width];
     * 
     *  for
     * 
     *      0 &lt;= a &lt; width
     *      0 &lt;= b &lt; height
     * 
     * Parameters:rgb - an array of ARGB values that composes the imagewidth - the width of the imageheight - the height of the imageprocessAlpha - true if rgb
     *  has an alpha channel,
     *  false if all pixels are fully opaque
     * Returns:the created image
     * Throws:
     * NullPointerException - if rgb is null.
     * IllegalArgumentException - if either width or
     *  height is zero or less
     * ArrayIndexOutOfBoundsException - if the length of
     *  rgb is
     *  less than width&nbsp;*&nbsp;height.Since:
     *   MIDP 2.0
     */
    public static Image createRGBImage(int[] rgb, int width, int height, boolean processAlpha);

    /**
     * Obtains ARGB pixel data from the specified region of this image and
     *  stores it in the provided array of integers.  Each pixel value is
     *  stored in 0xAARRGGBB format, where the high-order
     *  byte contains the
     *  alpha channel and the remaining bytes contain color components for
     *  red, green and blue, respectively.  The alpha channel specifies the
     *  opacity of the pixel, where a value of 0x00
     *  represents a pixel that
     *  is fully transparent and a value of 0xFF
     *  represents a fully opaque
     *  pixel.
     * 
     *   The returned values are not guaranteed to be identical to values
     *  from the original source, such as from
     *  createRGBImage or from a PNG
     *  image.  Color values may be resampled to reflect the display
     *  capabilities of the device (for example, red, green or blue pixels may
     *  all be represented by the same gray value on a grayscale device).  On
     *  devices that do not support alpha blending, the alpha value will be
     *  0xFF for opaque pixels and 0x00 for
     *  all other pixels (see Alpha Processing for further discussion.)  On devices
     *  that support alpha blending, alpha channel values may be resampled to
     *  reflect the number of levels of semitransparency supported.
     * 
     *  The scanlength specifies the relative offset within the
     *  array between the corresponding pixels of consecutive rows.  In order
     *  to prevent rows of stored pixels from overlapping, the absolute value
     *  of scanlength must be greater than or equal to
     *  width.  Negative values of scanlength are
     *  allowed.  In all cases, this must result in every reference being
     *  within the bounds of the rgbData array.
     * 
     *  Consider P(a,b) to be the value of the pixel
     *  located at column a and row b of the
     *  Image, where rows and columns are numbered downward from the
     *  top starting at zero, and columns are numbered rightward from
     *  the left starting at zero. This operation can then be defined
     *  as:
     * 
     *     rgbData[offset + (a - x) + (b - y) * scanlength] = P(a, b);
     * 
     *  for
     * 
     *      x &lt;= a &lt; x + width
     *      y &lt;= b &lt; y + height
     * 
     *  The source rectangle is required to not exceed the bounds of
     *  the image.  This means:
     * 
     *    x &gt;= 0
     *    y &gt;= 0
     *    x + width &lt;= image width
     *    y + height &lt;= image height
     * 
     *  If any of these conditions is not met an
     *  IllegalArgumentException is thrown.  Otherwise, in
     *  cases where width &lt;= 0 or height &lt;= 0,
     *  no exception is thrown, and no pixel data is copied to
     *  rgbData.
     * 
     * Parameters:rgbData - an array of integers in which the ARGB pixel data is
     *  storedoffset - the index into the array where the first ARGB value
     *  is storedscanlength - the relative offset in the array between
     *  corresponding pixels in consecutive rows of the regionx - the x-coordinate of the upper left corner of the regiony - the y-coordinate of the upper left corner of the regionwidth - the width of the regionheight - the height of the region
     * Throws:
     * ArrayIndexOutOfBoundsException - if the requested operation would
     *  attempt to access an element in the rgbData array
     *  whose index is either
     *  negative or beyond its length (the contents of the array are unchanged)
     * IllegalArgumentException - if the area being retrieved
     *  exceeds the bounds of the source image
     * IllegalArgumentException - if the absolute value of
     *  scanlength is less than width
     * NullPointerException - if rgbData is nullSince:
     *   MIDP 2.0
     */
    public void getRGB(int[] rgbData, int offset, int scanlength, int x, int y, int width, int height);

}

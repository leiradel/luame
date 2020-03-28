package javax.microedition.lcdui.game;

public class Sprite extends Layer {
    /**
     * No transform is applied to the Sprite.
     *  This constant has a value of 0.
     * 
     * See Also:Constant Field Values
     */
    public static final int TRANS_NONE = 0;

    /**
     * Causes the Sprite to appear rotated clockwise by 90 degrees.
     *  This constant has a value of 5.
     * 
     * See Also:Constant Field Values
     */
    public static final int TRANS_ROT90 = 5;

    /**
     * Causes the Sprite to appear rotated clockwise by 180 degrees.
     *  This constant has a value of 3.
     * 
     * See Also:Constant Field Values
     */
    public static final int TRANS_ROT180 = 3;

    /**
     * Causes the Sprite to appear rotated clockwise by 270 degrees.
     *  This constant has a value of 6.
     * 
     * See Also:Constant Field Values
     */
    public static final int TRANS_ROT270 = 6;

    /**
     * Causes the Sprite to appear reflected about its vertical
     *  center.
     *  This constant has a value of 2.
     * 
     * See Also:Constant Field Values
     */
    public static final int TRANS_MIRROR = 2;

    /**
     * Causes the Sprite to appear reflected about its vertical
     *  center and then rotated clockwise by 90 degrees.
     *  This constant has a value of 7.
     * 
     * See Also:Constant Field Values
     */
    public static final int TRANS_MIRROR_ROT90 = 7;

    /**
     * Causes the Sprite to appear reflected about its vertical
     *  center and then rotated clockwise by 180 degrees.
     *  This constant has a value of 1.
     * 
     * See Also:Constant Field Values
     */
    public static final int TRANS_MIRROR_ROT180 = 1;

    /**
     * Causes the Sprite to appear reflected about its vertical
     *  center and then rotated clockwise by 270 degrees.
     *  This constant has a value of 4.
     * 
     * See Also:Constant Field Values
     */
    public static final int TRANS_MIRROR_ROT270 = 4;

    /**
     * Creates a new non-animated Sprite using the provided Image.
     *  This constructor is functionally equivalent to calling
     *  new Sprite(image, image.getWidth(), image.getHeight())
     * 
     *  By default, the Sprite is visible and its upper-left
     *  corner is positioned at (0,0) in the painter's coordinate system.
     * 
     * Parameters:image - the Image to use as the single frame
     *  for the Sprite
     * Throws:
     * NullPointerException - if img is null
     */
    public Sprite(Image image) {
        construct(image);
    }

    private native void construct(Image image);

    /**
     * Creates a new animated Sprite using frames contained in
     *  the provided Image.  The frames must be equally sized, with the
     *  dimensions specified by frameWidth and
     *  frameHeight.  They may be laid out in the image
     *  horizontally, vertically, or as a grid.  The width of the source
     *  image must be an integer multiple of the frame width, and the height
     *  of the source image must be an integer multiple of the frame height.
     *  The  values returned by Layer.getWidth() and
     *  Layer.getHeight() will reflect the frame width and frame height
     *  subject to the Sprite's current transform.
     * 
     *  Sprites have a default frame sequence corresponding to the raw frame
     *  numbers, starting with frame 0.  The frame sequence may be modified
     *  with setFrameSequence(int[]).
     * 
     *  By default, the Sprite is visible and its upper-left corner is
     *  positioned at (0,0) in the painter's coordinate system.
     * 
     * Parameters:image - the Image to use for SpriteframeWidth - the width, in pixels, of the
     *  individual raw framesframeHeight - the height, in pixels, of the
     *  individual raw frames
     * Throws:
     * NullPointerException - if img is null
     * IllegalArgumentException - if frameHeight or
     *  frameWidth is less than 1
     * IllegalArgumentException - if the image
     *  width is not an integer multiple of the frameWidth
     * IllegalArgumentException - if the image
     *  height is not an integer multiple of the frameHeight
     */
    public Sprite(Image image, int frameWidth, int frameHeight) {
        construct(image, frameWidth, frameHeight);
    }

    private native void construct(Image image, int frameWidth, int frameHeight);

    /**
     * Creates a new Sprite from another Sprite.
     * 
     *  All instance attributes (raw frames, position, frame sequence, current
     *  frame, reference point, collision rectangle, transform, and visibility)
     *  of the source Sprite are duplicated in the new Sprite.
     * 
     * Parameters:s - the Sprite to create a copy of
     * Throws:
     * NullPointerException - if s is null
     */
    public Sprite(Sprite s) {
        construct(s);
    }

    private native void construct(Sprite s);

    /**
     * Defines the reference pixel for this Sprite.  The pixel is
     *  defined by its location relative to the upper-left corner of
     *  the Sprite's un-transformed frame, and it may lay outside of
     *  the frame's bounds.
     * 
     *  When a transformation is applied, the reference pixel is
     *  defined relative to the Sprite's initial upper-left corner
     *  before transformation. This corner may no longer appear as the
     *  upper-left corner in the painter's coordinate system under
     *  current transformation.
     * 
     *  By default, a Sprite's reference pixel is located at (0,0); that is,
     *  the pixel in the upper-left corner of the raw frame.
     * 
     *  Changing the reference pixel does not change the
     *  Sprite's physical position in the painter's coordinate system;
     *  that is, the values returned by getX() and
     *  getY() will not change as a result of defining the
     *  reference pixel.  However, subsequent calls to methods that
     *  involve the reference pixel will be impacted by its new definition.
     * 
     * Parameters:x - the horizontal location of the reference pixel, relative
     *  to the left edge of the un-transformed framey - the vertical location of the reference pixel, relative
     *  to the top edge of the un-transformed frameSee Also:setRefPixelPosition(int, int),
     * getRefPixelX(),
     * getRefPixelY()
     */
    public void defineReferencePixel(int x, int y);

    /**
     * Sets this Sprite's position such that its reference pixel is located
     *  at (x,y) in the painter's coordinate system.
     * 
     * Parameters:x - the horizontal location at which to place the reference pixely - the vertical location at which to place the reference pixelSee Also:defineReferencePixel(int, int),
     * getRefPixelX(),
     * getRefPixelY()
     */
    public void setRefPixelPosition(int x, int y);

    /**
     * Gets the horizontal position of this Sprite's reference pixel
     *  in the painter's coordinate system.
     * 
     * Returns:the horizontal location of the reference pixelSee Also:defineReferencePixel(int, int),
     * setRefPixelPosition(int, int),
     * getRefPixelY()
     */
    public int getRefPixelX();

    /**
     * Gets the vertical position of this Sprite's reference pixel
     *  in the painter's coordinate system.
     * 
     * Returns:the vertical location of the reference pixelSee Also:defineReferencePixel(int, int),
     * setRefPixelPosition(int, int),
     * getRefPixelX()
     */
    public int getRefPixelY();

    /**
     * Selects the current frame in the frame sequence.
     *  The current frame is rendered when paint(Graphics) is called.
     * 
     *  The index provided refers to the desired entry in the frame sequence,
     *  not the index of the actual frame itself.
     * 
     * Parameters:sequenceIndex - the index of of the desired entry in the frame
     *  sequence
     * Throws:
     * IndexOutOfBoundsException - if frameIndex is
     *  less than0
     * IndexOutOfBoundsException - if frameIndex is
     *  equal to or greater than the length of the current frame
     *  sequence (or the number of raw frames for the default sequence)See Also:setFrameSequence(int[]),
     * getFrame()
     */
    public void setFrame(int sequenceIndex);

    /**
     * Gets the current index in the frame sequence.
     *  The index returned refers to the current entry in the frame sequence,
     *  not the index of the actual frame that is displayed.
     * 
     * Returns:the current index in the frame sequenceSee Also:setFrameSequence(int[]),
     * setFrame(int)
     */
    public final int getFrame();

    /**
     * Gets the number of raw frames for this Sprite.  The value returned
     *  reflects the number of frames; it does not reflect the length of the
     *  Sprite's frame sequence.  However, these two values will be the same
     *  if the default frame sequence is used.
     * 
     * Returns:the number of raw frames for this SpriteSee Also:getFrameSequenceLength()
     */
    public int getRawFrameCount();

    /**
     * Gets the number of elements in the frame sequence.  The value returned
     *  reflects the length of the Sprite's frame sequence; it does not reflect
     *  the number of raw frames.  However, these two values will be the same
     *  if the default frame sequence is used.
     * 
     * Returns:the number of elements in this Sprite's frame sequenceSee Also:getRawFrameCount()
     */
    public int getFrameSequenceLength();

    /**
     * Selects the next frame in the frame sequence.
     * 
     *  The frame sequence is considered to be circular, i.e. if
     *  nextFrame() is called when at the end of the sequence,
     *  this method will advance to the first entry in the sequence.
     * 
     * See Also:setFrameSequence(int[]),
     * prevFrame()
     */
    public void nextFrame();

    /**
     * Selects the previous frame in the frame sequence.
     * 
     *  The frame sequence is considered to be circular, i.e. if
     *  prevFrame() is called when at the start of the sequence,
     *  this method will advance to the last entry in the sequence.
     * 
     * See Also:setFrameSequence(int[]),
     * nextFrame()
     */
    public void prevFrame();

    /**
     * Draws the Sprite.
     * 
     *  Draws current frame of Sprite using the provided Graphics object.
     *  The Sprite's upper left corner is rendered at the Sprite's current
     *  position relative to the origin of the Graphics object.  The current
     *  position of the Sprite's upper-left corner can be retrieved by
     *  calling Layer.getX() and Layer.getY().
     * 
     *  Rendering is subject to the clip region of the Graphics object.
     *  The Sprite will be drawn only if it is visible.
     * 
     *  If the Sprite's Image is mutable, the Sprite is rendered using the
     *  current contents of the Image.
     * 
     * Specified by:paint in class Layer
     * 
     * Parameters:g - the graphics object to draw Sprite on
     * Throws:
     * NullPointerException - if g is null
     */
    public final void paint(Graphics g);

    /**
     * Set the frame sequence for this Sprite.
     * 
     *  All Sprites have a default sequence that displays the Sprites
     *  frames in order.  This method allows for the creation of an
     *  arbitrary sequence using the available frames.  The current
     *  index in the frame sequence is reset to zero as a result of
     *  calling this method.
     * 
     *  The contents of the sequence array are copied when this method
     *  is called; thus, any changes made to the array after this method
     *  returns have no effect on the Sprite's frame sequence.
     * 
     *  Passing in null causes the Sprite to revert to the
     *  default frame sequence.
     * 
     * Parameters:sequence - an array of integers, where each integer represents
     *  a frame index
     * Throws:
     * ArrayIndexOutOfBoundsException - if seq is non-null and any member
     *          of the array has a value less than 0 or
     *          greater than or equal to the
     *          number of frames as reported by getRawFrameCount()
     * IllegalArgumentException - if the array has less than
     *  1 elementSee Also:nextFrame(),
     * prevFrame(),
     * setFrame(int),
     * getFrame()
     */
    public void setFrameSequence(int[] sequence);

    /**
     * Changes the Image containing the Sprite's frames.
     * 
     *  Replaces the current raw frames of the Sprite with a new set of raw
     *  frames.  See the constructor Sprite(Image, int, int) for
     *  information on how the frames are created from the image.  The
     *  values returned by Layer.getWidth() and Layer.getHeight()
     *  will reflect the new frame width and frame height subject to the
     *  Sprite's current transform.
     * 
     *  Changing the image for the Sprite could change the number of raw
     *  frames.  If the new frame set has as many or more raw frames than the
     *  previous frame set, then:
     * 
     *  The current frame will be unchanged
     *  If a custom frame sequence has been defined (using
     *      setFrameSequence(int[])), it will remain unchanged.  If no
     *      custom frame sequence is defined (i.e. the default frame
     *      sequence
     *      is in use), the default frame sequence will be updated to
     *      be the default frame sequence for the new frame set.  In other
     *      words, the new default frame sequence will include all of the
     *      frames from the new raw frame set, as if this new image had been
     *      used in the constructor.
     * 
     *  If the new frame set has fewer frames than the previous frame set,
     *  then:
     * 
     *  The current frame will be reset to entry 0
     *  Any custom frame sequence will be discarded and the frame sequence
     *      will revert to the default frame sequence for the new frame
     *      set.
     * 
     *  The reference point location is unchanged as a result of calling this
     *  method, both in terms of its defined location within the Sprite and its
     *  position in the painter's coordinate system.  However, if the frame
     *  size is changed and the Sprite has been transformed, the position of
     *  the Sprite's upper-left corner may change such that the reference
     *  point remains stationary.
     * 
     *  If the Sprite's frame size is changed by this method, the collision
     *  rectangle is reset to its default value (i.e. it is set to the new
     *  bounds of the untransformed Sprite).
     * 
     * Parameters:img - the Image to use for
     *  SpriteframeWidth - the width in pixels of the individual raw framesframeHeight - the height in pixels of the individual raw frames
     * Throws:
     * NullPointerException - if img is null
     * IllegalArgumentException - if frameHeight or
     *  frameWidth is less than 1
     * IllegalArgumentException - if the image width is not an integer
     *  multiple of the frameWidth
     * IllegalArgumentException - if the image height is not an integer
     *  multiple of the frameHeight
     */
    public void setImage(Image img, int frameWidth, int frameHeight);

    /**
     * Defines the Sprite's bounding rectangle that is used for collision
     *  detection purposes.  This rectangle is specified relative to the
     *  un-transformed Sprite's upper-left corner and defines the area that is
     *  checked for collision detection.  For pixel-level detection, only those
     *  pixels within the collision rectangle are checked.
     * 
     *  By default, a Sprite's collision rectangle is located at 0,0 as has the
     *  same dimensions as the Sprite.  The collision rectangle may be
     *  specified to be larger or smaller than the default rectangle; if made
     *  larger, the pixels outside the bounds of the Sprite are considered to be
     *  transparent for pixel-level collision detection.
     * 
     * Parameters:x - the horizontal location of the collision rectangle relative to
     *  the untransformed Sprite's left edgey - the vertical location of the collision rectangle relative to
     *  the untransformed Sprite's top edgewidth - the width of the collision rectangleheight - the height of the collision rectangle
     * Throws:
     * IllegalArgumentException - if the specified
     *  width or height is
     *  less than 0
     */
    public void defineCollisionRectangle(int x, int y, int width, int height);

    /**
     * Sets the transform for this Sprite.  Transforms can be
     *  applied to a Sprite to change its rendered appearance.  Transforms
     *  are applied to the original Sprite image; they are not cumulative,
     *  nor can they be combined.  By default, a Sprite's transform is
     *  TRANS_NONE.
     * 
     *  Since some transforms involve rotations of 90 or 270 degrees, their
     *  use may result in the overall width and height of the Sprite
     *  being swapped.  As a result, the values returned by
     *  Layer.getWidth() and Layer.getHeight() may change.
     * 
     *  The collision rectangle is also modified by the transform so that
     *  it remains static relative to the pixel data of the Sprite.
     *  Similarly, the defined reference pixel is unchanged by this method,
     *  but its visual location within the Sprite may change as a result.
     * 
     *  This method repositions the Sprite so that the location of
     *  the reference pixel in the painter's coordinate system does not change
     *  as a result of changing the transform.  Thus, the reference pixel
     *  effectively becomes the centerpoint for the transform.  Consequently,
     *  the values returned by getRefPixelX() and getRefPixelY()
     *  will be the same both before and after the transform is applied, but
     *  the values returned by getX() and getY()
     *  may change.
     * 
     * Parameters:transform - the desired transform for this Sprite
     * Throws:
     * IllegalArgumentException - if the requested
     *  transform is invalidSee Also:TRANS_NONE,
     * TRANS_ROT90,
     * TRANS_ROT180,
     * TRANS_ROT270,
     * TRANS_MIRROR,
     * TRANS_MIRROR_ROT90,
     * TRANS_MIRROR_ROT180,
     * TRANS_MIRROR_ROT270
     */
    public void setTransform(int transform);

    /**
     * Checks for a collision between this Sprite and the specified Sprite.
     * 
     *  If pixel-level detection is used, a collision is detected only if
     *  opaque pixels collide.  That is, an opaque pixel in the first
     *  Sprite would have to collide with an opaque  pixel in the second
     *  Sprite for a collision to be detected.  Only those pixels within
     *  the Sprites' respective collision rectangles are checked.
     * 
     *  If pixel-level detection is not used, this method simply
     *  checks if the Sprites' collision rectangles intersect.
     * 
     *  Any transforms applied to the Sprites are automatically accounted for.
     * 
     *  Both Sprites must be visible in order for a collision to be
     *  detected.
     * 
     * Parameters:s - the Sprite to test for collision withpixelLevel - true to test for collision on a
     *  pixel-by-pixel basis, false to test using simple
     *  bounds checking.
     * Returns:true if the two Sprites have collided, otherwise
     *  false
     * Throws:
     * NullPointerException - if Sprite s is
     *  null
     */
    public final boolean collidesWith(Sprite s, boolean pixelLevel);

    /**
     * Checks for a collision between this Sprite and the specified
     *  TiledLayer.  If pixel-level detection is used, a collision is
     *  detected only if opaque pixels collide.  That is, an opaque pixel in
     *  the Sprite would have to collide with an opaque pixel in TiledLayer
     *  for a collision to be detected.  Only those pixels within the Sprite's
     *  collision rectangle are checked.
     * 
     *  If pixel-level detection is not used, this method simply checks if the
     *  Sprite's collision rectangle intersects with a non-empty cell in the
     *  TiledLayer.
     * 
     *  Any transform applied to the Sprite is automatically accounted for.
     * 
     *  The Sprite and the TiledLayer must both be visible in order for
     *  a collision to be detected.
     * 
     * Parameters:t - the TiledLayer to test for collision withpixelLevel - true to test for collision on a
     *  pixel-by-pixel basis, false to test using simple bounds
     *  checking against non-empty cells.
     * Returns:true if this Sprite has
     *  collided with the TiledLayer, otherwise
     *  false
     * Throws:
     * NullPointerException - if t is null
     */
    public final boolean collidesWith(TiledLayer t, boolean pixelLevel);

    /**
     * Checks for a collision between this Sprite and the specified Image
     *  with its upper left corner at the specified location.  If pixel-level
     *  detection is used, a collision is detected only if opaque pixels
     *  collide.  That is, an opaque pixel in the Sprite would have to collide
     *  with an opaque  pixel in Image for a collision to be detected.  Only
     *  those pixels within the Sprite's collision rectangle are checked.
     * 
     *  If pixel-level detection is not used, this method simply checks if the
     *  Sprite's collision rectangle intersects with the Image's bounds.
     * 
     *  Any transform applied to the Sprite is automatically accounted for.
     * 
     *  The Sprite must be visible in order for a collision to be
     *  detected.
     * 
     * Parameters:image - the Image to test for collisionx - the horizontal location of the Image's
     *  upper left cornery - the vertical location of the Image's
     *  upper left cornerpixelLevel - true to test for collision on a
     *  pixel-by-pixel basis, false to test using simple
     *  bounds checking
     * Returns:true if this Sprite has
     *  collided with the Image, otherwise
     *  false
     * Throws:
     * NullPointerException - if image is
     *  null
     */
    public final boolean collidesWith(Image image, int x, int y, boolean pixelLevel);

}

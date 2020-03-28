package javax.microedition.lcdui;

public class CustomItem extends Item {
    /**
     * Interaction mode bit indicating support of horizontal traversal
     *  internal to the CustomItem.
     * 
     *  TRAVERSE_HORIZONTAL has the value 1.
     * 
     * See Also:getInteractionModes(),
     * traverse(int, int, int, int[]),
     * Constant Field Values
     */
    protected static final int TRAVERSE_HORIZONTAL = 1;

    /**
     * Interaction mode bit indicating support for vertical traversal
     *  internal to the CustomItem.
     * 
     *  TRAVERSE_VERTICAL has the value 2.
     * 
     * See Also:getInteractionModes(),
     * traverse(int, int, int, int[]),
     * Constant Field Values
     */
    protected static final int TRAVERSE_VERTICAL = 2;

    /**
     * Interaction mode bit indicating support for key pressed events.
     * 
     *  KEY_PRESS has the value 4.
     * 
     * See Also:getInteractionModes(),
     * keyPressed(int),
     * Constant Field Values
     */
    protected static final int KEY_PRESS = 4;

    /**
     * Interaction mode bit indicating support for key released events.
     * 
     *  KEY_RELEASE has the value 8.
     * 
     * See Also:getInteractionModes(),
     * keyReleased(int),
     * Constant Field Values
     */
    protected static final int KEY_RELEASE = 8;

    /**
     * Interaction mode bit indicating support for key repeated events.
     * 
     *  KEY_REPEAT has the value 0x10.
     * 
     * See Also:getInteractionModes(),
     * keyRepeated(int),
     * Constant Field Values
     */
    protected static final int KEY_REPEAT = ;

    /**
     * Interaction mode bit indicating support for point pressed events.
     * 
     *  POINTER_PRESS has the value 0x20.
     * 
     * See Also:getInteractionModes(),
     * pointerPressed(int, int),
     * Constant Field Values
     */
    protected static final int POINTER_PRESS = ;

    /**
     * Interaction mode bit indicating support for point released events.
     * 
     *  POINTER_RELEASE has the value 0x40.
     * 
     * See Also:getInteractionModes(),
     * pointerReleased(int, int),
     * Constant Field Values
     */
    protected static final int POINTER_RELEASE = ;

    /**
     * Interaction mode bit indicating support for point dragged events.
     * 
     *  POINTER_DRAG has the value 0x80.
     * 
     * See Also:getInteractionModes(),
     * pointerDragged(int, int),
     * Constant Field Values
     */
    protected static final int POINTER_DRAG = ;

    /**
     * A value for traversal direction that indicates that traversal has
     *  entered or has changed location within this item, but that no specific
     *  direction is associated with this traversal event.
     * 
     *  NONE has the value 0.
     * 
     * See Also:traverse(int, int, int, int[]),
     * Constant Field Values
     */
    protected static final int NONE = 0;

    /**
     * Superclass constructor, provided so that the
     *  CustomItem subclass can specify its label.
     * 
     * Parameters:label - the CustomItem's label
     */
    protected CustomItem(String label) {
        construct(label);
    }

    private native void construct(String label);

    /**
     * Gets the game action associated with the given key code of the
     *  device.  Returns zero if no game action is associated with this key
     *  code.  See the
     *  Game Actions
     *  section of class Canvas for further discussion
     *  of game actions.
     * 
     *  The mapping of key codes to game actions may differ between
     *  CustomItem and Canvas.
     * 
     * Parameters:keyCode - the key code
     * Returns:the game action corresponding to this key, or 0
     *  if none
     * Throws:
     * IllegalArgumentException - if keyCode is not
     *  a valid key code
     */
    public int getGameAction(int keyCode);

    /**
     * Gets the available interaction modes.  This method is intended to be
     *  called by CustomItem subclass code in order
     *  for it to determine what
     *  kinds of input are available from this device.  The modes available may
     *  be dependent upon several factors: the hardware keys on the actual
     *  device, which of these keys are needed for the system to do proper
     *  navigation, the presence of a pointing device, etc.  See Interaction Modes for further discussion.  If
     *  this method returns 0, the only interaction available
     *  is through item commands.
     * 
     * Returns:a bitmask of the available interaction modes
     */
    protected final int getInteractionModes();

    /**
     * Implemented by the subclass to return the minimum width of the content
     *  area, in pixels.  This method is called by the implementation as part
     *  of its layout algorithm.  The actual width granted is reported in the
     *  sizeChanged and paint methods.
     * 
     * Returns:the minimum content width in pixels
     */
    protected abstract int getMinContentWidth();

    /**
     * Implemented by the subclass to return the minimum height of the content
     *  area, in pixels.  This method is called by the implementation as part
     *  of its layout algorithm.  The actual height granted is reported in the
     *  sizeChanged and paint methods.
     * 
     * Returns:the minimum content height in pixels
     */
    protected abstract int getMinContentHeight();

    /**
     * Implemented by the subclass to return the preferred width of the content
     *  area, in pixels.  This method is called by the implementation as part
     *  of its layout algorithm.
     * 
     *  The height parameter is the tentative height assigned
     *  to the content area.  The subclass code may use this value in its
     *  computation of the preferred width.  The height parameter
     *  will be -1 if the implementation has not assigned a tentative value
     *  for the height.  Otherwise, height will have a specific
     *  value if the application has locked the height of the
     *  CustomItem or if the container's layout algorithm has
     *  already computed a tentative height at the time of this call.  The
     *  subclass must not assume that the tentative height passed or the
     *  preferred width returned will be granted.
     *  The actual size granted is reported in the
     *  sizeChanged and paint methods.
     * 
     * Parameters:height - the tentative content height in pixels, or -1 if a
     *  tentative height has not been computed
     * Returns:the preferred content width in pixels
     */
    protected abstract int getPrefContentWidth(int height);

    /**
     * Implemented by the subclass to return the preferred height of the
     *  content area, in pixels.  This method is called by the implementation
     *  as part of its layout algorithm.
     * 
     *  The width parameter is the tentative width assigned
     *  to the content area.  The subclass code may use this value in its
     *  computation of the preferred height.  The width parameter
     *  will be -1 if the implementation has not assigned a tentative value
     *  for the width.  Otherwise, width will have a specific
     *  value if the application has locked the width of the
     *  CustomItem or if the container's layout algorithm has
     *  already computed a tentative width at the time of this call.  The
     *  subclass must not assume that the tentative width passed or the
     *  preferred height returned will be granted.
     *  The actual size granted is reported in the
     *  sizeChanged and paint methods.
     * 
     * Parameters:width - the tentative content width in pixels, or -1 if a
     *  tentative width has not been computed
     * Returns:the preferred content height in pixels
     */
    protected abstract int getPrefContentHeight(int width);

    /**
     * Implemented by the subclass in order to handle size change events.
     *  This method is called by the system when the size of the content area
     *  of this CustomItem has changed.
     * 
     *  If the size of a CustomItem changes while it is
     *  visible on the display, it may trigger an automatic
     *  repaint request.  If this occurs, the call to
     *  sizeChanged will occur prior to the call to
     *  paint.  If the CustomItem has become
     *  smaller, the implementation may choose not to trigger a repaint
     *  request if the remaining contents of the CustomItem
     *  have been preserved.  Similarly, if the CustomItem
     *  has become larger, the implementation may choose to trigger a
     *  repaint only for the new region.  In both cases, the preserved
     *  contents must remain stationary with respect to the origin of the
     *  CustomItem.  If the size change is significant to
     *  the contents of the CustomItem, the application must
     *  explicitly issue a repaint request for the changed areas.  Note
     *  that the application's repaint request should not cause multiple
     *  repaints, since it can be coalesced with repaint requests that
     *  are already pending.
     * 
     *  If the size of the item's content area
     *  changes while it is not visible, calls to this method may be deferred.
     *  If the size had changed while the item was not visible,
     *  sizeChanged will be called at least once before the item
     *  becomes visible once again.
     * 
     *  The default implementation of this method does
     *  nothing.
     * 
     * Parameters:w - the new width of the item's content areah - the new height of the item's content area
     */
    protected void sizeChanged(int w, int h);

    /**
     * Signals that the CustomItem's size and traversal
     *  location need to be updated.
     *  This method is intended to be called by CustomItem
     *  subclass code to inform the implementation that the size of
     *  the CustomItem's content area or the internal
     *  traversal location might need to change.
     *  This often occurs if the contents of the CustomItem
     *  are modified.  A call to this method will return immediately, and it
     *  will cause the container's layout algorithm to run at some point in the
     *  future, possibly resulting in calls to
     * 
     *  getMinContentHeight,
     *  getMinContentWidth,
     *  getPrefContentHeight,
     *  getPrefContentWidth,
     *  sizeChanged, or
     *  traverse.
     * 
     *  The paint method may also be called if
     *  repainting is necessary as a result of the layout operation.
     *  If the content size is invalidated while the
     *  CustomItem is not visible, the
     *  layout operation may be deferred.  The traverse method
     *  will be called if the CustomItem contains the current
     *  traversal location at the time invalidate is called.
     */
    protected final void invalidate();

    /**
     * Implemented by the subclass to render the item within its container.
     *  At the time of the call, the Graphics context's
     *  destination is the content area of this CustomItem
     *  (or back buffer for it).  The
     *  Translation is set so that the upper left corner of the content area is
     *  at (0,0), and the clip is set to the area to be painted.
     *  The application must paint every pixel within the given clip area.  The
     *  item is allowed to modify the clip area, but the system must not allow
     *  any modification to result in drawing outside the bounds of the item's
     *  content area.  The w and h passed
     *  in are the width and height of the
     *  content area of the item.  These values will always be equal to the
     *  values passed with the most recent call to sizeChanged();
     *  they are passed here as well for convenience.
     * 
     *  Other values of the Graphics object are as follows:
     * 
     *  the current color is black;
     *  the font is the same as the font returned by
     *  Font.getDefaultFont();
     *  the stroke style is SOLID;
     * 
     *  The paint() method will be called only after
     *  showNotify() call on this item and before a subsequent
     *  hideNotify() call on this item, in other words, only when
     *  at least a portion of the item is actually visible on the display.
     *  In addition, the paint() method will be called only
     *  if the item's width and height are both greater than zero.
     * 
     * Parameters:g - the Graphics object to be used for
     *  rendering the itemw - current width of the item in pixelsh - current height of the item in pixels
     */
    protected abstract void paint(Graphics g, int w, int h);

    /**
     * Called by subclass code to request that the item be repainted.  If this
     *  item is visible on the display, this will result in a call to
     *  paint() the next time the CustomItem
     *  is to be displayed.  The CustomItem
     *  subclass should call this method when the item's internal state has
     *  been updated such that its visual representation needs to be updated.
     */
    protected final void repaint();

    /**
     * Called by subclass code to request that the specified rectangular area
     *  of the item be repainted.  If that area is visible on the display, this
     *  will result in call to paint with graphics set to
     *  include the specified rectangular area.
     *  The area is specified relative to the CustomItem's
     *  content area.
     *  The CustomItem should call this method when the item's
     *  internal state has been updated and only part of the visual
     *  representation needs to be updated.
     * 
     * Parameters:x - the x coordinate of the rectangular area to be updatedy - the y coordinate of the rectangular area to be updatedw - the width of the rectangular area to be updatedh - the height of the rectangular area to be updated
     */
    protected final void repaint(int x, int y, int w, int h);

    /**
     * Called by the system when traversal has entered the item or has
     *  occurred within the item.  The direction of traversal and the item's
     *  visible rectangle are passed into the method.  The method must do one
     *  of the following: it must either update its state information
     *  pertaining to its internal traversal location, set the return rectangle
     *  to indicate a region associated with this location, and return
     *  true; or, it must return false to indicate
     *  that this item does not support internal traversal, or that that
     *  internal traversal has reached the edge of the item and that traversal
     *  should proceed to the next item if possible.
     * 
     *  The implementation indicates support for internal traversal within a
     *  CustomItem by setting one or both of the
     *  TRAVERSE_HORIZONTAL or
     *  TRAVERSE_VERTICAL bits in the value returned by the
     *  getInteractionModes
     *  method.  The dir parameter indicates the direction of
     *  traversal by using Canvas game actions
     *  Canvas.UP, Canvas.DOWN,
     *  Canvas.LEFT, and Canvas.RIGHT, or the
     *  value NONE, which indicates that there is no specific
     *  direction associated with this traversal event.
     *  If the TRAVERSE_HORIZONTAL bit is set,
     *  this indicates that the Canvas.LEFT and
     *  Canvas.RIGHT values will be
     *  used to indicate the traversal direction.
     *  If the TRAVERSE_VERTICAL bit
     *  is set, this indicates that the Canvas.UP and
     *  Canvas.DOWN values will
     *  be used to indicate the traversal direction.  If both bits are set, all
     *  four direction values may be used for the traversal direction,
     *  indicating that the item should perform two-dimensional traversal.  The
     *  dir parameter may have the value NONE under
     *  any combination of the TRAVERSE_VERTICAL and
     *  TRAVERSE_HORIZONTAL bits.
     * 
     *  Although Canvas game actions are used to indicate the
     *  traversal direction, this does not imply that the keys mapped to these
     *  game actions are being used for traversal, nor that that keys are being
     *  used for traversal at all.
     * 
     *  The viewportWidth and viewportHeight
     *  parameters indicate the size of the viewable area the item's container
     *  has granted to its items.  This represents the largest area of the
     *  item that is likely to be visible at any given time.
     * 
     *  The visRect_inout parameter is used both for passing
     *  information into this method and for returning information from this
     *  method.  It must be an int[4] array.  The information in
     *  this array is a rectangle of the form [x,y,w,h]
     *  where (x,y) is the
     *  location of the upper-left corner of the rectangle relative to the
     *  item's origin, and (w,h) are the width and
     *  height of the rectangle.
     *  The return values placed into this array are significant only when the
     *  traverse() method returns true.
     *  The values are ignored if
     *  the traverse() method returns false.
     * 
     *  When this method is called, the visRect_inout array
     *  contains a rectangle representing the region of the item that is
     *  currently visible.  This region might have zero area if no part of the
     *  item is visible, for example, if it is scrolled offscreen.  The
     *  semantics of the rectangle returned are discussed below.
     * 
     *  The CustomItem must maintain state that tracks
     *  whether traversal is
     *  within this item, and if it is, it must also record the current
     *  internal location.  Initially, traversal is outside the item.  The
     *  first call to the traverse() method indicates
     *  that traversal has
     *  entered the item.  Subsequent calls to this method indicate that
     *  traversal is occurring within this item.  Traversal remains within the
     *  item until the traverseOut method is called.
     *  The CustomItem must keep
     *  track of its traversal state so that it can distinguish traversal
     *  entering the item from traversal within the item.
     * 
     *  When traversal enters the item, the traversal code
     *  should initialize its internal traversal
     *  location to the &quot;first&quot; location appropriate
     *  for the item's structure and the traversal direction.
     *  As an example of the latter policy, if
     *  the traversal direction is DOWN, the initial
     *  location should be the
     *  topmost internal element of the item.  Similarly, if the traversal
     *  direction is UP, the initial location should be the
     *  bottommost element of the item.
     *  The CustomItem
     *  should still choose the &quot;first&quot; location appropriately
     *  even if its primary axis is orthogonal to the
     *  axis of traversal.  For example, suppose the traversal
     *  mode supported is TRAVERSE_VERTICAL but the
     *  CustomItem is structured as a horizontal row
     *  of elements.  If the initial traversal direction is
     *  DOWN, the initial location might be the leftmost
     *  element, and if the initial traversal direction is
     *  UP, the initial location might be the rightmost
     *  element.
     * 
     *  Traversal may enter the item without any specific direction, in
     *  which case the traversal direction will be NONE.  This may
     *  occur if the user selects the item directly (e.g., with a pointing
     *  device), or if the item gains the focus because its containing
     *  Form has become current. The CustomItem
     *  should choose a default traversal location.  If the
     *  CustomItem had been traversed to previously, and if it is
     *  appropriate for the user interface of the CustomItem, the
     *  previous traversal location should be restored.
     * 
     *  When traversal occurs within
     *  the item, the internal traversal location must be moved to the next
     *  appropriate region in the direction of traversal.  The item must report
     *  its updated internal traversal location in the
     *  visRect_inout return parameter as described below and
     *  return true.  The item will typically provide a highlight
     *  to display the internal traversal location to the user.  Thus, the item
     *  will typically also request repaints of the old and new traversal
     *  locations after each traversal event.  There is no requirement that the
     *  area the item requests to be repainted is the same as the area returned
     *  in the visRect_inout rectangle.  The system will combine
     *  any repaint requests with any additional repainting that may occur as a
     *  result of scrolling.
     * 
     *  The traverse() method may be called with a direction of
     *  NONE when the traversal is already within the
     *  CustomItem.  This will occur in response to the
     *  CustomItem subclass code having called the
     *  invalidate() method.  In this case, the
     *  CustomItem should simply return its current notion of the
     *  traversal location.  This mechanism is useful if the
     *  CustomItem needs to update the traversal location
     *  spontaneously (that is, not in response to a traversal event), for
     *  example, because of a change in its contents.
     * 
     *  If the internal traversal location is such that the traversal event
     *  would logically cause traversal to proceed out of the item, the
     *  item should return false from the
     *  traverse() method.  For
     *  example, if the current traversal location is the bottommost internal
     *  element of the item, and the traversal direction is
     *  DOWN, the
     *  traverse() method should simply return
     *  false.  In this
     *  case the method need not update the values in the
     *  visRect_inout array.  The item must leave its internal
     *  traversal location unchanged, and it should not request a repaint to
     *  update its highlighting.  It should defer these actions until the
     *  traverseOut() method is called.
     *  The system will call the traverseOut()
     *  method when traversal actually leaves the item.  The system might not
     *  call the traverseOut() method, even if
     *  traverse() has returned
     *  false, if this item is at the edge of the
     *  Form or there is
     *  no other item beyond to accept the traversal.
     *  Even if the traverse()
     *  method returns false, the traversal location is still
     *  within this item.
     *  It remains within this item until traverseOut() is
     *  called.
     * 
     *  Note the subtle distinction here between the initial
     *  traverse() call
     *  signifying entry into the item and subsequent calls signifying
     *  traversal within the item.  A return value of
     *  false to the initial call indicates that this item
     *  performs no internal traversal at all, whereas a return of
     *  false to subsequent calls indicates that traversal is
     *  within this item and may now exit.
     * 
     *  The width and height of the rectangle returned in the
     *  visRect_inout array are used by the
     *  Form for scrolling
     *  and painting purposes.  The Form must always
     *  position the item so that
     *  the upper left corner of this rectangle, as specified by
     *  the (x,y)
     *  position, is visible.  In addition, the item may also specify a width
     *  and height, in which case the Form will
     *  attempt to position the item so
     *  that as much of this rectangle as possible is visible.  If the width
     *  and height are larger than the size of the viewport, the bottom and
     *  right portions of this rectangle will most likely not be visible to the
     *  user.  The rectangle thus returned will typically denote the size and
     *  location of one of the item's internal elements, and it will also
     *  typically (though not necessarily) correspond to where the element's
     *  highlight will be painted.  Width and height values of zero are legal
     *  and are not treated specially.  Negative values of width and height are
     *  treated as if they were zero.
     * 
     *  There is no requirement on the location of the rectangle returned in
     *  the visRect_inout array with respect to the traversal
     *  direction.  For example, if the CustomItem implements
     *  internal scrolling, a traversal direction of DOWN may
     *  cause the item's contents to scroll upwards far enough so that the
     *  rectangle returned may be above its old location.
     *  CustomItem subclasses must ensure that continued traversal
     *  in one direction will eventually reach the edge of the item and then
     *  traverse out by returning false from this method.
     *  CustomItems must not implement &quot;wraparound&quot;
     *  behavior (for example, traversing downwards from the bottommost element
     *  moves the traversal location to the topmost element) because this will
     *  trap the traversal within the item.
     * 
     *  If the CustomItem consists of internal
     *  elements that are smaller
     *  than the container's viewport, the rectangle returned
     *  should be the
     *  same size as one of these elements.  However, the
     *  CustomItem might have
     *  contents whose elements are larger than the viewport, or it might have
     *  contents having no internal structure.  In either of these cases, the
     *  item should return a rectangle that best represents its idea of the
     *  content area that is important for the user to see.  When traversal
     *  occurs, the item should move its traversal location by an amount based
     *  on the viewport size.  For example, if the viewport is
     *  80 pixels high,
     *  and traversal occurs downwards, the item might move its traversal
     *  location down by 70 pixels in order to display
     *  the next screenful of
     *  content, with 10 pixels overlap for context.
     * 
     *  All internal traversal locations must be reachable regardless of
     *  which traversal modes are provided by the implementation.  This
     *  implies that,
     *  if the implementation provides one-dimensional traversal, the
     *  CustomItem must linearize its internal locations.
     *  For example, suppose the traversal mode is
     *  TRAVERSE_VERTICAL and the CustomItem consists
     *  of a horizontal row of elements.  If the traversal direction is
     *  DOWN the internal traversal location should move to the
     *  right, and if the traversal direction is UP the internal
     *  traversal location should move to the left.  (The foregoing convention
     *  is appropriate for languages that use left-to-right text.  The opposite
     *  convention should be used for languages that use right-to-left text.)
     *  Consider a similar example where the traversal mode is
     *  TRAVERSE_VERTICAL and the CustomItem consists
     *  of a grid of elements.  A traversal direction of DOWN
     *  might proceed leftwards across each row, moving to the next row
     *  downwards when the location reaches the rightmost element in a row.
     * 
     *  If the implementation provides two-dimensional traversal but the
     *  CustomItem is one-dimensional, a traversal direction
     *  along the item's
     *  axis should traverse within the item, and a traversal direction
     *  orthogonal to the item's axis should cause immediate traversal out of
     *  the item by returning false from this method.  For
     *  example, suppose a CustomItem is implementing
     *  a vertical stack of
     *  elements and traversal is already inside the item.  If a traverse event
     *  is received with direction UP or DOWN,
     *  the traverse() method should
     *  move to the next element and return true.  On the other
     *  hand, if a traverse event is received with direction
     *  RIGHT or LEFT, the
     *  traverse() method should always return
     *  false so that
     *  traversal exits the item immediately.  An item that implements internal
     *  traversal should always accept entry - that is, the initial call to
     *  traverse() should return true -
     *  regardless of the axis of the traversal direction.
     * 
     *  If the traverse() method returns
     *  false when traversal
     *  is entering the item, this indicates to the system that the item does
     *  not support internal traversal.  In this case, the item should not
     *  perform any of its own highlighting, and the system will perform
     *  highlighting appropriate for the platform, external to the item.
     * 
     *  The default implementation of the traverse()
     *  method always returns false.
     * 
     * Parameters:dir - the direction of traversal, one of
     *  Canvas.UP, Canvas.DOWN,
     *  Canvas.LEFT, Canvas.RIGHT, or
     *  NONE.viewportWidth - the width of the container's viewportviewportHeight - the height of the container's viewportvisRect_inout - passes the visible rectangle into the method, and
     *  returns the updated traversal rectangle from the method
     * Returns:true if internal traversal had occurred,
     *  false if traversal should proceed outSee Also:getInteractionModes(),
     * traverseOut(),
     * TRAVERSE_HORIZONTAL,
     * TRAVERSE_VERTICAL
     */
    protected boolean traverse(int dir, int viewportWidth, int viewportHeight, int[] visRect_inout);

    /**
     * Called by the system when traversal has occurred out of the item.  This
     *  may occur in response to the CustomItem having returned
     *  false to a previous call to traverse(), if
     *  the user has begun interacting with another item, or if
     *  Form containing
     *  this item is no longer current.  If the CustomItem
     *  is using highlighting to indicate internal traversal,
     *  the CustomItem
     *  should set its state to be unhighlighted and request a repaint.  (Note
     *  that painting will not occur if the item is no longer visible.)
     * 
     * See Also:getInteractionModes(),
     * traverse(int, int, int, int[]),
     * TRAVERSE_HORIZONTAL,
     * TRAVERSE_VERTICAL
     */
    protected void traverseOut();

    /**
     * Called by the system when a key is pressed.  The implementation
     *  indicates support for delivery of key press events by setting the
     *  KEY_PRESS bit in the value returned by the
     *  getInteractionModes method.
     * 
     * Parameters:keyCode - the key code of the key that has been pressedSee Also:getInteractionModes()
     */
    protected void keyPressed(int keyCode);

    /**
     * Called by the system when a key is released.  The implementation
     *  indicates support for delivery of key release events by setting the
     *  KEY_RELEASE bit in the value returned by the
     *  getInteractionModes method.
     * 
     * Parameters:keyCode - the key code of the key that has been releasedSee Also:getInteractionModes()
     */
    protected void keyReleased(int keyCode);

    /**
     * Called by the system when a key is repeated.  The implementation
     *  indicates support for delivery of key repeat events by setting the
     *  KEY_REPEAT bit in the value returned by the
     *  getInteractionModes method.
     * 
     * Parameters:keyCode - the key code of the key that has been repeatedSee Also:getInteractionModes()
     */
    protected void keyRepeated(int keyCode);

    /**
     * Called by the system when a pointer down action (for example, a pen
     *  tap) has occurred within the item.  The (x,y)
     *  coordinates are relative
     *  to the origin of the item, and they will always indicate a location
     *  within the item.  The implementation indicates support for delivery of
     *  pointer press events by setting the POINTER_PRESS
     *  bit in the value
     *  returned by the getInteractionModes method.
     * 
     * Parameters:x - the x coordinate of the pointer downy - the y coordinate of the pointer downSee Also:getInteractionModes()
     */
    protected void pointerPressed(int x, int y);

    /**
     * Called by the system when a pointer up action (for example, a pen lift)
     *  has occurred after a pointer down action had occurred within the item.
     *  The (x,y) coordinates are relative to the origin
     *  of the item.
     *  Implementations should deliver a pointer release event to an item even
     *  if the pointer has moved outside the item when the release occurs.  In
     *  this case the (x,y) coordinates may indicate a
     *  location outside the
     *  bounds of the item.  The implementation indicates support for delivery
     *  of pointer release events by setting the
     *  POINTER_RELEASE bit in the
     *  value returned by the getInteractionModes method.
     * 
     * Parameters:x - the x coordinate of the pointer upy - the x coordinate of the pointer upSee Also:getInteractionModes()
     */
    protected void pointerReleased(int x, int y);

    /**
     * Called by the system when a pointer drag action (for example, pen
     *  motion after a press but before a release) has occurred within the item.
     *  The (x,y) coordinates are relative to the origin
     *  of the item.
     *  Implementations should deliver pointer drag events to an item even if
     *  the pointer is being moved outside the item.  In this case
     *  the (x,y)
     *  coordinates may indicate a location outside the bounds of the item.
     *  The implementation indicates support for delivery of pointer release
     *  events by setting the POINTER_DRAG bit in the
     *  value returned by the
     *  getInteractionModes method.
     * 
     * Parameters:x - the x coordinate of the pointer dragy - the x coordinate of the pointer dragSee Also:getInteractionModes()
     */
    protected void pointerDragged(int x, int y);

    /**
     * Called by the system to notify the item that it is now at least
     *  partially visible, when it previously had been completely invisible.
     *  The item may receive paint() calls after
     *  showNotify() has been called.
     * 
     *  The default implementation of this method does nothing.
     */
    protected void showNotify();

    /**
     * Called by the system to notify the item that it is now completely
     *  invisible, when it previously had been at least partially visible.  No
     *  further paint() calls will be made on this item
     *  until after a showNotify() has been called again.
     * 
     *  The default implementation of this method does nothing.
     */
    protected void hideNotify();

}

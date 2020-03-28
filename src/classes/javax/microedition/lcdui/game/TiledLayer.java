package javax.microedition.lcdui.game;

public class TiledLayer extends Layer {
    /**
     * Creates a new TiledLayer.
     * 
     *  The TiledLayer's grid will be rows cells high and
     *  columns cells wide.  All cells in the grid are initially
     *  empty (i.e. they contain tile index 0).  The contents of the grid may
     *  be modified through the use of setCell(int, int, int) and fillCells(int, int, int, int, int).
     * 
     *  The static tile set for the TiledLayer is created from the specified
     *  Image with each tile having the dimensions of tileWidth x tileHeight.
     *  The width of the source image must be an integer multiple of
     *  the tile width, and the height of the source image must be an integer
     *  multiple of the tile height; otherwise, an IllegalArgumentException
     *  is thrown;
     * 
     *  The entire static tile set can be changed using
     *  setStaticTileSet(Image, int, int).
     *  These methods should be used sparingly since they are both
     *  memory and time consuming.
     *  Where possible, animated tiles should be used instead to
     *  animate tile appearance.
     * 
     * Parameters:columns - the width of the TiledLayer,
     *  expressed as a number of cellsrows - the height of the TiledLayer,
     *  expressed as a number of cellsimage - the Image to use for creating
     *   the static tile settileWidth - the width in pixels of a single tiletileHeight - the height in pixels of a single tile
     * Throws:
     * NullPointerException - if image is null
     * IllegalArgumentException - if the number of rows
     *   or columns is less than 1
     * IllegalArgumentException - if tileHeight
     *   or tileWidth is less than 1
     * IllegalArgumentException - if the image
     *   width is not an integer multiple of the tileWidth
     * IllegalArgumentException - if the image
     *  height is not an integer multiple of the tileHeight
     */
    public TiledLayer(int columns, int rows, Image image, int tileWidth, int tileHeight) {
        construct(columns, rows, image, tileWidth, tileHeight);
    }

    private native void construct(int columns, int rows, Image image, int tileWidth, int tileHeight);

    /**
     * Creates a new animated tile and returns the index that refers
     *  to the new animated tile.  It is initially associated with
     *  the specified tile index (either a static tile or 0).
     * 
     *  The indices for animated tiles are always negative.  The first
     *  animated tile shall have the index -1, the second, -2, etc.
     * 
     * Parameters:staticTileIndex - the index of the associated tile
     *  (must be 0 or a valid static tile index)
     * Returns:the index of newly created animated tile
     * Throws:
     * IndexOutOfBoundsException - if the
     *  staticTileIndex is invalid
     */
    public int createAnimatedTile(int staticTileIndex);

    /**
     * Associates an animated tile with the specified static tile.
     * 
     * Parameters:animatedTileIndex - the index of the animated tilestaticTileIndex - the index of the associated tile
     *  (must be 0 or a valid static tile index)
     * Throws:
     * IndexOutOfBoundsException - if the
     *  staticTileIndex is invalid
     * IndexOutOfBoundsException - if the animated tile index
     *  is invalidSee Also:getAnimatedTile(int)
     */
    public void setAnimatedTile(int animatedTileIndex, int staticTileIndex);

    /**
     * Gets the tile referenced by an animated tile.
     * 
     *  Returns the tile index currently associated with the
     *  animated tile.
     * 
     * Parameters:animatedTileIndex - the index of the animated tile
     * Returns:the index of the tile reference by the animated tile
     * Throws:
     * IndexOutOfBoundsException - if the animated tile index
     *  is invalidSee Also:setAnimatedTile(int, int)
     */
    public int getAnimatedTile(int animatedTileIndex);

    /**
     * Sets the contents of a cell.
     * 
     *  The contents may be set to a static tile index, an animated
     *  tile index, or it may be left empty (index 0)
     * 
     * Parameters:col - the column of cell to setrow - the row of cell to settileIndex - the index of tile to place in cell
     * Throws:
     * IndexOutOfBoundsException - if there is no tile with index
     *          tileIndex
     * IndexOutOfBoundsException - if row or
     *          col is outside the bounds of the
     *          TiledLayer gridSee Also:getCell(int, int),
     * fillCells(int, int, int, int, int)
     */
    public void setCell(int col, int row, int tileIndex);

    /**
     * Gets the contents of a cell.
     * 
     *  Gets the index of the static or animated tile currently displayed in
     *  a cell.  The returned index will be 0 if the cell is empty.
     * 
     * Parameters:col - the column of cell to checkrow - the row of cell to check
     * Returns:the index of tile in cell
     * Throws:
     * IndexOutOfBoundsException - if row or
     *          col is outside the bounds of the
     *          TiledLayer gridSee Also:setCell(int, int, int),
     * fillCells(int, int, int, int, int)
     */
    public int getCell(int col, int row);

    /**
     * Fills a region cells with the specific tile.  The cells may be filled
     *  with a static tile index, an animated tile index, or they may be left
     *  empty (index 0).
     * 
     * Parameters:col - the column of top-left cell in the regionrow - the row of top-left cell in the regionnumCols - the number of columns in the regionnumRows - the number of rows in the regiontileIndex - the Index of the tile to place in all cells in the
     *  specified region
     * Throws:
     * IndexOutOfBoundsException - if the rectangular region
     *          defined by the parameters extends beyond the bounds of the
     *          TiledLayer grid
     * IllegalArgumentException - if numCols is less
     *  than zero
     * IllegalArgumentException - if numRows is less
     *  than zero
     * IndexOutOfBoundsException - if there is no tile with
     *          index tileIndexSee Also:setCell(int, int, int),
     * getCell(int, int)
     */
    public void fillCells(int col, int row, int numCols, int numRows, int tileIndex);

    /**
     * Gets the width of a single cell, in pixels.
     * 
     * Returns:the width in pixels of a single cell in the
     *  TiledLayer grid
     */
    public final int getCellWidth();

    /**
     * Gets the height of a single cell, in pixels.
     * 
     * Returns:the height in pixels of a single cell in the
     *  TiledLayer grid
     */
    public final int getCellHeight();

    /**
     * Gets the number of columns in the TiledLayer grid.
     *  The overall width of the TiledLayer, in pixels,
     *  may be obtained by calling Layer.getWidth().
     * 
     * Returns:the width in columns of the
     *  TiledLayer grid
     */
    public final int getColumns();

    /**
     * Gets the number of rows in the TiledLayer grid.  The overall
     *  height of the TiledLayer, in pixels, may be obtained by
     *  calling Layer.getHeight().
     * 
     * Returns:the height in rows of the
     *  TiledLayer grid
     */
    public final int getRows();

    /**
     * Change the static tile set.
     * 
     *  Replaces the current static tile set with a new static tile set.
     *  See the constructor TiledLayer(int, int, Image, int, int)
     *  for information on how the tiles are created from the
     *  image.
     * 
     *  If the new static tile set has as many or more tiles than the
     *  previous static tile set,
     *  the the animated tiles and cell contents will be preserve.  If
     *  not, the contents of
     *  the grid will be cleared (all cells will contain index 0) and
     *  all animated tiles
     *  will be deleted.
     * 
     * Parameters:image - the Image to use for creating the
     *  static tile settileWidth - the width in pixels of a single tiletileHeight - the height in pixels of a single tile
     * Throws:
     * NullPointerException - if image is null
     * IllegalArgumentException - if tileHeight
     *   or tileWidth is less than 1
     * IllegalArgumentException - if the image
     *   width is not an integer  multiple of the tileWidth
     * IllegalArgumentException - if the image
     *   height is not an integer  multiple of the tileHeight
     */
    public void setStaticTileSet(Image image, int tileWidth, int tileHeight);

    /**
     * Draws the TiledLayer.
     * 
     *  The entire TiledLayer is rendered subject to the clip region of
     *  the Graphics object.
     *  The TiledLayer's upper left corner is rendered at the
     *  TiledLayer's current
     *  position relative to the origin of the Graphics object.   The current
     *  position of the TiledLayer's upper-left corner can be retrieved by
     *  calling Layer.getX() and Layer.getY().
     *  The appropriate use of a clip region and/or translation allows
     *  an arbitrary region
     *  of the TiledLayer to be rendered.
     * 
     *  If the TiledLayer's Image is mutable, the TiledLayer is rendered
     *  using the current contents of the Image.
     * 
     * Specified by:paint in class Layer
     * 
     * Parameters:g - the graphics object to draw the TiledLayer
     * Throws:
     * NullPointerException - if g is null
     */
    public final void paint(Graphics g);

}

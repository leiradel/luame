package javax.microedition.lcdui;

public class Ticker {
    /**
     * Constructs a new Ticker object, given its initial
     *  contents string.
     * 
     * Parameters:str - string to be set for the Ticker
     * Throws:
     * NullPointerException - if str is null
     */
    public Ticker(String str) {
        construct(str);
    }

    private native void construct(String str);

    /**
     * Sets the string to be displayed by this ticker. If this ticker is active
     *  and is on the display, it immediately begins showing the new string.
     * 
     * Parameters:str - string to be set for the Ticker
     * Throws:
     * NullPointerException - if str is nullSee Also:getString()
     */
    public void setString(String str);

    /**
     * Gets the string currently being scrolled by the ticker.
     * 
     * Returns:string of the tickerSee Also:setString(java.lang.String)
     */
    public String getString();

}

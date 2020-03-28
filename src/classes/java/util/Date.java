package java.util;

public class Date {
    /**
     * Allocates a Date object and initializes it to
     *  represent the current time specified number of milliseconds since the
     *  standard base time known as "the epoch", namely January 1,
     *  1970, 00:00:00 GMT.
     * 
     * See Also:System.currentTimeMillis()
     */
    public Date() {
        construct();
    }

    private native void construct();

    /**
     * Allocates a Date object and initializes it to
     *  represent the specified number of milliseconds since the
     *  standard base time known as "the epoch", namely January 1,
     *  1970, 00:00:00 GMT.
     * 
     * Parameters:date - the milliseconds since January 1, 1970, 00:00:00 GMT.See Also:System.currentTimeMillis()
     */
    public Date(long date) {
        construct(date);
    }

    private native void construct(long date);

    /**
     * Returns the number of milliseconds since January 1, 1970, 00:00:00 GMT
     *  represented by this Date object.
     * 
     * Returns:the number of milliseconds since January 1, 1970, 00:00:00 GMT
     *           represented by this date.See Also:setTime(long)
     */
    public long getTime();

    /**
     * Sets this Date object to represent a point in time that is
     *  time milliseconds after January 1, 1970 00:00:00 GMT.
     * 
     * Parameters:time - the number of milliseconds.See Also:getTime()
     */
    public void setTime(long time);

    /**
     * Compares two dates for equality.
     *  The result is true if and only if the argument is
     *  not null and is a Date object that
     *  represents the same point in time, to the millisecond, as this object.
     * 
     *  Thus, two Date objects are equal if and only if the
     *  getTime method returns the same long
     *  value for both.
     * 
     * Overrides:equals in class Object
     * 
     * Parameters:obj - the object to compare with.
     * Returns:true if the objects are the same;
     *           false otherwise.See Also:getTime()
     */
    public boolean equals(Object obj);

    /**
     * Returns a hash code value for this object. The result is the
     *  exclusive OR of the two halves of the primitive long
     *  value returned by the getTime()
     *  method. That is, the hash code is the value of the expression:
     * 
     *  (int)(this.getTime()^(this.getTime() >>> 32))
     * 
     * Overrides:hashCode in class Object
     * 
     * Returns:a hash code value for this object.See Also:Object.equals(java.lang.Object),
     * Hashtable
     */
    public int hashCode();

    /**
     * Converts this Date object to a String
     *  of the form:
     * 
     *  dow mon dd hh:mm:ss zzz yyyy
     *  where:
     *  dow is the day of the week (Sun, Mon, Tue, Wed,
     *      Thu, Fri, Sat).
     *  mon is the month (Jan, Feb, Mar, Apr, May, Jun,
     *      Jul, Aug, Sep, Oct, Nov, Dec).
     *  dd is the day of the month (01 through
     *      31), as two decimal digits.
     *  hh is the hour of the day (00 through
     *      23), as two decimal digits.
     *  mm is the minute within the hour (00 through
     *      59), as two decimal digits.
     *  ss is the second within the minute (00 through
     *      61, as two decimal digits.
     *  zzz is the time zone (and may reflect daylight savings
     *      time). If time zone information is not available,
     *      then zzz is empty - that is, it consists
     *      of no characters at all.
     *  yyyy is the year, as four decimal digits.
     * 
     * Overrides:toString in class Object
     * 
     * Returns:a string representation of this date.Since:
     *   CLDC 1.1
     */
    public String toString();

}

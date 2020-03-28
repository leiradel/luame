package java.util;

public class TimeZone {
    public TimeZone() {
        construct();
    }

    private native void construct();

    /**
     * Gets offset, for current date, modified in case of
     *  daylight savings. This is the offset to add *to* GMT to get local time.
     *  Gets the time zone offset, for current date, modified in case of daylight
     *  savings. This is the offset to add *to* GMT to get local time. Assume
     *  that the start and end month are distinct. This method may return incorrect
     *  results for rules that start at the end of February (e.g., last Sunday in
     *  February) or the beginning of March (e.g., March 1).
     * 
     * Parameters:era - The era of the given date (0 = BC, 1 = AD).year - The year in the given date.month - The month in the given date. Month is 0-based. e.g.,
     *                       0 for January.day - The day-in-month of the given date.dayOfWeek - The day-of-week of the given date.millis - The milliseconds in day in standard local time.
     * Returns:The offset to add *to* GMT to get local time.
     * Throws:
     * IllegalArgumentException - the era, month, day,
     *  dayOfWeek, or millis parameters are out of range
     */
    public abstract int getOffset(int era, int year, int month, int day, int dayOfWeek, int millis);

    /**
     * Gets the GMT offset for this time zone.
     * 
     * Returns:the GMT offset for this time zone.
     */
    public abstract int getRawOffset();

    /**
     * Queries if this time zone uses Daylight Savings Time.
     * 
     * Returns:if this time zone uses Daylight Savings Time.
     */
    public abstract boolean useDaylightTime();

    /**
     * Gets the ID of this time zone.
     * 
     * Returns:the ID of this time zone.
     */
    public String getID();

    /**
     * Gets the TimeZone for the given ID.
     * 
     * Parameters:ID - the ID for a TimeZone, either an abbreviation such as
     *  "GMT", or a full name such as "America/Los_Angeles".
     *   The only time zone ID that is required to be supported is "GMT".
     * Returns:the specified TimeZone, or the GMT zone if the given ID cannot be
     *  understood.
     */
    public static TimeZone getTimeZone(String ID);

    /**
     * Gets the default TimeZone for this host.
     *  The source of the default TimeZone
     *  may vary with implementation.
     * 
     * Returns:a default TimeZone.
     */
    public static TimeZone getDefault();

    /**
     * Gets all the available IDs supported.
     * 
     * Returns:an array of IDs.
     */
    public static String[] getAvailableIDs();

}

package java.lang;

public class System {
    /**
     * The "standard" output stream. This stream is already
     *  open and ready to accept output data. Typically this stream
     *  corresponds to display output or another output destination
     *  specified by the host environment or user.
     * 
     *  For simple stand-alone Java applications, a typical way to write
     *  a line of output data is:
     * 
     *      System.out.println(data)
     * 
     *  See the println methods in class PrintStream.
     * 
     * See Also:PrintStream.println(),
     * PrintStream.println(boolean),
     * PrintStream.println(char),
     * PrintStream.println(char[]),
     * PrintStream.println(int),
     * PrintStream.println(long),
     * PrintStream.println(java.lang.Object),
     * PrintStream.println(java.lang.String)
     */
    public static final PrintStream out = ;

    /**
     * The "standard" error output stream. This stream is already
     *  open and ready to accept output data.
     * 
     *  Typically this stream corresponds to display output or another
     *  output destination specified by the host environment or user. By
     *  convention, this output stream is used to display error messages
     *  or other information that should come to the immediate attention
     *  of a user even if the principal output stream, the value of the
     *  variable out, has been redirected to a file or other
     *  destination that is typically not continuously monitored.
     */
    public static final PrintStream err = ;

    /**
     * Returns the current time in milliseconds.
     * 
     * Returns:the difference, measured in milliseconds, between the current
     *           time and midnight, January 1, 1970 UTC.
     */
    public static long currentTimeMillis();

    /**
     * Copies an array from the specified source array, beginning at the
     *  specified position, to the specified position of the destination array.
     *  A subsequence of array components are copied from the source
     *  array referenced by src to the destination array
     *  referenced by dst. The number of components copied is
     *  equal to the length argument. The components at
     *  positions srcOffset through
     *  srcOffset+length-1 in the source array are copied into
     *  positions dstOffset through
     *  dstOffset+length-1, respectively, of the destination
     *  array.
     * 
     *  If the src and dst arguments refer to the
     *  same array object, then the copying is performed as if the
     *  components at positions srcOffset through
     *  srcOffset+length-1 were first copied to a temporary
     *  array with length components and then the contents of
     *  the temporary array were copied into positions
     *  dstOffset through dstOffset+length-1 of the
     *  destination array.
     * 
     *  If dst is null, then a
     *  NullPointerException is thrown.
     * 
     *  If src is null, then a
     *  NullPointerException is thrown and the destination
     *  array is not modified.
     * 
     *  Otherwise, if any of the following is true, an
     *  ArrayStoreException is thrown and the destination is
     *  not modified:
     * 
     *  The src argument refers to an object that is not an
     *      array.
     *  The dst argument refers to an object that is not an
     *      array.
     *  The src argument and dst argument refer to
     *      arrays whose component types are different primitive types.
     *  The src argument refers to an array with a primitive
     *      component type and the dst argument refers to an array
     *      with a reference component type.
     *  The src argument refers to an array with a reference
     *      component type and the dst argument refers to an array
     *      with a primitive component type.
     * 
     *  Otherwise, if any of the following is true, an
     *  IndexOutOfBoundsException is
     *  thrown and the destination is not modified:
     * 
     *  The srcOffset argument is negative.
     *  The dstOffset argument is negative.
     *  The length argument is negative.
     *  srcOffset+length is greater than
     *      src.length, the length of the source array.
     *  dstOffset+length is greater than
     *      dst.length, the length of the destination array.
     * 
     *  Otherwise, if any actual component of the source array from
     *  position srcOffset through
     *  srcOffset+length-1 cannot be converted to the component
     *  type of the destination array by assignment conversion, an
     *  ArrayStoreException is thrown. In this case, let
     *  k be the smallest nonnegative integer less than
     *  length such that src[srcOffset+k]
     *  cannot be converted to the component type of the destination
     *  array; when the exception is thrown, source array components from
     *  positions srcOffset through
     *  srcOffset+k-1
     *  will already have been copied to destination array positions
     *  dstOffset through
     *  dstOffset+k-1 and no other
     *  positions of the destination array will have been modified.
     *  (Because of the restrictions already itemized, this
     *  paragraph effectively applies only to the situation where both
     *  arrays have component types that are reference types.)
     * 
     * Parameters:src - the source array.src_position - start position in the source array.dst - the destination array.dst_position - pos   start position in the destination data.length - the number of array elements to be copied.
     * Throws:
     * IndexOutOfBoundsException - if copying would cause
     *                access of data outside array bounds.
     * ArrayStoreException - if an element in the src
     *                array could not be stored into the dest array
     *                because of a type mismatch.
     * NullPointerException - if either src or
     *                dst is null.
     */
    public static void arraycopy(Object src,
                             int src_position,
                             Object dst,
                             int dst_position,
                             int length);

    /**
     * Returns the same hashcode for the given object as
     *  would be returned by the default method hashCode(),
     *  whether or not the given object's class overrides
     *  hashCode().
     *  The hashcode for the null reference is zero.
     * 
     * Parameters:x - object for which the hashCode is to be calculated
     * Returns:the hashCodeSince:
     *   JDK1.1
     */
    public static int identityHashCode(Object x);

    /**
     * Gets the system property indicated by the specified key.
     * 
     * Parameters:key - the name of the system property.
     * Returns:the string value of the system property,
     *              or null if there is no property with that key.
     * Throws:
     * NullPointerException - if key is
     *              null.
     * IllegalArgumentException - if key is empty.
     */
    public static String getProperty(String key);

    /**
     * Terminates the currently running Java application. The
     *  argument serves as a status code; by convention, a nonzero
     *  status code indicates abnormal termination.
     * 
     *  This method calls the exit method in class
     *  Runtime. This method never returns normally.
     * 
     *  The call System.exit(n) is effectively equivalent
     *  to the call:
     * 
     *  Runtime.getRuntime().exit(n)
     * 
     * Parameters:status - exit status.See Also:Runtime.exit(int)
     */
    public static void exit(int status);

}

package java.lang;

public class Class {
    /**
     * Converts the object to a string. The string representation is the
     *  string "class" or "interface", followed by a space, and then by the
     *  fully qualified name of the class in the format returned by
     *  getName.  If this Class object represents a
     *  primitive type, this method returns the name of the primitive type.  If
     *  this Class object represents void this method returns
     *  "void".
     * 
     * Overrides:toString in class Object
     * 
     * Returns:a string representation of this class object.
     */
    public String toString();

    /**
     * Returns the Class object associated with the class
     *  with the given string name.
     *  Given the fully-qualified name for a class or interface, this
     *  method attempts to locate, load and link the class.  If it
     *  succeeds, returns the Class object representing the class.  If
     *  it fails, the method throws a ClassNotFoundException.
     * 
     *  For example, the following code fragment returns the runtime
     *  Class descriptor for the class named
     *  java.lang.Thread:
     * 
     *    Class&nbsp;t&nbsp;= Class.forName("java.lang.Thread")
     * 
     * Parameters:className - the fully qualified name of the desired class.
     * Returns:the Class descriptor for the class with the
     *              specified name.
     * Throws:
     * ClassNotFoundException - if the class could not be found.Since:
     *   JDK1.0
     */
    public static Class forName(String className) throws ClassNotFoundException;

    /**
     * Creates a new instance of a class.
     * 
     * Returns:a newly allocated instance of the class represented by this
     *              object. This is done exactly as if by a new
     *              expression with an empty argument list.
     * Throws:
     * IllegalAccessException - if the class or initializer is
     *                not accessible.
     * InstantiationException - if an application tries to
     *                instantiate an abstract class or an interface, or if the
     *                instantiation fails for some other reason.Since:
     *   JDK1.0
     */
    public Object newInstance() throws InstantiationException, IllegalAccessException;

    /**
     * Determines if the specified Object is assignment-compatible
     *  with the object represented by this Class.  This method is
     *  the dynamic equivalent of the Java language instanceof
     *  operator. The method returns true if the specified
     *  Object argument is non-null and can be cast to the
     *  reference type represented by this Class object without
     *  raising a ClassCastException. It returns false
     *  otherwise.
     * 
     *   Specifically, if this Class object represents a
     *  declared class, this method returns true if the specified
     *  Object argument is an instance of the represented class (or
     *  of any of its subclasses); it returns false otherwise. If
     *  this Class object represents an array class, this method
     *  returns true if the specified Object argument
     *  can be converted to an object of the array class by an identity
     *  conversion or by a widening reference conversion; it returns
     *  false otherwise. If this Class object
     *  represents an interface, this method returns true if the
     *  class or any superclass of the specified Object argument
     *  implements this interface; it returns false otherwise. If
     *  this Class object represents a primitive type, this method
     *  returns false.
     * 
     * Parameters:obj - the object to check
     * Returns:true if obj is an instance of this classSince:
     *   JDK1.1
     */
    public boolean isInstance(Object obj);

    /**
     * Determines if the class or interface represented by this
     *  Class object is either the same as, or is a superclass or
     *  superinterface of, the class or interface represented by the specified
     *  Class parameter. It returns true if so;
     *  otherwise it returns false. If this Class
     *  object represents a primitive type, this method returns
     *  true if the specified Class parameter is
     *  exactly this Class object; otherwise it returns
     *  false.
     * 
     *   Specifically, this method tests whether the type represented by the
     *  specified Class parameter can be converted to the type
     *  represented by this Class object via an identity conversion
     *  or via a widening reference conversion. See The Java Language
     *  Specification, sections 5.1.1 and 5.1.4 , for details.
     * 
     * Parameters:cls - the Class object to be checked
     * Returns:the boolean value indicating whether objects of the
     *  type cls can be assigned to objects of this class
     * Throws:
     * NullPointerException - if the specified Class parameter is
     *             null.Since:
     *   JDK1.1
     */
    public boolean isAssignableFrom(Class cls);

    /**
     * Determines if the specified Class object represents an
     *  interface type.
     * 
     * Returns:true if this object represents an interface;
     *           false otherwise.
     */
    public boolean isInterface();

    /**
     * Determines if this Class object represents an array class.
     * 
     * Returns:true if this object represents an array class;
     *           false otherwise.Since:
     *   JDK1.1
     */
    public boolean isArray();

    /**
     * Returns the fully-qualified name of the entity (class, interface, array
     *  class, primitive type, or void) represented by this Class
     *  object, as a String.
     * 
     *   If this Class object represents a class of arrays, then
     *  the internal form of the name consists of the name of the element type
     *  in Java signature format, preceded by one or more "["
     *  characters representing the depth of array nesting. Thus:
     * 
     *  (new Object[3]).getClass().getName()
     * 
     *  returns "[Ljava.lang.Object;" and:
     * 
     *  (new int[3][4][5][6][7][8][9]).getClass().getName()
     * 
     *  returns "[[[[[[[I". The encoding of element type names
     *  is as follows:
     * 
     *  B            byte
     *  C            char
     *  D            double
     *  F            float
     *  I            int
     *  J            long
     *  Lclassname;  class or interface
     *  S            short
     *  Z            boolean
     * 
     *  The class or interface name classname is given in fully
     *  qualified form as shown in the example above.
     * 
     * Returns:the fully qualified name of the class or interface
     *           represented by this object.
     */
    public String getName();

    /**
     * Finds a resource with a given name.  This method returns null if no
     *  resource with this name is found.  The rules for searching
     *  resources associated with a given class are profile
     *  specific.
     * 
     * Parameters:name - name of the desired resource
     * Returns:a java.io.InputStream object.Since:
     *   JDK1.1
     */
    public InputStream getResourceAsStream(String name);

}

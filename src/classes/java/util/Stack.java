package java.util;

public class Stack extends Vector {
    /**
     * Pushes an item onto the top of this stack. This has exactly
     *  the same effect as:
     * 
     *  addElement(item)
     * 
     * Parameters:item - the item to be pushed onto this stack.
     * Returns:the item argument.See Also:Vector.addElement(java.lang.Object)
     */
    public Object push(Object item);

    /**
     * Removes the object at the top of this stack and returns that
     *  object as the value of this function.
     * 
     * Returns:The object at the top of this stack (the last item
     *              of the Vector object).
     * Throws:
     * EmptyStackException - if this stack is empty.
     */
    public Object pop();

    /**
     * Looks at the object at the top of this stack without removing it
     *  from the stack.
     * 
     * Returns:the object at the top of this stack (the last item
     *              of the Vector object).
     * Throws:
     * EmptyStackException - if this stack is empty.
     */
    public Object peek();

    /**
     * Tests if this stack is empty.
     * 
     * Returns:true if and only if this stack contains
     *           no items; false otherwise.
     */
    public boolean empty();

}

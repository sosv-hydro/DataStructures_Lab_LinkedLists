package linkedLists;

import java.util.NoSuchElementException;

public abstract class AbstractLinkedList implements LinkedList<Object>{

	/*
	 * An error (Object.clone() cannot hide the public abstract method) is visible on the rest of the classes
	 * that implement LinkedList<E> interface. when trying to extend this abstract methid, for the two implementations 
	 * used in this class. This error prevents the program to be run. 
	 * 
	 * 'It is not possible to access the clone method on an abstract type. Most interfaces and abstract classes in Java do not 
	 * have to specify a public clone method. As a result, the clone method is used only if the actual class of an object
	 *  is known which is against the abstraction principle of using the most generic type possible"

	 * 
	 */
	
	
	
	public Object clone() throws CloneNotSupportedException{
		return super.clone();
		
	}

}

package linkedLists;

import java.util.NoSuchElementException;

import linkedLists.AbstractSLList.SNode;

public class DLDHDTList<E> extends AbstractDLList<E> {
	private DNode<E> header, trailer; 
	private int length; 
	
	public DLDHDTList() { 
		header = new DNode<>();
		trailer = new DNode<>();
		header.setNext(trailer);
		trailer.setPrev(header);
		length = 0;
	}
	
	public void addFirstNode(Node<E> nuevo) {
		addNodeAfter(header, nuevo); 
	}
	
	public void addLastNode(Node<E> nuevo) { 
		DNode<E> dnuevo = (DNode<E>) nuevo; 
		DNode<E> nBefore = trailer.getPrev();  
		nBefore.setNext(dnuevo); 
		trailer.setPrev(dnuevo); 
		dnuevo.setPrev(nBefore); 
		dnuevo.setNext(trailer); 
		length++; 
	}

	public void addNodeAfter(Node<E> target, Node<E> nuevo) {
		DNode<E> dnuevo = (DNode<E>) nuevo; 
		DNode<E> nBefore = (DNode<E>) target; 
		DNode<E> nAfter = nBefore.getNext(); 
		nBefore.setNext(dnuevo); 
		nAfter.setPrev(dnuevo); 
		dnuevo.setPrev(nBefore); 
		dnuevo.setNext(nAfter); 
		length++; 
	}

	public void addNodeBefore(Node<E> target, Node<E> nuevo) {
		DNode<E> dnuevo = (DNode<E>) nuevo; 
		DNode<E> nBefore = (DNode<E>) this.getNodeBefore(target);
		DNode<E> tar = nBefore.getNext();  
		nBefore.setNext(dnuevo); 
		tar.setPrev(dnuevo); 
		dnuevo.setPrev(nBefore); 
		dnuevo.setNext(tar); 
		length++; 
	}

	public Node<E> createNewNode() {
		return new DNode<E>();
	}

	public Node<E> getFirstNode() throws NoSuchElementException {
		if (length == 0) 
			throw new NoSuchElementException("List is empty."); 
		return header.getNext();
	}

	public Node<E> getLastNode() throws NoSuchElementException {
		if (length == 0) 
			throw new NoSuchElementException("List is empty."); 
		return trailer.getPrev();
	}

	public Node<E> getNodeAfter(Node<E> target)
			throws NoSuchElementException {
		if(length == 0) 
			throw new NoSuchElementException("List is empty.");
		DNode<E> tar = (DNode<E>) target;
		if (tar == trailer)
			throw new NoSuchElementException("No nodes after trailer");
		DNode<E> temp = header;
		while (temp != tar)
			temp = temp.getNext();
		return temp.getNext(); 
	}

	public Node<E> getNodeBefore(Node<E> target)
			throws NoSuchElementException {
		if(length == 0) 
			throw new NoSuchElementException("List is empty.");
		DNode<E> tar = (DNode<E>) target;
		if (tar == header)
			throw new NoSuchElementException("No nodes before header");
		DNode<E> temp = header;
		while (temp != tar)
			temp = temp.getNext();
		return temp.getPrev();  
		
	}

	public int length() {
		return length;
	}

	public void removeNode(Node<E> target) {
		DNode<E> tar = (DNode<E>) target;
		DNode<E> temp = header;
		while (temp != tar)
			temp = temp.getNext();
		DNode<E> nPrev = temp.getPrev();
		DNode<E> nAfter = temp.getNext();
		nPrev.setNext(nAfter);
		nAfter.setPrev(nPrev);
		temp.clean();
		trailer = null;
		length--;
	}
	
	/**
	 * Prepares every node so that the garbage collector can free 
	 * its memory space, at least from the point of view of the
	 * list. This method is supposed to be used whenever the 
	 * list object is not going to be used anymore. Removes all
	 * physical nodes (data nodes and control nodes, if any)
	 * from the linked list
	 */
	private void destroy() {
		while (header != null) { 
			DNode<E> nnode = header.getNext(); 
			header.clean(); 
			header = nnode; 
		}
	}
	
	/**
	 * The execution of this method removes all the data nodes from
	 * the current instance of the list, leaving it as a valid empty
	 * doubly linked list with dummy header and dummy trailer nodes. 
	 */
	public void makeEmpty() { 
		while(header.getNext() != trailer){
			DNode<E> ntr = header.getNext();
			ntr.getNext().setPrev(header);
			header.setNext(ntr.getNext());
			//For the GC
			ntr.clean();
			ntr.setElement(null);
		}
	}
		
	protected void finalize() throws Throwable {
	    try {
			this.destroy(); 
	    } finally {
	        super.finalize();
	    }
	}

	public Object clone() throws CloneNotSupportedException{
		return super.clone();
		
	}
}

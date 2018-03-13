package linkedLists;
/**
 * Singly linked list with references to its first and its
 * last node. 
 * 
 * @author pirvos
 *
 */

import java.util.Arrays;
import java.util.NoSuchElementException;

public class SLFLList<E> extends SLList<E> {
	private SNode<E> first, last; // reference to the first node and to the last
									// node
	int length;

	public SLFLList() { // to create an empty list instance
		first = last = null;
		length = 0;
	}

	public void addFirstNode(Node<E> nuevo) {
		SNode<E> newFirstNode = (SNode<E>) nuevo;
		if (length == 0) {
			first = newFirstNode;
			last = newFirstNode;
		} else {
			newFirstNode.setNext(first);
			first = newFirstNode;
		}
		length++;

	}

	public void addNodeAfter(Node<E> target, Node<E> nuevo) {
		SNode<E> ts = (SNode<E>) target;
		SNode<E> ns = (SNode<E>) nuevo;
		if (ts == last) {
			last.setNext(ns);
			last = ns;
		} else {
			SNode<E> temp = first;
			while (temp != ts) {
				temp = temp.getNext();
			}
			ns.setNext(temp.getNext());
			temp.setNext(ns);
		}
		length++;
	}

	public void addNodeBefore(Node<E> target, Node<E> nuevo) {
		SNode<E> ts = (SNode<E>) target;
		SNode<E> ns = (SNode<E>) nuevo;

		if (ts == first) {
			this.addFirstNode(ns);
		} else {
			SNode<E> nb4 = (SNode<E>) this.getNodeBefore(ts);
			ns.setNext(nb4.getNext());
			nb4.setNext(ns);
		}
		length++;
	}

	public Node<E> getFirstNode() throws NoSuchElementException {
		if (length == 0)
			throw new NoSuchElementException("getFirstNode() : linked list is empty...");
		return first;
	}

	public Node<E> getLastNode() throws NoSuchElementException {
		if (length == 0)
			throw new NoSuchElementException();
		return last;

	}

	public Node<E> getNodeAfter(Node<E> target) throws NoSuchElementException {
		SNode<E> ts = (SNode<E>) target;
		if (length == 0 || ts == last)
			throw new NoSuchElementException();

		SNode<E> temp = first;
		while (temp != ts) {
			temp = temp.getNext();
		}
		return temp.getNext();
	}

	public Node<E> getNodeBefore(Node<E> target) throws NoSuchElementException {
		// Pre: target is a node in the list
		SNode<E> ts = (SNode<E>) target;
		if (length == 0 || ts == first)
			throw new NoSuchElementException();

		SNode<E> temp = first;
		int index = 0;
		while (temp != ts) {
			temp = temp.getNext();
			index++;
		}
		temp = first;
		for (int i = 1; i < index; i++)
			temp = temp.getNext();
		return temp;
	}

	public int length() {
		return length;
	}

	public void removeNode(Node<E> target) {
		SNode<E> tar = (SNode<E>) target;

		if (tar == first) {
			SNode<E> temp = first;
			first = first.getNext();
			temp.setNext(null);
		} else if (tar == last) {
			SNode<E> nb4 = (SNode<E>) this.getNodeBefore(tar);
			last = nb4;
			nb4.setNext(null);
		} else {
			SNode<E> nb4 = (SNode<E>) this.getNodeBefore(tar);
			SNode<E> nafter = (SNode<E>) this.getNodeAfter(tar);
			nb4.getNext().setNext(null);
			nb4.setNext(nafter);
		}
		length--;

	}

	public Node<E> createNewNode() {
		return new SNode<E>();
	}

	

}

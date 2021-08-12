/*
 * @author Rowena
 * This class that defines a node object.
 * It has both previous and next pointers, therefore making it a double node.
 */

public class Node<T> {
	
	private Node<T> next;
	private Node<T> prev;
	private T data;
	
	/**
	 * Creates an empty node, where all instance variables are null.
	 */
	public Node() {
		next = null;
		prev = null;
		data = null;
	}
	
	/**
	 * Creates a node storing the given data in which next and prev are null.
	 */
	public Node(T newData) {
		next = null;
		prev = null;
		data = newData;
				
	}
	
	/**
	 * Returns the value of next.
	 */
	public Node<T> getNext() {
		return next;
	}
	
	/**
	 * Returns the value of prev.
	 */
	public Node<T> getPrev() {
		return prev;
	}
	
	/**
	 * Returns the value of data.
	 */
	public T getData() {
		return data;
	}
	
	/**
	 * Stores nextNode in next.
	 */
	public void setNext(Node<T> nextNode) {
		next = nextNode;
	}
	
	/**
	 * Stores prevNode in prev.
	 */
	public void setPrev(Node<T> prevNode) {
		prev = prevNode;
	}
	
	/**
	 * Stores newData in data.
	 */
	public void setData(T newData) {
		data = newData;
	}
	
}

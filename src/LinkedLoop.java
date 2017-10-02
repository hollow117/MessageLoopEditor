///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  MessageLoopEditor.java
// File:             MessageLoopEditor.java, LinkedLoopIterator.java, 
//					 LinkedLoop.java, EmptyLoopException.java
// Semester:         CS367 Summer 2016
//
// Author:           Jason Choe choe2@wisc.edu
// CS Login:         choe
// Lecturer's Name:  Amanda Strominger
// Lab Section:      001
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ////////////////////
//
// Pair Partner:     Jason Leduc
// Email:            jlleduc@wisc.edu
// CS Login:         leduc
// Lecturer's Name:  Amanda Strominger
// Lab Section:      001
//
////////////////////////////////////////////////////////////////////////////////

import java.util.Iterator;

/**
 * The LinkedLoop class which implements the Loop<E> interface. In addition to 
 * the methods given in the Loop<E> interface, LinkedLoop<E> class has a 
 * constructor that takes no arguments and creates an empty loop. The internal 
 * data structure used by your LinkedLoop<E> must be a circular, doubly-linked 
 * chain of nodes.
 *
 * <p>Bugs: None known
 *
 * @author Jason Choe, Jason Leduc
 */

public class LinkedLoop<E> implements Loop<E>, Iterable<E> {

	private DblListnode<E> current;
	private int numNodes;

	/**
	 * This is a generic constructor.
	 * 
	 * @param none
	 * @return none
	 */
	public LinkedLoop() {
		current = null;
		numNodes = 0;
	}

	/**
	 * This method returns current item 
	 *
	 * @param 	none
	 * @return 	current.getData() 	return current item
	 */
	@Override
	public E getCurrent() throws EmptyLoopException {
		if (current == null) {
			throw new EmptyLoopException();
		}
		return current.getData();
	}
	
	/**
	 * This method  inserts new item before current item and add pointers 
	 * between the new item and the one previously before current as well as to 
	 * and from current
	 *
	 * @param 	E node  	node that will be added onto Linkedlist
	 * @return	none
	 */
	@Override
	public void insert (E node) {
		if (current == null) {
			current = new DblListnode<E>(node);
			current.setNext(current);
			current.setPrev(current);
		} else {
			DblListnode<E> addedNode = new DblListnode<E>(node);
			addedNode.setNext(current);
			addedNode.setPrev(current.getPrev());
			current.getPrev().setNext(addedNode);
			current.setPrev(addedNode);
			current = addedNode;
		}
		numNodes++;
	}

	/**
	 * This method removes current item
	 *
	 * @param 	none
	 * @return 	return null if DblListnode size is 1
	 * @return 	return what used to be current item if DblListnode size is 
	 * 			bigger than 1
	 */


	@Override
	public E removeCurrent() throws EmptyLoopException {
		if (current == null) {
			throw new EmptyLoopException();
		}
		DblListnode<E> nodeAfterRemove = current;
		if (numNodes == 1) {
			current = null;
			numNodes--;
			return nodeAfterRemove.getData(); 
		} else {
			current = nodeAfterRemove.getNext();
			current.setPrev(nodeAfterRemove.getPrev());
			nodeAfterRemove.getPrev().setNext(current);
			numNodes--;
			return nodeAfterRemove.getData(); 
		}
	}

	/**
	 * This method moves current pointer forward one element
	 *
	 * @param 	none
	 * @return	none
	 */
	@Override
	public void forward() {
		current = current.getNext();
	}

	/**
	 * This method moves current pointer backward one element
	 *
	 * @param 	none
	 * @return	none
	 */
	@Override
	public void backward() {
		current = current.getPrev();
	}

	/**
	 * This return the size of the loop, i.e. the number of items
	 *
	 * @param 	none
	 * @return	numNodes		return size of the loop
	 */
	@Override
	public int size() {
		return numNodes;
	}
	
	/**
	 * This method uses presence or absence of current to decide whether or 
	 * not the list is empty
	 *
	 * @param 	none
	 * @return	true			if numNodes is 0	
	 * @return	false			if numNodes is not 0
	 */
	@Override
	public boolean isEmpty() {
		if (numNodes == 0) {
			return true;
		}
		return false;
	}
	
	/**
	 * This method create a new iterator, initialized using the 
	 * linkedLoop and current
	 *
	 * @param 	none
	 * @return	new LinkedLoopIterator<E>(current, numNodes)	
	 * return new iterator
	 */

	@Override
	public Iterator<E> iterator() {
		return new LinkedLoopIterator<E>(current, numNodes);
	}

	
}
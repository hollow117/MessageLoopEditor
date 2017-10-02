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
 * The LinkedLoopIterator class is used to iterate over LinkedLoop
 *
 * <p>Bugs: None known
 *
 * @author Jason Choe, Jason Leduc
 */

public class LinkedLoopIterator<E> implements Iterator<E> {
	private DblListnode<E> current;
	private int position;
	private int numNodes;
	
	/**
	 * This is a constructor that initializes currNode, position and 
	 * numNodes fields.
	 * 
	 * @param DblListnode<E> current
	 * @param int numNodes
	 * @return none
	 */
	LinkedLoopIterator(DblListnode<E> current, int numNodes) {
		this.current = current;
		this.position = 0;
		this.numNodes = numNodes;
	}

	/**
	 * This method tells if there is next node available by checking to see
	 * if position is less than the number of available nodes  
	 *
	 * @param 	none
	 * @return 	true	if position is less than numNodes
	 * @return	false	if position is equal to or bigger than numNodes
	 */
	@Override
	public boolean hasNext() {
		if ((this.position < this.numNodes) == true){
			return true;
		}else
			return false;
	}

	/**
	 * This method returns what is in the current node before setting the 
	 * pointer next of the current node 
	 *
	 * @param 	none
	 * @return 	data 	this is what was inside current node prior to change
	 * 					pointer
	 */
	@Override
	public E next() {
		E data = current.getData();
		current = current.getNext();
		position++;
		return data;
	}

	/**
	 * This method is not supported
	 *
	 * @param 	none
	 * @return 	none
	 */
	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}
}
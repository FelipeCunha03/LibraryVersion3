/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Controller;

/**
 *
 * @author felipecunha
 */
public interface InterfaceQueue {
    
    /**
     * Adds an element at the back of the queue
     *
     * @param idStudent
     * @return false is do not add
     */
    public boolean AddStudentQueue(int idStudent);

    /**
     * Removes an element from the front of the queue
     *
     * @param idStudent
     * @return the idStudent of queue that is removing.
     */
    public int RemoveFirstStudentQueue();

    /**
     * Remove the first element of the queue
     *
     * @return  first student of queue.
     */
    public int getFirstStudentOfQueue();

    /**
     * Last element of the queue without removing it
     *
     * @return the last student of queue.
     */
    public int getLastStudentOfQueue();

    /**
     * Number of elements in the StudentQueue
     *
     * @return size of studentQueue.
     */
    public int sizeOfQueue();

    /**
     * True if there is no elements in the Student Queue
     *
     * @return true is StudentQueue is empty.
     */
    public boolean isEmpty();
    
    /**
     * 
     *
     * @return the queue of student by Book..
     */
    
    public int[] listQueue();
      
  
}

    


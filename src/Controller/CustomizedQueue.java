/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

/**
 *
 * @author felipecunha
 */
public class CustomizedQueue<T> implements InterfaceQueue {

    private int[] idStudent;
    private int queueSize;
    private int first;//  index of the element
    private int last;
    private int capacity;
    
    
    

    // constructor
    public CustomizedQueue(int capacity) {

        this.idStudent = new int[capacity];
        this.queueSize = 0;
        this.first = -1;
        this.last = -1;
        this.capacity = capacity;
    }    

    @Override
    public boolean AddStudentQueue(int id) {

        if(queueSize >= capacity) {
            return false;
        }

        if(first == -1){
            first++;
        }
        last++;
        idStudent[last] = id;
        queueSize++;
        return true;
    }

    @Override
    public int RemoveStudentQueue(int id) {

        int idReturn;
        if(queueSize == 0){
            return -1;
        }
        idReturn = idStudent[first];
        // idStudent[first] = 0;
        first++;
        queueSize--;
        return idReturn;
    }

    @Override
    public int getFirstStudentOfQueue() {

        if(queueSize == 0){
            return -1;
        }
        return idStudent[first];
    }

    @Override
    public int getLastStudentOfQueue() {

        if(queueSize == 0){
            return -1;
        }
        return idStudent[last];
    }

    @Override
    public int sizeOfQueue() {
        return queueSize;
    }

    @Override
    public boolean isEmpty() {

        if (queueSize == 0) {
            return true;
        } else {
            return false;
        }
    }
    
    @Override
    public  int[] listQueue() {
   
        return idStudent;
        
    }
    
    public String toString() {
        return "CustomizedQueue{" + "idStudent=" + idStudent + ", queueSize=" + queueSize + ", first=" + first + ", last=" + last + ", capacity=" + capacity + '}';
    }

    
}

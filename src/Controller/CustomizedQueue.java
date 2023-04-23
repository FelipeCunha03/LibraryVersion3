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
    public int RemoveFirstStudentQueue() {
        
        final int pos = 0;

        if(queueSize == 0){
            return -1;
        }
        int temp = this.idStudent[pos];
    
        for (int i = pos; i< idStudent.length - 1; i++){
            idStudent[i] = idStudent[i+1];
        }
        queueSize--;
        return temp;


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
    public int[] listQueue() {
   
        return idStudent;      
    }   
    public String toString() {
        return "CustomizedQueue{" + "idStudent=" + idStudent + ", queueSize=" + queueSize + ", first=" + first + ", last=" + last + ", capacity=" + capacity + '}';
    }
  
}

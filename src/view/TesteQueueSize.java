/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import Controller.CustomizedQueue;
import java.io.IOException;
import java.util.Arrays;

/**
 *
 * @author felipecunha
 */
public class TesteQueueSize {
    
     public static void main(String[] args) throws IOException {
         
         CustomizedQueue queueTeste = new CustomizedQueue(10);
         
         queueTeste.AddStudentQueue(1);
         queueTeste.AddStudentQueue(0);
         queueTeste.AddStudentQueue(3);
         
         
          
             
             
             int[] aux = queueTeste.listQueue();
             
             
                String idStudent = Arrays.toString(aux);
                
                System.out.println(idStudent);
                String idStudentFile = idStudent.replaceAll("\\s+", "").replace("[", "").replace("]", "").replace(",0", "");
                
                System.out.println(idStudentFile);
                
                for (int i = 0; i < idStudentFile.length(); i++) {
                aux[i] = (int) idStudentFile.charAt(i);
                 System.out.println("vetor de interior: " + aux[i]);
                }  
                
                    for(int j= 0; j<aux.length; j++){
                        System.out.println("vetor de interior: " + aux[j]);
}
     }
     
    
}

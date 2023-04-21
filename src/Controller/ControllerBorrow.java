/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import static Controller.ControllerStudent.listStudent;
import Model.Book;
import Model.Borrow;
import Model.Student;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;

/**
 *
 * @author rapha
 */
public class ControllerBorrow {

    Scanner s = new Scanner(System.in);

    public static List<Borrow> listBorrowed = new ArrayList();
    static List<Borrow> ListBookByStudent = new ArrayList();
    Student myStudent;
    Book myBook;
    ControllerBook myCB = new ControllerBook();
    ControllerStudent myCS = new ControllerStudent();
    ControllerAvailabilityBook myCAB = new ControllerAvailabilityBook();
    LocalDateTime localTime;
    String dataReturned, dataBorrowed;
    Map<Book, CustomizedQueue<Integer>> myMap = new HashMap<>();
    CustomizedQueue<Integer> myQueue;

    public Borrow borrowBook() throws IOException {

        myQueue = new CustomizedQueue(100);

        //call the method to search book by title
        myBook = myCB.searchBookByTitle();
        if (myBook == null) {
            messageError("Book");
            return null;
        }

        //call the method to search student by ID
        myStudent = myCS.searchStudentByID();
        if (myStudent == null) {
            messageError("Student");
            return null;
        }

        for (int i = 0; i < ControllerAvailabilityBook.listAvailableBook.size(); i++) {

            //check if the book is available to be borrowed
            if (ControllerAvailabilityBook.listAvailableBook.get(i).getIdBook().equals(myBook.getIdBook())
                    && ControllerAvailabilityBook.listAvailableBook.get(i).isIsAvailable() == true) {

                if (myMap.get(myBook) != null) { //if the book is available, check if the map is not empty 

                    //if the student who wants borrow the book is the first of the queue waiting, remove this student of the queue and let him borrow it
                    if (myStudent.getIdStudent() == myMap.get(myBook).getFirstStudentOfQueue()) {
                        myMap.get(myBook).RemoveStudentQueue(myStudent.getIdStudent());

                    } else {//if not, show a message with the name of the first on the queue

                        for (int j = 0; j < listStudent.size(); j++) {//for to return the name of the first student waiting on the queue

                            if (listStudent.get(j).getIdStudent() == myMap.get(myBook).getFirstStudentOfQueue()) {
                                System.out.println("The book is not available to you yet because the first student of the queue is: " + listStudent.get(j).getfNameStudent() + " " + listStudent.get(j).getlNameStudent() + " - id student: "
                                        + myMap.get(myBook).getFirstStudentOfQueue() + ".");
                                return null; //return null to don't let the student borrow 
                            }
                        }
                    }
                }
                ControllerAvailabilityBook.listAvailableBook.get(i).setIsAvailable(false); //set false when the book is borrowed

                localTime = LocalDateTime.now();
                DateTimeFormatter dataFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
                dataBorrowed = localTime.format(dataFormat);  //save the data and time the book is borrowed

                LocalDateTime dReturned = null;
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
                Optional<LocalDateTime> optionalDate = Optional.ofNullable(dReturned);
                dataReturned = optionalDate.map(formatter::format).orElse(" --- "); //when the book is borrowed, there is no data returned

                //save the book that was borrowed and send the information and include it to the borrowing's list
                Borrow myBorred = new Borrow(myBook.getIdBook(), myBook.getBookTitle(), myStudent.getIdStudent(),
                        myStudent.getfNameStudent(), myStudent.getlNameStudent(), dataBorrowed, dataReturned);

                listBorrowed.add(myBorred);
                storageListBorrowedFile();
                
                String idBook = myBook.getIdBook();
                myCAB.overWriteAvailabilityFile();
                

                Borrow myBorredByStudent = new Borrow(myStudent.getIdStudent(), myBook);
                ListBookByStudent.add(myBorredByStudent);

                System.out.println(" \n***Confirmed the borrowing of the book to the student***\n");
                return myBorred; //return the book borrowed

            }
        }
        //if the book is not avaiable to be borrowed, ask the user about go to the queue
        System.out.println("Book is borrowed. Would you like to wait on the queue for the book? S/N");
        String answer = s.nextLine().toLowerCase();

        if (answer.equals("s")) {
            waitingQueue(); //if the student wants go to the queue, call the method waitingQueue
        }
        return null;
    }

    public void waitingQueue() {

        if (!myMap.containsKey(myBook)) {   //if in the map doesn't exist the book, insert the book in the map and a queue of students          
            myMap.put(myBook, myQueue);
        }
        //call the method to add student on the queue
        boolean myReturn = myMap.get(myBook).AddStudentQueue(myStudent.getIdStudent());

        if (myReturn == true) { //if the method returns true, the student was add on the queue for that book

            System.out.println("\n***Confirmed! The student " + myStudent.getfNameStudent() + " "
                    + myStudent.getlNameStudent() + " is on the queue for the book " + myBook.getBookTitle() + "***\n");
            storageQueuedFile();
        } else {
            System.out.println("The student was not add on the queue, because it is full.");
        }
    }

    public void returnBook() throws IOException {

        myBook = myCB.searchBookByTitle(); //ask to the user which book he wants to return
        ControllerAvailabilityBook myCAB = new ControllerAvailabilityBook();

        if (myBook == null) { //book was not found
            messageError("Book");
        } else {

            for (int i = 0; i < ControllerAvailabilityBook.listAvailableBook.size(); i++) {

                if (ControllerAvailabilityBook.listAvailableBook.get(i).getIdBook().equals(myBook.getIdBook())) {

                    if (ControllerAvailabilityBook.listAvailableBook.get(i).isIsAvailable() == false) { //check if the book is borrowed

                        ControllerAvailabilityBook.listAvailableBook.get(i).setIsAvailable(true); //set the book to available
                        myCAB.overWriteAvailabilityFile();

                        localTime = LocalDateTime.now();
                        DateTimeFormatter dataFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
                        dataReturned = localTime.format(dataFormat); //includes data returned

                        System.out.println("\n*Book returned!*");
                        System.out.println("Returned date: " + dataReturned + "\n");

                    } else {
                        System.out.println("Book is not borrowed!");
                    }

                    if (myMap.get(myBook) == null) { //check if the queue for the book is empty
                        System.out.println("There is no student waiting on the queue.");
                    } else {    // if the queue is not empty, shows the first student waiting on the queue

                        for (int j = 0; j < listStudent.size(); j++) {

                            if (listStudent.get(j).getIdStudent() == myMap.get(myBook).getFirstStudentOfQueue()) {

                                System.out.println("The first student waiting on the queue for this book is: " + listStudent.get(j).getfNameStudent() + " " + listStudent.get(j).getlNameStudent() + " - id student: "
                                        + myMap.get(myBook).getFirstStudentOfQueue() + ".");
                            }
                        }
                    }
                }
            }
        }
    }

    public void listBookBorrowByStudent() {

        ControllerStudent myCS = new ControllerStudent();
        List<Book> listAux = new ArrayList();
        myStudent = myCS.searchStudentByID();
        
        System.out.println("*************LIST OF BOOKS BORROWED BY STUDENT***************");
      

        for (int i = 0; i < listBorrowed.size(); i++) {

            if ( listBorrowed.get(i).getIdStudent() == myStudent.getIdStudent()) {
                System.out.println(listBorrowed.get(i));
            }
        }
        
    }

    // storge the list borrred in  file txt.
    public void storageListBorrowedFile() {

        String idBook;
        String idStudent;
        String dataBorrowing;
        String dataReturned;
        String firsName;
        String lastName;
        String title;

        try {
            // try overwrite txt if something went wrong  will be have Exception
            BufferedWriter myWriter = new BufferedWriter(new FileWriter("src/library/Borrow_table.csv", false));

            myWriter.write("idBook" + "," + "Title" +"," + " idStudent " + ","+ "First Name" + "," + "LastName"+"," +"borrowedDate " + "," + "returnedDate ");
            myWriter.newLine();

            for (int i = 0; i < listBorrowed.size(); i++) {

                idBook = listBorrowed.get(i).getIdBook();
                title= listBorrowed.get(i).getTitleBook();
                idStudent = Integer.toString(listBorrowed.get(i).getIdStudent());
                firsName =  listBorrowed.get(i).getStudentFirstName();
                lastName =  listBorrowed.get(i).getStudentLastName(); 
                dataBorrowing = listBorrowed.get(i).getDataBorrowing();
                dataReturned = listBorrowed.get(i).getDataReturned();
                myWriter.write(idBook + "," + title + "," + idStudent + "," +firsName+ "," + lastName +"," +dataBorrowing + "," + dataReturned);
              
                myWriter.newLine();

            }
            myWriter.close();

        } catch (Exception e) {
            System.out.println("Error writing on txt! ");
        }
    }

    public void messageError(String objError) {
        System.out.println(objError + " was not found!");
    }

    public void createdBorrowedFile() {

        File file = new File("src/library/Borrow_table.csv");

        if (file.exists() == false) {

            try {
                // try  to creat the txt and put number and  no number in there if something went wrong I have catch.
                BufferedWriter myWriter = new BufferedWriter(new FileWriter("src/library/Borrow_table.csv"));
                myWriter.write("idBook " + "," + " idStudent " + "," + "borrowedDate " + "," + "returnedDate ");

                myWriter.close();

            } catch (Exception e) { // if something went wrong when system try to  read txt treat Exception

                System.out.println("Error acess file !!");

            }
            System.out.println("Arquivo criado!!");
       
    }  
        else{
            System.out.println("arquivo ja criado");
        }

    }

    public void readFileBorrowBook() throws FileNotFoundException, IOException {

        String idBook;
        int idStudent;
        String dataBorrowing;
        String dataReturned;
        String firsName;
        String lastName;
        String title;

        String path = "src/library/Borrow_table.csv"; //path of data It is.

        BufferedReader br = new BufferedReader(new FileReader(path));
        br.readLine();

        String line = br.readLine();

        try {

            
            while (line != null) {

                
                String[] vetBorrow = line.split(",");
                idBook = vetBorrow[0];
                title = vetBorrow[1];
                idStudent = Integer.parseInt(vetBorrow[2]);
                firsName = vetBorrow[3];
                lastName = vetBorrow[4];   
                dataBorrowing = vetBorrow[5];
                dataReturned = vetBorrow[6];
         
                Borrow BorrowObj = new Borrow(idBook, title, idStudent, firsName,lastName,dataBorrowing, dataReturned);
                listBorrowed.add(BorrowObj);
                line = br.readLine(); //read the next line of file csv.
            }
        } catch (Exception e) {
            System.out.println("Error open file\nMessage error: " + e.getMessage());
        }
    }
    
    

    public void queueStudentByBook() {

        myBook = myCB.searchBookByTitle();

        if (!myMap.containsKey(myBook)) {
            System.out.println("No students are waiting for " + myBook.getBookTitle());

        } else {

            int[] studentQueue = myMap.get(myBook).listQueue();

            System.out.println("\n*LIST OF STUDENTS WAITING ON THE QUEUE FOR THE BOOK: " + myBook.getBookTitle() + "*\n");

            for (int i = 0; i < myMap.get(myBook).sizeOfQueue(); i++) {
                for (int j = 0; j < listStudent.size(); j++) {

                    if (studentQueue[i] == listStudent.get(j).getIdStudent()) {

                        System.out.println("Id Student: " + studentQueue[i] + " - Name Student: " + listStudent.get(i).getfNameStudent()
                                + " " + listStudent.get(i).getfNameStudent());
                    }
                }

            }
        }
    }

    public void listBookAreBorrowed() {

        System.out.println("**LIST ALL BOOKS ARE BORROWED**");

          for (int i = 0; i < ControllerAvailabilityBook.listAvailableBook.size(); i++) {

            if (ControllerAvailabilityBook.listAvailableBook.get(i).isIsAvailable() != true) {

                for (int j = 0; j < ControllerBook.listBook.size(); j++){

                    if (ControllerBook.listBook.get(j).getIdBook().equals(ControllerAvailabilityBook.listAvailableBook.get(i).getIdBook())){

                        System.out.println(ControllerBook.listBook.get(j));
                    }
                }
            }      
        }
    }
    
    public void storageQueuedFile() {
        
    String idBook = null;
    String idStudent = null;
    int count = myMap.get(myBook).sizeOfQueue();
    
        try {
            // try overwrite txt if something went wrong  will be have Exception
            BufferedWriter myWriter = new BufferedWriter(new FileWriter("src/library/Queue_table.csv", false));

            myWriter.write("idBook " + "," + " idStudent");
            myWriter.newLine();

            for (Book countbook : myMap.keySet()){

                idBook = countbook.getIdBook();
                myWriter.write(idBook);    
                for (CustomizedQueue queueSt : myMap.values()){

                   int[] aux = queueSt.listQueue();                            
                   idStudent = Arrays.toString(aux); 
                   myWriter.write("," + idStudent +"\n");                   
               }                                     
            }
            myWriter.close();

        }catch (Exception e){
            System.out.println("Error writing on txt! ");
        }
    }    
    
        
}
    
    



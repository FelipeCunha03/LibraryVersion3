/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import Controller.ControllerAvailabilityBook;
import Controller.ControllerBorrow;
import Controller.ControllerStudent;
import Controller.ControllerBook;
import Model.Book;
import Model.Borrow;
import Model.Student;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author rapha
 */
public class Menu {
    
      public static void main(String[] args) throws IOException {
                        

        Scanner s = new Scanner(System.in);
        ControllerBook myCB = new ControllerBook();
        ControllerStudent myCS = new ControllerStudent();
        ControllerBorrow myBW = new ControllerBorrow();
        ControllerAvailabilityBook myCAB = new ControllerAvailabilityBook();
   
        int option = 0;
        Book myBook;
        Student myStudent;
        Borrow myBorrow;

        // calling the methot that will get the file data of books/students/generateAvailableBook 
        myCB.getBookObj();
        myCS.getStudentObj();
        myCAB.getAvailabilityBookFile();   
        myBW.createdBorrowedFile(); 
        myBW.readFileBorrowBook();
        
       // System.out.println(ControllerBorrow.listBorrowed);
        
          
       
        do{

            try{
                System.out.println("**********************************************************");
                System.out.println("**                 Welcome to CCT Library!!             **");
                System.out.println("**********************************************************");
                System.out.println("**  Choose one option:                                  **");
                System.out.println("**                                                      **");
                System.out.println("**  1. Search for a specific book by title              **");
                System.out.println("**  2. Search for a specific book by author             **");
                System.out.println("**  3. List all books by title                          **");
                System.out.println("**  4. List all books by author's name                  **");
                System.out.println("**  5. Search for a specific student by name            **");
                System.out.println("**  6. Search for a specific student by ID              **");
                System.out.println("**  7. List all students by name                        **");
                System.out.println("**  8. List all students by ID                          **");
                System.out.println("**  9. Register that a student has borrowed a book      **");
                System.out.println("** 10. Register a student has returned a book           **");
                System.out.println("** 11. List the books borrowed by a specific student    **");
                System.out.println("** 12. Check book availability                          **");
                System.out.println("** 13. List all the books that was borrowed              **");
                System.out.println("** 14. List all the books that are borrowed              **");
                System.out.println("** 15. List the Queue by Book                           **");
                System.out.println("** 16. Exit                                             **");
                System.out.println("**********************************************************");
                System.out.println("**********************************************************");
                System.out.println("===> Enter with the option: ");

                option = s.nextInt();
                
                switch(option){
                    
                    case (1):
                        myBook = myCB.searchBookByTitle();
                        if (myBook == null) {
                            myBW.messageError("Book");
                        }else{
                            System.out.println(myBook);
                        }
                        break;

                    case (2):
                        myBook = myCB.searchBookByAuthor();
                        if (myBook == null) {
                            myBW.messageError("Author");
                        }else{
                            System.out.println(myBook);
                        }
                        break;

                    case (3):
                        myCB.listBookByTitle();
                        break;

                    case (4):
                        myCB.listBookByAuthor();
                        break;

                    case (5):
                        myStudent = myCS.searchStudentByName();
                        if (myStudent == null) {
                            myBW.messageError("Student");
                        }else{
                            System.out.println(myStudent);
                        }
                        break;

                    case (6):
                        myStudent = myCS.searchStudentByID();
                        if (myStudent == null) {
                            myBW.messageError("ID");
                        }else{
                            System.out.println(myStudent);
                        }
                        break;

                    case (7):
                        myCS.listStudentByName();
                        break;

                    case (8):
                        myCS.listStudentByID();
                        break;

                    case (9):                      
                        myBorrow = myBW.borrowBook();
                        if (myBorrow == null) {
                            s.nextLine();
                        }else{
                            System.out.println(myBorrow);
                        }
                        break;

                    case (10):
                        myBW.returnBook();                              
                        break;

                    case (11):
                        myBW.listBookBorrowByStudent();
                        break;

                    case (12):                      
                        boolean check = myCAB.checkBookAvailability(); 
                        if(check == true){
                            System.out.println("This book is available.");
                        }else{
                            System.out.println("This book is borrowed.");
                        }
                        break;
                        
                    case (13):
                        System.out.println(ControllerBorrow.listBorrowed);
                        break;
                        
                    case (14):
                         myBW.listBookAreBorred();
                        break;
                        
                    case (15):
                        myBW.queueStudentByBook();
                        break;

                    default:
                        System.out.println("Please, choose an option between 1 and 14.");
                }
                
            }catch (Exception e) {
                System.out.println("Please, choose an option between 1 and 13. \nMessage error: " + e.getMessage());
                s.nextLine();
            }
        }while (option != 16);
        
    }
    
}

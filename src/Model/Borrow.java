/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author rapha
 */
public class Borrow {

    private String idBook;
    private String titleBook;
    private int idStudent;
    private String StudentFirstName;
    private String StudentLastName;
    private String dataBorrowing;
    private String dataReturned;
    private Book myBook;

    public Borrow(String idBook, String titleBook, int idStudent, String StudentFirstName, String StudentLastName, String dataBorrowing, String dataReturned) {
        this.idBook = idBook;
        this.titleBook = titleBook;
        this.idStudent = idStudent;
        this.StudentFirstName = StudentFirstName;
        this.StudentLastName = StudentLastName;
        this.dataBorrowing = dataBorrowing;
        this.dataReturned = dataReturned;
    }
    
    public Borrow(String idBook, int idStudent, String dataBorrowing, String dataReturned) {
          
        this.idBook = idBook;
        this.idStudent = idStudent;
        this.dataBorrowing = dataBorrowing;
        this.dataReturned = dataReturned;
    }

    public Borrow(int idStudent, Book myBook) {
        this.idStudent = idStudent;
        this.myBook = myBook;
    }

    public String getIdBook() {
        return idBook;
    }

    public void setIdBook(String idBook) {
        this.idBook = idBook;
    }

    public String getTitleBook() {
        return titleBook;
    }

    public void setTitleBook(String titleBook) {
        this.titleBook = titleBook;
    }

    public int getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(int idStudent) {
        this.idStudent = idStudent;
    }

    public String getStudentFirstName() {
        return StudentFirstName;
    }

    public void setStudentFirstName(String StudentFirstName) {
        this.StudentFirstName = StudentFirstName;
    }

    public String getStudentLastName() {
        return StudentLastName;
    }

    public void setStudentLastName(String StudentLastName) {
        this.StudentLastName = StudentLastName;
    }

    public String getDataBorrowing() {
        return dataBorrowing;
    }

    public void setDataBorrowing(String dataBorrowing) {
        this.dataBorrowing = dataBorrowing;
    }

    public String getDataReturned() {
        return dataReturned;
    }

    public void setDataReturned(String dataReturned) {
        this.dataReturned = dataReturned;
    }

    public Book getMyBook() {
        return myBook;
    }

    public void setMyBook(Book myBook) {
        this.myBook = myBook;
    }

    

    @Override
    public String toString() {
        return "----------------Borrow's Details--------------"
                + "\n Id Book: " + idBook
                +"\n Book's title: " + titleBook 
                + "\n Id Student: " + idStudent 
                + "\n Student's Name: " + StudentFirstName + " " + StudentLastName 
                + "\n Data Borrowing: " + dataBorrowing
                + "\n Data Returned: " + dataReturned + "\n";
    }
}

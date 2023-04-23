/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author felipecunha
 */
public class Student {
    
    private int idStudent;
    private String fNameStudent;
    private String lNameStudent;
    private String address;
    private String city;
    private String phoneNumber;
    private char gender;
    private String DOB;

    public Student(int idStudent, String fNameStudent, String lNameStudent, String address, String city, String phoneNumber, char gender, String DOB) {
        this.idStudent = idStudent;
        this.fNameStudent = fNameStudent;
        this.lNameStudent = lNameStudent;
        this.address = address;
        this.city = city;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.DOB = DOB;
    }

    // gets and setters
    public int getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(int idStudent) {
        this.idStudent = idStudent;
    }

    public String getfNameStudent() {
        return fNameStudent;
    }

    public void setfNameStudent(String fNameStudent) {
        this.fNameStudent = fNameStudent;
    }

    public String getlNameStudent() {
        return lNameStudent;
    }

    public void setlNameStudent(String lNameStudent) {
        this.lNameStudent = lNameStudent;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    @Override
    public String toString() {
        return "\n---------------Student's details ------------\n"
                + "Id Student: " + idStudent
                + "\nStudent's name: " + fNameStudent + " " + lNameStudent
                + "\nAddress: " + address
                + "\nCity: " + city
                + "\nPhone Number: " + phoneNumber
                + "\nGender: " + gender
                + "\nDate of Birth: " + DOB;
    }
}

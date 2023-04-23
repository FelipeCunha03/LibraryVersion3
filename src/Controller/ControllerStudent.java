/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Student;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author rapha
 */
public class ControllerStudent {

    Scanner s = new Scanner(System.in);
    static List<Student> listStudent;

    public List<Student> getStudentObj() throws FileNotFoundException, IOException {

        Set<Student> myStudentSet = new HashSet<>();
        String path = "src/library/students.csv";

        BufferedReader br = new BufferedReader(new FileReader(path));
        br.readLine(); // Read the fist line (header of file) and does not do anything.

        String line = br.readLine(); // Now read the second line and passing to String

        try{
            
            while(line != null){

                String[] vetStudent = line.split(",");
                int idS = Integer.parseInt(vetStudent[0]);
                String firstNameS = vetStudent[1];
                String lastNameS = vetStudent[2];
                String address = vetStudent[3];
                String city = vetStudent[4];
                String phoneNumber = vetStudent[5];
                String gender = vetStudent[6];
                char charGender = gender.charAt(0);
                String DOB = vetStudent[7];

                Student studentObj = new Student(idS, firstNameS.toLowerCase(), lastNameS.toLowerCase(), address, city, phoneNumber, charGender, DOB);
                myStudentSet.add(studentObj);
                line = br.readLine();
            }
        }catch(Exception e){
            System.out.println("Error to open file\nMessage error: " + e.getMessage());
        }

        listStudent = new ArrayList<>(myStudentSet);
        return listStudent;
    }

    public Student searchStudentByName() { //linear searching

        String fNameStudent, lNameStudent;
        System.out.println("Inform the student's name: ");
        String studentName = s.nextLine().toLowerCase();

        if(studentName.contains(" ")){
            fNameStudent = studentName.substring(0, studentName.indexOf(" "));
            lNameStudent = studentName.substring(studentName.indexOf(" ") + 1);

        }else{
            System.out.println("Inform the student's full name.");
            return null;
        }
        for (int i = 0; i < listStudent.size(); i++) {

            if ((listStudent.get(i).getfNameStudent().equals(fNameStudent))
                    && (listStudent.get(i).getlNameStudent().equals(lNameStudent))) {

                return listStudent.get(i);
            }
        }
        return null;
    }

    public Student searchStudentByID() { //linear searching

        boolean checkId = false;
        String idValid = null; // It was created as String because I wanted to used the regex for to valid the ID 
        //and make sure the user type just number!

        do{
            System.out.println("Informe the student's ID: ");
            idValid = s.nextLine();

            if(idValid.matches("[0-9]+")){
                checkId = true;
            }else{
                System.out.println("Please, inform just numbers: ");
            }

        }while(checkId == false);

        int studentID = Integer.parseInt(idValid); // convert to the ID to INT because the atribute IDstudent is int 
        //and It not possible compare String vs int in the nexr for.

        for(int i = 0; i < listStudent.size(); i++){

            if (listStudent.get(i).getIdStudent() == studentID) {
                return listStudent.get(i);
            }
        }
        return null;
    }

    public void listStudentByName() { //bubble sorting

        for(int i = 0; i < listStudent.size(); i++){ //go through the list of students

            for(int j = 0; j < listStudent.size() - 1; j++){ //check which element in the list to swap until the
                                                             //size-1 because the last position will be in the correct position after all the sorting                   
                if ((listStudent.get(j).getfNameStudent().compareTo(listStudent.get(j + 1).getfNameStudent()) > 0)||
                        (listStudent.get(j).getfNameStudent().compareTo(listStudent.get(j + 1).getfNameStudent()) == 0)
                        && (listStudent.get(j).getlNameStudent().compareTo(listStudent.get(j + 1).getlNameStudent()) > 0)) {
                        //if the name in second position(j+1) is bigger than name in the first position(j) do the sorting
                    Student tempStudent = listStudent.get(j);   //save the name in first position in a temporary variable
                    listStudent.set(j, listStudent.get(j + 1)); //get the name in second position and put in first position
                    listStudent.set(j + 1, tempStudent);        //get the first name saved in temporary and put in the second position
                }
            }   //if it's not big, do nothing, keep the position
        }
        System.out.println("\n*************LIST STUDENTS BY NAME ORDER*************");
        System.out.println(listStudent);
    }

    public void listStudentByID() { //bubble sorting

        Student temp;
        for(int i = 0; i < listStudent.size(); i++){

            for (int j = 0; j < listStudent.size() - 1; j++) {
                if (listStudent.get(j).getIdStudent() > listStudent.get(j + 1).getIdStudent()) {
                    temp = listStudent.get(j);
                    listStudent.set(j, listStudent.get(j + 1));
                    listStudent.set((j + 1), temp);
                }
            }
        }
        System.out.println("\n*************LIST STUDENTS BY ID ORDER*************");
        System.out.println(listStudent);
    }
}

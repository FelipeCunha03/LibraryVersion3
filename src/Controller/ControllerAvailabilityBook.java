/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import static Controller.ControllerBook.listBook;
import static Controller.ControllerBorrow.listBorrowed;
import Model.AvailabilityBook;
import Model.Book;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author felipecunha
 */
public class ControllerAvailabilityBook {

    public static List<AvailabilityBook> listAvailableBook = new ArrayList();

    public void getAvailabilityBookFile() throws FileNotFoundException, IOException {

        Set<AvailabilityBook> AvailabilitykSet = new HashSet<>();
        String path = "src/library/AvailabilityOfBook_table.csv"; //path of data It is.

        BufferedReader br = new BufferedReader(new FileReader(path));
        br.readLine();

        String line = br.readLine();

        try {

            while (line != null) {

                String[] vetAvailability = line.split(",");
                String idBook = vetAvailability[0];
                boolean Availability = Boolean.parseBoolean(vetAvailability[1]);

                AvailabilityBook AvailabilityBookObj = new AvailabilityBook(idBook, Availability);
                AvailabilitykSet.add(AvailabilityBookObj);
                line = br.readLine(); //read the next line of file csv.
            }
        }catch (Exception e){
            System.out.println("Error open file\nMessage error: " + e.getMessage());
        }
        listAvailableBook = new ArrayList<>(AvailabilitykSet);// to convert setList of book to arrayList,I think It's easier to use List for maniputation.
    }
    
    
    public void overWriteAvailabilityFile(){
               
        try {
            // try overwrite txt if something went wrong  will be have Exception
            BufferedWriter myWriter = new BufferedWriter(new FileWriter("src/library/AvailabilityOfBook_table.csv", false));
                
             myWriter.newLine();
            for (int i = 0; i < listAvailableBook.size(); i++) {

                String idBook = listAvailableBook.get(i).getIdBook();
                String Availability = Boolean.toString(listAvailableBook.get(i).isIsAvailable());
                
                myWriter.write(idBook + "," + Availability );         
                myWriter.newLine();
            }
            myWriter.close();
        } catch (Exception e) {
            System.out.println("Error writing on txt! ");
        }   
    }

    public boolean checkBookAvailability() {

        ControllerBook myCB = new ControllerBook();

        //call the method to search book by title
        Book myBook = myCB.searchBookByTitle();

        for (int i = 0; i < listAvailableBook.size(); i++) {

            if (listAvailableBook.get(i).getIdBook().equals(myBook.getIdBook())) {

                if (listAvailableBook.get(i).isIsAvailable() == false) {
                    return false;
                }
            }
        }
        return true;
    }
}

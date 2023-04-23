/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Book;
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
 * @author felipecunha
 */
public class ControllerBook {

     static List<Book> listBook;
    Scanner s = new Scanner(System.in);
    public boolean isAvailable;

    /**
     * The methods that will get the file of book put in list of set.
     *
     * @return list of obj
     * @throws FileNotFoundException
     * @throws IOException
     */
    public List<Book> getBookObj() throws FileNotFoundException, IOException {

        //We had decide to use Set TreeSet because will but in orderalphabetic and Set for avoid duplicate values , makes sense for library system.
        Set<Book> myBookSet = new HashSet<>();
        String path = "src/library/books.csv"; //path of data It is.

        BufferedReader br = new BufferedReader(new FileReader(path));
        br.readLine();

        String line = br.readLine();

        //We had put all processe for get the book from data  for treatement some exeception if will have some erroe ao open the file.  
        try{
           
            // Start read the file books.The Loop will try get line by line still the next line will be NULL.
            while(line != null){

                // Created Array of String   for get  each information from file CSV.
                String[] vetBook = line.split(",");
                String idBook = vetBook[0];
                String firstNameA = vetBook[1];
                String lastNameA = vetBook[2];
                String title = vetBook[3];
                String genre = vetBook[4];             

                Book bookObj = new Book(idBook, firstNameA.toLowerCase(),lastNameA.toLowerCase(), title.toLowerCase(), genre);
                myBookSet.add(bookObj);
                line = br.readLine(); //read the next line of file csv.
            }
        }catch(Exception e){
            System.out.println("Error open file\nMessage error: " + e.getMessage());
        }

        listBook = new ArrayList<>(myBookSet);// to convert setList of book to arrayList,I think It's easier to use List for maniputation.
        return listBook;
    }

    public Book searchBookByTitle() { //linear searching

        System.out.println("Inform the book's title: ");
        String bookTitle = s.nextLine().trim().toLowerCase();

        for (int i = 0; i < listBook.size(); i++) {

            if (listBook.get(i).getBookTitle().equals(bookTitle)) {

                return listBook.get(i);
            }
        }
        return null;
    }

    public Book searchBookByAuthor() { //linear searching

        String fNameAuthor, lNameAuthor;
        System.out.println("Inform the name book's author : ");
        String nameAuthor = s.nextLine().trim().toLowerCase();

        if (nameAuthor.contains(" ")) {
            fNameAuthor = nameAuthor.substring(0, nameAuthor.indexOf(" "));
            lNameAuthor = nameAuthor.substring(nameAuthor.indexOf(" ") + 1);

        } else {
            System.out.println("Inform the author's full name.");
            return null;
        }
        for (int i = 0; i < listBook.size(); i++) {

            if (listBook.get(i).getfNameAuthor().equals(fNameAuthor)
                    && listBook.get(i).getlNameAuthor().endsWith(lNameAuthor)) {

                return listBook.get(i);
            }
        }
        return null;
    }

    public void listBookByAuthor() { //bubble sorting

        for (int i = 0; i < listBook.size(); i++) {

            for (int j = 0; j < listBook.size() - 1; j++) {

                if ((listBook.get(j).getfNameAuthor().trim().compareTo(listBook.get(j + 1).getfNameAuthor().trim()) > 0)||
                        (listBook.get(j).getfNameAuthor().compareTo(listBook.get(j+1).getfNameAuthor()) == 0) &&
                        (listBook.get(j).getlNameAuthor().compareTo(listBook.get(j+1).getlNameAuthor()) > 0)){

                    Book tempAuthor = listBook.get(j);
                    listBook.set(j, listBook.get(j + 1));
                    listBook.set(j + 1, tempAuthor);
                }
            }
        }
        System.out.println("\n*************LIST BOOKS BY AUTHOR ORDER*************\n");
        System.out.println(listBook);
    }

    public void listBookByTitle() {//bubble sorting

        for (int i = 0; i < listBook.size(); i++) {

            for (int j = 0; j < listBook.size() - 1; j++) {

                if (listBook.get(j).getBookTitle().trim().compareTo(listBook.get(j + 1).getBookTitle().trim()) > 0) {

                    Book tempTitle = listBook.get(j);
                    listBook.set(j, listBook.get(j + 1));
                    listBook.set(j + 1, tempTitle);
                }
            }
        }
        System.out.println("\n*************LIST BOOKS BY BOOK'S TITLE ORDER*************\n");
        System.out.println(listBook);
    }
}

  
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author felipecunha
 */
public class AvailabilityBook {
    
    private String idBook;
    private boolean isAvailable ;

    public AvailabilityBook(String idBook, boolean isAvailable) {
        this.idBook = idBook;
        this.isAvailable = isAvailable;
    }

    public String getIdBook() {
        return idBook;
    }

    public void setIdBook(String idBook) {
        this.idBook = idBook;
    }

    public boolean isIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    @Override
    public String toString() {
        return "Id Book: " + idBook +
                " Availability: " + isAvailable + "\n";
    }
    
}
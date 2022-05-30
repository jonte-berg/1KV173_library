/*      Kurs: 1IK173
        Projekt
        Kursdeltagare: Dennis Schill, Robin Sjöberg, Jonathan Berg, Konstantinos Karidas
        Termin och datum: VT22 30/5   */

package model;

public class Book {

    private int isbn;
    private String title;
    private String genre;
    private int copies; 
    private int available;


    public Book(){}

    public Book(int isbn, String title, String genre, int numberOfCopies, int numberOfAvailableBooks){
        this.isbn=isbn;
        this.title=title;
        this.genre=genre;
        this.copies = numberOfCopies;
        this.available = numberOfAvailableBooks;
    }

    public int getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }


    public int getCopies() {
        return copies;
    }

    public void setCopies(int numberOfCopies) {
        this.copies = numberOfCopies;
    }

    public int getAvailable() {
        return this.available;
    }

    public void setAvailable(int numberOfAvailableBooks) {
        this.available = numberOfAvailableBooks;
    }
}

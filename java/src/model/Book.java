package model;

public class Book {

    private int isbn;
    private String title;
    private String genre;
    private int totalQuantity; //Robin har ändrat namn
    private int totalAvailable; //Robin har tagit bort quantetiesLeft och bytt namn på isAvalable till totalAvailable.


    public Book(){}

    public Book(int isbn, String title, String genre, int quantityTotal, int totalAvailable){
        this.isbn=isbn;
        this.title=title;
        this.genre=genre;
        this.totalQuantity = quantityTotal;
        this.totalAvailable = totalAvailable;
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



    public int getTotalAvailable() {
        return this.totalAvailable;
    }

    public void setAvailable(int available) {
        totalAvailable = available;
    }
}

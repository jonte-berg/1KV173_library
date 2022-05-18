package model;

public class Book {

    private int isbn;
    private String title;
    private String genre;
    private int quantity;
    private boolean isAvailable;

    public Book(){}

    public Book(int isbn, String title, String genre, int quantity, boolean isAvailable){
        this.isbn=isbn;
        this.title=title;
        this.genre=genre;
        this.quantity=quantity;
        this.isAvailable=isAvailable;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}

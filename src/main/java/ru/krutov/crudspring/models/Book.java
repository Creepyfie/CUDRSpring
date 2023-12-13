package ru.krutov.crudspring.models;

import jakarta.validation.constraints.*;


public class Book {
    private int book_id;

    private int person_id;

    @NotNull
    @Size(min = 2,max=50)
    private String bookName;

    @NotNull
    @Size(min = 2, max = 50)
    private  String author;
    @Positive
    @Min(0)
    @Max(2023)
    //@Pattern(regexp = "\\d{4}")
    private int year;
    public Book(){

    }

    public Book(int book_id,int person_id, String bookName, String author, int year) {
        this.book_id = book_id;
        this.person_id=person_id;
        this.bookName = bookName;
        this.author = author;
        this.year = year;
    }

    public int getBook_id() {
        return book_id;
    }

    public int getPerson_id() {
        return person_id;
    }

    public void setPerson_id(int person_id) {
        this.person_id = person_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

}

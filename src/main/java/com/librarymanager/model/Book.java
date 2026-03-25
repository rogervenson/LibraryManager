package com.librarymanager.model;

public class Book {
    // This class represents a Book entity. It contains the user attributes and methods pertaining user info.
    private String title;
    private String author;
    private int id;
    private boolean isBorrowed;
    private User borrowedBy;

    public Book(String title, String author, int id) {
        this.title = title;
        this.author = author;
        this.id = id;
    }

    public boolean isBorrowed() {
        return isBorrowed;
    }

    public void borrow(User user) {
        this.isBorrowed = true;
        this.borrowedBy = user;
    }

    public void returnBook() {
        this.isBorrowed = false;
        this.borrowedBy = null;
    }


    public String getTitle() { return title; };

    public int getID () { return id; }

    public String toString() {
        if (borrowedBy == null) {
            return "\nBook: " + title + "\nAuthor: " + author + "\nID: " + id;
        }
        return "\nBook: " + title + "\nAuthor: " + author + "\nID: " + id + "\nBorrowed by: " + borrowedBy.getName() + " (User ID: " + borrowedBy.getID() + ")";
    }
}

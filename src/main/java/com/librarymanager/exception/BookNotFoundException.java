package com.librarymanager.exception;

public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(int id) {
        super("Book not found with ID: " + id);
    }
}

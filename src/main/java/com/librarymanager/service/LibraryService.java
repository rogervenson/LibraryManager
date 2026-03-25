package com.librarymanager.service;

import com.librarymanager.model.Library;
import com.librarymanager.model.Book;
import com.librarymanager.model.User;
import com.librarymanager.model.BorrowResult;
import com.librarymanager.model.ReturnResult;
import com.librarymanager.repository.LibraryRepository;
import com.librarymanager.exception.BookNotFoundException;

public class LibraryService {
    private Library library;
    private LibraryRepository repo;

    public LibraryService(Library library, LibraryRepository repo) {
        this.library = library;
        this.repo = repo;
    }

    // --- BOOKS ---
    public void registerBook(String title, String author, int id) {
        library.registerBook(title, author, id);
        repo.save(library);
    }

    public boolean removeBook(int id) {
        boolean removed = library.removeBook(id);
        if (removed) repo.save(library);
        return removed;
    }

    public void listBooks() {
        library.listBooks();
    }

    public boolean bookExists(int id) {
        try {
            library.findBookByID(id);
            return true;
        } catch (BookNotFoundException e) {
            return false;
        }
    }

    public BorrowResult borrowBook(int bookID, int userID) {
        BorrowResult result = library.borrowBook(bookID, userID);
        if (result == BorrowResult.SUCCESS) repo.save(library);
        return result;
    }

    public ReturnResult returnBook(int bookID) {
        ReturnResult result = library.returnBook(bookID);
        if (result == ReturnResult.SUCCESS) repo.save(library);
        return result;
    }

    public Book findBookByID (int id) {
        return library.findBookByID(id);
    }

    public boolean hasBooks() {
        return library.hasBooks();
    }

    // --- USERS ---
    public void registerUser(String name, int id) {
        library.registerUser(name, id);
        repo.save(library);
    }

    public boolean removeUser(int id) {
        boolean removed = library.removeUser(id);
        if (removed) repo.save(library);
        return removed;
    }

    public void listUsers() {
        library.listUsers();
    }

    public boolean userExists(int id) {
        return library.userExists(id);
    }

    public User findUserByID(int id) {
        return library.findUserByID(id);
    }

    public boolean hasUsers() {
        return library.hasUsers();
    }




}

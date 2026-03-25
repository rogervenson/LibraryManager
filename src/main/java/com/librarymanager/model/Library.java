package com.librarymanager.model;

import java.util.ArrayList;
import com.librarymanager.exception.BookNotFoundException;
import com.librarymanager.exception.UserNotFoundException;

public class Library {
    // This class manages the Library. It holds the registries (books, users) and methods to update them.
    private ArrayList<Book> books = new ArrayList<>();
    private ArrayList<User> users = new ArrayList<>();

    public ArrayList<Book> getBooks() {
        return books;
    }

    public ArrayList<User> getUsers() {
        return users;
    }



    // --- BOOK MANAGEMENT ---
    // To add a book:
    public void registerBook(String newTitle, String newAuthor, int newBookID) {
        books.add(new Book(newTitle, newAuthor, newBookID));
    }

    // To remove a book:a
    public boolean removeBook(int rmBookID) {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getID() == rmBookID) {
                books.remove(i);
                return true;
            }
        }
        return false;
    }

    // To print a list of all books:
    public void listBooks() {
        if (books.isEmpty()){
            System.out.println("\nBook list is empty!");
            return;
        }
        for (Book b : books) {
            System.out.println(b);
        }
    }

    // To find a book by ID:
    public Book findBookByID(int id) {
        for (Book book : books) {
            if (book.getID() == id) return book;
        }
        throw new BookNotFoundException(id);
    }

    // To borrow a book:
    public BorrowResult borrowBook(int bookID, int userID) {
        Book book = findBookByID(bookID);

        User user = findUserByID(userID);

        if (book.isBorrowed()) return BorrowResult.ALREADY_BORROWED;

        book.borrow(user);
        return BorrowResult.SUCCESS;
    }

    // To return a book:
    public ReturnResult returnBook(int bookID) {
        Book book = findBookByID(bookID);

        if (!book.isBorrowed()) return ReturnResult.NOT_BORROWED;

        book.returnBook();
        return ReturnResult.SUCCESS;
    }

    public boolean hasBooks() {
        return !books.isEmpty();
    }

    // --- USER MANAGEMENT ---
    // To add a user:
    public void registerUser(String name, int id) {
        users.add(new User(name, id));
    }

    // To remove a user:
    public boolean removeUser(int rmUserID) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getID() == rmUserID) {
                users.remove(i);
                return true;
            }
        }
        return false;
    }

    // To print a list of all users:
    public void listUsers() {
        if (users.isEmpty()) {
            System.out.println("\nUser list is empty!");
            return;
        }

        for (User u : users) {
            System.out.println(u);
        }
    }

    // To check if a user exists using his or her ID:
    public boolean userExists(int id) {
        for (User user : users) {
            if (user.getID() == id) {
                return true;
            }
        }
        return false;
    }

    // To find a user by ID:
    public User findUserByID(int id) {
        for (User user : users) {
            if (user.getID() == id) return user;

        }
        throw new UserNotFoundException(id);
    }

    public boolean hasUsers() {
        return !users.isEmpty();
    }
}
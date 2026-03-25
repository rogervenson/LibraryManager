package com.librarymanager.ui;


import com.librarymanager.service.LibraryService;
import com.librarymanager.exception.BookNotFoundException;
import com.librarymanager.exception.UserNotFoundException;
import com.librarymanager.model.BorrowResult;
import com.librarymanager.model.ReturnResult;
import com.librarymanager.model.Book;
import com.librarymanager.model.User;
import com.librarymanager.model.Library;
import java.util.Scanner;

public class Menu {
    // This class represents the Menu. It contains the main menu and the submenus, alongside methods for better
    // user interaction.

    Scanner scanner = new Scanner(System.in);
    int choice = 0;

    // Main Menu:
    public void mainMenu(LibraryService service) {
        while (true) {
            System.out.print("--- MAIN MENU ---\n");
            System.out.print("1. Manage books;\n2. Manage users;\n0. Exit.\nChoose an option: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    booksMenu(service);
                    break;
                case 2:
                    usersMenu(service);
                    break;
                case 0:
                    System.out.println("\nAre you sure you want to leave?");
                    pressContinue(scanner);
                    return;
            }
        }
    }

    // Book Management Menu:
    public void booksMenu(LibraryService service){
        while (true) {
            clearScreen();
            System.out.print("--- MANAGE BOOKS ---\n1. Add new book;\n2. Remove book;\n3. List books;\n4. Borrow book;\n5. Return book;\n0. Return to main menu.\nChoose an option: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("To register a new book, please inform the title: ");
                    String newTitle = scanner.nextLine();
                    System.out.print("Inform the author: ");
                    String newAuthor = scanner.nextLine();
                    int newBookID;
                    while (true) {
                        System.out.print("Create the book ID (only numbers): ");
                        newBookID = scanner.nextInt();
                        scanner.nextLine();
                        try {
                            service.findBookByID(newBookID);
                            System.out.println("Book ID already registered! Try again.");
                        } catch (BookNotFoundException e) {
                            break;
                        }
                    }
                    service.registerBook(newTitle, newAuthor, newBookID);
                    System.out.printf("\nAdded '%s' by %s\nID: %d\n", newTitle, newAuthor, newBookID);
                    pressContinue(scanner);
                    break;
                case 2:
                    if (service.hasBooks()) {
                        int rmBookID;
                        while (true) {
                            System.out.print("To remove a book, enter the correspondent ID: ");
                            rmBookID = scanner.nextInt();
                            scanner.nextLine();
                            if (service.removeBook(rmBookID)) {
                                System.out.println("\nBook removed.");
                                break;
                            }
                            System.out.println("Book ID does not exist! Try again.");
                        }
                    } else {
                        System.out.println("\nNothing to remove, book list is empty!");
                    }

                    pressContinue(scanner);
                    break;
                case 3:
                    System.out.println("\nList of current books:");
                    service.listBooks();
                    pressContinue(scanner);
                    break;
                case 4:
                    System.out.print("\nTo borrow a book, inform the book ID: ");
                    int borrowBookID = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("\nInform the ID of the user the book is being lend to: ");
                    int userID = scanner.nextInt();
                    scanner.nextLine();
                    try {
                        BorrowResult borrowResult = service.borrowBook(borrowBookID, userID);
                        if (borrowResult == BorrowResult.SUCCESS) {
                            Book borrowBook = service.findBookByID(borrowBookID);
                            User borrowUser = service.findUserByID(userID);
                            System.out.printf("\n%s successfully borrowed '%s'!\n", borrowUser.getName(), borrowBook.getTitle());
                        } else {
                            Book borrowBook = service.findBookByID(borrowBookID);
                            System.out.printf("\n'%s' is already borrowed!\n", borrowBook.getTitle());
                        }
                    } catch (BookNotFoundException e) {
                        System.out.printf("\nBook not found! There is no book with ID %d!\n", borrowBookID);
                    } catch (UserNotFoundException e) {
                        System.out.printf("\nUser not found! There is no user with ID %d!\n", userID);
                    }
                    pressContinue(scanner);
                    break;
                case 5:
                    System.out.print("\nTo return a book, inform the book ID: ");
                    int returnBookID = scanner.nextInt();
                    scanner.nextLine();
                    try {
                        Book returnBook = service.findBookByID(returnBookID);
                        ReturnResult returnResult = service.returnBook(returnBookID);
                        if (returnResult == ReturnResult.SUCCESS) {
                            System.out.printf("\nSuccessfully returned '%s'!\n", returnBook.getTitle());
                        } else {
                            System.out.printf("\n'%s' is NOT borrowed!\n", returnBook.getTitle());
                        }
                    } catch (BookNotFoundException e) {
                        System.out.printf("\nBook not found! There is no book with ID %d!\n", returnBookID);
                    }
                    pressContinue(scanner);
                    break;
                case 0:
                    clearScreen();
                    return;
            }
        }
    }

    // User Management Menu:
    public void usersMenu(LibraryService service) {
        while (true) {
            clearScreen();
            System.out.print("--- MANAGE USERS ---\n1. Add new user;\n2. Remove user;\n3. List users;\n0. Return to main menu.\nChoose an option: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("To register a new user, please inform the user's name: ");
                    String newName = scanner.nextLine();
                    int newUserID;
                    while (true) {
                        System.out.print("Create the user ID (only numbers): ");
                        newUserID = scanner.nextInt();
                        scanner.nextLine();
                        if (!service.userExists(newUserID)) {
                            break;
                        }
                        System.out.println("User ID already registered. Try again!");
                    }
                    service.registerUser(newName, newUserID);
                    System.out.printf("\nAdded user '%s'\nUser ID: %d\n", newName, newUserID);
                    pressContinue(scanner);
                    break;
                case 2:
                    if (service.hasUsers()) {
                        int rmUserID;
                        while (true) {
                            System.out.print("To remove a user, enter the correspondent ID: ");
                            rmUserID = scanner.nextInt();
                            scanner.nextLine();
                            if (service.removeUser(rmUserID)) {
                                System.out.println("\nUser removed.");
                                break;
                            }
                            System.out.println("User ID does not exist! Try again.");
                        }
                    } else {
                        System.out.println("\nNothing to remove, user list is empty!");
                    }
                    pressContinue(scanner);
                    break;
                case 3:
                    System.out.println("\nList of current users: ");
                    service.listUsers();
                    pressContinue(scanner);
                    break;
                case 0:
                    clearScreen();
                    return;
            }
        }
    }

    // To clear the screen:
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    // To wait for user to press enter:
    public static void pressContinue(Scanner scanner){
        System.out.print("\nPress enter to continue. ");
        scanner.nextLine();
    }
}

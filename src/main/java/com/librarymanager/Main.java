package com.librarymanager;

import com.librarymanager.model.Library;
import com.librarymanager.repository.LibraryRepository;
import com.librarymanager.repository.JsonLibraryRepository;
import com.librarymanager.service.LibraryService;
import com.librarymanager.ui.Menu;

public class Main {
    public static void main(String[] args) {
        LibraryRepository repo = new JsonLibraryRepository();
        Library library = repo.load();
        LibraryService service = new LibraryService(library, repo);

        Menu menu = new Menu();
        Menu.clearScreen();
        System.out.println("--- LIBRARY MANAGER ---");

        menu.mainMenu(service);
    }
}
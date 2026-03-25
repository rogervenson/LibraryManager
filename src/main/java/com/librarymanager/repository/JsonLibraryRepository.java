package com.librarymanager.repository;

import com.librarymanager.model.Library;
import com.google.gson.Gson;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class JsonLibraryRepository implements LibraryRepository {

    public void save(Library library) {
        Gson gson = new Gson();

        try (FileWriter writer = new FileWriter("library.json")) {
            gson.toJson(library, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Library load() {
        Gson gson = new Gson();

        try (FileReader reader = new FileReader("library.json")) {
            return gson.fromJson(reader, Library.class);
        } catch (IOException e) {
            e.printStackTrace();
            return new Library();
        }
    }
}

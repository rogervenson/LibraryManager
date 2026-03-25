package com.librarymanager.model;

public class User {
    // This class represents a User entity. It contains the user attributes and methods pertaining user info.
    private String name;
    private int id;

    public User(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public int getID() {
        return id;
    }

    public String getName() { return name; }

    public String toString() { return "\nUser: " + name + "\nID: " + id; }

}

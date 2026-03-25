package com.librarymanager.repository;

import com.librarymanager.model.Library;

public interface LibraryRepository {
    void save(Library library);
    Library load();
}

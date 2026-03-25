# 📚 Library Manager

A command-line application for managing a library's books and users, built with Java as a learning project with the goal of progressing towards Spring Boot.

## Description

Library Manager allows you to register and remove books and users, borrow and return books, and persist all data locally in a JSON file. The project is structured following the **Controller → Service → Repository** pattern used in Spring Boot applications.

## Technologies

- Java 17
- Maven
- Gson 2.11.0

## Project Structure

```
src/main/java/com/librarymanager/
├── Main.java
├── model/
│   ├── Book.java
│   ├── User.java
│   ├── Library.java
│   ├── BorrowResult.java
│   └── ReturnResult.java
├── repository/
│   ├── LibraryRepository.java       # Interface
│   └── JsonLibraryRepository.java   # JSON implementation
├── service/
│   └── LibraryService.java
├── ui/
│   └── Menu.java
└── exception/
    ├── BookNotFoundException.java
    └── UserNotFoundException.java
```

## How to Run

**Requirements:** Java 17 and Maven installed.

1. Clone the repository:
```bash
git clone https://github.com/rogervenson/LibraryManager
```

2. Navigate to the project folder and build:
```bash
cd LibraryManager
mvn compile
```

3. Run:
```bash
mvn exec:java -Dexec.mainClass="com.librarymanager.Main"
```

Or simply open the project in IntelliJ IDEA and run `Main.java` directly.

## Next Steps

- Migrate to Spring Boot, exposing the library's features as a REST API
- Replace JSON file persistence with a relational database using Spring Data JPA
- Add input validation and proper HTTP error handling

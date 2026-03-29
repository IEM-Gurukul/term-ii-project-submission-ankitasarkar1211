# Library Management System - UML Class Diagram

This document shows the UML Class Diagram for the Library Management System project developed using Java, SQLite, and Swing UI.

---

## Class Diagram

```mermaid
classDiagram

class Book {
    int id
    String title
    String author
    getId()
    getTitle()
    getAuthor()
}

class Student {
    int id
    String name
    getId()
    getName()
}

class DBConnection {
    getConnection()
}

class DBSetup {
    init()
}

class LibraryService {
    addBook(Book)
    getAllBooks()
    issueBook(int)
    returnBook(int)
}

class LibraryUI {
    LibraryService service
}

class Main {
    main()
}

LibraryService --> Book : uses
LibraryService --> DBConnection : uses
DBSetup --> DBConnection : uses

LibraryUI --> LibraryService : interacts
Main --> LibraryUI : starts
Main --> DBSetup : initializes
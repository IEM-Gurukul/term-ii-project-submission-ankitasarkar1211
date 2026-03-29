# Library Management System - UML Class Diagram

This document represents the UML Class Diagram for the Library Management System project developed using Java, SQLite, and Swing UI.

---

## Class Diagram

```plantuml
@startuml

package "models" {
    class Book {
        - id : int
        - title : String
        - author : String
        + getId()
        + getTitle()
        + getAuthor()
    }

    class Student {
        - id : int
        - name : String
        + getId()
        + getName()
    }
}

package "database" {
    class DBConnection {
        + getConnection() : Connection
    }

    class DBSetup {
        + init() : void
    }
}

package "services" {
    class LibraryService {
        + addBook(book : Book) : void
        + getAllBooks() : String
        + issueBook(id : int) : void
        + returnBook(id : int) : void
    }
}

package "ui" {
    class LibraryUI {
        - service : LibraryService
    }
}

class Main {
    + main(args : String[]) : void
}

' Relationships
LibraryService --> Book : uses
LibraryService --> DBConnection : uses
DBSetup --> DBConnection : uses

LibraryUI --> LibraryService : interacts
Main --> LibraryUI : starts
Main --> DBSetup : initializes

@enduml
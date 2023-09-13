package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Library {
    private List<Book> books = new ArrayList<>();

    public void addBook(Book book){
        books.add(book);
    }
    public List<Book> searchBooks(String title){

        return books.stream()
                .filter(book -> book.getTitle().equalsIgnoreCase(title))
                .collect(Collectors.toList());
    }
}



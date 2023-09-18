package org.example.model;

import org.example.model.Book;

import java.util.ArrayList;
import java.util.List;

public class Member {
    private String name;
    private int memberID;
    private List<Book> borrowedBooks = new ArrayList<>();


    public Member(String name, int memberID) {
        this.name = name;
        this.memberID = memberID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMemberID(int memberID) {
        this.memberID = memberID;
    }

    public String getName() {
        return name;
    }

    public int getMemberID() {
        return memberID;
    }

    public List<Book> getborrowedBooks(){
        return borrowedBooks;
    }

    public void borrowBook(Book book){
        borrowedBooks.add(book);
        book.setAvailable(false);
    }

    public void returnBook(Book book){
        borrowedBooks.remove(book);
        book.setAvailable(true);
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }
}


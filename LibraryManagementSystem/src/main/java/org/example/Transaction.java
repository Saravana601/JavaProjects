package org.example;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class Transaction {
    private Member member;
    private Book book;
    private LocalDate transactionDate;
    private LocalDate dueDate;

    public Transaction(Member member, Book book) {
        this.member = member;
        this.book = book;
        this.transactionDate = LocalDate.now();
        this.dueDate = LocalDate.now().plusDays(14);
    }

    public Member getMember() {
        return member;
    }

    public Book getBook() {
        return book;
    }

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void recordBorrowBook(){
        member.borrowBook(book);
    }

    public void recordReturnBook(){
        member.returnBook(book);
    }

    public boolean isOverdue(){
        return LocalDate.now().isAfter(dueDate);
    }

    public int calculateLateFee(){
        if(isOverdue()){
            int lateFeePerDay = 10; // Late fee per day in Rs
            long daysLate = ChronoUnit.DAYS.between(dueDate, LocalDate.now());
            return (int) (lateFeePerDay * daysLate);
        }
        return 0;
    }
}


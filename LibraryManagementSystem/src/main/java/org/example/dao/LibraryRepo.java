package org.example.dao;

import org.example.model.Book;
import org.example.model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class LibraryRepo {
    @Autowired
    private List<Book> bookList;
    @Autowired
    private List<Member> memberList;

    public List<Book> getBookList() {
        return bookList;
    }

    public List<Member> getMemberList() {
        return memberList;
    }
}

package org.example.api;

import org.example.model.Book;
import org.example.model.Member;
import org.example.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class LibraryController {
    @Autowired
    LibraryService libraryService;

    // Book Service Controller

    @PostMapping("book")
    public String addBook(@RequestBody Book book){
        return libraryService.addBook(book);
    }

    @GetMapping("book/{title}")
    public List<Book> getBookByTitle(@PathVariable String title){
        return libraryService.searchBooks(title);
    }

    @GetMapping("books")
    public List<Book> getBooks(){
        return libraryService.getAllBooks();
    }

    // Member Service Controller

    @PostMapping("member")
    public String addMember(@RequestBody Member member){
        return libraryService.addMember(member);
    }

    @GetMapping("member/{memberId}")
    public Member getMemberById(@PathVariable int memberId){
        return libraryService.getByMemberId(memberId);
    }

    public String deleteMember(@PathVariable int memberId){
        return libraryService.removeMember(memberId);
    }
}

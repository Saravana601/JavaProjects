package org.example.service;

import org.example.dao.LibraryRepo;
import org.example.model.Book;
import org.example.model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LibraryService {
    @Autowired
    LibraryRepo libraryRepo;

    // Book Service


    // Get All Books List
    public List<Book> getAllBooks(){
        return libraryRepo.getBookList();
    }

    // add Books
    public String addBook(Book book){
        List<Book> originalList = getAllBooks();
        originalList.add(book);
        return "Book Added in Library";
    }

    // get Book
    public List<Book> searchBooks(String title){
        List<Book> originalList = getAllBooks();

        return originalList.stream()
                .filter(book -> book.getTitle().equalsIgnoreCase(title))
                .collect(Collectors.toList());
    }

    // Member Service


    // Get All Members List
    public List<Member> getAllMembers(){
        return libraryRepo.getMemberList();
    }

    // Add Member
    public String addMember(Member member){
        List<Member> membersList = getAllMembers();
        membersList.add(member);
        return "Member Added Succefully";
    }

    // Get Member By I'd
    public Member getByMemberId(int memberId){
        List<Member> membersList = getAllMembers();
        for(Member member : membersList){
            if(member.getMemberID() == memberId){
                return member;
            }
        }
        return null;
    }

    // displayMemberInfo
    public void displayMemberInfo(int memberId){
        Member member = getByMemberId(memberId);

        if(member != null){
            System.out.println("Member Name :" + member.getName());
            System.out.println("Member Id : " + member.getMemberID());

            List<Book> borrowedBooks = member.getBorrowedBooks();
            if(!borrowedBooks.isEmpty()){
                System.out.println("Borrowed Books");
                for(Book book : borrowedBooks){
                    System.out.println("Title : " + book.getTitle());

                    String dueDate = calculateDueDate(book);

                    System.out.println("Due Date : " + dueDate);
                    System.out.println("");
                }
            }
            else{
                System.out.println("No Borrowed Books found!");
            }
        }
        else{
            System.out.println("Member not found!");
        }
    }
    private String calculateDueDate(Book book){
        int daysAllowed = 14;

        LocalDate currentDate = LocalDate.now();
        LocalDate dueDate = currentDate.plusDays(daysAllowed);

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-mm-dd");
        String formattedDueDate = dueDate.format(dateTimeFormatter);

        return formattedDueDate;
    }

    // Delete Member
    public String removeMember(int memberId){
        List<Member> membersList = getAllMembers();
        membersList.removeIf(member -> member.getMemberID() == memberId);
        return "Member deleted Successfully";
    }

}

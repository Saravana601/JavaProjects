package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Library library = initializeLibrary();
        List<Member> members = initializeMembers();

        System.out.print("Which book you Want to Search : ");
        Scanner scan = new Scanner(System.in);
        String target = scan.nextLine();

        List<Book> foundBooks = library.searchBooks(target);

        if(foundBooks.isEmpty()){
            System.out.println("Book Not Found!");
        }
        else {
            displayFoundBooks(foundBooks);
        }
        for(Member member : members) {
            memberInfo(member);
        }

    }

    private static List<Member> initializeMembers(){
        List<Member> memberToAdd = new ArrayList<>();
        memberToAdd.add(new Member("John",950001));
        memberToAdd.add(new Member("Elliot",950002));
        memberToAdd.add(new Member("Diana",950003));

        return memberToAdd;
    }
    private static void memberInfo(Member member){
        MemberInformationAndInfoDisplay memberInfo = new MemberInformationAndInfoDisplay();
        memberInfo.displayMemberInfo(member.getMemberID());
    }
    private static Library initializeLibrary(){
        List<Book> booksToAdd = new ArrayList<>();

        booksToAdd.add(new Book
                ("Computer Architecture","A.P.Godse","978-93-332-0026-4"));
        booksToAdd.add(new Book
                ("Database Management Systems","A.A.Puntambekar","978-93-332-2129-0"));
        booksToAdd.add(new Book
                ("Relational Database Management Systems","Dr.P.Rizwan AHmed","978-93-86398-14-7"));


        Library library = new Library();
        for(Book book : booksToAdd){
            library.addBook(book);
        }
        return library;
    }
    private static void displayFoundBooks(List<Book> foundBooks){
        for (Book book : foundBooks) {
            if(book.isAvailable()) {
                System.out.println("Your book " + book.getTitle() +
                        " is found in our library which is written by " + book.getAuthor()
                        + ". Also, Your book is available in our Library. Happy Learning");
            }
            else{
                System.out.println("Your book " + book.getTitle() +
                        " is found in our library which is written by " + book.getAuthor()
                        + ". but, Sorry the book isn't available right now. Please check later");
            }
            dueDate(book);
        }
    }
    private static void dueDate(Book book){
        Transaction transaction = new Transaction(null, book); // Dummy transaction, no member needed
        System.out.println("Due Date: " + transaction.getDueDate());

        if(transaction.isOverdue()){
            int toPay = transaction.calculateLateFee();
            System.out.println("You have to pay late fee : " + toPay + " rs");
        }
        else{
            System.out.println("You returned your book on time");
        }
    }
}

package org.example;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class MemberInformationAndInfoDisplay {

    private List<Member> members;
    public MemberInformationAndInfoDisplay() {
        this.members = new ArrayList<>();
    }

    public void registerMember(String name, int memberId){
        Member newMember = new Member(name,memberId);
        members.add(newMember);
    }

    public Member getByMemberId(int memberId){
        for(Member member : members){
            if(member.getMemberID() == memberId){
                return member;
            }
        }
        return null;
    }

    public void addMember(Member member){
        members.add(member);
    }

    public void removeMember(int memberId){
        members.removeIf(member -> member.getMemberID() == memberId);
    }

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
}

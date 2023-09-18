package org.example.dao;

import org.example.model.Book;
import org.example.model.Member;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class DataSource {
    @Bean
    public List<Book> bookList(){
        return new ArrayList<>();
    }
    @Bean
    public List<Member> memberList(){
        return new ArrayList<>();
    }
}

package com.allAboutBasics.graphql.service.datafetcher;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.allAboutBasics.graphql.model.Book;
import com.allAboutBasics.graphql.repository.BookRepository;

import java.util.List;

@Component
public class AllBooksDataFetcher implements DataFetcher<List<Book>>{

    @Autowired
    BookRepository bookRepository;

    @Override
    public List<Book> get(DataFetchingEnvironment dataFetchingEnvironment) {
    	System.out.println(dataFetchingEnvironment.getArguments());
        return bookRepository.findAll();
    }
}

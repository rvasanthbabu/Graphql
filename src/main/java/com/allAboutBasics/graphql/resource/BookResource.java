package com.allAboutBasics.graphql.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.allAboutBasics.graphql.service.GraphQLService;
import com.allAboutBasics.graphql.service.GraphQLUserService;

import graphql.ExecutionResult;

@RestController
public class BookResource {

    @Autowired
    GraphQLService graphQLService;
    @Autowired
    GraphQLUserService graphQLUserService;

    @PostMapping("/rest/books")
    public ResponseEntity<Object> getAllBooks(@RequestBody String query) {
        ExecutionResult execute = graphQLService.getGraphQL().execute(query);

        return new ResponseEntity<>(execute, HttpStatus.OK);
    }
    
    @PostMapping("/graphql/users")
    public ResponseEntity<Object> getAllUsers(@RequestBody String query) {
        ExecutionResult execute = graphQLUserService.getGraphQL().execute(query);

        return new ResponseEntity<>(execute, HttpStatus.OK);
    }
}

package com.allAboutBasics.graphql.service;

import java.io.File;
import java.io.IOException;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.allAboutBasics.graphql.service.datafetcher.AllUsersDataFetcher;
import com.allAboutBasics.graphql.service.datafetcher.AllUsersWithPaginationDataFetcher;

import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;

@Service
public class GraphQLUserService {
    @Value("classpath:users.graphql")
    Resource resource;

    private GraphQL graphQL;
    @Autowired
    private AllUsersWithPaginationDataFetcher allUsersWithPaginationDataFetcher;
    @Autowired
    private AllUsersDataFetcher allUsersDataFetcher; 
    
    // load schema at application start up
    @PostConstruct
    private void loadSchema() throws IOException {
        // get the schema
        File schemaFile = resource.getFile();
        // parse schema
        TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(schemaFile);
        RuntimeWiring wiring = buildRuntimeWiring();
        GraphQLSchema schema = new SchemaGenerator().makeExecutableSchema(typeRegistry, wiring);
        graphQL = GraphQL.newGraphQL(schema).build();
    }

    

    private RuntimeWiring buildRuntimeWiring() {
        return RuntimeWiring.newRuntimeWiring()
                .type("Query", typeWiring -> typeWiring
                        .dataFetcher("allUsersWithPagination", allUsersWithPaginationDataFetcher)
                        .dataFetcher("allUsers", allUsersDataFetcher)
                        )
                .build();
    }


    public GraphQL getGraphQL() {
        return graphQL;
    }
}

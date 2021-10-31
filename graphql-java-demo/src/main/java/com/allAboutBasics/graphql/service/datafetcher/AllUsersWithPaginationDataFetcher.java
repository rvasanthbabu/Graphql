package com.allAboutBasics.graphql.service.datafetcher;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.allAboutBasics.graphql.model.Users;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

@Component
public class AllUsersWithPaginationDataFetcher implements DataFetcher<List<Users>>{

    @Autowired
    RestTemplate restTemplate;
    
    @Override
    public List<Users> get(DataFetchingEnvironment dataFetchingEnvironment) {
    	Users users;
		try {
			users = restTemplate.getForEntity(new URI("https://gorest.co.in/public/v1/users"), Users.class).getBody();
			System.out.println(dataFetchingEnvironment.getArguments());
		} catch (RestClientException | URISyntaxException e) {
			throw new RuntimeException(e);
		}
        return Arrays.asList(users);
    }
}

# Graphql

### About the project
This project highlights how to use GraphQL with java.
This project expose GraphQL endpoint covering the below 2 usecases
* Filter data using graphQL from DB(H2 in this case)
* Filter data from already exposed REST endpoint

### Technologies used
* Graphql
* Spring Boot
* Spring Data
* Spring REST
* H2

### Test requests
#### User API
Request URL: POST http://localhost:8080/graphql/users
1. Query to fetch user details from public REST API with pagination details 
```
{
 allUsersWithPagination{
   data{
      name
      email
      gender
      status
   }
  meta{
     pagination{
        links{
            next
        }
      }
    }
  }
 }
```
2. Query to fetch user details alone from public REST API 
```
{
   getAllUsersResponse: allUsers{
	name
	status
  }
}
```

#### Book API
Request URL: POST http://localhost:8080/rest/books
1. Query to fetch Book details from H2 DB based on ID 
```
{
   book(id: "123") {
     title
     authors
     publisher
   }
}
```
#### Sample postman screen shot
![Alt text](/img/GraphQL-Postman.png?raw=true "Sample Postman screen shot")

### Useful links
* [JSON to Java POJO Generator](https://www.jsonschema2pojo.org/)
* [JSON to GraphQL Schema Generator](https://walmartlabs.github.io/json-to-simple-graphql-schema/)

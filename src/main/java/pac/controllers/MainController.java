package pac.controllers;

import graphql.ExecutionResult;
import graphql.GraphQL;
import pac.graphqlUtilities.GraphQlUtility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class MainController {

    private GraphQL graphQL;
    private GraphQlUtility graphQlUtility;
    @Autowired
    MainController(GraphQlUtility graphQlUtility) throws IOException {
        this.graphQlUtility = graphQlUtility;
        graphQL = graphQlUtility.createGraphQlObject();
    }

    @PostMapping(value = "/query", consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity query(@RequestBody String query){
    	
        ExecutionResult result = graphQL.execute(query);
        System.out.println("errors: "+result.getErrors());
        return ResponseEntity.ok(result.getData());
    }

}

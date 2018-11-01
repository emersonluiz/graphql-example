package br.com.emersonluiz.graphql.graphqlexample.resource;

import br.com.emersonluiz.graphql.graphqlexample.Service.GraphqlService;
import graphql.ExecutionResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/persons")
public class PersonResource {

    @Autowired
    GraphqlService graphqlService;

    @PostMapping
    public ResponseEntity<Object> findAll(@RequestBody String query) {
        ExecutionResult execute = graphqlService.getGraphQL().execute(query);

        return new ResponseEntity<>(execute, HttpStatus.OK);
    }
}

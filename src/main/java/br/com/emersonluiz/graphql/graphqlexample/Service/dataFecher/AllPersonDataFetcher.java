package br.com.emersonluiz.graphql.graphqlexample.Service.dataFecher;

import br.com.emersonluiz.graphql.graphqlexample.model.Person;
import br.com.emersonluiz.graphql.graphqlexample.repository.PersonRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AllPersonDataFetcher implements DataFetcher<List<Person>> {

    @Autowired
    PersonRepository personRepository;

    @Override
    public List<Person> get(DataFetchingEnvironment dataFetchingEnvironment) throws Exception {
        return personRepository.findAll();
    }
}

package br.com.emersonluiz.graphql.graphqlexample.Service.dataFecher;

import br.com.emersonluiz.graphql.graphqlexample.model.Person;
import br.com.emersonluiz.graphql.graphqlexample.repository.PersonRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PersonDataFetcher implements DataFetcher<Person> {

    @Autowired
    PersonRepository personRepository;

    @Override
    public Person get(DataFetchingEnvironment dataFetchingEnvironment) throws Exception {

        String id = dataFetchingEnvironment.getArgument("id");

        return personRepository.findOne(id);
    }
}

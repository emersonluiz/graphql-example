package br.com.emersonluiz.graphql.graphqlexample.Service;

import br.com.emersonluiz.graphql.graphqlexample.Service.dataFecher.AllPersonDataFetcher;
import br.com.emersonluiz.graphql.graphqlexample.Service.dataFecher.PersonDataFetcher;
import br.com.emersonluiz.graphql.graphqlexample.model.Person;
import br.com.emersonluiz.graphql.graphqlexample.repository.PersonRepository;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.UUID;
import java.util.stream.Stream;

@Service
public class GraphqlService {

    @Autowired
    PersonRepository personRepository;

    @Value("classpath:person.graphql")
    Resource resource;

    private GraphQL graphQL;

    @Autowired
    private AllPersonDataFetcher allPersonsDataFetcher;
    @Autowired
    private PersonDataFetcher personDataFetcher;

    @PostConstruct
    private void loadSchema() throws IOException {

        loadDataIntoHSQL();

        File schemaFile = resource.getFile();
        TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(schemaFile);
        RuntimeWiring wiring = buildRuntimeWiring();
        GraphQLSchema schema = new SchemaGenerator().makeExecutableSchema(typeRegistry, wiring);
        graphQL = GraphQL.newGraphQL(schema).build();
    }

    private void loadDataIntoHSQL() {
        Stream.of(
                new Person(UUID.randomUUID().toString(), "Emerson"),
                new Person(UUID.randomUUID().toString(), "Luiz")
        ).forEach(person -> {
            personRepository.save(person);
        });
    }

    private RuntimeWiring buildRuntimeWiring() {
        return RuntimeWiring.newRuntimeWiring()
                .type("Query", typeWiring -> typeWiring
                            .dataFetcher("allPersons", allPersonsDataFetcher)
                            .dataFetcher("person", personDataFetcher))
                .build();
    }

    public GraphQL getGraphQL() {
        return graphQL;
    }
}

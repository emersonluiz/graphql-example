package br.com.emersonluiz.graphql.graphqlexample.repository;

import br.com.emersonluiz.graphql.graphqlexample.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, String> {
}

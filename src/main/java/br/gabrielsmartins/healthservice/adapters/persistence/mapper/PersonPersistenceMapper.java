package br.gabrielsmartins.healthservice.adapters.persistence.mapper;

import br.gabrielsmartins.healthservice.adapters.persistence.entity.PersonEntity;
import br.gabrielsmartins.healthservice.application.domain.Person;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class PersonPersistenceMapper {

    public Person mapToDomain(PersonEntity personEntity){
        var mapper = new ModelMapper();
        return mapper.map(personEntity, Person.class);
    }

    public PersonEntity mapToEntity(Person person){
        var mapper = new ModelMapper();
        return mapper.map(person, PersonEntity.class);
    }
}

package br.gabrielsmartins.healthservice.adapters.persistence.mapper;

import br.gabrielsmartins.healthservice.adapters.persistence.entity.PersonEntity;
import br.gabrielsmartins.healthservice.application.domain.Person;
import br.gabrielsmartins.healthservice.application.domain.enums.Gender;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

@Component
public class PersonPersistenceMapper {

    public Person mapToDomain(PersonEntity personEntity){
        var mapper = new ModelMapper();
        mapper.addMappings(new PropertyMap<PersonEntity, Person>() {
            @Override
            protected void configure() {
              using((Converter<String, Gender>) context -> Gender.fromPrefix(context.getSource().charAt(0))).map(this.source.getGender(), this.destination.getGender());
            }
        });
        return mapper.map(personEntity, Person.class);
    }

    public PersonEntity mapToEntity(Person person){
        var mapper = new ModelMapper();
        mapper.addMappings(new PropertyMap<PersonEntity, PersonEntity>() {
            @Override
            protected void configure() {
                using((Converter<Gender, String>) context -> context.getSource().getPrefix().toString()).map(this.source.getGender(), this.destination.getGender());
            }
        });
        return mapper.map(person, PersonEntity.class);
    }
}

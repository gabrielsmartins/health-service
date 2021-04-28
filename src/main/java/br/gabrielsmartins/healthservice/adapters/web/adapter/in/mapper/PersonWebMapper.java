package br.gabrielsmartins.healthservice.adapters.web.adapter.in.mapper;

import br.gabrielsmartins.healthservice.adapters.web.adapter.in.dto.PersonDTO;
import br.gabrielsmartins.healthservice.application.domain.Person;
import br.gabrielsmartins.healthservice.application.domain.enums.Gender;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

@Component
public class PersonWebMapper {

    public Person mapToDomain(PersonDTO personDTO){
        var mapper = new ModelMapper();
        mapper.addMappings(new PropertyMap<PersonDTO, Person>() {
            @Override
            protected void configure() {
              using((Converter<String, Gender>) context -> Gender.fromPrefix(context.getSource().charAt(0)))
                      .map(this.source.getGender(), this.destination.getGender());
            }
        });
        return mapper.map(personDTO, Person.class);
    }

    public PersonDTO mapToDTO(Person person){
        var mapper = new ModelMapper();
        mapper.addMappings(new PropertyMap<Person, PersonDTO>() {
            @Override
            protected void configure() {
                using((Converter<Gender, String>) context -> String.valueOf(context.getSource().getPrefix()))
                        .map(this.source.getGender(), this.destination.getGender());
            }
        });
        return mapper.map(person, PersonDTO.class);
    }
}

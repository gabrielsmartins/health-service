package br.gabrielsmartins.healthservice.adapters.web.adapter.in.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static br.gabrielsmartins.healthservice.adapters.web.support.PersonDTOSupport.defaultPersonDTO;
import static br.gabrielsmartins.healthservice.application.support.PersonSupport.defaultPerson;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PersonWebMapperTest {

    private PersonWebMapper mapper;

    @BeforeEach
    public void setup(){
        this.mapper = new PersonWebMapper();
    }

    @Test
    @DisplayName("Given Person DTO When Map Then Return Person")
    public void givenPersonDTOWhenMapThenReturnPerson(){

        var personDTO = defaultPersonDTO().build();

        var person = this.mapper.mapToDomain(personDTO);

        assertThat(person).hasNoNullFieldsOrProperties();
        assertThat(person.getId()).isEqualTo(personDTO.getId());
        assertThat(person.getFirstName()).isEqualTo(personDTO.getFirstName());
        assertThat(person.getLastName()).isEqualTo(personDTO.getLastName());
        assertThat(person.getDob()).isEqualTo(personDTO.getDob());
        assertThat(person.getGender().getPrefix().toString()).isEqualTo(personDTO.getGender());
    }

    @Test
    @DisplayName("Given Person When Map Then Return Person DTO")
    public void givenPersonWhenMapThenReturnPersonDTO(){

        var person = defaultPerson().build();

        var personDTO = this.mapper.mapToDTO(person);

        assertThat(personDTO).hasNoNullFieldsOrProperties();
        assertThat(personDTO.getId()).isEqualTo(person.getId());
        assertThat(personDTO.getFirstName()).isEqualTo(person.getFirstName());
        assertThat(personDTO.getLastName()).isEqualTo(person.getLastName());
        assertThat(personDTO.getDob()).isEqualTo(person.getDob());
        assertThat(personDTO.getGender()).isEqualTo(person.getGender().getPrefix().toString());
    }
}

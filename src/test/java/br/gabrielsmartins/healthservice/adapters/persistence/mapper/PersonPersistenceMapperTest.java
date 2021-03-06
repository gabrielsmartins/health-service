package br.gabrielsmartins.healthservice.adapters.persistence.mapper;

import br.gabrielsmartins.healthservice.adapters.persistence.entity.enums.GenderData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static br.gabrielsmartins.healthservice.adapters.persistence.support.PersonEntitySupport.defaultPersonEntity;
import static br.gabrielsmartins.healthservice.application.support.PersonSupport.defaultPerson;
import static org.assertj.core.api.Assertions.assertThat;

public class PersonPersistenceMapperTest {

    private PersonPersistenceMapper mapper;

    @BeforeEach
    public void setup(){
        this.mapper = new PersonPersistenceMapper();
    }

    @Test
    @DisplayName("Given Person When Map Then Return Person Entity")
    public void givenPersonWhenMapThenReturnPersonEntity(){
        var person = defaultPerson().build();

        var personEntity = this.mapper.mapToEntity(person);

        assertThat(personEntity).hasNoNullFieldsOrProperties();
        assertThat(personEntity.getId()).isEqualTo(person.getId());
        assertThat(personEntity.getFirstName()).isEqualTo(person.getFirstName());
        assertThat(personEntity.getLastName()).isEqualTo(person.getLastName());
        assertThat(personEntity.getGender().charAt(0)).isEqualTo(GenderData.fromSource(person.getGender()).getPrefix());
        assertThat(personEntity.getDob()).isEqualTo(person.getDob());
    }

    @Test
    @DisplayName("Given Person Entity When Map Then Return Person")
    public void givenPersonEntityWhenMapThenReturnPerson(){

        var personEntity = defaultPersonEntity().build();

        var person = this.mapper.mapToDomain(personEntity);

        assertThat(person).hasNoNullFieldsOrProperties();
        assertThat(person.getId()).isEqualTo(personEntity.getId());
        assertThat(person.getFirstName()).isEqualTo(personEntity.getFirstName());
        assertThat(person.getLastName()).isEqualTo(personEntity.getLastName());
        assertThat(person.getGender()).isEqualTo(GenderData.fromPrefix(personEntity.getGender().charAt(0)).getSource());
        assertThat(person.getDob()).isEqualTo(personEntity.getDob());
    }
}

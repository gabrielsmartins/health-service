package br.gabrielsmartins.healthservice.adapters.persistence.support;

import br.gabrielsmartins.healthservice.adapters.persistence.entity.enums.GenderData;
import br.gabrielsmartins.healthservice.adapters.persistence.entity.PersonEntity;

import java.time.LocalDate;
import java.util.UUID;

public class PersonEntitySupport {

    private PersonEntitySupport(){
        super();
    }

    public static PersonEntity.PersonEntityBuilder defaultPersonEntity(){
        return PersonEntity.builder()
                     .withId(UUID.randomUUID())
                     .withFirstName("Foo")
                     .withLastName("Bar")
                     .withGender(String.valueOf(GenderData.MALE.getPrefix()))
                     .withDob(LocalDate.now());
    }
}

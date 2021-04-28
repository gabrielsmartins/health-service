package br.gabrielsmartins.healthservice.application.support;

import br.gabrielsmartins.healthservice.application.domain.enums.Gender;
import br.gabrielsmartins.healthservice.application.domain.Person;

import java.time.LocalDate;
import java.util.UUID;

public class PersonSupport {

    private PersonSupport(){
        super();
    }

    public static Person.PersonBuilder defaultPerson(){
        return Person.builder()
                     .withId(UUID.randomUUID())
                     .withName("Foo")
                     .withLastName("Bar")
                     .withGender(Gender.MALE)
                     .withDob(LocalDate.now());
    }
}

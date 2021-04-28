package br.gabrielsmartins.healthservice.adapters.web.support;

import br.gabrielsmartins.healthservice.adapters.web.adapter.in.dto.PersonDTO;
import br.gabrielsmartins.healthservice.application.domain.enums.Gender;

import java.time.LocalDate;
import java.util.UUID;

public class PersonDTOSupport {

    private PersonDTOSupport(){
        super();
    }

    public static PersonDTO.PersonDTOBuilder defaultPersonDTO(){
        return PersonDTO.builder()
                     .withId(UUID.randomUUID())
                     .withFirstName("Foo")
                     .withLastName("Bar")
                     .withGender(String.valueOf(Gender.MALE.getPrefix()))
                     .withDob(LocalDate.now());
    }
}

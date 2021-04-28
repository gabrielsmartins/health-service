package br.gabrielsmartins.healthservice.application.ports.in;

import br.gabrielsmartins.healthservice.application.domain.Person;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface SearchPersonUseCase {

    Mono<Person> findById(UUID id);

}

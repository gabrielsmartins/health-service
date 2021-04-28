package br.gabrielsmartins.healthservice.application.ports.out;

import br.gabrielsmartins.healthservice.application.domain.Person;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface SearchPersonPort {

    Mono<Person> findById(UUID id);

}

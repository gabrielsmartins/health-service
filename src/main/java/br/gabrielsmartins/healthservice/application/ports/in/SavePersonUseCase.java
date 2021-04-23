package br.gabrielsmartins.healthservice.application.ports.in;

import br.gabrielsmartins.healthservice.application.domain.Person;
import reactor.core.publisher.Mono;

public interface SavePersonUseCase {

    Mono<Person> save(Person person);

}

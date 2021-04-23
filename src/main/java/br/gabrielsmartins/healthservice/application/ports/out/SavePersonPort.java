package br.gabrielsmartins.healthservice.application.ports.out;

import br.gabrielsmartins.healthservice.application.domain.Person;
import reactor.core.publisher.Mono;

public interface SavePersonPort {

    Mono<Person> save(Person person);

}

package br.gabrielsmartins.healthservice.application.service;

import br.gabrielsmartins.healthservice.application.domain.Person;
import br.gabrielsmartins.healthservice.application.ports.in.SavePersonUseCase;
import br.gabrielsmartins.healthservice.application.ports.out.SavePersonPort;
import br.gabrielsmartins.healthservice.common.UseCase;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@UseCase
@RequiredArgsConstructor
public class SavePersonService implements SavePersonUseCase {

    private final SavePersonPort port;

    @Override
    public Mono<Person> save(Person person) {
        return this.port.save(person);
    }

}

package br.gabrielsmartins.healthservice.application.service;

import br.gabrielsmartins.healthservice.application.domain.Person;
import br.gabrielsmartins.healthservice.application.ports.in.SearchPersonUseCase;
import br.gabrielsmartins.healthservice.application.ports.out.SearchPersonPort;
import br.gabrielsmartins.healthservice.common.UseCase;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.UUID;

@UseCase
@RequiredArgsConstructor
public class SearchPersonService implements SearchPersonUseCase {

    private final SearchPersonPort port;

    @Override
    public Mono<Person> findById(UUID id) {
        return this.port.findById(id);
    }

}

package br.gabrielsmartins.healthservice.adapters.persistence.adapter;

import br.gabrielsmartins.healthservice.adapters.persistence.mapper.PersonPersistenceMapper;
import br.gabrielsmartins.healthservice.adapters.persistence.service.ISearchPersonPersistenceService;
import br.gabrielsmartins.healthservice.application.domain.Person;
import br.gabrielsmartins.healthservice.application.ports.out.SearchPersonPort;
import br.gabrielsmartins.healthservice.common.PersistenceAdapter;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.UUID;

@PersistenceAdapter
@RequiredArgsConstructor
public class SearchPersonPersistenceAdapter implements SearchPersonPort {

    private final ISearchPersonPersistenceService service;
    private final PersonPersistenceMapper mapper;

    @Override
    public Mono<Person> findById(UUID id) {
        return this.service.findById(id).map(this.mapper::mapToDomain);
    }

}

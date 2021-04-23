package br.gabrielsmartins.healthservice.adapters.persistence.adapter;

import br.gabrielsmartins.healthservice.adapters.persistence.mapper.PersonPersistenceMapper;
import br.gabrielsmartins.healthservice.adapters.persistence.service.ISavePersonPersistenceService;
import br.gabrielsmartins.healthservice.application.domain.Person;
import br.gabrielsmartins.healthservice.application.ports.out.SavePersonPort;
import br.gabrielsmartins.healthservice.common.PersistenceAdapter;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@PersistenceAdapter
@RequiredArgsConstructor
public class SavePersonPersistenceAdapter implements SavePersonPort {

    private final ISavePersonPersistenceService service;
    private final PersonPersistenceMapper mapper;

    @Override
    public Mono<Person> save(Person person) {
        var personEntity = this.mapper.mapToEntity(person);
        return this.service.save(personEntity).map(this.mapper::mapToDomain);
    }

}

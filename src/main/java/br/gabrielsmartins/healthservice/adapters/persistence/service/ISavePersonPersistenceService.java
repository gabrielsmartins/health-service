package br.gabrielsmartins.healthservice.adapters.persistence.service;

import br.gabrielsmartins.healthservice.adapters.persistence.entity.PersonEntity;
import reactor.core.publisher.Mono;

public interface ISavePersonPersistenceService {

    Mono<PersonEntity> save(PersonEntity personEntity);

}

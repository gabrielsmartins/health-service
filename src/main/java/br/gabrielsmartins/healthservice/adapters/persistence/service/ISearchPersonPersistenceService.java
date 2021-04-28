package br.gabrielsmartins.healthservice.adapters.persistence.service;

import br.gabrielsmartins.healthservice.adapters.persistence.entity.PersonEntity;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface ISearchPersonPersistenceService {

    Mono<PersonEntity> findById(UUID id);

}

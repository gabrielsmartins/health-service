package br.gabrielsmartins.healthservice.adapters.persistence.service;

import br.gabrielsmartins.healthservice.adapters.persistence.entity.PersonEntity;
import br.gabrielsmartins.healthservice.adapters.persistence.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class SavePersonPersistenceService implements ISavePersonPersistenceService {

    private final PersonRepository repository;

    @Override
    public Mono<PersonEntity> save(PersonEntity personEntity) {
        return this.repository.save(personEntity);
    }
}

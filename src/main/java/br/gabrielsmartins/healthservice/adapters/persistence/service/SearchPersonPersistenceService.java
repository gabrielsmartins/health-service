package br.gabrielsmartins.healthservice.adapters.persistence.service;

import br.gabrielsmartins.healthservice.adapters.persistence.entity.PersonEntity;
import br.gabrielsmartins.healthservice.adapters.persistence.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SearchPersonPersistenceService implements ISearchPersonPersistenceService {

    private final PersonRepository repository;

    @Override
    public Mono<PersonEntity> findById(UUID id) {
        return this.repository.findById(id);
    }
}

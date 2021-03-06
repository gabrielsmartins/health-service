package br.gabrielsmartins.healthservice.adapters.persistence.repository;

import br.gabrielsmartins.healthservice.adapters.persistence.entity.PersonEntity;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PersonRepository extends ReactiveSortingRepository<PersonEntity, UUID> {

}

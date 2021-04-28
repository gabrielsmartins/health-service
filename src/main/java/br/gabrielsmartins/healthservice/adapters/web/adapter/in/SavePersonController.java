package br.gabrielsmartins.healthservice.adapters.web.adapter.in;

import br.gabrielsmartins.healthservice.adapters.web.adapter.in.dto.PersonDTO;
import br.gabrielsmartins.healthservice.adapters.web.adapter.in.mapper.PersonWebMapper;
import br.gabrielsmartins.healthservice.application.ports.in.SavePersonUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping("/health-v1/persons")
@RequiredArgsConstructor
@Validated
public class SavePersonController {

    private final PersonWebMapper mapper;
    private final SavePersonUseCase useCase;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<PersonDTO> save(@RequestBody @Valid PersonDTO personDTO){
        var person = this.mapper.mapToDomain(personDTO);
        return this.useCase.save(person).map(mapper::mapToDTO);
    }
}

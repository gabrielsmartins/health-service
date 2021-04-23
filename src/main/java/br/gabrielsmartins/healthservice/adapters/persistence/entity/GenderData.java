package br.gabrielsmartins.healthservice.adapters.persistence.entity;

import br.gabrielsmartins.healthservice.application.domain.Gender;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.stream.Stream;

@Getter
@RequiredArgsConstructor
public enum GenderData {

    MALE('M', Gender.MALE),
    FEMALE('F', Gender.FEMALE),
    UNKNOWN('U', Gender.UNKNOWN);

    private final Character prefix;
    private final Gender source;

    public static GenderData fromPrefix(Character prefix){
       return Stream.of(GenderData.values())
                .filter(genderData -> genderData.getPrefix().equals(prefix))
                .findFirst()
                .orElse(GenderData.UNKNOWN);
    }

    public static GenderData fromSource(Gender source){
        return Stream.of(GenderData.values())
                .filter(genderData -> genderData.getSource() == source)
                .findFirst()
                .orElse(GenderData.UNKNOWN);
    }

}

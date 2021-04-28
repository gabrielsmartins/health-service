package br.gabrielsmartins.healthservice.application.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.stream.Stream;

@Getter
@RequiredArgsConstructor
public enum Gender {

    MALE('M', "Male"),
    FEMALE('F', "Female"),
    UNKNOWN('U', "Male"),;

    private final Character prefix;
    private final String description;

    public static Gender fromPrefix(Character prefix){
       return Stream.of(Gender.values())
                .filter(gender -> gender.getPrefix().equals(prefix))
                .findFirst()
                .orElse(Gender.UNKNOWN);
    }

    public static Gender fromDescription(String description){
        return Stream.of(Gender.values())
                .filter(gender -> gender.getDescription().equals(description))
                .findFirst()
                .orElse(Gender.UNKNOWN);
    }

}

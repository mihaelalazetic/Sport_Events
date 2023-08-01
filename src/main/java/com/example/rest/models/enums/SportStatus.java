package com.example.rest.models.enums;

import com.example.rest.models.Pair;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum SportStatus {
    COMPLETED("Завршен"),
    INCOMPLETE("Незавршен"),
    NOT_STARTED("Не започнат"),
    ACTIVE("Во тек");

    public static List<Pair> getSportStatusValues(){
        return Stream.of(SportStatus.class.getEnumConstants())
//                .map(Enum::name)
                .map((item)->
                        new Pair(item.name(),item.toString())
                )
                .collect(Collectors.toList());
//        return new ArrayList<SportStatus>(List.of(values()));
    }

    private final String name;

    private SportStatus(String s) {
        name = s;
    }

    public boolean equalsName(String otherName) {
        // (otherName == null) check is not needed because name.equals(null) returns false
        return name.equals(otherName);
    }

    public String toString() {
        return this.name;
    }
}

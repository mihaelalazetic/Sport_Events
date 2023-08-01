package com.example.rest.models.enums;

import com.example.rest.models.Pair;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum SportType {
    BASKETBALL("Кошарка"),
    MULTI_SPORT("Мулти Спорт"),
    RUGBY("Рагби"),
    SOCCER("Фудбал"),
    TENNIS("Тенис"),
    VOLLEYBALL("Одбојка"),
    HANDBALL("Ракомет"),
    SKIING("Скијање"),
    BASEBALL("Бејзбол"),
    BOXING("Бокс"),
    HOCKEY("Хокеј");

    public static List<Pair> getSportTypeValues(){
        return Stream.of(SportType.class.getEnumConstants())
//                .map(Enum::name)
                .map((item)->
                        new Pair(item.name(),item.toString())
                )
                .collect(Collectors.toList());
//        return new ArrayList<SportType>(List.of(values()));
    }

    private final String name;

    private SportType(String s) {
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

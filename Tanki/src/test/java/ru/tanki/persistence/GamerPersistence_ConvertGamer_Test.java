package ru.tanki.persistence;

import ru.tanki.domain.Gamer;

import java.util.Map;
import java.util.Objects;

public class GamerPersistence_ConvertGamer_Test {

    private static final GamerPersistance gamerPersistence = new GamerPersistance();

    public static void main(String[] args) {

        Map<String, String> input = Map.of(
                "nameid", "OLEG",
                "gametime", "3"
        );

        Gamer gamer = gamerPersistence.convertGamer(input);

        if (!Objects.equals(gamer.getNameId(), "OLEG")) {
            throw new RuntimeException(
                    "Invalid. Actual infl: " + gamer.getNameId()
            );
        }

        if (!Objects.equals(gamer.getTime(), 3)) {
            throw new RuntimeException(
                    "Invalid. Actual infl: " + gamer.getTime()
            );
        }
    }
}
package ru.tanki.persistence;

import org.junit.jupiter.api.Test;
import ru.tanki.domain.Gamer;

import java.util.Map;
import java.util.Objects;

import static org.mockito.BDDMockito.then;

public class GamerPersistence_ConvertGamer_Test {

    private static final GamerPersistance gamerPersistence = new GamerPersistance();
    @Test
    public void checkCreation() {
        Map<String, String> input = Map.of(
                "nameid", "OLEG",
                "gametime", "3"
        );

        Gamer gamer = gamerPersistence.convertGamer(input);

        then(gamer.getNameId()).equals("OLEG");
        then(gamer.getTime()).equals(3);
    }
}

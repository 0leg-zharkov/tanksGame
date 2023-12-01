package ru.tanki.persistence;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.tanki.domain.Gamer;

import static org.assertj.core.api.Java6BDDAssertions.then;

public class GamerPersistance_GetByNameId_Test {

    private  final GamerPersistance gamerPersistance = new GamerPersistance();

    @Test
    @DisplayName("when gamer exists in DB then return null")
    public void whenGamerExistsInDbThenReturnNull() {
        Gamer gamer = gamerPersistance.getByNameId("OLEG");

        then(gamer).isNotNull();
        then(gamer.getNameId()).isEqualTo("OLEG");
        then(gamer.getTime()).isEqualTo(3);
    }

}

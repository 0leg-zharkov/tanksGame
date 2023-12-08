package ru.tanki.service;

import org.junit.jupiter.api.Test;
import ru.tanki.GamerService;
import ru.tanki.NumberEqualsService;
import ru.tanki.domain.Gamer;

import java.util.List;

import static org.mockito.BDDMockito.then;

public class GamerServiceTest {

    @Test
    public void checkAverage() {

        GamerService gamerService = new GamerService();
        NumberEqualsService numberEqualsService = new NumberEqualsService();

        List<Gamer> gamers = List.of(
                new Gamer("OLEG", 5),
                new Gamer("ARTUR", 7),
                new Gamer("LEGO", 9)
        );

        float actual = gamerService.countAverageAge(gamers);

        then(numberEqualsService.lessFloat(actual, 10));
    }
}

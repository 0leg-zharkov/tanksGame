package ru.tanki;

import ru.tanki.domain.Gamer;

import java.util.List;

public class GamerService {

    public float countAverageAge(List<Gamer> gamers) {
        int sum = gamers.stream()
                .mapToInt(Gamer::getTime)
                .sum();
        return sum / (float) gamers.size();
    }
}

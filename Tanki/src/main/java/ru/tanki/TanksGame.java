package ru.tanki;

import ru.tanki.frames.BeginFrame;
import ru.tanki.persistence.GamerPersistance;

public class TanksGame {

    private static final BeginFrame frame = new BeginFrame();

    public static void main(String[] args) {
        GamerPersistance gamerPersistance = new GamerPersistance();
        gamerPersistance.createGamer("OLEG", 3);
        gamerPersistance.getAll();
        frame.init();
    }
}
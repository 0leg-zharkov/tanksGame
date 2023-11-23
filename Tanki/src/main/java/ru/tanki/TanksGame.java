package ru.tanki;

import ru.tanki.frames.BeginFrame;
import ru.tanki.persistence.GamerPersistance;

public class TanksGame {

    public GamerPersistance gamerPersistance = new GamerPersistance();
    private static final BeginFrame frame = new BeginFrame();

    public static void main(String[] args) {
        frame.init();
    }
}
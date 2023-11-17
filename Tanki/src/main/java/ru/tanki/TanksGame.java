package ru.tanki;

import ru.tanki.frames.BeginFrame;

public class TanksGame {
    private static final BeginFrame frame = new BeginFrame();

    public static void main(String[] args) {
        frame.init();
    }
}
package ru.tanki.frames;

import ru.tanki.frames.panel.NewType;
import ru.tanki.frames.threads.MyThread;

import javax.swing.*;

public class GameFrame extends JFrame {

    private NewType game;
    private String gamerName;
    Thread thread;
    public void start(String gamerName) {
        game = new NewType();
        this.add(game);
        this.setTitle("Tanks");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.thread = new MyThread(this);
        this.gamerName = gamerName;
        thread.start();
    }

    public NewType type() {
        return game;
    }

    public String getGamerName() {
        return gamerName;
    }
}

package ru.tanki.frames;

import ru.tanki.frames.panel.NewType;
import ru.tanki.frames.threads.MyThread;
import ru.tanki.persistence.GamerPersistance;

import javax.swing.*;

public class GameFrame extends JFrame {

    private NewType game;
    private String gamerName;
    private Thread thread;
    private GamerPersistance gamerPersistance;

    public void start(String gamerName, GamerPersistance gamerPersistance) {
        game = new NewType();
        this.add(game);
        this.setTitle("Tanks");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.gamerPersistance = gamerPersistance;
        this.gamerName = gamerName;
        this.thread = new MyThread(this);
        System.out.println("gamerName in game set. " + gamerName);
        thread.start();
    }

    public NewType type() {
        return game;
    }

    public String getGamerName() {
        return gamerName;
    }

    public GamerPersistance getGamerPersistance() {
        return gamerPersistance;
    }
}

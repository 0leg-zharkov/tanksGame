package ru.tanki.frames.threads;

import ru.tanki.domain.Gamer;
import ru.tanki.frames.GameFrame;
import ru.tanki.frames.panel.NewType;
import ru.tanki.persistence.GamerPersistance;

import java.util.List;

public class MyThread extends Thread{

    private final NewType type;
    private final GameFrame frame;
    private final String gamerName;
    private GamerPersistance gamerPersistance;
    private boolean isOld;

    public MyThread(GameFrame frame) {
        super("MyThread");
        this.frame = frame;
        type = frame.type();
        gamerName = frame.getGamerName();
        gamerPersistance = frame.getGamerPersistance();
        if (gamerPersistance == null) {
            isOld = false;
        } else {
            List<Gamer> gamersList = frame.getGamerPersistance().getAll();
            for (Gamer gamer : gamersList) {
                isOld = gamerName.equals(gamer.getNameId());
            }
        }
    }

    public void run() {
        long beginTime = System.currentTimeMillis();
        long finishTime;
        while (true) {
            if (type.checkCollisions()) {
                type.move();
                type.moveEnemy();
                type.gamerShoot();
                type.pcShoot();
                try {
                    Thread.sleep(120);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                finishTime = System.currentTimeMillis();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                        e.printStackTrace();
                }
                int result = Integer.parseInt(String.valueOf((finishTime - beginTime) / 1000));
                //закоментил, чтобы бд не тормошить
                if (isOld) {
                    gamerPersistance.updateGamer(gamerName, result);
                } else {
                    gamerPersistance.createGamer(gamerName, result);
                }
                System.out.println(result + " res");
                frame.dispose();
                break;
            }
        }
    }
}
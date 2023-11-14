import javax.swing.*;
import java.awt.*;

public class MyThread extends Thread{

    private NewType type;
    private GameFrame frame;
    private String isContinue = " ";

    public MyThread(GameFrame frame) {
        super("MyThread");
        this.frame = frame;
        type = frame.type();
    }

    public void run() {

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
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                        e.printStackTrace();
                }
                frame.dispose();
                break;
            }
        }
    }
}

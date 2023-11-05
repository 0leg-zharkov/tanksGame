public class MyThread extends Thread{

    private NewType type;
    private GameFrame frame;

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
                frame.dispose();
                break;
            }
        }
    }
}

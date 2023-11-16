public class MyThread extends Thread{

    private NewType type;
    private GameFrame frame;
    private GamerPersistance gamerPersistance;

    public MyThread(GameFrame frame) {
        super("MyThread");
        this.frame = frame;
        type = frame.type();
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
                finishTime = System.currentTimeMillis();;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                        e.printStackTrace();
                }
                frame.dispose();
                break;
            }
        }
        int result = Integer.parseInt(String.valueOf((finishTime - beginTime) / 1000));
        gamerPersistance = new GamerPersistance();
        gamerPersistance.createGamer(frame.getGamerName(), result);
        System.out.println(result + " res");
    }
}
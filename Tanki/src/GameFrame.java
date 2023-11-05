import javax.swing.*;

public class GameFrame extends JFrame {

    private NewType game;
    Thread thread;
    public void start() {
        game = new NewType();
        this.add(game);
        this.setTitle("Tanks");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.thread = new MyThread(this);
        thread.start();
    }

    public boolean rest() {
        return game.over;
    }

    public NewType type() {
        return game;
    }
}

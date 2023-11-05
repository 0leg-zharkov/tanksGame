import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.Timer;
import java.util.TimerTask;

import static nums.AllNums.*;

public class NewType extends JPanel implements ActionListener {


    private int gamerX = SPAWN_MID;
    private int gamerY = SCREEN_HEIGHT;
    private int rand;
    private int pcX;
    private int pcY;
    private int gamerBulletX;
    private int gamerBulletY;
    private int pcBulletX;
    private int pcBulletY;
    private char direction;
    private char bulletDirection;
    private char pcDirection;
    private char pcBulletDirection;
    private char fire = 'N';
    private char whoWon;
    private boolean checkFire;
    private Random random;
    private Thread thread;
    private boolean bulletCollision = true;
     boolean over = false;

    public NewType() {

        random = new Random();
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.black);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        this.thread = new MyThread(this);
        startGame();
        thread.start();
    }

    public void startGame() {
        spawnEnemy();
        spawnGamer();
        spawnPcBullet();
        pcBulletDirection = 'L';
        pcShoot();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
       if (checkCollisions()) {
            draw(g);
            if (fire == 'F') {
                bulletDraw(g);
            }
            pcBulletDraw(g);
        } else {
           gameOver(g);
           //this.setVisible(false);
           over = true;
           System.out.println(pcX);
           System.out.println(pcY);
        }
    }

    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(pcX, pcY, UNIT_SIZE, UNIT_SIZE);
        g.setColor(Color.MAGENTA);
        g.fillRect(gamerX, gamerY, UNIT_SIZE, UNIT_SIZE);
        for (int i = Y_DOWN_BARR; i != Y_UP_BARR; i--) {
            g.setColor(Color.ORANGE);
            g.fillRect(BARRIER, i, UNIT_SIZE, UNIT_SIZE);
            g.fillRect(SCREEN_WIDTH - BARRIER, i, UNIT_SIZE, UNIT_SIZE);
            g.fillRect(i, Y_BARRIER, UNIT_SIZE, UNIT_SIZE);
        }
        g.setColor(Color.GREEN);
        g.fillRect(BARRIER + BORDER, Y_DOWN_BARR, Y_DOWN_BARR - UNIT_SIZE, UNIT_SIZE * 3);
        g.fillRect(BARRIER + BORDER, Y_UP_BARR - SPAWN_MID, Y_DOWN_BARR - UNIT_SIZE,
                UNIT_SIZE * 3);
        this.repaint();
    }

    public void bulletDraw(Graphics g) {
        g.setColor(Color.YELLOW);
        g.fillOval(gamerBulletX, gamerBulletY, BULLET_SIZE, BULLET_SIZE);
        this.repaint();
    }

    public void pcBulletDraw(Graphics g) {
        g.setColor(Color.red);
        g.fillOval(pcBulletX, pcBulletY, BULLET_SIZE, BULLET_SIZE);
        this.repaint();
    }

    public void spawnEnemy() {
        pcX = SCREEN_WIDTH - SPAWN_MID * 2;
        pcY = UNIT_SIZE;

    }

    public void spawnGamer() {
        gamerX = SPAWN_MID;
        gamerY = SCREEN_HEIGHT - BORDER;
        direction = 'R';
    }

    public void move() {
        switch(direction) {
            case 'U':
                gamerY -= UNIT_SIZE;
                this.repaint();
                break;
            case 'D':
                gamerY += UNIT_SIZE;
                this.repaint();
                break;
            case 'L':
                gamerX -= UNIT_SIZE;
                this.repaint();
                break;
            case 'R':
                gamerX += UNIT_SIZE;
                this.repaint();
                break;
        }
    }

    public void moveEnemy() {
        rand = random.nextInt(1, 5);
        switch (rand) {
            case 1:
                if (pcX >= SCREEN_WIDTH - BORDER) {
                    pcY += UNIT_SIZE;
                    pcX -= UNIT_SIZE;
                    pcDirection = 'L';
                } else {
                    pcX += UNIT_SIZE;
                    pcDirection = 'R';
                }
                break;
            case 2:
                if (pcX == UNIT_SIZE) {
                    pcY += UNIT_SIZE;
                    pcX += UNIT_SIZE;
                    pcDirection = 'R';
                } else if (pcX == SCREEN_HEIGHT - BORDER && pcY < Y_DOWN_BARR + UNIT_SIZE && pcY >= Y_UP_BARR) {
                    pcY += UNIT_SIZE;
                    pcDirection = 'D';
                } else {
                    pcX -= UNIT_SIZE;
                    pcDirection = 'L';
                }
                break;
            case 3:
                if (pcY == SCREEN_HEIGHT - BORDER) {
                    pcX += UNIT_SIZE;
                    pcY -= UNIT_SIZE;
                    pcDirection = 'U';
                } else if (pcX < Y_DOWN_BARR + UNIT_SIZE && pcX > Y_UP_BARR - UNIT_SIZE
                        && pcY == Y_BARRIER - UNIT_SIZE) {
                    pcX += UNIT_SIZE;
                    pcY -= UNIT_SIZE;
                    pcDirection = 'U';
                } else if ((pcY < Y_DOWN_BARR + UNIT_SIZE && pcY > Y_UP_BARR - BORDER)
                        && (pcX == BARRIER + UNIT_SIZE || pcX == SCREEN_WIDTH - BARRIER - UNIT_SIZE)) {
                    pcX -= UNIT_SIZE;
                    pcY -= UNIT_SIZE;
                    pcDirection = 'U';
                } else if (pcY == Y_UP_BARR - UNIT_SIZE && pcX == SCREEN_WIDTH - BARRIER) {
                    pcX -= UNIT_SIZE;
                    pcDirection = 'L';
                } else if (pcY >= Y_UP_BARR - UNIT_SIZE && pcY <= Y_DOWN_BARR + UNIT_SIZE
                        && pcX == SCREEN_WIDTH - BARRIER - UNIT_SIZE) {
                    pcY += UNIT_SIZE;
                    pcDirection = 'D';
                } else if(pcY == Y_BARRIER && pcX == Y_DOWN_BARR + UNIT_SIZE) {
                    pcY += UNIT_SIZE;
                    pcDirection = 'D';
                }else {
                    pcY += UNIT_SIZE;
                    pcDirection = 'D';
                }
                break;
            case 4:
                if (pcY == UNIT_SIZE) {
                    pcX += UNIT_SIZE;
                    pcY += UNIT_SIZE;
                    pcDirection = 'R';
                } else {
                    pcY -= UNIT_SIZE;
                    pcDirection = 'U';
                }
                break;

        }
        // xz почему, но так лучше начал работать?????
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {

            }
        };
        Timer timer = new Timer(true);
        // будем запускать каждых 10 секунд (10 * 1000 миллисекунд)
        timer.scheduleAtFixedRate(timerTask, 0, 20*1000);
    }

    public boolean checkCollisions() {
        if (gamerY == 0 || gamerY == SCREEN_HEIGHT - UNIT_SIZE || gamerX == 0 || gamerX == SCREEN_WIDTH - UNIT_SIZE) {
            whoWon = 'E';
            return false;
        } else if (gamerBulletX == pcX && gamerBulletY == pcY) {
            whoWon = 'G';
            return false;
        } else if (!bulletCollision) {
            whoWon = 'E';
            return false;
        } else if (gamerX == pcX && gamerY == pcY) {
            whoWon = 'D';
            return false;
        } else if (pcY == 0 || pcY == SCREEN_HEIGHT - UNIT_SIZE || pcX == 0 || pcX == SCREEN_WIDTH - UNIT_SIZE) {
            System.out.println(pcX);
            System.out.println(pcY);
            System.out.println(direction);
            whoWon = 'G';
            return false;
        } else if ((gamerY < Y_DOWN_BARR + UNIT_SIZE && gamerY > Y_UP_BARR - UNIT_SIZE) &&
                (gamerX == BARRIER || gamerX == SCREEN_WIDTH - BARRIER)) {
            whoWon = 'E';
            return false;
        } else if ((pcY < Y_DOWN_BARR + UNIT_SIZE && pcY > Y_UP_BARR - UNIT_SIZE)
                && (pcX == BARRIER || pcX == SCREEN_WIDTH - BARRIER)) {
            whoWon = 'G';
            System.out.println(pcX);
            System.out.println(pcY);
            System.out.println(direction);
            return false;
        } else if (gamerX < Y_DOWN_BARR + UNIT_SIZE && gamerX > Y_UP_BARR - UNIT_SIZE && gamerY == Y_BARRIER) {
            whoWon = 'E';
            return false;
        } else if (pcX < Y_DOWN_BARR + UNIT_SIZE && pcX > Y_UP_BARR - UNIT_SIZE && pcY == Y_BARRIER) {
            whoWon = 'G';
            System.out.println(pcX);
            System.out.println(pcY);
            System.out.println(direction);
            return false;
        }
        return true;
    }

    public void spawnBullet() {
        gamerBulletX = gamerX;
        gamerBulletY = gamerY;
        bulletDirection = direction;
        checkFire = true;
    }
    public void gameOver(Graphics g) {
        g.setColor(Color.red);
        g.setFont(new Font("Ink Free", Font.BOLD, 75));
        FontMetrics metrics = getFontMetrics(g.getFont());
        switch (whoWon) {
            case 'E':
                g.drawString("GAME OVER", (SCREEN_WIDTH - metrics.stringWidth("GAME OVER")) / 2,
                        SCREEN_HEIGHT / 2);
                g.drawString("YOU LOST", (SCREEN_WIDTH - metrics.stringWidth("YOU LOST")) / 2,
                        (SCREEN_HEIGHT / 2) + metrics.getHeight());
                break;
            case 'G':
                g.drawString("GAME OVER", (SCREEN_WIDTH - metrics.stringWidth("GAME OVER")) / 2,
                        SCREEN_HEIGHT / 2);
                g.drawString("YOU WON", (SCREEN_WIDTH - metrics.stringWidth("YOU WON")) / 2,
                        (SCREEN_HEIGHT / 2) + metrics.getHeight());
                break;
            case 'D':
                g.drawString("GAME OVER", (SCREEN_WIDTH - metrics.stringWidth("GAME OVER")) / 2,
                        SCREEN_HEIGHT / 2);
                g.drawString("DRAW", (SCREEN_WIDTH - metrics.stringWidth("YOU DRAW")) / 2,
                        (SCREEN_HEIGHT / 2) + metrics.getHeight());
        }

    }

    public void gamerShoot() {

        if (gamerBulletX > SCREEN_WIDTH || gamerBulletX < 0 || gamerBulletY < 0 || gamerBulletY > SCREEN_HEIGHT) {
            fire = 'N';
            checkFire = false;
        } else if ((gamerBulletY < Y_DOWN_BARR + UNIT_SIZE && gamerBulletY > Y_UP_BARR - UNIT_SIZE)
                && (gamerBulletX == BARRIER || gamerBulletX == SCREEN_WIDTH - BARRIER)) {
            fire = 'N';
            checkFire = false;
        } else if (gamerBulletX < Y_DOWN_BARR + UNIT_SIZE && gamerBulletX > Y_UP_BARR - UNIT_SIZE
                && gamerBulletY <= Y_BARRIER + UNIT_SIZE && gamerBulletY >= Y_BARRIER - UNIT_SIZE) {
            fire = 'N';
            checkFire = false;
        }
        int vremen;
        switch (bulletDirection) {
            case 'U':
                vremen = gamerBulletY;
                gamerBulletY -= BORDER;
                for (int i = 0; i < BORDER; i++) {
                    vremen--;
                    if (vremen == pcY && gamerBulletX == pcX) {
                        bulletCollision = false;
                    }
                }
                this.repaint();
                break;
            case 'D':
                vremen = gamerBulletY;
                gamerBulletY += BORDER;
                for (int i = 0; i < BORDER; i++) {
                    vremen++;
                    if (vremen == pcY && gamerBulletX == pcX) {
                        bulletCollision = false;
                    }
                }
                this.repaint();
                break;
            case 'R':
                vremen = gamerBulletX;
                gamerBulletX += BORDER;
                for (int i = 0; i < BORDER; i++) {
                    vremen++;
                    if (vremen == pcX && gamerBulletY == pcY) {
                        bulletCollision = false;
                    }
                }
                this.repaint();
                break;
            case 'L':
                vremen = gamerBulletX;
                gamerBulletX -= BORDER;
                for (int i = 0; i < BORDER; i++) {
                    vremen--;
                    if (vremen == pcX && gamerBulletY == pcY) {
                        bulletCollision = false;
                    }
                }
                this.repaint();
                break;
        }
    }

    public void pcShoot() {
        if (pcBulletX > SCREEN_WIDTH || pcBulletX < 0 || pcBulletY < 0 || pcBulletY > SCREEN_HEIGHT) {
            spawnPcBullet();
        } else if ((pcBulletY < Y_DOWN_BARR + UNIT_SIZE && pcBulletY > Y_UP_BARR - UNIT_SIZE)
                && (pcBulletX == BARRIER || pcBulletX == SCREEN_WIDTH - BARRIER)) {
            spawnPcBullet();
        } else if (pcBulletX < Y_DOWN_BARR + UNIT_SIZE && pcBulletX > Y_UP_BARR - UNIT_SIZE
                && pcBulletY <= Y_BARRIER + UNIT_SIZE && pcBulletY >= Y_BARRIER - UNIT_SIZE) {
            spawnPcBullet();
        }
        int vremen;
        switch (pcBulletDirection) {
            case 'U':
                vremen = pcBulletY;
                pcBulletY -= BORDER;
                for (int i = 0; i < BORDER; i++) {
                    vremen--;
                    if (vremen == gamerY && pcBulletX == gamerX) {
                        bulletCollision = false;
                    }
                }
                this.repaint();
                break;
            case 'D':
                vremen = pcBulletY;
                pcBulletY += BORDER;
                for (int i = 0; i < BORDER; i++) {
                    vremen++;
                    if (vremen == gamerY && pcBulletX == gamerX) {
                        bulletCollision = false;
                    }
                }
                this.repaint();
                break;
            case 'R':
                vremen = pcBulletX;
                pcBulletX += BORDER;
                for (int i = 0; i < BORDER; i++) {
                    vremen++;
                    if (vremen == gamerX && pcBulletY == gamerY) {
                        bulletCollision = false;
                    }
                }
                this.repaint();
                break;
            case 'L':
                vremen = pcBulletX;
                pcBulletX -= BORDER;
                for (int i = 0; i < BORDER; i++) {
                    vremen--;
                    if (vremen == gamerX && pcBulletY == gamerY) {
                        bulletCollision = false;
                    }
                }
                this.repaint();
                break;
        }
    }

    public void spawnPcBullet() {
        pcBulletX = pcX;
        pcBulletY = pcY;
        pcBulletDirection = pcDirection;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    public class MyKeyAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_SPACE:
                    fire = 'F';
                    if (!checkFire)
                     spawnBullet();
                    break;
                case KeyEvent.VK_LEFT:
                    if (direction != 'R') {
                        direction = 'L';
                        System.out.println("R");
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if (direction != 'L') {
                        direction = 'R';
                        System.out.println("L");
                    }
                    break;
                case KeyEvent.VK_UP:
                    if (direction != 'D') {
                        direction = 'U';
                        System.out.println("D");
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if (direction != 'U') {
                        direction = 'D';
                        System.out.println("U");
                    }
                    break;
            }
        }
    }
}


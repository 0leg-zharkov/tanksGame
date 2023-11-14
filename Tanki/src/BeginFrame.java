import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BeginFrame extends JFrame implements ActionListener {

    JButton startGame;
    JLabel instructionsGap;
    JLabel instructionsRight;
    JLabel instructionsUp;
    JLabel instructionsLeft;
    JLabel instructionsDown;
    Dimension labelSize = new Dimension(200, 40);


    public void init() {

        this.setTitle("Tanks");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new FlowLayout());
        this.setSize(600, 600);
        this.setVisible(true);
        this.setLocationRelativeTo(null);

        instructionsGap = new JLabel();
        instructionsUp = new JLabel();
        instructionsDown = new JLabel();
        instructionsLeft = new JLabel();
        instructionsRight = new JLabel();

        instructionsGap.setText("Press gap ↔ to shoot");
        instructionsGap.setVerticalAlignment(JLabel.BOTTOM);
        instructionsGap.setHorizontalAlignment(JLabel.CENTER);
        instructionsGap.setPreferredSize(labelSize);

        instructionsUp.setText("Press ⬆ to turn up");
        instructionsUp.setVerticalAlignment(JLabel.BOTTOM);
        instructionsUp.setHorizontalAlignment(JLabel.CENTER);
        instructionsUp.setPreferredSize(labelSize);

        instructionsLeft.setText("Press ⬅ to turn left");
        instructionsLeft.setVerticalAlignment(JLabel.BOTTOM);
        instructionsLeft.setHorizontalAlignment(JLabel.CENTER);
        instructionsLeft.setPreferredSize(labelSize);

        instructionsDown.setText("Press ⬇ to turn down");
        instructionsDown.setVerticalAlignment(JLabel.BOTTOM);
        instructionsDown.setHorizontalAlignment(JLabel.CENTER);
        instructionsDown.setPreferredSize(labelSize);

        instructionsRight.setText("Press ➡ to turn right");
        instructionsRight.setVerticalAlignment(JLabel.BOTTOM);
        instructionsRight.setHorizontalAlignment(JLabel.CENTER);
        instructionsRight.setPreferredSize(labelSize);

        startGame = new JButton("START GAME");
        startGame.addActionListener(this);

        this.add(startGame);
        this.add(instructionsGap);
        this.add(instructionsUp);
        this.add(instructionsDown);
        this.add(instructionsLeft);
        this.add(instructionsRight);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        final GameFrame frame = new GameFrame();
        frame.start();
    }
}




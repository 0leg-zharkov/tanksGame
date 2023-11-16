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
    JTextField name;


    public void init() {

        this.setTitle("Tanks");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new FlowLayout(FlowLayout.LEADING));
        //this.setSize(1000, 1000);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        //this.setResizable(false);

        startGame = new JButton("START GAME");
        startGame.addActionListener(this);
        //startGame.setBounds(10, 10, 150, 25);

        instructionsGap = new JLabel("Press gap ↔ to shoot");
        instructionsUp = new JLabel("Press ⬆ to turn up");
        instructionsDown = new JLabel("Press ⬇ to turn down");
        instructionsLeft = new JLabel("Press ⬅ to turn left");
        instructionsRight = new JLabel("Press ➡ to turn right");

        instructionsGap.setPreferredSize(labelSize);
        instructionsUp.setPreferredSize(labelSize);
        instructionsLeft.setPreferredSize(labelSize);
        instructionsDown.setPreferredSize(labelSize);
        instructionsRight.setPreferredSize(labelSize);

//        instructionsGap.setBounds(0, 300 - 5 * labelSize.height, labelSize.width, labelSize.height);
//        instructionsUp.setBounds(0, 300 - 4 *  labelSize.height, labelSize.width, labelSize.height);
//        instructionsDown.setBounds(0, 300 - 3 * labelSize.height, labelSize.width, labelSize.height);
//        instructionsLeft.setBounds(0, 300 - 2 * labelSize.height, labelSize.width, labelSize.height);
//        instructionsRight.setBounds(10, 150 + labelSize.height, labelSize.width, labelSize.height);

        name = new JTextField(15);
        name.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(BeginFrame.this, "Ваше имя: " + name.getText());
            }
        });
        //name.setBounds(300, 300, 50, 25);

        this.add(startGame);
        this.add(instructionsGap);
        this.add(instructionsUp);
        this.add(instructionsDown);
        this.add(instructionsLeft);
        this.add(instructionsRight);
        this.add(name);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        final GameFrame frame = new GameFrame();
        frame.start(name.getText());
    }
}




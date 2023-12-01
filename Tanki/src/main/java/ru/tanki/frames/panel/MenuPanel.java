package ru.tanki.frames.panel;

import ru.tanki.frames.GameFrame;
import ru.tanki.persistence.GamerPersistance;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static ru.tanki.nums.AllNums.*;

public class MenuPanel extends JPanel {

    private JButton startGame = new JButton("START GAME");
    private JButton knowTime = new JButton("Show gamers time");
    private final String NAME_INPUT = "Введите имя";
    private JLabel name_input = new JLabel(NAME_INPUT);
    private JTextField name = new JTextField(15);
    private JLabel instructionsGap = new JLabel("Press gap ↔ to shoot");
    private JLabel instructionsUp = new JLabel("Press ⬆ to turn up");
    private JLabel instructionsDown = new JLabel("Press ⬇ to turn down");
    private JLabel instructionsLeft = new JLabel("Press ⬅ to turn left");
    private JLabel instructionsRight = new JLabel("Press ➡ to turn right");
    private JLabel instructions = new JLabel("INSTRUCTIONS");

    public MenuPanel(GamerPersistance gamerPersistance) {
        this.setVisible(true);

        this.setBounds(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);

        startGame.setBounds(X_SET_LOCATION, 0, CMPTS_MENU_WIDTH, CMPTS_MENU_HEIGHT);
        startGame.setVisible(false);
        startGame.addActionListener(e -> {
            final GameFrame frame = new GameFrame();
            frame.start(name.getText(), gamerPersistance);
        });

        name_input.setBounds(X_SET_LOCATION, CMPTS_MENU_HEIGHT, CMPTS_MENU_WIDTH, CMPTS_MENU_HEIGHT);

        name.setBounds(X_SET_LOCATION, CMPTS_MENU_HEIGHT * 2, CMPTS_MENU_WIDTH, CMPTS_MENU_HEIGHT);
        name.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(MenuPanel.this, "Ваше имя: " + name.getText());
                System.out.println("Gamer name is " + name.getText());
                startGame.setVisible(true);
            }
        });

        knowTime.setBounds(X_SET_LOCATION, CMPTS_MENU_HEIGHT * 3, CMPTS_MENU_WIDTH, CMPTS_MENU_HEIGHT);

        instructions.setBounds(X_SET_LOCATION,CMPTS_MENU_HEIGHT * 4, CMPTS_MENU_WIDTH, CMPTS_MENU_HEIGHT);
        instructionsGap.setBounds(  0,     CMPTS_MENU_HEIGHT * 5, CMPTS_MENU_WIDTH, CMPTS_MENU_HEIGHT);
        instructionsUp.setBounds(   0,     CMPTS_MENU_HEIGHT * 6, CMPTS_MENU_WIDTH, CMPTS_MENU_HEIGHT);
        instructionsDown.setBounds( 0,     CMPTS_MENU_HEIGHT * 7, CMPTS_MENU_WIDTH, CMPTS_MENU_HEIGHT);
        instructionsLeft.setBounds( 0,     CMPTS_MENU_HEIGHT * 8, CMPTS_MENU_WIDTH, CMPTS_MENU_HEIGHT);
        instructionsRight.setBounds(0,     CMPTS_MENU_HEIGHT * 9, CMPTS_MENU_WIDTH, CMPTS_MENU_HEIGHT);

        this.setLayout(null);
        this.add(startGame);
        this.add(name_input);
        this.add(name);
        this.add(knowTime);
        this.add(instructions);
        this.add(instructionsGap);
        this.add(instructionsUp);
        this.add(instructionsDown);
        this.add(instructionsLeft);
        this.add(instructionsRight);

        this.setVisible(true);
        this.repaint();
    }

    public JButton getKnowTime() {
        return knowTime;
    }
}

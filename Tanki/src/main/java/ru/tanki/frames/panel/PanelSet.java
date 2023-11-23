package ru.tanki.frames.panel;

import ru.tanki.persistence.GamerPersistance;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelSet extends JPanel implements ActionListener {

    private final String NEXT = "Show gamers time";
    private final String PREVIOUS = "PREVIOUS";
    private GamerPersistance gamerPersistance;

    public PanelSet(MenuPanel menu, RecordsPanel records, GamerPersistance gamerPersistance) {

        this.gamerPersistance = gamerPersistance;
        this.setLayout(new CardLayout());
        this.add(menu, NEXT);
        this.add(records, PREVIOUS);
        this.setVisible(true);
        this.repaint();

        menu.getKnowTime().addActionListener(this::actionPerformed);
        records.getGoBack().addActionListener(this::actionPerformed);

        this.setVisible(true);
        this.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        CardLayout cl = (CardLayout) (this.getLayout());
        String cmd = e.getActionCommand();
        if (cmd.equals(NEXT)) {
            cl.last(this);
            System.out.println("pressed show gamers");
            gamerPersistance.updateTable();
            this.repaint();
        } else {
            cl.first(this);
            System.out.println(cmd);
            this.repaint();
        }
    }
}
package ru.tanki.frames;

import ru.tanki.frames.panel.MenuPanel;
import ru.tanki.frames.panel.PanelSet;
import ru.tanki.frames.panel.RecordsPanel;
import ru.tanki.persistence.GamerPersistance;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import static ru.tanki.nums.AllNums.*;


public class BeginFrame extends JFrame {

    private GamerPersistance gamerPersistance = new GamerPersistance();

    public void init() {

        this.setTitle("Tanks");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.repaint();

        MenuPanel menu = new MenuPanel(gamerPersistance);
        RecordsPanel records = new RecordsPanel(gamerPersistance);
        PanelSet allPanels = new PanelSet(menu, records, gamerPersistance);

        this.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                gamerPersistance.deleteAllGamers();
            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });

        this.add(allPanels);
        this.setVisible(true);
        this.repaint();
    }
}




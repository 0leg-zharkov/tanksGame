package ru.tanki.frames.panel;

import ru.tanki.domain.Gamer;
import ru.tanki.persistence.GamerPersistance;

import javax.swing.*;
import java.util.List;
import java.util.Vector;

import static ru.tanki.nums.AllNums.*;

public class RecordsPanel extends JPanel {

    private JButton goBack = new JButton("go back");
    private List<Gamer> gamersList;
    private Vector<String> records = new Vector<>();
    private JList<String> recordList;
    private JScrollPane scroll;

    public RecordsPanel(GamerPersistance gamerPersistance) {

        this.setVisible(true);

        gamersList = gamerPersistance.getAll();

        sorting();

        recordList = new JList<>(records);

        scroll = new JScrollPane(recordList);

        goBack.setBounds(X_SET_LOCATION, 0, CMPTS_MENU_WIDTH, CMPTS_MENU_HEIGHT);

        scroll.setBounds(X_SET_LOCATION, CMPTS_MENU_HEIGHT, CMPTS_MENU_WIDTH, CMPTS_MENU_HEIGHT);

        this.add(scroll);
        this.add(goBack);

        this.setVisible(true);
        this.repaint();
    }

    private void sorting() {
        if (gamersList.size() == 0) return;
        for (Gamer gamer : gamersList) {
            records.add(gamer.getNameId() + " - " + gamer.getTime());
        }
    }

    public JButton getGoBack() {
        return goBack;
    }
}

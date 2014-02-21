package util;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @author Daniel Michalski
 */

public class potwierdzenieZamknieciaOkna extends WindowAdapter {
    public void windowClosing(WindowEvent e) {
        int answer = JOptionPane.showConfirmDialog(null, "Czy na pewno chcesz zamknąć program", "Informacja", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);

        if (answer == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }
}
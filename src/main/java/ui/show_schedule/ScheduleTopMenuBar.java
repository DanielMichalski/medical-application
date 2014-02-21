package ui.show_schedule;

import ui.components.calendar.ChangeCalendarColorListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ScheduleTopMenuBar extends JMenuBar implements ActionListener {
    public static final int ID_ITEM_CLOSE_WINDOW = 1;
    public static final int ID_ITEM_CHANGE_COLOR = 2;

    private JDialog frame;
    private Dialog chooserColorDialog;

    public ScheduleTopMenuBar(JDialog frame, ChangeCalendarColorListener changeCalendarColorListener) {
        this.frame = frame;
        chooserColorDialog = createChooserColorDialog(changeCalendarColorListener);

        add(createFileMenu());
        add(createOptionsMenu());
    }

    private JMenu createFileMenu() {
        JMenuItem zamknijItem = new JMenuItem("Zamknij");
        zamknijItem.addActionListener(this);
        zamknijItem.setMnemonic(ID_ITEM_CLOSE_WINDOW);

        JMenu fileMenu = new JMenu("Plik");
        fileMenu.add(zamknijItem);
        return fileMenu;
    }

    private JMenu createOptionsMenu() {
        JMenuItem changeColorItem = new JMenuItem("Zmien kolor tła kalendarza");
        changeColorItem.addActionListener(this);
        changeColorItem.setMnemonic(ID_ITEM_CHANGE_COLOR);

        JMenu optionsMenu = new JMenu("Opcje");
        optionsMenu.add(changeColorItem);
        return optionsMenu;
    }

    private Dialog createChooserColorDialog(ChangeCalendarColorListener changeColorListener) {
        Dialog chooserColorDialog = new JDialog(frame, "Zmieniacz koloru", false);
        chooserColorDialog.add(new ChooserColorDialog(changeColorListener));
        chooserColorDialog.pack();
        return chooserColorDialog;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        JMenuItem menuItem = (JMenuItem) event.getSource();
        switch (menuItem.getMnemonic()) {
            case ScheduleTopMenuBar.ID_ITEM_CHANGE_COLOR:
                chooserColorDialog.setVisible(true);
                break;
            case ScheduleTopMenuBar.ID_ITEM_CLOSE_WINDOW:
                frame.dispose();
                break;
        }
    }
}

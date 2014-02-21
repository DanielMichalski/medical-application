package ui.show_schedule;

import ui.components.calendar.ChangeCalendarColorListener;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class ChooserColorDialog extends JColorChooser implements ChangeListener {

    private ChangeCalendarColorListener changeColorListener;

    public ChooserColorDialog(ChangeCalendarColorListener changeColorListener) {
        this.changeColorListener = changeColorListener;
        setColor(getBackground());
        getSelectionModel().addChangeListener(this);
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        Color selectedColor = getColor();
        changeColorListener.changeColor(selectedColor);
    }
}

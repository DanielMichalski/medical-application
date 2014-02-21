package ui.components.calendar;

import ui.components.calendar.util.CalendarUtil;
import util.Const;

import javax.swing.*;
import java.awt.*;

public class WeekdaysHeaderCalendarPanel extends JPanel implements ChangeCalendarColorListener {

    public WeekdaysHeaderCalendarPanel(Color background) {
        setupContent(background);
    }

    private void setupContent(Color backgroundComponents) {
        setBackground(Const.Colors.BACKGROUND_CALENDAR);
        setLayout(new GridLayout(1, 7));
        for (String shortWeekdayName : CalendarUtil.getShortWeekdaysName()) {
            JLabel lblWeekday = new JLabel(shortWeekdayName, JLabel.CENTER);
            lblWeekday.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
            add(lblWeekday);
        }
        setMaximumSize(new Dimension(Integer.MAX_VALUE, 0));
        setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, backgroundComponents));

        changeColor(backgroundComponents);
    }

    @Override
    public void changeColor(Color color) {
        setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, color));
    }
}

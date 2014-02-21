package ui.components.calendar;

import ui.components.calendar.util.CalendarUtil;

import javax.swing.*;
import java.awt.*;


public class TodayCalendarPanel extends JPanel implements ChangeCalendarColorListener {

    private JLabel lblToday;

    public TodayCalendarPanel(Color background) {
        initComponents();
        setupContent(background);
    }

    private void initComponents() {
        lblToday = new JLabel("Dzisiaj: " + CalendarUtil.getTodayWithFormat());
        lblToday.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 0));
    }

    private void setupContent(Color backgroundComponents) {
        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        add(lblToday);
        add(Box.createHorizontalGlue());

        changeColor(backgroundComponents);
    }

    @Override
    public void changeColor(Color color) {
        setBackground(color);
    }
}

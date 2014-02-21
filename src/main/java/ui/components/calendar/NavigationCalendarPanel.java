package ui.components.calendar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class NavigationCalendarPanel extends JPanel implements ChangeCalendarColorListener {
    public static final int ID_BUTTON_NEXT_MONTH = 1;
    public static final int ID_BUTTON_PREVIOUS_MONTH = 2;

    private JButton btnPreviousMonth;
    private JLabel lblDisplayMonthYear;
    private JButton btnNextMonth;

    public NavigationCalendarPanel(Color background, ActionListener buttonClickListener) {
        initComponents(buttonClickListener);
        setupContent(background);
    }

    private void initComponents(ActionListener actionListener) {
        lblDisplayMonthYear = new JLabel();

        btnPreviousMonth = new JButton("<");
        btnPreviousMonth.setMnemonic(ID_BUTTON_PREVIOUS_MONTH);
        btnPreviousMonth.addActionListener(actionListener);

        btnNextMonth = new JButton(">");
        btnNextMonth.setMnemonic(ID_BUTTON_NEXT_MONTH);
        btnNextMonth.addActionListener(actionListener);
    }

    private void setupContent(Color backgroundComponents) {
        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        add(btnPreviousMonth);
        add(Box.createHorizontalGlue());
        add(lblDisplayMonthYear);
        add(Box.createHorizontalGlue());
        add(btnNextMonth);

        changeColor(backgroundComponents);
    }

    public void actualizeDisplayMonthLbl(String displayMonthYear) {
        lblDisplayMonthYear.setText("");
        lblDisplayMonthYear.setText(displayMonthYear);
    }

    @Override
    public void changeColor(Color color) {
        setBackground(color);
    }
}

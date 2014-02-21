package ui.show_schedule.doctor;

import ui.components.FillTitleBorder;
import ui.components.calendar.CalendarPanel;
import ui.show_schedule.HeaderPanel;
import ui.show_schedule.ReservationsTablePanel;
import util.Const;

import javax.swing.*;
import java.awt.*;

public class SelectorSchedulePanel extends JPanel {

    private CalendarPanel calendarPanel;
    private ReservationsTablePanel reservationsTablePanel;

    private HeaderPanel headerChooseHour;
    private HeaderPanel headerChooseDate;

    public SelectorSchedulePanel(SchedulePresenter schedulePresenter) {
        super(new GridBagLayout());
        setBorder(new FillTitleBorder("Selektor wizyt"));
        setBackground(Const.Colors.BACKGROUND_PANEL);
        setPreferredSize(new Dimension(300, 0));

        initPanels(schedulePresenter);
        createContent();
    }

    private void initPanels(SchedulePresenter schedulePresenter) {
        headerChooseHour = new HeaderPanel("Godzina wizyty", "Nie wybrano godziny");
        reservationsTablePanel = new ReservationsTablePanel(headerChooseHour, false, schedulePresenter);

        headerChooseDate = new HeaderPanel("Termin wizyty", "Nie wybrano terminu wizyty");
        calendarPanel = new CalendarPanel(headerChooseDate, schedulePresenter, schedulePresenter);
    }

    private void createContent() {
        GridBagConstraints gridConstraints = new GridBagConstraints();
        gridConstraints.fill = GridBagConstraints.BOTH;
        Insets betweenViews = new Insets(0, 0, 0, 0);
        Insets betweenLabels = new Insets(15, 5, 0, 5);

        gridConstraints.insets = new Insets(0, 5, 0, 5);
        settleGridConstrains(gridConstraints, 0, 2, 1.0, 0.0);
        add(headerChooseDate, gridConstraints);
        gridConstraints.insets = betweenViews;
        settleGridConstrains(gridConstraints, 0, 3, 1.0, 0.0);
        add(calendarPanel, gridConstraints);

        gridConstraints.insets = betweenLabels;
        settleGridConstrains(gridConstraints, 0, 4, 1.0, 0.0);
        add(headerChooseHour, gridConstraints);
        gridConstraints.insets = betweenViews;
        settleGridConstrains(gridConstraints, 0, 5, 1.0, 1.0);
        add(reservationsTablePanel, gridConstraints);
    }

    private void settleGridConstrains(GridBagConstraints gridConstraints, int x, int y, double weightX, double weightY) {
        gridConstraints.gridx = x;
        gridConstraints.gridy = y;
        gridConstraints.weightx = weightX;
        gridConstraints.weighty = weightY;
    }

    public CalendarPanel getCalendarPanel() {
        return calendarPanel;
    }

    public ReservationsTablePanel getReservationsTablePanel() {
        return reservationsTablePanel;
    }
}
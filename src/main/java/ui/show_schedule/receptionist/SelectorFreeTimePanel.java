package ui.show_schedule.receptionist;

import ui.components.FillTitleBorder;
import ui.components.calendar.CalendarPanel;
import ui.show_schedule.HeaderPanel;
import ui.show_schedule.ReservationsTablePanel;
import util.Const;

import javax.swing.*;
import java.awt.*;

public class SelectorFreeTimePanel extends JPanel {
    private GridBagConstraints gridConstraints;

    private DoctorChooserPanel doctorChooserPanel;
    private CalendarPanel calendarPanel;
    private ReservationsTablePanel reservationsTablePanel;

    public SelectorFreeTimePanel(VisitSelectorPresenter visitSelectorPresenter) {
        setupPanel();

        initPanels(visitSelectorPresenter);
        createContent();
    }

    private void setupPanel() {
        setLayout(new GridBagLayout());
        setBackground(Const.Colors.BACKGROUND_PANEL);
        setBorder(new FillTitleBorder("Selektor wizyty"));
        setPreferredSize(new Dimension(300, 0));
    }

    HeaderPanel headerChooseHour;
    HeaderPanel headerChooseDoctor;
    HeaderPanel headerChooseDate;

    private void initPanels(VisitSelectorPresenter visitSelectorPresenter) {
        headerChooseDoctor = new HeaderPanel("Lekarz", "Nie wybrano lekarza");
        doctorChooserPanel = new DoctorChooserPanel(headerChooseDoctor, visitSelectorPresenter.getDoctorsFromDB(), visitSelectorPresenter);

        headerChooseHour = new HeaderPanel("Godzina wizyty", "Nie wybrano godziny");
        reservationsTablePanel = new ReservationsTablePanel(headerChooseHour, true, visitSelectorPresenter);

        headerChooseDate = new HeaderPanel("Termin wizyty", "Nie wybrano terminu wizyty");
        calendarPanel = new CalendarPanel(headerChooseDate, visitSelectorPresenter, visitSelectorPresenter);
    }

    private void createContent() {

        gridConstraints = new GridBagConstraints();
        gridConstraints.fill = GridBagConstraints.BOTH;
        Insets betweenViews = new Insets(0, 5, 0, 5);
        Insets betweenLabels = new Insets(15, 5, 0, 5);

        settleGridConstrains(0, 0, 1.0, 0.0, betweenViews);
        add(headerChooseDoctor, gridConstraints);
        settleGridConstrains(0, 1, 1.0, 0.0, betweenViews);
        add(doctorChooserPanel, gridConstraints);

        settleGridConstrains(0, 2, 1.0, 0.0, betweenLabels);
        add(headerChooseDate, gridConstraints);
        settleGridConstrains(0, 3, 1.0, 0.0, betweenViews);
        add(calendarPanel, gridConstraints);

        settleGridConstrains(0, 4, 1.0, 0.0, betweenLabels);
        add(headerChooseHour, gridConstraints);
        settleGridConstrains(0, 5, 1.0, 1.0, new Insets(0, 5, 5, 5));
        add(reservationsTablePanel, gridConstraints);
    }

    private void settleGridConstrains(int x, int y, double weightX, double weightY, Insets insets) {
        gridConstraints.gridx = x;
        gridConstraints.gridy = y;
        gridConstraints.weightx = weightX;
        gridConstraints.weighty = weightY;
        gridConstraints.insets = insets;
    }

    public CalendarPanel getCalendarPanel() {
        return calendarPanel;
    }

    public DoctorChooserPanel getDoctorChooserPanel() {
        return doctorChooserPanel;
    }

    public ReservationsTablePanel getReservationsTablePanel() {
        return reservationsTablePanel;
    }
}
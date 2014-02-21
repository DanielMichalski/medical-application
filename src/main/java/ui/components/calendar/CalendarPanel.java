package ui.components.calendar;

import ui.components.calendar.cell.*;
import ui.show_schedule.HeaderPanel;
import util.Const;
import util.validate.Validable;
import util.validate.ValidationException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;

public class CalendarPanel extends JPanel implements ClickCalendarCellListener, Validable {
    private SetterReservationsInCells setterReservationsInCells;
    private ClickCalendarCellListener clickCellListener;
    private ButtonActionListener buttonActionListener;

    private NavigationCalendarPanel navigationCalendarPanel;
    private WeekdaysHeaderCalendarPanel weekdaysHeaderCalendarPanel;
    private DaysInGridCalendarPanel gridWithDaysCalendarPanel;
    private TodayCalendarPanel todayCalendarPanel;

    private HeaderPanel header;
    private Color backgroundComponents;
    private CalendarCellFactory calendarCellFactory;

    public CalendarPanel(HeaderPanel header, SetterReservationsInCells setterReservationsInCells, ClickCalendarCellListener clickCellListener) {
        this.header = header;
        this.setterReservationsInCells = setterReservationsInCells;
        this.clickCellListener = clickCellListener;
        buttonActionListener = new ButtonActionListener();
        backgroundComponents = Const.Colors.BACKGROUND_CALENDAR_COMPONENT;
        calendarCellFactory = new CalendarCellFactory(backgroundComponents, this);

        initPanels();
        setupContent();
    }

    private void initPanels() {
        navigationCalendarPanel = new NavigationCalendarPanel(backgroundComponents, buttonActionListener);
        weekdaysHeaderCalendarPanel = new WeekdaysHeaderCalendarPanel(backgroundComponents);
        gridWithDaysCalendarPanel = new DaysInGridCalendarPanel();
        todayCalendarPanel = new TodayCalendarPanel(backgroundComponents);
    }

    private void setupContent() {
        setBackground(Const.Colors.backgroundColor);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(navigationCalendarPanel);
        add(weekdaysHeaderCalendarPanel);
        add(gridWithDaysCalendarPanel);
        add(todayCalendarPanel);
    }

    public void refreshCalendar() {
        List<CalendarCell> calendarCells = calendarCellFactory.getCalendarCellsForDisplayMonth();
        refreshCalendarWith(calendarCells);

        CalendarCell selectedCell = gridWithDaysCalendarPanel.getSelectedCell();
        if (selectedCell != null) {
            onClickCell(selectedCell);
        }
    }

    private void refreshCalendarWith(List<CalendarCell> calendarCells) {
        navigationCalendarPanel.actualizeDisplayMonthLbl(calendarCellFactory.getDisplayMonthAndYear());

        Date firstDayInPage = calendarCellFactory.getFirstDateInPage();
        Date lastDayInPage = calendarCellFactory.getLastDateInPage();
        calendarCells = setterReservationsInCells.setReservationsIn(calendarCells, firstDayInPage, lastDayInPage);
        gridWithDaysCalendarPanel.actualizeDaysInGrid(calendarCells);
    }

    @Override
    public void onClickCell(CalendarCell cell) {
        header.hideError();
        if (cell.getCellType() == CellType.FROM_ANOTHER_MONTH) {
            List<CalendarCell> calendarCellsForAnotherMonth = calendarCellFactory.getCalendarCellsForDay(cell.getDay());
            refreshCalendarWith(calendarCellsForAnotherMonth);
        }
        gridWithDaysCalendarPanel.selectCellInGrid(cell);
        clickCellListener.onClickCell(cell);
    }

    @Override
    public void check() throws ValidationException {
        if (gridWithDaysCalendarPanel.getSelectedCell() == null) {
            header.showError();
            throw new ValidationException();
        }
    }

    class ButtonActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            JButton btn = (JButton) event.getSource();
            switch (btn.getMnemonic()) {
                case NavigationCalendarPanel.ID_BUTTON_PREVIOUS_MONTH:
                    refreshCalendarWith(calendarCellFactory.getCalendarCellsForPreviousMoth());
                    break;
                case NavigationCalendarPanel.ID_BUTTON_NEXT_MONTH:
                    refreshCalendarWith(calendarCellFactory.getCalendarCellsForNextMoth());
                    break;
            }
        }
    }

    public void changeComponentsColor(Color color) {
        navigationCalendarPanel.changeColor(color);
        weekdaysHeaderCalendarPanel.changeColor(color);
        gridWithDaysCalendarPanel.changeColor(color);
        todayCalendarPanel.changeColor(color);

        calendarCellFactory.setBackgroundCell(color);
    }
}
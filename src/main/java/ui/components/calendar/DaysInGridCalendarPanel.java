package ui.components.calendar;

import ui.components.calendar.cell.CalendarCell;
import util.Const;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class DaysInGridCalendarPanel extends JPanel implements ChangeCalendarColorListener {

    private List<CalendarCell> displayCalendarCells;
    private CalendarCell selectedCell;

    public DaysInGridCalendarPanel() {
        setLayout(new GridLayout(6, 7));
        setBackground(Const.Colors.BACKGROUND_CALENDAR);
    }

    public void actualizeDaysInGrid(List<CalendarCell> calendarCells) {
        removeAll();
        displayCalendarCells = calendarCells;
        for (CalendarCell cell : displayCalendarCells) {
            cell.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            add(cell);
        }
        selectCellInGrid(selectedCell);
    }

    public void selectCellInGrid(CalendarCell selectedCell) {
        this.selectedCell = selectedCell;
        for (CalendarCell cell : displayCalendarCells) {
            boolean isSelected = cell.equals(selectedCell);
            cell.setSelected(isSelected);

            if (isSelected) {
                this.selectedCell = cell;
            }
        }
    }

    @Override
    public void changeColor(Color color) {
        for (CalendarCell cell : displayCalendarCells) {
            cell.setColor(color, color);
        }
    }

    public CalendarCell getSelectedCell() {
        return selectedCell;
    }
}

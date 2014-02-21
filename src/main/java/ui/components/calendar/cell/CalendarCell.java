package ui.components.calendar.cell;

import patient.Reservation;
import util.Const;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CalendarCell extends JLabel implements MouseListener {
    private ClickCalendarCellListener clickCellListener;

    private boolean noFreeReservation;
    private boolean selected;
    private Color noFreeReservationBackground;
    private Color defaultBackground;
    private Color enteredBackground;
    private Color selectedBackground;

    private Date date;
    private CellType cellType;
    private List<Reservation> reservations;

    public CalendarCell(Date date, ClickCalendarCellListener clickCellListener) {
        super("" + date.getDate(), JLabel.CENTER);
        this.clickCellListener = clickCellListener;
        this.cellType = CellType.FROM_CURRENT_MONTH;
        this.date = date;
        this.reservations = new ArrayList<Reservation>();
        this.noFreeReservation = false;
        setOpaque(true);
        addMouseListener(this);

        noFreeReservationBackground = Color.PINK;
        setColor(Const.Colors.backgroundCalendarItems, Const.Colors.backgroundCalendarItems);
        defaultBackground = getBackground();
    }

    public void setCellType(Date today, int displayMonth) {
        if (date.equals(today)) {
            cellType = CellType.TODAY;
        } else if (date.getMonth() == displayMonth) {
            cellType = CellType.FROM_CURRENT_MONTH;
        } else {
            cellType = CellType.FROM_ANOTHER_MONTH;
        }
        setEnabled(cellType.getEnable());
    }

    public CellType getCellType() {
        return cellType;
    }

    public Date getDay() {
        return date;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
        noFreeReservation = noFreeReservation();
    }

    private boolean noFreeReservation() {
        for (Reservation reservation : reservations) {
            if (!reservation.isPersisted()) {
                return false;
            }
        }
        return true;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
        if (selected) {
            setBackground(selectedBackground);
        } else if (noFreeReservation) {
            setBackground(noFreeReservationBackground);
        } else {
            setBackground(defaultBackground);
        }
    }

    public void setColor(Color enteredBackground, Color selectedBackground) {
        this.enteredBackground = enteredBackground;
        this.selectedBackground = selectedBackground;
        setSelected(selected);
        repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        clickCellListener.onClickCell(this);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        setBackground(enteredBackground);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (selected) {
            setBackground(selectedBackground);
        } else if (noFreeReservation) {
            setBackground(noFreeReservationBackground);
        } else {
            setBackground(defaultBackground);
        }
    }

    //przeciarzony equals aby porownywal obiekty CalendarCell tylko po dacie
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CalendarCell that = (CalendarCell) o;

        if (date != null ? !date.equals(that.date) : that.date != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return date != null ? date.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "CalendarCell{" +
                "date=" + date +
                ", cellType=" + cellType +
                '}';
    }
}
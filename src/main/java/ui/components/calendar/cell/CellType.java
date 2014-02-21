package ui.components.calendar.cell;

import java.awt.*;

public enum CellType {
    TODAY {
        @Override
        public Color getColorText() {
            return Color.BLUE;
        }

        @Override
        public boolean getEnable() {
            return true;
        }
    },
    FROM_CURRENT_MONTH {
        @Override
        public Color getColorText() {
            return Color.BLACK;
        }

        @Override
        public boolean getEnable() {
            return true;
        }
    }, FROM_ANOTHER_MONTH {
        @Override
        public Color getColorText() {
            return Color.GRAY;
        }

        @Override
        public boolean getEnable() {
            return false;
        }
    };

    abstract public Color getColorText();

    abstract public boolean getEnable();

}


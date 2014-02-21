package util;

import java.awt.*;

public class GridConstrainsBuilder extends GridBagConstraints {

    public GridConstrainsBuilder() {
        super();
        fill = GridBagConstraints.BOTH;
    }

    public void setPosition(int row, int col) {
        gridx = row;
        gridy = col;
    }

    public void setMargin(int left, int right, int top, int bottom) {
        insets = new Insets(top, left, bottom, right);
    }

    public void setWeights(double width, double height) {
        weightx = width;
        weighty = height;
    }

}

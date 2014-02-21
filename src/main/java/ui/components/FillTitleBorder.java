package ui.components;

import util.Const;

import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class FillTitleBorder extends TitledBorder {

    private final int DIMENSION_ROUNDING = 10;
    private int lineBorderX;
    private int lineBorderY;
    private int lineBorderWidth;
    private int lineBorderHeight;

    public FillTitleBorder(String title) {
        super(title);
        setTitleFont(Const.Fonts.HEADER);
        setTitleColor(Const.Colors.HEADER_TEXT);
        LineBorder lineBorder = createLinearBorder(Const.Colors.BORDER_LINE);
        setBorder(lineBorder);
    }

    private LineBorder createLinearBorder(Color color) {
        LineBorder roundedLineBorder = new LineBorder(color, 1) {
            @Override
            public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
                width -= 2;
                height -= 2;
                lineBorderX = x;
                lineBorderY = y;
                lineBorderWidth = width;
                lineBorderHeight = height;

                Graphics2D graphics2D = (Graphics2D) g;
                graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                graphics2D.setColor(Const.Colors.BORDER_LINE);
                graphics2D.drawRoundRect(x, y, width, height, DIMENSION_ROUNDING, DIMENSION_ROUNDING);
            }
        };
        return roundedLineBorder;
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.setColor(Const.Colors.FILL_BORDER);
        graphics2D.fillRoundRect(lineBorderX, lineBorderY, lineBorderWidth, lineBorderHeight, DIMENSION_ROUNDING, DIMENSION_ROUNDING);
        super.paintBorder(c, g, x, y, width, height);
    }
}

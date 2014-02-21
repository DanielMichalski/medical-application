package util;

import javax.swing.*;
import java.awt.*;

/**
 * @author Daniel Michalski
 */

public class Utils {
    /**
     * Metoda centrująca okno
     */
    public static void centreOnScreen(JFrame frame, int DEFAULT_WIDTH, int DEFAULT_HEIGHT) {
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        frame.setLocation((screenWidth - DEFAULT_WIDTH) / 2, (screenHeight - DEFAULT_HEIGHT) / 2);
    }

    public static void setApplicationIcon(Window owner) {
        Toolkit toolkit = owner.getToolkit();

        Image IconImage = toolkit.createImage(new Hash().getClass().getResource("/images/ikona.gif"));
        owner.setIconImage(IconImage);
    }

    public static void setWindowsLookAndFeel() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                    null,
                    "Wystąpił błąd podczas ładowania wyglądu okna: " + e,
                    "Uwaga",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}

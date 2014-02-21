package ui.components;

import javax.swing.*;

public class ErrorLabel extends JLabel {

    public ErrorLabel(String errorMsg) {
        ToolTipManager.sharedInstance().setInitialDelay(10);
        setToolTipText(errorMsg);
        setText(errorMsg);
        setIcon(new ImageIcon(getClass().getResource("/images/ic_error.gif")));
    }

}


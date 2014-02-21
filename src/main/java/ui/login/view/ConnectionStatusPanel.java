package ui.login.view;

import database.DBConnection;
import util.Const;

import javax.swing.*;
import java.awt.*;

public class ConnectionStatusPanel extends JPanel {

    private JLabel lblConnectedWithServer;

    public ConnectionStatusPanel() {
        super(new BorderLayout());
        setBackground(Const.Colors.BACKGROUND_PANEL);

        init();
        add(lblConnectedWithServer, BorderLayout.WEST);
    }

    private void init() {
        if (DBConnection.isConnectedWithDB())
            lblConnectedWithServer = new JLabel(Const.Labels.ONLINELBL);
        else
            lblConnectedWithServer = new JLabel(Const.Labels.OFFLINELBL);
        lblConnectedWithServer.setFont(Const.Fonts.FOOTER);
    }
}
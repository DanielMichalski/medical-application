package ui.main_frame;

import database.DBConnection;
import ui.login.view.LoginFrame;
import util.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;

/**
 * @author Daniel Michalski
 */

public class MainFrame extends JFrame {
    private final static Logger LOGGER = MyProgramLogger.getMyLogger();

    public static final int DEFAULT_WIDTH = 800;
    public static final int DEFAULT_HEIGHT = 490;

    private String workerLogin;
    private boolean isConnectedToDB;
    private String accountTypeId;

    // Tworzenie ramki
    public MainFrame(Worker worker) {

        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        setLocationRelativeTo(null);
        setTitle("Przychodnia lekarska - menu główne");
        setResizable(false);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        Utils.setApplicationIcon(this);

        addWindowListener(new potwierdzenieZamknieciaOkna());

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException |
                InstantiationException |
                IllegalAccessException |
                UnsupportedLookAndFeelException e)
        {
            JOptionPane.showMessageDialog(this,
                    "Wystąpił błąd podczas ładowania wyglądu okna: " + e,
                    "Uwaga",
                    JOptionPane.ERROR_MESSAGE);
        }

        workerLogin = worker.getLogin();
        isConnectedToDB = DBConnection.isConnectedWithDB();
        accountTypeId = worker.getAccountType().toString();

        initComponents(worker);
        setVisible(true);
        LOGGER.info("Pokazanie głównego okna");
    }

    // Inicjalizacja komponentów
    public void initComponents(Worker worker) {

        // tworzenie paska menu
        JMenuBar menuBar = new JMenuBar();
        JMenu plikMenu = new JMenu("Plik");
        JMenuItem wylogujItem = new JMenuItem("Wyloguj");
        wylogujItem.addActionListener(new wylogujSluchacz());

        JMenuItem zamknijItem = new JMenuItem("Zamknij");
        zamknijItem.addActionListener(new zamknijSluchacz());
        plikMenu.add(wylogujItem);
        plikMenu.add(zamknijItem);
        menuBar.add(plikMenu);

        JMenu pomocMenu = new JMenu("Pomoc");
        JMenuItem oProgramie = new JMenuItem("O programie");
        oProgramie.addActionListener(new oProgramieSluchacz());
        pomocMenu.add(oProgramie);
        menuBar.add(pomocMenu);
        setJMenuBar(menuBar);

        // Tworzenie glownego panelu
        PanelGlowny panelGlowny = new PanelGlowny(accountTypeId, worker);
        add(panelGlowny);


        // Dodanie status baru do ramki
        JLabel uzytkownikLabel = new JLabel("Użytkownik: " + workerLogin + "  | ");
        JLabel polaczenieLabel = new JLabel();
        if (isConnectedToDB) {
            polaczenieLabel.setText("Połączenie: " + Const.Labels.ONLINELBL);
        } else {
            polaczenieLabel.setText("Połączenie: " + Const.Labels.OFFLINELBL);
        }

        JLabel typUzytkownikaLabel = new JLabel();
        switch (accountTypeId) {
            case Const.AccountType.ADMIN:
                typUzytkownikaLabel.setText("  |  Rodzaj konta: Administrator");
                break;
            case Const.AccountType.DOCTOR:
                typUzytkownikaLabel.setText("  |  Rodzaj konta: Lekarz");
                break;
            case Const.AccountType.RECEPTIONIST:
                typUzytkownikaLabel.setText("  |  Rodzaj konta: Recepcjonista");
                break;
        }


        JPanel statusBar = new JPanel();
        statusBar.setLayout(new FlowLayout(FlowLayout.LEFT));
        statusBar.setBackground(Const.Colors.FILL_BORDER);
        statusBar.add(uzytkownikLabel);
        statusBar.add(polaczenieLabel);
        statusBar.add(typUzytkownikaLabel);

        add(statusBar, BorderLayout.SOUTH);
    }

    class wylogujSluchacz implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();
            LOGGER.info("Właśnie wylogowano użytkownika");
            LoginFrame loginFrame = new LoginFrame();
            loginFrame.setVisible(true);
        }
    }

    private class oProgramieSluchacz implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            OProgramieDialog dialog = new OProgramieDialog();
            dialog.setVisible(true);
        }
    }
}

class zamknijSluchacz implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        System.exit(0);
    }
}







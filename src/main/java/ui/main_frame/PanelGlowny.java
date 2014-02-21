package ui.main_frame;


import employees.Doctor;
import ui.branches_and_specializations.BranchesAndSpecializationsFrame;
import ui.patient.update.RegisterPatientFrame;
import ui.show_schedule.doctor.ScheduleFrame;
import ui.worker.RegisterWorkerFrame;
import util.Const;
import util.MyProgramLogger;
import util.Worker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.logging.Logger;

/**
 * @author Daniel Michalski
 */

public class PanelGlowny extends JPanel {
    private final static Logger LOGGER = MyProgramLogger.getMyLogger();

    private static final int BUTTON_WIDTH = 280;
    private static final int BUTTON_HEIGHT = 88;

    private JButton pracownicyBtn, wizytyBtn, oddzialyBtn, pacjetRejestracjaBtn;

    public PanelGlowny(String rodzajKonta, final Worker worker) {
        super();
        setLayout(null);

        // Ustawienie koloru tła
        setBackground(Const.Colors.FILL_BORDER);

        // Tworzenie popUpMenu nad panelem
        JPopupMenu popupMenu = new JPopupMenu("fd");
        JMenuItem oProgramieMenuItem = new JMenuItem("O Programie");
        JMenuItem zamknijMenuItem = new JMenuItem("Zamknij");
        zamknijMenuItem.addActionListener(new zamknijSluchacz());
        popupMenu.add(oProgramieMenuItem);
        popupMenu.add(zamknijMenuItem);
        setComponentPopupMenu(popupMenu);

        // Tworzenie ikon wykorzystywanych do przyciskow
        Icon rejestracjaIcon = new ImageIcon(getClass().getResource("/images/rejestracja.jpg"));
        Icon kalendarzIcon = new ImageIcon(getClass().getResource("/images/kalendarz.jpg"));
        Icon oddzialyIcon = new ImageIcon(getClass().getResource("/images/oddziały.jpg"));
        Icon pacjentIcon = new ImageIcon(getClass().getResource("/images/pacjent.jpg"));

        // Tworzenie przycisków
        pracownicyBtn = new JButton("Pracownicy");
        pracownicyBtn.setBounds(10, 10, BUTTON_WIDTH, BUTTON_HEIGHT);
        pracownicyBtn.setForeground(Color.darkGray);
        pracownicyBtn.setIcon(rejestracjaIcon);
        pracownicyBtn.setHorizontalAlignment(SwingConstants.LEFT);
        pracownicyBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LOGGER.info("Kliknięcie w przycisk 'Pracownicy'");
                RegisterWorkerFrame registrationFrame = new RegisterWorkerFrame();
                //registrationFrame.setModal(true);
                registrationFrame.setVisible(true);
            }
        }
        );

        wizytyBtn = new JButton("Wizyty pacjentów");
        wizytyBtn.setBounds(10, 110, BUTTON_WIDTH, BUTTON_HEIGHT);
        wizytyBtn.setForeground(Color.darkGray);
        wizytyBtn.setIcon(kalendarzIcon);
        wizytyBtn.setHorizontalAlignment(SwingConstants.LEFT);
        wizytyBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LOGGER.info("Kliknięcie w przycisk 'Wizyty pacjentów'");
                ScheduleFrame scheduleFrame = new ScheduleFrame((Doctor) worker);
                scheduleFrame.setModal(true);
                scheduleFrame.setVisible(true);
            }
        });

        oddzialyBtn = new JButton("Oddziały i Specjalizacje");
        oddzialyBtn.setBounds(10, 210, BUTTON_WIDTH, BUTTON_HEIGHT);
        oddzialyBtn.setForeground(Color.darkGray);
        oddzialyBtn.setHorizontalAlignment(SwingConstants.LEFT);
        oddzialyBtn.setIcon(oddzialyIcon);
        oddzialyBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LOGGER.info("Kliknięcie w przycisk 'Oddziały i specjalizacje'");
                BranchesAndSpecializationsFrame frame = new BranchesAndSpecializationsFrame();
                frame.setModal(true);
                frame.setVisible(true);
            }
        });

        pacjetRejestracjaBtn = new JButton("Pacjenci");
        pacjetRejestracjaBtn.setBounds(10, 310, BUTTON_WIDTH, BUTTON_HEIGHT);
        pacjetRejestracjaBtn.setForeground(Color.darkGray);
        pacjetRejestracjaBtn.setHorizontalAlignment(SwingConstants.LEFT);
        pacjetRejestracjaBtn.setIcon(pacjentIcon);
        pacjetRejestracjaBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LOGGER.info("Kliknięcie w przycisk 'Pacjenci'");
                RegisterPatientFrame frame = new RegisterPatientFrame(worker);
                frame.setVisible(true);
            }
        });

        // Dodanie przycisków do panelu
        add(pracownicyBtn);
        add(wizytyBtn);
        add(pacjetRejestracjaBtn);
        add(oddzialyBtn);

        // Zmiana widoku dla poszczególnych rodzajów kont użytkownika
        switch (rodzajKonta) {
            case Const.AccountType.ADMIN:
                viewForAdmin();
                break;
            case Const.AccountType.DOCTOR:
                viewForDoktorka();
                break;
            case Const.AccountType.RECEPTIONIST:
                viewFormReceptionist();
                break;
        }

    }
    private void viewForAdmin() {
        pracownicyBtn.setEnabled(true);
        wizytyBtn.setEnabled(false);
        oddzialyBtn.setEnabled(true);
        pacjetRejestracjaBtn.setEnabled(true);
    }

    private void viewForDoktorka() {
        pracownicyBtn.setEnabled(false);
        wizytyBtn.setEnabled(true);
        oddzialyBtn.setEnabled(false);
        pacjetRejestracjaBtn.setEnabled(true);
    }

    private void viewFormReceptionist() {
        pracownicyBtn.setEnabled(false);
        wizytyBtn.setEnabled(false);
        oddzialyBtn.setEnabled(false);
        pacjetRejestracjaBtn.setEnabled(true);
    }

    // Dodanie obrazka z prawej strony
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Toolkit tool = Toolkit.getDefaultToolkit();

        URL url = getClass().getResource("/images/lekarze.png");

        Image img = tool.getImage(url);
        g.drawImage(img, 300, 50, this);
    }
}

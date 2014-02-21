package ui.show_schedule.receptionist;

import employees.Doctor;
import ui.components.comboBox.ComboBoxDoctorAdapter;
import ui.show_schedule.HeaderPanel;
import util.validate.Validable;
import util.validate.ValidationException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class DoctorChooserPanel extends JPanel implements ActionListener, Validable {

    private ComboBoxDoctorAdapter cbDoctorAdapter;
    private SelectDoctorListener selectDoctorListener;
    private HeaderPanel header;

    public DoctorChooserPanel(HeaderPanel header, List<Doctor> doctors, SelectDoctorListener selectDoctorListener) {
        super(new GridBagLayout());
        this.header = header;
        this.selectDoctorListener = selectDoctorListener;

        initCbAdapter(doctors);
        setupContent();
    }

    private void initCbAdapter(List<Doctor> doctors) {
        cbDoctorAdapter = new ComboBoxDoctorAdapter(doctors);
    }

    private void setupContent() {
        JComboBox doctorsComboBox = new JComboBox();
        doctorsComboBox.addActionListener(this);
        doctorsComboBox.setModel(cbDoctorAdapter);
        GridBagConstraints gridConstraints = new GridBagConstraints();
        gridConstraints.fill = GridBagConstraints.BOTH;

        settleGridConstrains(gridConstraints, 0, 0, 1.0, 1.0);
        add(doctorsComboBox, gridConstraints);
    }

    private void settleGridConstrains(GridBagConstraints gridConstraints, int x, int y, double weightX, double weightY) {
        gridConstraints.gridx = x;
        gridConstraints.gridy = y;
        gridConstraints.weightx = weightX;
        gridConstraints.weighty = weightY;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        header.hideError();
        selectDoctorListener.onSelectDoctor(getSelectedDoctor());
    }

    public Doctor getSelectedDoctor() {
        return cbDoctorAdapter.getSelectedDoctor();
    }

    @Override
    public void check() throws ValidationException {
        if (cbDoctorAdapter.getSelectedDoctor() == null) {
            header.showError();
            throw new ValidationException();
        }
    }

}
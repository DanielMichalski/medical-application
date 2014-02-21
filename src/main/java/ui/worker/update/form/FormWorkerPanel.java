package ui.worker.update.form;

import database.dao.BranchDao;
import database.dao.DaoFactory;
import database.dao.SpecializationDao;
import database.util.Hash;
import employees.AccountType;
import employees.Admin;
import employees.Doctor;
import employees.Receptionist;
import ui.components.comboBox.ComboBoxBranchAdapter;
import ui.components.comboBox.ComboBoxSpecializationAdapter;
import util.Const;
import util.Worker;
import util.validate.validators.*;

import javax.swing.*;
import java.awt.*;

public class FormWorkerPanel extends JPanel {
    Hash hash;
    private JDialog parent;
    private WorkerAddressDataPanel addressPanel;
    private WorkerPersonalDataPanel personalPanel;
    private WorkerUserDataPanel userDataPanel;
    private WorkerTypePanel workerTypePanel;
    private ComboBoxBranchAdapter cbBranchAdapter;
    private ComboBoxSpecializationAdapter cbSpecializationAdapter;
    private NameValidator firstNameValidator;
    private NameValidator lastNameValidator;
    private PhoneNoValidator phoneNoValidator;
    private EmailValidator emailValidator;
    private NotEmptyValidator localityValidator;
    private AddressValidator addressValidator;
    private ZipCodeValidator zipCodeValidator;
    private LoginValidator loginValidator;
    private PasswordValidator password1Validator;
    private PasswordValidator password2Validator;

    public FormWorkerPanel(JDialog parent, Worker worker) {
        super(new GridBagLayout());
        this.parent = parent;
        setBackground(Const.Colors.BACKGROUND_PANEL);

        init();
        fillForm(worker);
        createContent();
        fillModels(worker);
    }

    private void init() {
        hash = new Hash();
        personalPanel = new WorkerPersonalDataPanel();

        firstNameValidator = new NameValidator(parent, personalPanel.getTfFirstName());
        personalPanel.getTfFirstName().setInputVerifier(firstNameValidator);
        lastNameValidator = new NameValidator(parent, personalPanel.getTfLastName());
        personalPanel.getTfLastName().setInputVerifier(lastNameValidator);
        phoneNoValidator = new PhoneNoValidator(parent, personalPanel.getTfPhoneNo());
        personalPanel.getTfPhoneNo().setInputVerifier(phoneNoValidator);
        emailValidator = new EmailValidator(parent, personalPanel.getTfEmail());
        personalPanel.getTfEmail().setInputVerifier(emailValidator);

        addressPanel = new WorkerAddressDataPanel();
        addressValidator = new AddressValidator(parent, addressPanel.getTfAddress());
        addressPanel.getTfAddress().setInputVerifier(addressValidator);
        zipCodeValidator = new ZipCodeValidator(parent, addressPanel.getTfZipCode());
        addressPanel.getTfZipCode().setInputVerifier(zipCodeValidator);
        localityValidator = new NotEmptyValidator(parent, addressPanel.getTfLocality());
        addressPanel.getTfLocality().setInputVerifier(localityValidator);

        userDataPanel = new WorkerUserDataPanel();
        loginValidator = new LoginValidator(parent, userDataPanel.getTflogin());
        userDataPanel.getTflogin().setInputVerifier(loginValidator);
        password1Validator = new PasswordValidator(parent, userDataPanel.getPfPassword1());
        userDataPanel.getPfPassword1().setInputVerifier(password1Validator);
        password2Validator = new PasswordValidator(parent, userDataPanel.getPfPassword2());
        userDataPanel.getPfPassword2().setInputVerifier(password2Validator);

        workerTypePanel = new WorkerTypePanel();
    }

    private void createContent() {
        GridBagConstraints gridConstraints = new GridBagConstraints();
        gridConstraints.fill = GridBagConstraints.BOTH;
        gridConstraints.insets = new Insets(5, 5, 5, 5);
        gridConstraints.weightx = 1.0;

        settleGridConstrains(gridConstraints, 0, 0);
        add(personalPanel, gridConstraints);

        settleGridConstrains(gridConstraints, 0, 1);
        add(addressPanel, gridConstraints);

        settleGridConstrains(gridConstraints, 0, 2);
        add(userDataPanel, gridConstraints);

        settleGridConstrains(gridConstraints, 0, 3);
        add(workerTypePanel, gridConstraints);
    }

    private void settleGridConstrains(GridBagConstraints gridConstraints, int x, int y) {
        gridConstraints.gridx = x;
        gridConstraints.gridy = y;
    }

    public void fillForm(Worker worker) {
        personalPanel.fillPersonalDataForm(worker);
        addressPanel.fillAddressForm(worker);
        userDataPanel.fillPersonalDataForm(worker);
    }

    public void fillModels(Worker worker) {
        BranchDao branchDao = new BranchDao(new DaoFactory());
        SpecializationDao specializationDao = new SpecializationDao(new DaoFactory());

        cbBranchAdapter =
                new ComboBoxBranchAdapter(branchDao.getAllBranches());
        cbSpecializationAdapter =
                new ComboBoxSpecializationAdapter(specializationDao.getAllSpecializations());

        workerTypePanel.getBranchesComboBox().setModel(cbBranchAdapter);
        workerTypePanel.getSpecializationComboBox().setModel(cbSpecializationAdapter);

        if (worker.getBranch() != null)
            cbBranchAdapter.setSelectedItem(worker.getBranch());
        if (worker.getSpecialization() != null)
            cbSpecializationAdapter.setSelectedItem(worker.getSpecialization());

    }

    public Worker getWorkerFromFields(Worker worker) {
        Integer id = worker.getId();
        AccountType workerAccountType = worker.getAccountType();
        System.out.println(workerAccountType);

        if (worker.getAccountType() != null) {
            switch (worker.getAccountType()) {
                case DOCTOR:
                    worker = getDoctorFromFields();
                    break;
                case RECEPTIONIST:
                    worker = getRecepionistFromFields();
                    break;
                case ADMIN:
                    worker = getAdminFromFields();
                    break;
            }
        } else {
            switch (workerTypePanel.getSelectedAccountType()) {
                case DOCTOR:
                    worker = getDoctorFromFields();
                    break;
                case RECEPTIONIST:
                    worker = getRecepionistFromFields();
                    break;
                case ADMIN:
                    worker = getAdminFromFields();
                    break;
            }
        }


        if (id != null)
            worker.setId(id);

        return worker;
    }

    private Worker getAdminFromFields() {
        return new Admin(
                personalPanel.getPatientFirstName(),
                personalPanel.getPatientLastName(),
                addressPanel.getPatientAddress(),
                personalPanel.getPatientPhoNumber(),
                personalPanel.getPatientEmail(),
                userDataPanel.getPatientLogin(),
                hash.HashString(userDataPanel.getPatientPassword1())
        );
    }

    private Worker getRecepionistFromFields() {
        return new Receptionist(
                personalPanel.getPatientFirstName(),
                personalPanel.getPatientLastName(),
                addressPanel.getPatientAddress(),
                personalPanel.getPatientPhoNumber(),
                personalPanel.getPatientEmail(),
                userDataPanel.getPatientLogin(),
                hash.HashString(userDataPanel.getPatientPassword1()),
                cbBranchAdapter.getSelectedBranch()
        );
    }

    private Worker getDoctorFromFields() {
        return new Doctor(
                personalPanel.getPatientFirstName(),
                personalPanel.getPatientLastName(),
                addressPanel.getPatientAddress(),
                personalPanel.getPatientPhoNumber(),
                personalPanel.getPatientEmail(),
                userDataPanel.getPatientLogin(),
                hash.HashString(userDataPanel.getPatientPassword1()),
                cbBranchAdapter.getSelectedBranch(),
                cbSpecializationAdapter.getSelectedSpecialization()
        );
    }

    public NameValidator getFirstNameValidator() {
        return firstNameValidator;
    }

    public NameValidator getLastNameValidator() {
        return lastNameValidator;
    }

    public AddressValidator getAddressValidator() {
        return addressValidator;
    }

    public ZipCodeValidator getZipCodeValidator() {
        return zipCodeValidator;
    }

    public NotEmptyValidator getLocalityValidator() {
        return localityValidator;
    }

    public LoginValidator getLoginValidator() {
        return loginValidator;
    }

    public PasswordValidator getPassword2Validator() {
        return password2Validator;
    }

    public PasswordValidator getPassword1Validator() {
        return password1Validator;
    }

    public PhoneNoValidator getPhoneNoValidator() {
        return phoneNoValidator;
    }

    public EmailValidator getEmailValidator() {
        return emailValidator;
    }

    public void setVisibleForEdit() {
        workerTypePanel.setVisible(false);
        userDataPanel.disableTFLogin();
    }

    public void setVisibleForRegister() {
        workerTypePanel.setVisible(true);
    }

    public WorkerUserDataPanel getUserDataPanel() {
        return userDataPanel;
    }

    public ComboBoxBranchAdapter getCbBranchAdapter() {
        return cbBranchAdapter;
    }

    public ComboBoxSpecializationAdapter getCbSpecializationAdapter() {
        return cbSpecializationAdapter;
    }

}
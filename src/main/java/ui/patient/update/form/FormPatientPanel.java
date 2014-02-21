package ui.patient.update.form;

import patient.PatientCard;
import util.Const;
import util.validate.validators.*;

import javax.swing.*;
import java.awt.*;

public class FormPatientPanel extends JPanel {

    private JDialog parent;
    private PatientAddressDataPanel addressPanel;
    private PatientContactDataPanel contactPanel;
    private PatientPersonalDataPanel personalPanel;

    private NameValidator firstNameValidator;
    private NameValidator lastNameValidator;
    private PeselValidator peselValidator;
    private EmailValidator emailValidator;
    private PhoneNoValidator phoneNoValidator;
    private NotEmptyValidator localityValidator;
    private AddressValidator addressValidator;
    private ZipCodeValidator zipCodeValidator;


    public FormPatientPanel(JDialog parent, PatientCard patientCard) {
        super(new GridBagLayout());
        this.parent = parent;
        setBackground(Const.Colors.BACKGROUND_PANEL);

        init();
        fillForm(patientCard);
        createContent();
    }

    private void init() {
        addressPanel = new PatientAddressDataPanel();
        addressValidator = new AddressValidator(parent, addressPanel.getTfAddress());
        addressPanel.getTfAddress().setInputVerifier(addressValidator);
        zipCodeValidator = new ZipCodeValidator(parent, addressPanel.getTfZipCode());
        addressPanel.getTfZipCode().setInputVerifier(zipCodeValidator);
        localityValidator = new NotEmptyValidator(parent, addressPanel.getTfLocality());
        addressPanel.getTfLocality().setInputVerifier(localityValidator);

        personalPanel = new PatientPersonalDataPanel();
        firstNameValidator = new NameValidator(parent, personalPanel.getTfFirstName());
        personalPanel.getTfFirstName().setInputVerifier(firstNameValidator);
        lastNameValidator = new NameValidator(parent, personalPanel.getTfLastName());
        personalPanel.getTfLastName().setInputVerifier(lastNameValidator);
        peselValidator = new PeselValidator(parent, personalPanel.getTfPesel());
        personalPanel.getTfPesel().setInputVerifier(peselValidator);

        contactPanel = new PatientContactDataPanel();
        emailValidator = new EmailValidator(parent, contactPanel.getTfEmail());
        contactPanel.getTfEmail().setInputVerifier(emailValidator);
        phoneNoValidator = new PhoneNoValidator(parent, contactPanel.getTfPhoneNo());
        contactPanel.getTfPhoneNo().setInputVerifier(phoneNoValidator);
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
        add(contactPanel, gridConstraints);
    }

    private void settleGridConstrains(GridBagConstraints gridConstraints, int x, int y) {
        gridConstraints.gridx = x;
        gridConstraints.gridy = y;
    }

    public void fillForm(PatientCard patientCard) {
        personalPanel.fillPersonalDataForm(patientCard);
        addressPanel.fillAddressForm(patientCard);
        contactPanel.fillContactForm(patientCard);
    }

    public PatientCard getPatientCardFromFields(PatientCard patientCard) {
        patientCard.setFirstName(personalPanel.getPatientFirstName());
        patientCard.setLastName(personalPanel.getPatientLastName());
        patientCard.setPESEL(personalPanel.getPatientPesel());

        patientCard.setAddress(addressPanel.getPatientAddress());

        patientCard.setPhoneNo(contactPanel.getPatientPhoneNo());
        patientCard.seteMail(contactPanel.getPatientEmailAddress());
        return patientCard;
    }

    public NameValidator getFirstNameValidator() {
        return firstNameValidator;
    }

    public NameValidator getLastNameValidator() {
        return lastNameValidator;
    }

    public PeselValidator getPeselValidator() {
        return peselValidator;
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

    public PhoneNoValidator getPhoneNoValidator() {
        return phoneNoValidator;
    }

    public EmailValidator getEmailValidator() {
        return emailValidator;
    }
}

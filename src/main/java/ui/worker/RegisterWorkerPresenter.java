package ui.worker;

import database.dao.BranchDao;
import database.dao.DaoFactory;
import database.dao.SpecializationDao;
import database.dao.WorkerDao;
import ui.worker.review.ReviewWorkerTablePanel;
import ui.worker.update.form.FormWorkerPanel;
import util.Worker;
import util.validate.ValidationException;
import util.validate.Validator;
import work.Branch;
import work.Specialization;

import javax.swing.*;
import java.util.Collections;
import java.util.List;

public class RegisterWorkerPresenter {

    private WorkerDao workerDao;
    private BranchDao branchDao;
    private SpecializationDao specializationDao;

    private FormWorkerPanel formWorkerPanel;
    private ReviewWorkerTablePanel tableWithWorkerPanel;
    private Validator validator;

    public RegisterWorkerPresenter() {
        DaoFactory factory = new DaoFactory();
        workerDao = factory.getWorkerDAO();
    }

    public RegisterWorkerPresenter(FormWorkerPanel formWorkerPanel) {
        this.formWorkerPanel = formWorkerPanel;
        DaoFactory factory = new DaoFactory();

        workerDao = factory.getWorkerDAO();
        branchDao = factory.getBranchDAO();
        specializationDao = factory.getSpecializationDAO();

        validator = new Validator(
                formWorkerPanel.getFirstNameValidator(),
                formWorkerPanel.getLastNameValidator(),
                formWorkerPanel.getEmailValidator(),
                formWorkerPanel.getAddressValidator(),
                formWorkerPanel.getZipCodeValidator(),
                formWorkerPanel.getPhoneNoValidator(),
                formWorkerPanel.getLocalityValidator(),
                formWorkerPanel.getLoginValidator(),
                formWorkerPanel.getPassword1Validator(),
                formWorkerPanel.getPassword2Validator()
        );
    }

    public Worker trySaveWorkerInDB(Worker workerToSave)
            throws ValidationException {
        validator.validate();
        String pass1 = String.valueOf(formWorkerPanel.getUserDataPanel().getPfPassword1().getPassword());
        String pass2 = String.valueOf(formWorkerPanel.getUserDataPanel().getPfPassword2().getPassword());

        if (!pass1.equals(pass2)) {
            JOptionPane.showMessageDialog(null, "Podane hasła nie są jednakowe", "Uwaga", JOptionPane.ERROR_MESSAGE);
            throw new ValidationException();
        }

        workerToSave = formWorkerPanel.getWorkerFromFields(workerToSave);
        workerToSave = workerDao.saveWorker(workerToSave);
        return workerToSave;
    }

    public Worker tryDeleteWorkerFromDB(Worker workerToDelete) {
        workerDao.deleteWorker(workerToDelete);
        return workerToDelete;
    }

    public List<Worker> getWorkersFromDB() {
        List<Worker> allWorkers = workerDao.getAllWorkers();
        Collections.sort(allWorkers);
        return allWorkers;
    }

    public List<Branch> getBranchesFromDB() {
        List<Branch> allBranches = branchDao.getAllBranches();
        return allBranches;
    }

    public List<Specialization> getSpecializationsFromDB() {
        List<Specialization> allSpecializations =
                specializationDao.getAllSpecializations();
        return allSpecializations;
    }

    public void setTableWithWorkerPanel(ReviewWorkerTablePanel tableWithPatientsPanel) {
        this.tableWithWorkerPanel = tableWithPatientsPanel;
    }

}
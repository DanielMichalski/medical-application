package ui.login.presenter;

import database.dao.DaoFactory;
import database.dao.WorkerDao;
import database.exceptions.NotFoundWorkerException;
import ui.login.view.LoginFrame;
import ui.login.view.LoginPanel;
import ui.main_frame.MainFrame;
import util.MyProgramLogger;
import util.Worker;
import util.finder.PressEnterListener;
import util.validate.ValidationException;
import util.validate.Validator;

import javax.swing.*;
import java.util.logging.Logger;

public class LoginPresenter implements PressEnterListener {
    private final static Logger LOGGER = MyProgramLogger.getMyLogger();

    private WorkerDao workerDao;
    private LoginFrame loginFrame;
    private LoginPanel loginPanel;
    private Validator validator;

    public LoginPresenter(LoginFrame loginFrame) {
        this.loginFrame = loginFrame;

        DaoFactory daoFactory = new DaoFactory();
        workerDao = daoFactory.getWorkerDAO();
    }

    public void loginWorkerAction() {
        try {
            validator.validate();
            Worker worker = logInUser(loginPanel.getLogin(), loginPanel.getPassword());
            LOGGER.info("pracownik sie zalogowal: " + worker.getLogin());

            new MainFrame(worker);
            loginFrame.dispose();
        } catch (ValidationException e) {
            LOGGER.info("Podano błedne dane logowania");
        } catch (NotFoundWorkerException e) {
            LOGGER.info("Podano zły login lub hasło - logowanie nieudane");
            JOptionPane.showMessageDialog(null, "Nie poprawnie wpisałeś własny login lub hasło", "Błąd", JOptionPane.ERROR_MESSAGE);
        }
    }

    // logowanie pracownika
    private Worker logInUser(String login, String password) throws NotFoundWorkerException {
        return workerDao.getWorkerByLoginAndPassword(login, password);
    }

    @Override
    public void onClickEnter() {
        loginWorkerAction();
    }

    public void setLoginPanel(LoginPanel loginPanel) {
        this.loginPanel = loginPanel;
    }

    public void createValidator() {
        validator = new Validator(
                loginPanel.getLoginValidator(),
                loginPanel.getPasswordValidator()
        );
    }
}

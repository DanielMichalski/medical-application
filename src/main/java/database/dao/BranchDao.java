package database.dao;

import database.SQLBranch;
import database.util.DaoUtil;
import util.MyProgramLogger;
import work.Branch;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class BranchDao extends SQLBranch {
    private final static Logger LOGGER = MyProgramLogger.getMyLogger();

    private DaoFactory daoFactory;

    public BranchDao(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public List<Branch> getAllBranches() {
        List<Branch> branches = new ArrayList<Branch>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement(selectAllQuery());
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Branch branch = getBranchByResultSet(resultSet);
                branches.add(branch);
            }
            LOGGER.info("Oddzialy pobrane z bazy");
        } catch (SQLException e) {
            LOGGER.warning(e.getMessage());
        } finally {
            DaoUtil.close(connection, preparedStatement, resultSet);
        }
        return branches;
    }

    public Branch saveBranch(Branch branch) {
        if (branch.isPersisted()) {
            updateBranch(branch);
        } else {
            branch = insertBranch(branch);
        }
        return branch;
    }

    private Branch insertBranch(Branch branch) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet generatedKeys = null;
        try {
            connection = daoFactory.getConnection();
            preparedStatement = DaoUtil.prepareStatement(connection, insertQuery(branch), true);
            preparedStatement.executeUpdate();
            generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                branch.setId(generatedKeys.getInt(1));
            }
            LOGGER.info("nowy oddzial dodany do bazy: " + branch.getName());
        } catch (SQLException e) {
            LOGGER.warning(e.getMessage());
        } finally {
            DaoUtil.close(connection, preparedStatement, generatedKeys);
        }
        return branch;
    }

    private void updateBranch(Branch branch) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = daoFactory.getConnection();
            preparedStatement = DaoUtil.prepareStatement(connection, updateQuery(branch), false);
            preparedStatement.executeUpdate();
            LOGGER.info("zmodyfikowany oddzial w bazie: " + branch.getName());
        } catch (SQLException e) {
            LOGGER.warning(e.getMessage());
        } finally {
            DaoUtil.close(connection, preparedStatement);
        }
    }

    public void deleteBranch(Branch branch) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = daoFactory.getConnection();
            preparedStatement = DaoUtil.prepareStatement(connection, deleteQuery(branch), false);
            preparedStatement.executeUpdate();
            LOGGER.info("usuniety oddzial z bazy: " + branch.getName());
        } catch (SQLException e) {
            LOGGER.warning(e.getMessage());
        } finally {
            DaoUtil.close(connection, preparedStatement);
        }
    }
}

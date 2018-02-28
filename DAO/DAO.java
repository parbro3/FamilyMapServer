package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Parker on 2/26/18.
 */

public class DAO {

    private Connection connection = null;
    private UserDAO userDAO = null;
    private PersonDAO personDAO = null;
    private EventDAO eventDAO = null;
    private AuthTokenDAO authTokenDAO = null;


    public DAO()
    {
        try {
            final String driver = "org.sqlite.JDBC";
            Class.forName(driver);

            //create an instance of each access object...
            userDAO = new UserDAO();
            personDAO = new PersonDAO();
            eventDAO = new EventDAO();
            authTokenDAO = new AuthTokenDAO();

        }
        catch(ClassNotFoundException e) {
            System.out.print(e.getMessage());
        }
    }

    public void openConnection()
    {
        String dbName = "FMDB.db";
        String connectionURL = "jdbc:sqlite:" + dbName;

        try {
            // Open a database connection
            connection = DriverManager.getConnection(connectionURL);
            // Start a transaction
            connection.setAutoCommit(false);
        }
        catch (SQLException e) {
            System.out.print(e.getMessage());
        }
    }

    public void closeConnection(boolean commit) {
        try {
            if (commit) {
                connection.commit();
            }
            else {
                connection.rollback();
            }

            connection.close();
            connection = null;
        }
        catch (SQLException e) {
            System.out.print(e.getMessage());
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public UserDAO getUserDAO() {
        userDAO.setConnection(this.connection);
        return userDAO;
    }

    public PersonDAO getPersonDAO() {
        personDAO.setConnection(this.connection);
        return personDAO;
    }

    public EventDAO getEventDAO() {
        eventDAO.setConnection(this.connection);
        return eventDAO;
    }

    public AuthTokenDAO getAuthTokenDAO() {
        authTokenDAO.setConnection(this.connection);
        return authTokenDAO;
    }
}

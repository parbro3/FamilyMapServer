package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Parker on 2/26/18.
 */

public class DAO {

    protected static Connection connection = null;
    private UserDAO userDAO = null;
    private PersonDAO personDAO = null;
    private EventDAO eventDAO = null;
    private AuthTokenDAO authTokenDAO = null;


    public DAO()
    {
    }

    public void initialize()
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
        return userDAO;
    }

    public PersonDAO getPersonDAO() {
        return personDAO;
    }

    public EventDAO getEventDAO() {
        return eventDAO;
    }

    public AuthTokenDAO getAuthTokenDAO() {
        return authTokenDAO;
    }
}

package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 * Parent class of all DAO objects. initialize() initializes all child DAO objects
 * shares the same connection among all dao classes
 */
public class DAO {

    protected static Connection connection = null;
    private UserDAO userDAO = null;
    private PersonDAO personDAO = null;
    private EventDAO eventDAO = null;
    private AuthTokenDAO authTokenDAO = null;


    /**
     * empty constructor
     */
    public DAO()
    {
    }

    /**
     * initializes all dao objects
     */
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
            //System.out.print(e.getMessage());
        }
    }

    /**
     * Opens a connection in the FMDB database
     */
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
            //System.out.print(e.getMessage());
        }
    }

    /**
     * closes a connection in the FMDB database
     * @param commit
     */
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
            //System.out.print(e.getMessage());
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

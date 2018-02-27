package DAO;

import android.database.DatabaseErrorHandler;

import Model.User;
import java.sql.*;
import java.util.*;
import java.io.*;

/**
 * Represents an User data access object
 * Methods access User table in database.
 */

public class UserDAO {

    PreparedStatement stmt = null;
    Statement keyStmt = null;
    ResultSet keyRS = null;
    Connection connection = null;

    /**
     * Empty constructor for Gson compatibility
     */
    public UserDAO()
    {
        try {
            final String driver = "org.sqlite.JDBC";
            Class.forName(driver);
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


    /**
     * Takes in a Model User object with all information
     * and inserts the information into the database. The function returns
     * true if the method succeeded.
     * @param user Model User
     * @return true if the user insert succeeded
     */
    public Boolean createUser(User user) throws SQLException
    {
        Boolean success = false;
        try
        {
            String sql = "insert into Users (UserName, Password, Email, FirstName, LastName, Gender, PersonID)" +
                    " values (?, ?, ?, ?, ?, ?, ?)";
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, user.getUserName());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getFirstName());
            stmt.setString(5, user.getLastName());
            stmt.setString(6, user.getGender());
            stmt.setString(7, user.getID());



            //if it inserted a row.
            if (stmt.executeUpdate() == 1)
            {
                System.out.print("Insert successful!");
                success = true;
            }
        }
        catch (SQLException e)
        {
            System.out.print("SQL Exception: " + e.getMessage());
        }
        catch (Exception e)
        {
            System.out.print("General Exception: " + e.getMessage());
        }
        finally
        {
            if (stmt != null) stmt.close();
            if (keyRS != null) keyRS.close();
            if (keyStmt != null) keyStmt.close();
        }

        return success;
    }

    /**
     * Takes in a userName as a string and checks the database for that user
     * which is the primary key. The function returns the User object.
     * @param userName String username
     * @return returns Model User if user is found in database
     */
    public User readUser(String userName) { return null; }

    /**
     * Deletes all users from database and returns a true boolean if the request succeeded
     * @return returns true if the request succeeded
     */
    public Boolean deleteAllUsers()
    {
        return true;
    }

    /**
     * Takes in a username string to delete a specific user from the database
     * @param username string is passed in
     * @return Returns a true boolean if the user was deleted
     */
    public Boolean deleteUser(String username)
    {
        return true;
    }




    /*
    UserDAO
    booleanMaybe? addUser(User u)

    User findUser(String userName);
    CRUD

    boolean deleteAllUsers();

    don't have single delete or update functions...

    //get everything you anticipate having as methods in the javadocs
     */



}

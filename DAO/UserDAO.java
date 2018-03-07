package DAO;

import Model.User;
import java.sql.*;
import java.util.ArrayList;

/**
 * Represents an User data access object
 * Methods access User table in database.
 */

public class UserDAO extends DAO{

    PreparedStatement stmt = null;
    Statement keyStmt = null;
    ResultSet keyRS = null;

    /**
     * Empty constructor for Gson compatibility
     */
    protected UserDAO(){}

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
            openConnection();
            String sql = "insert into Users (UserName, Password, Email, FirstName, LastName, Gender, PersonID)" +
                    " values (?, ?, ?, ?, ?, ?, ?)";

            System.out.print("before prepare statement\n");
            stmt = connection.prepareStatement(sql);
            System.out.print("after prepare statement\n");
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
                System.out.print("Insert User successful!");
                success = true;
            }
            closeConnection(true);
        }
        catch (SQLException e)
        {
            System.out.print("User Insert SQL Exception: " + e.getMessage());
            closeConnection(false);
        }
        catch (Exception e)
        {
            System.out.print("User Insert General Exception: " + e.getMessage());
            closeConnection(false);
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
    public User readUser(String userName) throws SQLException
    {
        ArrayList<User> queryUsers = new ArrayList();

        try {
            openConnection();
            System.out.print("Entered Read User Function! ");
            String sql = "select UserName, Password, Email, FirstName, LastName, Gender, PersonID from Users" +
                    " where Users.UserName = ?";

            stmt = connection.prepareStatement(sql);
            stmt.setString(1, userName);

            keyRS = stmt.executeQuery();

            while (keyRS.next()) {
                User user = new User();
                user.setUserName(keyRS.getString(1));
                user.setPassword(keyRS.getString(2));
                user.setEmail(keyRS.getString(3));
                user.setFirstName(keyRS.getString(4));
                user.setLastName(keyRS.getString(5));
                user.setGender(keyRS.getString(6));
                user.setID(keyRS.getString(7));

                queryUsers.add(user);
            }

            if (queryUsers.size() == 1) {
                System.out.print("User found!");
            }
            else {
                System.out.print("User not found!");
            }
            closeConnection(true);
        }
        catch(SQLException e)
        {
            System.out.print(e.getMessage());
            closeConnection(false);
        }
        catch(Exception e)
        {
            System.out.print(e.getMessage());
            closeConnection(false);
        }
        finally
        {
            if (stmt != null) stmt.close();
            if (keyRS != null) keyRS.close();
            if (keyStmt != null) keyStmt.close();
        }


        //return
        if(queryUsers.size() > 0)
        {
            return queryUsers.get(0);
        }
        return null;
    }

    /**
     * Takes in a username string to delete a specific user from the database
     * @param userName string is passed in
     * @return Returns a true boolean if the user was deleted
     */
    public Boolean deleteUser(String userName) throws SQLException
    {
        Boolean success = false;
        try {
            openConnection();
            String sql = "delete from Users where UserName = ?";
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, userName);

            if (stmt.executeUpdate() == 1) {
                System.out.print("Delete Successful!");
                success = true;
            }
            else
            {
                System.out.print("User not found!");
            }
            closeConnection(true);

        }
        catch(SQLException e)
        {
            System.out.print(e.getMessage());
            connection.rollback();
        }
        catch(Exception e)
        {
            System.out.print(e.getMessage());
            connection.rollback();
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
     * Deletes all users from database and returns a true boolean if the request succeeded
     * @return returns true if the request succeeded
     */
    public Boolean deleteAllUsers() throws SQLException
    {
        Boolean success = false;
        try {
            openConnection();
            String sql = "delete from Users";
            stmt = connection.prepareStatement(sql);

            if (stmt.executeUpdate() >= 0) {
                System.out.print("Delete successful!");
                success = true;
            }
            else
            {
                System.out.print("Delete unsuccessful");
            }
            closeConnection(true);
        }
        catch(SQLException e)
        {
            System.out.print(e.getMessage());
            closeConnection(false);
        }
        catch(Exception e)
        {
            System.out.print(e.getMessage());
            closeConnection(false);
        }
        finally
        {
            if (stmt != null) stmt.close();
            if (keyRS != null) keyRS.close();
            if (keyStmt != null) keyStmt.close();
        }
        return success;
    }
}

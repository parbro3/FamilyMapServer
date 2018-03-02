package DAO;

import java.sql.*;

import Model.AuthToken;
import java.util.ArrayList;

/**
 * Represents an AuthToken data access object
 * Methods access AuthToken table in database.
 */

public class AuthTokenDAO {

    PreparedStatement stmt = null;
    Statement keyStmt = null;
    ResultSet keyRS = null;
    private Connection connection = null;

    /**
     * Empty constructor to be accessed by Gson
     */
    protected AuthTokenDAO(){}

    /**
     * Takes in a Model Event object with all information
     * and inserts the information into the database. The function returns
     * true if the method succeeded.
     * @param authToken Model Event
     * @return true if the event insert succeeded
     */
    public Boolean createAuthToken(AuthToken authToken) throws SQLException
    {
        Boolean success = false;
        try
        {
            String sql = "insert into AuthTokens (AuthTokenID, UserName)" +
                    " values (?, ?)";
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, authToken.getID());
            stmt.setString(2, authToken.getUserName());

            //if it inserted a row.
            if (stmt.executeUpdate() == 1)
            {
                System.out.print("Insert AuthToken successful!");
                success = true;
            }
        }
        catch (SQLException e)
        {
            System.out.print("AuthToken Insert SQL Exception: " + e.getMessage());
            connection.rollback();
        }
        catch (Exception e)
        {
            System.out.print("AuthToken Insert General Exception: " + e.getMessage());
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
     * Takes in a AuthID as a string and checks the database for that AuthToken
     * which is the primary key. The function returns the AuthToken object.
     * @param AuthID String
     * @return returns Model AuthToken if user is found in database
     */
    public AuthToken readAuthToken(String AuthID) throws SQLException
    {
        try {
            String sql = "select AuthTokenID, UserName from AuthTokens" +
                    " where AuthTokens.AuthTokenID = ?";

            stmt = connection.prepareStatement(sql);
            stmt.setString(1, AuthID);

            keyRS = stmt.executeQuery();

            ArrayList<AuthToken> queryUsers = new ArrayList();

            while (keyRS.next()) {
                AuthToken authToken = new AuthToken();
                authToken.setID(keyRS.getString(1));
                authToken.setUserName(keyRS.getString(2));

                queryUsers.add(authToken);
            }

            if (queryUsers.size() == 1) {
                System.out.print("AuthToken found!");
                return queryUsers.get(0);
            }
            System.out.print("AuthToken not found!");
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
        return null;
    }

    /**
     * Deletes all AuthTokens from database and returns a true boolean if the request succeeded
     * @return returns true if the request succeeded
     */
    public Boolean deleteAllAuthTokens() throws SQLException
    {
        try {
            String sql = "delete from AuthTokens";
            stmt = connection.prepareStatement(sql);

            if (stmt.executeUpdate() > 0) {
                System.out.print("Delete successful!");
                return true;
            }

            System.out.print("Delete unsuccessful");
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
        return false;
    }

    /**
     * Takes in a authID string to delete a specific authToken from the database
     * @param authID string is passed in
     * @return Returns a true boolean if the authToken was deleted
     */
    public Boolean deleteAuthToken(String authID) throws SQLException
    {
        try {
            String sql = "delete from AuthTokens where AuthTokenID = ?";
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, authID);

            if (stmt.executeUpdate() == 1) {
                System.out.print("Delete Successful!");
                return true;
            }
            System.out.print("AuthToken not found!");
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
        return false;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
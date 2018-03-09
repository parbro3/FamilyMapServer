package DAO;

import java.sql.*;

import Model.AuthToken;
import java.util.ArrayList;

/**
 * Represents an AuthToken data access object
 * Methods access AuthToken table in database.
 */

public class AuthTokenDAO extends DAO{

    PreparedStatement stmt = null;
    Statement keyStmt = null;
    ResultSet keyRS = null;

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
            openConnection();
            String sql = "insert into AuthTokens (AuthTokenID, UserName)" +
                    " values (?, ?)";
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, authToken.getAuthTokenID());
            stmt.setString(2, authToken.getUserName());

            //if it inserted a row.
            if (stmt.executeUpdate() == 1)
            {
                //System.out.print("Insert AuthToken successful!");
                success = true;
            }
            closeConnection(true);
        }
        catch (SQLException e)
        {
            //System.out.print("AuthToken Insert SQL Exception: " + e.getMessage());
            closeConnection(false);
        }
        catch (Exception e)
        {
            //System.out.print("AuthToken Insert General Exception: " + e.getMessage());
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
     * Takes in a AuthID as a string and checks the database for that AuthToken
     * which is the primary key. The function returns the AuthToken object.
     * @param AuthID String
     * @return returns Model AuthToken if user is found in database
     */
    public AuthToken readAuthToken(String AuthID) throws SQLException
    {
        ArrayList<AuthToken> queryAuthTokens = new ArrayList();

        try {
            openConnection();
            //System.out.print("Entered AuthToken read function\n");
            String sql = "select AuthTokenID, UserName from AuthTokens" +
                    " where AuthTokens.AuthTokenID = ?";

            stmt = connection.prepareStatement(sql);
            stmt.setString(1, AuthID);

            keyRS = stmt.executeQuery();

            while (keyRS.next()) {
                AuthToken authToken = new AuthToken();
                authToken.setAuthTokenID(keyRS.getString(1));
                authToken.setUserName(keyRS.getString(2));

                queryAuthTokens.add(authToken);
            }

            if (queryAuthTokens.size() == 1) {
                //System.out.print("AuthToken found!");
            }
            else
            {
                //System.out.print("AuthToken not found!");
            }
            closeConnection(true);

        }
        catch(SQLException e)
        {
            //System.out.print(e.getMessage());
            closeConnection(false);
        }
        catch(Exception e)
        {
            //System.out.print(e.getMessage());
            closeConnection(false);
        }
        finally
        {
            if (stmt != null) stmt.close();
            if (keyRS != null) keyRS.close();
            if (keyStmt != null) keyStmt.close();
        }

        //return
        if(queryAuthTokens.size() > 0)
        {
            return queryAuthTokens.get(0);
        }
        return null;
    }

    /**
     * Deletes all AuthTokens from database and returns a true boolean if the request succeeded
     * @return returns true if the request succeeded
     */
    public Boolean deleteAllAuthTokens() throws SQLException
    {
        Boolean success = false;
        try {
            openConnection();
            String sql = "delete from AuthTokens";
            stmt = connection.prepareStatement(sql);

            if (stmt.executeUpdate() > 0) {
                //System.out.print("Delete successful!");
                success = true;
            }
            else
            {
                //System.out.print("Delete unsuccessful");
            }

            closeConnection(true);
        }
        catch(SQLException e)
        {
            //System.out.print(e.getMessage());
            closeConnection(false);
        }
        catch(Exception e)
        {
            //System.out.print(e.getMessage());
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
     * Takes in a authID string to delete a specific authToken from the database
     * @param authID string is passed in
     * @return Returns a true boolean if the authToken was deleted
     */
    public Boolean deleteAuthToken(String authID) throws SQLException
    {
        Boolean success = false;
        try {
            openConnection();
            String sql = "delete from AuthTokens where AuthTokenID = ?";
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, authID);

            if (stmt.executeUpdate() == 1) {
                System.out.print("Delete Successful!");
                success = true;
            }
            else
            {
                System.out.print("AuthToken not found!");
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
package DAO;

import Model.AuthToken;

/**
 * Represents an AuthToken data access object
 * Methods access AuthToken table in database.
 */

public class AuthTokenDAO {

    /**
     * Empty constructor to be accessed by Gson
     */
    public AuthTokenDAO(){}

    /**
     * Takes in a Model Event object with all information
     * and inserts the information into the database. The function returns
     * true if the method succeeded.
     * @param authToken Model Event
     * @return true if the event insert succeeded
     */
    public Boolean createAuthToken(AuthToken authToken)
    {
        return true;
    }

    /**
     * Takes in a AuthID as a string and checks the database for that AuthToken
     * which is the primary key. The function returns the AuthToken object.
     * @param AuthID String
     * @return returns Model AuthToken if user is found in database
     */
    public AuthToken readAuthToken(String AuthID)
    {
        return null;
    }

    /**
     * Deletes all AuthTokens from database and returns a true boolean if the request succeeded
     * @return returns true if the request succeeded
     */
    public Boolean deleteAllAuthTokens()
    {
        return true;
    }

    /**
     * Takes in a authID string to delete a specific authToken from the database
     * @param authID string is passed in
     * @return Returns a true boolean if the authToken was deleted
     */
    public Boolean deleteAuthToken(String authID)
    {
        return true;
    }
}
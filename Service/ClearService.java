package Service;

import java.sql.SQLException;

import DAO.DAO;
import Service.Request.ClearRequest;
import Service.Result.ClearResult;

/**
 * Represents a Clear Service object. Implements Service interface.
 * Instance created by ClearHandler. Brains of the api call.
 * Verifies Request. Calls EventDAO and other necessary DAO objects
 * to add and modify tables.
 *
 * Connection between ClearHandler and DAO classes
 *
 * Service method takes an object that implements "request" as a parameter and returns an object
 * of that implements "result."
 */
public class ClearService {

    DAO dao = new DAO();

    /**
     * Brains of the eventID creator. Verifies Request.
     * Calls EventDAO and other necessary DAO objects to add and modify
     * tables.
     * @param request of type Request Interface
     * @return Returns a ClearResult with success or error message.
     */
    public ClearResult service( ClearRequest request ){

        //System.out.print("Entered Clear service function!" );

        //probably need to edit this DAO stuff to check for stuff first butttt....
        ClearResult cResult = new ClearResult();

        try
        {
            dao.initialize();
            dao.getAuthTokenDAO().deleteAllAuthTokens();
            dao.getEventDAO().deleteAllEvents();
            dao.getPersonDAO().deleteAllPersons();
            dao.getUserDAO().deleteAllUsers();

            //return clear result
            cResult.setMessage("Clear succeeded.");
            return cResult;
        }
        catch(SQLException e)
        {
            cResult.setMessage("Internal Server Error: " + e.getMessage());
            ////System.out.print(e.getMessage());
        }
        catch(Exception e)
        {
            cResult.setMessage("Internal Server Error: " + e.getMessage());
            ////System.out.print(e.getMessage());
        }
        finally
        {
            return cResult;
        }

    }

}

package Service;
import Service.Result.FillResult;
import Service.Request.Request;

/**
 * Represents a Fill Service object. Implements Service interface.
 * Instance created by FillHandler. Brains of the api call. Verifies Request.
 * Populates the server's database with generated data for
 * the specified user name. The required "username" parameter
 * must be a user already registered with the server. If
 * there is any data in the database already associated with
 * the given user name, it is deleted. The optional “generations”
 * parameter lets the caller specify the number of generations of
 * ancestors to be generated, and must be a non-negative integer
 * (the default is 4, which results in 31 new persons each with
 * associated events).
 *
 * Connection between FillHandler and DAO classes
 *
 * Service method takes an object that implements "request" as a parameter and returns an object
 * of that implements "result."
 */

public class FillService implements Service  {

    /**
     * Brains of the Fill service. Verifies Request.
     * Populates the server's database with generated data for
     * the specified user name. The required "username" parameter
     * must be a user already registered with the server. If
     * there is any data in the database already associated with
     * the given user name, it is deleted. The optional “generations”
     * parameter lets the caller specify the number of generations of
     * ancestors to be generated, and must be a non-negative integer
     * (the default is 4, which results in 31 new persons each with
     * associated events).
     * @param request of type Request Interface
     * @return Returns a FillResult with success or error message.
     */
    public FillResult service( Request request ){

        return null;
    }
}

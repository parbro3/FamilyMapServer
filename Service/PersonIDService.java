package Service;
import Service.Request.Request;
import Service.Result.PersonIDResult;

/**
 * Represents a PersonID Service object. Implements Service interface.
 * Instance created by PersonIDHandler. Brains of the api call.
 * Verifies Request. Returns the single Person object with the specified ID.
 *
 * Connection between PersonIDHandler and DAO classes
 *
 * Service method takes an object that implements "request" as a parameter and returns an object
 * of that implements "result."
 */
public class PersonIDService implements Service  {

    /**
     * Brains of the personID service. Verifies Request.
     * Returns the single Person object with the specified ID.
     * @param request of type Request Interface
     * @return Returns an PersonIDResult with success or error message.
     */
    public PersonIDResult service(Request request ){

        return null;
    }

}

package Service;
import Service.Result.EventIDResult;
import Service.Request.Request;

/**
 * Represents a EventID Service object. Implements Service interface.
 * Instance created by EventIDHandler. Brains of the api call.
 * Verifies Request. Returns the single Event object with the specified ID.
 *
 * Connection between EventIDHandler and DAO classes
 *
 * Service method takes an object that implements "request" as a parameter and returns an object
 * of that implements "result."
 */

public class EventIDService implements Service {


    /**
     * Brains of the eventID creator. Verifies Request.
     * Returns the single Event object with the specified ID.
     * @param request of type Request Interface
     * @return Returns an EventIDResult with success or error message.
     */
    public EventIDResult service(Request request ){

        return null;
    }
}

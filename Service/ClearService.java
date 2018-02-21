package Service;

import Service.Request.Request;
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
public class ClearService implements Service {

    /**
     * Brains of the eventID creator. Verifies Request.
     * Calls EventDAO and other necessary DAO objects to add and modify
     * tables.
     * @param request of type Request Interface
     * @return Returns a ClearResult with success or error message.
     */
    public ClearResult service( Request request ){
        return null;
    }

}

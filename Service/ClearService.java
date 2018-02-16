package Service;

import Service.Request.Request;
import Service.Result.ClearResult;

/**
 * Represents a Clear Service object. Implements Service interface.
 * Instance created by ClearHandler.
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

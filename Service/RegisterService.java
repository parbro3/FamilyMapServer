package Service;

import Service.Result.*;
import Service.Request.*;

/**
 * Represents a Register Service object. Implements Service interface.
 * Instance created by RegisterHandler.
 */

public class RegisterService implements Service{

    /**
     * Brains of the register service. Verifies Request.
     * Creates a new user account, generates 4 generations
     * of ancestor data for the new user, logs the user in,
     * and returns an auth token.
     * @param request of type Request Interface
     * @return Returns a RegisterResult with success or error message.
     */
    public RegisterResult service( Request request )
    {
        return null;
    }

    /*
    This is the brains of the operations... the service classes
     */

    /*
    Register Service class:
     register(uer)
     UserDAO.findUser(username);
     UserDAO.add(user);
     */
}

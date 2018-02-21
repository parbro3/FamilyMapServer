package Service;
import Service.Request.Request;
import Service.Result.LoginResult;

/**
 * Represents a Login Service object. Implements Service interface.
 * Instance created by LoginHandler. Brains of the api call.
 * Verifies Request. Logs in the user and returns an auth token.
 *
 * Connection between LoginHandler and DAO classes
 *
 * Service method takes an object that implements "request" as a parameter and returns an object
 * of that implements "result."
 */

public class LoginService implements Service  {

    /**
     * Brains of the login service. Verifies Request.
     * Logs in the user and returns an auth token.
     * @param request of type Request Interface
     * @return Returns an LoginResult with success or error message.
     */
    public LoginResult service(Request request ){

        return null;
    }
}

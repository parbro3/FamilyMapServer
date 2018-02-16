package Service;
import Service.Request.Request;
import Service.Result.LoginResult;

/**
 * Represents a Login Service object. Implements Service interface.
 * Instance created by LoginHandler.
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

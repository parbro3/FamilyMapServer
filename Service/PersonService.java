package Service;
import Service.Request.Request;
import Service.Result.PersonResult;

/**
 * Represents a Person Service object. Implements Service interface.
 * Instance created by PersonHandler. Brains of the api call.
 * Verifies Request. Returns ALL family members of the current user.
 * The current user is determined from the provided auth token.
 *
 * Service method takes an object that implements "request" as a parameter and returns an object
 * of that implements "result."
 */

public class PersonService implements Service  {

    /**
     * Brains of the Person service. Verifies Request.
     * Returns ALL family members of the current user.
     * The current user is determined from the provided auth token.
     * @param request of type Request Interface
     * @return Returns a PersonResult with success or error message.
     */
    public PersonResult service(Request request ){

        return null;
    }
}

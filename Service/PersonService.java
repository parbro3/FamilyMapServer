package Service;
import java.sql.SQLException;

import Model.AuthToken;
import Model.User;
import Service.Request.PersonRequest;
import Service.Result.PersonResult;
import DAO.DAO;
import Model.Person;
import java.util.ArrayList;

/**
 * Represents a Person Service object. Implements Service interface.
 * Instance created by PersonHandler. Brains of the api call.
 * Verifies Request. Returns ALL family members of the current user.
 * The current user is determined from the provided auth token.
 *
 * Connection between PersonHandler and DAO classes
 *
 * Service method takes an object that implements "request" as a parameter and returns an object
 * of that implements "result."
 */

public class PersonService {

    DAO dao = new DAO();

    /**
     * Brains of the Person service. Verifies Request.
     * Returns ALL family members of the current user.
     * The current user is determined from the provided auth token.
     * @param request of type Request Interface
     * @return Returns a PersonResult with success or error message.
     */
    public PersonResult service(PersonRequest request )
    {
        System.out.print("Entered Person service function!" );

        PersonResult pResult = new PersonResult();

        //probably need to edit this DAO stuff to check for stuff first butttt....
        try
        {
            dao.initialize();
            System.out.print("looking for authtoken: " + request.getAuthID());
            AuthToken authToken = dao.getAuthTokenDAO().readAuthToken(request.getAuthID());
            if (authToken != null) {
                System.out.print("authToken is not null! ");

                User user = dao.getUserDAO().readUser(authToken.getUserName());
                ArrayList<Person> persons = dao.getPersonDAO().readPersonsFamily(user.getUserName());

                Person[] personArray = new Person[persons.size()];
                personArray = persons.toArray(personArray);

                pResult.setData(personArray);

            } else {
                pResult.setMessage("Invalid auth token");
            }
        }
        catch(SQLException e)
        {
            pResult.setMessage("Internal Server Error: " + e.getMessage());
            System.out.print(e.getMessage());
        }
        catch(Exception e)
        {
            pResult.setMessage("Internal Server Error: " + e.getMessage());
            System.out.print(e.getMessage());
        }
        return pResult;
    }
}

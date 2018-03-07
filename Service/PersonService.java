package Service;
import java.sql.SQLException;

import Model.AuthToken;
import Model.User;
import Service.Request.PersonRequest;
import Service.Result.PersonResult;
import DAO.DAO;
import Model.Person;

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
            System.out.print("looking for authtoken: " + request.getAuthID());
            dao.openConnection();
            AuthToken authToken = dao.getAuthTokenDAO().readAuthToken(request.getAuthID());
            if (authToken != null) {
                System.out.print("authToken is not null! ");
                dao.openConnection();

                User user = dao.getUserDAO().readUser(authToken.getUserName());
                Person[] persons = (Person[])dao.getPersonDAO().readPersonsFamily(user.getID()).toArray();

                //create the auth token in the database!!
                dao.getAuthTokenDAO().createAuthToken(authToken);

                pResult.setData(persons);
                dao.closeConnection(true);
                return pResult;

            } else {
                pResult.setMessage("Invalid auth token");
            }
        }
        catch(SQLException e)
        {
            dao.closeConnection(false);
            pResult.setMessage("Internal Server Error: " + e.getMessage());
            System.out.print(e.getMessage());
        }
        catch(Exception e)
        {
            dao.closeConnection(false);
            pResult.setMessage("Internal Server Error: " + e.getMessage());
            System.out.print(e.getMessage());
        }
        return pResult;
    }
}

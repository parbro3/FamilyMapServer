package Service;
import java.sql.SQLException;

import Service.Request.PersonIDRequest;
import Service.Result.PersonIDResult;
import DAO.DAO;
import Model.AuthToken;
import Model.Person;

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
public class PersonIDService {

    DAO dao = new DAO();

    /**
     * Brains of the personID service. Verifies Request.
     * Returns the single Person object with the specified ID.
     * @param request of type Request Interface
     * @return Returns an PersonIDResult with success or error message.
     */
    public PersonIDResult service(PersonIDRequest request ){

        PersonIDResult pResult = null;

        try
        {
            System.out.print("Entered PersonID Service Function!");
            //if the check was good....
            String checkAuthResult = checkAuth(request.getPersonID(), request.getAuthID());
            if (checkAuthResult.equals("good")) {
                pResult = new PersonIDResult();
                dao.openConnection();
                Person person = dao.getPersonDAO().readPerson(request.getPersonID());
                pResult.setDescendant(person.getDescendant());
                pResult.setFatherID(person.getFatherID());
                pResult.setFirstName(person.getFirstName());
                pResult.setGender(person.getGender());
                pResult.setLastName(person.getLastName());
                pResult.setMotherID(person.getMotherID());
                pResult.setSpouseID(person.getSpouseID());
                pResult.setPersonID(person.getID());
            }
            else
            {
                pResult.setMessage(checkAuthResult);
            }
        }
        catch(SQLException e)
        {
            System.out.print(e.getMessage());
        }
        catch(Exception e)
        {
            System.out.print(e.getMessage());
        }

        dao.closeConnection(true);
        return pResult;
    }

    /**
     * Checks to see if the auth token exists... 1
     * checks to see if the person id exists.... 2
     * Checks to see if the person belongs to the user/descendant... 3
     * @param personID
     * @param authID
     * @return "good" if passed all test else the associated error message
     */
    public String checkAuth(String personID, String authID)
    {
        try
        {
            dao.openConnection();
            AuthToken authToken = dao.getAuthTokenDAO().readAuthToken(authID);
            Person person = dao.getPersonDAO().readPerson(personID);

            if(authToken != null)
            {
                if(person != null)
                {
                    //now check if the person's descendant matches the authtoken username
                    if(authToken.getUserName().equals(person.getDescendant()))
                    {
                        dao.closeConnection(false);
                        return "good";
                    }
                    else
                    {
                        dao.closeConnection(false);
                        return "Requested person does not belong to this user";
                    }
                }
                else
                {
                    dao.closeConnection(false);
                    return "Invalid personID parameter";
                }
            }
            else
            {
                dao.closeConnection(false);
                return "Invalid auth token";
            }
        }
        catch(SQLException e)
        {
            dao.closeConnection(false);
            System.out.print(e.getMessage());
        }
        catch(Exception e)
        {
            dao.closeConnection(false);
            System.out.print(e.getMessage());
        }
        return null;
    }

}

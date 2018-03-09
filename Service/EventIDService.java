package Service;
import java.sql.SQLException;

import DAO.DAO;
import Model.AuthToken;
import Service.Request.EventIDRequest;
import Service.Result.EventIDResult;
import Model.Event;

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

public class EventIDService {

    DAO dao = new DAO();

    /**
     * Brains of the eventID creator. Verifies Request.
     * Returns the single Event object with the specified ID.
     * @param request of type Request Interface
     * @return Returns an EventIDResult with success or error message.
     */
    public EventIDResult service(EventIDRequest request){

        EventIDResult result = new EventIDResult();

        try
        {
            dao.initialize();
            //System.out.print("Entered EventID Service Function!");
            //if the check was good....
            String checkAuthResult = checkAuth(request.getEventID(), request.getAuthID());
            if (checkAuthResult.equals("good")) {
                result = new EventIDResult();
                Event event = dao.getEventDAO().readEvent(request.getEventID());
                result.setDescendant(event.getDescendant());
                result.setCity(event.getCity());
                result.setCountry(event.getCountry());
                result.setEventID(event.getEventID());
                result.setEventType(event.getEventType());
                result.setLatitude(event.getLatitude());
                result.setLongitude(event.getLongitude());
                result.setPersonID(event.getPersonID());
                result.setYear(event.getYear());
            }
            else
            {
                result.setMessage(checkAuthResult);
            }
        }
        catch(SQLException e)
        {
            result.setMessage("Internal Server Error: " + e.getMessage());
            //System.out.print(e.getMessage());
        }
        catch(Exception e)
        {
            result.setMessage("Internal Server Error: " + e.getMessage());
            //System.out.print(e.getMessage());
        }

        return result;
    }

    /**
     * Checks to see if the auth token exists... 1
     * checks to see if the person id exists.... 2
     * Checks to see if the person belongs to the user/descendant... 3
     * @param eventID
     * @param authID
     * @return "good" if passed all test else the associated error message
     */
    public String checkAuth(String eventID, String authID)
    {
        try
        {
            AuthToken authToken = dao.getAuthTokenDAO().readAuthToken(authID);
            Event event = dao.getEventDAO().readEvent(eventID);

            if(authToken != null)
            {
                if(event != null)
                {
                    //DESCENDANT IS A USERNAME!!
                    //now check if the person's descendant matches the authtoken username
                    if(authToken.getUserName().equals(event.getDescendant()))
                    {
                        return "good";
                    }
                    else
                    {
                        return "Requested event does not belong to this user";
                    }
                }
                else
                {
                    return "Invalid eventID parameter";
                }
            }
            else
            {
                return "Invalid auth token";
            }
        }
        catch(SQLException e)
        {
            //System.out.print("Internal Server Error: " + e.getMessage());
        }
        catch(Exception e)
        {
            //System.out.print("Internal Server Error: " + e.getMessage());
        }
        return null;
    }
}

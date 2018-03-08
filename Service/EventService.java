package Service;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.AuthToken;
import Model.Event;
import Model.User;
import Service.Request.EventRequest;
import Service.Result.EventResult;
import DAO.DAO;

/**
 * Represents a Event Service object. Implements Service interface.
 * Instance created by EventHandler. Brains of the api call.
 * Verifies Request. Returns ALL events for ALL family members of
 * the current user. The current user is determined
 * from the provided auth token.
 *
 * Connection between EventHandler and DAO classes
 *
 * Service method takes an object that implements "request" as a parameter and returns an object
 * of that implements "result."
 */

public class EventService {

    DAO dao = new DAO();

    /**
     * Brains of the event service. Verifies Request.
     * Returns ALL events for ALL family members of
     * the current user. The current user is determined
     * from the provided auth token.
     *
     * @param request of type Request Interface
     * @return Returns an EventResult with success or error message.
     */
    public EventResult service(EventRequest request) {
        {
            System.out.print("Entered Event service function!");

            EventResult result = new EventResult();

            //probably need to edit this DAO stuff to check for stuff first butttt....
            try {
                dao.initialize();
                System.out.print("looking for authtoken: " + request.getAuthID());
                AuthToken authToken = dao.getAuthTokenDAO().readAuthToken(request.getAuthID());
                if (authToken != null) {
                    System.out.print("authToken is not null! ");

                    User user = dao.getUserDAO().readUser(authToken.getUserName());
                    ArrayList<Event> events = dao.getEventDAO().readPersonEvents(user.getUserID());

                    Event[] personArray = new Event[events.size()];
                    personArray = events.toArray(personArray);

                    result.setData(personArray);

                } else {
                    result.setMessage("Invalid auth token");
                }
            } catch (SQLException e) {
                result.setMessage("Internal Server Error: " + e.getMessage());
                System.out.print(e.getMessage());
            } catch (Exception e) {
                result.setMessage("Internal Server Error: " + e.getMessage());
                System.out.print(e.getMessage());
            }
            return result;
        }
    }
}
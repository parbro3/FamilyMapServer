package Service;
import DAO.DAO;
import Service.Request.LoadRequest;
import Service.Result.LoadResult;

/**
 * Represents a Load Service object. Implements Service interface.
 * Instance created by LoadHandler. Brains of the api call.
 * Verifies Request. Clears all data from the database (just like
 * the /clear API), and then loads the posted user,
 * person, and event data into the database.
 *
 * Connection between LoadHandler and DAO classes
 *
 * Service method takes an object that implements "request" as a parameter and returns an object
 * of that implements "result."
 */

public class LoadService {

    DAO dao = new DAO();

    /**
     * Brains of the load service. Verifies Request.
     * Clears all data from the database (just like
     * the /clear API), and then loads the posted user,
     * person, and event data into the database.
     * @param request of type Request Interface
     * @return Returns an LoadResult with success or error message.
     */
    public LoadResult service(LoadRequest request ){

        //System.out.print("Entered Event service function!");

        LoadResult result = new LoadResult();
        try
        {
            dao.initialize();
            dao.getAuthTokenDAO().deleteAllAuthTokens();
            dao.getEventDAO().deleteAllEvents();
            dao.getPersonDAO().deleteAllPersons();
            dao.getUserDAO().deleteAllUsers();

            int userCount = 0;
            int personCount = 0;
            int eventCount = 0;
            if(request.getUsers() != null && request.getPersons() != null && request.getEvents() != null)
            {
                for(int i = 0; i < request.getUsers().length; i++, userCount++)
                {
                    dao.getUserDAO().createUser(request.getUsers()[i]);
                }
                for(int i = 0; i < request.getPersons().length; i++, personCount++)
                {
                    dao.getPersonDAO().createPerson(request.getPersons()[i]);
                }
                for(int i = 0; i < request.getEvents().length; i++, eventCount++)
                {
                    dao.getEventDAO().createEvent(request.getEvents()[i]);
                }
                result.setMessage("Successfully added "+userCount+" users, "+personCount+" persons, and "+eventCount+" events to the database.");
            }
            else
            {
                result.setMessage("Must include an array of users, persons, and events");
            }

        }
        catch(Exception e)
        {
            result.setMessage("Internal Server Error: " + e.getMessage());
            //System.out.print(e.getMessage());
        }

        return result;
    }
}

package Service;

import java.sql.SQLException;

import Model.AuthToken;
import Service.Result.*;
import Service.Request.*;
import Model.User;
import DAO.*;

/**
 * Represents a Register Service object. Implements Service interface.
 * Instance created by RegisterHandler. The Brains of the api call.
 * Creates a new user account, generates 4 generations
 * of ancestor data for the new user, logs the user in,
 * and returns an auth token.
 *
 * Connection between RegisterHandler and DAO classes
 *
 * Service method takes an object that implements "request" as a parameter and returns an object
 * of that implements "result."
 */

public class RegisterService{

    DAO dao = new DAO();

    /**
     * Brains of the register service. Verifies Request.
     * Creates a new user account, generates 4 generations
     * of ancestor data for the new user, logs the user in,
     * and returns an auth token.
     * @param request of type Request Interface
     * @return Returns a RegisterResult with success or error message.
     */
    public RegisterResult service( RegisterRequest request )
    {
        System.out.print("Entered service function!" );

        //gotta build user object out of the request...
        User user = new User(request.getUserName(), request.getPassWord(),request.getEmail(),request.getFirstName(),request.getLastName(),request.getGender());
        AuthToken authToken = null;


        //probably need to edit this DAO stuff to check for stuff first butttt....
        dao.openConnection();
        try
        {
            if(dao.getUserDAO().createUser(user))
            {
                //create the auth token??
                authToken = new AuthToken();
                authToken.setUserName(user.getUserName());
                authToken.setID(authToken.generateID());

                //create the auth token in the database!!
                dao.getAuthTokenDAO().createAuthToken(authToken);

                RegisterResult rResult = new RegisterResult();
                rResult.setUserName(user.getUserName());
                rResult.setPersonID(user.getID());

                //is this where i build the auth token???
                //and set it to the user name??
                rResult.setAuthToken(authToken.getID());
                dao.closeConnection(true);
                return rResult;
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

        return null;
    }

    public Boolean checkUsername(String username)
    {
        DAO dao = new DAO();
        dao.openConnection();
        try
        {
            if(dao.getUserDAO().readUser(username) == null)
            {
                //dao.closeConnection(true);
                return false;
            }
        }
        catch(SQLException e)
        {
            System.out.print(e.getMessage());
        }
        finally
        {
            dao.closeConnection(true);
        }
        return true;
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

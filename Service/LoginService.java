package Service;
import java.sql.SQLException;

import Model.AuthToken;
import Model.User;
import Service.Request.LoginRequest;
import Service.Result.LoginResult;
import Service.Result.LoginResult;
import DAO.DAO;

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

public class LoginService {

    //gotta build user object out of the request...
    User user = null;
    AuthToken authToken = null;
    DAO dao = null;

    /**
     * Brains of the login service. Verifies Request.
     * Logs in the user and returns an auth token.
     *
     * @param request of type Request Interface
     * @return Returns an LoginResult with success or error message.
     */
    public LoginResult service(LoginRequest request) {

        System.out.print("Entered Login service function!");

        LoginResult lResult = new LoginResult();

        //probably need to edit this DAO stuff to check for stuff first butttt....
        try {
            if (request.checkValues()) {
                if (checkUsername(request.getUserName())) {
                    if(checkPassword(request))
                    {
                        dao.openConnection();
                        user = dao.getUserDAO().readUser(request.getUserName());
                        //create the auth token??
                        authToken = new AuthToken();
                        authToken.setUserName(user.getUserName());
                        authToken.setID(authToken.generateID());

                        //create the auth token in the database!!
                        dao.getAuthTokenDAO().createAuthToken(authToken);


                        lResult.setUserName(user.getUserName());
                        lResult.setPersonID(user.getID());
                        lResult.setAuthToken(authToken.getID());

                        dao.closeConnection(true);
                        return lResult;

                    } else {
                        lResult.setMessage("Password is incorrect");
                    }
                } else {
                    lResult.setMessage("Username doesn't exist");
                }
            } else {
                lResult.setMessage(("Request property missing or has invalid value"));
            }
        } catch (SQLException e) {
            dao.closeConnection(false);
            lResult.setMessage("Internal Server SQL Error: " + e.getMessage());
            System.out.print(e.getMessage());
        } catch (Exception e) {
            dao.closeConnection(false);
            lResult.setMessage("Internal Server General Error: " + e.getMessage());
            System.out.print(e.getMessage());
        }
        return lResult;
    }

    public Boolean checkPassword(LoginRequest request)
    {
        dao.openConnection();
        try{
            User user = dao.getUserDAO().readUser(request.getUserName());
            if(user.getPassword().equals(request.getPassWord()))
                return true;
        }
        catch(SQLException e)
        {
            System.out.print(e.getMessage());
        }
        catch(Exception e)
        {
            System.out.print(e.getMessage());
        }
        return false;
    }

    public Boolean checkUsername(String username) {
        dao.openConnection();
        try {
            if (dao.getUserDAO().readUser(username) == null) {
                //dao.closeConnection(true);
                return false;
            }
        } catch (SQLException e) {
            System.out.print(e.getMessage());
        } finally {
            dao.closeConnection(true);
        }
        return true;
    }

}

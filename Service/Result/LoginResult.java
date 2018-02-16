package Service.Result;

/**
 * Represents the Login Result object with the appropriate message and data
 * depending on the outcome of the Login service.
 */

public class LoginResult implements Result {

    /**
     * LoginResult's error message
     */
    String errorMessage;
    /**
     * LoginResult's authorization token (UUID)
     */
    String authToken;
    /**
     * LoginResult's person id (UUID)
     */
    String personID;
    /**
     * LoginResult's username
     */
    String userName;

    /**
     * Empty constructor
     */
    public LoginResult() {
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}

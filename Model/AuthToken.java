package Model;

/**
 * Represents a Model class Authorization Token in memory
 * Only getters and setters because it's a shared dataholder object
 */

public class AuthToken {

    String authID;
    String userName;

    /**
     * Empty constructor to be accessed by Gson
     */
    AuthToken(){}

    public String getAuthID() {
        return authID;
    }

    public void setAuthID(String authID) {
        this.authID = authID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}

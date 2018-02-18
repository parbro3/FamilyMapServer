package Model;

/**
 * Represents a Model class Authorization Token in memory
 * Only getters and setters because it's a shared dataholder object
 */

public class AuthToken extends Model {

    /**
     * username that the authtoken belongs to
     */
    String userName;

    /**
     * Empty constructor to be accessed by Gson
     */
    public AuthToken(){}

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}

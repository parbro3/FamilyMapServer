package Service.Request;

/**
 * Represents a request object to return the single
 * Person object with the specified ID.
 */


public class PersonIDRequest {

    String authID;
    String personID;

    /**
     * Empty constructor
     */
    public PersonIDRequest() {
    }

    public String getAuthID() {
        return authID;
    }

    public void setAuthID(String authID) {
        this.authID = authID;
    }

    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }
}

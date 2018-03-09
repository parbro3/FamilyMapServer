package Service.Request;

/**
 * Represents a request object to return the single
 * Person object with the specified ID.
 */


public class PersonIDRequest {

    /**
     * authorization id from auth token
     */
    String authID;
    /**
     * person id to call for returning person
     */
    String personID;

    /**
     * Empty constructor
     */
    public PersonIDRequest() {
    }

    public Boolean checkValues()
    {
        return(getAuthID()!= null && getPersonID()!=null);
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

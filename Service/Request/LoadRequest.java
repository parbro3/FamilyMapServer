package Service.Request;

/**
 * Represents a request object to Clears all data from
 * the database (just like the /clear API), and then loads
 * the posted user, person, and event data into the database.
 */


public class LoadRequest {

    /**
     * LoadRequest's users
     */
    String[] users;
    /**
     * LoadRequest's persons
     */
    String[] persons;
    /**
     * LoadRequest's events
     */
    String[] events;

    /**
     * Empty constructor
     */
    public LoadRequest() {}

    public Boolean checkValues()
    {
        return (getUsers()!= null && getEvents()!=null && getPersons()!= null);
    }

    public String[] getUsers() {
        return users;
    }

    public void setUsers(String[] users) {
        this.users = users;
    }

    public String[] getPersons() {
        return persons;
    }

    public void setPersons(String[] persons) {
        this.persons = persons;
    }

    public String[] getEvents() {
        return events;
    }

    public void setEvents(String[] events) {
        this.events = events;
    }
}

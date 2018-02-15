package Service.Request;

/**
 * Represents a request object to Clears all data from
 * the database (just like the /clear API), and then loads
 * the posted user, person, and event data into the database.
 */


public class LoadRequest {

    String[] users;
    String[] persons;
    String[] events;

    public LoadRequest() {
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

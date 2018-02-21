package DAO;

import Model.Event;

/**
 * Represents an Event data access object
 * Methods access event table in database.
 */

public class EventDAO {

    /**
     * Empty constructor to be accessed by Gson
     */
    public EventDAO(){}

    /**
     * Takes in a Model Event object with all information
     * and inserts the information into the database. The function returns
     * true if the method succeeded.
     * @param event Model Event
     * @return true if the event insert succeeded
     */
    public Boolean createEvent(Event event)
    {
        return true;
    }

    /**
     * Takes in a eventID as a string and checks the database for that event
     * which is the primary key. The function returns the Event object.
     * @param eventID String
     * @return returns Model Event if user is found in database
     */
    public Event readEvent(String eventID)
    {
        return null;
    }

    /**
     * Deletes all persons from database and returns a true boolean if the request succeeded
     * @return returns true if the request succeeded
     */
    public Boolean deleteAllEvents()
    {
        return true;
    }

    /**
     * Takes in a eventID string to delete a specific event from the database
     * @param eventID string is passed in
     * @return Returns a true boolean if the event was deleted
     */
    public Boolean deleteEvent(String eventID)
    {
        return true;
    }


    /**
     * Takes in the userid of the current user as a string and looks in the database
     * to find all other family members of the current user and returns the events
     * belonging to those family members
     * @param UserID
     * @return
     */
    public Event[] readEventFamily(String UserID) { return null; }

}
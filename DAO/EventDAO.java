package DAO;

import Model.Event;
import java.sql.*;
import java.util.ArrayList;

/**
 * Represents an User data access object
 * Methods access User table in database.
 */

public class EventDAO extends DAO {

    PreparedStatement stmt = null;
    Statement keyStmt = null;
    ResultSet keyRS = null;

    /**
     * Empty constructor for Gson compatibility
     */
    protected EventDAO(){}

    /**
     * Takes in a Event object with all information
     * and inserts the information into the database. The function returns
     * true if the method succeeded.
     * @param event Model Event
     * @return true if the event insert succeeded
     */
    public Boolean createEvent(Event event) throws SQLException
    {
        Boolean success = false;
        try
        {
            openConnection();
            String sql = "insert into Events (EventID, Descendant, PersonID, Latitude, Longitude, Country, City, EventType, EventYear)" +
                    " values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, event.getID());
            stmt.setString(2, event.getDescendant());
            stmt.setString(3, event.getPerson());
            stmt.setString(4, event.getLatitude());
            stmt.setString(5, event.getLongitude());
            stmt.setString(6, event.getCountry());
            stmt.setString(7, event.getCity());
            stmt.setString(8, event.getEventType());
            stmt.setString(9, event.getYear());

            //if it inserted a row.
            if (stmt.executeUpdate() == 1)
            {
                System.out.print("Insert Event successful!");
                success = true;
            }
            closeConnection(true);
        }
        catch (SQLException e)
        {
            System.out.print("Event Insert SQL Exception: " + e.getMessage());
            closeConnection(false);
        }
        catch (Exception e)
        {
            System.out.print("Event Insert General Exception: " + e.getMessage());
            closeConnection(false);
        }
        finally
        {
            if (stmt != null) stmt.close();
            if (keyRS != null) keyRS.close();
            if (keyStmt != null) keyStmt.close();
        }

        return success;
    }

    /**
     * Takes in a eventID as a string and checks the database for that user
     * which is the primary key. The function returns the User object.
     * @param eventID String username
     * @return returns Model Event if user is found in database
     */
    public Event readEvent(String eventID) throws SQLException
    {
        ArrayList<Event> queryEvents = new ArrayList();

        try {
            openConnection();
            String sql = "select EventID, Descendant, PersonID, Latitude, Longitude, Country, City, EventType, EventYear from Events" +
                    " where Events.EventID = ?";

            stmt = connection.prepareStatement(sql);
            stmt.setString(1, eventID);

            keyRS = stmt.executeQuery();

            while (keyRS.next()) {
                Event event = new Event();
                event.setDescendant(keyRS.getString(1));
                event.setPerson(keyRS.getString(2));
                event.setLatitude(keyRS.getString(3));
                event.setLongitude(keyRS.getString(4));
                event.setCountry(keyRS.getString(5));
                event.setCity(keyRS.getString(6));
                event.setEventType(keyRS.getString(7));
                event.setYear(keyRS.getString(8));

                queryEvents.add(event);
            }

            if (queryEvents.size() == 1) {
                System.out.print("Event found!");
            }
            else
            {
                System.out.print("Event not found!");
            }
            closeConnection(true);

        }
        catch (SQLException e)
        {
            System.out.print("Event Read SQL Exception: " + e.getMessage());
            closeConnection(false);
        }
        catch (Exception e)
        {
            System.out.print("Event Read General Exception: " + e.getMessage());
            closeConnection(false);
        }
        finally
        {
            if (stmt != null) stmt.close();
            if (keyRS != null) keyRS.close();
            if (keyStmt != null) keyStmt.close();
        }

        if(queryEvents.size() > 0)
        {
            return queryEvents.get(0);
        }
        return null;
    }

    /**
     * Takes in a eventid string to delete a specific user from the database
     * @param eventID string is passed in
     * @return Returns a true boolean if the user was deleted
     */
    public Boolean deleteEvent(String eventID) throws SQLException
    {
        Boolean success = false;
        try {
            openConnection();
            String sql = "delete from Events where EventID = ?";
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, eventID);

            if (stmt.executeUpdate() == 1) {
                System.out.print("Delete Successful!");
                success = true;
            }
            else
            {
                System.out.print("Event not found!");
            }
            closeConnection(true);

        }
        catch (SQLException e)
        {
            System.out.print("Event Delete SQL Exception: " + e.getMessage());
            closeConnection(false);
        }
        catch (Exception e)
        {
            System.out.print("Event Delete General Exception: " + e.getMessage());
            closeConnection(false);
        }
        finally
        {
            if (stmt != null) stmt.close();
            if (keyRS != null) keyRS.close();
            if (keyStmt != null) keyStmt.close();
        }
        return success;
    }

    /**
     * Deletes all users from database and returns a true boolean if the request succeeded
     * @return returns true if the request succeeded
     */
    public Boolean deleteAllEvents() throws SQLException
    {
        Boolean success = false;
        try {
            openConnection();
            String sql = "delete from Events";
            stmt = connection.prepareStatement(sql);

            if (stmt.executeUpdate() > 0) {
                System.out.print("Delete successful!");
                success = true;
            }
            else
            {
                System.out.print("Delete unsuccessful");
            }

            closeConnection(true);

        }
        catch (SQLException e)
        {
            System.out.print("Event DeleteAll SQL Exception: " + e.getMessage());
            closeConnection(false);
        }
        catch (Exception e)
        {
            System.out.print("Event DeleteAll General Exception: " + e.getMessage());
            closeConnection(false);
        }
        finally
        {
            if (stmt != null) stmt.close();
            if (keyRS != null) keyRS.close();
            if (keyStmt != null) keyStmt.close();
        }
        return success;
    }

    public ArrayList<Event> readPersonEvents(String userID) throws SQLException
    {
        ArrayList<Event> queryEvents = new ArrayList();
        try {
            openConnection();
            //this would definitely have to be recursive if
            String sql = "select EventID, Descendant, PersonID, Latitude, Longitude, Country, City, EventType, EventYear from Events" +
                    " where Events.Descendant = ?";

            stmt = connection.prepareStatement(sql);
            stmt.setString(1, userID);

            keyRS = stmt.executeQuery();

            while (keyRS.next()) {
                Event event = new Event();
                event.setID(keyRS.getString(1));
                event.setDescendant(keyRS.getString(2));
                event.setPerson(keyRS.getString(3));
                event.setLatitude(keyRS.getString(4));
                event.setLongitude(keyRS.getString(5));
                event.setCountry(keyRS.getString(6));
                event.setCity(keyRS.getString(7));
                event.setEventType(keyRS.getString(8));
                event.setYear(keyRS.getString(9));

                queryEvents.add(event);
            }

            if (queryEvents.size() > 0) {
                System.out.print("Events found!");
            }
            else {
                System.out.print("No events found!");
            }
            closeConnection(true);

        }
        catch (SQLException e)
        {
            System.out.print("Event ReadPerson SQL Exception: " + e.getMessage());
            closeConnection(false);
        }
        catch (Exception e)
        {
            System.out.print("Event ReadPerson General Exception: " + e.getMessage());
            closeConnection(false);
        }
        finally
        {
            if (stmt != null) stmt.close();
            if (keyRS != null) keyRS.close();
            if (keyStmt != null) keyStmt.close();
        }

        //return
        if(queryEvents.size() > 0)
        {
            return queryEvents;
        }
        return null;
    }
}

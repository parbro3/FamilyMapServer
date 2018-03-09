package Service;
import java.sql.Array;
import java.sql.SQLException;
import java.util.ArrayList;

import DAO.DAO;
import Service.Request.FillRequest;
import Service.Result.FillResult;
import Service.Request.Request;
import Service.Result.FillResult;
import Model.Person;
import Model.Event;
import Model.User;
import java.util.Random;
import Data.*;
import JSON.Encoder;

/**
 * Represents a Fill Service object. Implements Service interface.
 * Instance created by FillHandler. Brains of the api call. Verifies Request.
 * Populates the server's database with generated data for
 * the specified user name. The required "username" parameter
 * must be a user already registered with the server. If
 * there is any data in the database already associated with
 * the given user name, it is deleted. The optional “generations”
 * parameter lets the caller specify the number of generations of
 * ancestors to be generated, and must be a non-negative integer
 * (the default is 4, which results in 31 new persons each with
 * associated events).
 *
 * Connection between FillHandler and DAO classes
 *
 * Service method takes an object that implements "request" as a parameter and returns an object
 * of that implements "result."
 */

public class FillService {

    DAO dao = new DAO();
    Random random = new Random();
    LocationData locData = null;
    MaleNames mNames = null;
    LastName lNames = null;
    FemaleNames fNames = null;

    //number of adds data
    int personAdds = 0;
    int eventAdds = 0;


    /**
     * Brains of the Fill service. Verifies Request.
     * Populates the server's database with generated data for
     * the specified user name. The required "username" parameter
     * must be a user already registered with the server. If
     * there is any data in the database already associated with
     * the given user name, it is deleted. The optional “generations”
     * parameter lets the caller specify the number of generations of
     * ancestors to be generated, and must be a non-negative integer
     * (the default is 4, which results in 31 new persons each with
     * associated events).
     * @param request of type Request Interface
     * @return Returns a FillResult with success or error message.
     */
    public FillResult service( FillRequest request ){

        //System.out.print("Entered Fill service function!");

        FillResult result = new FillResult();
        try
        {
            if(request.checkValues())
            {
                dao.initialize();
                if(checkUsername(request.getUserName()))
                {
                    //configure random object
                    random.setSeed((int) System.nanoTime());
                    readData();

                    //delete persons and events associated with the username
                    ArrayList<Event> deleteEvents = dao.getEventDAO().readPersonEvents(request.getUserName());
                    ArrayList<Person> deletePersons = dao.getPersonDAO().readPersonsFamily(request.getUserName());

                    if(deleteEvents != null)
                    {
                        for(int i = 0; i < deleteEvents.size(); i++)
                        {
                            dao.getEventDAO().deleteEvent(deleteEvents.get(i).getEventID());
                        }
                    }

                    if(deletePersons != null)
                    {
                        for(int i = 0; i < deletePersons.size(); i++)
                        {
                            dao.getPersonDAO().deletePerson(deletePersons.get(i).getPersonID());
                        }
                    }


                    //generate the data for the user
                    //create person
                    User user = dao.getUserDAO().readUser(request.getUserName());
                    Person rootPerson = new Person();
                    rootPerson.setGender(user.getGender());
                    rootPerson.setPersonID(user.getPersonID());
                    rootPerson.setLastName(user.getLastName());
                    rootPerson.setFirstName(user.getFirstName());
                    rootPerson.setDescendant(user.getUserName());

                    //generateEvents(rootPerson, 1994);

                    int userCount = 0;
                    int personCount = 0;
                    int eventCount = 0;
                    int generations = request.getGenerations();

                    generationCreator(rootPerson, generations, 1994);

                    result.setMessage("Successfully added "+personAdds+" persons, and "+eventAdds+" events to the database.");
                }
                else
                {
                    result.setMessage("Invalid Username");
                    //System.out.print("Invalid Username");
                }
            }
            else
            {
                result.setMessage("Invalid Generation Value or null userName");
                //System.out.print("Invalid Generation Value or null userName");
            }
        }
        catch(Exception e)
        {
            result.setMessage("Internal Server Error: " + e.getMessage());
            //System.out.print(e.getMessage());
        }

        return result;
    }

    public void readData()
    {
        Encoder encoder = new Encoder();
        fNames = (FemaleNames) encoder.decodeFile("app/src/main/java/Data/fnames.json", FemaleNames.class);
        mNames = (MaleNames) encoder.decodeFile("app/src/main/java/Data/mnames.json", MaleNames.class);
        lNames = (LastName) encoder.decodeFile("app/src/main/java/Data/snames.json", LastName.class);
        locData = (LocationData)encoder.decodeFile("app/src/main/java/Data/locations.json", LocationData.class);

    }

    public void generationCreator(Person person, int generations, int birthYear) throws SQLException
    {
        //create a father...
        Person father = createPerson(person, "m");
        Person mother = createPerson(person, "f");

        //set father and mother
        marry(father,mother);
        person.setFatherID(father.getPersonID());
        person.setMotherID(mother.getPersonID());

        //if there are no more generations... just add the person and to the DB and return
        //otherwise set the father and mother... and then add all three to the database
        if(generations == 0)
        {
            //how can i create the father, mother, and spouse id's??
            //before I add the person??
            dao.getPersonDAO().createPerson(person);
            personAdds++;
            generateEvents(person, birthYear);

            return;
        }
        generations--;

        //subtract birthyear
        generationCreator(father, generations, random.nextInt(birthYear - (birthYear-30)) + birthYear);
        generationCreator(mother, generations, random.nextInt(birthYear - (birthYear-30)) + birthYear);

        //then create the person??
        dao.getPersonDAO().createPerson(person);
        personAdds++;
        //create events
        generateEvents(person, birthYear);

    }

    public void marry(Person father, Person mother)
    {
        father.setSpouseID(mother.getPersonID());
        mother.setSpouseID(father.getPersonID());
    }

    public Person createPerson(Person child, String gender) throws SQLException
    {
        Person person = new Person();
        person.setDescendant(child.getDescendant());
        person.setLastName(lNames.getData()[random.nextInt(lNames.getData().length - 1)]);
        person.setPersonID(person.generateID());
        person.setGender(gender);

        if(gender.equals("m"))
        {
            person.setFirstName(mNames.getData()[random.nextInt(mNames.getData().length - 1)]);
        }
        else
        {
            person.setFirstName(fNames.getData()[random.nextInt(fNames.getData().length - 1)]);
        }

        return person;
    }


    //returns an array list of the created events.
    public ArrayList<Event> generateEvents(Person person, int birthYear) throws SQLException
    {
        ArrayList<Event> events = new ArrayList();
        events.add(bdmEvent(person, birthYear, "birth"));
        events.add(bdmEvent(person, random.nextInt((birthYear+20) - birthYear) + birthYear, "marriage"));
        events.add(bdmEvent(person, random.nextInt((birthYear+70) - birthYear) + birthYear, "death"));

        return events;
    }

    public Event bdmEvent(Person person, int birthYear, String eventType) throws SQLException
    {
        Event event = new Event();
        event.setDescendant(person.getDescendant());
        event.setEventID(event.generateID());
        event.setPersonID(person.getPersonID());
        event.setYear(String.valueOf(birthYear));
        event.setEventType(eventType);
        //this randomizer is probably wrong...
        Location location = locData.getData()[random.nextInt(locData.getData().length - 1)];
        event.setCity(location.getCity());
        event.setCountry(location.getCountry());
        event.setLongitude(location.getLongitude());
        event.setLatitude(location.getLatitude());

        dao.getEventDAO().createEvent(event);
        eventAdds++;

        return event;
    }



    public Boolean checkUsername(String username) {
        try {
            if (dao.getUserDAO().readUser(username) == null) {
                return false;
            }
        } catch (SQLException e) {
            //System.out.print(e.getMessage());
        }
        return true;
    }
}

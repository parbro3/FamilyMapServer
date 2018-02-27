package DAO;

import Model.Person;

/**
 * Represents a Person data access object
 * Methods access Person table in database.
 */

public class PersonDAO extends DAO {

    /**
     * Empty constructor to be accessed by Gson
     */
    PersonDAO(){}

    /**
     * Takes in a Model Person object with all information
     * and inserts the information into the database. The function returns
     * true if the method succeeded.
     * @param person Model Person
     * @return true if the person insert succeeded
     */
    public Boolean createPerson(Person person) { return true; }

    /**
     * Takes in a personID as a string and checks the database for that person
     * which is the primary key. The function returns the User object.
     * @param personID String
     * @return returns Model Person if user is found in database
     */
    public Person readPerson(String personID)
    {
        return null;
    }

    /**
     * Deletes all persons from database and returns a true boolean if the request succeeded
     * @return returns true if the request succeeded
     */
    public Boolean deleteAllPersons()
    {
        return true;
    }

    /**
     * Takes in a personID string to delete a specific person from the database
     * @param personID string is passed in
     * @return Returns a true boolean if the person was deleted
     */
    public Boolean deletePerson(String personID)
    {
        return true;
    }

    /**
     * Takes in a personID string and checks the database for that person. The
     * function will then return all the family members of the person.
     * @param personID
     * @return Person[] Array of Persons
     */
    public Person[] readPersonsFamily(String personID)
    {
        return null;
    }

}

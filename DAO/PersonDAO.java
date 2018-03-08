package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Model.Person;

/**
 * Represents a Person data access object
 * Methods access Person table in database.
 */

public class PersonDAO extends DAO {

    PreparedStatement stmt = null;
    Statement keyStmt = null;
    ResultSet keyRS = null;

    /**
     * Empty constructor to be accessed by Gson
     */
    protected PersonDAO() {}

    /**
     * Takes in a Model Person object with all information
     * and inserts the information into the database. The function returns
     * true if the method succeeded.
     * @param person Model Person
     * @return true if the person insert succeeded
     */
    public Boolean createPerson(Person person) throws SQLException
    {
        Boolean success = false;
        try
        {
            openConnection();
            String sql = "insert into Persons (PersonID, Descendant, FirstName, LastName, Gender, FatherID, MotherID, SpouseID)" +
                    " values (?, ?, ?, ?, ?, ?, ?, ?)";
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, person.getPersonID());
            stmt.setString(2, person.getDescendant());
            stmt.setString(3, person.getFirstName());
            stmt.setString(4, person.getLastName());
            stmt.setString(5, person.getGender());
            stmt.setString(6, person.getFatherID());
            stmt.setString(7, person.getMotherID());
            stmt.setString(7, person.getSpouseID());


            //if it inserted a row.
            if (stmt.executeUpdate() == 1)
            {
                System.out.print("Insert Person successful!");
                success = true;
            }
            closeConnection(true);
        }
        catch (SQLException e)
        {
            System.out.print("Person Insert SQL Exception: " + e.getMessage());
            closeConnection(false);
        }
        catch (Exception e)
        {
            System.out.print("Person Insert General Exception: " + e.getMessage());
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
     * Takes in a personID as a string and checks the database for that person
     * which is the primary key. The function returns the User object.
     * @param personID String
     * @return returns Model Person if user is found in database
     */
    public Person readPerson(String personID) throws SQLException
    {
        ArrayList<Person> queryPersons = new ArrayList();
        try
        {
            openConnection();
            String sql = "select PersonID, Descendant, FirstName, LastName, Gender, FatherID, MotherID, SpouseID from Persons" +
                    " where Persons.PersonID = ?";

            stmt = connection.prepareStatement(sql);
            stmt.setString(1, personID);

            keyRS = stmt.executeQuery();

            while (keyRS.next()) {
                Person person = new Person();
                person.setPersonID(keyRS.getString(1));
                person.setDescendant(keyRS.getString(2));
                person.setFirstName(keyRS.getString(4));
                person.setLastName(keyRS.getString(5));
                person.setGender(keyRS.getString(6));
                person.setFatherID(keyRS.getString(7));
                person.setMotherID(keyRS.getString(7));
                person.setSpouseID(keyRS.getString(7));

                queryPersons.add(person);
            }

            if (queryPersons.size() == 1) {
                System.out.print("Person found!");
            }
            else {
                System.out.print("Person not found!");
            }
            closeConnection(true);

        }
        catch(SQLException e)
        {
            System.out.print(e.getMessage());
            closeConnection(false);
        }
        catch(Exception e)
        {
            System.out.print(e.getMessage());
            closeConnection(false);
        }
        finally
        {
            if (stmt != null) stmt.close();
            if (keyRS != null) keyRS.close();
            if (keyStmt != null) keyStmt.close();
        }

        //return
        if(queryPersons.size() > 0)
        {
            return queryPersons.get(0);
        }
        return null;
    }

    /**
     * Deletes all persons from database and returns a true boolean if the request succeeded
     * @return returns true if the request succeeded
     */
    public Boolean deleteAllPersons() throws SQLException
    {
        Boolean success = false;
        try {
            openConnection();
            String sql = "delete from Persons";
            stmt = connection.prepareStatement(sql);

            if (stmt.executeUpdate() >= 0) {
                System.out.print("Delete successful!");
                success = true;
            }
            else
            {
                System.out.print("Delete unsuccessful");
            }
            closeConnection(true);

        }
        catch(SQLException e)
        {
            System.out.print(e.getMessage());
            closeConnection(false);
        }
        catch(Exception e)
        {
            System.out.print(e.getMessage());
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
     * Takes in a personID string to delete a specific person from the database
     * @param personID string is passed in
     * @return Returns a true boolean if the person was deleted
     */
    public Boolean deletePerson(String personID) throws SQLException
    {
        Boolean success = false;
        try {
            openConnection();
            String sql = "delete from Persons where PersonID = ?";
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, personID);

            if (stmt.executeUpdate() == 1) {
                System.out.print("Delete Successful!");
                success = true;
            }
            else
            {
                System.out.print("Person not found!");
            }
            closeConnection(true);

        }
        catch(SQLException e)
        {
            System.out.print(e.getMessage());
            closeConnection(false);
        }
        catch(Exception e)
        {
            System.out.print(e.getMessage());
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
     * Takes in a personID string and checks the database for that person. The
     * function will then return all the family members of the person.
     * @param userID
     * @return Person[] Array of Persons
     */
    public ArrayList<Person> readPersonsFamily(String userID) throws SQLException
    {
        ArrayList<Person> queryPersons = new ArrayList();

        try {
            openConnection();
            //this would definitely have to be recursive if
            String sql = "select PersonID, Descendant, FirstName, LastName, Gender, FatherID, MotherID, SpouseID from Persons" +
                    " where Persons.Descendant = ?";

            stmt = connection.prepareStatement(sql);
            stmt.setString(1, userID);

            keyRS = stmt.executeQuery();


            while (keyRS.next()) {
                Person person = new Person();
                person.setPersonID(keyRS.getString(1));
                person.setDescendant(keyRS.getString(2));
                person.setFirstName(keyRS.getString(4));
                person.setLastName(keyRS.getString(5));
                person.setGender(keyRS.getString(6));
                person.setFatherID(keyRS.getString(7));
                person.setMotherID(keyRS.getString(7));
                person.setSpouseID(keyRS.getString(7));

                queryPersons.add(person);
            }

            if (queryPersons.size() > 0) {
                System.out.print("Family members found!");
            }
            else
            {
                System.out.print("No family members found!");
            }
            closeConnection(true);

        }
        catch(SQLException e)
        {
            System.out.print(e.getMessage());
            closeConnection(false);
        }
        catch(Exception e)
        {
            System.out.print(e.getMessage());
            closeConnection(false);
        }
        finally
        {
            if (stmt != null) stmt.close();
            if (keyRS != null) keyRS.close();
            if (keyStmt != null) keyStmt.close();
        }

        //return
        if(queryPersons.size() > 0)
        {
            return queryPersons;
        }
        return null;
    }
}


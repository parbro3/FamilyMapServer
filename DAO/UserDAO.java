package DAO;

import Model.User;

/**
 * Represents an User data access object
 * Methods access User table in database.
 */

public class UserDAO {


    /**
     * Empty constructor for Gson compatibility
     */
    public UserDAO(){}

    /**
     * Takes in a Model User object with all information
     * and inserts the information into the database. The function returns
     * true if the method succeeded.
     * @param user Model User
     * @return true if the user insert succeeded
     */
    public Boolean createUser(User user)
    {
        return true;
    }

    /**
     * Takes in a userName as a string and checks the database for that user
     * which is the primary key. The function returns the User object.
     * @param userName String username
     * @return returns Model User if user is found in database
     */
    public User readUser(String userName)
    {
        return null;
    }

    /**
     * Deletes all users from database and returns a true boolean if the request succeeded
     * @return returns true if the request succeeded
     */
    public Boolean deleteAllUsers()
    {
        return true;
    }

    /**
     * Takes in a username string to delete a specific user from the database
     * @param username string is passed in
     * @return Returns a true boolean if the user was deleted
     */
    public Boolean deleteUser(String username)
    {
        return true;
    }

    /*
    UserDAO
    booleanMaybe? addUser(User u)

    User findUser(String userName);
    CRUD

    boolean deleteAllUsers();

    don't have single delete or update functions...

    //get everything you anticipate having as methods in the javadocs
     */


    /*

        User Table:
        username - primary_key (this column must be unique)... when trying to find... do so by username because it's a primary key
        password
        email
        ...
        personID - Foreign_key (used to access other tables)


        Person Table:
        Descendant - foreign_key (this is an owner)

        Event Table:
        event_id - primary key
        person_id - foreign key

        AuthToken table:
        AuthID - PK
        userName - FK


     */
}

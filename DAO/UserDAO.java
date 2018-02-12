package DAO;

/**
 * Created by Parker on 2/10/18.
 */

public class UserDAO {


    public UserDAO(){}

    public void createUser()
    {

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

package Model;

import java.util.UUID;

/**
 * Represents a Model class Person in memory
 * Only getters and setters because it's a shared dataholder object
 */

public class Person {

    /**
     * Person's owner/descendant
     */
    String descendant;
    /**
     * Person's first name
     */
    String firstName;
    /**
     * Person's last name
     */
    String lastName;
    /**
     * Person's gender (m or f)
     */
    String gender;
    /**
     * Person's father's ID
     */
    String fatherID;
    /**
     * Person's mother's ID
     */
    String motherID;
    /**
     * Person's spouse's ID
     */
    String spouseID;

    String personID;

    /**
     * Empty constructor to be accessed by Gson
     */
    public Person(){}

    //personID needs to be generated.. doesn't include father, mother, spouse id because they are optional
    public Person(String descendant, String firstName, String lastName, String gender)
    {
        this.descendant = descendant;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.setPersonID(this.generateID());
    }

    //with already created personID.. doesn't include father, mother, spouse id because they are optional
    public Person(String descendant, String firstName, String lastName, String gender, String personID)
    {
        this.descendant = descendant;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.setPersonID(personID);
    }

    public String getDescendant() {
        return descendant;
    }

    public void setDescendant(String descendant) {
        this.descendant = descendant;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getFatherID() {
        return fatherID;
    }

    public void setFatherID(String fatherID) {
        this.fatherID = fatherID;
    }

    public String getMotherID() {
        return motherID;
    }

    public void setMotherID(String motherID) {
        this.motherID = motherID;
    }

    public String getSpouseID() {
        return spouseID;
    }

    public void setSpouseID(String spouseID) {
        this.spouseID = spouseID;
    }

    public String generateID()
    {
        return UUID.randomUUID().toString();
    }

    public String getPersonID() { return personID; }

    public void setPersonID(String ID) {
        this.personID = ID;
    }
}

package Service.Result;

/**
 * Represents the PersonID Result object with the appropriate message and data
 * depending on the outcome of the PersonID service.
 */

public class PersonIDResult  implements Result{

    /**
     * PersonIDResult's descendant
     */
    String descendant;
    /**
     * PersonIDResult's personID
     */
    String personID;
    /**
     * PersonIDResult's first name
     */
    String firstName;
    /**
     * PersonIDResult's last name
     */
    String lastName;
    /**
     * PersonIDResult's gender (m or f)
     */
    String gender;
    /**
     * PersonIDResult's father's id (UUID)
     */
    String fatherID;
    /**
     * PersonIDResult's mother's id (UUID)
     */
    String motherID;
    /**
     * PersonIDResult's spouse's id (UUID)
     */
    String spouseID;
    /**
     * PersonIDResult's error message
     */
    String errorMessage;

    /**
     * Empty constructor
     */
    public PersonIDResult() {
    }

    public String getDescendant() {
        return descendant;
    }

    public void setDescendant(String descendant) {
        this.descendant = descendant;
    }

    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
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

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}

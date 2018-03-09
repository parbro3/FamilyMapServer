package Model;

import java.util.UUID;

/**
 * Currently unused.
 * Model class that model classes inherit from for ID generation
 */

public class Model {

    private String ID;

    public String generateID()
    {
        return UUID.randomUUID().toString();
    }

    public String getID() { return ID; }

    public void setID(String ID) {
        this.ID = ID;
    }

}

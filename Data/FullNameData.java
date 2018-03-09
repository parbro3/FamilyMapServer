package Data;

/**
 * FullNameData class that has all names. read in from gson.
 */

public class FullNameData {
    FemaleNames firstNames;
    LastName lastNames;
    MaleNames maleNames;

    public FemaleNames getFirstNames() {
        return firstNames;
    }

    public void setFirstNames(FemaleNames firstNames) {
        this.firstNames = firstNames;
    }

    public LastName getLastNames() {
        return lastNames;
    }

    public void setLastNames(LastName lastNames) {
        this.lastNames = lastNames;
    }

    public MaleNames getMiddleNames() {
        return maleNames;
    }

    public void setMiddleNames(MaleNames middleNames) {
        this.maleNames = middleNames;
    }
}

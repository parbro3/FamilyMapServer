package Service.Result;

/**
 * Represents the Clear Result object with the appropriate message
 * depending on the outcome of the clear service.
 */

public class ClearResult implements Result {

    /**
     * ClearResult's success message
     */
    String successMessage;
    /**
     * ClearResult's error message
     */
    String errorMessage;


    /**
     * Empty constructor
     */
    public ClearResult() {
    }

    public String getSuccessMessage() {
        return successMessage;
    }

    public void setSuccessMessage(String successMessage) {
        this.successMessage = successMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}

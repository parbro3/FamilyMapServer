package Service.Result;

/**
 * Represents the Fill Result object with the appropriate message
 * depending on the outcome of the Fill service.
 */

public class FillResult implements Result {


    /**
     * FillResult's success message
     */
    String successMessage;
    /**
     * FillResult's error message
     */
    String errorMessage;

    /**
     * Empty constructor
     */
    public FillResult() {
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

package Service.Result;

/**
 * Represents the Load Result object with the appropriate message
 * depending on the outcome of the Load service.
 */
public class LoadResult implements Result {

    /**
     * LoadResult's success message
     */
    String successMessage;
    /**
     * LoadResult's error message
     */
    String errorMessage;


    /**
     * Empty constructor
     */
    public LoadResult() {
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

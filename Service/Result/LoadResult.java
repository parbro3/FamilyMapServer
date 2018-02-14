package Service.Result;

/**
 * Represents the Load Result object with the appropriate message
 * depending on the outcome of the Load service.
 */
public class LoadResult {

    String successMessage;
    String errorMessage;

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

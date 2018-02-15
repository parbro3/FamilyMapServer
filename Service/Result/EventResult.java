package Service.Result;

/**
 * Represents the Event Result object with the appropriate message
 * depending on the outcome of the Event service.
 */
public class EventResult {

    String errorMessage;
    String successMessage;

    public EventResult() {
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getSuccessMessage() {
        return successMessage;
    }

    public void setSuccessMessage(String successMessage) {
        this.successMessage = successMessage;
    }
}

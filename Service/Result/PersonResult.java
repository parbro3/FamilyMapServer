package Service.Result;

/**
 * Represents the Person Result object with the appropriate message
 * depending on the outcome of the Person service.
 */

public class PersonResult  implements Result{

    /**
     * PersonResult's success message
     */
    String successMessage;
    /**
     * PersonResult's error message
     */
    String errorMessage;

    public PersonResult() {
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

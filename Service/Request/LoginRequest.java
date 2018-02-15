package Service.Request;

/**
 * Represents a request object to clear the database
 */


public class LoginRequest {

    String userName;
    String passWord;

    public LoginRequest() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
}

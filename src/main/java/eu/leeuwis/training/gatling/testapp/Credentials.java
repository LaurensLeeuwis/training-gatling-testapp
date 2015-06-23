package eu.leeuwis.training.gatling.testapp;

public class Credentials {

    private String username;
    private String password;

    private static final String CORRECT_USERNAME = "myUsername";
    private static final String CORRECT_PASSWORD = "myPassword";

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isValid() {
        return CORRECT_USERNAME.equals(username) && CORRECT_PASSWORD.equals(password);
    }
}

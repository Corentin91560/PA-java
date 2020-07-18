package sample.Class;

public class Admin {
    private String login;
    private String error;

    public Admin() {}

    public Admin(String login, String error) {
        this.login = login;
        this.error = error;
    }

    public String getLogin() {
        return login;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}

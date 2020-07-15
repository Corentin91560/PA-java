package sample.Class;

public class Admin {

    private String login;
    private String password;
    private int idadmin;
    private String email;
    private String error;

    public Admin(String login, String password, int idadmin, String email, String error) {
        this.login = login;
        this.password = password;
        this.idadmin = idadmin;
        this.email = email;
        this.error = error;
    }

    public Admin(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getIda() {
        return idadmin;
    }

    public void setIda(int idadmin) {
        this.idadmin = idadmin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}

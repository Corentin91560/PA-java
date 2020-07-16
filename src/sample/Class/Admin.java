package sample.Class;

public class Admin {

    private String login;
    private String password;
    private int idAdmin;
    private String email;
    private String error;

    public Admin() {

    }

    public Admin(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public Admin(String login, String password, int idAdmin, String email, String error) {
        this.login = login;
        this.password = password;
        this.idAdmin = idAdmin;
        this.email = email;
        this.error = error;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setIdAdmin(int idadmin) {
        this.idAdmin = idadmin;
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

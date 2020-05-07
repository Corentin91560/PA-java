package sample.Class;

public class User {
    String login;
    String password;
    int ida;
    String email;
    String error;

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public User(String login, String password, int ida, String email,String error) {
        this.login = login;
        this.password = password;
        this.ida = ida;
        this.email = email;
        this.error = error;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public int getIda() {
        return ida;
    }

    public void setIda(int ida) {
        this.ida = ida;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
}

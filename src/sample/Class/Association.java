package sample.Class;

public class Association {
    int idas;
    String name;
    String logo;
    String email;
    String phone;

    public Association(int idas, String name, String logo, String email, String phone) {
        this.idas = idas;
        this.name = name;
        this.logo = logo;
        this.email = email;
        this.phone = phone;
    }

    public Association(int idas) {
        this.idas = idas;
    }

    public int getIdas() {
        return idas;
    }

    public void setIdas(int idas) {
        this.idas = idas;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}

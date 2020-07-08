package sample.Class;

public class User {
int idu;
String name;
String firstname;
String email;
String phone;
String profilpicture;

    public User(int idu, String name, String firstname, String email, String phone, String profilpicture) {
        this.idu = idu;
        this.name = name;
        this.firstname = firstname;
        this.email = email;
        this.phone = phone;
        this.profilpicture = profilpicture;
    }

    public User(int idu) {
        this.idu = idu;
    }

    public int getIdu() {
        return idu;
    }

    public void setIdu(int idu) {
        this.idu = idu;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
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

    public String getProfilpicture() {
        return profilpicture;
    }

    public void setProfilpicture(String profilpicture) {
        this.profilpicture = profilpicture;
    }

    @Override
    public String toString() {
        return "User{" +
                "idu=" + idu +
                ", name='" + name + '\'' +
                ", firstname='" + firstname + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}

package sample.Class;

public class User {
int iduser;
String name;
String firstname;
String email;
String phone;
String profilpicture;

    public User(int iduser, String name, String firstname, String email, String phone, String profilpicture) {
        this.iduser = iduser;
        this.name = name;
        this.firstname = firstname;
        this.email = email;
        this.phone = phone;
        this.profilpicture = profilpicture;
    }

    public User(int iduser) {
        this.iduser = iduser;
    }

    public int getIdu() {
        return iduser;
    }

    public void setIdu(int iduser) {
        this.iduser = iduser;
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
                "iduser=" + iduser +
                ", name='" + name + '\'' +
                ", firstname='" + firstname + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}

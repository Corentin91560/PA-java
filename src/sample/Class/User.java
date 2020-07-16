package sample.Class;

public class User {
    private int idUser;
    private String name;
    private String firstName;
    private String email;
    private String phone;
    private String profilPicture;

    public User(int idUser) {
        this.idUser = idUser;
    }

    public void setIdUser(int iduser) {
        this.idUser = iduser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
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

    public String getProfilPicture() {
        return profilPicture;
    }

    public void setProfilPicture(String profilPicture) {
        this.profilPicture = profilPicture;
    }

    @Override
    public String toString() {
        return "User{" +
                "iduser=" + idUser +
                ", name='" + name + '\'' +
                ", firstname='" + firstName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}

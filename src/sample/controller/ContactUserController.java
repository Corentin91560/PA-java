package sample.controller;

import javafx.fxml.FXML;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import sample.Class.User;

public class ContactUserController {
    @FXML
    Label namecontact;
    @FXML
    Label firstnamecontact;
    @FXML
    Label emailcontact;
    @FXML
    Label phonecontact;
    @FXML
    ImageView imagecontact;

    public void initialize() {

    }

    public void transferMessage(User user) {
        namecontact.setText(user.getName());
        firstnamecontact.setText(user.getFirstname());
        emailcontact.setText(user.getEmail());
        phonecontact.setText(user.getPhone());
        Image image = new Image(user.getProfilpicture());
        imagecontact.setImage(image);
    }
}


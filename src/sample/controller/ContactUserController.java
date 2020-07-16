package sample.controller;

import javafx.fxml.FXML;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import sample.Class.User;

public class ContactUserController {
    @FXML
    Label userNameLabel;
    @FXML
    Label userFirstnameLabel;
    @FXML
    Label userEmailLabel;
    @FXML
    Label userPhoneLabel;
    @FXML
    ImageView userPictureImageView;

    public void initialize() {

    }

    public void transferMessage(User user) {
        Image image = new Image(user.getProfilPicture());

        userNameLabel.setText(user.getName());
        userFirstnameLabel.setText(user.getFirstName());
        userEmailLabel.setText(user.getEmail());
        userPhoneLabel.setText(user.getPhone());
        userPictureImageView.setImage(image);
    }
}


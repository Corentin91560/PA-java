package sample.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import sample.Class.Association;

public class ContactAssoController {
    @FXML
    Label assoNameLabel;
    @FXML
    Label assoEmailLabel;
    @FXML
    Label assoPhoneLabel;
    @FXML
    ImageView assoLogoImageView;

    public void initialize() {

    }

    public void transferMessage(Association association) {
        Image image = new Image(association.getLogo());

        assoNameLabel.setText(association.getName());
        assoEmailLabel.setText(association.getEmail());
        assoPhoneLabel.setText(association.getPhone());
        assoLogoImageView.setImage(image);
    }



}

package sample.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import sample.Class.Association;

public class ContactAssoController {
    @FXML
    Label nameassocontact;
    @FXML
    Label emailassocontact;
    @FXML
    Label phoneassocontact;
    @FXML
    ImageView logoassocontact;

    public void initialize(){

    }

    public void transferMessage(Association association) {
        nameassocontact.setText(association.getName());
        emailassocontact.setText(association.getEmail());
        phoneassocontact.setText(association.getPhone());
        Image image = new Image(association.getLogo());
        logoassocontact.setImage(image);
    }



}

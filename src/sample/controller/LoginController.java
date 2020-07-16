package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import sample.Class.Admin;
import sample.Tools;
import sample.api.ApiCaller;

public class LoginController {

    static Admin connectedAdmin;

    @FXML
    private TextField loginTF;
    @FXML
    private PasswordField passwordTF;
    @FXML
    private Label errorLabel;

    public void connect(ActionEvent actionEvent) {
        try {
            ApiCaller caller = ApiCaller.getInstance();
            LoginController.connectedAdmin = caller.signInAdmin(loginTF.getText(), passwordTF.getText());
            if (connectedAdmin.getError() == null) {
                try {
                    ChangeSceneController controller = new ChangeSceneController();
                    controller.changeScene("../ressource/home.fxml", actionEvent);

                } catch (Exception e) {
                    Tools.showError(e);
                }
            } else {
                errorLabel.setVisible(true);
            }

        } catch (Exception e) {
           Tools.showError(e);
        }
    }
}

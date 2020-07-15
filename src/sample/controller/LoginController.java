package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import sample.Class.Admin;
import sample.api.ApiCaller;

public class LoginController {

    static Admin currentAdmin;

    @FXML
    private TextField login_tf;
    @FXML
    private PasswordField password_tf;
    @FXML
    private Label labelerror;

    public void connect(ActionEvent actionEvent) {
        Admin tryAdmin = new Admin(login_tf.getText(), password_tf.getText());

        try {
            ApiCaller caller = ApiCaller.getInstance();
            setCurrentAdmin(caller.signInAdmin(tryAdmin));
            if (currentAdmin.getError() == null) {

                try {
                    ChangeSceneController controller = new ChangeSceneController();
                    controller.changeScene("../ressource/home.fxml", actionEvent);

                } catch (Exception e) {
                    e.printStackTrace();
                }

            } else {
                labelerror.setVisible(true);
            }

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText("serveur non disponible verifier la connexion ");
            alert.showAndWait();
        }
    }

    private void setCurrentAdmin(Admin currentAdmin) {
        LoginController.currentAdmin = currentAdmin;
    }

}

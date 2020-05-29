package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import sample.Class.User;
import sample.api.ApiCaller;

import java.util.Optional;

public class LoginController {

    @FXML
    private TextField login_tf;
    @FXML private PasswordField password_tf;


    public void connect(ActionEvent actionEvent) {
        User currentUser = new User(login_tf.getText(), password_tf.getText());

        try {

            ApiCaller caller = ApiCaller.getInstance();
            User resultconn = caller.signInUser(currentUser);
            if (resultconn.getError()==null){

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    System.out.println(actionEvent);
                    alert.setTitle("Confirmation Dialog");
                    alert.setHeaderText("Welcome " + currentUser.getLogin());
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == ButtonType.OK) {
                        try {
                            ChangeSceneController controller = new ChangeSceneController();
                            controller.changeScene("../ressource/home.fxml", actionEvent);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {
                        System.out.println("nop");
                    }

            }else{
                Alert alerterror = new Alert(Alert.AlertType.INFORMATION);
                System.out.println(actionEvent);
                alerterror.setTitle("Confirmation Dialog");
                alerterror.setHeaderText(currentUser.getError());
                alerterror.showAndWait();
            }


        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            System.out.println(actionEvent);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText("serveur non disponible verifier la connection ");
            alert.showAndWait();
            System.out.println(e);
        }
    }

}

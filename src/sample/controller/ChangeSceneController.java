package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Window;

public class ChangeSceneController {
    public void changeScene(String fxmlfile, ActionEvent actionEvent){

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlfile));
            Parent root = (Parent) fxmlLoader.load();
            //<editor-fold desc="change scene from current stage">
            Node source = (Node) actionEvent.getSource();
            Window theStage = source.getScene().getWindow();
            Stage currentStage = (Stage)theStage.getScene().getWindow();
            currentStage.setScene(new Scene(root));
            currentStage.show();
            //</editor-fold>
        } catch(Exception e) {
            e.printStackTrace();
        }

    }

}

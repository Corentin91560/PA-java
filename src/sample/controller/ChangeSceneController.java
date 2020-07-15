package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

class ChangeSceneController {
    void changeScene(String fxmlfile, ActionEvent actionEvent){

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlfile));
            Parent root = fxmlLoader.load();
            //<editor-fold desc="change scene from current stage">
            Node source = (Node) actionEvent.getSource();
            Stage currentStage = (Stage)source.getScene().getWindow();
            currentStage.setScene(new Scene(root));
            currentStage.show();
            //</editor-fold>
        } catch(Exception e) {
            e.printStackTrace();
        }

    }

}

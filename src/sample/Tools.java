package sample;

import javafx.scene.control.Alert;

public class Tools {
    public static void showError(Exception e) {
        Alert alertError = new Alert(Alert.AlertType.WARNING);
        alertError.setTitle("ERREUR");
        alertError.setHeaderText("L'application a rencontré une erreur :\n" + e);
        alertError.showAndWait();
    }
}

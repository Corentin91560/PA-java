package sample.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Side;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import sample.Class.Feedback;
import sample.api.ApiCaller;

import java.util.List;
import java.util.Optional;

public class StatController {
    @FXML
            PieChart chartbug;
    @FXML
    Label caption;
    private int statuspending=0;
    private int statusvalidate=0;
    private int statusnottreated=0;

    public void initialize(){
        SetChartData();
    }

    private void SetChartData() {

        ApiCaller caller = ApiCaller.getInstance();
        List<Feedback> listbug = caller.Returnbug();
        int i = 0;
        while (i < listbug.size()) {
            if (listbug.get(i).getStatus().equals("pending")) {
                statuspending += 1;
            }
            if (listbug.get(i).getStatus().equals("validate")) {
                statusvalidate += 1;
            }
            if (listbug.get(i).getStatus().equals("")) {
                statusnottreated += 1;
            }
            i = i + 1;
        }

        System.out.println("pending " + statuspending);
        System.out.println("valide " + statusvalidate);
        System.out.println("not treated " + statusnottreated);

        PieChart.Data slice2 = new PieChart.Data("pending", statuspending);
        PieChart.Data slice3 = new PieChart.Data("implemented", statusvalidate);
        PieChart.Data slice1 = new PieChart.Data("not treated", statusnottreated);

        chartbug.getData().add(slice1);
        chartbug.getData().add(slice2);
        chartbug.getData().add(slice3);
        chartbug.setLegendSide(Side.LEFT);

        caption.setText("");
        caption.setTextFill(Color.BLACK);
        caption.setStyle("-fx-font: 12 arial;");

        for (final PieChart.Data data : chartbug.getData()) {
            data.getNode().addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    caption.setTranslateX(e.getSceneX()-150);
                    caption.setTranslateY(e.getSceneY()-100);
                    caption.setText(String.valueOf((int) data.getPieValue()));
                }
            });
        }

    }


    public void Disconnect(ActionEvent actionEvent) {


        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Deconnection");
        alert.setContentText("Etes vous sur de vouloir vous deconnecté ?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            try {
                ChangeSceneController controller = new ChangeSceneController();
                controller.changeScene("../ressource/login.fxml", actionEvent);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            alert.close();
        }

    }

    public void GoToHome(ActionEvent actionEvent) {
        ChangeSceneController controller = new ChangeSceneController();
        controller.changeScene("../ressource/home.fxml", actionEvent);
    }
}

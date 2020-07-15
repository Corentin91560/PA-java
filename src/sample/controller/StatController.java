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

    @FXML
    PieChart chartimp;
    @FXML
    Label captionimp;
    private int note0=0;
    private int note1=0;
    private int note2=0;
    private int note3=0;
    private int note4=0;
    private int note5=0;


    public void initialize(){
        SetChartDataBug();
        SetChartDataimp();
    }

    private void SetChartDataBug() {

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

        PieChart.Data slice2 = new PieChart.Data("En cours", statuspending);
        PieChart.Data slice3 = new PieChart.Data("Implementé", statusvalidate);
        PieChart.Data slice1 = new PieChart.Data("Non traité", statusnottreated);

        chartbug.getData().add(slice1);
        chartbug.getData().add(slice2);
        chartbug.getData().add(slice3);
        chartbug.setLegendSide(Side.LEFT);

        caption.setText("");
        caption.setTextFill(Color.BLACK);

        for (final PieChart.Data data : chartbug.getData()) {
            data.getNode().addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    caption.setTranslateX(e.getSceneX() - 150);
                    caption.setTranslateY(e.getSceneY() - 100);
                    caption.setText(String.valueOf((int) data.getPieValue()));
                }
            });
        }

    }

    private void SetChartDataimp() {

        ApiCaller caller = ApiCaller.getInstance();
        List<Feedback> listimp = caller.Returnimprove();
        int i = 0;
        while (i < listimp.size()) {
            if (listimp.get(i).getNote()==0) {
                note0 += 1;
            }
            if (listimp.get(i).getNote()==1) {
                note1 += 1;
            }
            if (listimp.get(i).getNote()==2) {
                note2 += 1;
            }
            if (listimp.get(i).getNote()==3) {
                note3 += 1;
            }
            if (listimp.get(i).getNote()==4) {
                note4 += 1;
            }
            if (listimp.get(i).getNote()==5) {
                note5 += 1;
            }
            i = i + 1;
        }

        PieChart.Data slice0 = new PieChart.Data("0 Etoile", note0);
        PieChart.Data slice1 = new PieChart.Data("1 Etoile", note1);
        PieChart.Data slice2 = new PieChart.Data("2 Etoiles", note2);
        PieChart.Data slice3 = new PieChart.Data("3 Etoiles", note3);
        PieChart.Data slice4 = new PieChart.Data("4 Etoiles", note4);
        PieChart.Data slice5 = new PieChart.Data("5 Etoiles", note5);

        chartimp.getData().add(slice0);
        chartimp.getData().add(slice1);
        chartimp.getData().add(slice2);
        chartimp.getData().add(slice3);
        chartimp.getData().add(slice4);
        chartimp.getData().add(slice5);
        chartimp.setLegendSide(Side.LEFT);

        captionimp.setText("");
        captionimp.setTextFill(Color.BLACK);

        for (final PieChart.Data data : chartimp.getData()) {
            data.getNode().addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    captionimp.setTranslateX(e.getSceneX() - 150);
                    captionimp.setTranslateY(e.getSceneY() - 100);
                    captionimp.setText(String.valueOf((int) data.getPieValue()));
                }
            });
        }
    }


    public void Disconnect(ActionEvent actionEvent) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Déconnexion");
        alert.setContentText("Etes vous sur de vouloir vous déconnecter ?");
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

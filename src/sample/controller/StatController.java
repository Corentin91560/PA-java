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
import sample.Tools;
import sample.api.ApiCaller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class StatController {
    @FXML
    PieChart bugPieChart;
    @FXML
    PieChart ratingPieChart;
    @FXML
    Label bugElementCountLabel;
    @FXML
    Label ratingElementCountLabel;

    private int pendingStatus = 0;
    private int validateStatus = 0;
    private int untreatedStatus = 0;
    private final List<Integer> notes = Arrays.asList(0, 0, 0, 0, 0, 0);

    public void initialize() {
        SetChartDataBug();
        SetChartDataimp();
    }

    private void SetChartDataBug() {
        ApiCaller caller = ApiCaller.getInstance();
        List<Feedback> bugList = caller.getBugs();
        int i = 0;

        while (i < bugList.size()) {
            if (bugList.get(i).getStatus().equals("pending")) pendingStatus += 1;
            if (bugList.get(i).getStatus().equals("validate")) validateStatus += 1;
            if (bugList.get(i).getStatus().equals("")) untreatedStatus += 1;
            i = i + 1;
        }

        PieChart.Data sliceNotTreated = new PieChart.Data("Non traité", untreatedStatus);
        PieChart.Data slicePending = new PieChart.Data("En cours", pendingStatus);
        PieChart.Data sliceValidate = new PieChart.Data("Implementé", validateStatus);

        if (untreatedStatus > 0) bugPieChart.getData().add(sliceNotTreated);
        if (pendingStatus > 0) bugPieChart.getData().add(slicePending);
        if (validateStatus > 0) bugPieChart.getData().add(sliceValidate);

        bugPieChart.setLegendSide(Side.LEFT);
        bugElementCountLabel.setText("");
        bugElementCountLabel.setTextFill(Color.BLACK);

        for (final PieChart.Data data : bugPieChart.getData()) {
            data.getNode().addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    bugElementCountLabel.setTranslateX(e.getSceneX() - 150);
                    bugElementCountLabel.setTranslateY(e.getSceneY() - 100);
                    bugElementCountLabel.setText(String.valueOf((int) data.getPieValue()));
                }
            });
        }
    }

    private void SetChartDataimp() {
        ApiCaller caller = ApiCaller.getInstance();
        List<Feedback> ratingList = caller.getRatings();
        int i = 0;

        while (i < ratingList.size()) {
            if (ratingList.get(i).getNote() == 0) {
                notes.set(0, notes.get(0) + 1);
            }
            if (ratingList.get(i).getNote()==1) {
                notes.set(1, notes.get(1) + 1);
            }
            if (ratingList.get(i).getNote()==2) {
                notes.set(2, notes.get(2) + 1);
            }
            if (ratingList.get(i).getNote()==3) {
                notes.set(3, notes.get(3) + 1);
            }
            if (ratingList.get(i).getNote()==4) {
                notes.set(4, notes.get(4) + 1);
            }
            if (ratingList.get(i).getNote()==5) {
                notes.set(5, notes.get(5) + 1);
            }
            i += 1;
        }

        PieChart.Data slice0 = new PieChart.Data("0 / 5 Etoile", notes.get(0));
        PieChart.Data slice1 = new PieChart.Data("1 / 5 Etoile", notes.get(1));
        PieChart.Data slice2 = new PieChart.Data("2 / 5 Etoiles", notes.get(2));
        PieChart.Data slice3 = new PieChart.Data("3 / 5 Etoiles", notes.get(3));
        PieChart.Data slice4 = new PieChart.Data("4 / 5 Etoiles", notes.get(4));
        PieChart.Data slice5 = new PieChart.Data("5 / 5 Etoiles", notes.get(5));

        if (notes.get(0) > 0) ratingPieChart.getData().add(slice0);
        if (notes.get(1) > 0) ratingPieChart.getData().add(slice1);
        if (notes.get(2) > 0) ratingPieChart.getData().add(slice2);
        if (notes.get(3) > 0) ratingPieChart.getData().add(slice3);
        if (notes.get(4) > 0) ratingPieChart.getData().add(slice4);
        if (notes.get(5) > 0) ratingPieChart.getData().add(slice5);

        ratingPieChart.setLegendSide(Side.LEFT);
        ratingElementCountLabel.setText("");
        ratingElementCountLabel.setTextFill(Color.BLACK);

        for (final PieChart.Data data : ratingPieChart.getData()) {
            data.getNode().addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    ratingElementCountLabel.setTranslateX(e.getSceneX() - 150);
                    ratingElementCountLabel.setTranslateY(e.getSceneY() - 100);
                    ratingElementCountLabel.setText(String.valueOf((int) data.getPieValue()));
                }
            });
        }
    }

    public void Disconnect(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Déconnexion");
        alert.setHeaderText("Etes vous sur de vouloir vous déconnecter ?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.OK) {
            try {
                ChangeSceneController controller = new ChangeSceneController();
                controller.changeScene("../ressource/login.fxml", actionEvent);

            } catch (Exception e) {
                Tools.showError(e);
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

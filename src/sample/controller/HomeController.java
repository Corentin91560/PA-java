package sample.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sample.Class.*;
import sample.api.ApiCaller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public class HomeController {

    private List<Feedback> listimprove;
    private int selected;
    private int selectedimp;
    private List<Feedback> listbug;
    private Admin currentAdmin = LoginController.currentAdmin;
    @FXML
    private ListView<String> buglist;
    @FXML
    private ListView<String> improvelist;
    @FXML
    private TextField tftitlebug;
    @FXML
    private TextArea tfcontentbug;
    @FXML
    private TextField tfstatusbug;
    @FXML
    private TextField tfplatebug;
    @FXML
    private TextField tfnoteimp;
    @FXML
    private TextArea tfcontentimp;
    @FXML
    private TextField tfplateimp;
    @FXML
    private TextArea content_news_tf;
    @FXML
    private TextField title_news_tf;
    @FXML
    private Button btnaddtrello;
    @FXML
    private Button btnvalidate;
    @FXML
    private Button contactuserbtn;

    private ObservableList<String> observableBugList = FXCollections.observableArrayList();
    private ObservableList<String> observableImproveList = FXCollections.observableArrayList();


    public void initialize() {
        init();
    }

    public void init() {

        ApiCaller caller = ApiCaller.getInstance();
        listbug = caller.Returnbug();
        int i = 0;
        observableBugList.clear();

        while (i < listbug.size()) {
            observableBugList.add(listbug.get(i).getDate().substring(0,10) + " - " + listbug.get(i).getTitle());
            i += 1;
        }

        buglist.setItems(observableBugList);
        buglist.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                selected = buglist.getSelectionModel().getSelectedIndices().get(0);
                tfcontentbug.setText(listbug.get(selected).getContent());
                tfstatusbug.setText(listbug.get(selected).getStatus());

                if (listbug.get(selected).getStatus().equals("pending")) {
                    btnaddtrello.setDisable(true);
                    btnvalidate.setDisable(false);

                } else if (listbug.get(selected).getStatus().equals("validate")) {
                    btnaddtrello.setDisable(true);
                    btnvalidate.setDisable(true);

                } else {
                    btnaddtrello.setDisable(false);
                    btnvalidate.setDisable(true);
                }
                tftitlebug.setText(listbug.get(selected).getTitle() + " #" + listbug.get(selected).getIdfe());
                tfplatebug.setText(listbug.get(selected).getPlateform());
            }
        });

        listimprove = caller.Returnimprove();
        int j = 0;
        observableImproveList.clear();

        while (j < listimprove.size()) {
            observableImproveList.add(listimprove.get(j).getContent());
            j = j + 1;
        }
        improvelist.setItems(observableImproveList);

        improvelist.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                contactuserbtn.setDisable(false);
                selectedimp = improvelist.getSelectionModel().getSelectedIndices().get(0);
                tfnoteimp.setText(String.valueOf(listimprove.get(selectedimp).getNote()));
                tfcontentimp.setText(listimprove.get(selectedimp).getContent());
                tfplateimp.setText(listimprove.get(selectedimp).getPlateform());
            }
        });
    }

    public void AddBugToTrello(ActionEvent actionEvent) {

        ApiCaller caller = ApiCaller.getInstance();
        String information = caller.BugToTrello(currentAdmin, listbug.get(selected), tfcontentbug.getText());
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(information);
        alert.showAndWait();
        init();
    }

    public void Contact(ActionEvent actionEvent) {
        ApiCaller caller = ApiCaller.getInstance();
        System.out.println(listimprove.get(selectedimp).getIdu());

        if (listimprove.get(selectedimp).getIdu() == 0){
            Association detailasso = caller.getAssoInfo(listimprove.get(selectedimp).getIdas());

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../ressource/contactasso.fxml"));

                Stage stage = new Stage();
                stage.setTitle("Contact Association");
                stage.setScene(new Scene(loader.load(), 300, 300));
                ContactAssoController contactAssoController = loader.getController();
                contactAssoController.transferMessage(detailasso);
                stage.show();
            }
            catch (IOException e) {
                Alert alertError = new Alert(Alert.AlertType.WARNING);
                alertError.setTitle("ERREUR");
                alertError.setHeaderText("L'application à rencontrer une erreur :\n" + e);
                alertError.showAndWait();
            }

        } else {
            User detailuser = caller.getUserInfo(listimprove.get(selectedimp).getIdu());

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../ressource/contactuser.fxml"));

                Stage stage = new Stage();
                stage.setTitle("Contact Utilisateur");
                stage.setScene(new Scene(loader.load(), 300, 300));
                ContactUserController contactController = loader.getController();
                contactController.transferMessage(detailuser);
                stage.show();
            }
            catch (IOException e) {
                Alert alertError = new Alert(Alert.AlertType.WARNING);
                alertError.setTitle("ERREUR");
                alertError.setHeaderText("L'application à rencontrer une erreur :\n" + e);
                alertError.showAndWait();
            }
        }
    }
    public void ValidateBug(ActionEvent actionEvent) {
        ApiCaller caller = ApiCaller.getInstance();
        String information = caller.validatebug(listbug.get(selected));

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(information);
        alert.showAndWait();
        init();
    }

    public void GoToStat(ActionEvent actionEvent) {
        ChangeSceneController controller = new ChangeSceneController();
        controller.changeScene("../ressource/stat.fxml", actionEvent);
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
                Alert alertError = new Alert(Alert.AlertType.WARNING);
                alertError.setTitle("ERREUR");
                alertError.setHeaderText("L'application à rencontrer une erreur :\n" + e);
                alertError.showAndWait();
            }
        } else {
            alert.close();
        }

    }

    public void SendNews(ActionEvent actionEvent) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String datenow = formatter.format(new Date());

        News news = new News(title_news_tf.getText(), content_news_tf.getText(),datenow);

        ApiCaller caller = ApiCaller.getInstance();
        String information = caller.AddNews(news);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(information);
        alert.showAndWait();
    }
}

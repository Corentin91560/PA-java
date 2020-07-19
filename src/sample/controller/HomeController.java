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
import sample.Tools;
import sample.api.ApiCaller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public class HomeController {
    private List<Feedback> ratingList;
    private int selectedBugIndex;
    private int selectedRatingIndex;
    private List<Feedback> bugList;
    private final Admin connectedAdmin = LoginController.connectedAdmin;

    @FXML
    private ListView<String> bugListView;
    @FXML
    private TextField bugTitleTF;
    @FXML
    private TextArea bugContentTF;
    @FXML
    private TextField bugStatusTF;
    @FXML
    private TextField bugPlatformTF;
    @FXML
    private ListView<String> ratingListView;
    @FXML
    private TextField ratingNoteTF;
    @FXML
    private TextArea ratingContentTF;
    @FXML
    private TextField ratingPlatformTF;
    @FXML
    private TextField categoryNameTF;
    @FXML
    private TextArea newsContentTF;
    @FXML
    private TextField newsTitleTF;
    @FXML
    private Button addToTrelloButton;
    @FXML
    private Button validateButton;
    @FXML
    private Button contactUserButton;

    private final ObservableList<String> observableBugList = FXCollections.observableArrayList();
    private final ObservableList<String> observableRatingList = FXCollections.observableArrayList();

    public void initialize() {
        init();
    }

    public void init() {

        ApiCaller caller = ApiCaller.getInstance();
        bugList = caller.getBugs();
        int i = 0;
        observableBugList.clear();

        while (i < bugList.size()) {
            observableBugList.add(bugList.get(i).getDate().substring(0,10) + " - " + bugList.get(i).getTitle());
            i += 1;
        }

        bugListView.setItems(observableBugList);
        bugListView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                selectedBugIndex = bugListView.getSelectionModel().getSelectedIndices().get(0);
                bugContentTF.setText(bugList.get(selectedBugIndex).getContent());
                bugStatusTF.setText(bugList.get(selectedBugIndex).getStatus());

                if (bugList.get(selectedBugIndex).getStatus().equals("pending")) {
                    addToTrelloButton.setDisable(true);
                    validateButton.setDisable(false);

                } else if (bugList.get(selectedBugIndex).getStatus().equals("validate")) {
                    addToTrelloButton.setDisable(true);
                    validateButton.setDisable(true);

                } else {
                    addToTrelloButton.setDisable(false);
                    validateButton.setDisable(true);
                }
                bugTitleTF.setText(bugList.get(selectedBugIndex).getTitle() + " #" + bugList.get(selectedBugIndex).getIdfe());
                bugPlatformTF.setText(bugList.get(selectedBugIndex).getPlatform());
            }
        });

        ratingList = caller.getRatings();
        int j = 0;
        observableRatingList.clear();

        while (j < ratingList.size()) {
            observableRatingList.add(ratingList.get(j).getContent());
            j = j + 1;
        }
        ratingListView.setItems(observableRatingList);

        ratingListView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                selectedRatingIndex = ratingListView.getSelectionModel().getSelectedIndices().get(0);
                ratingNoteTF.setText(String.valueOf(ratingList.get(selectedRatingIndex).getNote()));
                ratingContentTF.setText(ratingList.get(selectedRatingIndex).getContent());
                ratingPlatformTF.setText(ratingList.get(selectedRatingIndex).getPlatform());
                contactUserButton.setDisable(false);
            }
        });
    }

    public void addBugToTrello() {
        ApiCaller caller = ApiCaller.getInstance();
        String information = caller.BugToTrello(connectedAdmin, bugList.get(selectedBugIndex), bugContentTF.getText());

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(information);
        alert.showAndWait();
        init();
    }

    public void Contact(ActionEvent actionEvent) {
        ApiCaller caller = ApiCaller.getInstance();

        if (ratingList.get(selectedRatingIndex).getIdu() == 0){
            Association detailAsso = caller.getAssoInfo(ratingList.get(selectedRatingIndex).getIdas());
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../ressource/contactasso.fxml"));
                Stage stage = new Stage();

                stage.setTitle("Contact Association");
                stage.setScene(new Scene(loader.load(), 300, 300));
                stage.setResizable(false);
                ContactAssoController contactAssoController = loader.getController();
                contactAssoController.transferMessage(detailAsso);
                stage.show();
            }
            catch (IOException e) {
                Tools.showError(e);
            }
        } else {
            User detailuser = caller.getUserInfo(ratingList.get(selectedRatingIndex).getIdu());
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../ressource/contactuser.fxml"));
                Stage stage = new Stage();

                stage.setTitle("Contact Utilisateur");
                stage.setScene(new Scene(loader.load(), 300, 300));
                stage.setResizable(false);
                ContactUserController contactController = loader.getController();
                contactController.transferMessage(detailuser);
                stage.show();
            }
            catch (IOException e) {
               Tools.showError(e);
            }
        }
    }
    public void ValidateBug(ActionEvent actionEvent) {
        ApiCaller caller = ApiCaller.getInstance();
        String information = caller.validatebug(bugList.get(selectedBugIndex));

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
                controller.changeScene("/sample/ressource/login.fxml", actionEvent);

            } catch (Exception e) {
                Tools.showError(e);
            }
        } else {
            alert.close();
        }
    }

    public void SendNews() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String now = formatter.format(new Date());
        News news = new News(newsTitleTF.getText(), newsContentTF.getText(), now);

        if(newsTitleTF.getText().isEmpty() || newsContentTF.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Attention !");
            alert.setHeaderText("Veuillez remplir tout les champs !");
            alert.showAndWait();
        } else {
            ApiCaller caller = ApiCaller.getInstance();
            String information = caller.AddNews(news);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(information);
            alert.showAndWait();
        }
    }

    public void sendCategory() {
        Category category = new Category(categoryNameTF.getText());

        if(categoryNameTF.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Attention !");
            alert.setHeaderText("Veuillez entrez un nom de categorie !");
            alert.showAndWait();
        } else {
            ApiCaller caller = ApiCaller.getInstance();
            String information = caller.AddCategory(category);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(information);
            alert.showAndWait();
        }

    }
}

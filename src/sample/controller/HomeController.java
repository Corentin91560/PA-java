package sample.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import sample.Class.Feedback;
import sample.Class.User;
import sample.api.ApiCaller;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class HomeController {

    List<Feedback> listimprove;
    int selected;
    List<Feedback> listbug;
    User currentUser = LoginController.currentUser;
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
    private TextField tftitleimp;
    @FXML
    private TextArea tfcontentimp;
    @FXML
    private TextField tfplateimp;
    @FXML
    private Button btnaddtrello;
    @FXML
    private Button btnvalidate;

    private ObservableList<String> observableBugList = FXCollections.observableArrayList();
    private ObservableList<String> observableImproveList = FXCollections.observableArrayList();


    public void initialize(){
        refresh();
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

    public void refresh(){

        ApiCaller caller = ApiCaller.getInstance();
        listbug = caller.Returnbug();
        int i=0;
        observableBugList.clear();
        while(i<listbug.size()){

            observableBugList.add(listbug.get(i).getDate().substring(0,10)+" - "+listbug.get(i).getTitle());
            i=i+1;
        }

        Comparator<String> comparator = Comparator.comparing(String::new);
        comparator = comparator.reversed();
        FXCollections.sort(observableBugList,comparator);

        buglist.setItems(observableBugList);
        buglist.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                System.out.println("clicked on " + buglist.getSelectionModel().getSelectedIndices());
                selected = buglist.getSelectionModel().getSelectedIndices().get(0);
                tfcontentbug.setText(listbug.get(selected).getContent());
                tfstatusbug.setText(listbug.get(selected).getStatus());
                if (listbug.get(selected).getStatus().equals("pending") || listbug.get(selected).getStatus().equals("validate")){
                    btnaddtrello.setDisable(true);
                    btnvalidate.setDisable(false);
                }else{
                    btnaddtrello.setDisable(false);
                    btnvalidate.setDisable(true);
                }
                tftitlebug.setText(listbug.get(selected).getTitle()+" #"+listbug.get(selected).getIdfe());
                tfplatebug.setText(listbug.get(selected).getPlateform());
            }
        });

        listimprove = caller.Returnimprove();
        int j=0;
        observableImproveList.clear();
        while(j<listimprove.size()){
            observableImproveList.add(listimprove.get(j).getTitle());
            j=j+1;
        }
        improvelist.setItems(observableImproveList);

        improvelist.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                System.out.println("clicked on " + improvelist.getSelectionModel().getSelectedItem());
                selected = improvelist.getSelectionModel().getSelectedIndices().get(0);
                //System.out.println(listbug.get(selected));
                tfcontentimp.setText(listimprove.get(selected).getContent());
                tftitleimp.setText(listimprove.get(selected).getTitle()+" #"+listimprove.get(selected).getIdfe());
                tfplateimp.setText(listimprove.get(selected).getPlateform());
            }
        });
    }


    public void AddBugToTrello(ActionEvent actionEvent) {
        ApiCaller caller = ApiCaller.getInstance();
        String information = caller.BugToTrello(currentUser,listbug.get(selected));
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        System.out.println(actionEvent);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(information);
        alert.showAndWait();
        refresh();
    }

    public void AddImpToTrello(ActionEvent actionEvent) {



    }
    public void ValidateBug(ActionEvent actionEvent) {
        ApiCaller caller = ApiCaller.getInstance();
        String information = caller.validatebug(listbug.get(selected));
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        System.out.println(actionEvent);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(information);
        alert.showAndWait();
        refresh();


    }

    public void GoToStat(ActionEvent actionEvent) {
        ChangeSceneController controller = new ChangeSceneController();
        controller.changeScene("../ressource/stat.fxml", actionEvent);
    }
}

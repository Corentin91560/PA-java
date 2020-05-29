package sample.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import sample.Class.Feedback;
import sample.api.ApiCaller;

import java.util.List;
import java.util.Optional;

public class HomeController {

    @FXML
    private ListView<String> buglist;
    @FXML
    private ListView<String> improvelist;
    @FXML
    private TextField tftitle;
    @FXML
    private TextArea tfcontent;
    @FXML
    private TextField tfplate;

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
        List<Feedback> listbug = caller.Returnbug();
        //System.out.println(listbug.get(0).getContent());
        int i=0;
        observableBugList.clear();
        while(i<listbug.size()){
            observableBugList.add(listbug.get(i).getTitle());
            i=i+1;
        }
        buglist.setItems(observableBugList);

        buglist.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                System.out.println("clicked on " + buglist.getSelectionModel().getSelectedIndices());
                int selected = buglist.getSelectionModel().getSelectedIndices().get(0);
                tfcontent.setText(listbug.get(selected).getContent());
                tftitle.setText(listbug.get(selected).getTitle()+" #"+listbug.get(selected).getIdfe());
            }
        });

        List<Feedback> listimprove = caller.Returnimprove();
        //System.out.println(listbug.get(0).getContent());
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
            }
        });
    }

}

package controller;

import DBUtil.DbConnection;
import interfaces.TableLoadEvent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import logics.deliveryTeamController;
import model.DeliveryTeam;
import view.tm.DeliveryTeamsTM;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class deliveryTeamManageFormController implements Initializable, TableLoadEvent {
    public AnchorPane paneDeliveryTeams;
    public TableColumn colTeamName;
    public TableView<DeliveryTeamsTM> tblDeliveryTeams;
    public TableColumn colTeamContact;
    public TableColumn colTeamID;
    public TableColumn colTeamAddress;
    public TableColumn colDeliveriesDone;
    public TableColumn colEdit;
    public TextField txtTeamID;
    public TableColumn colDelete;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colTeamID.setCellValueFactory(new PropertyValueFactory<>("teamID"));
        colTeamName.setCellValueFactory(new PropertyValueFactory<>("teamName"));
        colTeamContact.setCellValueFactory(new PropertyValueFactory<>("teamContact"));
        colTeamAddress.setCellValueFactory(new PropertyValueFactory<>("teamAddress"));
        colEdit.setCellValueFactory(new PropertyValueFactory<>("button1"));
        colDelete.setCellValueFactory(new PropertyValueFactory<>("button2"));

        tblDeliveryTeams.getColumns().setAll(colTeamID,colTeamName,colTeamContact,colTeamAddress,colEdit,colDelete);
        loadTableData();
    }

    public void addNewTeam(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("../view/addDeliveryTeam.fxml"));
            Parent parent = loader.load();
            addDeliveryTeamFormController controller = loader.getController();
            controller.setEvent(this);
            Stage stage = new Stage();
            stage.setScene(new Scene(parent));
            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void searchTeam(ActionEvent actionEvent) {
        searchProcess(txtTeamID.getText());
        txtTeamID.clear();
    }

    //search method
    private void searchProcess(String text) {
    }


    @Override
    public void loadData() {
        loadTableData();
    }

    private void loadTableData() {
        try {
            List<DeliveryTeam> allDeliveryTeams = deliveryTeamController.getAllTeams();
            ObservableList<DeliveryTeamsTM> list = FXCollections.observableArrayList();
            for (DeliveryTeam team :allDeliveryTeams){
             list.add(new DeliveryTeamsTM(
                     team.getTeamID(),
                     team.getTeamName(),
                     team.getTeamContact(),
                     team.getTeamAddress(),
                     new Button("Edit Details"),
                     new Button("Delete")
             ));
            }
            //set Action on edit
            tblDeliveryTeams.getItems().setAll(list);
            for (DeliveryTeamsTM deliveryTeamsTM:tblDeliveryTeams.getItems()){
                deliveryTeamsTM.getButton1().setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        showEditForm(deliveryTeamsTM);
                    }
                });
            }

            //set Action on delete
            for (DeliveryTeamsTM deliveryTeamsTM:tblDeliveryTeams.getItems()){
                deliveryTeamsTM.getButton2().setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        try {
                            Connection connection = DbConnection.getInstance().getConnection();
                            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM deliveryTeam WHERE teamID = ?");
                            Object teamID = tblDeliveryTeams.getSelectionModel().getSelectedItem().getTeamID();
                            preparedStatement.setObject(1, teamID);
                            preparedStatement.executeUpdate();
                            new Alert(Alert.AlertType.INFORMATION,"Success.!").show();
                            loadTableData();
                        } catch (SQLException | ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }



    }

    private void showEditForm(DeliveryTeamsTM deliveryTeamsTM) {
        try {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("../view/updateDeliveryTeamForm.fxml"));
            Parent parent = loader.load();
            updateDeliveryTeamFormController controller = loader.getController();
            controller.txtTeamID.setText(deliveryTeamsTM.getTeamID());
            controller.txtTeamName.setText(deliveryTeamsTM.getTeamName());
            controller.txtTeaContact.setText(deliveryTeamsTM.getTeamContact());
            controller.txtTeamAddress.setText(deliveryTeamsTM.getTeamAddress());
            controller.setEvent(this);
            Stage stage1 = new Stage();
            stage1.setScene(new Scene(parent));
            stage1.initStyle(StageStyle.UNDECORATED);
            stage1.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

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
import logics.itemController;
import model.Item;
import view.tm.BanksTM;
import view.tm.ItemsTM;

import java.io.IOException;
import java.net.ConnectException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;


public class itemManagementFormController implements Initializable, TableLoadEvent {
    public AnchorPane itemPaneContext;
    public TextField txtSearchID;
    public TableView<ItemsTM> tblItems;
    public TableColumn colItemID;
    public TableColumn colItemDescription;
    public TableColumn colItemQNT;
    public TableColumn colItemUnitPrice;
    public TableColumn colSupplierID;
    public TableColumn colEdit;
    public TableColumn colDelete;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colItemID.setCellValueFactory(new PropertyValueFactory<>("itemID"));
        colItemDescription.setCellValueFactory(new PropertyValueFactory<>("itemDescription"));
        colItemQNT.setCellValueFactory(new PropertyValueFactory<>("itemQNT"));
        colItemUnitPrice.setCellValueFactory(new PropertyValueFactory<>("itemUnitePrice"));
        colSupplierID.setCellValueFactory(new PropertyValueFactory<>("supplierID"));
        colEdit.setCellValueFactory(new PropertyValueFactory<>("button1"));
        colDelete.setCellValueFactory(new PropertyValueFactory<>("button2"));

        tblItems.getColumns().setAll(colItemID,colItemDescription,colItemQNT,colItemUnitPrice,colSupplierID,colEdit,colDelete);

        loadTableData();

    }

    private void loadTableData() {
        try {
            List<Item> allItems = itemController.getAllItems();
            ObservableList <ItemsTM> observableList = FXCollections.observableArrayList();
            for (Item item : allItems){
                observableList.add(new ItemsTM(
                        item.getItemID(),
                        item.getItemDescription(),
                        item.getItemQNT(),
                        item.getItemUnitePrice(),
                        item.getSupplierID(),
                        new Button("Edit Details"),
                        new Button("Delete")
                ));
            }tblItems.getItems().setAll(observableList);
            //tbl on action button delete
            for (ItemsTM itemsTM : tblItems.getItems()){
                itemsTM.getButton2().setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        try {
                            Connection connection = DbConnection.getInstance().getConnection();
                            PreparedStatement stm = connection.prepareStatement("DELETE FROM item WHERE itemID = ?");
                            Object itemID = tblItems.getSelectionModel().getSelectedItem().getItemID();
                            stm.setObject(1,itemID);
                            stm.executeUpdate();
                            loadTableData();
                            new Alert(Alert.AlertType.INFORMATION,"Success.!").showAndWait();
                        } catch (SQLException |ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }


        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void searchOnAction(ActionEvent actionEvent) {
    }

    public void addNewItem(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("../view/addNewItemForm.fxml"));
            Parent parent = loader.load();
            addNewItemFormController controller = loader.getController();
            controller.setEvent(this);
            Stage stage = new Stage();
            stage.setScene(new Scene(parent));
            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void loadData() {
        loadTableData();
    }
}

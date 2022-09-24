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
import logics.bankController;
import model.Bank;
import view.tm.BanksTM;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class bankFormController implements Initializable, TableLoadEvent {
    public AnchorPane paneBranchs;
    public TextField txtBankIDSearch;
    public TableView<BanksTM> tblBanks;
    public TableColumn colBranchID;
    public TableColumn colBranchName;
    public TableColumn colBranchAddress;
    public TableColumn colBranchContact;
    public TableColumn colBranchEMail;
    public TableColumn colDelete;
    public TableColumn colEdit;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colBranchID.setCellValueFactory(new PropertyValueFactory<>("branchID"));
        colBranchName.setCellValueFactory(new PropertyValueFactory<>("branchName"));
        colBranchAddress.setCellValueFactory(new PropertyValueFactory<>("branchAddress"));
        colBranchContact.setCellValueFactory(new PropertyValueFactory<>("branchContact"));
        colBranchEMail.setCellValueFactory(new PropertyValueFactory<>("branchEmail"));
        colDelete.setCellValueFactory(new PropertyValueFactory<>("button"));
        colEdit.setCellValueFactory(new PropertyValueFactory<>("button1"));

        tblBanks.getColumns().setAll(colBranchID, colBranchName, colBranchAddress, colBranchContact, colBranchEMail, colDelete, colEdit);

        loadTableData();
    }




    public void search(ActionEvent actionEvent) {
        searchProcess(txtBankIDSearch.getText());
        txtBankIDSearch.clear();
    }


    public void addNewBank(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("../view/addNewBank.fxml"));
            Parent parent = loader.load();
            addNewBankFormController controller = loader.getController();
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

    public void loadTableData() {
        try {
            List<Bank> allBankLists = bankController.getAllBanks();
            ObservableList<BanksTM> list = FXCollections.observableArrayList();
            for (Bank bank : allBankLists) {
                list.add(new BanksTM(
                        bank.getBranchID(),
                        bank.getBranchName(),
                        bank.getBranchAddress(),
                        bank.getBranchContact(),
                        bank.getBranchEmail(),
                        new Button("Delete"),
                        new Button("Update")
                ));
            }
            tblBanks.getItems().setAll(list);
            //popup edit
           for (BanksTM banksTM:tblBanks.getItems()){
                banksTM.getButton1().setOnAction(new EventHandler <ActionEvent>(){
                    @Override
                    public void handle(ActionEvent event) {
                        showUpdateForm(banksTM);
                    }
                });
            }

            //delete on table button
            for (BanksTM bank : tblBanks.getItems()) {
                bank.getButton().setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        try {
                            Connection connection = DbConnection.getInstance().getConnection();
                            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM branch WHERE branchID = ?");
                            Object branchID = tblBanks.getSelectionModel().getSelectedItem().getBranchID();
                            preparedStatement.setObject(1, branchID);
                            preparedStatement.executeUpdate();
                            new Alert(Alert.AlertType.CONFIRMATION,"Success.!").show();
                            loadTableData();
                        } catch (SQLException | ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showUpdateForm(BanksTM banksTM) {
        try{
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("../view/updateBankForm.fxml"));
            Parent parent  = loader.load();
            updateBankFormController controller = loader.getController();
            controller.txtBranchID.setText(String.valueOf(banksTM.getBranchID()));
            controller.txtBranchName.setText(banksTM.getBranchName());
            controller.txtBranchAddress.setText(banksTM.getBranchAddress());
            controller.txtBranchContact.setText(banksTM.getBranchContact());
            controller.txtBranchEmail.setText(banksTM.getBranchEmail());
            controller.setEvent(this);
            Stage stage1 = new Stage();
            stage1.setScene(new Scene(parent));
            stage1.initStyle(StageStyle.UNDECORATED);
            stage1.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //search process
    private void searchProcess(String id) {
        try{
            List<Bank> banksList = bankController.searchBanks(id);
            ObservableList<BanksTM> banks = FXCollections.observableArrayList();
            for (Bank bank: banksList){
                banks.add(new BanksTM(
                     bank.getBranchID(),
                     bank.getBranchName(),
                     bank.getBranchAddress(),
                     bank.getBranchContact(),
                     bank.getBranchEmail(),
                     new Button("Update"),
                     new Button("Delete")
                ));
            }
            if (banksList.isEmpty()){
                new Alert(Alert.AlertType.WARNING,"Such Data Entry is not Available").showAndWait();
            }else{
                tblBanks.getItems().setAll(banks);
            }


        }catch(Exception e){}
    }
}

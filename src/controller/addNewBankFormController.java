package controller;

import interfaces.TableLoadEvent;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import logics.bankController;
import model.Bank;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class addNewBankFormController implements Initializable {
    public AnchorPane contextAddNewBank;
    public TextField txtBranchID;
    public TextField txtBranchName;
    public TextField txtBranchAddress;
    public TextField txtBranchContact;
    public TextField txtBranchEmail;
    private TableLoadEvent event;

    @Override
    public void initialize(URL locations, ResourceBundle resources) {

    }

    public void cancel(ActionEvent actionEvent) {
        Stage stage = (Stage) contextAddNewBank.getScene().getWindow();
        stage.close();

    }

    public void saveBranch(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Bank b1 = new Bank(txtBranchID.getText(), txtBranchName.getText(), txtBranchAddress.getText(), txtBranchContact.getText(), txtBranchEmail.getText());

      if (new bankController().saveBranch(b1)) {
            new Alert(Alert.AlertType.CONFIRMATION, "Success.!").show();
            txtBranchName.clear();
            txtBranchID.clear();
            txtBranchAddress.clear();
            txtBranchContact.clear();
            txtBranchEmail.clear();
            event.loadData();

        }else{
            new Alert(Alert.AlertType.WARNING,"Enter All Information and Try Again.! ").show();
        }
    }
    public void setEvent(TableLoadEvent event){
        this.event=event;}

}

package controller;

import interfaces.TableLoadEvent;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import logics.bankController;
import model.Bank;

import java.net.URL;
import java.util.ResourceBundle;

public class updateBankFormController implements Initializable {
    public Pane branchDetailsUpdateContext;
    public TextField txtBranchID;
    public TextField txtBranchName;
    public TextField txtBranchAddress;
    public TextField txtBranchContact;
    public TextField txtBranchEmail;

    private TableLoadEvent event;
    @Override
    public void initialize(URL location , ResourceBundle resources){

    }

    public void cancel(ActionEvent actionEvent) {
        Stage stage1 =(Stage) branchDetailsUpdateContext.getScene().getWindow();
        stage1.close();
    }

    public void saveUpdates(ActionEvent actionEvent) {
        try{
           boolean isUpdate = bankController.updateBank(new Bank(
                    txtBranchID.getText(),
                    txtBranchName.getText(),
                    txtBranchAddress.getText(),
                    txtBranchContact.getText(),
                    txtBranchEmail.getText()
            ));
            if (isUpdate){
                new Alert(Alert.AlertType.INFORMATION,"Update Successful.!").show();
                event.loadData();
                Stage stage1 = (Stage) branchDetailsUpdateContext.getScene().getWindow();
                stage1.close();
           }else {
                new Alert(Alert.AlertType.ERROR,"Something Wrong,Try Again.!").show();
           }
        }catch (Exception e){

        }


    }
    public void setEvent(TableLoadEvent event){
        this.event = event;
    }
}

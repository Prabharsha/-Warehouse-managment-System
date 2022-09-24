package controller;

import interfaces.TableLoadEvent;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import logics.employeeController;
import model.Employee;

import java.net.URL;
import java.util.ResourceBundle;

public class updateEmployeeFormController  implements Initializable {
    public AnchorPane emplupdateContext;
    public TextField txtEmplNIC;
    public TextField txtEmplName;
    public TextField txtEmplAddress;
    public TextField txtEmplContact;
    public TextField txtEmplJobType;
    private TableLoadEvent event;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void cancel(ActionEvent actionEvent) {
        Stage stage = (Stage) emplupdateContext.getScene().getWindow();
        stage.close();
    }

    public void saveUpdates(ActionEvent actionEvent) {
       try {
           boolean isUpdate = employeeController.updateEmployee(
                   new Employee(
                           txtEmplNIC.getText(),
                           txtEmplName.getText(),
                           txtEmplAddress.getText(),
                           txtEmplContact.getText(),
                           txtEmplJobType.getText()
                   ));
           if (isUpdate){
               new Alert(Alert.AlertType.INFORMATION,"Update Successful..!").showAndWait();
                       event.loadData();Stage stage1 = (Stage) emplupdateContext.getScene().getWindow();
                       stage1.close();
           }
       }catch (Exception e){
           e.printStackTrace();
       }
    }
    public void setEvent(TableLoadEvent event){
        this.event = event;
    }

}

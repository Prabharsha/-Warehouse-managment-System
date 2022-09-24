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
import java.sql.SQLException;
import java.util.ResourceBundle;

public class addNewEmployeeFormController implements Initializable {
    public AnchorPane addNewEmployeeContext;
    public TextField txtEmployeeNIC;
    public TextField txtEmployeeName;
    public TextField txtEmployeeAddress;
    public TextField txtEmployeeContact;
    public TextField txtEmployeeJobType;
    private TableLoadEvent event;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void cancel(ActionEvent actionEvent) {
        Stage stage = (Stage) addNewEmployeeContext.getScene().getWindow();
        stage.close();
    }

    public void saveEmployee(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Employee employee = new Employee(txtEmployeeNIC.getText(),txtEmployeeName.getText(),txtEmployeeAddress.getText(),txtEmployeeContact.getText(),txtEmployeeJobType.getText());
        if (new employeeController().saveEmployee(employee)){
            new Alert(Alert.AlertType.CONFIRMATION,"Success.!").showAndWait();
            txtEmployeeNIC.clear();
            txtEmployeeName.clear();
            txtEmployeeAddress.clear();
            txtEmployeeContact.clear();
            txtEmployeeJobType.clear();
            event.loadData();
        }else{
            new Alert(Alert.AlertType.ERROR,"Something Wrong,Try Again.!").showAndWait();
        }
    }
    public void setEvent(TableLoadEvent event){
        this.event=event;
    }


}

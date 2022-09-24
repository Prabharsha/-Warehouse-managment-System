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
import logics.employeeController;
import model.Employee;
import view.tm.EmployeeTM;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class employeeManagementFormController implements Initializable, TableLoadEvent {
    public AnchorPane paneEmployee;
    public TextField txtEmployeeID;
    public TableView<EmployeeTM> tblEmployee;
    public TableColumn colEmplNIC;
    public TableColumn colEmplName;
    public TableColumn colEmplAddress;
    public TableColumn colEmplContact;
    public TableColumn colEmplJobType;
    public TableColumn colEdit;
    public TableColumn colDelete;

    @Override
    public void initialize(URL location , ResourceBundle resources){
        colEmplNIC.setCellValueFactory(new PropertyValueFactory<>("employeeNIC"));
        colEmplName.setCellValueFactory(new PropertyValueFactory<>("employeeName"));
        colEmplAddress.setCellValueFactory(new PropertyValueFactory<>("employeeAddress"));
        colEmplContact.setCellValueFactory(new PropertyValueFactory<>("employeeContact"));
        colEmplJobType.setCellValueFactory(new PropertyValueFactory<>("employeeJobType"));
        colEdit.setCellValueFactory(new PropertyValueFactory<>("button1"));
        colDelete.setCellValueFactory(new PropertyValueFactory<>("button2"));

        tblEmployee.getColumns().setAll(colEmplNIC,colEmplName,colEmplAddress,colEmplContact,colEmplJobType,colEdit,colDelete);
        loadTableData();
    }

    public void addEmployee(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("../view/addNewEmployee.fxml"));
            Parent parent = loader.load();
            addNewEmployeeFormController controller = loader.getController();
            controller.setEvent(this);
            Stage stage = new Stage();
            stage.setScene(new Scene(parent));
            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void searchEmployee(ActionEvent actionEvent) {
        searchProcess(txtEmployeeID.getText());
        txtEmployeeID.clear();
    }
    //search
    private void searchProcess(String text) {
        try {
            List<Employee> employeeList = employeeController.searchEmployee(text);
            ObservableList<EmployeeTM> employeeTMS = FXCollections.observableArrayList();
            for (Employee employee:employeeList){
                employeeTMS.add(new EmployeeTM(
                        employee.getEmployeeNIC(),
                        employee.getEmployeeName(),
                        employee.getEmployeeAddress(),
                        employee.getEmployeeContact(),
                        employee.getEmployeeJobType(),
                        new Button("Edit"),
                        new Button("Delete")
                ));
            }
            if (employeeList.isEmpty()){
                new Alert(Alert.AlertType.WARNING,"Such Data Entry is not Available").showAndWait();
            }else {
                tblEmployee.getItems().setAll(employeeTMS);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void loadData() {
        loadTableData();
    }

    private void loadTableData() {
        try {
            List<Employee> allEmployee = employeeController.getAllEmployee();
            ObservableList<EmployeeTM> employeeList = FXCollections.observableArrayList();
            for (Employee employee :allEmployee){
                employeeList.add(new EmployeeTM(
                        employee.getEmployeeNIC(),
                        employee.getEmployeeName(),
                        employee.getEmployeeAddress(),
                        employee.getEmployeeContact(),
                        employee.getEmployeeJobType(),
                        new Button("Edit Details"),
                        new Button("Delete")

                ));
                tblEmployee.getItems().setAll(employeeList);

                //setAction on buttons - delete
                for (EmployeeTM employeeTM:tblEmployee.getItems()){
                employeeTM.getButton2().setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        try {
                            Connection connection = DbConnection.getInstance().getConnection();
                            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM employee WHERE employeeNIC = ?");
                            Object employeeNIC = tblEmployee.getSelectionModel().getSelectedItem().getEmployeeNIC();
                            preparedStatement.setObject(1,employeeNIC);
                            preparedStatement.executeUpdate();
                            new Alert(Alert.AlertType.INFORMATION,"Successful..!").showAndWait();
                            loadTableData();
                        } catch (SQLException | ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                });
                }

                //set OnAction edit button - edit
                for (EmployeeTM employeeTM : tblEmployee.getItems()){
                    employeeTM.getButton1().setOnAction(new EventHandler<ActionEvent>() {

                        @Override
                        public void handle(ActionEvent event) {
                           showEditForm(employeeTM);
                            System.out.println("edit button pressed");
                        }
                    });
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void showEditForm(EmployeeTM employeeTM) {
        try {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("../view/updateEmployeeForm.fxml"));
            Parent parent = loader.load();
            System.out.println("On Action called");
            updateEmployeeFormController controller = loader.getController();
            controller.txtEmplNIC.setText(employeeTM.getEmployeeNIC());
            controller.txtEmplName.setText(employeeTM.getEmployeeName());
            controller.txtEmplAddress.setText(employeeTM.getEmployeeAddress());
            controller.txtEmplContact.setText(employeeTM.getEmployeeContact());
            controller.txtEmplJobType.setText(employeeTM.getEmployeeJobType());
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

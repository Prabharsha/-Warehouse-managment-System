package controller;

import interfaces.TableLoadEvent;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import logics.supplierController;
import model.Supplier;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class addNewSupplierFormController implements Initializable {
    public AnchorPane addNewSupplierContext;
    public TextField txtSupplierID;
    public TextField txtSupplierName;
    public TextField txtSupplierAddress;
    public TextField txtSupplierContact;
    private TableLoadEvent event;
    private Double dft = 0.00;

    @Override
    public void initialize(URL locations, ResourceBundle resources) {

    }

    public void cancel(ActionEvent actionEvent) {
        Stage stage = (Stage) addNewSupplierContext.getScene().getWindow();
        stage.close();
    }

    public void saveSuppllier(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        Supplier supplier1 = new Supplier(txtSupplierID.getText(),txtSupplierName.getText(),txtSupplierAddress.getText(),txtSupplierContact.getText(),dft);
        if (new supplierController().saveSupplier(supplier1)){
            new Alert(Alert.AlertType.CONFIRMATION,"Success..!").show();
           txtSupplierName.clear();
           txtSupplierID.clear();
           txtSupplierAddress.clear();
           txtSupplierContact.clear();
           event.loadData();
        }
    }

    public void setEvent(TableLoadEvent event){
        this.event=event;}


}




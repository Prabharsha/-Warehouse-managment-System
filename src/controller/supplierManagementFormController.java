package controller;

import interfaces.TableLoadEvent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import logics.supplierController;
import model.Supplier;
import view.tm.SuppliersTM;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class supplierManagementFormController implements TableLoadEvent, Initializable {
    public AnchorPane paneSupplier;
    public TableView<SuppliersTM> tblSupplier;
    public TableColumn colSupplierID;
    public TableColumn colSupplierName;
    public TableColumn colSupplierAddress;
    public TableColumn colSupplierContact;
    public TableColumn colAmountToBePayed;
    public TableColumn colEdit;
    public TextField txtSearchID;
    public TableColumn colDelete;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colSupplierID.setCellValueFactory(new PropertyValueFactory<>("supplierID"));
        colSupplierName.setCellValueFactory(new PropertyValueFactory<>("supplierName"));
        colSupplierAddress.setCellValueFactory(new PropertyValueFactory<>("supplierAddress"));
        colSupplierContact.setCellValueFactory(new PropertyValueFactory<>("supplierContact"));
        colAmountToBePayed.setCellValueFactory(new PropertyValueFactory<>("amountToBePayed"));
        colEdit.setCellValueFactory(new PropertyValueFactory<>("button1"));
        colDelete.setCellValueFactory(new PropertyValueFactory<>("button2"));

        tblSupplier.getColumns().setAll(colSupplierID, colSupplierName, colSupplierAddress, colSupplierContact, colAmountToBePayed, colEdit, colDelete);

        loadTableData();


    }

    public void addNewSupplier(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("../view/addNewSupplierForm.fxml"));
            Parent parent = loader.load();
            addNewSupplierFormController controller = loader.getController();
            controller.setEvent(this);
            Stage stage = new Stage();
            stage.setScene(new Scene(parent));
            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void searchSupplier(ActionEvent actionEvent) {
    }



    @Override
    public void loadData() {
        loadTableData();
    }

    private void loadTableData() {
        try {
            List<Supplier> allSuppliers = supplierController.getAllSuppliers();
            ObservableList<SuppliersTM> list = FXCollections.observableArrayList();
            for (Supplier supplier : allSuppliers) {
                list.add(new SuppliersTM(
                        supplier.getSupplierID(),
                        supplier.getSupplierName(),
                        supplier.getSupplierAddress(),
                        supplier.getSupplierContact(),
                        supplier.getAmountToBePayed(),
                        new Button("Edit Details"),
                        new Button("Delete")
                ));
            }
             tblSupplier.getItems().setAll(list);

            //edit button process
           for (SuppliersTM suppliers:tblSupplier.getItems()) {
                suppliers.getButton1().setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        showUpdateForm(suppliers);
                    }
                });
            }

            //delete button process
           /* for (SuppliersTm suppliers : tblSupplier.getItems()){

            }*/
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showUpdateForm(SuppliersTM suppliersTm) {
    }
}

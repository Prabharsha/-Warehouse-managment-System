package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import interfaces.TableLoadEvent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import logics.paymentController;
import logics.supplierController;
import model.Supplier;
import model.supplierPayHistory;
import view.tm.SuppliersPayTM;
import view.tm.supplierpaymentsHistory;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class paymentsFormController implements Initializable,TableLoadEvent{


    public  AnchorPane paymentContext;
    public TableView<Object> tblSupplierPayments;
    public TableColumn<Object, Object> colSupplierID;
    public TableColumn<Object, Object> colSupplierName;
    public TableColumn colSupplierAddress;
    public TableColumn colSupplierContact;
    public TableColumn colAmountToBePayed;
    public JFXTextField txtSupplierID;
    public TableView tblsalaryDis;
    public TableColumn colEmployeeNIC;
    public TableColumn colSalaryMonth;
    public TableColumn colSalaryPayed;
    public JFXButton searchRecord;
    public JFXTextField txtEmployeeNIC;
    public JFXButton MakeAsalaryPayment;
    public TableView tblSupPaymentHistory;
    public TableColumn colPaymentID;
    public TableColumn colPaymentSupplierID;
    public TableColumn colPaymentHistory;
    public TableColumn colPaymentAmount;
    public Tab supplierPaymentContext;
    int selectRow = -1;
    List<Supplier> allSuppliers = new ArrayList<>();
    static Supplier supplier1 = new Supplier();




    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //tbl supplier payment view

        colSupplierID.setCellValueFactory(new PropertyValueFactory<>("supplierID"));
        colSupplierName.setCellValueFactory(new PropertyValueFactory<>("supplierName"));
        colSupplierAddress.setCellValueFactory(new PropertyValueFactory<>("supplierAddress"));
        colSupplierContact.setCellValueFactory(new PropertyValueFactory<>("supplierContact"));
        colAmountToBePayed.setCellValueFactory(new PropertyValueFactory<>("amountToBePayed"));

        colPaymentID.setCellValueFactory(new PropertyValueFactory<>("paymentID"));
        colPaymentSupplierID.setCellValueFactory(new PropertyValueFactory<>("supplierID"));
        colPaymentHistory.setCellValueFactory(new PropertyValueFactory<>("date"));
        colPaymentAmount.setCellValueFactory(new PropertyValueFactory<>("paymentAmount"));


        tblSupplierPayments.getColumns().setAll(colSupplierID, colSupplierName, colSupplierAddress, colSupplierContact, colAmountToBePayed);
        tblSupPaymentHistory.getColumns().setAll(colPaymentID,colPaymentSupplierID,colPaymentHistory,colPaymentAmount);
        loadTableData();

        //add listner to the table supplier payments
        tblSupplierPayments.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
        selectRow =(int)newValue;
        supplier1= allSuppliers.get(selectRow);


        });

    }

    private void loadTableData() {

        loadTableDataSupplierPayement();
        loadTablePaymentHistory();
    }

    private void loadTablePaymentHistory() {
        try {
            ArrayList<supplierPayHistory> allPaymentHistory = new ArrayList<>();
            allPaymentHistory = paymentController.getAllRecords();
            ObservableList<supplierpaymentsHistory> list = FXCollections.observableArrayList();
            for (supplierPayHistory history:allPaymentHistory){
                list.add(new supplierpaymentsHistory(
                        history.getPaymentID(),
                        history.getSupplierID(),
                        history.getDate(),
                        history.getPaymentAmount()
                ));
            }
            tblSupPaymentHistory.getItems().setAll(list);

        }catch (Exception e){
            e.printStackTrace();
        }
    }


    private void loadTableDataSupplierPayement() {
        try {
            allSuppliers = supplierController.getAllSuppliers();
            ObservableList<SuppliersPayTM> list = FXCollections.observableArrayList();
            for (Supplier supplier : allSuppliers) {
                list.add(new SuppliersPayTM(
                        supplier.getSupplierID(),
                        supplier.getSupplierName(),
                        supplier.getSupplierAddress(),
                        supplier.getSupplierContact(),
                        supplier.getAmountToBePayed()
                ));
            }
            tblSupplierPayments.getItems().setAll(list);
            
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

        public void searchSUpplier (ActionEvent actionEvent){
        }

        public void makeSPayment (ActionEvent actionEvent){
            try{
                FXMLLoader loader = new FXMLLoader(this.getClass().getResource("../view/makesupplierpayment.fxml"));
                Parent parent  = loader.load();
                makesupplierpaymentController controller = loader.getController();
                controller.lblSupplierID.setText(String.valueOf(supplier1.getSupplierID()));
                controller.lblSupplierName.setText(supplier1.getSupplierName());
                controller.lblSupplierAddress.setText(supplier1.getSupplierAddress());
                controller.lblSupplierContact.setText(supplier1.getSupplierContact());
                controller.setEvent(this);
                Stage stage1 = new Stage();
                stage1.setScene(new Scene(parent));
                stage1.initStyle(StageStyle.UNDECORATED);
                stage1.show();

            } catch (IOException e) {
                e.printStackTrace();
            }

        }


    @Override
    public void loadData() {
        loadTableData();
    }
}

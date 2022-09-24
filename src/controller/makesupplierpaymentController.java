package controller;

import com.jfoenix.controls.JFXButton;
import interfaces.TableLoadEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import logics.paymentController;
import util.ValidationUtil;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.ResourceBundle;
import java.util.regex.Pattern;


public class makesupplierpaymentController  implements Initializable {
    public AnchorPane contextMakePayment;
    public TextField txtAmountPay;
    public Label lblSupplierID;
    public Label lblSupplierName;
    public Label lblSupplierAddress;
    public Label lblSupplierContact;
    public Label lblDate;
    public JFXButton btnPay;
    private TableLoadEvent event;
    LinkedHashMap<TextField, Pattern> map = new LinkedHashMap();

    Pattern paymentAmountPatter = Pattern.compile("^[1-9][0-9]*([.][0-9]{2})?$");



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Date date = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        lblDate.setText(f.format(date));

        storeValidationPayment();
        btnPay.setDisable(true);

    }

    private void storeValidationPayment() {
    map.put(txtAmountPay,paymentAmountPatter);
    }

    public void cancel(ActionEvent actionEvent) {
        Stage stage = (Stage) contextMakePayment.getScene().getWindow();
        stage.close();
    }

    public void payOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String tempSupplierID =  lblSupplierID.getText();
        Double tempPayAmount = Double.parseDouble(txtAmountPay.getText());
        String tempDate = lblDate.getText();
       if(paymentController.updateSupplierAmountToPay(tempSupplierID,tempPayAmount,tempDate)){
           System.out.println("pd");
           new Alert(Alert.AlertType.INFORMATION,"Payment Done.!").showAndWait();
           Stage stage1 = (Stage) contextMakePayment.getScene().getWindow();
          event.loadData();
           stage1.close();
       }else{
           new Alert(Alert.AlertType.WARNING,"Someting Wrong,Try Again..!").showAndWait();
       }

    }

    public void setEvent(TableLoadEvent event) {
        this.event = event;
    }

    public void textFieldKeyReleaseOnAction(KeyEvent keyEvent) {
        Object response = ValidationUtil.validate(map, btnPay);

        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (response instanceof TextField) {
                TextField errorText = (TextField) response;
                errorText.requestFocus();
            } else if (response instanceof Boolean) {

            }
        }
    }
}

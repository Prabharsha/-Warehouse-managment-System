package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import interfaces.TableLoadEvent;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import logics.itemController;
import logics.paymentController;
import logics.supplierController;
import model.Item;
import model.supplierPayment;
import util.ValidationUtil;

import java.net.URL;
import java.sql.SQLException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class addNewItemFormController implements Initializable {
    public JFXButton btnSave;
    private Double itemUnitPrice = 0.00;
    private int itemQNT = 0;
    public AnchorPane contextAddNewItem;
    public TextField txtItemID;
    public TextField txtItemQNT;
    public TextField txtItemUnitPrice;
    public JFXComboBox<String> cmbSupplierID;
    public TextField txtItemDescription;
    Date date = new Date();
    Double tempAmount = 0.00;
    private TableLoadEvent event;
    private String supplierID;
    private String paymentID;
    LinkedHashMap<TextField, Pattern> map = new LinkedHashMap();

    Pattern itemIDValidate = Pattern.compile("^Item-\\b[0-9]*{3}$");
    Pattern itemDescriptionValidate = Pattern.compile("^[a-z|A-Z,[ ]0-9]{2,}[a-z]$");
    Pattern itemQuantiValidate = Pattern.compile("^[0-9]*$");
    Pattern itemUnitPriceValidate = Pattern.compile("^[0-9.]*$");

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            cmbSupplierID.getItems().setAll(new supplierController().getAllIDs());
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            btnSave.setDisable(true);
            storeValidationsItems();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    private void storeValidationsItems() {
        map.put(txtItemID, itemIDValidate);
        map.put(txtItemDescription, itemDescriptionValidate);
        map.put(txtItemQNT, itemQuantiValidate );
        map.put(txtItemUnitPrice, itemUnitPriceValidate);
    }


    public void cancel(ActionEvent actionEvent) {
        Stage stage1 = (Stage) contextAddNewItem.getScene().getWindow();
        stage1.close();
    }

    public void saveItem(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Item item = new Item(txtItemID.getText(), txtItemDescription.getText(), Integer.parseInt(txtItemQNT.getText()), Double.parseDouble(txtItemUnitPrice.getText()), cmbSupplierID.getSelectionModel().getSelectedItem());

        //initialising variables for save amountTopay
        itemUnitPrice = Double.parseDouble(txtItemUnitPrice.getText());
        itemQNT = Integer.parseInt(txtItemQNT.getText());

        //method calling for calculation
        tempAmount = calculateSupplierPayment(itemUnitPrice, itemQNT);
        supplierID = cmbSupplierID.getSelectionModel().getSelectedItem();
        System.out.println(tempAmount+" "+supplierID);

        //save supplier amoount to pay
        try {
            if (new itemController().saveItem(item) && new supplierController().updateAmountToPay(supplierID,tempAmount)) {
                new Alert(Alert.AlertType.INFORMATION, "Success.!").show();
                txtItemID.clear();
                txtItemDescription.clear();
                txtItemQNT.clear();
                txtItemUnitPrice.clear();
                cmbSupplierID.getItems().clear();
                Stage stage1 = (Stage) contextAddNewItem.getScene().getWindow();
                stage1.close();
                event.loadData();
            } else {
                new Alert(Alert.AlertType.WARNING, "Enter All Information and Try Again.! ").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.WARNING, "Entered Item ID already in use,Try again with a different number.!").showAndWait();
        }


    }

    public void setEvent(TableLoadEvent event) {
        this.event = event;
    }

    private Double calculateSupplierPayment(Double itemUnitPrice, int itemQNT) {
        Double paymentTotal = (itemUnitPrice * itemQNT);
        System.out.println("payment" + paymentTotal);
        return paymentTotal;
    }

    public void textFieldKeyRelease(KeyEvent keyEvent) {
        Object response = ValidationUtil.validate(map, btnSave);

        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (response instanceof TextField) {
                TextField errorText = (TextField) response;
                errorText.requestFocus();
            } else if (response instanceof Boolean) {

            }
        }
    }
}

package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import logics.OrderController;
import logics.bankController;
import logics.itemController;
import model.Bank;
import model.Item;
import model.ItemDetails;
import model.Order;
import util.ValidationUtil;
import view.tm.ItemListTM;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.*;
import java.util.regex.Pattern;

public class placeOrderFormController implements Initializable {
    public AnchorPane placeOrderContext;
    public JFXComboBox<String> cmbBranchName;
    public JFXComboBox<String> cmbItem;
    public TableView<ItemListTM> tblOrderListView;
    public TableColumn colItemID;
    public TableColumn colItemDescription;
    public TableColumn colItemQNT;
    public TableColumn colRemove;
    public Label lblBranchID;
    public Label lblBranchAddress;
    public Label lblBranchContact;
    public TextField txtorderIDShow;
    public Label lblTime;
    public Label lblDate;
    public TextField lblQNTOnHand;
    public TextField lblItemID;
    public TextField txtQNTEntered;
    public JFXButton btnAddToList;

    LinkedHashMap<TextField, Pattern> map = new LinkedHashMap();

    Pattern itemQNTValidate = Pattern.compile("^[1-9][0-9]*$");

    ObservableList<String> itemNameList = FXCollections.observableArrayList();
    ObservableList<String> bankNameList = FXCollections.observableArrayList();
    ObservableList<ItemListTM> itemList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        int x ;
        x=1;

        colItemID.setCellValueFactory(new PropertyValueFactory<>("itemID"));
        colItemDescription.setCellValueFactory(new PropertyValueFactory<>("itemDescription"));
        colItemQNT.setCellValueFactory(new PropertyValueFactory<>("itemQNT"));

        try {
            setOrderID();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            //set date
            loadTimeAnddate();

            List<Item> allItemNames = itemController.getAllItems();
            for (Item item : allItemNames) {
                itemNameList.add(
                        item.getItemDescription()
                );
            }
            List<Bank> allBankNames = bankController.getAllBanks();
            for (Bank bank : allBankNames) {
                bankNameList.add(
                        bank.getBranchName()
                );
            }



            //set branch details
            cmbBranchName.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                System.out.println(oldValue + "  " + newValue);
                setLblvalues(newValue);
            });

            //set qnt on hand and iteID and itemID
            cmbItem.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                try {
                    setLblQNTonStock(newValue);
                } catch (SQLException sqlException) {
                    sqlException.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            });
            btnAddToList.setDisable(true);
            storeValidationsItems();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        //Initialize comboboxes
        cmbItem.getItems().setAll(itemNameList);
        cmbBranchName.getItems().setAll(bankNameList);
    }

    private void storeValidationsItems() {
        map.put(txtQNTEntered,itemQNTValidate);
    }

    private void setOrderID() throws SQLException, ClassNotFoundException {
        String tempID = OrderController.getOrderID();
        txtorderIDShow.setText(tempID);
    }


    //method of set qnt on hand and itemID
    private void setLblQNTonStock(String newValue) throws SQLException, ClassNotFoundException {
        Item item = new itemController().getItemsDetails(newValue);
        if (item == null) {
            new Alert(Alert.AlertType.WARNING, "Empty Result Set");
        } else {
            lblQNTOnHand.setText(String.valueOf(item.getItemQNT()));
            lblItemID.setText(String.valueOf(item.getItemID()));
            System.out.println(item.getItemQNT() + "      " + item.getItemID());

        }
    }

    //date and time
    private void loadTimeAnddate() {
        Date date = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        lblDate.setText(f.format(date));

        // load Time
        Timeline time = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            LocalTime currentTime = LocalTime.now();
            lblTime.setText(
                    currentTime.getHour() + " : " + currentTime.getMinute() + " : " + currentTime.getSecond()
            );
        }),
                new KeyFrame(Duration.seconds(1))
        );
        time.setCycleCount(Animation.INDEFINITE);
        time.play();
    }

    //method for set branch details
    private void setLblvalues(String newValue) {
        try {
            Bank bankList = bankController.getBankDetailsFromName(newValue);
            ObservableList<String> list = FXCollections.observableArrayList();
            if (bankList != null) {
                lblBranchAddress.setText(bankList.getBranchAddress());
                lblBranchID.setText(bankList.getBranchID());
                lblBranchContact.setText(bankList.getBranchContact());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void cancel(ActionEvent actionEvent) {
        refreshPane();
    }

    private void refreshPane() {
        try {
            AnchorPane pane;
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("../view/placeOrderForm.fxml"));
            pane = loader.load();
            placeOrderContext.getChildren().setAll(pane);
        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    public void saveOrder(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        ArrayList <ItemDetails> itemDetails = new ArrayList<>();
        for (ItemListTM tempList : itemList){
            itemDetails.add(new ItemDetails(
                    tempList.getItemID(),
                    tempList.getItemDescription(),
                    tempList.getItemQNT()
            ));
        }
        Order order = new Order(txtorderIDShow.getText(),lblDate.getText(),lblBranchID.getText(),itemDetails);
        if (new OrderController().saveOrder(order) && new OrderController().saveOrderID((txtorderIDShow.getText()))){
            new Alert(Alert.AlertType.INFORMATION,"Order saved Succesfully.!").showAndWait();
            tblOrderListView.getItems().clear();
            setOrderID();
            refreshPane();
        }else{
            new Alert(Alert.AlertType.WARNING, "Something Wrong,Try Again").show();
        }

    }

    public void addToList(ActionEvent actionEvent) {
    String tempItemID = lblItemID.getText();
    String tempIDescription = cmbItem.getSelectionModel().getSelectedItem();
    int tempQNTEntered = Integer.parseInt(txtQNTEntered.getText());
    int QNTOnHand = Integer.parseInt(lblQNTOnHand.getText());

    if (tempQNTEntered>QNTOnHand){
        new Alert(Alert.AlertType.WARNING, "Entered QNT is out Of Stock.!").show();
        return;
    }
        ItemListTM listTm = new ItemListTM(
                tempItemID,
                tempIDescription,
                tempQNTEntered
        );
    itemList.add(listTm);
    tblOrderListView.setItems(itemList);
    tblOrderListView.refresh();
    txtQNTEntered.clear();
    lblItemID.clear();
    lblQNTOnHand.clear();
    cmbItem.getSelectionModel().clearSelection();
    btnAddToList.setDisable(true);
    }

    public void textFieldKeyRelease(KeyEvent keyEvent) {
        Object response = ValidationUtil.validate(map, btnAddToList);

        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (response instanceof TextField) {
                TextField errorText = (TextField) response;
                errorText.requestFocus();
            } else if (response instanceof Boolean) {

            }
        }
    }
}

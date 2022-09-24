package controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import logics.OrderController;
import logics.bankController;
import logics.deliveryTeamController;
import logics.orderDispatchController;
import model.*;
import view.tm.orderDetailsTM;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class orderDetailsFormController implements Initializable {
    public AnchorPane orderDetailsContext;
    public JFXComboBox<String> cmbSelectOrder1;
    public TableView<orderDetailsTM> tblListView1;
    public TableColumn<Object, Object> colItemID1;
    public TableColumn colItemDescription1;
    public TableColumn colItenQNT1;
    public Label lblBrancID1;
    public Label lblBranchName1;
    public Label lblOrderDate1;
    public JFXComboBox<String> cmbSelectDeliveryteam;
    public Label lblBranchID2;
    public Label lblBranchName2;
    public Label lblorderDate2;
    public TableView<orderDetailsTM> tblListView2;
    public TableColumn colitemID2;
    public TableColumn colItemDescription2;
    public TableColumn colItemQNT2;
    public Label lblDeliveryTeam;
    public Label lblDispatchedDate;
    public Label lblDate;
    public JFXComboBox<String> cmbSelectOrder2;
    ObservableList<String> teamList = FXCollections.observableArrayList();
    ObservableList<String> savedOrderList = FXCollections.observableArrayList();
    ObservableList<String> dispatchedOrderList = FXCollections.observableArrayList();
    ArrayList<orderDetailsTM> temporderList = new ArrayList<>();
    ArrayList<orderDetailsTM> temporderList2 = new ArrayList<>();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //table orders to dispatch
        colItemID1.setCellValueFactory(new PropertyValueFactory<>("itemID"));
        colItemDescription1.setCellValueFactory(new PropertyValueFactory<>("itemDescription"));
        colItenQNT1.setCellValueFactory(new PropertyValueFactory<>("itemQNT"));

        //tabel dispatched orders
        colitemID2.setCellValueFactory(new PropertyValueFactory<>("itemID"));
        colItemDescription2.setCellValueFactory(new PropertyValueFactory<>("itemDescription"));
        colItemQNT2.setCellValueFactory(new PropertyValueFactory<>("itemQNT"));


        try {
            loadDate();

            //set teams
            List<DeliveryTeam> allteams = deliveryTeamController.getAllTeams();
            for (DeliveryTeam teams : allteams) {
                teamList.add(
                        teams.getTeamName()
                );
            }
            cmbSelectDeliveryteam.getItems().setAll(teamList);

            //add listner to cmbSelectOrder1 and set data
            List<Order> allOrders = OrderController.getAllOrders();
            for (Order order : allOrders) {
                savedOrderList.add(
                        order.getOrderID()
                );
                cmbSelectOrder1.getItems().setAll(savedOrderList);
            }

            cmbSelectOrder1.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                try {
                    //OTD = orders to dispatc
                    setOTDLables(newValue);
                    loadTabledata1(newValue);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            //initialize cmb2/select Dispatched orders
            List<DispatchOrder> allDispatchedOrders = orderDispatchController.getAllDispatchedOrders();
            System.out.println(allDispatchedOrders.toString());
            for (DispatchOrder orders : allDispatchedOrders) {
                dispatchedOrderList.add(
                        orders.getOrderID()
                );
                cmbSelectOrder2.getItems().setAll(dispatchedOrderList);
            }
            cmbSelectOrder2.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                loadDataOfDispatchedOrders(newValue);
                loadTableData2(newValue);
            });


            //set data for dispatched orders

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    private void loadTableData2(String newValue) {
        try {
            temporderList2 = orderDispatchController.getAllOrderListDetails(newValue);
            tblListView2.setItems(FXCollections.observableArrayList(temporderList2));
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void loadDataOfDispatchedOrders(String newValue) {
        try {
            DispatchOrder dispatchOrder = orderDispatchController.getAllDispatchedOrderDetails(newValue);
            if (dispatchOrder != null) {
                lblBranchID2.setText(dispatchOrder.getbranchID());

                //set branch name
                String branchID = lblBranchID2.getText();
                System.out.println("Inside ldodo : " + branchID);
                Bank bankDetails = bankController.getBankNameFromID(branchID);
                lblBranchName2.setText(bankDetails != null ? bankDetails.getBranchName() : null);

                lblorderDate2.setText(dispatchOrder.getOrderData());
                lblDispatchedDate.setText(dispatchOrder.getDispatchedDate());
                lblDeliveryTeam.setText(dispatchOrder.getDeliveryTeamName());
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void loadTabledata1(String newValue) throws SQLException, ClassNotFoundException {
        temporderList = OrderController.getOrderListDetails(newValue);
        tblListView1.setItems(FXCollections.observableArrayList(temporderList));

    }

    private void setOTDLables(String newValue) {
        try {

            Order orderDetails = OrderController.getOrderDetails(newValue);
            if (orderDetails != null) {
                lblBrancID1.setText(orderDetails.getBranchID());
                lblOrderDate1.setText(orderDetails.getDate());
                String tempBankId = lblBrancID1.getText();
                Bank bankDetails = bankController.getBankNameFromID(tempBankId);

                lblBranchName1.setText(bankDetails != null ? bankDetails.getBranchName() : null);

            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void loadDate() {
        Date date = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        lblDate.setText(f.format(date));
        lblDate.setVisible(false);
    }

    public void dispatchOrder(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        DispatchOrder dispatchOrder = new DispatchOrder(cmbSelectOrder1.getSelectionModel().getSelectedItem(), lblOrderDate1.getText(), lblDate.getText(), lblBrancID1.getText(), cmbSelectDeliveryteam.getSelectionModel().getSelectedItem(), temporderList);
        if (cmbSelectOrder1.getSelectionModel().getSelectedItem() == null) {
            new Alert(Alert.AlertType.WARNING, "Select Order First.!", ButtonType.OK).showAndWait();
        } else if (cmbSelectDeliveryteam.getSelectionModel().getSelectedItem() == null) {
            new Alert(Alert.AlertType.WARNING, "Select A Delivery Team.!", ButtonType.OK).showAndWait();
        } else {
            if (new orderDispatchController().dispatchOrder(dispatchOrder)) {
                new Alert(Alert.AlertType.INFORMATION, "Order Dispatched.!").showAndWait();
                orderDispatchController.removeSavedOrderEntry(cmbSelectOrder1.getSelectionModel().getSelectedItem());
                tblListView1.getItems().clear();
                refreshPane();

            } else {
                new Alert(Alert.AlertType.WARNING, "Something Wrong,Try Again").show();
            }

        }
    }

    private void refreshPane() {
        try {
            AnchorPane pane;
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("../view/orderDetailsForm.fxml"));
            pane = loader.load();
            orderDetailsContext.getChildren().setAll(pane);
        } catch (IOException io) {
            io.printStackTrace();
        }
    }


    public void onActionCancelOrder(ActionEvent actionEvent) {
        try {
            boolean ifDeleted = OrderController.cancelOrder(cmbSelectOrder1.getSelectionModel().getSelectedItem());
            System.out.println(cmbSelectOrder1.getSelectionModel().getSelectedItem());
            if (ifDeleted){
                new Alert(Alert.AlertType.INFORMATION, "Order Canceled.!").showAndWait();
            }else{
                new Alert(Alert.AlertType.WARNING, "Something Wrong,Try Again").show();
            }
            refreshPane();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

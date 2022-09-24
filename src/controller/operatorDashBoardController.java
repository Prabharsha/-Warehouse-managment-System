package controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.ResourceBundle;

public class operatorDashBoardController implements Initializable {
    public AnchorPane operatorDashBoardContext;
    public Label lblJobTitle;
    public Label lblUserName;
    public Label lbltopLarge;
    public Label lblTime;
    public  Pane paneContextx;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
    lblUserName.setText("operator");
    lblJobTitle.setText("operator");
    loadTimeAnddate();
    }

    public void logOut(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) operatorDashBoardContext.getScene().getWindow();
        stage.close();
        Parent root = FXMLLoader.load(getClass().getResource("../view/logInForm.fxml"));
        Stage stage1 = new Stage();
        stage1.initStyle(StageStyle.TRANSPARENT);
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        stage1.setScene(scene);
        stage1.show();
    }
    private void loadTimeAnddate() {
        Date date = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
       // lblTime.setText(f.format(date));

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

    public void orderDetails(ActionEvent actionEvent) {
        try {
            lblTime.setVisible(true);
            AnchorPane pane;
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("../view/orderDetailsForm.fxml"));
            pane = loader.load();
            lbltopLarge.setText("Order Details");
            paneContextx.getChildren().setAll(pane);
        }catch (IOException io){
            io.printStackTrace();
        }
    }

    public void itemManagement(ActionEvent actionEvent) throws IOException {
        try {
            lblTime.setVisible(true);
            AnchorPane pane;
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("../view/itemManagementForm.fxml"));
            pane = loader.load();
            lbltopLarge.setText("Items Management");
            paneContextx.getChildren().setAll(pane);
        }catch (IOException io){
            io.printStackTrace();
        }
    }

    public void placeOrder(ActionEvent actionEvent) {
        try {
            lblTime.setVisible(false);
            AnchorPane pane;
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("../view/placeOrderForm.fxml"));
            pane = loader.load();
            lbltopLarge.setText("Place Order");
            paneContextx.getChildren().setAll(pane);
        }catch (IOException io){
            io.printStackTrace();
        }
    }

    public  void payements(ActionEvent actionEvent) {
        try {
            AnchorPane pane;
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("../view/paymentsForm.fxml"));
            pane = loader.load();
            lbltopLarge.setText("Payments Management");
            paneContextx.getChildren().setAll(pane);
        }catch (IOException io){
            io.printStackTrace();
        }
    }

}

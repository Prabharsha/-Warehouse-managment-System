package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class dashBoardFormController implements Initializable {
    public Label lblUserNameDis;
    public Label lblUserTitleDis;
    public Label timeLabel;
    public AnchorPane rootPane;
    public AnchorPane mainAnchor;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lblUserNameDis.setText("Administrator");
        lblUserTitleDis.setText("Administrator");

    }

    public void manageBanks(ActionEvent actionEvent) {
        try {
            AnchorPane pane;
            FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("../view/bankForm.fxml"));
            pane = fxmlLoader.load();
            rootPane.getChildren().setAll(pane);
        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    public void manageSuppliers(ActionEvent actionEvent) {
        try {
            AnchorPane pane;
            FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("../view/supplierManagementForm.fxml"));
            pane = fxmlLoader.load();
            rootPane.getChildren().setAll(pane);
        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    public void ManageEmployees(ActionEvent actionEvent) {
        try {
            AnchorPane pane;
            FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("../view/employeeManagementForm.fxml"));
            pane = fxmlLoader.load();
            rootPane.getChildren().setAll(pane);
        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    public void manageDeliveryTeams(ActionEvent actionEvent) {
        try {
            AnchorPane pane;
            FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("../view/deliveryTeamManageForm.fxml"));
            pane = fxmlLoader.load();
            rootPane.getChildren().setAll(pane);
        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    public void manageFinances(ActionEvent actionEvent) {
        try {
            AnchorPane pane;
            FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("../view/financeForm.fxml"));
            pane = fxmlLoader.load();
            rootPane.getChildren().setAll(pane);
        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    public void logOut(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) mainAnchor.getScene().getWindow();
        stage.close();
        Parent root = FXMLLoader.load(getClass().getResource("../view/logInForm.fxml"));
        Stage stage1 = new Stage();
        stage1.initStyle(StageStyle.TRANSPARENT);
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        stage1.setScene(scene);
        stage1.show();

    }


}

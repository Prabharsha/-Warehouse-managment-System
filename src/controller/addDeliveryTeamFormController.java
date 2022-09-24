package controller;

import interfaces.TableLoadEvent;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import logics.deliveryTeamController;
import model.DeliveryTeam;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class addDeliveryTeamFormController implements Initializable {
    public AnchorPane addNewTeamContext;
    public TextField txtTeamID;
    public TextField txtTeamName;
    public TextField txtTeamAddress;
    public TextField txtTeamContact;
    private TableLoadEvent event;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setEvent(TableLoadEvent event) {
        this.event = event;
    }


    public void cancel(ActionEvent actionEvent) {
        Stage stage1 = (Stage) addNewTeamContext.getScene().getWindow();
        stage1.close();
    }

    public void saveTeam(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        DeliveryTeam deliveryTeam = new DeliveryTeam(txtTeamID.getText(),txtTeamName.getText(),txtTeamContact.getText(),txtTeamAddress.getText());

        if (new deliveryTeamController().saveTeam(deliveryTeam)){
            new Alert(Alert.AlertType.INFORMATION,"Team Added.!").showAndWait();
            txtTeamID.clear();;
            txtTeamName.clear();
            txtTeamAddress.clear();
            txtTeamContact.clear();
            event.loadData();
        }
    }
}

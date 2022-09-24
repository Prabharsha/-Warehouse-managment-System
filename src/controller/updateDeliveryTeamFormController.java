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
import java.util.ResourceBundle;

public class updateDeliveryTeamFormController implements Initializable {
    public AnchorPane updateDeliveryTeamContext;
    public TextField txtTeamID;
    public TextField txtTeamName;
    public TextField txtTeaContact;
    public TextField txtTeamAddress;
    public TableLoadEvent event;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void cancel(ActionEvent actionEvent) {
        Stage stage = (Stage) updateDeliveryTeamContext.getScene().getWindow();
        stage.close();
    }

    public void saveUpdates(ActionEvent actionEvent) {
        try {
            boolean isUpdate = deliveryTeamController.updateTeam(new DeliveryTeam(
                    txtTeamID.getText(),
                    txtTeamName.getText(),
                    txtTeaContact.getText(),
                    txtTeamAddress.getText()
            ));
            if (isUpdate) {
                new Alert(Alert.AlertType.INFORMATION, "Update Successful..!").showAndWait();
                event.loadData();
                Stage stage1 = (Stage) updateDeliveryTeamContext.getScene().getWindow();
                stage1.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setEvent(TableLoadEvent event) {
        this.event = event;
    }
}

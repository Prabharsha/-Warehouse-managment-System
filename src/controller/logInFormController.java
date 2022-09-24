package controller;

import DBUtil.DbConnection;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class logInFormController implements Initializable {
    public PasswordField txtPassword;
    public TextField txtUserName;
    public AnchorPane logInContext;
    public JFXButton btnLoggIn;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


    public void exitProgramme(MouseEvent mouseEvent) {
        System.exit(0);
    }

    public void logIn(MouseEvent mouseEvent) throws IOException, SQLException, ClassNotFoundException {
       /* if (txtUserName.getText().equals("Admin") && txtPassword.getText().equals("Admin")) {
            Stage stage = (Stage) logInContext.getScene().getWindow();
            stage.close();
            URL resource = getClass().getResource("../view/dashBoardForm.fxml");
            Parent load = FXMLLoader.load(resource);
            Scene scene = new Scene(load);
            Stage stage1 = new Stage();
            stage1.setScene(scene);
            stage1.initStyle(StageStyle.UNDECORATED);
            stage1.show();
        }
        if (txtUserName.getText().equals("Op") && txtPassword.getText().equals("Op")) {
            Stage stage = (Stage) logInContext.getScene().getWindow();
            stage.close();
            URL resource = getClass().getResource("../view/operatorDashBoard.fxml");
            Parent load = FXMLLoader.load(resource);
            Scene scene = new Scene(load);
            Stage stage1 = new Stage();
            stage1.setScene(scene);
            stage1.initStyle(StageStyle.UNDECORATED);
            stage1.show();
        }*/

        //interact With dataBase
        String userName = txtUserName.getText();
        String password = txtPassword.getText();
        Connection connection = DbConnection.getInstance().getConnection();
        String query = "SELECT * FROM logInDetails WHERE userName LIKE '%"+userName+"%'";
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery(query);


        if (resultSet.next()) {


            if (userName.equals(resultSet.getString(1)) && password.equals(resultSet.getString(2))) {
                if (resultSet.getString(3).equalsIgnoreCase("admi")) {
                    Stage stage = (Stage) logInContext.getScene().getWindow();
                    stage.close();
                    URL resource = getClass().getResource("../view/dashBoardForm.fxml");
                    Parent load = FXMLLoader.load(resource);
                    Scene scene = new Scene(load);
                    Stage stage1 = new Stage();
                    stage1.setScene(scene);
                    stage1.initStyle(StageStyle.UNDECORATED);
                    stage1.show();
                }

                if (resultSet.getString(3).equalsIgnoreCase("oper")){

                    Stage stage = (Stage) logInContext.getScene().getWindow();
                    stage.close();
                    FXMLLoader loader = new FXMLLoader(this.getClass().getResource("../view/operatorDashBoard.fxml"));
                    Parent parent  = loader.load();
                    /*URL resource = getClass().getResource("../view/operatorDashBoard.fxml");
                    Parent load = FXMLLoader.load(resource);*/
                    operatorDashBoardController controller = loader.getController();
                    controller.lblUserName.setText(userName);
                    Scene scene = new Scene(parent);
                    Stage stage1 = new Stage();
                    stage1.setScene(scene);
                    stage1.initStyle(StageStyle.UNDECORATED);
                    stage1.show();
                }
                }else {new Alert(Alert.AlertType.WARNING,"Wrong UserName or Password,Try Again.!").showAndWait();}
            }
        }

    public void newUser(MouseEvent mouseEvent) {
    }



}

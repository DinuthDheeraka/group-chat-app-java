/**
 * @author :  Dinuth Dheeraka
 * Created : 8/9/2022 1:29 PM
 */
package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import util.Navigations;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginFormController implements Initializable {

    public TextField txtUserName;
    private Parent parent;
    private Scene scene;
    private Stage stage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void minimizeBtnOnAction(ActionEvent actionEvent) {
    }

    public void closeBtnOnAction(ActionEvent actionEvent) {
    }

    public void loginBtnOnAction(ActionEvent actionEvent) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/Client-Form.fxml"));

        try {
            parent = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Transfer User name
        ClientFormController controller = fxmlLoader.getController();
        controller.setUserName(txtUserName.getText());

        stage = new Stage();
        scene = new Scene(parent);
        stage.setScene(scene);

        Navigations.getInstance().transparentUi(stage,scene);
    }
}

/**
 * @author :  Dinuth Dheeraka
 * Created : 8/9/2022 11:35 AM
 */
package controller;

import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class ClientFormController implements Initializable {

    private Socket socket;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            socket  = new Socket("localhost",7777);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

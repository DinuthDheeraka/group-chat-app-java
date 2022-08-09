/**
 * @author :  Dinuth Dheeraka
 * Created : 8/9/2022 11:35 AM
 */
package controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import util.Navigations;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class ClientFormController implements Initializable {

    public TextArea txtChat;
    public TextField txtMessage;
    private Socket socket;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;
    private String userName;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        try {
//            socket  = new Socket("localhost",7777);
//
//            dataInputStream = new DataInputStream(socket.getInputStream());
//            dataOutputStream = new DataOutputStream(socket.getOutputStream());
//
//            while (true){
//                txtChat.appendText("\n"+userName+" : "+dataInputStream.readUTF());
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    public void connect(String userName){
        try {
            socket  = new Socket("localhost",7777);

            dataInputStream = new DataInputStream(socket.getInputStream());
            dataOutputStream = new DataOutputStream(socket.getOutputStream());

            while (true){
                txtChat.appendText("\n"+userName+" : "+dataInputStream.readUTF());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendBtnOnAction(ActionEvent actionEvent) {
        try {
            dataOutputStream.writeUTF(txtMessage.getText());
            dataOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void minimizeBtnOnAction(ActionEvent actionEvent) {
        Navigations.getInstance().minimizeStage(actionEvent);
    }

    public void closeBtnOnAction(ActionEvent actionEvent) {
        Navigations.getInstance().closeStage(actionEvent);
    }
}

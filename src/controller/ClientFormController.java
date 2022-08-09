/**
 * @author :  Dinuth Dheeraka
 * Created : 8/9/2022 11:35 AM
 */
package controller;

import com.jfoenix.controls.JFXButton;
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
    public JFXButton sendBtn;
    private Socket socket;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;
    private String userName;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        new Thread(()->{
            try {
                socket  = new Socket("localhost",9999);

                dataInputStream = new DataInputStream(socket.getInputStream());
                dataOutputStream = new DataOutputStream(socket.getOutputStream());

                while (true){
                    System.out.println(dataInputStream.readUTF());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void sendBtnOnAction(ActionEvent actionEvent) {
        try {
            if(sendBtn.getText().equals("Connect")){
                sendBtn.setText("Send");
                dataOutputStream.writeUTF(userName);
                dataOutputStream.flush();
            }else{
                dataOutputStream.writeUTF(txtMessage.getText());
                dataOutputStream.flush();
            }
        }catch (IOException e){

        }
    }

    public void minimizeBtnOnAction(ActionEvent actionEvent) {
        Navigations.getInstance().minimizeStage(actionEvent);
    }

    public void closeBtnOnAction(ActionEvent actionEvent) {
        Navigations.getInstance().closeStage(actionEvent);
    }

    public void setUserName(String userName){
        this.userName = userName;
    }
}

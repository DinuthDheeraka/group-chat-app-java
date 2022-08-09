/**
 * @author :  Dinuth Dheeraka
 * Created : 8/9/2022 11:35 AM
 */
package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import model.Client;
import util.Navigations;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.ResourceBundle;

public class ServerFormController implements Initializable {

    public TableView tblClients;
    private ObservableList tblClientsList = FXCollections.observableArrayList();
    private LinkedHashMap<String,Client> clients;
    private ServerSocket serverSocket = new ServerSocket(9999);

    public ServerFormController() throws IOException {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        new Thread(()->{
            while (true){
                try {
                    Socket socket = serverSocket.accept();
                    System.out.println("Added client");
                    DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
                    DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());

                    new Thread(()->{
                        while (true){
                            try {
                                System.out.println(dataInputStream.readUTF());
                                if(dataInputStream.readUTF().startsWith("USER_NAME:")){
                                    clients.put(dataInputStream.readUTF().replace("USER_NAME:",""),new Client(
                                            dataInputStream.readUTF().replace("USER_NAME:",""),socket,
                                            dataInputStream,dataOutputStream
                                    ));
                                    System.out.println("added client");
                                }else{
                                    System.out.println(dataInputStream.readUTF());
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void minimizeBtnOnAction(ActionEvent actionEvent) {
        Navigations.getInstance().minimizeStage(actionEvent);
    }

    public void closeBtnOnAction(ActionEvent actionEvent) {
//        try {
            //serverSocket.close();
            System.exit(1);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}

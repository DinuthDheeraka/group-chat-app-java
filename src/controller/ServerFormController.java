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
    private ServerSocket serverSocket;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//          while (true){
//              try {
//                  Socket socket = serverSocket.accept();
//
//                  DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
//                  DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
//
//                  //adding a new client(user)
//                  clients.put(dataInputStream.readUTF(),new Client(dataInputStream.readUTF(),socket,dataInputStream,dataOutputStream));
//
//                  //implement live chatting
//                  new Thread(()->{
//                      String message = "";
//                      while (true){
//                          try {
//                             message  = clients.get(dataInputStream.readUTF()).getUserName()+" : "+dataInputStream.readUTF();
//                          } catch (IOException e) {
//                              e.printStackTrace();
//                          }
//                          for(String key : clients.keySet()){
//                              try {
//                                  Client client = clients.get(key);
//                                  client.getDataOutputStream().writeUTF(message);
//                                  client.getDataOutputStream().flush();
//                              } catch (IOException e) {
//                                  e.printStackTrace();
//                              }
//                          }
//                      }
//                  }).start();
//              } catch (IOException e) {
//                  e.printStackTrace();
//              }
//          }
    }

    public void minimizeBtnOnAction(ActionEvent actionEvent) {
        Navigations.getInstance().minimizeStage(actionEvent);
    }

    public void closeBtnOnAction(ActionEvent actionEvent) {
        Navigations.getInstance().closeStage(actionEvent);
    }
}

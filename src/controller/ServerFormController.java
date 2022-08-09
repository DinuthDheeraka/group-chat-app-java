/**
 * @author :  Dinuth Dheeraka
 * Created : 8/9/2022 11:35 AM
 */
package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import model.Client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ServerFormController implements Initializable {

    public TableView tblClients;
    private ObservableList tblClientsList = FXCollections.observableArrayList();
    private ArrayList<Client> clients;
    private ServerSocket serverSocket;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        new Thread(()->{
          while (true){
              try {
                  Socket socket = serverSocket.accept();

                  DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
                  DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());

                  //adding a new client(user)
                  clients.add(new Client(dataInputStream.readUTF(),socket,dataInputStream,dataOutputStream));

                  //set client table data
                  tblClientsList.add(dataInputStream.readUTF());
                  tblClients.setItems(tblClientsList);
                  tblClients.refresh();

                  //implement live chatting
                  new Thread(()->{
                      String message = null;
                      while (true){
                          try {
                             message  = dataInputStream.readUTF();
                          } catch (IOException e) {
                              e.printStackTrace();
                          }
                          for(Client client : clients){
                              try {
                                  client.getDataOutputStream().writeUTF(message);
                                  client.getDataOutputStream().flush();
                              } catch (IOException e) {
                                  e.printStackTrace();
                              }
                          }
                      }
                  }).start();
              } catch (IOException e) {
                  e.printStackTrace();
              }
          }
        }).start();
    }
}

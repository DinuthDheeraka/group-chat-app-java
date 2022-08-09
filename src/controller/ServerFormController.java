/**
 * @author :  Dinuth Dheeraka
 * Created : 8/9/2022 11:35 AM
 */
package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Client;
import util.Navigations;
import view.tm.ClientTM;

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

    public TableView<ClientTM> tblClients;
    public TableColumn colClients;
    private ObservableList<ClientTM> tblClientsList = FXCollections.observableArrayList();
    private LinkedHashMap<String,Client> clients = new LinkedHashMap();
    private ServerSocket serverSocket = new ServerSocket(9999);

    public ServerFormController() throws IOException {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colClients.setCellValueFactory(new PropertyValueFactory("userName"));
        new Thread(()->{
            while (true){
                try {
                    Socket socket = serverSocket.accept();
                    System.out.println("client accepted..");
                    DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
                    DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());

                    new Thread(()->{
                        while (true){
                            try {
                                String message = dataInputStream.readUTF();
                                if(message.startsWith("USER_NAME: ")){
                                    System.out.println(message.replace("USER_NAME: ",""));
                                    String newUser = message.replace("USER_NAME: ","");
                                    clients.put(message.replace("USER_NAME: ",""),new Client(
                                            message.replace("USER_NAME: ",""),socket,
                                            dataInputStream,dataOutputStream
                                    ));
                                    System.out.println("added client to list");

                                    //adding new client to table
                                    tblClientsList.add(new ClientTM(message.replace("USER_NAME: ","")));
                                    tblClients.setItems(tblClientsList);
                                    tblClients.refresh();

                                    //Sending connected user Info
                                    for(String s : clients.keySet()){
                                        Client client = clients.get(s);
                                        client.getDataOutputStream().writeUTF(newUser+" joined");
                                    }

                                }else{
                                    System.out.println(dataInputStream.readUTF());
                                    //Sending messages to all connected users
                                    for(String s : clients.keySet()){
                                        Client client = clients.get(s);
                                        client.getDataOutputStream().writeUTF(message);
                                    }
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

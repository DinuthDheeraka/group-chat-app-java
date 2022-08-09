/**
 * @author :  Dinuth Dheeraka
 * Created : 8/9/2022 11:40 AM
 */
package model;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class Client {
    String userName;
    Socket socket;
    DataInputStream dataInputStream;
    DataOutputStream dataOutputStream;
}

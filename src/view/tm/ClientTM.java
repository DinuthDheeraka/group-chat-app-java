/**
 * @author :  Dinuth Dheeraka
 * Created : 8/10/2022 2:17 AM
 */
package view.tm;

public class ClientTM {
    private String userName;

    public ClientTM() {
    }

    public ClientTM(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}

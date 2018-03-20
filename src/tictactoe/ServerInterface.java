package tictactoe;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerInterface extends Remote {

    int connect() throws RemoteException;

    void disconnect(int id) throws RemoteException;

    int checkPlayerStatus(int id) throws RemoteException;

    Boolean selectField(int id, int number) throws RemoteException;

    Board getBoard() throws RemoteException;
}

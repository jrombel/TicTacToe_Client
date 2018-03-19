package tictactoe;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerInterface extends Remote {

    int connect() throws RemoteException;

    void disconnect(int id) throws RemoteException;

    int checkPlayerStatus(int id) throws RemoteException;

    Boolean checkWhoseMove() throws RemoteException;

    Board getBoard() throws RemoteException;

    Boolean makeMove(Board board) throws RemoteException;
}

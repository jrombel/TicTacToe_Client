package tictactoe;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerInterface extends Remote {

    Boolean checkWhoseMove() throws RemoteException;

    Board getBoard() throws RemoteException;

    Boolean makeMove(Board board) throws RemoteException;

    String test(String testString) throws RemoteException;
}

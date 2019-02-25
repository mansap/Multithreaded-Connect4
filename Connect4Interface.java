/**
 * FileName  : Connect4Interface.java
 * 
 * Version   : 1.0
 * 
 * Author(s) : Mansa Pabbaraju
 * 
 * Revisions : Initial Revision
 * 
 */

/**
 *
 * Description : 
 * 
 * The class Connect4Interface interface for Connect4 game.
 * 
 *
 */
 
import java.rmi.RemoteException;

public interface Connect4Interface extends java.rmi.Remote
{
    public int getMove() throws RemoteException;
    public void setMove(int move) throws RemoteException;
    public String getStringBoard() throws RemoteException;
    public void setBoard(char[][] board) throws RemoteException;
    public void setMessage(String message) throws RemoteException;
    public String getMessage() throws RemoteException;
    public int getIndex() throws RemoteException;
    public void setIndex(int index) throws RemoteException;
    public void setFlag(boolean val) throws RemoteException;
    public boolean getFlag() throws RemoteException;
    public void serverSetMessage(boolean val) throws RemoteException;
    public boolean serverGetMessage() throws RemoteException;
}

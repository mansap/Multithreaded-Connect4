/**
 * FileName  : Connect4Implementation.java
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
 * The class Connect4Implementation executes model paradigm for Connect4 game.
 * 
 *
 */
 
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

@SuppressWarnings("serial")
public class Connect4Implementation extends UnicastRemoteObject implements Connect4Interface
{
    protected Connect4Implementation() throws RemoteException 
    {
    }
    
    public int move;
    public char[][] board;
    public String message;
    public int index;
    public boolean flag = false;
    public boolean serverFlag = false;
    public boolean clientFlag = true;
    
    public void serverSetMessage(boolean val) throws RemoteException
    {
        this.serverFlag = val;
    }
    
    public boolean serverGetMessage() throws RemoteException
    {
        return this.serverFlag;
    }
    
    public int getMove() throws RemoteException
    {
        return this.move;
    }

    public void setMove(int move) throws RemoteException
    {
        this.move = move;
    }
    
    public String getStringBoard()
    {
        StringBuilder boardStr = new StringBuilder();
        for(int rowIndex = 0; rowIndex < 9; rowIndex++)
        {
            for(int colIndex = 0; colIndex < 25; colIndex++)
            {
                boardStr.append(String.valueOf(this.board[rowIndex][colIndex]));
            }
            boardStr.append("\r\n");
        }
        return boardStr.toString();
    }

    public void setBoard(char[][] board) 
    {
        this.board = board;    
    }

    public void setMessage(String message) 
    {
        this.message = message;
        
    }
    public void setFlag(boolean val) 
    {
        this.flag = val;
    }
    public boolean getFlag() 
    {
        return this.flag;
    }
    
    public String getMessage() 
    {
        return this.message;
    }
    
    public int getIndex()
    {
        return this.index;
    }
    public void setIndex(int index)
    {
        this.index = index;
    }    
}

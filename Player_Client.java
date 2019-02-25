/**
 * FileName  : Player_Client.java
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
 * The class Player_Client executes client implementation for Connect4 game.
 * 
 *
 */
 
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Player_Client
{
    private Registry regM;
    Connect4Interface obj;
    
    public Player_Client(int id, String host, int port) throws Exception 
    {
        regM = LocateRegistry.getRegistry(host,port);
        obj = (Connect4Interface)regM.lookup("game"+id);
    }
    public void play() throws Exception 
    {
        Player_Controller playerController = new Player_Controller();
        String responseServer;
        try 
        {
            while (true) 
            {
                if(obj.getFlag())
                {
                    responseServer = obj.getMessage();
                    obj.setFlag(false);
                    System.out.println(responseServer);
                    if(responseServer.startsWith("Y"))
                    {
                        playerController.printBoard(responseServer);                    
                        responseServer = obj.getStringBoard();
                        playerController.printBoard(responseServer);
                        obj.serverSetMessage(true);                   
                        break;                    
                    }
                    
                    else if(responseServer.startsWith("Wi"))
                    {
                        playerController.printBoard("Game over!, Winner is "+responseServer.charAt(10));                    
                        responseServer = obj.getStringBoard();
                        playerController.printBoard(responseServer);
                        obj.serverSetMessage(true);
                        break;
                    }
                    else if(responseServer.startsWith("D"))
                    {
                        playerController.printBoard("Game over, it's a DRAW! ");                    
                        responseServer = obj.getStringBoard();
                        playerController.printBoard(responseServer);
                        obj.serverSetMessage(true);
                        break;
                    }
                    
                    else
                    {
                        if(responseServer.startsWith("M"))
                        {
                            int index = obj.getIndex();
                            int i = playerController.askControllerMove(index);
                            obj.setMove(i);
                            /*Now server's turn*/
                            obj.serverSetMessage(true);
                        }
                        
                        if(responseServer.startsWith("C"))
                        {
                            responseServer = obj.getStringBoard();
                            playerController.printBoard(responseServer);
                            /*Now server's turn*/
                            obj.serverSetMessage(true);
                        }
                    }
                }
            }
        }
        catch (Exception e) 
        {
            System.out.println("Player Client exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

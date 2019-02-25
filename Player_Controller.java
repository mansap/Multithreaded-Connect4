/**
 * FileName  : Player_Controller.java
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
 * The class Player_Controller executes controller implementation for Connect4 game.
 * 
 *
 */

public class Player_Controller 
{
    private static Player_View playerView = new Player_View();
    
    public int askControllerMove(int index)
    {         
        return playerView.askUserMove(index);
    }
    
    public void printBoard(String response)
    {
        playerView.printBoardFromView(response);
    } 
    
    public static void main(String argv[]) throws Exception 
    {
    	int id = 0;
    	int port = 1099;
    	String host = "127.0.0.1";
    	    	
        for (int i = 0; i < argv.length; i ++)
        {
            if (argv[i].equals("-id")) 
            {
               id = new Integer(argv[++i]).intValue();
               System.out.println(id);
            }
            else if (argv[i].equals("-port"))
            {
                port = new Integer(argv[++i]).intValue();
                System.out.println(port);
            }
            else if (argv[i].equals("-host"))
            {
                host = new String(argv[++i]);
                System.out.println(host);
            }
        }
        Player_Client a = new Player_Client(id,host,port);
        a.play();
    }   
}

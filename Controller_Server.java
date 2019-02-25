/**
 * FileName  : Controller_Server.java
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
 * The class Controller_Server executes server implementation for Connect4 game.
 *
 */
 
import java.io.IOException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Controller_Server 
{    
    public static void main( String[] args ) throws IOException, InterruptedException 
    {    
        Registry reg;
        int port = 1099;
        int num = 0;
        int current = 0;
        
        for (int i = 0; i < args.length; i ++)
        {
            if (args[i].equals("-port")) 
            {
               port = new Integer(args[++i]).intValue();
               System.out.println(port);
            }
            else if (args[i].equals("-num"))
            {
                num = new Integer(args[++i]).intValue();
                System.out.println(num);
            }
        }
         reg = LocateRegistry.createRegistry(port);
         char[][] board = new char[9][25];
         Connect4View view = new Connect4View();
         Connect4Field aConnect4Field;
         Thread player1;
         int icon = 34;
        
        try
        {    
            while(current < num)
            {                
                Connect4Interface obj = new Connect4Implementation();
                reg.rebind("game"+current, obj);
                System.out.println("game"+current);           
                aConnect4Field = new Connect4Field(obj,board,current,(char)(++icon), current);            
                player1 = new Thread(aConnect4Field);            
                aConnect4Field.addObserver(view);
                current++;    
                aConnect4Field.setPlayerNum(num);
                aConnect4Field.init();
                player1.start();            
            }            
            Connect4Field.flag1 =true;
        }
        catch (Exception e) 
        {
            System.out.println("Connect4 Server registry error: " + e.getMessage());
            e.printStackTrace();
        }
        
    }
}
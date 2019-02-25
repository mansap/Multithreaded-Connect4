/**
 * FileName  : Connect4View.java
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
 * The class Connect4View implements View in MVC paradigm.
 * 
 *
 */
import java.util.*;
public class Connect4View implements Observer
{ 

    /**
     * Description: implements update method of Observer interface.
     * 
     * @param: Observable a, Object o
     *
     * @return: None.
     *
     */
    public void update( Observable a, Object o ) 
    {
        if(o == null)
        {
            System.out.println("Incorrect Input!");
        }
        else if (o instanceof char[][])
        {
            printBoard((char[][]) o);           
        }
        else if (o instanceof String)
            System.out.println(o);
    }
        
    /**
     * Description: Returns the board array in string form.
     * 
     * @param: None
     *
     * @return: String of board.
     *
     */
    public void printBoard(char[][] board)
    {
        StringBuilder boardStr = new StringBuilder();
        for(int rowIndex = 0; rowIndex < 9; rowIndex++)
        {
            for(int colIndex = 0; colIndex < 25; colIndex++)
            {
                boardStr.append(String.valueOf(board[rowIndex][colIndex]));
            }
            boardStr.append("\r\n");
        }
        System.out.println( boardStr.toString());
    }    
}
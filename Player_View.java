/**
 * FileName  : Player_View.java
 * 
 * Version   : 1.0
 * 
 * Revisions : Initial Revision
 * 
 */

/**
 *
 * Description : 
 * 
 * The class Player_View executes controller implementation for Connect4 game.
 * 
 *
 */
 
import java.util.Scanner;

public class Player_View 
{
    /**
     * Description: asks player for next move.
     * 
     * @param: index of player to be asked to.
     *
     * @return: next move.
     *
     */
    @SuppressWarnings("resource")
    public int askUserMove(int index)
    {
        Scanner input = new Scanner(System.in);
        int move = 0;
        System.out.println("Enter column, Player " + index);
        move = input.nextInt();
        return move;
    }
    
    public void printBoardFromView(String response)
    {
        System.out.println(response);
    }
}

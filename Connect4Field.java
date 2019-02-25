/**
 * FileName  : Connect4Field.java
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
 * The class Connect4Field executes model paradigm for Connect4 game.
 * 
 *
 */
import java.io.*;
import java.util.*;

public class Connect4Field extends Observable implements Runnable
{
    public char[][] board;
    int lastColumn, lastRow = 0;
    char lastChar;
    int row, column;
    int name; 
    char gamePiece;
    int index;
    static boolean[] playerFlags;
    static boolean gameIsOver = false;
    static boolean gameWon = false;
    Connect4Interface obj;
    static int winnerIndex;
    static boolean flag1 = false;
    static int count = 0;
    static boolean firstFlag =true;
    static int num;
    
    
    Connect4Field(Connect4Interface obj, char[][] board1,int name, char gamePiece, int index)
    {
        this.obj = obj;
        this.board = board1;
        this.name = name;
        this.gamePiece = gamePiece;
        this.index = index;
    }
    
    public void boardReset()
    {
        for(int rowIndex = 0; rowIndex < 9; rowIndex++)
        {
            for(int colIndex = 0; colIndex < 25; colIndex++)
            {
                board[rowIndex][colIndex] = ' ';
            }
        }
    }
    
    /**
     * Description: setPlayerNum sets the number of players in this game.
     * 
     * @param: num1.
     *
     * @return: None.
     *
     */
    public void setPlayerNum(int num1)
    {
        num = num1;
        playerFlags = new boolean[num];
    }
    
    /**
     * Description: boardInit initializes as per shape of given Connect4 board.
     * 
     * @param: None.
     *
     * @return: None.
     *
     */ 
    
    public void boardInit()
    {
        
        int var_row = 0;
        int var_col = 25;
        for (row = 0; row < 9; row++)
        {    
            for(column = var_row; column < var_col; column++)
            {
                board[row][column] = '.';
            }
            var_row = var_row +1;
            var_col = var_col - 1;
        }
    }
   
    /**
     * Description: insertAtEmptySpace inserts disc at a free location on board 
     *                            incase a best possible location is not found.
     * 
     * @param: None.
     *
     * @return: None.
     *
     */ 
     
    public void insertAtEmptySpace()
    {
        row = 0;
        column = 3;
        boolean flag = false;
        do
        {
            row = 0;
            do
            {
                if(board[row][column] == '.')
                {
                    flag = true;
                }
                else
                {
                   row++;
                }
            }while((flag == false) && (row < 9));
            column++;
        }while((flag == false) && (column < 22));
    }

    /**
     * Description: Checks whether column where piece is to be dropped is valid.
     * 
     * @param: column
     *
     * @return: flag
     *
     */    
    
    public boolean checkIfPiecedCanBeDroppedIn(int column)
    {
        boolean flag;
        if((column < 0) || (column >=25))
        {
            flag = false;
        }
        else
        {
            int row = 0;
            flag = false;
            do
            {
                if(board[row][column] == '.')
                {
                    flag = true;
                }
                row++;
            }while((flag == false) && (row < 9));
        }
        return flag;
    }

    /**
     * Description: Drops the gamePiece in the specified column number.
     * 
     * @param: column, gamePiece
     *
     * @return: None
     *
     */
    public void dropPieces(int column, char gamePiece)
    {
        row = 8;
        boolean flag = false;
        do
        {
            /*Check where the gamePiece should be dropped*/
            if(board[row][column] == '.')
            {
                /*Drop here*/
                board[row][column] = gamePiece;
                lastChar = gamePiece;
                lastColumn = column;
                lastRow = row;
                flag = true;
                setChanged();
                notifyObservers(this.board);
            }            
            row--;
        }while((flag == false) && (row >= 0));    
    }

    
    /**
     * Description: Checks for win.
     * 
     * @param: None.
     *
     * @return: flag id last was win.
     *
     */
    public boolean didLastMoveWin()
    {
        boolean flag = false;
        flag = checkRowWin(lastChar);
        if(flag != true)
        {
            flag = checkColumnWin(lastChar);
            if(flag != true)
            {
                flag = checkDiagonalWin(lastChar);
            }
        }
        return flag;
    }

    /**
     * Description: Checks whether 4 in a row.
     * 
     * @param: check, gamePiece.
     *
     * @return: flag whether row win.
     *
     */
    public boolean checkRowWin(char gamePiece)
    {
        boolean flag = false;
        row = 8;
        do
        {
            column = 0;
            do
            {            
                if((board[row][column] == gamePiece)
                      && (board[row][column+1] == gamePiece)
                         && (board[row][column+2] == gamePiece) 
                           && (board[row][column+3] == gamePiece))
                    {                  
                        /*4 in a row found!*/
                        flag =  true;
                    }
                column++;
                
            }while((column < 22) && (flag == false));
         row--;
        }while((row >= 0 )&& (flag == false));        
        return flag;
    }
    
    /**
     * Description: Checks whether 4 pieces are together in a column.
     * 
     * @param: check, gamePiece
     *
     * @return: flag
     *
     */
    public boolean checkColumnWin(char gamePiece)
    {        
        boolean flag = false;       
        column = 0;
        do
        {
            row = 8;
            do
            {
                if((board[row][column] == gamePiece)
                   && (board[row-1][column] == gamePiece)
                      && (board[row-2][column] == gamePiece) 
                        && (board[row-3][column] == gamePiece))
                      
                {
                    /*4 in a row found!*/
                    flag =  true;
                }
                row--;                    
               
            }while((row >= 3 )&& (flag == false)); 
         column++;
        }while((column < 24) && (flag == false));       
        return flag;
    }
    
    /**
     * Description: Checks whether 4 pieces are together in a diagonal form.
     * 
     * @param: check, gamePiece
     *
     * @return: flag
     *
     */
    public boolean checkDiagonalWin(char gamePiece)
    {
        boolean flag = false;
        for(row = 8; row >= 3; row--)//row
        {
            /*right up*/
            for(column = 0; column <22; column ++)
            {
                if((board[row][column] == gamePiece) 
                  && (board[row-1][column+1] == gamePiece) 
                    && (board[row-2][column+2] == gamePiece) 
                      && (board[row-3][column+3] == gamePiece))                  
                {
                    flag = true;
                    break;
                }                 
            }
            /*left up*/
            for(column = 22; column >= 0; column --)
            {
                    
                if((board[row][column] == gamePiece) 
                    && (board[row-1][column-1] == gamePiece) 
                      && (board[row-2][column-2] == gamePiece) 
                        && (board[row-3][column-3] == gamePiece))                  
                {
                    flag = true;
                    break;
                }
                    
            }
        }        
        return flag;
    }
    
    /**
     * Description: Checks whether tgame has reached a draw state.
     * 
     * @param: None
     *
     * @return: flag -  result of check.
     *
     */
    public boolean isItaDraw()
    {
        boolean flag = false;
        row =0;
        column =0;
        int count = 0;
        
        /*Check if any place is still left in the board*/ 
        do
        {
            column = 0;
            do
            {
                if(board[row][column] == '.')
                {                   
                    count++;
                }               
                column++;
            }while((column <25)&&(flag == false));
            row++;
        }while((row<9)&& (flag == false));
        if(count == 1)
        {
            flag = true;
        }
        return flag;
    }
    
    /**
     * Description: Initializes game parameters.
     * 
     * @param: aplayer, bplayer
     *
     * @return: None
     *
     */
    public void init()
    {
       boardReset();
       boardInit();
       setChanged();
       notifyObservers(board);
       playerFlags[0] = true;
       for(int index = 1; index < num; index++)
       {
           playerFlags[index] = false;
       }
    }
    
    public void run()
    {
        while(true)
        {
        while(flag1)
        {
            if(!gameIsOver)
            {
                try 
                {
                    playTheGame();
                } 
                catch (InterruptedException | IOException e) 
                {
                    e.printStackTrace();
                }
            }
            else
            {
                
                if(firstFlag)
                {
                    for(int index = 0; index < num; index++)
                    {
                        playerFlags[index] = true;
                    }
                    firstFlag = false;
                }
                if(playerFlags[this.index])
                {
                    if(gameWon)
                    {
                        String winner = Integer.toString(winnerIndex);
                        try
                        {
                            obj.setMessage("Winner is " + winnerIndex);
                            obj.setBoard(this.board);
                            obj.setFlag(true);
                            while(!obj.serverGetMessage())
                            {
                                Thread.sleep(100);
                            }
                            obj.serverSetMessage(false);
                        }
                        catch(Exception e) 
                        {
                            e.printStackTrace();
                        }
                        
                    }
                    else
                    {                        
                        try
                        {
                            obj.setMessage("Draw");
                            obj.setBoard(this.board);
                            obj.setFlag(true);
                            while(!obj.serverGetMessage())
                            {
                                Thread.sleep(100);
                            }
                            obj.serverSetMessage(false);
                        }
                        catch(Exception e) 
                        {
                            e.printStackTrace();
                        }
                    }
                    playerFlags[this.index] = false;
                    count++;
                }
                if(count == num-1)
                {
                    System.out.println("Done!");
                    flag1 = false;
                }
            }
        }
        }
    }
    
    /**
     * Description: Begins the Connect4 game.
     * 
     * @param: None
     *
     * @return: None
     *
     */
    public void playTheGame() throws InterruptedException, IOException      
    {
        synchronized(board)
        {
            if(playerFlags[this.index] && !gameIsOver)
            {
                Integer column;
                                       
                if ( isItaDraw() )     
                {
                    setChanged();
                    notifyObservers("Draw");
                    gameIsOver = true;
                } 
                else 
                {
                    obj.setMessage("Current Board");
                    obj.setBoard(this.board);
                    obj.setFlag(true);
                    while(!obj.serverGetMessage())
                    {
                        Thread.sleep(100);
                    }
                    obj.serverSetMessage(false);
                    
                    obj.setMessage("Move");
                    obj.setIndex(index);
                    obj.setFlag(true);
                    playerFlags[this.index] = false;
                    while(!obj.serverGetMessage())
                    {
                        Thread.sleep(100);
                    }
                    obj.serverSetMessage(false);
                    
                    column = obj.getMove();
                    System.out.println(column);
                    if((column > 24) || (column < 0))
                    {
                        setChanged();
                        notifyObservers("Cannot be dropped!");
                        /*Set next players flag as true*/
                        playerFlags[(this.index + 1) % num] = true;
                    }
                    else
                    {
                        dropPieces(column, this.gamePiece);
                        if ( didLastMoveWin() ) 
                        {
                            gameIsOver = true;
                            gameWon = true;
                            winnerIndex = this.index;
                            setChanged();
                            notifyObservers("The winner is: " + this.name + "\n");
                            setChanged();
                            notifyObservers(this.board);
                            
                            obj.setMessage("You are the winner!");
                            obj.setBoard(this.board);
                            obj.setFlag(true);
                            while(!obj.serverGetMessage())
                            {
                                Thread.sleep(100);
                            }
                            obj.serverSetMessage(false);
                            
                            playerFlags[(this.index + 1) % num] = false;
                        }
                        else
                        {
                            /*Set next players flag as true*/
                            playerFlags[(this.index + 1) % num] = true;
                        }
                    }
                } 
                board.notifyAll();
                board.wait();
            }
        }        
    }
    
    /**
     * Description: Converts the char[][] board into String form.
     * 
     * @param: char[][] board
     *
     * @return: String form of the Connect4 board.
     *
     */
    public String stringBoard(char[][] board)
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
        return boardStr.toString();
    }
}

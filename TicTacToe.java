import java.util.Scanner;
import static java.lang.System.out;

public class TicTacToe
{
    static char PLACEHOLDER = '.';
    static Scanner keyboard = new Scanner(System.in);
    public static void printWelcome()
    {
        System.out.println("Welcome to Tic Tac Toe!");
        System.out.println("To play, enter a number for which box to play in.");
        System.out.println("1 2 3");
        System.out.println("4 5 6");
        System.out.println("7 8 9");
        System.out.println("You'll need a buddy to play with.  Ready to begin?  Here we go.");

    }
    public static void drawBoard(char[][] board)
    {
        for(int row=0;row<3;row++)
        {
            for(int column=0;column<3;column++)
            {
                out.print(board[row][column]);
                if(column<2)
                {
                    out.print("|");
                }

            }
            out.println();
        }
    }

    public static char[][] createEmptyBoard()
    {
        char[][] gameBoard = new char[3][3];
        for (int row = 0; row < 3; row++)
        {
            for (int col = 0; col < 3; col++)
            {
                gameBoard[row][col] = PLACEHOLDER;
            }
        }
        return gameBoard;
    }

    public static boolean isWin(char[][] board)
    {
        return isHorizontalWin(board) || isVerticalWin(board) || isDiagonalWin(board);
    }

    public static boolean isFull(char[][] board) 
    {
        int counter=0;

        for (int row = 0; row < 3; row++)
        {
            for (int column = 0; column < 3; column++)
            {
                if (board[row][column] != PLACEHOLDER)
                {
                    counter++;
                }

            }

        }
        if(counter==9)
        {
            return true;
        }
        return false;

    }

    public static boolean isHorizontalWin(char[][] board)
    {
        for (int row = 0; row < 3; row++)
        {
            if (board[row][0] != PLACEHOLDER && board[row][0] == board[row][1]
            && board[row][1] == board[row][2])
            {
                return true;
            }
        }
        return false;
    }

    public static boolean isVerticalWin(char[][] board)
    {
        for (int column = 0; column < 3; column++)
        {
            if (board[0][column] != PLACEHOLDER && board[0][column] == board[1][column]
            && board[1][column] == board[2][column])
            {
                return true;
            }
        }
        return false;

    }
    public static boolean isDiagonalWin(char[][] board)
    {

        if((board[0][0])=='X' && (board[1][1])=='X' && (board[2][2])=='X')
        {
            return true;
        }
        if(board[0][0]=='O' && board[1][1]=='O' && board[2][2]=='O')
        {
            return true;
        }
        if(board[2][0]=='X'&&board[1][1]=='X'&&board[0][2]=='X')
        {
            return true;
        }
        if(board[2][0]=='O'&&board[1][1]==')'&&board[0][2]=='O')
        {
            return true;
        }

        return false;

    }   
    public static boolean wantsToPlayAgain()
    {

        System.out.print("Would you like to play again?");
        String wantsToPlayAgain=keyboard.next();
        if(wantsToPlayAgain.equalsIgnoreCase("yes"))
        {
            return true;
        }
        else
        {
            return false;
        }

    }

    public static char getTokenAtPosition(int position, char[][] board)
    {
        int row = (position - 1) / 3;
        int col = (position - 1) % 3;
        return board[row][col];
    }

    public static void placeToken(int position, char[][] board, boolean isXTurn)
    {
        //determine if the turn is x or o, place token at that spot, and then call draw board method.
        int row = (position - 1) / 3;
        int col = (position - 1) % 3;
        if(isXTurn)
        {
            board[row][col]='X';
        }
        else
        {
            board[row][col]='O';
        }
    }

    public static void getPositionAndPlaceToken(char[][] board, boolean isXTurn)
    {
        boolean invalidInput = true;
        boolean full = true;
        int position = 0;

        do {
            out.print("?");
            position=keyboard.nextInt();
            if(position<1||position>9)
            {
                invalidInput=true;       

                out.println("That's an invalid choice. Try again.");
                out.print("?");
                position=keyboard.nextInt();
            }
            char positionValue=getTokenAtPosition(position, board);

            if(positionValue==PLACEHOLDER)
            {
                invalidInput=false;
                full=false;
            }
            else
            {
                full=true;
            }

        } while (full || invalidInput);
        placeToken(position, board, isXTurn);
    }

    public static void game()
    {
        int xWins = 0;
        int oWins = 0;
        int draws = 0;
        boolean doesXStart = true;
        boolean isXTurn = doesXStart;

        printWelcome();

        do 
        {
            char[][] gameBoard = createEmptyBoard();
            isXTurn = doesXStart;
            doesXStart = ! doesXStart;
            boolean gameStillGoing = true;
  
            if(isXTurn)
            {
                out.println("X moves first");
                drawBoard(gameBoard);
            }
            if(!isXTurn)
            {
                out.println("O moves first");
                drawBoard(gameBoard);
            }
            do {
                getPositionAndPlaceToken(gameBoard, isXTurn);
                drawBoard(gameBoard);
                if (isWin(gameBoard)) {
                    gameStillGoing = false;
                    if (isXTurn) {
                        // X made the most recent play, so they must have won that round.
                        xWins++;
                        System.out.println("X wins!");
                    } else {
                        // O made the most recent play, so they must have won that round.
                        oWins++;
                        System.out.println("O wins!");
                    }
                } else if (isFull(gameBoard)) {
                    gameStillGoing = false;
                    // Nobody won, but the board is full - must be a draw.
                    draws++;
                    System.out.println("The game is a draw.  Nobody wins.");
                } else {
                    // board is not full, so continue to next player's turn.
                }
                isXTurn = ! isXTurn;
            } while (gameStillGoing);
            System.out.println("Score: X=" + xWins + ", O=" + oWins + ", draws=" + draws);

        } while (wantsToPlayAgain());

        // They're done playing.  Method will return and program will finish.
    }   

    public static void main(String[] args)
    {

        game();
        System.out.println("Goodbye!");
    }

}

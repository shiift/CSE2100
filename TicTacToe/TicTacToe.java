/**
 * 
 * @author		William S. Dickson
 * @class		CSE2100
 * @instructor	Chun-Hsi Huang
 * @school		University of Connecticut
 * @date		December 7, 2013
 * 
 */

public class TicTacToe
{
	/***********************************************************************
	class ListArray - a tictactoe game instance based on a 2d integer array
	***********************************************************************/
	
	protected static final int X = 1, O = -1;	// players
	protected static final int EMPTY = 0;		// empty cell
	protected int board[][] = new int[3][3];	// game board
	protected int player;						// current player
	
	public TicTacToe()
	{
		// TicTacToe() - initializes the board
		clearBoard();
	}
	
	public void clearBoard()
	{
		// clearboard() - fills all of the squares with blank squares
		for(int i = 0; i < 3; i++)
		{
			for(int j = 0; j < 3; j++)
			{
				board[i][j] = EMPTY;
			}
		}
		player = X;
	}
	
	public boolean putMark(int i, int j) throws IllegalArgumentException
	{
		/***********************************************************************
		putMark() - puts a mark in point (i,j) as the current player then
					switches the player
		***********************************************************************/
		if((i < 0) || (i > 2) || (j < 0) || (j > 2))
		{
			return false;
		}
		if(board[i][j] != EMPTY)
		{
			return false;
		}
		board[i][j] = player;
		player = -player;
		return true;
	}
	
	public boolean isWin(int mark)
	{
		// isWin() - returns true if the player has won the game, else false
		return((board[0][0] + board[0][1] + board[0][2] == mark * 3)		// row 0
				|| (board[1][0] + board[1][1] + board[1][2] == mark * 3)	// row 1
				|| (board[2][0] + board[2][1] + board[2][2] == mark * 3)	// row 2
				|| (board[0][0] + board[1][0] + board[2][0] == mark * 3)	// col 0
				|| (board[0][1] + board[1][1] + board[2][1] == mark * 3)	// col 1
				|| (board[0][2] + board[1][2] + board[2][2] == mark * 3)	// col 2
				|| (board[0][0] + board[1][1] + board[2][2] == mark * 3)	// diag
				|| (board[2][0] + board[1][1] + board[0][2] == mark * 3));	// diag
	}
	
	public int winner()
	{	
		// winner() - returns the player who has won
		if(isWin(X))
		{
			return(X);
		}
		else if(isWin(O))
		{
			return(O);
		}
		else
		{
			return(0);
		}
	}
	
	@Override
	public String toString()
	{
		// toString() - displays the game board
		String s = "";
		for(int i = 0; i < 3; i++)
		{
			for(int j = 0; j < 3; j++)
			{
				switch(board[i][j])
				{
				case X: s += "X"; break;
				case O: s += "O"; break;
				case EMPTY: s += " "; break;
				}
				if(j < 2){
					s += "|";
				}
			}
			if(i < 2)
			{
				s += "\n-----\n";
			}
		}
		return s;
	}
}

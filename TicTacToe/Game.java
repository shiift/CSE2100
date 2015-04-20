/**
 * 
 * @author		William S. Dickson
 * @class		CSE2100
 * @instructor	Chun-Hsi Huang
 * @school		University of Connecticut
 * @date		December 7, 2013
 * 
 */

import java.util.Scanner;

public class Game
{
	/***********************************************************************
	class Game - runs the tictactoe game
	***********************************************************************/
	protected static final int X = 1, O = -1;	// players
	protected static final int TIE = 0, EMPTY = 0;		// tie or empty cell

	public static void main(String[] args)
	{
		/***********************************************************************
		main() - runs the game
		***********************************************************************/
		TicTacToe game = new TicTacToe();
		System.out.println(game);
		Scanner sc = new Scanner(System.in);
		int turn = 0;
		while(!game.isWin(X) && !game.isWin(O) && turn < 9)
		{
			System.out.println("Make a move by typing a position 1-9:");
			int move = sc.nextInt();
			int loc1 = (move-1)/3;
			int loc2 = (move-1)%3;
			boolean moved = game.putMark(loc1, loc2);
			if(moved)
			{
				turn++;
				System.out.println(game + "\n");
				if(turn < 9)
				{
					System.out.println("Comptuer's move:");
					int[] cMove = testMoves(game.board.clone(), O, 2);
					game.putMark(cMove[1],cMove[2]);
					System.out.println(game + "\n");
					turn++;
				}
			}
			else
			{
				System.out.println("Invalid move.");
			}
		}
		sc.close();
		int winningPlayer = game.winner();
		if(winningPlayer == 1)
		{
			System.out.println("Player wins");
		}
		else if(winningPlayer == -1)
		{
			System.out.println("Computer wins");
		}
		else
		{
			System.out.println("Tie");
		}
	}
	
	public static int[] testMoves(int[][] board, int p, int k)
	{
		/***********************************************************************
		testMoves() - recursive move algorithm (minimax) looks ahead k moves for
					  the best possible move
		***********************************************************************/

		int bestScore = (p == X) ? Integer.MAX_VALUE : Integer.MIN_VALUE;
		int currentScore;
		int bestRow = -1;
		int bestCol = -1;
		
		if(k == 0 || isWin(X, board) || isWin(O, board))
		{
			bestScore = calcScore(board);
		}
		else
		{
			for(int i = 0; i < 3; i++)
			{
				for(int j = 0; j < 3; j++)
				{
					if(isValid(board, i, j))
					{
						board[i][j] = p;
						currentScore = testMoves(board, -p, k-1)[0];
						
						if(p == O)	// computer's turn
						{
							if(currentScore > bestScore)
							{
								bestScore = currentScore;
								bestRow = i;
								bestCol = j;
							}
						}
						else		// player's turn
						{
							if(currentScore < bestScore)
							{
								bestScore = currentScore;
								bestRow = i;
								bestCol = j;
							}
						}
						
						board[i][j] = 0;
					}
				}
			}
		}
		
		return new int[]{bestScore, bestRow, bestCol};
	}
	
	public static int calcScore(int[][] board)
	{
		/***********************************************************************
		calcScore() - calculates the score of the current board. higher score
					  means a better board for O while a lower score is a better
					  board for X
		***********************************************************************/
		if(isWin(O, board))
		{
			return Integer.MAX_VALUE;
		}
		if(isWin(X, board))
		{
			return Integer.MIN_VALUE;
		}
		
		int score = 0;
		
		score += calcLine(0, 0, 0, 1, 0, 2, board);  // row 0
		score += calcLine(1, 0, 1, 1, 1, 2, board);  // row 1
		score += calcLine(2, 0, 2, 1, 2, 2, board);  // row 2
		score += calcLine(0, 0, 1, 0, 2, 0, board);  // col 0
		score += calcLine(0, 1, 1, 1, 2, 1, board);  // col 1
		score += calcLine(0, 2, 1, 2, 2, 2, board);  // col 2
		score += calcLine(0, 0, 1, 1, 2, 2, board);  // diag 1
		score += calcLine(0, 2, 1, 1, 2, 0, board);  // diag 2
		
		return score;
	}
	
	public static int calcLine(int r1, int c1, int r2, int c2, int r3, int c3, int[][] board)
	{
		/***********************************************************************
		calcLine() - calculates the score of a row/col/diag using the following
		 			  rules:
		 			  		O | O | O	= 100
		 			  		O | O | _	= 10
		 			  		O | _ | _	= 1
		 			  		X | X | X	= 100
		 			  		X | X | _	= 10
		 			  		X | _ | _	= 1
		 			  		X | O | _	= 0
		 			  		O | _ | X	= 0 (any combinations of X and O = 0)
		***********************************************************************/
		int score = 0;
		
		if(board[r1][c1] == O)
		{
			score = 1;
		}
		else if (board[r1][c1] == X)
		{
			score = -1;
		}
		
		if(board[r2][c2] == O)
		{
			if(score == 1)
			{
				score = 10;
			}
			else if(score == -1)
			{
				return 0;
			}
			else
			{
				score = 1;
			}
		}
		else if(board[r2][c2] == X)
		{
			if(score == 1)
			{
				score = -10;
			}
			else if(score == -1)
			{
				return 0;
			}
			else
			{
				score = -1;
			}
		}
		
		if(board[r3][c3] == O)
		{
			if(score > 0)
			{
				score *= 10;
			}
			else if(score < 0)
			{
				return 0;
			}
			else
			{
				score = 1;
			}
		}
		else if(board[r3][c3] == X)
		{
			if(score < 0)
			{
				score *= 10;
			}
			else if(score > 0)
			{
				return 0;
			}
			else
			{
				score = -1;
			}
		}
		
		return score;
	}
	
	public static boolean isValid(int[][] board, int i, int j)
	{
		/***********************************************************************
		isValid() - returns true if the game move is a valid move, else false
		***********************************************************************/
		if(board[i][j] != EMPTY){
			return false;
		}
		else
		{
			return true;
		}
	}
	
	public static boolean isWin(int mark, int[][] board)
	{
		/***********************************************************************
		isValid() - returns true if the board is a winning board for the
					specified player
		***********************************************************************/
		return((board[0][0] + board[0][1] + board[0][2] == mark * 3)		// row 0
				|| (board[1][0] + board[1][1] + board[1][2] == mark * 3)	// row 1
				|| (board[2][0] + board[2][1] + board[2][2] == mark * 3)	// row 2
				|| (board[0][0] + board[1][0] + board[2][0] == mark * 3)	// col 0
				|| (board[0][1] + board[1][1] + board[2][1] == mark * 3)	// col 1
				|| (board[0][2] + board[1][2] + board[2][2] == mark * 3)	// col 2
				|| (board[0][0] + board[1][1] + board[2][2] == mark * 3)	// diag
				|| (board[2][0] + board[1][1] + board[0][2] == mark * 3));	// diag
	}
}

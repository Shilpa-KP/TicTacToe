import java.util.Random;
import java.util.Scanner;

class Game{
	static char[][] board;
	public Game()
	{
		board = new char[3][3];
		initBoard();
	}
	
	void initBoard()
	{
		for(int i=0;i<board.length;i++) 
		{
			for(int j=0;j<board[i].length;j++)
			{
				board[i][j] = ' ';
			}
		}
	}
	
	static void dispBoard()
	{
		System.out.println("-------------");
		for(int i=0;i<board.length;i++)
		{
			System.out.print("| ");
			for(int j=0;j<board[i].length;j++)
			{
				System.out.print(board[i][j] + " | " );
			}
			System.out.println();
			System.out.println("-------------");
		}
	}
	
	static void placeMark(int row,int col, char mark) 
	{
		if(row>=0 && row<=2 && col>=0 && col<=2)
		{
		board[row][col]=mark;
		}
		else
		{
			System.out.println("Invalid Position");
		}	
	}
	
	static boolean checkColWin() {
		for(int j=0;j<=2;j++) {
			if(board[0][j]!=' ' && board[0][j] == board[1][j] && board[1][j] == board[2][j])
			{
				return true;
				
			}
		}
		return false;
	}
	
	static boolean checkRowWin() {
		for(int i=0;i<=2;i++) {
			if(board[i][0]!=' ' && board[i][0] == board[i][1] && board[i][1] == board[i][2])
			{
				return true;
				
			}
		}
		return false;
	}
	
	static boolean checkDiagWin() 
	{
		if(board[0][0]!=' ' && board[0][0] == board[1][1] && board[1][1] == board[2][2]
				|| board[0][2]!=' ' && board[0][2] == board[1][1] && board[1][1] == board[2][0])
		{
			return true;
		}
		else
		{
			return false;
		}
	}		

	static boolean checkDraw()
	{
		for(int i =0;i<=2;i++)
		{
			for(int j=0;j<=2;j++)
			{
				if(board[i][j] == ' ')
				{
					return false;
				}
			}
		}
		return true;
	}


}

abstract class Human
{
	String name;
	char mark;
	 
	abstract void makeMove();
	
	boolean isValidMove(int row, int col)
	{
		if(row>=0 && row<=2 &&
		   col>=0 && col<=2)
		{
			if(Game.board[row][col] ==' ')
			{
				return true;
			}
		}
		return false;
	}
}

class UserPlayer extends Human
{
	
	UserPlayer(String name, char mark)
	{
		this.name=name;
		this.mark=mark;
	}
	
	void makeMove()
	{
		Scanner scan=new Scanner(System.in);
		int row;
		int col;
		do 
		{
			System.out.println("Enter the row and col");
			row=scan.nextInt();
			col=scan.nextInt();
		}while(!isValidMove(row, col));
		Game.placeMark(row, col, mark);		
	}
	
}


class AIPlayer extends Human
{
	
	AIPlayer(String name, char mark)
	{
		this.name=name;
		this.mark=mark;
	}
	
	void makeMove()
	{
		Scanner scan=new Scanner(System.in);
		int row;
		int col;
		do 
		{
			//java will generate random integer you have to tell the boundary
			Random r = new Random();
			row = r.nextInt(3);
			col = r.nextInt(3);
		}while(!isValidMove(row, col));
		Game.placeMark(row, col, mark);		
	}
			
}
public class AIBased {

	public static void main(String[] args) {
		
		Game t=new Game();
		UserPlayer p1 = new UserPlayer("Shilpa", 'X');
		AIPlayer p2 = new AIPlayer("AI", 'O');

		Human cp;
		cp=p1;
		
		while(true)
		{
			System.out.println(cp.name + " turn");
			cp.makeMove();
			Game.dispBoard();
		
			if(Game.checkColWin() || Game.checkDiagWin() || Game.checkRowWin()) 
			{
				System.out.println(cp.name + " has won");
				break;
			}
			else if(Game.checkDraw())
			{
				System.out.println("Game is a draw");
				break;
			}
			else
			{
				if(cp==p1)
				{
					cp=p2;
				}
				else
				{
					cp=p1;
				}
			}
		}
	}

}

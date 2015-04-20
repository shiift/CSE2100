import java.util.Scanner;

public class StackANSVP
{
	/***********************************************************************
	class StackANSVP - solves a 'All Nearest Smaller Values Problem' such
					   as:
			 input:	   0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15
			output:	   -, 0, 0, 4, 0, 2, 2, 6, 0, 1, 1, 5, 1, 3, 3, 7
	***********************************************************************/
	public static void main(String[] args)
	{
		/***********************************************************************
		main() - runs each of the methods to solve the puzzle
		***********************************************************************/
		System.out.println("Enter the sequence:");
		Scanner sc = new Scanner(System.in);
		String input = sc.nextLine();
		sc.close();
		
		input = input.replaceAll(",", " ");
		input = input.replaceAll("\\s+", ",");
				
		int[] intArr = convertIntArr(input);
		
		solverANSVP(intArr);
	}
	public static void solverANSVP(int[] intArr)
	{
		/***********************************************************************
		solverANSVP() - finds the nearest smaller value of each value in an
						array of integers
		***********************************************************************/
		NodeStack<Integer> wStack = new NodeStack<Integer>();
		int p = 1;
		for(int x : intArr)
		{
			while(!wStack.isEmpty() && wStack.top() >= x)
			{
				wStack.pop();
			}
			
			if(wStack.isEmpty())
			{
				System.out.print('-');
			}
			else
			{
				System.out.print(wStack.top());
			}
			
			if(p != intArr.length)
			{
				System.out.print(", ");
			}
			p++;
			wStack.push(x);
		}
		System.out.println();
	}
	public static int[] convertIntArr(String intStr)
	{
		/***********************************************************************
		convertIntArr() - converts a string of integers separated by commas to
						  an array of integers
		***********************************************************************/
		String[] numbers = intStr.split(",");
		int[] intArr = new int[numbers.length];
		for(int i = 0; i < numbers.length; i++)
		{
			intArr[i] = Integer.parseInt(numbers[i]);
		}
		return intArr;
	}
}

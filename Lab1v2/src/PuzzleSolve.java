import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Scanner;

public class PuzzleSolve
{
	/***********************************************************************
	class PuzzleSolve - solves a character puzzle, such as:
						    a + a = b
						    pot + pan = bib
							dog + cat = pig
							boy + girl = baby
	***********************************************************************/
	
	static int[] _intU = {0,1,2,3,4,5,6,7,8,9};		//array of valid numbers
	static char[] _chars;							//characters in puzzle
	static String _addend1;							//characters in first addend
	static String _addend2;							//characters in second addend
	static String _sum;								//characters in sum
	
	public static void main(String[] args)
	{
		/***********************************************************************
		main() - runs each of the methods to solve the puzzle
		***********************************************************************/
		//read in puzzle
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter puzzle: ");
		String input = sc.nextLine();
		sc.close();
		
		//convert to lowercase and remove any whitespace
		input = input.toLowerCase();
		input = input.replaceAll("\\s", "");
		
		//confirm that the problem is in the correct format
		if(!input.matches("[a-z]+\\+[a-z]+\\=[a-z]+"))
		{
			System.out.println("Puzzle not valid. Please enter in the format: a + b = c");
			return;
		}
		
		//get parts of the puzzle
		String[] stringSplit = input.split("\\+");
		_addend1 = stringSplit[0];
		stringSplit = stringSplit[1].split("\\=");
		_addend2 = stringSplit[0];
		_sum = stringSplit[1];
		
		//remove plus and minus
		input = input.replaceAll("\\+", "");
		input = input.replaceAll("\\=", "");
		
		char[] chars = input.toCharArray();
		
		//remove duplicate characters
		chars = removeDuplicates(chars);
		_chars = chars;
		
		if(chars.length > 10)
		{
			System.out.println("Puzzle has too many characters.");
			return;
		}
		
		//print out unique characters
		System.out.println(_chars);

		permute(chars.length, new SLinkedList(), _intU);
	}
	
	public static void permute(int depth, SLinkedList numPerm, int[] remainingInt)
	{
		/***********************************************************************
		permute() - takes an array of integers and recursively finds the
		            permutation
		          - for each permutation this method calls the solver() method
		***********************************************************************/
		
		for(int e: remainingInt)
		{
			int[] workingU = removeInt(remainingInt, e);
			numPerm.addToTail(new Node(e, null));
			if(depth == 1)
			{
				solver(numPerm);
			}
			else
			{
				permute(depth - 1, numPerm, workingU);
			}
			numPerm.removeFromTail();
		}
	}
	
	public static void solver(SLinkedList numPerm)
	{
		/***********************************************************************
		solver() - takes the linked list (digit permutation) and assigns each
				   each number to each letter respectively, then checks to see
				   if the equation is valid
		***********************************************************************/
		
		//assign each number to each letter in the hash
		HashMap<Character, Integer> charHash = new HashMap<Character, Integer>();
		Node currentNode = numPerm.head;
		for(int i = 0; i < _chars.length; i++)
		{
			charHash.put(_chars[i], currentNode.getElement());
			currentNode = currentNode.getNext();
		}

		//determine the values of each addend and the sum
		int addend1Value = 0;
		for(int l = 0; l < _addend1.length(); l++)
		{
			addend1Value *= 10;
			addend1Value += charHash.get(_addend1.charAt(l));
		}
		int addend2Value = 0;
		for(int l = 0; l < _addend2.length(); l++)
		{
			addend2Value *= 10;
			addend2Value += charHash.get(_addend2.charAt(l));
		}
		int sumValue = 0;
		for(int l = 0; l < _sum.length(); l++)
		{
			sumValue *= 10;
			sumValue += charHash.get(_sum.charAt(l));
		}

		//if the addends equal the value of the sum then display the values of the characters
		if(addend1Value + addend2Value == sumValue)
		{
			for(char c: _chars)
			{
				System.out.print(charHash.get(c));
			}
			System.out.println();
		}
	}
		
	public static char[] removeDuplicates(char[] startChar)
	{
		/***********************************************************************
		removeDuplicates() - removes duplicate characters from a character array
		***********************************************************************/

		LinkedHashSet<Character> charHashSet = new LinkedHashSet<Character>();
		for (char c: startChar)
		{
			charHashSet.add(new Character(c));
		}
		
		int count = 0;
		char[] newChars = new char[charHashSet.size()];
		for (Character c: charHashSet)
		{
			newChars[count++] = c;
		}
		return newChars;
	}
	public static int[] removeInt(int[] intArr, int element)
	{
		/***********************************************************************
		removeInt() - 'removes' the last element of an array
		***********************************************************************/

		int[] newIntArr = new int[intArr.length - 1];
		int count = 0;
		for(int i = 0; i < intArr.length; i++)
		{
			if(element != intArr[i])
			{
				newIntArr[count] = intArr[i];
				count++;
			}
		}
		return newIntArr;
	}
}

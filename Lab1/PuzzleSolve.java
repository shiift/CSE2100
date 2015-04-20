import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Scanner;


public class PuzzleSolve {

	static int[] _intU = {0,1,2,3,4,5,6,7,8,9};
	static String _addend1;
	static String _addend2;
	static String _sum;
	static char[] _chars;
	static SLinkedList _charS;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter puzzle: ");
		String input = sc.nextLine();
		sc.close();
		
		input = input.toLowerCase();
		input = input.replaceAll("\\s", "");
		
		if(!input.matches("[a-z]+\\+[a-z]+\\=[a-z]+")){
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
		
		System.out.println(_chars);
		permute(chars.length, new SLinkedList(), chars);
	}
	
	public static void permute(int k, SLinkedList charS, char[] charU){
		for(char e: charU){
			char[] workingU = removeChar(charU,e);
			charS.addToTail(new Node(e,null));
			if(k == 1){
				_charS = charS;
				numbers2(charS.size,new LinkedList(),_intU);
			}else{
				permute(k-1, charS, workingU);
			}
			charS.removeFromTail();
		}
	}
	
	public static void numbers2(int k, LinkedList intS, int[] intU){
		for(int i:intU){
			int[] workingIntU = removeInt(intU,i);
			if(intS.isEmpty() || i > (int) intS.peekLast()){
				intS.addLast(i);
				if(k == 1){
					LinkedList intSTemp = (LinkedList) intS.clone();
					HashMap<Character,Integer> charHash = new HashMap<Character,Integer>();
					Node currentNode = _charS.head;
					while(currentNode != null){
						charHash.put(currentNode.getElement(), (int) intSTemp.poll());
						currentNode = currentNode.getNext();
					}
					int addend1Value = 0;
					for(int l=0; l<_addend1.length(); l++){
						addend1Value += charHash.get(_addend1.charAt(l))*(int)Math.pow(10,(_addend1.length()-l-1));
					}
					int addend2Value = 0;
					for(int l=0; l<_addend2.length(); l++){
						addend2Value += charHash.get(_addend2.charAt(l))*(int)Math.pow(10,(_addend2.length()-l-1));
					}
					int sumValue = 0;
					for(int l=0; l<_sum.length(); l++){
						sumValue += charHash.get(_sum.charAt(l))*(int)Math.pow(10,(_sum.length()-l-1));
					}
					if(addend1Value + addend2Value == sumValue){
						for(char c: _chars){
							System.out.print(charHash.get(c));
						}
						System.out.println();
					}
				}else{
					numbers2(k-1,intS,workingIntU);
				}
				intS.removeLast();
			}
		}
	}
		
	public static char[] removeDuplicates(char[] startChar){
		LinkedHashSet<Character> charHashSet = new LinkedHashSet<Character>();
		for (char c: startChar){
			charHashSet.add(new Character(c));
		}
		
		int count = 0;
		char[] newChars = new char[charHashSet.size()];
		for (Character c : charHashSet){
			newChars[count++] = c;
		}
		return newChars;
	}
	public static char[] removeChar(char[] charArr, char element){
		char[] newCharArr = new char[charArr.length-1];
		int count = 0;
		for(int i=0; i<charArr.length; i++){
			if(element != charArr[i]){
				newCharArr[count] = charArr[i];
				count++;
			}
		}
		return newCharArr;
	}
	public static int[] removeInt(int[] intArr, int element){
		int[] newIntArr = new int[intArr.length-1];
		int count = 0;
		for(int i=0; i<intArr.length; i++){
			if(element != intArr[i]){
				newIntArr[count] = intArr[i];
				count++;
			}
		}
		return newIntArr;
	}
}

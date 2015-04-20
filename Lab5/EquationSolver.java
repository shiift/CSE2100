import java.util.Comparator;
import java.util.Scanner;

public class EquationSolver
{
	/***********************************************************************
	class EquationSolver - solves ﬁfth-order Diophantine equations smaller
						   than the given N in the format shown below.
			 input:	   java EquationSolver 75
			output:	   19 43 46 47 67 72
	***********************************************************************/
	public static void main(String[] args)
	{
		if(args.length == 0)
    	{
        	System.out.println("Proper Usage is: java EquationSolver integer");
        	System.exit(0);
    	}
		Integer limit = Integer.parseInt(args[0]);
		
		SLinkedList<Triple> abcList = new SLinkedList<Triple>();
		for(int a = 1; a <= limit; a++)
		{
			for(int b = a; b <= limit; b++)
			{
				for(int c = b; c <= limit; c++)
				{
					long sum = (long)a*a*a*a*a + (long)b*b*b*b*b + (long)c*c*c*c*c;
					Triple trip = new Triple(a, b, c, sum);
					abcList.addToHead(new Node<Triple>(trip, null));
				}
			}
		}

		SLinkedList<Triple> defList = new SLinkedList<Triple>();
		for(int d = 1; d <= limit; d++)
		{
			for(int e = d; e <= limit; e++)
			{
				for(int f = e; f <= limit; f++)
				{
					long sum = (long)f*f*f*f*f - (long)d*d*d*d*d - (long)e*e*e*e*e;
					Triple trip = new Triple(d, e, f, sum);
					defList.addToHead(new Node<Triple>(trip, null));
				}
			}
		}
		
		mergeSort(abcList, new TripleComparator());
		mergeSort(defList, new TripleComparator());

		findDupes(abcList, defList, new TripleComparator());

	}
	
	public static <E> void mergeSort (SLinkedList<E> in, Comparator<E> c)
	{
		/***********************************************************************
		mergeSort() - sorts the elements of list in in nondecreasing order
					  according to comparator c, using the merge-sort algorithm.
		***********************************************************************/
		int n = in.size;
		if (n < 2)
		{
			return; // the in list is already sorted in this case
		}
		// divide
		SLinkedList<E> in1 = new SLinkedList<E>();
		SLinkedList<E> in2 = new SLinkedList<E>();
		int i = 0;
		while(i < n / 2)
		{
			in1.addToTail(in.removeFromHead()); // move the first n/2 elements to in1
			i++;
		}
		while(in.size != 0)
		{
			in2.addToTail(in.removeFromHead()); // move the rest to in2
		}
		// recur
		mergeSort(in1, c);
		mergeSort(in2, c);
		// conquer
		merge(in1, in2, c, in);
	}

	public static <E> void merge(SLinkedList<E> in1, SLinkedList<E> in2, Comparator<E> c, SLinkedList<E> in)
	{
		/***********************************************************************
		merge() - merges two sorted lists, in1 and in2, into a sorted list in.
		***********************************************************************/
		while (in1.size != 0 && in2.size != 0)
		{
			if (c.compare(in1.head.getElement(), in2.head.getElement()) <= 0)
			{
				in.addToTail(in1.removeFromHead());
			}
			else
			{
				in.addToTail(in2.removeFromHead());
			}
		}
		while(in1.size != 0) // move the remaining elements of in1
		{
			in.addToTail(in1.removeFromHead());
		}
		while(in2.size != 0) // move the remaining elements of in2
		{
			in.addToTail(in2.removeFromHead());
		}
	}
	
	public static <E> void findDupes(SLinkedList<Triple> in1, SLinkedList<Triple> in2, Comparator<Triple> c) {
		/***********************************************************************
		findDupes() - finds duplicate values in two sorted lists, in1 and in2.
		***********************************************************************/
		int counter = 0;
		while (in1.size != 0 && in2.size != 0)
		{
			if (c.compare(in1.head.getElement(), in2.head.getElement()) < 0)
			{
				in1.removeFromHead();
			}
			else if (c.compare(in1.head.getElement(), in2.head.getElement()) == 0)
			{
				if(in1.head.getElement()._z <= in2.head.getElement()._x)
				{
					System.out.print(in1.head.getElement()+" ");
					System.out.println(in2.head.getElement());
					counter++;
				}
				in1.removeFromHead();
			}
			else
			{
				in2.removeFromHead();
			}
		}
		while(in1.size != 0) // remove the remaining elements of in1
		{
			in1.removeFromHead();
		}
		while(in2.size != 0) // remove the remaining elements of in2
		{
			in2.removeFromHead();
		}
		if(counter == 0)
		{
			System.out.println("no solution");
		}
	}
}

import java.util.Comparator;

/**
 * 
 * @author		William S. Dickson
 * @class		CSE2100
 * @instructor	Chun-Hsi Huang
 * @school		University of Connecticut
 * @date		November 10, 2013
 * 
 */

public class EquationSolver
{
	/***********************************************************************
	class EquationSolver - solves fifth-order Diophantine equations smaller
						   than the given N in the format shown below using
						   a heap and heap-sort
			 input:	   java EquationSolver 75
			output:	   19 43 46 47 67 72
	***********************************************************************/
	public static void main(String[] args)
	{
		if(args.length == 0)
    	{
        	System.out.println("Proper Usage is: java EquationSolver int");
        	System.exit(0);
    	}
		Integer limit = Integer.parseInt(args[0]);
		
		LinkedTripleHeap abcList = new LinkedTripleHeap();
		for(int a = 1; a <= limit; a++)
		{
			for(int b = a; b <= limit; b++)
			{
				for(int c = b; c <= limit; c++)
				{
					long sum = (long)a*a*a*a*a + (long)b*b*b*b*b + (long)c*c*c*c*c;
					Triple trip = new Triple(a, b, c, sum);
					abcList.add(trip);
				}
			}
		}
		
		LinkedTripleHeap defList = new LinkedTripleHeap();
		for(int d = 1; d <= limit; d++)
		{
			for(int e = d; e <= limit; e++)
			{
				for(int f = e; f <= limit; f++)
				{
					long sum = (long)f*f*f*f*f - (long)d*d*d*d*d - (long)e*e*e*e*e;
					Triple trip = new Triple(d, e, f, sum);
					defList.add(trip);
				}
			}
		}

		findDupes(abcList, defList, new TripleComparator());

	}
	
	public static <E> void findDupes(LinkedTripleHeap in1, LinkedTripleHeap in2, Comparator<Triple> c)
	{
		/***********************************************************************
		findDupes() - finds duplicate values in two sorted lists, in1 and in2.
		***********************************************************************/
		int counter = 0;
		while (in1.size() != 0 && in2.size() != 0)
		{
			if (c.compare(in1.top(), in2.top()) < 0)
			{
				in1.remove();
			}
			else if (c.compare(in1.top(), in2.top()) == 0)
			{
				if(in1.top()._z <= in2.top()._x)
				{
					System.out.print(in1.top()+" ");
					System.out.println(in2.top());
					counter++;
				}
				in1.remove();
			}
			else
			{
				in2.remove();
			}
		}
		while(in1.size() != 0) // remove the remaining elements of in1
		{
			in1.remove();
		}
		while(in2.size() != 0) // remove the remaining elements of in2
		{
			in2.remove();
		}
		if(counter == 0)
		{
			System.out.println("no solution");
		}
	}
}

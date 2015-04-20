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

public class TripleComparator implements Comparator<Triple>
{
	/***********************************************************************
	class TripleComparator - compares two triples by the value of the sums.
	***********************************************************************/
	
	@Override
	public int compare(Triple o1, Triple o2)
	{
		/***********************************************************************
		compare() - compares two triples and returns 1, 0, and -1 if the o1 is
					greater than, equal to, and less than o2, repspectively
		***********************************************************************/
		long difference = o1._sum - o2._sum;
		if(difference > 0){
			return 1;
		}
		else if(difference == 0)
		{
			return 0;
		}
		else
		{
			return -1;
		}
	}
}

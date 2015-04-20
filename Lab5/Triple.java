public class Triple
{
	/***********************************************************************
	class Triple - object that contains three integers, _x, _y, _z, and a
				   long number, _sum.
	***********************************************************************/

	public int _x, _y, _z;
	public long _sum;
	
	public Triple()
	{
		/***********************************************************************
		Triple() - constructs an empty Triple.
		***********************************************************************/
		_x = 0;
		_y = 0;
		_z = 0;
		_sum = 0;
	}
	public Triple(int x, int y, int z, long sum)
	{
		/***********************************************************************
		Triple() - constructs a Triple with values x, y, z, and sum.
		***********************************************************************/
		_x = x;
		_y = y;
		_z = z;
		_sum = sum;
	}
	
	@Override
	public String toString()
	{
		/***********************************************************************
		toString() - prints our the values of _x, _y and _z.
		***********************************************************************/
		return String.valueOf(_x) + " " + String.valueOf(_y) + " " + String.valueOf(_z);
	}
}

import java.util.Scanner;

public class StockTran
{
	/***********************************************************************
	class StockTran - records buying and selling of shares of stock and
					  prints the capital gain (which is reset every time
					  sold)
			 input:	   s 10 10
			 		   b 10 10
			 		   c
			 		   q
			output:	   0
	***********************************************************************/
	static int _cGain;
	static CircularTranQueue _stockQueue;
	
	public static void main(String[] args)
	{
		_stockQueue = new CircularTranQueue(32);
		Scanner sc = new Scanner(System.in);
		String input;
		String[] inputs;
		_cGain = 0;
		
		while(true)
		{
			//System.out.println("Enter a transaction:");
			input = sc.nextLine();
			inputs = input.split("\\s+");
			
			if(inputs[0].matches("q"))
			{
				break;
			}
			if(inputs[0].matches("c"))
			{
				System.out.println(_cGain);
			}
			if(inputs[0].matches("b|s") && inputs.length == 3 && inputs[1].matches("\\d+") && inputs[2].matches("\\d+"))
			{
				if(inputs[0].matches("b"))
				{
					if(_stockQueue.isFull())
					{
						System.out.println("You cannot buy more shares. Your queue is full.");
					}
					else
					{
						_stockQueue.enqueue(new Tran(Integer.parseInt(inputs[1]), Integer.parseInt(inputs[2])));
					}
				}
				else
				{
					sellShares(Integer.parseInt(inputs[1]), Integer.parseInt(inputs[2]));
				}
			}
		}
		sc.close();
	}
	
	static public void sellShares(int shares, int price)
	{
		// sellShares(shares, price) - sells the shares at the given price
		_cGain = 0;
		int sharesLeft = shares;
		while(sharesLeft > 0)
		{
			if(_stockQueue.isEmpty())
			{
				System.out.print("You do not have any more shares to sell. ");
				System.out.print(sharesLeft);
				System.out.println(" shares were not sold.");
				break;
			}
			if(sharesLeft < _stockQueue.top()._numShares)
			{
				_stockQueue.top()._numShares -= sharesLeft;
				_cGain += sharesLeft * (price - _stockQueue.top()._price);
				sharesLeft = 0;
			}
			else
			{
				Tran currentTran = _stockQueue.dequeue();
				_cGain += currentTran._numShares * (price - currentTran._price);
				sharesLeft -= currentTran._numShares;
			}
		}
	}
}

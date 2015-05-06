import java.io.FileNotFoundException;


public class InventoryManager {
	
	private static InventoryStack inventory;
	private static CustomerQueue queue;
	private static double income;
	private static double expenses;
	
	
	public static void main(String[] args) throws FileNotFoundException{
		String[] input = FileReader.readFile("transactions.txt");
		
		int x = 0;
		int amountIn = 0, amountOut = 0;
		double price = 0;
		boolean receive = false;
		inventory = new InventoryStack();
		queue = new CustomerQueue();
		while (input[x] != null){
			String temp = input[x];
			if (temp.equals("R")){
				receive = true;
				amountIn = Integer.parseInt(input[++x]);
				price = Double.parseDouble(input[++x]);
			}
			else if (temp.equals("S")){
				receive = false;
				amountOut = Integer.parseInt(input[++x]);
			}
			if (receive){
				inventory.add(amountIn, price);
				expenses = expenses + (amountIn*price);
				System.out.println("EXPENSE " + amountIn + " @ " + price + " = " + (amountIn*price)+ " TOTAL " + expenses);
			}
			else if (!receive){
				if (inventory.total == 0){
					//System.out.println("making a raincheck for " + amountOut);
					queue.add(amountOut, inventory.topPrice);
				}
				else {
					queue.add(amountOut, 0);
					//System.out.println(amountOut);
				}
			}
			while (inventory.total > 0 && queue.orderTotal > 0){
				//System.out.println(inventory.total + " " + queue.head.amount + " " + queue.orderTotal);
				if (queue.hasRaincheck()){
					if (inventory.total >= queue.orderTotal){
						
						double priceCharged = queue.rainCheck()*1.4;
						priceCharged *= 100;
						priceCharged = Math.round(priceCharged);
						priceCharged /= 100;
						int amountRemoved = queue.remove();
						inventory.remove(amountRemoved);
						double incomeThisRound = amountRemoved*priceCharged;
						income += incomeThisRound;
						System.out.println("INCOME " + amountRemoved + " @ " + priceCharged + " = " + incomeThisRound + " TOTAL " + income);
						
					}
					else if (inventory.total<queue.orderTotal){
						//System.out.println("here");
						double priceCharged = queue.rainCheck();
						int amountRemoved;
						if (queue.head.amount<inventory.total){
							amountRemoved = queue.remove();
						}
						else{
							amountRemoved = inventory.total;
							queue.partialRemove(amountRemoved, priceCharged);
						}
						priceCharged *= 1.4;
						priceCharged *= 100;
						priceCharged = Math.round(priceCharged);
						priceCharged /= 100;
						inventory.remove(amountRemoved);
						double incomeThisRound = amountRemoved*priceCharged;
						income += incomeThisRound;
						System.out.println("INCOME " + amountRemoved + " @ " + priceCharged + " = " + incomeThisRound + " TOTAL " + income);
					}
				}
				else {
					if (inventory.total >= queue.orderTotal){
						
						double priceCharged = inventory.topPrice*1.4;
						priceCharged *= 100;
						priceCharged = Math.round(priceCharged);
						priceCharged /= 100;
						int amountRemoved = queue.remove();
						inventory.remove(amountRemoved);
						double incomeThisRound = amountRemoved*priceCharged;
						income += incomeThisRound;
						System.out.println("INCOME " + amountRemoved + " @ " + priceCharged + " = " + incomeThisRound + " TOTAL " + income);
					}
					else if (inventory.total<queue.orderTotal){
						
						double priceCharged = inventory.topPrice;
						int amountRemoved = inventory.total;
						queue.partialRemove(amountRemoved, priceCharged);
						priceCharged *= 1.4;
						priceCharged *= 100;
						priceCharged = Math.round(priceCharged);
						priceCharged /= 100;
						inventory.remove(amountRemoved);
						double incomeThisRound = amountRemoved*priceCharged;
						income += incomeThisRound;
						System.out.println("INCOME " + amountRemoved + " @ " + priceCharged + " = " + incomeThisRound + " TOTAL " + income);
					}
				
				}
			}
			x++;
			
		}
		System.out.println("Expenses are: " + expenses);
		System.out.println("Income is: " + income);
		System.out.println("Profit is: "+ (income - expenses));
	}
}
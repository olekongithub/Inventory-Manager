
public class InventoryStack {
	private Widget top;
	int total = 0;
	double topPrice;
	
	public void add(int amountIn, double purchasePrice){
		//adds amountIn number of widgets to the top of the stack at 
		//the price they were purchased for
		topPrice = purchasePrice;
		if (top == null){
			top = new Widget(purchasePrice);
			amountIn--;
			total++;
		}
		int x = 0;
		while ( x< amountIn){
			top = new Widget(purchasePrice, top);
			x++;
			total++;
		}
	}
	
	public void remove(int amountOut){
		//remove amount from top of inventory stack
		for(int x = 0; x<amountOut; x++){
			topPrice = top.price;
			top = top.next;
			total--;
		}
		
		
	}
		
}

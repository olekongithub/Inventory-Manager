
public class CustomerQueue {

	Customer head;
	int orderTotal;
	
	public boolean hasRaincheck(){
		if (head == null) return false;
		return (head.rainCheck>0);
	}
	
	public double rainCheck(){
		return head.rainCheck;
	}
	
	public void add(int amountOut, double rainCheck){
		// add a new customer to queue with the amount of widgets they want
		//if rainCheck is zero, it is ignored and regular pricing applies
		if (head == null){
			head = new Customer (amountOut, rainCheck);
			orderTotal += amountOut;
		}
		else {
			Customer temp = head;
			while (temp.previous!=null){
				temp = temp.previous;
			}
			temp.previous = new Customer (amountOut, rainCheck);
			orderTotal += amountOut;
		}
	}
	
	public int partialRemove(int amountOut, double rainCheck){
		//removes part of the first order when there is not enough inventory and saves the price.
		Customer temp = head.previous;
		double guaranteedPrice = rainCheck;
		head = new Customer((head.amount-amountOut), guaranteedPrice);
		head.previous = temp;
		orderTotal -= amountOut;
		return amountOut;
		
	}
	
	public int remove(){
		//removes the first order returns guaranteed price, if 0 regular pricing
		int amountRemoved = head.amount;
		head = head.previous;
		orderTotal -= amountRemoved;
		return amountRemoved;
		
	}
	
}

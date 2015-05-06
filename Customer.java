
public class Customer {
	double rainCheck = 0;
	int amount;
	Customer previous;
	
	public Customer(int amount, double price){
		this.amount = amount;
		rainCheck = price;
	}
	public Customer(int amount, double price, Customer previous){
		this.amount = amount;
		this.rainCheck = price;
		this.previous = previous;
	}


}


public class Widget {


	double price;
	Widget next;
	
	public Widget (double cost){
		price = cost;
	}
	
	public Widget(double cost, Widget n){
		price = cost;
		next = n;
	}

}

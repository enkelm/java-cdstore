package model;

import java.util.Date;

public class Bill {
	
	public int billID;
	public int quantity;
	public double totPrice;
	public Date DateCreated;
	public long millis = System.currentTimeMillis();
	
	public Bill(int billID, int quantity, double totPrice, Date DateCreated) {
		this.billID = billID;
		this.quantity = quantity;
		this.totPrice = totPrice;
		this.DateCreated = new Date();
	}

}

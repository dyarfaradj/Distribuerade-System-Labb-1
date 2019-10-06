package BO;
/**
 * Representation of a bill.
 * 
 * @author Kamal and Dyar
 */
public class Billing {

	private int bill_no;
	
	private int uid;
	
	private int total_amt;
	
	private boolean packed;
	
	private int product_id;

	private String product_name;

	private int quantity;
	
	private int stock;
	
	public Billing() {
		super();
	}

	public Billing(int bill_no, int uid, int total_amt, boolean packed) {
		super();
		this.bill_no = bill_no;
		this.uid = uid;
		this.total_amt = total_amt;
		this.packed = packed;
	}
	
	public Billing(int bill_no, int product_id, int quantity, String product_name, int stock) {
		super();
		this.bill_no = bill_no;
		this.product_name = product_name;
		this.product_id = product_id;
		this.quantity = quantity;
		this.stock = stock;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public int getProduct_id() {
		return product_id;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public int getBill_no() {
		return bill_no;
	}

	public void setBill_no(int bill_no) {
		this.bill_no = bill_no;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public int getTotal_amt() {
		return total_amt;
	}

	public void setTotal_amt(int total_amt) {
		this.total_amt = total_amt;
	}

	public boolean isPacked() {
		return packed;
	}

	public void setPacked(boolean packed) {
		this.packed = packed;
	}

	
	
}

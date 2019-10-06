package BO;
/**
 * Representation of a cart.
 * 
 * @author Kamal and Dyar
 */
public class Cart {

	private int cart_id;
	
	private int uid;
	
	private int product_id;
	
	private String product_name;
	
	private int quantity;
	
	private int price;

	public int getCartId() {
		return cart_id;
	}
	
	public Cart() {
		super();
	}

	public Cart(int product_id, String product_name, int quantity, int price) {
		super();
		this.product_id = product_id;
		this.product_name = product_name;
		this.quantity = quantity;
		this.price = price;
	}

	public Cart(int cart_id, int uid, int product_id, String product_name, int quantity, int price) {
		super();
		this.cart_id = cart_id;
		this.uid = uid;
		this.product_id = product_id;
		this.product_name = product_name;
		this.quantity = quantity;
		this.price = price;
	}

	public void setCartId(int cart_id) {
		this.cart_id = cart_id;
	}

	public int getuId() {
		return uid;
	}

	public void setuId(int uid) {
		this.uid = uid;
	}


	public int getCart_id() {
		return cart_id;
	}

	public void setCart_id(int cart_id) {
		this.cart_id = cart_id;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public int getProduct_id() {
		return product_id;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Cart [cartId=" + cart_id + ", uid=" + uid + ", productId="
				+ product_id + ", productName=" + product_name + ", quantity="
				+ quantity + ", price=" + price + "]";
	}
	
	
}

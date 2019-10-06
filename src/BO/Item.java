package BO;

/**
 * Representation of a item.
 * 
 * @author Kamal and Dyar
 */

public class Item {
	private int product_id;
	private String product_name;
	private int cat_id;
	private int stock;
	private int price;
	

		
	
	public Item() {
		
	}
	
	public Item( String product_name, int cat_id, int stock, int price){
		super();
		this.product_name = product_name;
		this.cat_id = cat_id;
		this.stock = stock;
		this.price = price;
	}


	public Item(int product_id, String product_name, int cat_id, int stock, int price){
		super();
        this.product_id = product_id;
		this.product_name = product_name;
		this.cat_id = cat_id;
		this.stock = stock;
		this.price = price;
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

	public int getCat_id() {
		return cat_id;
	}

	public void setCat_id(int cat_id) {
		this.cat_id = cat_id;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	

 
  
}

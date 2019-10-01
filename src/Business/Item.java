package Business;

/**
 * Book.java
 * This is a model class represents a book entity
 * @author www.codejava.net
 *
 */
public class Item {
	private int id;
	private String title;
	private String description;
	private String quantity;
	private float price;
	

		
	
	public Item() {
		
	}
	
	public Item( String title, String description, String quantity, float price){
		super();
		this.title = title;
		this.description = description;
		this.quantity = quantity;
		this.price = price;
	}
	
	public Item(int id, String title, String description, String quantity, float price){
		super();
        this.id = id;
		this.title = title;
		this.description = description;
		this.quantity = quantity;
		this.price = price;
	}
	
	public int getId() {
		return id;
	}
	public void setId() {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
 
  
}

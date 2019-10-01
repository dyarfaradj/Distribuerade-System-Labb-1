package Business;

import java.io.Serializable;/**
 * Book.java
 * This is a model class represents a book entity
 * @author www.codejava.net
 *
 */
public class Item implements Serializable {
	private int id;
	private String title;
	private String author;
	private float price;
 
    public Item() {
    }
 
    public Item() {
        this.id = id;
    }
 
    public Item(int id, String title, String author, float price) {
        this(title, author, price);
        this.id = id;
    }
     
    public Item(String title, String author, float price) {
        this.title = title;
        this.author = author;
        this.price = price;
    }
 
    public int getId() {
        return id;
    }
 
    public void setId(int id) {
        this.id = id;
    }
 
    public String getTitle() {
        return title;
    }
 
    public void setTitle(String title) {
        this.title = title;
    }
 
    public String getAuthor() {
        return author;
    }
 
    public void setAuthor(String author) {
        this.author = author;
    }
 
    public float getPrice() {
        return price;
    }
 
    public void setPrice(float price) {
        this.price = price;
    }
}

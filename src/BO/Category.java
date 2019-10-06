package BO;
/**
 * Representation of a category.
 * 
 * @author Kamal and Dyar
 */

import java.io.Serializable;

public class Category implements Serializable {

	private static final long serialVersionUID = 1L;
	private int cat_id;

	public Category(String cat_name) {
		super();
		this.cat_name = cat_name;
	}

	private String cat_name;

	public int getCat_id() {
		return cat_id;
	}

	public Category(int cat_id, String cat_name) {
		super();
		this.cat_id = cat_id;
		this.cat_name = cat_name;
	}

	public void setCat_id(int cat_id) {
		this.cat_id = cat_id;
	}

	public String getCat_name() {
		return cat_name;
	}

	public void setCat_name(String cat_name) {
		this.cat_name = cat_name;
	}

}

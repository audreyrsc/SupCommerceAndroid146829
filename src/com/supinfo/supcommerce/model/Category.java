package com.supinfo.supcommerce.model;

import java.io.Serializable;
import java.util.Collection;

public class Category implements Serializable {

	private static final long serialVersionUID = -4959975553152009352L;

	private String key;
	private String name;
	
	private Collection<Product> products;
	
	public Category() {}
	
	public Category(String key, String name) {
		this.key = key;
		this.name = name;
	}
	
	
	public String getKey() {
		return key;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Collection<Product> getProducts() {
		return products;
	}
	
	public void setProducts(Collection<Product> products) {
		this.products = products;
	}
	
	
}

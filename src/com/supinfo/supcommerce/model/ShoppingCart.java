package com.supinfo.supcommerce.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Panier de l'utilisateur
 * @author Audrey
 */
public class ShoppingCart implements Serializable {

	private static final long serialVersionUID = -2184812959386813156L;
	private Collection<Product> products;
	
	public ShoppingCart() {
		products = new ArrayList<Product>();
	}
	
	public Collection<Product> getProducts() {
		if(products==null)
			products = new ArrayList<Product>();
		return products;
	}
	
	public void setProducts(Collection<Product> products) {
		this.products = products;
	}
}

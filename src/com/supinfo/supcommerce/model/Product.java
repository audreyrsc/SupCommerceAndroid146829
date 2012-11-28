package com.supinfo.supcommerce.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;


public class Product implements Serializable {

	private static final long serialVersionUID = -6491580734752070406L;

	private String key;
	private String name;
	private String content;
	private BigDecimal price;
	private Category category;

	
	public Product(String key, String name, String content, BigDecimal price) {
		this.key = key;
		this.name = name;
		this.content = content;
		this.price = price;
	}
	
	
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	/**
	 * On renvoie le prix avec 2 chiffes après la virgule
	 * @return BigDecimal
	 */
	public BigDecimal getPrice() {
		return new BigDecimal(price.toPlainString()).setScale(2, RoundingMode.HALF_UP);
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	/**
	 * Deux produits sont egaux si leurs noms, contenus et prix sont les mêmes
	 */
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Product))
			return false;
		
		Product p = (Product) obj;
		
		if(name!=null && content!=null && price!=null)
			return name.equals(p.name) && content.equals(p.content) && price.equals(p.price);
		else return false;
	}
	
	@Override
	public int hashCode() {
		int result = 17;
		result = 37 * result + name.hashCode();
		result = 37 * result + content.hashCode();
		result = 37 * result + price.hashCode();
		return result;
	}
	
}

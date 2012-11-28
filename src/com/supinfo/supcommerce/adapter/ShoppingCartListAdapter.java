package com.supinfo.supcommerce.adapter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import com.supinfo.supcommerce.R;
import com.supinfo.supcommerce.model.Product;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Adapter pour la vue d'un element du panier
 * @author Audrey
 */
public class ShoppingCartListAdapter extends ArrayAdapter<Product>{

	private int resource;
	
	public ShoppingCartListAdapter(Context context, int resource, int textViewResourceId, List<Product> products) {
		super(context, resource, textViewResourceId, products);
		this.resource = resource;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		if(convertView==null){
			LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(resource, null);
		}
		
		Product product = getItem(position);
		
		if(product!=null) {
			TextView txtView = (TextView) convertView.findViewById(R.id.productNameTextView);
			txtView.setText(product.getName());
			
			txtView = (TextView) convertView.findViewById(R.id.priceTextView);
			BigDecimal price = product.getPrice();
			price.setScale(2, RoundingMode.HALF_UP);
			txtView.setText(price+"€");
		}
		
		return convertView;
	}
}

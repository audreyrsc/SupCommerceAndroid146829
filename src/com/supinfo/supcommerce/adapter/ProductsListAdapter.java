package com.supinfo.supcommerce.adapter;

import java.util.List;

import com.supinfo.supcommerce.R;
import com.supinfo.supcommerce.model.Category;
import com.supinfo.supcommerce.model.Product;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Adapter pour la vue d'un element de la liste des produits
 * @author Audrey
 */
public class ProductsListAdapter extends ArrayAdapter<Product> {

	private int resource;
	
	public ProductsListAdapter(Context context, int resource, int textViewResourceId, List<Product> objects) {
		super(context, resource, textViewResourceId, objects);
		this.resource = resource;
	}
	
	@Override
	public View getView(int position, View view, ViewGroup parent) {
		
		if(view==null){
			LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(resource, null);
		}
		
		Product product = getItem(position);
		
		if(product!=null) {
			TextView textView = (TextView) view.findViewById(R.id.nameProduct);
			textView.setText(product.getName());
			
			textView = (TextView) view.findViewById(R.id.contentProduct);
			textView.setText(product.getContent());
			
			textView = (TextView) view.findViewById(R.id.categoryProduct);
			Category category = product.getCategory();
			if(category!=null)
				textView.setText(product.getCategory().getName());
			else textView.setText("Undefined");
			
			textView = (TextView) view.findViewById(R.id.priceProduct);
			textView.setText(product.getPrice()+" €");
		}
		
		return view;
	}

}

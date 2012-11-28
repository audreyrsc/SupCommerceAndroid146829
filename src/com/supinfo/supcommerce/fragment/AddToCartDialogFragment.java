package com.supinfo.supcommerce.fragment;

import java.util.ArrayList;

import com.supinfo.supcommerce.model.Product;
import com.supinfo.supcommerce.model.ShoppingCart;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;

/**
 * Dialogue d'ajout d'un produit au panier
 * @author Audrey
 */
@TargetApi(11)
public class AddToCartDialogFragment extends DialogFragment {

	private Product product;
	private ShoppingCart shoppingCart;
	
	@Override
	public void setArguments(Bundle bundle) {
		super.setArguments(bundle);
		product = (Product) bundle.getSerializable("product");
		shoppingCart = (ShoppingCart) bundle.getSerializable("shoppingCart");
	}
	
	@TargetApi(11)
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setMessage("Voulez-vous ajouter cet objet à votre panier?")
			.setTitle(product.getName())
			.setPositiveButton("Ajouter", new OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					ArrayList<Product> productsList = (ArrayList<Product>) shoppingCart.getProducts();
					if(productsList==null)
						productsList = new ArrayList<Product>();
					
					productsList.add(product);
					shoppingCart.setProducts(productsList);
				}
			})
			.setNegativeButton("Annuler", new OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.cancel();
				}
			});
		
		return builder.create();
	}

	public ShoppingCart getShoppingCart() {
		return shoppingCart;
	}
}

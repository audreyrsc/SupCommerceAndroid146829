package com.supinfo.supcommerce.activity;

import java.util.List;
import java.util.concurrent.ExecutionException;


import com.supinfo.supcommerce.R;
import com.supinfo.supcommerce.REST.ProductResources;
import com.supinfo.supcommerce.adapter.ProductsListAdapter;
import com.supinfo.supcommerce.fragment.AddToCartDialogFragment;
import com.supinfo.supcommerce.model.Product;
import com.supinfo.supcommerce.model.ShoppingCart;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

/**
 * Activité correspondant à la liste des produits
 * @author Audrey
 */
public class ProductsListActivity extends Activity {

	public static final int REQUEST_CODE = 2;
	private ShoppingCart shoppingCart;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.products_list);
		
		shoppingCart = (ShoppingCart) getIntent().getExtras().getSerializable("shoppingCart");
		
		// On recupere la liste des produits //
		ProductResources productsRes = new ProductResources(this);
		productsRes.execute();
		
		try {
			List<Product> productsList = productsRes.get();
			ProductsListAdapter adapter = new ProductsListAdapter(this, R.layout.product_template, android.R.id.text1, productsList);
			
			ListView listView = (ListView) findViewById(R.id.productsListView);
			listView.setAdapter(adapter);
			
		} catch (InterruptedException e) {
			Log.e("Get products list", e.getMessage());
		} catch (ExecutionException e) {
			Log.e("Get products list", e.getMessage());
		}
		
		initOnItemClick();
	}
	
	/**
	 * Initialisation du listener de click sur un element de la liste des produits
	 */
	private void initOnItemClick() {
		ListView list = (ListView) findViewById(R.id.productsListView);
		
		if(list!=null) {
			list.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> list, View view, int position, long id) {
					Product p = (Product) list.getItemAtPosition(position);
					if(p!=null) {
						// On cree un bundle pour l'envoyer au dialog //
						Bundle b = new Bundle(2);
						b.putSerializable("product", p);
						b.putSerializable("shoppingCart", shoppingCart);

						AddToCartDialogFragment dialog = new AddToCartDialogFragment();
						dialog.setArguments(b);
						dialog.show(getFragmentManager(), "addToCart");
						shoppingCart = dialog.getShoppingCart();
					}
				}
			});
		}
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode==KeyEvent.KEYCODE_BACK) {
			getIntent().putExtra("shoppingCart",shoppingCart);
			setResult(RESULT_OK, getIntent());
			finish();
		}
		return super.onKeyDown(keyCode, event);
	}
	
}

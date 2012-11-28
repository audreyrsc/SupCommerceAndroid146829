package com.supinfo.supcommerce.activity;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import com.supinfo.supcommerce.R;
import com.supinfo.supcommerce.adapter.ShoppingCartListAdapter;
import com.supinfo.supcommerce.model.Product;
import com.supinfo.supcommerce.model.ShoppingCart;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Activité correspondant au panier
 * @author Audrey
 */
public class ShowCartActivity extends Activity {

	public static final int REQUEST_CODE = 3;
	private ShoppingCart shoppingCart;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.show_cart);
		
		shoppingCart = (ShoppingCart) getIntent().getExtras().getSerializable("shoppingCart");
		
 		ListView lv = (ListView) findViewById(R.id.shoppingCart);
 		
 		ShoppingCartListAdapter adapter = new ShoppingCartListAdapter(this, R.layout.shoppingcart_product_template, android.R.id.text1, (List<Product>) shoppingCart.getProducts());
 		lv.setAdapter(adapter);
 		
 		TextView total = (TextView) findViewById(R.id.totalTextView);
 		total.setText(calculateTotal()+" €");
	}
	
	/**
	 * Calcule le total du panier
	 * @return BigDecimal avec 2 chiffres après la virgule
	 */
	private BigDecimal calculateTotal() {
		List<Product> products = (List<Product>) shoppingCart.getProducts();
		BigDecimal sum = new BigDecimal(0);
		
		for(Product p : products)
			sum = sum.add(p.getPrice());
		
		sum.setScale(2, RoundingMode.HALF_UP);
		return sum;
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

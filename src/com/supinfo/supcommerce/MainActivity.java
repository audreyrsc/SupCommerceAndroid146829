package com.supinfo.supcommerce;

import com.supinfo.supcommerce.activity.ProductsListActivity;
import com.supinfo.supcommerce.activity.ShowCartActivity;
import com.supinfo.supcommerce.model.ShoppingCart;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

	public static final int REQUEST_CODE = 1;
	
	private ShoppingCart shoppingCart;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
 		// Creation du panier //
 		shoppingCart = new ShoppingCart();
 		
 		// On ne peut pas voir le panier tant qu'il est vide //
 		Button showCartButton = (Button) findViewById(R.id.cartButton);
 		showCartButton.setEnabled(false);
 		
        initOnClickProductsListButton();
        initOnClickCartButton();
    }
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		
		if(resultCode==RESULT_OK && requestCode==REQUEST_CODE) {
			shoppingCart = (ShoppingCart) data.getExtras().getSerializable("shoppingCart");
			if(shoppingCart!=null && shoppingCart.getProducts().size()>0) {
				// On active le bouton pour voir le panier //
				Button showCartButton = (Button) findViewById(R.id.cartButton);
		 		showCartButton.setEnabled(true);
			}
				
		}
	}

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.shopping_cart, menu);
//        return true;
//    }
    
	/**
	 * Initialise l'action quand on clique sur le bouton pour voir la liste des produits
	 */
    private void initOnClickProductsListButton() {
    	Button button = (Button) findViewById(R.id.productsListButton);
    	
    	if(button==null) return;
    	
    	button.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this,ProductsListActivity.class);
				intent.putExtra("shoppingCart", shoppingCart);
				startActivityForResult(intent, REQUEST_CODE);
			}
		});
    }
    
    /**
     * Initialise l'action quand on clique sur le bouton pour voir le panier
     */
    private void initOnClickCartButton() {
    	Button button = (Button) findViewById(R.id.cartButton);
    	
    	if(button==null) return;
    	
    	button.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this,ShowCartActivity.class);
				intent.putExtra("shoppingCart", shoppingCart);
				startActivityForResult(intent, REQUEST_CODE);
			}
		});
    }
}

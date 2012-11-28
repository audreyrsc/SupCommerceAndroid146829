package com.supinfo.supcommerce.REST;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.supinfo.supcommerce.model.Category;
import com.supinfo.supcommerce.model.Product;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

public class ProductResources extends AsyncTask<Void, Void, List<Product>> {

	private PreferencesREST preferences;
	
	public ProductResources(Context context) {
		preferences = new PreferencesREST(context);
	}
	
	@Override
	protected List<Product> doInBackground(Void... params) {
		HttpClient client = new DefaultHttpClient();
		HttpGet get = new HttpGet(preferences.getRestURI()+"/products");
		
		ArrayList<Product> productsList = new ArrayList<Product>();
		
		try {
			HttpResponse response = client.execute(get);
			
			String result = EntityUtils.toString(response.getEntity(),HTTP.UTF_8);
			
			JSONObject jsonObj = new JSONObject(result);
			
			JSONArray products = jsonObj.getJSONArray("product");
			
			for(int i=0; i<products.length(); i++) {
				JSONObject productObj = products.getJSONObject(i);
				
				// on recupere les infos sur le produit //
				String key = productObj.getString("id");
				String name = productObj.getString("name");
				String content = productObj.getString("content");
				BigDecimal price = new BigDecimal(productObj.getString("price"));
				
				Product product = new Product(key, name, content, price);
				
				// On récupère les infos sur la catégorie du produit //
				JSONObject categoryObj = productObj.getJSONObject("category");
				String categoryKey = categoryObj.getString("id");
				String categoryName = categoryObj.getString("name");
				
				product.setCategory(new Category(categoryKey,categoryName));
				
				productsList.add(product);
			}
			
		} catch (ClientProtocolException e) {
			Log.e("productsRes", e.getMessage());
		} catch (IOException e) {
			Log.e("productsRes", e.getMessage());
		} catch (ParseException e) {
			Log.e("productsRes", e.getMessage());
		} catch (JSONException e) {
			Log.e("productsRes", e.getMessage());
		} catch (NullPointerException e) {
			Log.e("productsRes", e.getMessage());
		} catch (Exception e) {
			Log.e("productsRes", e.getMessage());
		}
		
		return productsList;
	}

}

package com.supinfo.supcommerce.REST;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class PreferencesREST {

	private SharedPreferences preferences = null;
	private String RestURI = null; 
	private Boolean RestActivated = null;
	
	public PreferencesREST(Context context) {
		preferences = PreferenceManager.getDefaultSharedPreferences(context);
		RestURI = "http://supcommercews146829.appspot.com/rest";
	}
	
	public String getRestURI() {
		if(RestURI == null) {
			if(preferences != null)
				RestURI = preferences.getString("rest_uri", "");
		}
		return RestURI;
	}
	
	public Boolean getRestActivated() {
		if(RestActivated == null) {
			if(preferences != null)
				RestActivated = preferences.getBoolean("rest_activated", false);
		}
		return RestActivated;
	}
}

package net.mypapit.mobile.simplediscount;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;

public class SettingsActivity extends Activity {
	EditText etvalue1, etvalue2,etvalue3,etvalue4,etvalue5,etvalue6;
	SharedPreferences prefs;
	
	
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.quickbutton);
		
		etvalue1 = (EditText) findViewById(R.id.etvalue1);
		etvalue2 = (EditText) findViewById(R.id.etvalue2);
		etvalue3 = (EditText) findViewById(R.id.etvalue3);
		etvalue4 = (EditText) findViewById(R.id.etvalue4);
		etvalue5 = (EditText) findViewById(R.id.etvalue5);
		etvalue6 = (EditText) findViewById(R.id.etvalue6);
		
		prefs = this.getSharedPreferences("net.mypapit.mobile.simplediscount", Context.MODE_PRIVATE);
		
		etvalue1.setText(prefs.getInt("first", 10)+"");
		etvalue2.setText(prefs.getInt("second", 20)+"");
		etvalue3.setText(prefs.getInt("third", 30)+"");
		etvalue4.setText(prefs.getInt("fourth", 50)+"");
		etvalue5.setText(prefs.getInt("fifth", 70)+"");
		etvalue6.setText(prefs.getInt("sixth", 0)+"");
		
		
		
		
		

		
	}
	
	public void onStop() {
		super.onStop();
		saveValues();
		
		
		
	}
	
	public void onPause() {
		super.onPause();
		saveValues();

		
	}
	
	public void saveValues() {
		
		
		prefs.edit().putInt(  "first", sanitizeValue(etvalue1,10)).commit();
		prefs.edit().putInt(  "second", sanitizeValue(etvalue2,20) ).commit();
		prefs.edit().putInt(  "third", sanitizeValue(etvalue3,30) ).commit();
		prefs.edit().putInt(  "fourth", sanitizeValue(etvalue4,50)).commit();
		prefs.edit().putInt(  "fifth", sanitizeValue(etvalue5,70)).commit();
		prefs.edit().putInt(  "sixth", sanitizeValue(etvalue6,0)).commit();
		
		
	}
	
	public int sanitizeValue(EditText etvalue, int orig){
		int returnvalue;
		String value = etvalue.getText().toString();
		try {
			returnvalue = Integer.parseInt(value);
			if (returnvalue <1){
				returnvalue = orig;
			}
		} catch (Exception ex) {
			returnvalue = orig;
		}
		
		return returnvalue;
		
	}
	
	

}

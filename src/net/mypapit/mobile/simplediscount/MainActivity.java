package net.mypapit.mobile.simplediscount;

import java.text.DecimalFormat;

import net.mypapit.mobile.ButtonSeekBar;

//import net.mypapit.mobile.net.mydiscountcalc.R;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager.NameNotFoundException;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class MainActivity extends Activity {
	ClearableEditText etDiscount;
	ButtonSeekBar seekbar;
	TextView tvNewPrice, tvSavings, tvPercentage,tvTax,tvTaxText;
	Button btn1, btn2, btn3, btn4, btn5;
	SharedPreferences prefs;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.discount_layout);

		etDiscount = (ClearableEditText) findViewById(R.id.etOriginal);

		etDiscount.setHint("Original Price");

		seekbar = (ButtonSeekBar) findViewById(R.id.buttonseekbar);
		// etDiscount = (EditText) layout.findViewById(R.id.etOriginal);
		tvNewPrice = (TextView) findViewById(R.id.tvNewPrice);
		tvSavings = (TextView) findViewById(R.id.tvSavings);
		tvTax = (TextView) findViewById(R.id.tvTax);
		tvTaxText = (TextView) findViewById(R.id.tvTaxText);

		tvPercentage = (TextView) findViewById(R.id.tvPercentage);

		//Button btn10, btn20, btn30, btn50, btn70;

		btn1 = (Button) findViewById(R.id.btn10);
		btn2 = (Button) findViewById(R.id.btn20);
		btn3 = (Button) findViewById(R.id.btn30);
		btn4 = (Button) findViewById(R.id.btn50);
		btn5 = (Button) findViewById(R.id.btn70);

		
		
		prefs = this.getSharedPreferences("net.mypapit.mobile.simplediscount", Context.MODE_PRIVATE);
		
		
		btn1.setText(prefs.getInt("first", 10) + "%");
		btn2.setText(prefs.getInt("second", 20) + "%");
		btn3.setText(prefs.getInt("third", 30) + "%");
		btn4.setText(prefs.getInt("fourth", 50) + "%");
		btn5.setText(prefs.getInt("fifth", 70) + "%");
		int tax=prefs.getInt("sixth",0);
		tvTaxText.setText("Tax ("+tax +"%)");
		
		LinearLayout layout = (LinearLayout) findViewById(R.id.layoutTax);
		if (tax <1) {
			
			layout.setVisibility(View.GONE);
			
			
		} else {
			
			layout.setVisibility(View.VISIBLE);
		}
		

		
		btn1.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				seekbar.setProgress( prefs.getInt("first", 10) );
				calculate();

			}
		});

		btn2.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				seekbar.setProgress( prefs.getInt("second", 20) );
				calculate();
			}
		});

		btn3.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				seekbar.setProgress( prefs.getInt("third", 30) );
				calculate();
			}
		});
		btn4.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				seekbar.setProgress( prefs.getInt("fourth", 50) );
				calculate();
			}
		});

		btn5.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				seekbar.setProgress( prefs.getInt("fifth", 70) );
				calculate();
			}
		});

		seekbar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				// TODO Auto-generated method stub
				tvPercentage.setText(seekBar.getProgress() + "%");

				try {
					calculate();
				} catch (Exception ex) {

				}

			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub

			}

		});
		seekbar.setProgress(20);
		
		etDiscount.addTextChangedListener(new TextWatcher() {

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				calculate();
				
				
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub
				
			}
			
			
		});
	}

	public void calculate() throws NumberFormatException,
			IllegalArgumentException {

		try {
			double original = Float.parseFloat(etDiscount.getText().toString());
			double newprice = (original * ((100 - seekbar.getProgress()) / 100.0));
			
			double tax = newprice * ( prefs.getInt("sixth", 0)/100.0 );
			
			newprice = newprice + tax;
			
			double savings = original - newprice;
			
			DecimalFormat df = new DecimalFormat("#.00");

			tvTax.setText(df.format(tax));
			tvNewPrice.setText(df.format(newprice));
			tvSavings.setText(df.format(savings));
			
			
		} catch (Exception ex) {

		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public boolean onOptionsItemSelected(MenuItem item){
		
		Intent intent;
		switch (item.getItemId()) {
		
		case R.id.action_settings:
			intent = new Intent();
			intent.setClassName(getBaseContext(), "net.mypapit.mobile.simplediscount.SettingsActivity");
			startActivity(intent);
			
		return true;
		
		case R.id.action_about:
    		// do something
    		try {
    		showDialog();
    		} catch (NameNotFoundException ex){
    			Toast toast = Toast.makeText(this, ex.toString(), Toast.LENGTH_SHORT);
    			toast.show();
		
    		}
		
		
    		return true;
    		
		case R.id.action_rate:
    		Intent urlIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + this.getPackageName()));
    		
    		try {
    			startActivity(urlIntent);
    			
    			
    		} catch (ActivityNotFoundException anfe){
    			Toast.makeText(this, "Couldn't launch Google Play store", Toast.LENGTH_LONG).show();
    		}
    		
    		return true;
		}
		return false;
		
	}
	
	private void showDialog() throws NameNotFoundException {
		// TODO Auto-generated method stub
		
		final Dialog dialog = new Dialog(this);
    	dialog.setContentView(R.layout.about_dialog);
    	dialog.setTitle("About Quick Discount "+ getPackageManager().getPackageInfo(getPackageName(), 0).versionName);
    	dialog.setCancelable(true);
    	
    	//text
    	TextView text = (TextView) dialog.findViewById(R.id.tvAbout);
    	text.setText(R.string.txtLicense);
    	
    	//icon image
    	ImageView img = (ImageView) dialog.findViewById(R.id.ivAbout);
    	img.setImageResource(R.drawable.ic_launcher);
    	
    	
    	
    	dialog.show();
    	
		
	}

	public void onResume() {
		super.onResume();
		btn1.setText(prefs.getInt("first", 10) + "%");
		btn2.setText(prefs.getInt("second", 20) + "%");
		btn3.setText(prefs.getInt("third", 30) + "%");
		btn4.setText(prefs.getInt("fourth", 50) + "%");
		btn5.setText(prefs.getInt("fifth", 70) + "%");
		
		int tax = prefs.getInt("sixth",0);
		tvTaxText.setText("Tax ("+tax +"%)");
		
		LinearLayout layout = (LinearLayout) findViewById(R.id.layoutTax);
		if (tax <1) {
			
			layout.setVisibility(View.GONE);
			
			
		} else {
			
			layout.setVisibility(View.VISIBLE);
		}
		

		calculate();
		
	}

}

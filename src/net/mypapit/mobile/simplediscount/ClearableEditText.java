/* 
 * 
 	Quick Discount Calculator
	Copyright 2013 Mohammad Hafiz bin Ismail <mypapit@gmail.com>
	http://blog.mypapit.net/
	http://myrepeater.googlecode.com/
 
 
 
  This file is part of Quick Discount Calculator.

    Quick Discount Calculator is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    Quick Discount Calculator is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with Quick Discount Calculator.  If not, see <http://www.gnu.org/licenses/>
    	
    	*/
package net.mypapit.mobile.simplediscount;


//import net.mypapit.mobile.simplediscount.R;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.app.Activity;

public class ClearableEditText extends RelativeLayout{
	
	
	
	
	LayoutInflater inflater = null;
	EditText edittext;
	public Button btnClear;
	

 	public ClearableEditText(Context context, AttributeSet attr) {
 
		super(context,attr);
		// TODO Auto-generated constructor stub
		init();
		
		
		
		
		
	}
	public ClearableEditText(Context context) {
		super(context);
		init();
	}
	
	public ClearableEditText(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
		
	}

	public void init() {
		
	}
	
	protected void onFinishInflate(){
		super.onFinishInflate();
		//inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		((Activity)getContext()).getLayoutInflater().inflate(R.layout.clearable_edit_text, this);
		
		//inflater.inflate(R.layout.clearable_edit_text,this,true);
		edittext = (EditText) findViewById(R.id.clearable_edit);
		btnClear = (Button) findViewById(R.id.clearable_button_clear);
		clearText();
		showHideClearButton();
		btnClear.setVisibility(RelativeLayout.INVISIBLE);

		
	}
	
	void clearText() 
	{
		btnClear.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				edittext.setText("");
				
				
			}
			
			
		
		
		});
		
		
		
		
	}
	
	void showHideClearButton() {
		
		edittext.addTextChangedListener(new TextWatcher() {

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				
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
				if (s.length()>0) {
					btnClear.setVisibility(RelativeLayout.VISIBLE);
					
					
				} else {
					btnClear.setVisibility(RelativeLayout.INVISIBLE);
					
				}
				
			}
			
			
			
			
		}
		
				);
		
		
		
	}

	public String getText() {
		// TODO Auto-generated method stub
		return edittext.getEditableText().toString();
	}

	public void setHint(String hint) {
		// TODO Auto-generated method stub
		edittext.setHint(hint);
		
	}

	public void addTextChangedListener(TextWatcher textwatcher) {
		// TODO Auto-generated method stub
		//edittext.addTextChangedListener(callsignViewActivity);
		edittext.addTextChangedListener(textwatcher);
		
		
	}
	
	
	

}
	

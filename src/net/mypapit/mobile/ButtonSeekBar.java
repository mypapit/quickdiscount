/*
 * Copyright (c) 2013, Mohammad Hafiz Ismail <mypapit@gmail.com> 
 * http://blog.mypapit.net/tag/java

All rights reserved.

Redistribution and use in source and binary forms, with or without modification, are
 permitted provided that the following conditions are met:

Redistributions of source code must retain the above copyright notice, this list of 
conditions and the following disclaimer.

Redistributions in binary form must reproduce the above copyright notice, this list 
of conditions and the following disclaimer in the documentation and/or other 
materials provided with the distribution.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY 
EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES 
OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT 
SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, 
SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT 
OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) 
HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR 
TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
 EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 
 */
package net.mypapit.mobile;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class ButtonSeekBar extends LinearLayout {
	private SeekBar seekbar;
	private Button btnPlus, btnMinus;
	String postfix = "";
	private int skipstep = 1;

	// private View mValue;
	private int progress = 0;

	public ButtonSeekBar(Context context) {
		this(context, null);
		// TODO Auto-generated constructor stub
	}

	public ButtonSeekBar(Context context, AttributeSet attrs) {
		super(context, attrs);

		btnMinus = new Button(context);
		addView(btnMinus, new LinearLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 0.15f));
		btnMinus.setText("-");

		seekbar = new SeekBar(context);
		addView(seekbar, new LinearLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 0.7f));

		btnPlus = new Button(context);
		addView(btnPlus, new LinearLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 0.15f));
		btnPlus.setText("+");

		seekbar.setMax(100);

		btnPlus.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View view) {
				// TODO Auto-generated method stub
				progress = seekbar.getProgress();
				seekbar.setProgress(progress + skipstep);

			}
		});

		btnMinus.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View view) {
				// TODO Auto-generated method stub
				progress = seekbar.getProgress();
				seekbar.setProgress(progress - skipstep);

			}
		});

	}

	/**
	 * Return the upperlimit of ButtonSeekBar
	 * 
	 * @return upper limit of ButtonSeekBar
	 */

	public int getMax() {
		return seekbar.getMax();

	}

	/**
	 * Get the progress bar current level of progress
	 * 
	 * @return current progress value
	 */
	public int getProgress() {
		return seekbar.getProgress();

	}

	/**
	 * 
	 * Get the current skip step value for the button in the Seekbar
	 * 
	 * By default the +/- button will increase/decrease seekbar value by 1.
	 * 
	 * 
	 * @return current value for the step
	 */

	public int getStep() {

		return this.skipstep;

	}

	/**
	 * Set the label for the Plus/Minus button in the ButtonSeekBar
	 * 
	 * Default label for plus and minus button is "+" and "-" respectively
	 * 
	 * @param plus
	 *            label for the plus button
	 * 
	 * @param minus
	 *            label for the minus button
	 */
	public void setButtonLabel(String plus, String minus) {
		this.btnPlus.setText(plus);
		this.btnMinus.setText(minus);

	}

	/**
	 * Set the range of the ButtonSeekBar to 0..max
	 * 
	 * @param max
	 *            the maximum value for SeekBar
	 */
	public void setMax(int max) {
		seekbar.setMax(max);

	}

	/**
	 * Sets a listener to receive notifications of changes to the
	 * ButtonSeekBar's progress level. Also provides notifications of when the
	 * user starts and stops a touch gesture within the SeekBar.
	 * 
	 * @param listener
	 *            the ButtonSeekBar notification listener
	 */
	public void setOnSeekBarChangeListener(OnSeekBarChangeListener listener) {

		seekbar.setOnSeekBarChangeListener(listener);

	}

	public void setPostfixText(String text) {
		this.postfix = text;

	}

	/**
	 * Set the current progress level for ButtonSeekBar
	 * 
	 * @param progress
	 *            progress level
	 */
	public void setProgress(int progress) {
		this.progress = progress;
		seekbar.setProgress(progress);

	}

	/**
	 * Set skip step for the button in the Seekbar
	 * 
	 * By default the +/- button will increase/decrease seekbar value by 1.
	 * Entering negative value may cause unexpected behavior in ButtonSeekbar
	 * 
	 * @param skip
	 *            the new value for the step
	 */

	public void setStep(int skip) {
		skipstep = skip;

	}

}

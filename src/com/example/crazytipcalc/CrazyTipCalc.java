package com.example.crazytipcalc;

import android.os.Bundle;
import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Spinner;
import android.widget.TextView;

public class CrazyTipCalc extends Activity {
	
	private static final String TOTAL_BILL = "TOTAL_BILL";
	private static final String CURRENT_TIP = "CURRENT_TIP";
	private static final String BILL_WITHOUT_TIP = "BILL_WITHOUT_TIP";
	
	private double billBeforeTip = 0.0;
	private double tipAmount = 0.0;
	private double finalBill = 0.0;
	
	EditText billBeforeTipET;
	EditText TipAmountET;
	EditText finalBillET;
	
	SeekBar tipSeekBar;
	
	private int[] checkListValues = new int[12];
	
	CheckBox friendlyCheckBox;
	CheckBox specialsCheckBox;
	CheckBox opinionCheckBox;
	
	RadioGroup availableRadioGroup;	
	RadioButton availableBadRadio;
	RadioButton availableOKRadio;
	RadioButton availableGoodRadio;
	
	Spinner problemsSpinner;
	
	Button startChronometerButton;
	Button pauseChronometerButton;
	Button resetChronometerButton;
	
	Chronometer timeWaitingChronometer;
	
	long secondsYouWaited = 0;
	
	TextView timeWaitingTextView;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_crazy_tip_calc);
		
		if(savedInstanceState == null)
		{
			billBeforeTip = 0.0;
			tipAmount = 0.15;
			finalBill = 0.0;
		}
		else
		{
			billBeforeTip = savedInstanceState.getDouble(BILL_WITHOUT_TIP);
			tipAmount = savedInstanceState.getDouble(CURRENT_TIP);
			finalBill = savedInstanceState.getDouble(TOTAL_BILL);
		}
		
		billBeforeTipET = (EditText) findViewById(R.id.billEditText);
		TipAmountET = (EditText) findViewById(R.id.tipEditText);
		finalBillET = (EditText) findViewById(R.id.finalBillEditText);
		
		tipSeekBar = (SeekBar) findViewById(R.id.changeTipSeekBar);
		
		
		tipSeekBar.setOnSeekBarChangeListener(tipSeekBarListener);
		
		billBeforeTipET.addTextChangedListener(billBeforeTipListener);
		
		friendlyCheckBox = (CheckBox) findViewById(R.id.friendlyCheckBox);
		specialsCheckBox = (CheckBox) findViewById(R.id.specialsCheckBox);
		opinionCheckBox = (CheckBox) findViewById(R.id.opinionCheckBox);
		
		setupIntroScheclBoxes();
		
	} 
	
	private TextWatcher billBeforeTipListener = new TextWatcher()
	{

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
			
			try
			{
				billBeforeTip = Double.parseDouble(s.toString());
			}
			catch(NumberFormatException e)
			{
				billBeforeTip = 0.0;
				
			}
			
			updateTipAndFinalBill(); 
		}
		
	};
	
	private void updateTipAndFinalBill()
	{
		double tipAmount = .15;
		try
		{
			tipAmount = Double.parseDouble(TipAmountET.getText().toString());
		}
		catch(NumberFormatException e)
		{
			tipAmount = .15;
		}
		
		double finalBill = billBeforeTip + (billBeforeTip * tipAmount);
		
		finalBillET.setText(String.format("%.02f", finalBill));
	}
	
	protected void onSaveInstanceState(Bundle outState)
	{
		super.onSaveInstanceState(outState);
		outState.putDouble(TOTAL_BILL,  finalBill);
		outState.putDouble(CURRENT_TIP, tipAmount);
		outState.putDouble(BILL_WITHOUT_TIP, billBeforeTip);
	}
	
	private OnSeekBarChangeListener tipSeekBarListener = new OnSeekBarChangeListener()
	{

		@Override
		public void onProgressChanged(SeekBar seekBar, int progress,
				boolean fromUser) {
			tipAmount = (tipSeekBar.getProgress()) * .01;
			TipAmountET.setText(String.format("%.02f", tipAmount));
			updateTipAndFinalBill(); 
			
			
		}

		@Override
		public void onStartTrackingTouch(SeekBar seekBar) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onStopTrackingTouch(SeekBar seekBar) {
			// TODO Auto-generated method stub
			
		}
		
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.crazy_tip_calc, menu);
		return true;
	}

}

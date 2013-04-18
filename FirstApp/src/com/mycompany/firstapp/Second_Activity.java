package com.mycompany.firstapp;

import android.app.Activity;
import android.widget.Toast;
import android.os.*;
import android.widget.*;


public class Second_Activity extends Activity
{

	private EditText edit_text;

	@Override
	public void onCreate( Bundle savedInstanceState ) 
	{
		super.onCreate( savedInstanceState );
		setContentView( R.layout.second );
		
		//add_key_listener();
	};
	
	public void add_key_listener( )
	{
		edit_text = (EditText) findViewById( R.id.act_02_et );
		
		
	};
}

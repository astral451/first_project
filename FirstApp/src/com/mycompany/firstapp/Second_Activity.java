package com.mycompany.firstapp;

import android.view.KeyEvent;
import android.view.View;
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
		
		add_key_listener();
	};
	
	View.OnKeyListener my_key_listender = new View.OnKeyListener( ) 
	{
	
		public boolean onKey( View v, int keycode, KeyEvent event )
		{
		
			if ( event.getAction( ) == KeyEvent.ACTION_DOWN ) 
			{
				if ( keycode == KeyEvent.KEYCODE_ENTER )
				{
					
					Toast.makeText( Second_Activity.this, edit_text.getText(), Toast.LENGTH_LONG ).show( );
					return true;
					
				} else if ( keycode == KeyEvent.KEYCODE_9 )
				{
					String message = new String( "Number 9 is pressed." );
					Toast.makeText( Second_Activity.this, message, Toast.LENGTH_LONG ).show( );
					return true;
					
				}
			} 
			return false;
		}
		
		
	};
	
	public void add_key_listener( )
	{
		//get edittext component
		edit_text = (EditText) findViewById( R.id.act_02_et );
		edit_text.setOnKeyListener( my_key_listender );

	};
}

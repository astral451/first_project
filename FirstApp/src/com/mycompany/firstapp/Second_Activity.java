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
	private CheckBox chkIos;
	private CheckBox chkAndroid;
	private CheckBox chkWindows;
	private Button btnDisplay;
	
	@Override
	public void onCreate( Bundle savedInstanceState ) 
	{
		super.onCreate( savedInstanceState );
		setContentView( R.layout.second );
		
		connect_interfaces();
		add_key_listener();
		add_check_listener( );
		add_button_listener( );
	};
	
	View.OnClickListener button_listener = new View.OnClickListener( )
	{
		
		@Override
		public void onClick( View v )
		{
			boolean ios = chkIos.isChecked( );
			boolean and = chkAndroid.isChecked( );
			boolean win = chkWindows.isChecked( );
			
			StringBuffer result = new StringBuffer( );
			result.append( "IPhone check :  ").append( ios );
			result.append( "\nAndroid check : ").append( and );
			result.append( "\nWindows check : ").append( win );
			
			//Print it out
			Toast.makeText( Second_Activity.this, result.toString(),Toast.LENGTH_LONG ).show( );
			
		}
		
	};
	
	View.OnClickListener check_box_listener = new View.OnClickListener( )
	{
		@Override
		public void onClick( View v )
		{
			
			if ( ( ( CheckBox ) v ).isChecked()) 
			{
				Toast.makeText( Second_Activity.this, "Bro, try Android", Toast.LENGTH_SHORT ).show( );
			}
			
		}
		
	};
	
	View.OnKeyListener key_listener = new View.OnKeyListener( ) 
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
	public void connect_interfaces( )
	{
		chkIos = ( CheckBox ) findViewById( R.id.chkIos );
		chkAndroid = ( CheckBox ) findViewById( R.id.chkAndroid );
		chkWindows = ( CheckBox ) findViewById( R.id.chkWindows );
		
		edit_text = ( EditText ) findViewById( R.id.act_02_et );
		btnDisplay = ( Button ) findViewById( R.id.btnDisplay );
		
	}
	
	public void add_check_listener( )
	{
		chkIos.setOnClickListener( check_box_listener ); 
	}	
	
	public void add_key_listener( )
	{
		edit_text.setOnKeyListener( key_listener );
	}
	
	public void add_button_listener( )
	{
		btnDisplay.setOnClickListener( button_listener );		
	}
}

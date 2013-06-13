package com.mycompany.firstapp;

import java.util.ArrayList;
import java.util.List;
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
	
	// country spinner stuff
	private Spinner country_spinner;
	private Spinner spinner2;
	private Button btn_submit;
	
	@Override
	public void onCreate( Bundle savedInstanceState ) 
	{
		super.onCreate( savedInstanceState );
		setContentView( R.layout.second );
		
		connect_interfaces();
		add_key_listener();
		add_check_listener( );
		add_button_listener( );
		
		// spinner setup
		add_items_to_spinner2( );
		//add_listener_on_button( );
		add_listener_on_spinner_item_selected( );
		
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
			
			Log_Data.add_entry( "Second results" );
			
			//Print it out
			Toast.makeText( Second_Activity.this, result.toString(),Toast.LENGTH_LONG ).show( );
			
		}
		
	};
	
	View.OnClickListener country_button_listener = new View.OnClickListener( )
	{
		
		
		@Override
		public void onClick( View v )
		{
			
			country_spinner = ( Spinner ) findViewById( R.id.spinCountry );
			spinner2 = ( Spinner ) findViewById( R.id.spinner2 );
			
			// make the display
			StringBuffer result = new StringBuffer( "" );
			
			result.append( "onClickListener : " );
			result.append( "\nspinner c : " + String.valueOf( country_spinner.getSelectedItem( ) ) ); 
			result.append( "\nspinner 2 : " + String.valueOf( spinner2.getSelectedItem( ) ) );
			
			Log_Data.add_entry( "country spinner" );
			
			Toast.makeText( Second_Activity.this, result.toString(), Toast.LENGTH_SHORT ).show();
			
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
			
			Log_Data.add_entry( "check box" );
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
					
				} else if ( keycode == KeyEvent.KEYCODE_9 ) {
					String message = new String( "Number 9 is pressed." );
					Log_Data.add_entry( "number 9" );
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
		
		country_spinner = ( Spinner ) findViewById( R.id.spinCountry );
		spinner2 = ( Spinner ) findViewById( R.id.spinner2 );
		btn_submit = ( Button ) findViewById( R.id.btnSubmit );
	}
	
	public void add_items_to_spinner2( )
	{
		
		List<String> list = new ArrayList<String>( );
		list.add( "list 1" );
		list.add( "list 2" );
		list.add( "list 3" );
		
		ArrayAdapter<String> data_adapter = new ArrayAdapter<String>( 
				this, 
				android.R.layout.simple_spinner_item,
				list  
				);
				
		data_adapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
		spinner2.setAdapter( data_adapter );
		
	}
	
	public void add_listener_on_spinner_item_selected( )
	{
		
		country_spinner = ( Spinner ) findViewById( R.id.spinCountry );
		// the next listener lives in a different .java file
		country_spinner.setOnItemSelectedListener( new custom_on_item_selected_listener() );
		
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
		btn_submit.setOnClickListener( country_button_listener );
	}
}

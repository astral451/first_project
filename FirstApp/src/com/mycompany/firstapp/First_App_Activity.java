package com.mycompany.firstapp;

import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import android.net.Uri;
import android.content.Intent;
import android.view.View.OnClickListener;




public class First_App_Activity extends Activity
{

	Button button;
	ImageButton image_button;
    Button new_button;
	Button third_act_button;
	//public Intent new_intent;
	
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

		addListenerOnButton();
    };
	
	public void go_to_second_activity( View view )
	{
		Intent new_intent = new Intent( First_App_Activity.this, Second_Activity.class);
		startActivity( new_intent );
	};
	
	public void addListenerOnButton( )
	{
		
		button = (Button) findViewById( R.id.button_01 );
		button.setOnClickListener( first_listener );
	
		image_button = (ImageButton) findViewById( R.id.image_button_03 );
		image_button.setOnClickListener( new_listener );
		
		
		third_act_button = (Button) findViewById( R.id.third_act_button );
		third_act_button.setOnClickListener( third_act_listener );
		//new_button = ( Button ) findViewById( R.id.button_02 );
		
		
	};
	
	View.OnClickListener third_act_listener = new OnClickListener( )
	{
		
		@Override
		public void onClick( View view )
		{
			Intent third_act_intent = new Intent( First_App_Activity.this, Third_Activity.class );
			startActivity( third_act_intent );			
		}
	};
	
	View.OnClickListener first_listener = new OnClickListener() 
	{

		@Override
		public void onClick( View arg0 ) {

			Intent browserIntent = 
				new Intent( Intent.ACTION_VIEW, Uri.parse("http://www.mkyong.com" ) );
			startActivity( browserIntent );
		}	
	}; 
	
	
	
	View.OnClickListener new_listener =  new OnClickListener( ) {
	
		@Override
		public void onClick( View arg0 ) {
			Toast.makeText( First_App_Activity.this, "Image button is clicked", Toast.LENGTH_SHORT ).show( );
			int x = 1;
			
		}
		
	
	
	
	};
}

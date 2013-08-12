package com.mycompany.firstapp;

import android.app.*;
import android.content.*;
import android.net.*;
import android.os.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;

import com.mycompany.firstapp.Log_Data;



public class First_App_Activity extends Activity
{

	final Context context  = this;
	Button button;
	ImageButton image_button;
    Button new_button;
	Button third_act_button;
	Button prog_act_button;
	Button popup_button;
	//public Intent new_intent;
	
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		Log_Data.add_entry( "First_App_Activity STARTED" );
		add_buttons();
    };
	
	public void go_to_prog_activity( View view )
	{
		// these are set up in the XML with android:onClick
		Log_Data.add_entry( "progress activity" );
		Intent prog_intent = new Intent( First_App_Activity.this, Progress_Activity.class );
		startActivity( prog_intent );
		
	};
	
	public void go_to_second_activity( View view )
	{
		// these are set up in the XML with android:onClick
		Log_Data.add_entry( "second activity" );
		Intent new_intent = new Intent( First_App_Activity.this, Second_Activity.class);
		startActivity( new_intent );
	};
	
	public void add_buttons( )
	{
		
		popup_button = ( Button ) findViewById( R.id.button_popup_dialog );
		// add the listener here.
		popup_button.setOnClickListener( popup_button_listener );
		
		button = (Button) findViewById( R.id.button_01 );
		button.setOnClickListener( first_listener );
	
		image_button = (ImageButton) findViewById( R.id.image_button_03 );
		image_button.setOnClickListener( new_listener );
		
		
		third_act_button = (Button) findViewById( R.id.third_act_button );
		third_act_button.setOnClickListener( third_act_listener );
		
		prog_act_button = ( Button ) findViewById( R.id.progres_act );
		
		
		
	};
	
	
	View.OnClickListener popup_button_listener = new OnClickListener( )
	{
		
		@Override
		public void onClick( View v )
		{
			
			final Dialog popup_dialog = new Dialog( context );
			popup_dialog.setContentView( R.layout.popup_dialog );
			popup_dialog.setTitle( "Title..." );
			
			TextView text_view = ( TextView ) popup_dialog.findViewById( R.id.popup_text );
			// finish up the custom view here.
			text_view.setText( "Android popup dialog" );
			ImageView image = ( ImageView ) popup_dialog.findViewById( R.id.popup_image );
			image.setImageResource( R.drawable.ic_launcher );
			
			Button button = ( Button ) popup_dialog.findViewById( R.id.popup_button );
			button.setOnClickListener( new OnClickListener( ) 
			{
				
				@Override
				public void onClick( View v )
				{
					
					popup_dialog.dismiss( );
					
				}
				
			} ); // end new listener
			
			popup_dialog.show( );
			
		}
		
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
			
		}
	};
}

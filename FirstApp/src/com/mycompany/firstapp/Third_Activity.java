package com.mycompany.firstapp;

import android.app.Activity;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Button;
import android.widget.ToggleButton;
import android.view.View;
import android.widget.Toast;



public class Third_Activity extends Activity
{


	private RadioGroup radio_sex_group;
	private RadioButton radio_sex_button;
	private Button btn_display;
	private ToggleButton btn_toggle;
	
	
	@Override
	public void onCreate( Bundle savedInstanceState )
	{
		super.onCreate( savedInstanceState );
		setContentView( R.layout.third );
		
		register_controls( );
	}
	
	public void register_controls( )
	{
		radio_sex_group = ( RadioGroup ) findViewById( R.id.radioSex );
		btn_display = ( Button ) findViewById( R.id.thirdActButton );
		btn_toggle = ( ToggleButton ) findViewById( R.id.thirdActToggleButton );
		
		radio_sex_group.setOnCheckedChangeListener( sex_group_listener );
		btn_display.setOnClickListener( sex_listener );	
	
		
	
	};
	
	RadioGroup.OnCheckedChangeListener sex_group_listener = new RadioGroup.OnCheckedChangeListener( )
	{
		
		@Override
		public void onCheckedChanged( RadioGroup r, int id )
		{
			if ( id == R.id.radioOther )
			{
				
				Toast.makeText( Third_Activity.this, "test on changed", Toast.LENGTH_SHORT ).show( );
				
			}
			
		}
		
	};
	
	View.OnClickListener sex_listener = new View.OnClickListener( )
	{
		
		@Override
		public void onClick( View v )
		{
			
			int selected_id = radio_sex_group.getCheckedRadioButtonId();
			radio_sex_button = (RadioButton) findViewById( selected_id );
			
			//Start the result string
			StringBuffer result = new StringBuffer( );
			
			if ( selected_id == R.id.radioOther )
			{
			
				//This appends if other is picked
				result.append( "Are you serious!?\n" );
					
			}
			
			result.append( "You have choosen:" );
			result.append( radio_sex_button.getText( ) );
			Toast.makeText( Third_Activity.this, result.toString() ,Toast.LENGTH_SHORT ).show( );
			
		}
	
	
	};
	
}

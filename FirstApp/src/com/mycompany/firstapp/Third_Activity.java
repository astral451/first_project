package com.mycompany.firstapp;

import android.app.Activity;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Button;
import android.widget.ToggleButton;
import android.view.View;
import android.widget.Toast;

// sound stuff
import android.media.AudioManager;
import android.media.SoundPool;
import android.media.SoundPool.OnLoadCompleteListener;
import java.util.prefs.Preferences;
import android.content.*;

// preferences




public class Third_Activity extends Activity
{


	private RadioGroup radio_sex_group;
	private RadioButton radio_sex_button;
	private Button btn_display;
	private Button play_audio;
	private ToggleButton btn_toggle;

	// Audio
	private SoundPool sound_pool;
	private int sound_id;
	private boolean sound_playing = false;
	boolean sound_loaded = false;
		
	
	
	@Override
	public void onCreate( Bundle savedInstanceState )
	{
		super.onCreate( savedInstanceState );
		setContentView( R.layout.third );
		
		register_controls( );
		register_audio( );
		
	}
	
	
	public void register_audio( )
	{
		
		sound_pool = new SoundPool( 10, AudioManager.STREAM_MUSIC, 0 );
		sound_pool.setOnLoadCompleteListener( new OnLoadCompleteListener( ) 
		{
			
			@Override
			public void onLoadComplete( SoundPool sound_pool, int sampleId, int status )
			{
				sound_loaded = true;
			}
		
		} );
		
		sound_id = sound_pool.load( this, R.raw.b01, 1 );
		
	};
	
	public void register_controls( )
	{
		radio_sex_group = ( RadioGroup ) findViewById( R.id.radioSex );
		btn_display = ( Button ) findViewById( R.id.thirdActButton );
		play_audio = ( Button ) findViewById( R.id.play_audio_button );
		btn_toggle = ( ToggleButton ) findViewById( R.id.thirdActToggleButton );
		
		radio_sex_group.setOnCheckedChangeListener( sex_group_listener );
		btn_display.setOnClickListener( sex_listener );	
		play_audio.setOnClickListener( audio_button_listener );
		btn_toggle.setOnClickListener( toggle_button_listener );
		
		SharedPreferences prefs = getPreferences( 0 );

		boolean toggle_state = prefs.getBoolean( "toggle", true );

		btn_toggle.setChecked( toggle_state );		
	
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

	View.OnClickListener toggle_button_listener = new View.OnClickListener( )
	{
		
		@Override
		public void onClick( View v )
		{
			
			boolean state = btn_toggle.isChecked( );
			// I guess I need to Cast it?  No idea why
			SharedPreferences prefs = getPreferences( 0 );			
			SharedPreferences.Editor editor = prefs.edit( );
			
			editor.putBoolean( "toggle", state );
			editor.commit();
		}
	};
	
	
	View.OnClickListener audio_button_listener = new View.OnClickListener( )
	{
		
		@Override
		public void onClick( View v )
		{ 
		
			// code here
			AudioManager audio_manager = ( AudioManager ) getSystemService( AUDIO_SERVICE );
			float actual_volume = audio_manager.getStreamVolume( AudioManager.STREAM_MUSIC );
			float max_volumne = audio_manager.getStreamMaxVolume( AudioManager.STREAM_MUSIC );
			
			float volume = actual_volume / max_volumne;
			
			boolean sound_playing = audio_manager.isMusicActive( );// AudioManager.STREAM_MUSIC );
			
			if ( sound_loaded  &&  !sound_playing )
			{
				sound_pool.play( sound_id, volume, volume, 1, 0, 1f );
				
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

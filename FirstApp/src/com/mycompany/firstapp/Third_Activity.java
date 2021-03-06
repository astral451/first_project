package com.mycompany.firstapp;

import android.util.TimeUtils;
import android.app.Activity;
import android.os.Bundle;
import android.widget.*;
import android.view.View;
import android.widget.AnalogClock;
import android.widget.ProgressBar;
import java.util.HashMap;

// Log Data
import com.mycompany.firstapp.Log_Data;

// sound stuff
import android.media.AudioManager;
import android.media.SoundPool;
import android.media.SoundPool.OnLoadCompleteListener;
import java.util.prefs.Preferences;
import android.content.*;
import com.mycompany.firstapp.Audio_Data;

import com.mycompany.firstapp.Log_View;
import android.text.method.*;
// preferences




public class Third_Activity extends Activity
{

	private Log_View log;
	private RadioGroup radio_sex_group;
	private RadioButton radio_sex_button;
	private Button btn_display;
	private Button play_a;
	private Button play_b;
	private ToggleButton btn_toggle;
    private android.widget.AnalogClock analog_clock;
    private Button btn_prog;
    private ProgressBar prog_bar;

	// Audio
	private SoundPool sound_pool;
	private int sound_id;
	boolean sound_loaded = false;
		
	//private Audio_Data audio_data = new Audio_Data( );
	
	private HashMap audio_data;// = new HashMap( );

	
	
	@Override
	public void onCreate( Bundle savedInstanceState )
	{
		super.onCreate( savedInstanceState );
		setContentView( R.layout.third );
		
		register_controls( );
		register_audio( );
		register_audio_data();
		
	}
	
	public void register_audio_data( )
	{
		
		audio_data = new HashMap<Integer, Integer> ( );
		
		sound_id = sound_pool.load( this, R.raw.a01, 1 );
		audio_data.put( R.id.play_letter_a, sound_id );
		sound_id = sound_pool.load( this, R.raw.b01, 1 );
		audio_data.put( R.id.play_letter_b, sound_id );
		
	}
	
	public void register_audio( )
	{
		
		sound_pool = new SoundPool( 10, AudioManager.STREAM_MUSIC, 0 );
		sound_pool.setOnLoadCompleteListener( new OnLoadCompleteListener( ) 
		{
			
			@Override
			public void onLoadComplete( SoundPool sound_pool, int sampleId, int status )
			{
				
				String text;
				text = "id : %s";
				text = text.format( Integer.toString( sampleId ) );
				Log_Data.add_entry( text );
				log.append_text( text );
				sound_loaded = true;
			}
		
		} );
		
	};
	
	public void register_controls( )
	{
		// a log view
		log = ( Log_View ) findViewById( R.id.log );
		log.setMovementMethod( new ScrollingMovementMethod( ) );
		log.set_auto_scroll( true );
		// examples of grouped controls
		radio_sex_group = ( RadioGroup ) findViewById( R.id.radioSex );
		btn_display = ( Button ) findViewById( R.id.thirdActButton );
		
		// audio button
		play_a = ( Button ) findViewById( R.id.play_letter_a );
		play_b = ( Button ) findViewById( R.id.play_letter_b );
		
		btn_toggle = ( ToggleButton ) findViewById( R.id.thirdActToggleButton );
		
		radio_sex_group.setOnCheckedChangeListener( sex_group_listener );
		btn_display.setOnClickListener( sex_listener );	
		play_a.setOnClickListener( audio_button_listener );
		play_b.setOnClickListener( audio_button_listener );
		btn_toggle.setOnClickListener( toggle_button_listener );
		
		SharedPreferences prefs = getPreferences( 0 );

		boolean toggle_state = prefs.getBoolean( "toggle", true );

		btn_toggle.setChecked( toggle_state );		

        analog_clock = ( AnalogClock ) findViewById( R.id.analogClock );



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
			
			int id = v.getId();
			
			//sound_id = audio_data.get( id );
			sound_id = (Integer) audio_data.get( id );
						
			// is the sound loaded and nothing is playing.
			if ( sound_loaded  &&  !audio_manager.isMusicActive() )
			{
	
				log.append_text( "Play:" );
				log.append_text( Integer.toString( sound_id ) );

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

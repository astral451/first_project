package com.mycompany.firstapp;

import android.app.Activity;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import com.mycompany.firstapp.audio.Tone;
import com.mycompany.firstapp.audio.Sine;


/**
 * Created by nathan on 8/20/13.
 */
public class Audio_Activity extends Activity {

	private Button start_audio;
	private Button stop_audio;
	private Tone new_tone;
	/*
	private Thread audio_thread;
	private AudioTrack audio_track;
	// All the audio stuff
	private final int duration = 3; // seconds
	private final int sample_rate = 8000; // samples per second
	private final int num_samples = duration * sample_rate;
	private final double sample[ ] = new double[num_samples];
	private final double freq_of_tone = 880; // hz

	private final byte generated_snd[ ] = new byte[ 2 * num_samples];
	*/
	Handler handler = new Handler( );


	@Override
	public void onCreate( Bundle savedInstanceState )
	{
		super.onCreate( savedInstanceState );
		setContentView( R.layout.audio );

		start_audio = ( Button )findViewById( R.id.start_audio );
		stop_audio = ( Button )findViewById( R.id.stop_audio );

		start_audio.setOnClickListener ( start_listener );
		stop_audio.setOnClickListener ( stop_listener );
	}

	View.OnClickListener start_listener =  new View.OnClickListener( )
	{
		@Override
		public void onClick( View view )
		{
			Thread audio_thread = new Thread( new Audio_Thread( ) );
			audio_thread.start( );
		}

	};

	View.OnClickListener stop_listener = new View.OnClickListener( )
	{

		@Override
		public void onClick( View view )
		{
			new_tone.stop_sound( );

		}
	};
	protected  void run_audio_thread( )
	{
		Thread audio_thread = new Thread( new Audio_Thread( ) );
		audio_thread.start( );
	}


	void play_sound( )
	{
		new_tone.play_sound( );
	}

	private class Audio_Thread implements Runnable
	{

		@Override
		public void run( )
		{

			//new_tone = com.mycompany.firstapp.audio.Sine( 1, 880 );
			new_tone = new Sine( 1, 880 );
			new_tone.create_sound();
			//gen_tone( );
			handler.post( new Runnable() {
				@Override
				public void run() {
					new_tone.play_sound();
				}
			});
		}

	}
}

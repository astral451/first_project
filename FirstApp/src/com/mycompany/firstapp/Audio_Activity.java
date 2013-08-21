package com.mycompany.firstapp;

import android.app.Activity;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

/**
 * Created by nathan on 8/20/13.
 */
public class Audio_Activity extends Activity {

	private Button start_audio;
	private Button stop_audio;

	private Thread audio_thread;
	private AudioTrack audio_track;
	// All the audio stuff
	private final int duration = 3; // seconds
	private final int sample_rate = 8000;
	private final int num_samples = duration * sample_rate;
	private final double sample[ ] = new double[num_samples];
	private final double freq_of_tone = 880; // hz

	private final byte generated_snd[ ] = new byte[ 2 * num_samples];

	Handler handler = new Handler( );


	@Override
	public void onCreate( Bundle savedInstanceState )
	{
		super.onCreate( savedInstanceState );
		setContentView( R.layout.audio );

		start_audio = ( Button )findViewById( R.id.start_audio );
		stop_audio = ( Button )findViewById( R.id.stop_audio );

		start_audio.setOnClickListener ( start_listener );
	}

	View.OnClickListener start_listener =  new View.OnClickListener( )
	{
		@Override
		public void onClick( View view )
		{
			run_audio_thread ();
		}

	};

	View.OnClickListener stop_listener = new View.OnClickListener ( ) {
		@Override
		public void onClick( View view ) {
			if ( audio_thread.isAlive ( ) )
			{
				audio_track.stop( );
			}
		}

	};

	protected  void run_audio_thread( )
	{
		Thread audio_thread = new Thread( new Audio_Thread( ) );
		audio_thread.start( );
	}

	void gen_tone( )
	{
		// fill the array
		for( int i = 0; i < num_samples; ++i )
		{
			sample[ i ] = Math.sin( 2 * Math.PI * i / ( sample_rate / freq_of_tone) );
		}

		// convert to 16 bit pcm sound array
		//assumes the sample buffer is normalized
		int idx = 0;
		for ( double d_val : sample )
		{
			short val = ( short ) ( d_val * 32767 );
			generated_snd[ idx++ ] = ( byte ) ( val & 0x00ff );
			generated_snd[ idx++ ] = ( byte ) ( ( val & 0xff00 ) >>> 8 );
		}
	}

	void play_sound( )
	{

		AudioTrack audio_track = new AudioTrack(
				AudioManager.STREAM_MUSIC,
				8000,
				AudioFormat.CHANNEL_OUT_DEFAULT,
				AudioFormat.ENCODING_PCM_16BIT,
				num_samples,
				AudioTrack.MODE_STATIC
				);
		audio_track.write(generated_snd, 0, num_samples );
		audio_track.play();

	}


	private class Audio_Thread implements Runnable
	{

		@Override
		public void run( )
		{

			gen_tone( );
			handler.post( new Runnable() {
				@Override
				public void run() {
					play_sound();
				}
			});
		}

		public void stop( )
		{
			handler.post( new Runnable ( ) {
				@Override
				public void run() {

					audio_track.stop( );
				}
			});
		}
	}
}

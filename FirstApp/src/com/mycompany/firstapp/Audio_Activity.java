package com.mycompany.firstapp;

import android.app.Activity;
import android.content.res.Resources;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.os.Bundle;
import android.os.Handler;
import android.view.DragEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.SeekBar;

import com.mycompany.firstapp.audio.Tone;
import com.mycompany.firstapp.audio.Sine;


/**
 * Created by nathan on 8/20/13.
 */
public class Audio_Activity extends Activity {

	private Button start_audio;
	private Button stop_audio;
	private Tone new_tone;
	private TextView freq_text_view;
	private SeekBar freq_seek_bar;

	private Integer low_freq;
	private Integer hi_freq;
	private Double final_freq;
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

		// This will get the resources from the resource files
		Resources res = getResources( );
		low_freq = res.getInteger( R.integer.low_freq );
		final_freq = ( double ) low_freq;
		hi_freq = res.getInteger( R.integer.hi_freq );

		freq_text_view = ( TextView )findViewById ( R.id.freq_text );
		freq_text_view.setText ( Integer.toString ( low_freq ) );
		freq_seek_bar = ( SeekBar )findViewById ( R.id.freq_seek_bar );

		freq_seek_bar.setOnSeekBarChangeListener ( freq_seek_bar_listener );

	}

	View.OnClickListener start_listener =  new View.OnClickListener( )
	{
		@Override
		public void onClick( View view )
		{
			Audio_Thread main_thread = new Audio_Thread ( );
			main_thread.set_freq ( final_freq );
			Thread audio_thread = new Thread( main_thread );
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
		Audio_Thread main_thread = new Audio_Thread( );
		main_thread.set_freq ( final_freq );

		Thread audio_thread = new Thread( main_thread );
		audio_thread.start( );
	}

	SeekBar.OnSeekBarChangeListener freq_seek_bar_listener = new SeekBar.OnSeekBarChangeListener( )
	{
		@Override
		public void onProgressChanged( SeekBar seek_bar, int progress, boolean from_user )
		{
			Integer freq_delta = hi_freq - low_freq;
			double percentage = progress / 100.0;
			double freq_from_percentage = percentage * freq_delta;
			final_freq = freq_from_percentage + low_freq;
			freq_text_view.setText ( Double.toString ( final_freq ) );
		}

		@Override
		public void onStartTrackingTouch( SeekBar seek_bar ){ }

		@Override
		public void onStopTrackingTouch( SeekBar seek_bar ){ }

	};

	void play_sound( )
	{
		new_tone.play_sound( );
	}

	private class Audio_Thread implements Runnable
	{
		private double frequency;

		public void set_freq( double freq )
		{
			frequency = freq;
		}
		@Override
		public void run( )
		{

			//new_tone = com.mycompany.firstapp.audio.Sine( 1, 880 );
			new_tone = new Sine( 10, frequency );
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

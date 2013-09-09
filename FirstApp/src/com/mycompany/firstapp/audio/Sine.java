package com.mycompany.firstapp.audio;

import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;

/**
 * Created by nathan on 8/24/13.
 */
public class Sine implements Tone
{
	public double frequency;

	private boolean keep_playing;
	//private Thread audio_thread;
	private AudioTrack audio_track;
	//private final int sample_rate = 8000; // samples per second
	private int duration; // seconds
	private int num_samples; // = duration * sample_rate;
	private double sample[ ];// = new double[num_samples];

	private byte generated_snd[ ];// = new byte[ 2 * num_samples];

	private boolean running = false;

	public Sine( int in_duration, double in_frequency )
	{
		this.duration = in_duration;
		this.frequency = in_frequency;
		create_sound( );

	}

	private void setup_audio_data( )
	{
		this.num_samples = duration * sample_rate;
		this.sample = new double[ num_samples ];
		this.generated_snd = new byte[ 2 * num_samples ];
	}

	@Override
	public void set_freq( double in_freq )
	{
		this.frequency = in_freq;
		create_sound( );
	}


	@Override
	public void set_duration( int in_duration)
	{
		this.duration = in_duration;
		create_sound( );
	}


	@Override
	public boolean is_running( )
	{
		
		return this.running;
		
	}
	
	
	void gen_tone( )
	{
		// fill the array
		for( int i = 0; i < num_samples; ++i )
		{
			sample[ i ] = Math.sin( 2 * Math.PI * i / ( sample_rate / frequency ) );
		}

		// convert to 16 bit pcm sound array
		//assumes the sample buffer is normalized
		int idx = 0;
		for ( double d_val : sample )
		{
			short val = ( short ) ( d_val * 32767 );
			this.generated_snd[ idx++ ] = ( byte ) ( val & 0x00ff );
			this.generated_snd[ idx++ ] = ( byte ) ( ( val & 0xff00 ) >>> 8 );
		}
	}

	@Override
	public void stop_sound( )
	{
		this.audio_track.stop();
		keep_playing = false;
		running = false;

	}

	@Override
	public void play_sound( )
	{

		this.audio_track = new AudioTrack(
				AudioManager.STREAM_MUSIC,
				8000,
				AudioFormat.CHANNEL_OUT_DEFAULT,
				AudioFormat.ENCODING_PCM_16BIT,
				num_samples,
				AudioTrack.MODE_STATIC
				);
		audio_track.write( generated_snd, 0, num_samples );
		keep_playing = true;
		audio_track.setLoopPoints ( 0, generated_snd.length / 4, -1 );
		audio_track.play();

		running = true;

	}
	
	
	@Override
	public void create_sound() {

		setup_audio_data ();
		gen_tone();
	}


}

package com.mycompany.firstapp.audio;

/**
 * Created by nathan on 8/24/13.
 */
public interface Tone {

	public static final int sample_rate = 8000;
	
	public boolean is_running( );
	public void set_freq( double in_freq );
	public void set_duration( int in_duration );
	public void create_sound( );
	public void play_sound( );
	public void stop_sound( );

}

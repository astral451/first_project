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
import java.util.*;
import android.os.*;


/**
 * Created by nathan on 8/20/13.
 */
public class Audio_Activity extends Activity {

	private Button start_audio;
	private Button stop_audio;
	private Tone new_tone;
	
	private Stack<Audio_Thread_01> current_threads;
	
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
	//{
		
	//	@Override
	//	private void handleMessage( Message message )
	//	{
				
	//	}
		
	//};


	@Override
	public void onCreate( Bundle savedInstanceState )
	{
		super.onCreate( savedInstanceState );
		setContentView( R.layout.audio );
		
		current_threads = new Stack<Audio_Thread_01>( );

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
			Audio_Thread_01 audio_thread = new Audio_Thread_01( );
			current_threads.push( audio_thread );
			current_threads.peek().start( ); //( current_threads.size( ) - 1 ).start( );
			//audio_thread.start( );
		}

	};

	View.OnClickListener stop_listener = new View.OnClickListener( )
	{

		@Override
		public void onClick( View view )
		{
			//new_tone.stop_sound( );
			// get the last thread
			if( current_threads.isEmpty() == false )
			{
				Audio_Thread_01 temp_thread = current_threads.pop( );//.currentThread();
				//if( temp_thread.isAlive() == true )
				//{
					temp_thread.stop_thread();
					
				//} 
				
			}
			
		}
	};
	//protected  void run_audio_thread( )
	//{
	//	Thread audio_thread = new Thread( new Audio_Thread( ) );
	//	audio_thread.start( );
	//}


	//void play_sound( )
	//{
	//	new_tone.play_sound( );
	//}

	// try to make a Thread
	private class Audio_Thread_01 extends Thread
	{
		
		public Handler handler = new Handler();
		private boolean keep_running = true;
		
		@Override
		public void run( )
		{
			//new_tone = com.mycompany.firstapp.audio.Sine( 1, 880 );
			new_tone = new Sine( 1, 440 );
			new_tone.create_sound();
			//gen_tone( );

			while( keep_running )
			{
				if ( new_tone.is_running() == false )
				{
					new_tone.play_sound();					
				}					
				//Thread.sleep( 10 );
			}
			new_tone.stop_sound();

		}


		
		public void stop_thread( )
		{
			
			handler.post( new Runnable() {
				
				@Override
				public void run( )
				{
					keep_running = false;	
				}
			});
		}		
	}
	
	
	private class Audio_Thread implements Runnable
	{

		private Tone new_tone;
		
		@Override
		public void run( )
		{
			
			//new_tone = com.mycompany.firstapp.audio.Sine( 1, 880 );
			new_tone = new Sine( 1, 880 );
			new_tone.create_sound();
			//gen_tone( );
			
			new_tone.play_sound();	
		
			
			/*
			handler.post( new Runnable() 
			{
				@Override
				public void run() 
				{
					new_tone.play_sound();	
					
				}
			});
			*/
		}
		
		public void stop()
		{
			handler.post(new Runnable() 
				{
					@Override
					public void run()
					{
						new_tone.stop_sound();	
					}
				});


		}

		

	}
}

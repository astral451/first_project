package com.mycompany.firstapp;
import android.app.*;
import android.app.ProgressDialog;
import android.os.*;
import android.view.*;
import android.widget.*;


public class Progress_Activity extends Activity
{

	Button prog_button;
	ProgressDialog prog_bar;
	private int progress = 0;
	private long filesize = 0;
	private Handler progress_handler = new Handler( );
	
	
	@Override
	public void onCreate( Bundle savedInstanceState )
	{
		
		super.onCreate( savedInstanceState );
		setContentView( R.layout.progress );
		
		register_controls( );	
	};

	public void register_controls( )
	{
		
		prog_button = ( Button ) findViewById( R.id.btn_progress );
		prog_button.setOnClickListener( prog_button_listener );
		
	}

	View.OnClickListener prog_button_listener = new View.OnClickListener( )
	{
		
		@Override
		public void onClick( View v )
		{
			
			
			prog_bar = new ProgressDialog( v.getContext() );
			prog_bar.setCancelable( true );
			prog_bar.setMessage( "File downloading..." );
			prog_bar.setProgress( 0 );
			prog_bar.setProgressStyle( ProgressDialog.STYLE_HORIZONTAL );
			prog_bar.setMax( 100 );
			prog_bar.show( );
			
			progress = 0;
			filesize = 0;
			
			new Thread( new Runnable( )
			{
				
				public void run( )
				{
					
					while( progress < 100 )
					{
						
						progress = do_some_stuff();
						sleep( 10 );
						
						progress_handler.post( new Runnable( ) 
						{
							
							public void run( )
							{
								
								prog_bar.setProgress( progress );
								
							}
							
						} );
						
					}// end while
					
					if ( progress >= 100 )
					{
						
						sleep( 30 );
						prog_bar.dismiss();
						
					}
				}
			} );
		}
	};
	
	// Makes the sleep call simpler
	public void sleep( int value )
	{
		try {
			Thread.sleep( value );
		} catch( InterruptedException e ) {
			e.printStackTrace( );
		}
		
	};
	
	public int do_some_stuff( )
	{
		
		
		while ( filesize <= 10000 )
		{
			filesize ++;
			
			if ( filesize == 2000 )
			{
				return 20;
			} else if ( filesize == 4000 )
			{
				return 40;
			}
			else if ( filesize == 6000 )
			{
				return 60;
			}
			else if ( filesize == 8000 )
			{
				return 80;
			}
			
		} // end while
		return 100;
		
	};
	
	
}

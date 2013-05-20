package com.mycompany.firstapp;
import android.app.*;
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
		
		
	}
	
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
			
			prog_bar = new ProgressDialog( v.getContext( ) );
			prog_bar.setCancelable( true );
			prog_bar.setMessage( "File downloading... " );
			prog_bar.setProgressStyle( ProgressDialog.STYLE_HORIZONTAL );
			prog_bar.setMax( 100 );
			prog_bar.setProgress( 0 );
			prog_bar.show( );
				
			progress = 0;
			filesize = 0;
			
			// thread stuff here
			
		}
		
	};
	
	
	Runnable prog_bar_work = new Runnable( )
	{
		
		public void run( )
		{
			while( progress < 100 )
			{
			
				progress = do_some_stuff( );	
				
				try {
					Thread.sleep( 1000 );
				} catch( InterruptedException e ) {
					//Interupt
					e.printStackTrace();
				}

				progress_handler.post( new Runnable( ) {
					
					public void run( )
					{
						
						prog_bar.setProgress( progress );
						
					}
					
				});
				
				
				
				
			}
			
			if ( progress >= 100 )
			{
				
				// wait to see the prog conclude
				try {
					Thread.sleep( 3000 );
				} catch( InterruptedException e ) {
					//Interupt
					e.printStackTrace();
				}
				
				prog_bar.dismiss();
				
			}
		}	
	};
	
	
	public int do_some_stuff( )
	{
		
		// needs more than this
		return 10;
		
	};
}

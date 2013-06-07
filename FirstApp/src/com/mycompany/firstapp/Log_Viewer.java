package com.mycompany.firstapp;

import android.app.*;
import android.os.*;
import android.widget.*;
import com.mycompany.firstapp.Log_Data;

public class Log_Viewer extends Activity
{

	private TextView text_view;
		
	@Override
	public void onCreate( Bundle savedInstanceState )
	{
		
		super.onCreate( savedInstanceState );
		setContentView( R.layout.log_viewer);
		
		TextView text_view = ( TextView ) findViewById( R.id.log_viewer_text );
		text_view.setText( Log_Data.get_entries() );
		
	}
//	
//	@Override
//	public void onCreate ( Bundle savedInstanceState )
//		{
//			super.onCreate ( savedInstanceState );
//			setContentView ( R.layout.main );
//
//			add_buttons ( );
//			};
	
}

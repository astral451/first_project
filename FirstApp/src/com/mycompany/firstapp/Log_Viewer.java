package com.mycompany.firstapp;

import android.app.*;
import android.os.*;
import com.mycompany.firstapp.*;
import java.util.*;

public class Log_Viewer extends Activity
{

	private com.mycompany.firstapp.Log_View text_view;
		
	@Override
	public void onCreate( Bundle savedInstanceState )
	{
		
		super.onCreate( savedInstanceState );
		setContentView( R.layout.log_viewer);
		
		text_view = ( com.mycompany.firstapp.Log_View ) findViewById( R.id.log_viewer_text );
		text_view.setText( split_log_data( Log_Data.get_entries() ) );

	}
	
	public String split_log_data( List entries  )
	{
		//int num_entries = entries. //entries.count;
		StringBuffer final_string = new StringBuffer();
		for( Object entry: entries )
		{
			String new_string = entry + "\n";
			final_string.append( new_string );			
		}
		
		return final_string.toString();
		
	}

	
}

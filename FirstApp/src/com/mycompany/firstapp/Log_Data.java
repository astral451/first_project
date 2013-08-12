package com.mycompany.firstapp;

import java.util.*;
import static java.lang.System.currentTimeMillis;

public class Log_Data
{

	// Holds lists of strings for the log database.
	private static List<String> entries = new ArrayList<String>( );
	private static Long _start_time = currentTimeMillis();
	private static List<Long> times = new ArrayList<Long>( );

	public static void add_entry( String string )
	{
		Long temp_time = currentTimeMillis();
		times.add( temp_time );
		entries.add( string );
	}
	
	public static List get_entries( )
	{
		List<String> return_list = new ArrayList<String>( );
		for( int i=0; i<entries.size(); i++){
			// The difference from the start of the app
			Long difference_time;
			difference_time = times.get( i ) - _start_time;
			float seconds = (float) ((float)difference_time / 1000.0);

			//Combine the data and time
			String time_as_string = String.valueOf( seconds );
			return_list.add( time_as_string + " : " + entries.get( i ) );
		}

		return return_list;
		
	}
}

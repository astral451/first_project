package com.mycompany.firstapp;

public class Log_Data
{

	private static String[] entries = new String[ 256 ];
	
	
	public static void add_entry( String string )
	{
		int empty_idx = 0;
		for ( int i = 1; i<entries.length; i++ ){
			
			if ( entries[ i ] == null ) {
				empty_idx = i;
				break;
			}
		}
		
		entries[ empty_idx ] = string;
	}
	
	public static String get_entries( )
	{
		
		return entries.toString();
		
	}
}

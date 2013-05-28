package com.mycompany.firstapp;

import java.util.*;

public class Log_Data
{

	private static List<String> entries = new ArrayList<String>( );
	
	
	public static void add_entry( String string )
	{
		
		entries.add( string );
	}
	
	public static String get_entries( )
	{
		
	
		return entries.toString();
		
	}
}

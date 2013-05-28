package com.mycompany.firstapp;

import java.util.*;

public class Log_Data
{

	private static List entries = new ArrayList( );
	
	
	public static void add_entry( String string )
	{
		
		entries.add( string );
	}
	
	public static String get_entries( )
	{
		
	
		return entries.toString();
		
	}
}

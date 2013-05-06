package com.mycompany.firstapp;

import java.util.HashMap;


public class Audio_Data 
{

	private HashMap data;
	
	@Override
	public void onCreate( )
	{

		data.put( R.id.play_letter_a, R.raw.a01 );
		data.put( R.id.play_letter_b, R.raw.b01 );
		
	}

	
	public int get( int id )
	{

		Object ret = data.get ( id ); //.hashCode( );
		return  ret.hashCode();

	}
	
		

}

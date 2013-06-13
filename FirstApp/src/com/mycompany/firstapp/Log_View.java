package com.mycompany.firstapp;
import android.content.*;
import android.os.*;
import android.widget.*;
import android.graphics.*;
import android.util.*;

public class Log_View extends TextView
{

	private boolean auto_scroll;
	
	// finishing adding Log_View constructor on the phone
	public Log_View( Context context )
	{
		
		super( context );
		//this.context = context;
		
	}
	
	
	public Log_View( Context context, AttributeSet attrs )
	{
		
		super( context, attrs );
		
	}
	
	
	public Log_View( Context context, AttributeSet attrs, int defStyle )
	{

		super( context, attrs, defStyle );

	}
	
	
	@Override
	protected void onMeasure( int widthMeasureSpec, int heightMeasureSpec )
	{
		
		super.onMeasure( widthMeasureSpec, heightMeasureSpec );
		
	}
	
	
	@Override
	protected void onDraw( Canvas canvas )
	{
		
		super.onDraw( canvas );
		
	}

	public void set_auto_scroll( boolean scroll )
	{
		
		auto_scroll = scroll;
	}
	
	public void clear_text( )
	{
		
		this.setText( "" );
		
	}
	
	public void append_text( String str )
	{
		
		String current_text = this.getText( ).toString();
		current_text += "\n";
		current_text += str;
		this.setText( current_text );
		
		if( auto_scroll )
		{
			int current_count = this.getLineCount();
			this.setScrollY( current_count * this.getLineHeight() - this.getHeight() );	
		}
	}
	
}

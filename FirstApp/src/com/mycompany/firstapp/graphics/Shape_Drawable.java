package com.mycompany.firstapp.graphics;

import android.content.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.graphics.drawable.shapes.*;
import android.view.*;

public class Shape_Drawable extends View
{

	private ShapeDrawable m_drawable;
	
	public Shape_Drawable( Context context )
	{
		super( context );
		
		int x = 10;
		int y = 10;
		int width = 300;
		int height = 50;
		
		m_drawable = new ShapeDrawable( new OvalShape( ) );
		m_drawable.getPaint( ).setColor( 0xff74AC23 );
		m_drawable.setBounds( x, y, x + width, y + height );
		
		
	}
	
	protected void onDraw( Canvas canvas )
	{
		
		m_drawable.draw( canvas );
		
	}

}

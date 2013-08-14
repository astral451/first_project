package com.mycompany.firstapp;

import java.util.ArrayList;
import java.util.List;

import android.os.AsyncTask;

import java.lang.Math;
import java.lang.Math;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;


/**
 * Created by nathan on 8/12/13.
 **/
public class Draw_View extends View implements OnTouchListener 
{

 	boolean allow_update = true;
	private static final String TAG = "DrawView";
	List<Point> points  = new ArrayList<Point>( );
	Paint paint = new Paint( );

	public Draw_View( Context context ) 
	{
		super( context );
		constructor();
	}

	public Draw_View( Context context, AttributeSet attrs )
	{
		super( context, attrs );
		constructor();
	}

	public Draw_View( Context context, AttributeSet attrs, int params )
	{
		super( context, attrs, params );
		constructor();
	}

	// A constructor for convienence
	private void constructor( )
	{
		Log_Data.add_entry( "Draw View" );
		setFocusable( true );
		setFocusableInTouchMode( true );
		this.setOnTouchListener( this );
		paint.setColor( Color.WHITE );
		paint.setAntiAlias( true );
	}

	public void update_points( List<Point> new_points  )
	{
		for( int i = 0; i < new_points.size(); i++ )
		{
			
			points.get( i ).radius = new_points.get( i ).radius;
			
		}
		
	}
	
	@Override
	public void onDraw( Canvas canvas ) {
		// Lets see if we can thread this.
		if( allow_update == true )
		{
			new Update_Circles().execute( points );
		}
		
		
		// now paint
		for ( Point point : points ) {
			paint.setColor( point.color );
			canvas.drawCircle( point.x, point.y, point.radius, paint );
		}
	}

	public boolean onTouch( View view, MotionEvent event ) 
	{
		int min_value = 64;
		int max_value = 255;
		int num_colors = 16;
		int min_radius = 4;
		int color_delta = ( max_value - min_value ) / num_colors;

		// first create a new point
		if( event.getAction() == MotionEvent.ACTION_DOWN ) {
			allow_update = false;
			// Action down amd make a point
			Point current_point = new Point();
			current_point.x = event.getX();
			current_point.y = event.getY();
			current_point.radius = min_radius;
			points.add( current_point );

			int point_size = points.size( );
			// looping the points and editing the colors
			for( int idx = point_size - 1; idx >= 0; idx-- )
			{

				// we are counting down from last index, but we need to
				// start from 0 to subtract 0 from 255 so that is what this
				// is doing.
				int color_index = point_size - idx - 1;
				
				color_index = Math.max( 0, color_index );

				//create the color, but ensure it doesn't fall below the delta
				int color_value = max_value - ( color_index  * color_delta );
				
				color_value = Math.max( color_value, min_value );
				points.get( idx  ).color = Color.rgb( color_value, color_value, color_value );
			}
		}

		// we are still moving around so make the circle larger
		if( event.getAction( ) == MotionEvent.ACTION_MOVE ) {
			Integer index = points.size() - 1;
			points.get( index ).radius += 2;
		}
		
		if( event.getAction() == MotionEvent.ACTION_UP ) 
		{
			// reset the update
			allow_update = true;
			
		}

		invalidate();
		return true;
	}


	
	private class Update_Circles extends AsyncTask< List<Point>, Void, List<Point> > {

		private List<Point> local_points;
		
		protected List<Point> doInBackground( List<Point>... points )
		{
			local_points = points[ 0 ];
			for( int i = local_points.size(); i > 0; i-- )
			{
				
				local_points.get( i ).radius += .25;
				//local_point.radius += .25;
			
			}
		
			return local_points;
		}
	
		protected void onPostExecute( List<Point> in_points )
		{
			
			update_points( in_points );
			
		}	
		
	}
}

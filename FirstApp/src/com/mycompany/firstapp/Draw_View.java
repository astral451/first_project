package com.mycompany.firstapp;

import java.util.ArrayList;
import java.util.List;

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
public class Draw_View extends View implements OnTouchListener {

	private static final String TAG = "DrawView";
	List<Point> points  = new ArrayList<Point>( );
	Paint paint = new Paint( );

	public Draw_View( Context context ) {
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
		setFocusable( true );
		setFocusableInTouchMode( true );
		this.setOnTouchListener( this );
		paint.setColor( Color.WHITE );
		paint.setAntiAlias( true );
	}

	@Override
	public void onDraw( Canvas canvas ) {
		for ( Point point : points ) {
			paint.setColor( point.color );
			canvas.drawCircle( point.x, point.y, point.radius, paint );
		}
	}

	public boolean onTouch( View view, MotionEvent event ) {
		int num_colors = 16;
		int color_delta = 256 / num_colors;

		// first create a new point
		if( event.getAction() == MotionEvent.ACTION_DOWN ) {
			// Action down amd make a point
			Point current_point = new Point();
			current_point.x = event.getX();
			current_point.y = event.getY();
			current_point.radius = 1;
			points.add( current_point );

			int point_size = points.size( );
			// looping the points and editing the colors
			for( int idx = point_size - 1; idx >= 0; idx-- )
			{

				// we are counting down from last index, but we need to
				// start from 0 to subtract 0 from 255 so that is what this
				// is doing.
				int color_index = point_size - idx - 1;
				if( color_index < 0 )
				{
					color_index = 0;
				}

				//create the color, but ensure it doesn't fall below the delta
				int color_value = 255 - ( color_index  * color_delta );
				if( color_value < color_delta )
				{
					color_value = color_delta;
				}
				points.get( idx  ).color = Color.rgb( color_value, color_value, color_value );
			}
		}

		// we are still moving around so make the circle larger
		if( event.getAction( ) == MotionEvent.ACTION_MOVE ) {
			Integer index = points.size() - 1;
			points.get( index ).radius += 2;
		}

		invalidate();
		return true;
	}
}


package com.mycompany.firstapp;

import java.util.ArrayList;
import java.util.List;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
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
		setFocusable( true );
		setFocusableInTouchMode( true );
		this.setOnTouchListener( this );
		paint.setColor( Color.WHITE );
		paint.setAntiAlias( true );
	}

	@Override
	public void onDraw( Canvas canvas ) {
		for ( Point point : points ) {
			canvas.drawCircle( point.x, point.y, point.radius, paint );
		}
	}

	public boolean onTouch( View view, MotionEvent event ) {

		// first create a new point
		if( event.getAction() == MotionEvent.ACTION_DOWN ) {
			Point current_point = new Point();
			current_point.x = event.getX();
			current_point.y = event.getY();
			current_point.radius = 1;
			points.add( current_point );
		}
		if( event.getAction( ) == MotionEvent.ACTION_MOVE ) {
			Integer index = points.size() - 1;
			points.get( index ).radius += 1;
		}
		invalidate();
		return true;
	}
}

class Point {
	float x, y, radius;

	@Override
	public String toString( ){
		return x + ", " + y + ", " + radius;
	}

}

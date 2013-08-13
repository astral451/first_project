package com.mycompany.firstapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.List;
import android.widget.*;


/**
 * Created by nathan on 8/12/13.
 */
public class Draw_Activity extends Activity {
	Draw_View draw_view;
	Button draw_button;
	List<Point> points = new ArrayList<Point>();

	@Override
	public void onCreate( Bundle savedInstanceState ) {
		super.onCreate( savedInstanceState );

		setContentView(R.layout.draw_view );
		//Set Full screen view
		//getWindow( ).setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		//requestWindowFeature( Window.FEATURE_NO_TITLE );
		
		draw_view = ( Draw_View ) findViewById( R.id.draw_view );
		//draw_view = new Draw_View( this );
		
		draw_button = ( Button ) findViewById( R.id.draw_button );
		
		setContentView( draw_view );
		draw_view.requestFocus( );

	}

	@Override
	public void onPause( )
	{
		points = draw_view.points;
		Log_Data.add_entry("leaving drawing");
		super.onPause();
	}

}

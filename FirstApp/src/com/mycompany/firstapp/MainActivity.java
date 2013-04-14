package com.mycompany.firstapp;

import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import android.net.Uri;
import android.content.Intent;
import android.view.View.OnClickListener;

public class MainActivity extends Activity
{

	Button button;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

		addListenerOnButton();
    }
	
	public void addListenerOnButton( ){
		
		button = (Button) findViewById( R.id.button_01 );
		
		button.setOnClickListener( new OnClickListener() {
			
			@Override
			public void onClick( View arg0 ) {
				
				Intent browserIntent = 
					new Intent( Intent.ACTION_VIEW, Uri.parse("http://www.mkyong.com" ) );
				startActivity( browserIntent );
			}
		} );
		
	}
}

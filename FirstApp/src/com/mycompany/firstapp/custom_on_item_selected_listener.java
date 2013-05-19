package com.mycompany.firstapp;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Toast;


public class custom_on_item_selected_listener implements OnItemSelectedListener
{

	public void onItemSelected( AdapterView<?> parent, View view, int pos, long id )
	{
		
		Toast.makeText( parent.getContext( ),
                    String.format("OnItemSelectedListener : %s", parent.getItemAtPosition(pos).toString()),
					Toast.LENGTH_SHORT ).show( );
						
	}
	
	@Override
	public void onNothingSelected( AdapterView<?> arg0 )
	{
		
		// TODO Auto-generated method stub
		
	}
	

}

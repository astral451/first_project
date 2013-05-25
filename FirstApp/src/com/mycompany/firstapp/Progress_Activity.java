package com.mycompany.firstapp;
import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;

import com.mycompany.firstapp.Log_Data;


public class Progress_Activity extends Activity
	{

		Button prog_button;
		ProgressDialog prog_bar;
		private int progress = 0;
		private long filesize = 0;
		private Handler progress_handler = new Handler ( );


		@Override
		public void onCreate ( Bundle savedInstanceState )
			{

				super.onCreate ( savedInstanceState );
				setContentView ( R.layout.progress );

				register_controls ( );	
			};

		public void register_controls ( )
			{

				prog_button = ( Button ) findViewById ( R.id.btn_progress );
				//prog_button.setOnClickListener( prog_button_listener );
				Button log_button = ( Button ) findViewById ( R.id.btn_pringlog );
				log_button.setOnClickListener ( log_button_listener );

			}

		View.OnClickListener prog_button_listener = new View.OnClickListener ( )
			{

				@Override
				public void onClick ( View v )
					{


						prog_bar = new ProgressDialog ( v.getContext ( ) );


						progress = 0;
						filesize = 0;

					}


			};

		View.OnClickListener log_button_listener = new View.OnClickListener ( )
			{

				@Override
				public void onClick ( View v )
					{

						String string = Log_Data.get_entries ( );
						Toast.makeText(Progress_Activity.this, string, Toast.LENGTH_SHORT ).show();
					}
			};

		public int do_some_stuff ( )
			{

				// needs more than this
				return 10;

			};


	}

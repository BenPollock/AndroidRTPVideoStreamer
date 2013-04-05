package com.ben.bpolloc3_clientstream;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainClientView extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_client_view);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main_client_view, menu);
		return true;
	}
	
	public void setup(View view){
		System.out.println("Setup");
		
		String movieFile = ((EditText)findViewById(R.id.editMjpeg)).getText().toString();
		System.out.println(movieFile);
		
	}
	
	public void play(View view){
		
	}
	
	public void pause(View view){
		
	}
	
	public void teardown(View view){
		
	}

}

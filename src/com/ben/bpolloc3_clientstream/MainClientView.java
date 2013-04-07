package com.ben.bpolloc3_clientstream;

import java.io.IOException;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainClientView extends Activity {
	
	public Rtsp RTSP;

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
	
	public void setup(View view) throws IOException{
		System.out.println("Setup");
		
		//Get the variables
		String movie_file = ((EditText)findViewById(R.id.editMjpeg)).getText().toString();
		String ip_addr = ((EditText)findViewById(R.id.editIP)).getText().toString();
		String string_port = ((EditText)findViewById(R.id.editPort)).getText().toString();
		int port = Integer.parseInt(string_port);
		
		//Create RTSP and call setup
		RTSP = new Rtsp(movie_file,ip_addr,port);
		RTSP.setup();
		
		
		
		
		
	}
	
	public void play(View view){
		
	}
	
	public void pause(View view){
		
	}
	
	public void teardown(View view){
		
	}

}

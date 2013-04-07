package com.ben.bpolloc3_clientstream;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainClientView extends Activity {
	
	String movie_file;
	String ip_addr;
	int port;
	int c_seq = 1;

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
		
		//Get the variables
		movie_file = ((EditText)findViewById(R.id.editMjpeg)).getText().toString();
		ip_addr = ((EditText)findViewById(R.id.editIP)).getText().toString();
		String string_port = ((EditText)findViewById(R.id.editPort)).getText().toString();
		port = Integer.parseInt(string_port);
		
		//Generate the RTSP Message
		String rtsp_setup = "SETUP rtsp://" + ip_addr + ":3000" + "/" + movie_file + " RTSP/1.0" + "\nCSeq: " + c_seq +"\n"+"Transport: RTP/UDP; client_port= " + port;
		System.out.println(rtsp_setup);
		
		//Write to UDP Port
		
		
	}
	
	public void play(View view){
		
	}
	
	public void pause(View view){
		
	}
	
	public void teardown(View view){
		
	}

}

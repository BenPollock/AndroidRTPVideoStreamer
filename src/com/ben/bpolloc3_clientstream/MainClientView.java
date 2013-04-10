package com.ben.bpolloc3_clientstream;

import java.io.IOException;

import android.os.Bundle;
import android.os.StrictMode;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainClientView extends Activity {
	
	StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
	
	public Rtsp RTSP;
	public String state;  //START->READY->PLAY/PAUSE/TEAR

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_client_view);
		StrictMode.setThreadPolicy(policy);
		state = "START";
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main_client_view, menu);
		return true;
	}
	
	public void setup(View view){
		
		//Send Setup Message
		try{
			if(state.equals("START")){
				System.out.println("Setup");
				
				//Get the variables
				String movie_file = ((EditText)findViewById(R.id.editMjpeg)).getText().toString();
				String ip_addr = ((EditText)findViewById(R.id.editIP)).getText().toString();
				String string_port = ((EditText)findViewById(R.id.editPort)).getText().toString();
				int port = Integer.parseInt(string_port);
				
				//Create RTSP and call setup
				RTSP = new Rtsp(movie_file,ip_addr,port);
				if(RTSP.setup()){
					state = "READY";
					System.out.println("State is now ready");
				}
			}
		}catch(Exception e){
			System.out.println(e);
		}
		
	}
	
	public void play(View view) throws IOException{
		
		//Call Play
		RTSP.play();
		
		
	}
	
	public void pause(View view){
		
	}
	
	public void teardown(View view){
		
	}

}

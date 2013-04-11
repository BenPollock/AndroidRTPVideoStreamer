package com.ben.bpolloc3_clientstream;

import java.util.Timer;
import java.util.TimerTask;

import android.os.Bundle;
import android.os.StrictMode;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Menu;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;

public class MainClientView extends Activity {
	
	StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
	
	public Rtsp RTSP;
	public String state;  //START->READY->PLAY/PAUSE/TEAR
	public Timer timer;
	public Rtp RTP;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_client_view);
		StrictMode.setThreadPolicy(policy);
		state = "START";
		timer = new Timer();
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
					RTP = new Rtp(port);
					System.out.println("State is now ready");
				}
			}
		}catch(Exception e){
			System.out.println(e);
		}
		
	}
	
	public void play(View view){
		if(state.equals("READY")){
			try{
				//Call Play
				if(RTSP.play()){
					state = "PLAY";
					System.out.println("Now Playing");
					
					//Init Timer
					timer.schedule(new TimerTask(){
						@Override
						public void run(){
							TimerTick();
						}
					}, 0,60);
				}
			}catch(Exception e){
				System.out.println(e);
			}
		}
		
		//If paused, notify the timer to run
		if(state.equals("PAUSE")){
			try{
				if(RTSP.play()){
					state = "PLAY";
					System.out.println("Video Unpaused");
					
					//Notify timer
					timer.notify();
				}
			}catch(Exception e){
				System.out.println(e);
			}
		}
		
		
	}
	
	public void pause(View view){
		if(state.equals("PLAY")){
			try{
				//Call Pause
				if(RTSP.pause()){
					state = "PAUSE";
					System.out.println("Video Paused");
					
					//Stop Timer
					timer.wait();
				}
			}catch(Exception e){
				System.out.println(e);
			}
		}
	}
	
	public void teardown(View view){
		if(state.equals("PLAY") || state.equals("PAUSE") || state.equals("READY")){
			try{
				//Call teardown
				if(RTSP.teardown()){
					state = "START";
					RTP.destroySocket();
					//Destroy timer
					timer.wait();
				}
			}catch(Exception e){
				System.out.println(e);
			}
		}
		
	}
	
	public void teardownnobutton(){
		try{
			//Call teardown
			if(RTSP.teardown()){
				state = "START";
				RTP.destroySocket();
				//Destroy timer
				timer.wait();
			}
		}catch(Exception e){
			System.out.println(e);
		}
	}
	
	//Hides the keyboard
	public void hideKeyboard(View view){
		InputMethodManager inputMethodManager = (InputMethodManager) this.getSystemService(Activity.INPUT_METHOD_SERVICE);
	    inputMethodManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 0);
	}
	
	//Called when the timer goes off
	private void TimerTick(){
		//Teardown if finished
		if(RTP.getFinished()){
			teardownnobutton();
			return;
		}
	
		//RTP should handle this stuff
		runOnUiThread(new Runnable(){
			public void run(){
				Bitmap bmp = RTP.timerEvent();
				if(bmp != null){
					try{
					ImageView image = (ImageView)findViewById(R.id.imageView);
					image.setImageBitmap(bmp);
					}catch(Exception e){
						System.out.println(e);
					}
				}
			}
		});
		
	}

}

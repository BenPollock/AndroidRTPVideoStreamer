package com.ben.bpolloc3_clientstream;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

import android.graphics.Bitmap;
import android.os.StrictMode;

public class Rtp {
	StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
	//Each Rtp instance should have a packet
	//Previous packets will be overwritten by the current packet
	RtpPacket rtp_packet;
	private byte[] buf;
	private DatagramSocket socket;
	private int port;
	private DatagramPacket receive_packet;
	private int plength;
	private int largestFrame;
	
	//Constructor
	public Rtp(int port){
		buf = new byte[15000];
		this.port = port;
		largestFrame = 0;
		try{
			socket = new DatagramSocket(25000);
			socket.setSoTimeout(10);
		}catch(Exception e){
			System.out.println("Error Creating Socket: " + e);
		}
	}
	
	//Get the next video frame
	public Bitmap timerEvent(){
		
		//Create packet
		receive_packet = new DatagramPacket(buf, buf.length);
		
		try{
			//Receive packet
			socket.receive(receive_packet);
			rtp_packet = new RtpPacket(receive_packet.getData(), receive_packet.getLength());
			Bitmap bmp = rtp_packet.getBmp();
			
			//Commented out now that we're getting BMP in packet
			//Get payload
			/*plength = rtp_packet.getLength();
			if(plength>largestFrame){
				largestFrame = plength;
				System.out.println("The Largest Frame is: " + largestFrame);
			}
			byte[] payload = new byte[plength];
			for(int i = 0; i < plength; i++){
				payload[i] = rtp_packet.getPayload()[i];
			}*/
			
			System.out.println("Got Payload!!!!!");
			return bmp;
			
		}
		//Sometimes the socket won't be made correctly if the user puts in an incorrect IP
		catch(java.lang.NullPointerException en){
			try{
				socket = new DatagramSocket(25000);
				socket.setSoTimeout(10);
			}catch(Exception e){
				System.out.println("Error Creating Socket Again!");
			}
			return null;
		}
		catch(java.net.BindException be){
			System.out.println("UH oh, bind exception");
			return null;
		}
		catch(java.net.SocketTimeoutException se){
			System.out.println("Reached end of video, teardown");
			return null;
		}
		catch(Exception e){
			System.out.println("Error with UDP, Packet Dropped");
			System.out.println(e);
			return null;
		}
		
	}
	
	public int getPlength(){
		return plength;
	}
	
	public void destroySocket(){
		socket.close();
	}
	
	


}

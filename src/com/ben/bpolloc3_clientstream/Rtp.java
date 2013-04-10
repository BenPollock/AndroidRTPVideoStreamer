package com.ben.bpolloc3_clientstream;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

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
	
	//Constructor
	public Rtp(int port){
		buf = new byte[15000];
		this.port = port;
		try{
			socket = new DatagramSocket(25000);
			socket.setSoTimeout(5);
		}catch(Exception e){
			System.out.println("Error Creating Socket: " + e);
		}
	}
	
	//Get the next video frame
	public void timerEvent(){
		
		//Create packet
		receive_packet = new DatagramPacket(buf, buf.length);
		
		try{
			//Receive packet
			socket.receive(receive_packet);
			rtp_packet = new RtpPacket(receive_packet.getData(), receive_packet.getLength());
			
			//Get payload
			int plength = rtp_packet.getLength();
			byte[] payload = new byte[plength];
			for(int i = 0; i < plength; i++){
				payload[i] = rtp_packet.getPayload()[i];
			}
			
			System.out.println("Got Payload!!!!!");
			
		}catch(Exception e){
			System.out.println("Error with UDP, Packet Dropped");
			System.out.println(e);
		}
		
	}


}

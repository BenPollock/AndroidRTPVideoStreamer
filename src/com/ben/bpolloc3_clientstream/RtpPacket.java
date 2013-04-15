package com.ben.bpolloc3_clientstream;

import java.util.Date;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class RtpPacket {
	private Bitmap bmp;
	
	private byte[] header;
	private byte[] payload;
	
	
	//Constructor
	public RtpPacket(){
		
	}
	
	//Special Constructor for received packets
	public RtpPacket(byte[] packet, int size){
		
		//This is commented out because the bmp line removes the need for it
		/*
		//Load header
		header = new byte[12];
		for(int i = 0; i < 12; i++)
			header[i] = packet[i];
		
		//Load payload
		int payload_length = size - 12;
		payload = new byte[payload_length];
		for (int i = 12; i < payload_length; i++)
			payload[i-12] = packet[i];
			*/
		
		bmp = BitmapFactory.decodeByteArray(packet, 12, packet.length - 12);
	}
	
	public void initPacket(String server_message){
		
	}
	
	public byte[] getPayload(){
		return payload;
	}

	public int getLength(){
		return payload.length;
	}
	
	public Bitmap getBmp(){
		return bmp;
	}

}

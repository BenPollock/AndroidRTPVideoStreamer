package com.ben.bpolloc3_clientstream;

import java.util.Date;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class RtpPacket {
	
	private int version;
	private int padding;
	private int extension;
	private int cc;
	private int marker;
	private int payload_type;
	private int sequence_number;
	private Date timestamp;
	private int ssrc;
	private Bitmap bmp;
	
	private byte[] header;
	private byte[] payload;
	
	
	//Constructor
	public RtpPacket(){
		
	}
	
	//Special Constructor for received packets
	public RtpPacket(byte[] packet, int size){
		
		version = 2;
		padding = 0;
		extension = 0;
		cc = 0;
		marker = 0;
		ssrc = 0;
		
		//This is commented out because the new bmp line removes the need for it
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
		
		System.out.println(payload);
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

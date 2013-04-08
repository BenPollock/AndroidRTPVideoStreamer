package com.ben.bpolloc3_clientstream;

import java.net.*;
import java.io.*;

import android.os.StrictMode;

public class Rtsp {
	
	
	private String movie_file;
	private String ip_addr;
	private int port;
	private int c_seq;
	private int session;
	private BufferedReader RTSPBufferedReader;
	private BufferedWriter RTSPBufferedWriter;
	private InetAddress addr;
	
	private Socket rtsp_socket;
	
	//Constructor
	public Rtsp(String movie_file, String ip_addr, int port) throws IOException{
		this.movie_file = movie_file;
		this.ip_addr = ip_addr;
		this.port = port;
		this.c_seq = 1;
		//Session given from setup
		this.session = 0;
		this.addr = InetAddress.getByName(ip_addr);
		
		//Create RTSP Socket
		this.rtsp_socket = new Socket(ip_addr,port);
		//Create RTSP Writers/Readers
		RTSPBufferedReader = new BufferedReader(new InputStreamReader(rtsp_socket.getInputStream()) );
		RTSPBufferedWriter = new BufferedWriter(new OutputStreamWriter(rtsp_socket.getOutputStream()) );
	}
	
	//SETUP
	public boolean setup() throws IOException{
		
		//Create RTSP Message
		String rtsp_setup = "SETUP rtsp://" + ip_addr + ":3000" + "/" + movie_file + " RTSP/1.0" + "\nCSeq: " + c_seq +"\n"+"Transport: RTP/UDP; client_port= " + 25000;
		System.out.println(rtsp_setup);
		
		//Create & Send Packet
		RTSPBufferedWriter.write(rtsp_setup);
		RTSPBufferedWriter.flush();
		//Receive response
		//This isn't working for some reason
		//DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
		//socket.receive(receivePacket);	
		
		return true;
	}
	
	public boolean play() throws IOException{
		//Create RTSP Message
		String rtsp_play = "PLAY rtsp://" + ip_addr + ":3000" + "/" + movie_file + "RTSP/1.0" + "\nCSeq: " + c_seq + "\n" +"Session: " + session;
		
		//Write to UDP Port
		byte[] sendData = new byte[1024];
		byte[] receiveData = new byte[1024];
		
		sendData = rtsp_play.getBytes();
		InetAddress addr;
		addr = InetAddress.getByName(ip_addr);
		
		//Create Packets
		DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, addr, 9876);
		
		
	//	socket.send(sendPacket);
		
		//Receive Response
		
		return true;
	}
	//TODO : do this soon
	//Gets the return code
	private int getRTSPResponse(){
		return 200;
	}
	
	
	
	//Getters and Setters
	public String getMovie_file(){
		return movie_file;
	}
	public void setMovie_file(String movie_file){
		this.movie_file = movie_file;
	}
	
	public String getIp_addr(){
		return ip_addr;
	}
	public void setIp_addr(String ip_addr){
		this.ip_addr = ip_addr;
	}
	
	public int getPort(){
		return port;
	}
	public void setPort(int port){
		this.port = port;
	}
	
	
	

}

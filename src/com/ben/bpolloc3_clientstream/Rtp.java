package com.ben.bpolloc3_clientstream;

import java.net.InetAddress;

public class Rtp {
	
	InetAddress IP;
	int port;
	//Each Rtp instance should have a packet
	RtpPacket rtp_packet;
	
	//Constructor
	public Rtp(InetAddress IP, int port){
		this.IP = IP;
		this.port = port;
		
		//Create RTP Packet
		
		
	}


}

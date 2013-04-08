package com.ben.bpolloc3_clientstream;

import java.util.Date;

public class RtpPacket {
	
	int version;
	int padding;
	int extension;
	int cc;
	int marker;
	int payload_type;
	int sequence_number;
	Date timestamp;
	int ssrc;
	
	//Constructor
	public RtpPacket(){
		
	}
	
	public void initPacket(String server_message){
		
	}

}

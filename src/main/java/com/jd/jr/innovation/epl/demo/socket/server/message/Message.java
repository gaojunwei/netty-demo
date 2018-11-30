package com.jd.jr.innovation.epl.demo.socket.server.message;

import java.io.Serializable;
import java.nio.charset.Charset;

public class Message implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 消息协议格式：包头:2 byte,长度:4 byte,Json数据区:n byte,包尾:2 byte;
	 */
	private byte[] startFlage = new byte[2];//消息头2个字节
	private int bodyLength = 0;//包体的长度4个字节
	byte[] bodyData = null;//消息体内容
	private byte[] endFlage = new byte[2];//消息结尾2个字节

	public Message(String data) {
		//包头
		this.startFlage[0] = (byte) 0xaa;
		this.startFlage[1] = (byte) 0xaa;
		//消息体内容
		this.bodyData = data.getBytes(Charset.forName("UTF-8"));
		//消息体长度
		this.bodyLength = this.bodyData.length;
		//包尾
		this.endFlage[0] = (byte) 0x0d;//'\r'
		this.endFlage[1] = (byte) 0x0a;//'\n'

	}

	public int getBodyLength() {
		return bodyLength;
	}

	public int getLength()
	{
		return (8+bodyData.length);
	}

	public byte[] getStartFlage() {
		return startFlage;
	}

	public byte[] getBodyData() {
		return bodyData;
	}

	public byte[] getEndFlage() {
		return endFlage;
	}
}

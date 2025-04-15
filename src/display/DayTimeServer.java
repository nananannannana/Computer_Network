package display;

import java.io.*;
import java.net.*;
import java.util.Date;

public class DayTimeServer {
	public final static int daytimeport=13;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ServerSocket theServer;
		Socket theSocket = null;
		BufferedWriter writer;
		try {
			//13번 포트에서 클라이언트의 접속 요청을 기다리는 서버소켓 객체를 생성
			theServer = new ServerSocket(daytimeport);
			while(true) {
				try {
					// 클라이언트의 접속요청을 기다리고 클라이언트의 소켓과 연결된 서버 측의 소켓(theSocket)을 생성
					theSocket = theServer.accept();
					// 클라이언트에 데이터를 전송할 OutputStream 객체를 생성
					OutputStream os = theSocket.getOutputStream();
					//클라이언트에 데이터를 전송하는 데이터를 전송하는 BufferedWriter 객체 생성
					writer = new BufferedWriter(new OutputStreamWriter(os));
					Date now = new Date();
					writer.write(now.toString() + "\r\n");//날짜를 전송
					writer.flush();
					theSocket.close();
				}catch(IOException e) {
					System.out.println(e);
				}
			}
		}catch(IOException e) {
			System.out.println(e);
		}finally {
			try {
				if(theSocket != null) theSocket.close();
			}catch(IOException e) {
				System.out.println(e);
			}
		}
	}

}

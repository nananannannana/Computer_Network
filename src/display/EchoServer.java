package display;

import java.io.*;
import java.net.*;


public class EchoServer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ServerSocket theServer;
		Socket theSocket = null;
		InputStream is;
		BufferedReader reader;
		OutputStream os;
		BufferedWriter writer;
		String theLine;
		try {
			//7번 포트에서 클라이언트의 접속요청을 기다리는 서버소켓 객체 생성
			theServer = new ServerSocket(7);
			//클라이언트의 접속요청을 기다리고 클라이언트의 소켓과 연결된 서버 측의 소켓을 생성
			theSocket = theServer.accept();
			//클라이언트가 전송한 데이터를 읽을 InputStream 객체 생성
			is = theSocket.getInputStream();
			//클라이언트가 전송한 데이터를 읽을 BufferedReader 객체 생성
			reader = new BufferedReader(new InputStreamReader(is));
			//클라이언트에 데이터를 전송할 OutputStream 객체 생성
			os = theSocket.getOutputStream();
			//클라이언트에 데이터를 전송하는 BufferedWriter 객체 생성
			writer = new BufferedWriter(new OutputStreamWriter(os));
			while((theLine = reader.readLine()) != null){ // 클라이언트의 데이터를 수신
				System.out.println(theLine); //받은 데이터를 화면에 출력
				writer.write(theLine+ '\r' + '\n'); // 클라이언트에 데이터를 재전송
				writer.flush();
			}
		}catch(UnknownHostException e) {
			System.err.println(e);
		}catch(IOException e) {
			System.err.println(e);
		}finally {
			if(theSocket != null) {
				try {
					theSocket.close();
				}catch(IOException e) {
					System.err.println(e);
				}
			}
		}
	}

}

package display;

import java.io.*;
import java.net.*;

public class RPSServer {
	public static void main(String[] args) {
		ServerSocket theServer;
		Socket theSocket = null;
		String c1, c2, n1, n2, re1;
		int cnt = 1;
		int w1 = 0, w2 = 0, d = 0, l1 = 0, l2 = 0;// 승 패 카운트
		try {
			//7번 포트에서 클라이언트의 접속요청을 기다리는 서버소켓 객체 생성
			theServer = new ServerSocket(7777);
			//클라이언트의 접속요청을 기다리고 클라이언트의 소켓과 연결된 서버 측의 소켓을 생성
			Socket socket1 = theServer.accept();
	        BufferedReader r1 = new BufferedReader(new InputStreamReader(socket1.getInputStream()));
	        BufferedWriter o1 = new BufferedWriter(new OutputStreamWriter(socket1.getOutputStream()));

	        Socket socket2 = theServer.accept();
	        BufferedReader r2 = new BufferedReader(new InputStreamReader(socket2.getInputStream()));
	        BufferedWriter o2 = new BufferedWriter(new OutputStreamWriter(socket2.getOutputStream()));
			n1 = r1.readLine();
			n2 = r2.readLine();
			while(cnt <= 10){ //10번 반복
				c1 = r1.readLine();
				c2 = r2.readLine();
				if (c1.equals(c2)) {
                    re1 = "비겼습니다!";
                    d++;
                } else if ((c1.equals("가위") && c2.equals("보")) ||
                           (c1.equals("바위") && c2.equals("가위")) ||
                           (c1.equals("보") && c2.equals("바위"))) {
                    re1 = n1 + " 승!";
                    w1++;
                    l2++;
                } else {
                    re1 = n2 + " 승!";
                    w2++;
                    l1++;
                }
				o1.write(cnt + ": " + re1);
				o1.newLine();
				o1.flush();
				o2.write(cnt + ": " + re1);
				o2.newLine();
				o2.flush();
				cnt++;
			}
			o1.write(n1 + w1 + " 승 " + d + "무 " + l1 + " 패");
			o1.newLine();
			o1.flush();
			o2.write(n2 + w2 + " 승 " + d + "무 " + l2 + " 패");
			o2.newLine();
			o2.flush();
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

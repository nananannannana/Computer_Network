package display;

import java.io.*;
import java.net.*;

public class DayTimeClient {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Socket theSocket;
		String host;
		InputStream is;
		BufferedReader reader;
		if(args.length>0) {
			host=args[0]; //원격호스트를 입력받음
		}else {
			host="localhost";//로컬호스트를 원격호스트로 사용
		}
		
		try {
			theSocket = new Socket(host,13);
			is = theSocket.getInputStream();
			reader = new BufferedReader(new InputStreamReader(is));
			String thetime = reader.readLine(); //날짜를 읽음
			System.out.println("호스트의 시간은" + thetime + "이다");
		}catch(UnknownHostException e) {
			System.out.println(args[0] + "호스트를 찾을 수 없습니다.");
		}catch(IOException e) {
			System.out.println(e);
		}
	}

}

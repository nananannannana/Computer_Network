package display;

import java.io.*;
import java.net.*;

public class RPSClient {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Socket theSocket = null;
		String host;
		InputStream is;
		BufferedReader reader, userInput;
		OutputStream os;
		BufferedWriter writer;
		String name;
		String r;
		int cnt = 0;
		if(args.length>0) {
			host=args[0]; //원격호스트를 입력받음
		}else {
			host="localhost";//로컬호스트를 원격호스트로 사용
		}
		try {
			theSocket = new Socket(host,7777); //RPS 서버에 접속
			is = theSocket.getInputStream();
			reader = new BufferedReader(new InputStreamReader(is));
			userInput = new BufferedReader(new InputStreamReader(System.in));
			os = theSocket.getOutputStream();
			writer = new BufferedWriter(new OutputStreamWriter(os));
			System.out.println("이름을 입력하여주십시오");
			name = userInput.readLine(); //데이터를 입력
			writer.write(name);
			writer.newLine();// BufferedReader.readLine()은 줄바꿈(\n)을 기준으로 입력을 읽음
			writer.flush();
			while(cnt < 10) {
				System.out.println("가위, 바위, 보 중 하나를 입력하여주십시오");
				r = userInput.readLine(); //데이터를 입력
				if(!r.equals("가위") && !r.equals("바위") && !r.equals("보")) {
					System.out.println("가위, 바위, 보 중 하나를 입력하여주십시오");
					continue;
				}
				writer.write(r);
				writer.newLine();
				writer.flush();//서버에 데이터 전송
				System.out.println(reader.readLine());
				cnt ++;
			}
			String fr = reader.readLine();
            System.out.println("최종 결과: " + fr);
		}catch(UnknownHostException e) {
			System.err.println(e);
		}catch(IOException e) {
			System.err.println(e);
		}finally {
			try {
				theSocket.close(); // 소켓을 닫는다
			}catch(IOException e) {
				System.err.println(e);
			}
		}
	}

}

package display;

import java.io.*;

public class WriteCharString {
	static DataOutputStream dos;
	static DataInputStream dis;
	static FileInputStream fin;
	public static void main(String[] args) {
		char ch;
		String sdata1, sdata2;
		try {
			String data;
			FileOutputStream fout = new FileOutputStream("chardata.txt");
			dos = new DataOutputStream(fout); // 파일과 연결함
			dos.writeChar(65); // 대문자 'A'를 전송함
			dos.writeUTF("반갑습니다."); //인수로 주어진 문자열을 UTF방식으로 인코딩해서 파일에 전송
			dos.writeUTF("자바 채팅 프로그래밍 교재");
			fin = new FileInputStream("chardata.txt");
			dis = new DataInputStream(fin);
			ch = dis.readChar(); // writeChar()의 대응메소드
			sdata1 = dis.readUTF();// writeUFF()의 대응메소드
			sdata2 = dis.readUTF();
			System.out.println(ch);
			System.out.println(sdata1);
			System.out.println(sdata2);
		}
		catch(EOFException e) {
			System.err.println(e);
		}
		catch(IOException e) {
			System.err.println(e);
		}
		finally {
			try {
				if(dos != null) dos.close();
				if(dos != null) dos.close();
			}
			catch(IOException e) {
				System.err.println(e);
			}
		}
	}

}

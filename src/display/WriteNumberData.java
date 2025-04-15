package display;

import java.io.*;

public class WriteNumberData {
	static FileOutputStream fout;
	static DataOutputStream dos;
	static FileInputStream fin;
	static DataInputStream dis;
	
	public static void main(String[] args) {
		boolean bdata;
		double ddata;
		int number;
		try {
			fout = new FileOutputStream("numberdata.txt");
			dos = new DataOutputStream(fout);// 파일과 연결된 필터 스트림 생성
			dos.writeBoolean(true); // 1 값을 파일에 저장
			dos.writeDouble(989.27);// 실수를 파일에 저장
			for(int i=1; i<=500; i++) {
				dos.writeInt(i); // 1 부터 500까지의 정수를 파일에 저장한다.
			}
			fin = new FileInputStream("numberdata.txt");
			dis = new DataInputStream(fin);
			bdata = dis.readBoolean(); // 부울 값을 읽음
			System.out.println(bdata); // 부울 값을 출력
			ddata = dis.readDouble(); // 실수 값을 읽음
			System.out.println(ddata); // 실수 값을 출력
			while(true) {
				number = dis.readInt();
				System.out.println(number+" "); //정수 값을 읽고 화면에 출력
			}
		}
		catch(EOFException e) {
			System.out.println("데이터를 모두 읽었습니다"); //정상 종료
		}
		catch(IOException e) {
			System.err.println(e);// 비정상 종료
		}
		finally {
			try {
				if(dos != null) dos.close();
			}
			catch(IOException e) {
				
			}
		}
	}

}

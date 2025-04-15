package display;

import java.io.*;
public class ReadFile {
	static FileOutputStream fout;
	
	public static void main(String[] args){ 
		try {
			int bytesRead;
			byte[] buffer = new byte[256];
			FileInputStream fin = null;
			fin = new FileInputStream("example1_9.txt");
			while((bytesRead = System.in.read(buffer))> 0) {
				fout.write(buffer, 0, bytesRead);
			}
		}
		catch(IOException e) {
			System.err.println("스트림으로부터 데이터를 읽을 수 없습니다.");
		}
		finally {
			try {
				if(fout != null) fout.close();
			}
			catch(IOException e) {
				System.err.println("스트림으로부터 데이터를 읽을 수 없습니다.");
			}
		}
	}

}

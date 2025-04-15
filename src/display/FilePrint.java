package display;

import java.io.*;

public class FilePrint {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String fname1 = "";
		String fname2 = "";
		String data1 = "";
		String data2 = "";
		int bytesRead1;
		int bytesRead2;
		FileInputStream fin;
		File f1;
		File f2;
		byte[] buffer1 = new byte[256]; //첫 번째 파일 내용
		byte[] buffer2 = new byte[256]; //두 번째 파일 내용
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("첫 번째 파일을 입력해주세요.");
            fname1 = br.readLine(); // 첫 번째 파일명 입력
            
            System.out.println("두 번째 파일을 입력해주세요.");
            fname2 = br.readLine(); // 두 번째 파일명 입력
			
			f1 = new File(fname1);
			fin = new FileInputStream(f1); // 파일 객체를 통해 파일 인풋스트림 객체 생성
			bytesRead1 = fin.read(buffer1);
			data1 = new String(buffer1, 0, bytesRead1);
			
			f2 = new File(fname2);
			fin = new FileInputStream(f2); // 파일 객체를 통해 파일 인풋스트림 객체 생성
			bytesRead2 = fin.read(buffer2);
			data2 = new String(buffer2, 0, bytesRead2);
			
			if(data1.equals(data2))
				 System.out.println("두 개의 파일의 내용은 같습니다.");
			else
				System.out.println("두 개의 파일의 내용은 다릅니다.");
			//(FP1.txt == FP2.txt) != FP3.txt 
			System.out.println(data1);
			System.out.println(data2);
		}catch(IOException e) {
			System.err.println(e);
		}
		
			
	}

}

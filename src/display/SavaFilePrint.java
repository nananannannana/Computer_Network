package display;

import java.io.*;
public class SavaFilePrint {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			String text, data;
			InputStreamReader isr;
			BufferedReader br;
			FileInputStream fin;
			FileOutputStream fos;
			OutputStreamWriter osw;
			BufferedWriter bw;
			isr = new InputStreamReader(System.in);
			br = new BufferedReader(isr);
			File f = new File("SFP.txt");
			fos = new FileOutputStream(f);
			osw = new OutputStreamWriter(fos,"UTF-8");
			bw = new BufferedWriter(osw);
			while((text = br.readLine()) != null) {
				bw.write(text + "\r\n");
				bw.flush();	
			}
			bw.close();  // 파일을 닫아 데이터를 저장
            br.close();

			
			fin = new FileInputStream(f);
			isr = new InputStreamReader(fin,"UTF-8");
			br = new BufferedReader(isr);
			osw = new OutputStreamWriter(System.out, "UTF-8");
			bw = new BufferedWriter(osw);
			while((data = br.readLine()) != null) {
				bw.write(data+"\r\n");
				bw.flush();
			}
			br.close();
	        bw.close();
		}catch(IOException e) {
			System.err.println(e);
		}
	}
}

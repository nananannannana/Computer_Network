package display;

import java.io.*;

public class RedefineClass extends FilterOutputStream{ 
	private FileOutputStream sStream;// 두번째 스트림 객체
	
	// 생성자에서 두 개의 OutputStream을 초기화
    public RedefineClass(FileOutputStream Stream1,FileOutputStream Stream2) {
    	super(Stream1); //부모 클래스 생성자 호출
        this.sStream = Stream2; // 두번째 스트림 저장
    }
    
    public void write(int a){
    	try {
    		super.write(a); // 첫 번째 스트림에 쓰기
            sStream.write(a); // 두 번째 스트림에 쓰기
    	}catch(IOException e) {
    		System.err.println(e);
    	}
    }
    
    public void write(byte[] b){ //오버로딩
    	try {
    		super.write(b); // 첫 번째 스트림에 쓰기
            sStream.write(b); // 두 번째 스트림에 쓰기
    	}catch(IOException e) {
    		System.err.println(e);
    	}
    }
    
    public void flush(){
    	try {
    		super.flush(); // 첫 번째 스트림 flush
            sStream.flush(); // 두 번째 스트림에 flush
    	}catch(IOException e) {
    		System.err.println(e);
    	}
    }
    
    public void close(){
    	try {
    		super.close(); // 첫 번째 스트림 flush
            sStream.close(); // 두 번째 스트림에 flush
    	}catch(IOException e) {
    		System.err.println(e);
    	}
    }
	
    public static void main(String[] args) {
		// TODO Auto-generated method stub
    	int bytesRead;
    	byte[] buffer = new byte[256];
    	String sfile = args[0]; // hello, world!
    	String tfile1 = args[1];
    	String tfile2 = args[2];
    	FileInputStream fin = null;
		try {
			fin = new FileInputStream(sfile);
			FileOutputStream fout1 = new FileOutputStream(tfile1);
			FileOutputStream fout2 = new FileOutputStream(tfile2);
			RedefineClass rfc = new RedefineClass(fout1, fout2);
			while((bytesRead = fin.read(buffer)) >= 0) {
				rfc.write(buffer, 0, bytesRead);
			}
			rfc.flush();// 남아 있는 데이터 저장		
		}catch(IOException e) {
			System.err.println(e);
		}
	}

}
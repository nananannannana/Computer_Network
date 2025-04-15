package display;

import java.io.*;

public class ReadCharactersIncr {
	static int size = 0;
	static int bufferSize = 80;
	static byte buffer[] = new byte[bufferSize];
	public static void main(String[] args) {
		try {
			int dataRead;
			while((dataRead = System.in.read(buffer, size, bufferSize-size)) != -1) { //배열, 저장 시작 인덱스, 읽을 최대 바이트 수
				size += dataRead; // size == 읽어들인 데이터 개수
				if(size == bufferSize)
					increaseBufferSize();
			}
			
			System.out.write(buffer,0,size);
		}
		catch(IOException e) {
			System.err.println("스트림으로 부터 데이터를 읽을 수 없습니다.");
		}
	}
	static void increaseBufferSize() {
		bufferSize += 80;
		byte[] newBuffer = new byte[bufferSize];
		arraycopy(buffer, 0, newBuffer, 0, size); // 직접정의, buffer의 데이터를 길이가 늘어난 newbuffer에 복사, (원본배열, 원본배열 복사시작 인덱스, 대상배열, 대상배열 복사시작 인덱스, 복사 요소 개수)
		buffer = newBuffer;// buffer 배열의 크기가 80개 늘어난다.
	}
	static void arraycopy(byte[] a ,int x, byte[] b, int y, int z) {
		for(int i = 0; i < z;i++ ) {
			b[y + i] = a[x + i];
		}
	}
}

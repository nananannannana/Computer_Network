package display;

public class Displayblock {

	public static void main(String[] args) throws java.io.IOException{
		// TODO Auto-generated method stub
		byte[] b = new byte[(127-31)*2];
		int index = 0;
		
		for(int i=32; i <127; i++) {
			b[index++] = (byte) i;
			
			if(i%8 == 7)
				b[index++] = (byte)'\n';
			else
				b[index++] = (byte)'t';
		}
		b[index++] = (byte)'\n';
	}

}

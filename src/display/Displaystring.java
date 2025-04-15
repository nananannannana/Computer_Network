package display;

public class Displaystring {

	public static void main(String[] args) throws java.io.IOException{
		// TODO Auto-generated method stub
		byte[] buffer;
		for(int i=0; i<args.length; i++) {
			buffer = args[i].getBytes();
			System.out.write(buffer);
			System.out.write('\n');
		}
	}

}

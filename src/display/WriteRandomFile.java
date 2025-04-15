package display;

import java.io.*;
import java.awt.*;
import java.awt.event.*;

public class WriteRandomFile extends Frame implements ActionListener {
	
	private TextField accF, nameF, balF;
	private Label cntL;// 고객 수 출력
	private Button en, in;
	private RandomAccessFile output;
	private Record data;
	int cnt = 0;
	public WriteRandomFile() {
		super("파일 쓰기");
		data = new Record();
		try {
			File f = new File("customer.txt");
			output = new RandomAccessFile(f, "rw");
		}catch(IOException e) {
			System.out.println(e.toString());
			System.exit(1);
		}
		setSize(300,150);
		setLayout(new GridLayout(5,2));
		add(new Label("고객 수"));
		cntL = new Label("0");
		add(cntL);
		add(new Label("구좌번호"));
		accF = new TextField();
		add(accF);
		add(new Label("이름"));
		nameF = new TextField();
		add(nameF);
		add(new Label("잔고"));
		balF = new TextField();
		add(balF);
		en = new Button("입력");
		en.addActionListener(this);
		add(en);
		in = new Button("출력");
		in.addActionListener(this);
		add(in);
		addWindowListener(new WinListener());
		setVisible(true);
	}
	public void addRecord() {
		int accN = 0;
		Double d;
		if(!accF.getText().equals("")) {
			try {
				accN = Integer.parseInt(accF.getText());
				if(accN <= 0)
					System.out.println("계좌번호는 1부터 입니다.");
				else {
					data.setAccount(accN);
					data.setName(nameF.getText());
					d = Double.parseDouble(balF.getText());
					data.setBalance(d);
					output.seek((long) (accN-1) * Record.size());//파일 포인터를 이동
					data.write(output);
					cnt++;
				}
				reset();
			}catch(NumberFormatException nfe) {
				System.out.println("숫자를 입력하세요");
			}catch(IOException io) {
				System.out.println("파일쓰기 에러\n" + io.toString());
				System.exit(1);
			}
		}
	}
	public void readRecord() {
		int read = Integer.parseInt(accF.getText());
		int acc;
		String n;
		double bal; 
		try {
			output.seek((long) (read-1) * Record.size());//파일 포인터를 이동
			data.read(output); //파일에서 데이터를 읽어옴
			acc = data.getAccount();
			n = data.getName();
			bal = data.getBalance();
			if(acc == 0) { //삭제된 레코드라면
				reset();//빈 텍스트필드 출력
			}
			else {
				accF.setText(String.valueOf(acc));
	            nameF.setText(n);
	            balF.setText(String.valueOf(bal));
			}
		}catch(IOException io) {
			System.out.println(io.toString());
		}
	}
	public void reset() {
		accF.setText("");
		nameF.setText("");
		balF.setText("");
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == en) {
			addRecord();
			cntL.setText(Integer.toString(cnt));;
		}
		if(e.getSource() == in) {
			readRecord();
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new WriteRandomFile();
	}
	class WinListener extends WindowAdapter {
        public void windowClosing(WindowEvent we) {
            System.exit(0);
        }
    }

}
class Record{
	private int acc;
	private String n;
	private double bal;
//RandomAccessFile로부터 한 레코드를 읽는다.
	public void read(RandomAccessFile file) {
		try {
			acc = file.readInt(); //파일로부터 구좌번호를 읽는다.
			char namearray[] = new char[15];
			for(int i = 0; i < namearray.length; i++)
				namearray[i] = file.readChar();
			n = new String(namearray);
			bal = file.readDouble();
		}catch(IOException io) {
			System.out.println(io.toString());
		}
	}
	public void write(RandomAccessFile file) {
		StringBuffer buf;
		try {
			file.writeInt(acc);
			if(n != null)
				buf = new StringBuffer(n);
			else
				buf = new StringBuffer(15);
			buf.setLength(15);//이름을 저장하는 메모리 크기를 15로 설정
			file.writeChars(buf.toString());
			file.writeDouble(bal);
		}catch(IOException io) {
			System.out.println(io.toString());
		}
	}
	public void setAccount(int a) {acc = a;}//구좌번호를 설정한다.
	public int getAccount() {return acc;}//구좌번호를 반환한다.
	public void setName(String f) {n = f;}
	public String getName() {return n;}
	public void setBalance(double d) {bal = d;}
	public double getBalance() {return bal;}
	public static int size() {return 42;}// 한 레코드의 길이
}

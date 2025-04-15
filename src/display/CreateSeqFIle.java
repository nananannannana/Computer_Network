package display;

import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*; //메시지 다이얼로그

public class CreateSeqFIle extends Frame implements ActionListener{
	private TextField acc , name, bal, cnt;
	private Button en, pr;
	private DataOutputStream output; // 필터스트림 객체
	private DataInputStream input;
	private int count = 0;
	public CreateSeqFIle() {
		super("고객 파일을 생성 후 출력");
		try {
			output = new DataOutputStream(new FileOutputStream("client.txt"));
			input = new DataInputStream(new FileInputStream("client.txt"));
		}catch(IOException e) {
			System.err.println(e.toString());
			System.exit(1);
		}
		setSize(250,130);
		setLayout(new GridLayout(5,2));
		add(new Label("고객 수"));
		cnt = new TextField(); //구좌번호 필드
		add(cnt);
		add(new Label("구좌번호"));
		acc = new TextField(); //구좌번호 필드
		add(acc);
		add(new Label("이름"));
		name = new TextField(20); // 이름 입력 필드
		add(name);
		add(new Label("잔고"));
		bal = new TextField(20); // 잔고 입력 필드
		add(bal);
		en = new Button("입력"); //입력된 데이터 저장 버튼
		en.addActionListener(this); //이벤트와 연결
		add(en);
		pr = new Button("출력"); //입력된 데이터 출력 버튼
		pr.addActionListener(this); //이벤트와 연결
		add(pr);
		addWindowListener(new WinListener());
		setVisible(true);
	}
	
	public void addRecord() {
		int accNo = 0;
		String n, dou;
		boolean allH = true, dt = true;
		 if (acc.getText().equals("") || name.getText().equals("") || bal.getText().equals("")) {// 하나라도 입력되지 않았으면 메시지 출력
		        JOptionPane.showMessageDialog(null, "모든 필드를 입력해주세요.", "경고", JOptionPane.WARNING_MESSAGE);
		    }
		 else// 모든 필드가 입력되었다면 실행
			try {
				accNo = Integer.parseInt(acc.getText());
				if(accNo < 0) {
					JOptionPane.showMessageDialog(null, "올바른 정수 값을 입력해주세요.", "경고", JOptionPane.WARNING_MESSAGE);
				}
				n = name.getText();				
				for (char ch : n.toCharArray()) { //name 필드에 입력된 n을 문자열 배열로 변경한 뒤 모든 문자에 대해 for문 실행
		            if (!(ch >= 0xAC00 && ch <= 0xD7A3)) { //name 필드에 입력된 값이 유니코드에 따라 한글이 아니라면
		            	JOptionPane.showMessageDialog(null, "올바른 이름을 입력해주세요.", "경고", JOptionPane.WARNING_MESSAGE);
		            	allH = false;
		            	break;
		            }
				}
				dou = bal.getText();
				if(Double.valueOf(dou) < 0) { // 잔고값이 0 이상인지 확인
					JOptionPane.showMessageDialog(null, "올바른 잔고값을 입력해주세요.", "경고", JOptionPane.WARNING_MESSAGE);
					dt = false;
				}
				if(accNo > 0 && allH == true && dt == true) {
					output.writeInt(accNo);
					output.writeUTF(n);
					output.writeDouble(Double.valueOf(dou));
					count++;
				}
				cnt.setText( Integer.toString(count));
				acc.setText("");
				name.setText("");
				bal.setText("");
		}
		catch(NumberFormatException nfe) {// 숫자가 아닌 것을 숫자로 바꾸려 할 때 예외 발생
			System.err.println("올바른 수를 입력해야 합니다.");
		}
		catch(IOException io) {
			System.err.println(io.toString());
			System.exit(1);
		}
	}
	public void readRecord() {
		int accNo;
		double d;
		String n;
		try {
			accNo = input.readInt(); //정수 값인 구좌번호 읽기
			n = input.readUTF(); // 문자열인 이름 읽기
			d = input.readDouble(); //실수 값인 잔고 읽기
			//읽어드린 값 필드에 출력
			acc.setText(String.valueOf(accNo));
			name.setText(n);
			bal.setText(String.valueOf(d));
		}catch(EOFException eof) {
			closeFile();
		}catch(IOException io) {
			System.err.println(io.toString());
			System.exit(1);
		}
	}
	private void closeFile() { // 스트림을 닫고 프로그램을 종료한다.
		try {
			input.close();
			output.close();
			System.exit(0);
		}catch(IOException io) {
			System.err.println(io.toString());
			System.exit(1);
		}
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == en) { //입력버튼을 눌렀다면
			addRecord();
		}
		else if(e.getSource() == pr) { //출력버튼을 눌렀다면
			readRecord();
		}
	}
	class WinListener extends WindowAdapter{
		public void windowClosing(WindowEvent we) {
			System.exit(0);
		}
	}
	public static void main(String[] args) {
		new CreateSeqFIle();
	}

}

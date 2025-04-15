package display;

import javax.swing.*;
import java.net.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;

public class ReadServerFile extends JFrame implements ActionListener{

	private JTextField en;
	private JTextArea con1, con2;
	
	public ReadServerFile() {
		super("호스트 파일 읽고 출력");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        setSize(350, 150);
        JPanel p = new JPanel(new BorderLayout());
        en = new JTextField("URL을 입력하세요!");
        en.addActionListener(this);
        p.add(en, BorderLayout.NORTH);
        con1 = new JTextArea();
        JScrollPane sp = new JScrollPane(con1); // 스크롤 추가
        p.add(sp, BorderLayout.CENTER);
        con2 = new JTextArea();
        sp = new JScrollPane(con2); // 스크롤 추가
        p.add(sp, BorderLayout.CENTER);
        add(p);
        setVisible(true);
	}
	public void actionPerformed(ActionEvent e) {
		URL url;
		InputStream is;
		BufferedReader input;
		String line;
		StringBuffer buf = new StringBuffer();
		String loc = e.getActionCommand();
		try {
			url = new URL(loc);
			is = url.openStream();//loc(호스트)와 연결시키는 InputStream 객체 생성
			input = new BufferedReader(new InputStreamReader(is));
			con1.setText("파일을 읽는 중입니다....");
			while((line = input.readLine())!= null)//파일(웹페이지)를 읽는다.
				buf.append(line).append('\n');
			con1.setText(buf.toString());//읽은 파일을 텍스트에어리어에 출력
			input.close();
		}catch(MalformedURLException mal) {
			con1.setText("URL 형식이 잘못되었습니다.");
		}catch(IOException io) {
			con1.setText(io.toString());
		}catch(Exception ex) {
			con1.setText("호스트 컴퓨터의 파일만을 열 수 있습니다.");
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ReadServerFile read = new ReadServerFile();
	}

}

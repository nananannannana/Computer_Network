package display;

import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class CopyandPrint extends Frame implements ActionListener {
	Label ef;
	Label pf;
	Label file;
	TextField ef1;
	TextField pf1;
	Button b;
	TextArea ta1;
	String efile, pfile; 
	
	public CopyandPrint() {
		setLayout(new FlowLayout());
		ef = new Label("입력파일");
		add(ef);
		ef1 = new TextField(20);
		add(ef1);//enterfile.txt
		pf = new Label("출력파일");
		add(pf);
		pf1 = new TextField(20);
		add(pf1);//printfile.txt
		b = new Button("확인");
		b.addActionListener(this);
		add(b);
		file = new Label("파일내용");
		add(file);
		ta1 = new TextArea(10, 25);
		add(ta1);
		addWindowListener(new WinListener());
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CopyandPrint p = new CopyandPrint();
		p.setSize(280,300);
		p.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent ae) {
		byte buffer1[] = new byte[100];
		byte buffer2[] = new byte[100];
		int bytesRead;
		pfile = pf1.getText();
		efile = ef1.getText();
		FileInputStream fin1 = null;
		FileOutputStream fout = null;
		try {
			fin1 = new FileInputStream(efile);
			fout = new FileOutputStream(pfile);
			while((bytesRead = fin1.read(buffer1))>= 0) {
				fout.write(buffer1, 0, bytesRead);
			}
			fin1 = new FileInputStream(pfile);
	        int bytesRead2 = fin1.read(buffer2);
	        String data = new String(buffer2, 0, bytesRead2);
	        ta1.setText(data + "\n");
		}
		catch(IOException e) {
			System.err.println("파일의 내용을 출력할 수 없습니다.");
		}
		finally {
			try {
				if(fin1 != null) fin1.close();
				if(fout != null) fout.close();
			}
			catch(IOException e) {
				
			}
		}
	}
	class WinListener extends WindowAdapter{
		public void windowClosing(WindowEvent we) {
			System.exit(0);
		}
	}

}

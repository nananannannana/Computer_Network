package display;

import java.awt.*;
import java.awt.event.*;
import java.io.*;


public class ReadFromFileEvent extends Frame implements ActionListener{

	Label Ifile;
	TextField tfile;
	TextArea tadata;
	String filename;
	public ReadFromFileEvent(String str) {
		super(str);
		setLayout(new FlowLayout());
		Ifile = new Label("파일 이름을 입력하세요");
		Ifile.setFont(new Font("Dialog", Font.PLAIN, 12));
		add(Ifile);
		tfile = new TextField(20);
		tfile.addActionListener(this);
		add(tfile);
		tadata = new TextArea(3,35);
		add(tadata);
		addWindowListener(new WinListner());
	}
	public static void main(String[] args) {
		ReadFromFileEvent text = new ReadFromFileEvent("파일읽기");
		text.setSize(270,160);
		text.setVisible(true);

	}
	public void actionPerformed(ActionEvent ae) {
		byte buffer[] = new byte[100];
		filename = tfile.getText();
		try {
			FileInputStream fin = new FileInputStream(filename);
			fin.read(buffer);
			String data = new String(buffer);
			tadata.setText(data+"\n");
		}
		catch(IOException e) {
			System.out.println(e.toString());
		}
	}
	class WinListner extends WindowAdapter{
		public void windowClosing(WindowEvent we) {
			System.exit(0);
		}
	}

}

package display;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;

public class GetHostInfor extends JFrame {
	JButton lb;//로컬호스트 정보 얻기 버튼
	JButton rb;//원격호스트 정보 얻기 버튼
	JTextField hn; //호스트 이름을 입력 받는 필드
	JTextArea ldp; //구해진 IP에 관한 로컬호스트 정보를 출력하는 필드
	JTextArea rdp; //구해진 IP에 관한 원격 호스트 정보를 출력하는 필드
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GetHostInfor h = new GetHostInfor("InetAddress 클래스");
		h.setVisible(true);
	}
	public GetHostInfor(String str) {
		super(str);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container p = getContentPane();
		p.setLayout(new BorderLayout());
		JPanel tp = new JPanel(new BorderLayout());
		lb = new JButton("로컬 호스트 정보 얻기");
		lb.addActionListener(new MyActionListener());
		tp.add(lb, BorderLayout.NORTH);
		ldp = new JTextArea(10, 40);
		tp.add(new JScrollPane(ldp), BorderLayout.CENTER);
		p.add(tp, BorderLayout.NORTH);
		JPanel cp = new JPanel(new BorderLayout());
		cp.add(new JLabel("호스트 이름: "), BorderLayout.NORTH);
		hn = new JTextField("", 30);
		cp.add(hn, BorderLayout.CENTER);
		p.add(cp, BorderLayout.CENTER);
		JPanel sp = new JPanel(new BorderLayout());
		rb = new JButton("원격 호스트 정보 얻기");
		rb.addActionListener(new MyActionListener());
		sp.add(rb, BorderLayout.NORTH);
		rdp = new JTextArea(10, 40);
		sp.add(new JScrollPane(rdp), BorderLayout.CENTER);
		p.add(sp, BorderLayout.SOUTH);
		setSize(330, 450);
	}
	private class MyActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JButton b = (JButton)e.getSource();
			String hostname = hn.getText();
			if(b == lb) {
				try {
					InetAddress myself = InetAddress.getLocalHost();
					ldp.setText("로컬 호스트 이름 : " + myself.getHostName() + "\n");
					ldp.append("로컬 호스트 주소 : " + myself.getHostAddress() + "\n");
					ldp.append("로컬 호스트 class : " + ipClass(myself.getAddress()) + "\n");
					ldp.append("로컬 Canonical Host Name: " + myself.getCanonicalHostName() +"\n");
					ldp.append("로컬 호스트 해시코드 : " + myself.hashCode() + "\n");
					ldp.append("로컬 호스트 루프백 주소: " + myself.getHostAddress());
				}catch(UnknownHostException ex) {
					System.err.println(ex);
				}
			}
			else {
				try {
					InetAddress machine = InetAddress.getByName(hostname);
					rdp.setText("원격 호스트 이름 : " + machine.getHostName() + "\n");
					rdp.append("원격 호스트 주소 : " + machine.getHostAddress() + "\n");
					rdp.append("원격 호스트 class : " + ipClass(machine.getAddress()) + "\n");
					rdp.append("원격 Canonical Host Name: " + machine.getCanonicalHostName() +"\n");
					rdp.append("로컬 호스트 해시코드 : " + machine.hashCode() + "\n");
				}catch(UnknownHostException ex) {
					System.err.println(ex);
				}
			}
		}
	}
	static char ipClass(byte[] ip) {
		int highByte = 0xff & ip[0];
		return(highByte < 128) ? 'A' : (highByte < 192) ? 'B' : (highByte < 224) ? 'C' : (highByte < 240) ? 'D' : 'E';
	}
}

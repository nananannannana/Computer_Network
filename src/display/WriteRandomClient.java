package display;

import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class WriteRandomClient extends JFrame implements ActionListener{
	private JTextField accF, nameF, balF;
    private JTextArea result;
    private JButton inputB, queryB, deleteB, clearB;
    private Socket socket;
    private BufferedWriter out;
    private BufferedReader in;

    public WriteRandomClient() {
        super("계좌 클라이언트");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container p = getContentPane();
        p.setLayout(new GridLayout(5, 2, 5, 5)); // 행 5, 열 2

        try {
            socket = new Socket("localhost", 7777);
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            System.err.println(e);
        }

        
        p.add(new JLabel("계좌번호"));
        accF = new JTextField();
        p.add(accF);

        p.add(new JLabel("이름"));
        nameF = new JTextField();
        p.add(nameF);

       p.add(new JLabel("잔고"));
        balF = new JTextField();
       p.add(balF);

        inputB = new JButton("입력");
        queryB = new JButton("조회");
        deleteB = new JButton("삭제");
        clearB = new JButton("Clear");

        inputB.addActionListener(this);
        queryB.addActionListener(this);
        deleteB.addActionListener(this);
        clearB.addActionListener(this);

        p.add(inputB);
        p.add(queryB);
        p.add(deleteB);
        p.add(clearB);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                try { socket.close(); } catch (IOException e) {}
                System.exit(0);
            }
        });

        setSize(400, 300);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String acc = accF.getText();
        if (acc.isEmpty()) return;

        try {
            if (e.getSource() == inputB) {
                String cmd = "입력:" + acc + ":" + nameF.getText() + ":" + balF.getText();
                out.write(cmd + "\n"); out.flush();
            } else if (e.getSource() == queryB) {
                out.write("조회:" + acc + "\n"); out.flush();
            } else if (e.getSource() == deleteB) {
                out.write("삭제:" + acc + "\n"); out.flush();
            } else if (e.getSource() == clearB) {
                accF.setText(""); nameF.setText(""); balF.setText("");
                return;
            }
            result.setText(in.readLine());
        } catch (IOException ex) {
            result.setText("서버 연결 오류");
        }
    }

    public static void main(String[] args) {
        new WriteRandomClient();
    }
}

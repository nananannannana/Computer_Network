package display;

import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class FileTestSouth2 extends Frame implements ActionListener {
    private TextField enter;
    private TextArea output1;
    private TextArea output2;

    public FileTestSouth2() {
        super("File 클래스 테스트");
        enter = new TextField("파일 및 디렉토리명을 입력하세요");
        enter.addActionListener(this);
        output1 = new TextArea(); //파일에 관한 정보 출력
        output2 = new TextArea(); // 디렉토리 및 파일 정보 출력
        
        add(enter, BorderLayout.NORTH);
        add(output1, BorderLayout.CENTER);
        add(output2, BorderLayout.SOUTH);
        
        addWindowListener(new WinListener());
        
        setSize(400, 400);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        File name = new File(e.getActionCommand()); // 텍스트 필드의 파일이름을 읽어서 File 객체 생성
        if (name.exists()) { // 파일이 존재하는지 확인
            output1.setText(name.getName() + "이 존재한다.\n" +
                (name.isFile() ? "파일이다.\n" : "파일이 아니다.\n") +
                (name.isDirectory() ? "디렉토리이다.\n" : "디렉토리가 아니다.\n") +
                (name.isAbsolute() ? "절대경로이다.\n" : "절대경로가 아니다.\n") +
                "마지막 수정날짜는 : " + name.lastModified() +
                "\n파일의 길이는 : " + name.length() +
                "\n파일의 경로는 : " + name.getPath() +
                "\n절대경로는 : " + name.getAbsolutePath() +
                "\n상위 디렉토리는 : " + name.getParent());
        }

        if (name.isFile()) { // 호출한 객체가 파일인지 확인
            try {
                RandomAccessFile r = new RandomAccessFile(name, "r");
                StringBuffer buf = new StringBuffer();
                String text;
                
                output2.append("\n\n");
                while ((text = r.readLine()) != null)
                    buf.append(text).append("\n");
                
                output2.append(buf.toString() + "\n");
            } catch (IOException e2) {
                System.err.println(e2);
            }
        } 
        else if (name.isDirectory()) { // 호출한 객체가 디렉토리인지 확인
            String directory[] = name.list();
            output2.append("\n\n디렉토리의 내용은 :\n");
            
            if (directory != null) {
                for (String file : directory)
                    output2.append(file + "\n");
            }
        } 
        else {
            output2.setText(e.getActionCommand() + "은 존재하지 않는다.\n");
        }
    }

    public static void main(String[] args) {
    	FileTestSouth2 f = new FileTestSouth2();
    }

    class WinListener extends WindowAdapter {
        public void windowClosing(WindowEvent we) {
            System.exit(0);
        }
    }
}

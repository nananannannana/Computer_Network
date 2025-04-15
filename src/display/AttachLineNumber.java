package display;

import java.io.*;

public class AttachLineNumber {

    public static void main(String[] args) {
        try {
            // 키보드 입력을 위한 BufferedReader
            InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(isr);

            // 파일명 입력 받기
            System.out.println("파일명을 입력해주세요.");
            String fname1 = br.readLine();
            String fname2 = "numbered_" + fname1;//새로운 파일명

            // 출력 파일 생성
            File f = new File(fname2);
            f.createNewFile();

            // 입력 파일 읽기
            FileInputStream fin = new FileInputStream(fname1);
            InputStreamReader isr1 = new InputStreamReader(fin, "KSC5601");
            BufferedReader br1 = new BufferedReader(isr1);
            
            // 출력 파일 쓰기
            FileOutputStream fos = new FileOutputStream(fname2);
            OutputStreamWriter osw = new OutputStreamWriter(fos, "KSC5601");
            BufferedWriter bw = new BufferedWriter(osw);

            // 줄 번호 추가하면서 파일 쓰기
            LineNumberReader lnr = new LineNumberReader(br1);
            String str;
            while ((str = lnr.readLine()) != null) {
                bw.write(lnr.getLineNumber() + ": " + str + "\n");
            }

            // 파일 쓰기 완료 후 flush & close
            bw.flush();
            bw.close();
            lnr.close();

            // 새로운 파일을 다시 읽어 출력
            FileInputStream fin1 = new FileInputStream(fname2);
            BufferedReader br2 = new BufferedReader(new InputStreamReader(fin1));
            PrintStream write = new PrintStream(System.out);//printwriter는 autoflush 안됨
            String buf;
            while ((buf = br2.readLine()) != null) {
                write.println(buf);
            }

            // 리소스 정리
            br2.close();
            fin1.close();

        } catch (IOException e) {
            System.err.println("오류 발생: " + e.getMessage());
        }
    }
}


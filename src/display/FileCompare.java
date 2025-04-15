package display;

import java.io.*;

public class FileCompare {

    public static void main(String[] args) {
        String fname1, fname2, data1, data2;
        File f1, f2;
        boolean same = true; // 두 파일의 내용이 같은지 여부

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("첫 번째 파일을 입력해주세요.");
            fname1 = br.readLine(); // 첫 번째 파일명 입력
            
            System.out.println("두 번째 파일을 입력해주세요.");
            fname2 = br.readLine(); // 두 번째 파일명 입력

            f1 = new File(fname1);
            f2 = new File(fname2);

            // 첫 번째 파일 읽기
            FileInputStream fin = new FileInputStream(fname1);
            InputStreamReader isr = new InputStreamReader(fin, "KSC5601");
            BufferedReader br1 = new BufferedReader(isr);

            // 두 번째 파일 읽기
            FileInputStream fin1 = new FileInputStream(fname2);
            InputStreamReader isr1 = new InputStreamReader(fin1, "KSC5601");
            BufferedReader br2 = new BufferedReader(isr1);

            // 파일 비교, (FP1.txt == FP2.txt)!= FP3.txt
            while (true) {// 첫 번째 줄부터 비교
                data1 = br1.readLine();
                data2 = br2.readLine();

                if (data1 == null && data2 == null) {
                    break; // 두 파일이 동시에 끝나면 break
                }
                if (data1 == null || data2 == null || !data1.equals(data2)) {//줄 수가 다르거나 내용이 다르면 false로 바꾼 후 break
                    same = false;
                    break;
                }
            }

            // 결과 출력
            if (same) {//same이 true인 경우(두 파일이 같은 경우)
                System.out.println("두 파일의 내용은 같습니다.\n");
                System.out.println("첫 번째 파일의 최종 수정 시간: " + f1.lastModified() + "\n");
                System.out.println("두 번째 파일의 최종 수정 시간: " + f2.lastModified() + "\n");
            } 
            else { //(두 파일이 다른 경우)
                System.out.println("두 파일의 내용이 다릅니다.");

                // 다른 내용을 NEW.txt에 저장
                String str = "NEW.txt";
                File f3 = new File(str);
                f3.createNewFile();
                FileOutputStream fos = new FileOutputStream(str, true);//`true`를 추가해서 기존 내용 뒤에 새로운 내용을 덧붙임
                OutputStreamWriter osw = new OutputStreamWriter(fos, "KSC5601");
                BufferedWriter bw = new BufferedWriter(osw);

                while ((data1 = br1.readLine()) != null) {
                    bw.write(data1 + "\r\n");
                }
                while ((data2 = br2.readLine()) != null) {
                    bw.write(data2 + "\r\n");
                }

                // 버퍼 정리 및 닫기
                bw.flush();
                bw.close();
            }

            // 스트림 닫기
            br1.close();
            br2.close();
            fin.close();
            fin1.close();
        } catch (IOException e) {
            System.err.println("오류 발생: " + e.getMessage());
        }
    }
}


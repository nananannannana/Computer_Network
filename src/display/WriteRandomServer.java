package display;

import java.io.*;
import java.net.*;

public class WriteRandomServer {
	 private static final int PORT = 7777;
	    private static final int MAX_ACCOUNTS = 100;
	    private static final int RECORD_SIZE = 42;

	    public static void main(String[] args) throws IOException {
	        ServerSocket serverSocket = new ServerSocket(PORT);
	        System.out.println("서버 실행 중...");

	        while (true) {
	            Socket clientSocket = serverSocket.accept();
	            new Thread(new ClientHandler(clientSocket)).start();
	        }
	    }

	    static class ClientHandler implements Runnable {
	        private Socket socket;
	        private RandomAccessFile file;

	        public ClientHandler(Socket socket) {
	            this.socket = socket;
	            try {
	                file = new RandomAccessFile("customer.txt", "rw");
	            } catch (IOException e) {
	                System.out.println("파일 열기 오류: " + e);
	            }
	        }

	        public void run() {
	            try (
	                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	                BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))
	            ) {
	                String command;
	                while ((command = in.readLine()) != null) {
	                    String[] parts = command.split(":");
	                    String action = parts[0];

	                    if (action.equals("ADD")) {
	                        int acc = Integer.parseInt(parts[1]);
	                        String name = parts[2];
	                        double bal = Double.parseDouble(parts[3]);
	                        writeRecord(acc, name, bal);
	                        out.write("저장 완료\n");
	                    } else if (action.equals("READ")) {
	                        int acc = Integer.parseInt(parts[1]);
	                        String result = readRecord(acc);
	                        out.write(result + "\n");
	                    } else if (action.equals("DELETE")) {
	                        int acc = Integer.parseInt(parts[1]);
	                        writeRecord(acc, "", 0.0);
	                        out.write("삭제 완료\n");
	                    } else if (action.equals("CLEAR")) {
	                        out.write("CLEARED\n");
	                    }
	                    out.flush();
	                }
	            } catch (IOException e) {
	                System.out.println("클라이언트 연결 종료: " + e);
	            } finally {
	                try {
	                    socket.close();
	                    file.close();
	                } catch (IOException e) {}
	            }
	        }

	        private void writeRecord(int acc, String name, double bal) throws IOException {
	            if (acc < 1 || acc > MAX_ACCOUNTS) return;
	            file.seek((acc - 1L) * RECORD_SIZE);
	            file.writeInt(acc);
	            StringBuffer buf = new StringBuffer(name);
	            buf.setLength(15);
	            file.writeChars(buf.toString());
	            file.writeDouble(bal);
	        }

	        private String readRecord(int acc) throws IOException {
	            if (acc < 1 || acc > MAX_ACCOUNTS) return "잘못된 계좌번호";
	            file.seek((acc - 1L) * RECORD_SIZE);
	            int number = file.readInt();
	            char[] nameChars = new char[15];
	            for (int i = 0; i < nameChars.length; i++)
	                nameChars[i] = file.readChar();
	            String name = new String(nameChars).trim();
	            double bal = file.readDouble();
	            if (number == 0 || name.isBlank()) return "해당 계좌 없음";
	            return "계좌번호: " + number + ", 이름: " + name + ", 잔고: " + bal;
	        }
	    }
}

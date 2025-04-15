package display;

public class Displaychar {

	public static void main(String[] args) throws java.io.IOException{
		// IOException == 입출력 작업 중 발생할 수 있는 오류
		for(int i=32; i < 127; i++) {
			System.out.write(i);// 32부터 127까지의 정수값을 프린터로 출력
			
			if(i%8 == 7)
				System.out.write('\n'); // 8개의 문자를 출력하고 줄을 이동
			
			else
				System.out.write('\t'); //하나의 문자를 출력하고 탭을 출력
		}
		System.out.write('\n');
	}

}

//try문을 사용한 예외처리
/*package display; 

public class Displaychar {

    public static void main(String[] args) {
        // 32부터 127까지의 정수값을 프린터로 출력
        for (int i = 32; i < 127; i++) {
            try {
                System.out.write(i);

                if (i % 8 == 7) {
                    System.out.write('\n'); // 8개의 문자를 출력하고 줄을 이동
                } else {
                    System.out.write('\t'); // 하나의 문자를 출력하고 탭을 출력
                }
            } catch (java.io.IOException e) {
                System.out.println("입출력 오류 발생: ");
                System.out.write(i);
            }
        }
		
		finally {
            System.out.write('\n');
        }
    }
}
*/

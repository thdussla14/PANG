package pangpang.controller.member.암호화.test;

import java.util.Arrays;

public class Pre_Processing {

	// 바이트 -> 바이너리
	private static void getBits(StringBuilder sb, byte b) {
        for (int i = 0; i < 8; i++) {
            sb.append((b & 128) == 0 ? 0 : 1);
            b <<= 1;
        }
        //sb.append(' ');
    }
	
	// 평문 -> 바이너리 (스트링 -> 바이트) 한글자씩 잘라서 바이트로 바꾸고 getBits에서 바이너리로 만듬
    public static String toBinary(String s) {
        byte[] bytes = s.getBytes();
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            System.out.println(b);
        	getBits(sb, b);
        }
        return sb.toString().trim();
    }
    
    // 빈자리 패딩
    public static String padding(String binary,int plengrh) {
    	StringBuilder sb = new StringBuilder();
    	sb.append(binary); 
    	//sb.append(' ');
    	for(int i = 1 ; i<=(512-plengrh-64);i++) {
    		sb.append(0);
    		if(i%8==0) {
    			//sb.append(' '); 
    		}
    	}
    	return sb.toString().trim();
	}
    
    // 문자의 길이 이진수로 만들기(10진수 -> 2진수)
    public static String toBinary(int n)
    {
        if (n == 0) {
           return "";
        }
        return toBinary(n / 2) + (n % 2);
    }
 
    public static String[] substring(String pre_processing) {
    	// 32비트로 자르기
        // 배열의 크기를 구합니다.
        int strArraySize = (int) Math.ceil((double)pre_processing.length() / 32);

        // 배열을 선언합니다. 32비트로 잘린 pre_processing 들어가는 자리
        String[] subStringArray = new String[strArraySize];

        // 문자열을 순회하여 특정 길이만큼 분할된 문자열을 배열에 할당합니다.
        int index = 0;
        for(int startIndex = 0; startIndex < pre_processing.length(); startIndex += 32) {
        	subStringArray[index++] =
    		  	pre_processing.substring(startIndex, Math.min(startIndex + 32, pre_processing.length()));
      			
        }
        return subStringArray;
	}
    
    
    
    
    public static void main(String[] args) {
    	// 평문 문자
        String ptext = "abc";
        
        // 평문 문자를 바이너리로 변환 했을때 길이
        int plengrh = ptext.length()*8;
        
        // 평문 문자를 바이너리로 바꿈
        System.out.println(toBinary(ptext));
        String binary = toBinary(ptext);
        
        // 남은 공간을 0으로 채움
        System.out.println(padding(binary,plengrh));
        String padding = padding(binary,plengrh);
 
        // 문자의 길이를 이진수로 바꿈 (총 길이는 64bit)
        String blengrh = String.format("%0" + 64 + "d", Integer.valueOf(toBinary(plengrh)));
        System.out.println("blengrh"+blengrh);
        
        // 최종 pre_processing 값
        String pre_processing = padding+blengrh;
        System.out.println(pre_processing);
        
        //32bit로 자르기
        String[] subStringArray = substring(pre_processing);
        System.out.println(Arrays.toString(subStringArray));
        
        
        for(int i = 0 ; i<subStringArray.length ; i++) {
        	 int binaryToDecimal = Integer.parseInt(subStringArray[i], 2);
        	 System.out.println(binaryToDecimal);
        	 ///String binaryString = Integer.toBinaryString(binaryToDecimal);
        	// System.out.println(binaryString);
        }
        
        
    }
}

package pangpang.controller.member.암호화.test;

import java.security.SecureRandom;
import java.util.Arrays;

public class made2 {
	
	// 초기값 H 7개 랜덤값
	private static final int[] hash = {
			0x7a6e4943, 
			0x49f5d96a, 
			0xa9e54be4, 
			0xe9ee22de, 
			0xe11bcaee, 
			0x92b1f31f, 
			0x86071c3e, 
			0x2523b2c6
	};
	
	// 초기값 K 63개 랜덤값
	private static final int[] k = {
			0xcc8f300d, 0xc3cab38c, 0xef75efc3, 0x72d6e20f, 0x7e634ede, 0x42a2c2ba, 
			0x303ac401, 0x9d2d9d90, 0x1f5a2fb4, 0x7551ea72, 0x23fc1625, 0xba4e1973, 
			0xb0365253, 0xf7da41b4, 0xbbf63c01, 0xd5c82f9d, 0x869264db, 0xa2ec9b52, 
			0xdd330ff5, 0x1dd71423, 0x6cff24f2, 0x720dfd0b, 0x6053fbe3, 0x1e9a19a1, 
			0x89c546c6, 0x468501f3, 0x830c5780, 0xfd68a533, 0x29cb2916, 0x677798f3, 
			0x687376a3, 0xd5137f39, 0x2d5aa859, 0x8c136ed1, 0x0c063f9c, 0xe0fd677f, 
			0x3a80adb5, 0xcd582b9b, 0xe9378360, 0xcd6aa0e6, 0x264027cf, 0x0770e717, 
			0xf24b9392, 0x9dada3eb, 0xe68cc640, 0x02926207, 0x80b8c24d, 0xe9a22665, 
			0x237a7ea4, 0xd144d6aa, 0x37e86165, 0x2ec35cf1, 0x77d941c3, 0xdf542c31, 
			0x46cc22f0, 0x41d8daf7, 0x4b41f5d8, 0xd9f89b8a, 0xa3642b7b, 0x27378089, 
			0x724cd749, 0x0d03ca03, 0xdde63401, 0x1d26f9e9
	};
	
	
	public static void main(String[] args) {
		//평문
		String pwd = "bnm123";
		System.out.println("pwd : "+pwd);
		
		// 솔트
		String salt = "74c7fac5e1dfaae206d4e1b750b772967a58d822";
				//getRandom(20);
		System.out.println("salt : "+salt);
		
		// pre_processing
		int[] pre_processing = pre_processing(pwd,salt);
		System.out.println(Arrays.toString(pre_processing));
		
		int[] w = madeW(pre_processing);
		System.out.println(Arrays.toString(w));
		
		String result = Compression(w);
		System.out.println(result);
		//e80963d4ddca64f2e4c2c79b703266ecda10762293082ea59e01a70f3ff6f983
	}
	
	public static String Compression(int[] w) {
		int a = hash [0];
		int b = hash [1];
		int c = hash [2];
		int d = hash [3];
		int e = hash [4];
		int f = hash [5];
		int g = hash [6];
		int h = hash [7];
		
		for(int i = 0; i<=63 ; i++) {
			int sl = rightRotate(e, 6)^rightRotate(e, 11)^rightRotate(e, 25);
			int ch = (e&f)^((~e)&g);
			int temp1 = h+sl+ch+k[i]+w[i];
			int s0 = rightRotate(a, 2)^rightRotate(a, 13)^rightRotate(a, 22);
			int maj = (a&b)^(a&c)^(b&c);
			int temp2 = s0+maj;
			
			h=g;
			g=f;
			f=e;
			e=d+temp1;
			d=c;
			c=b;
			b=a;
			a=temp1+temp2;	
		}
		
		hash[0] += a;
		hash[1] += b;
		hash[2] += c;
		hash[3] += d;
		hash[4] += e;
		hash[5] += f;
		hash[6] += g;
		hash[7] += h;
		
		//System.out.println(Arrays.toString(hash));
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0 ; i<=7 ; i++) {
			sb.append(Integer.toHexString(hash[i]));
			//System.out.println(Integer.toHexString(hash[i]));
		}
		
		return sb.toString().trim();
	}
	
	
	// W값 만들기
	public static int[] madeW(int[] arr) {
		int[] w = new int[64];
		
		for(int i=0;i<=15;i++) {
			w[i]=arr[i];
		}
		for(int i=16 ; i<=63 ; i++) {
			int s0 = (rightRotate(w[i-15], 7)) ^ (rightRotate(w[i-15], 18)) ^ (w[i-15]>>3);
			int s1 = (rightRotate(w[i-2], 17)) ^ (rightRotate(w[i-2], 19)) ^ (w[i-2]>>10);
			w[i]= w[i-16] + s0 + w[i-7] + s1;
		}
		
		return w;
	}
	
	//비트의 길이
	static final int INT_BITS = 32;
	
	//왼쪽 회전
	static int leftRotate(int n, int d) {
	    return (n << d) | (n >> (INT_BITS - d));
	}
	
	//오른쪽 회전
	static int rightRotate(int n, int d) {
	    return (n >> d) | (n << (INT_BITS - d));
	}
	
	// pre_processing
	public static int[] pre_processing(String plaintext, String salt) {
		// 평문 + 솔트 
        String ptext = plaintext+salt;
        
        // 평문 문자를 바이너리로 변환 했을때 길이
        int plengrh = ptext.length()*8;
        
        // 평문 문자를 바이너리로 바꿈       
        String binary = toBinary(ptext);
        	//System.out.println(toBinary(ptext));
        
        // 남은 공간을 0으로 채움
        String padding = padding(binary,plengrh);
        	//System.out.println(padding(binary,plengrh));
 
        // 문자의 길이를 이진수로 바꿈 (총 길이는 64bit)
        String blengrh = String.format("%0" + 64 + "d", Integer.valueOf(toBinary(plengrh)));
        	//System.out.println("blengrh"+blengrh);
        
        // 최종 pre_processing 값
        String pre_processing = padding+blengrh;
        	//System.out.println(pre_processing);
        
        // 32bit로 자르기
        String[] subStringArray = substring(pre_processing);
        	//System.out.println(Arrays.toString(subStringArray));
        
        // int로 바꾸기
        int[] subIntArray = new int[subStringArray.length];
        for(int i = 0 ; i<subStringArray.length ; i++) {
        	 int binaryToDecimal = Integer.parseInt(subStringArray[i], 2);
        	 subIntArray[i]=binaryToDecimal;
        }
		return subIntArray;
	}

	// 평문 -> 바이너리 (스트링 -> 바이트) 한글자씩 잘라서 바이트로 바꾸고 getBits에서 바이너리로 만듬
    public static String toBinary(String s) {
        byte[] bytes = s.getBytes();
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            //System.out.println(b);
        	getBits(sb, b);
        }
        return sb.toString().trim();
    }
	
	// 바이트 -> 바이너리
	private static void getBits(StringBuilder sb, byte b) {
        for (int i = 0; i < 8; i++) {
            sb.append((b & 128) == 0 ? 0 : 1);
            b <<= 1;
        }
        //sb.append(' ');
    }
    
    // 빈자리 0으로 채우기
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
	
    //salt 만들기
    // 랜덤바이트 만들기
 	public static String getRandom(int n) {
 		
 		// 1. SecureRandom,byte 객체 생성
 		SecureRandom random = new SecureRandom();
 		byte[] randombyte = new byte[n];
 		
 		// 2. 난수 생성
 		random.nextBytes(randombyte);
 		
 		// 3. byte -> String
 		return byte_to_String(randombyte);
 		
 	}
 	
 	// byte -> String
 	public static String byte_to_String(byte[] temp) {
 		StringBuilder sb = new StringBuilder();
 		for(byte b : temp) {
 			sb.append(String.format("%02x", b));
 		}
 		return sb.toString();
 	}
    
    // 초기값 만들기
	// H 만드는 방법
	public static void hexrandomH() {
		String[] haxarrayH = new String[8];
		
		for( int i = 0 ; i<=7 ; i++) {
			String Hexadecimal = getRandom(4);
			haxarrayH[i]= Hexadecimal;
		}
		
		System.out.println(Arrays.toString(haxarrayH));
	}
	
	// K 만드는 방법
	public static void hexrandomK() {
		String[] haxarrayK = new String[64];
		
		for( int i = 0 ; i<=63 ; i++) {
			String Hexadecimal = getRandom(4);
			haxarrayK[i]= Hexadecimal;
		}
		
		System.out.println(Arrays.toString(haxarrayK));
	}
	
}

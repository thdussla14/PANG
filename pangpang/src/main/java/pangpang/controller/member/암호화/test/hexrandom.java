package pangpang.controller.member.암호화.test;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Random;

public class hexrandom {
	public static void main(String[] args) {
		//hexrandom hex = new hexrandom();
		//long h0 = Long.parseLong(hex.getRandomHexString(8),16);
		//System.out.printf( "%x ", h0);
		
		// System.out.println("random_hex : "+ hex.getRandomHexString(8));//0x빼고 8자리 
		
		 //byte[] arr = new byte[8];
	     //new Random().nextBytes(arr);

	     //System.out.println("Bytes to Hex: " + convertBytesToHex(arr));
	//5be6f2d0 6a09e667	
		 
			     
		// 선생님이 만들어주신 것
		long[] haxarray = new long[8];
		String[] haxarray2 = new String[8];
		String[] binary = new String[8];
		      
		for( int i = 0 ; i<=7 ; i++ ) {
			       
			String Hexadecimal = getSalt();
			haxarray2[i]=Hexadecimal;
			
			long haxarray1 = Long.valueOf(Hexadecimal,16);
			haxarray[i]=haxarray1;
			
			String binarystring = Long.toBinaryString(haxarray1);
			binary[i]=binarystring;
		}
		 
		
		
		  
		System.out.println( Arrays.toString(haxarray));
		System.out.println( Arrays.toString(haxarray2));
		System.out.println( Arrays.toString(binary));
		//System.out.printf( "%x \n" , haxarray[0]  );
		//System.out.println( Integer.toHexString(haxarray[0]));
		System.out.println(Integer.valueOf(haxarray2[0],16));
		
	     
	     
	}

    private static String convertBytesToHex(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte temp : bytes) {
            result.append(String.format("%02x", temp));
        }
        return result.toString();
    }
	
	
	
	private String getRandomHexString(int numchars){
        Random r = new Random();
        StringBuffer sb = new StringBuffer();
        while(sb.length() < numchars){
            sb.append(String.format("%02x",r.nextInt()));
        }

        return sb.toString().substring(0, numchars);
    }
	
	private static int hash[] = new int[7];
	
	
	// 솔트 만들기
	public static String getSalt() {
		
		// 1. SecureRandom,byte 객체 생성
		SecureRandom random = new SecureRandom();
		byte[] salt = new byte[4];
		
		// 2. 난수 생성
		random.nextBytes(salt);
		
		// 3. byte -> String
		return byte_to_String(salt);
		
	}
	
	// byte -> String
	public static String byte_to_String(byte[] temp) {
		StringBuilder sb = new StringBuilder();
		for(byte b : temp) {
			sb.append(String.format("%02x", b));
		}
		return sb.toString();
	}
	
	
	
	
	
}

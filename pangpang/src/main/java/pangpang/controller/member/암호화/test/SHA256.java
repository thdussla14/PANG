package pangpang.controller.member.암호화.test;

import java.security.MessageDigest;
import java.security.SecureRandom;


public class SHA256 {
	public static void main(String[] args) {
		SHA256 sha256 = new SHA256();
		
		String pwd = "cceeun.tistory";
		System.out.println("pwd : "+pwd);
		
		// 솔트
		String salt = sha256.getSalt();
		System.out.println("salt : "+salt);
		
		// 암호화
		String result = sha256.getEncrypt(pwd, salt);
		System.out.println("result : "+result);
	}
	 
	
	// 솔트 만들기
	public String getSalt() {
		
		// 1. SecureRandom,byte 객체 생성
		SecureRandom random = new SecureRandom();
		byte[] salt = new byte[20];
		
		// 2. 난수 생성
		random.nextBytes(salt);
		
		// 3. byte -> String
		return byte_to_String(salt);
		
	}
	
	// 암호화 알고리즘
	public String getEncrypt( String pwd, String salt) {
		String result = "";
		
		try {
			// 1. SHA256 알고리즘 객체 생성
			MessageDigest md = MessageDigest.getInstance("SHA-256");//직접 만들어보기
			
			// 2. pwd와 salt 합친 문자열에 SHA256 적용
			System.out.println(pwd+salt);
			md.update((pwd+salt).getBytes());// 문자를 스트링으로 만드는 것
			byte[] pwdsalt = md.digest();
			// 3. byte -> String
			System.out.println(byte_to_String(pwdsalt));
			return byte_to_String(pwdsalt);
			
		} catch (Exception e) {
			System.out.println(e);
		}return null;
	}
	
	// byte -> String
	public String byte_to_String(byte[] temp) {
		StringBuilder sb = new StringBuilder();
		for(byte b : temp) {
			sb.append(String.format("%02x", b));
		}
		return sb.toString();
	}
	
	
}

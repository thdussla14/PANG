package pangpang.controller.member.암호화;


import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import pangpang.controller.member.암호화.test.SHA256;

public class AES256 {
	
	/*
	public static void main(String[] args) throws Exception {
		SHA256 sha256 = new SHA256();
		String pwd = "cceeun.tistory";
		String salt = sha256.getSalt();
		String key = (sha256.getEncrypt(pwd, salt)).substring(0,32);
		System.out.println("key : "+key);
		
		AES256 aes256 = new AES256();
		String text = "안녕하세요";
		String encrypt = aes256.encrypt(key, text);
		System.out.println("encrypt : " + encrypt);
		
		String decrypt = aes256.decrypt(key, encrypt);
		System.out.println("decrypt : "+decrypt);
		
	}
	*/
	
	public static String encrypt( String key, String text) throws Exception {
		// 1. Cipher 객체 인스턴스화 하기 
			// AES/ECB/PKCS5Padding 변환은 getInstance 메서드에 Cipher 객체를 AES 암호화, CBC operation mode, PKCS5 padding scheme로 초기화하라고 요청한다. 
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		// 2. 저장된 키를 불러와서 사용하려면 SecretKeySpec 클래스를 사용한다. 
			// SecretSpec 클래스의 생성자를 사용해서 바이트 배열의 데이터키를 비밀키로 변환할 수 있다.
		SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), "AES");
		// 3. Creates an IvParameterSpec object using the bytes in iv as the IV.
		IvParameterSpec ivspec = new IvParameterSpec((key.substring(0,16)).getBytes());
		// 4. cipher 초기화
			/*다음은 이용 가능한 cipher 작업 모드 목록이다.
				- ENCRYPT_MODE: cipher 객체를 암호화 모드로 초기화한다.
				- DECRYPT_MODE: cipher 객체를 복호화 모드로 초기화한다.
				- WRAP_MODE: cipher 객체를 key-wrapping 모드로 초기화한다.
				- UNWRAP_MODE: cipher 객체를 key-unwrapping 모드로 초기화한다.*/
		cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivspec);
		
		// 5. doFinal는 암호화 또는 복호화된 메세지를 포함한 byte 배열을 반환한다.
		byte[] encrypted = cipher.doFinal(text.getBytes());
		// 6. 스트링으로 변환해서 반환
		return Base64.getEncoder().encodeToString(encrypted);
	}
	
	public static String decrypt(String key, String cipherText) throws Exception {
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), "AES");
        IvParameterSpec ivParamSpec = new IvParameterSpec((key.substring(0,16)).getBytes());
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivParamSpec);
        
        // 스트링을 다시 바이트로 변환
        byte[] decodedBytes = Base64.getDecoder().decode(cipherText);
        byte[] decrypted = cipher.doFinal(decodedBytes);
        return new String(decrypted, "UTF-8");
	}
}

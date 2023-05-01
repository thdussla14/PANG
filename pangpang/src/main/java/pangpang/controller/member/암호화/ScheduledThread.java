package pangpang.controller.member.암호화;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


public class ScheduledThread extends Thread{
	
	@Override
	public void run(){		
		// 실행간격 지정
		int sleepSec = 600;		
		// 주기적인 작업을 위한 
		final ScheduledThreadPoolExecutor  exec = new ScheduledThreadPoolExecutor(1);
		exec.scheduleAtFixedRate(new Runnable(){	
			public void run(){
				try {
					Date date = new Date();
					final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
					
					String date2 = simpleDateFormat.format(date);
					String salt = getRandom(20);
				
					OutputStream output = new FileOutputStream("c:/java/salt.txt", true);
				    String str = salt+","+date2+"\n";
				    
				    byte[] bytearr=str.getBytes();
				    output.write(bytearr);
				    output.close();
					
				} catch (Exception e) {
					e.printStackTrace();
					// 에러 발생시 Executor를 중지시킨다
					exec.shutdown() ;
				} 
			}
		}, 0, sleepSec, TimeUnit.SECONDS);
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

}

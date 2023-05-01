package pangpang.controller.member.암호화.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;

import pangpang.model.Dto.member.SaltDto;

public class 파일처리 {
	public static void main(String[] args) {
		try {
			/*
		    OutputStream output = new FileOutputStream("c:/java/salt1.txt", true);
		    String str ="a9815071d4c7f4bd013a29a823cdad511fa03a30\n";
		    byte[] by=str.getBytes();
		    output.write(by);
		    output.close();
		    */
			File file = new File("c:/java/salt.txt");
	        //입력 스트림 생성
	        FileReader file_reader = new FileReader(file);
	        BufferedReader bufReader = new BufferedReader(file_reader);
	        String line = "";
	        ArrayList<SaltDto> salts = new ArrayList<>();
	        
	        while ((line = bufReader.readLine()) != null) {
	          System.out.println("한줄 : " +line);
	          String[] saltArr = line.split(",");
	          SaltDto dto = new SaltDto(saltArr[0], saltArr[1]);
	          salts.add(dto);
	        }
	        
	        System.out.println(salts);
	        bufReader.close();
				
		} catch (Exception e) {
	            e.getStackTrace();
		}
	}
}

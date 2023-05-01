package pangpang.controller.member.암호화;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import pangpang.model.Dto.member.SaltDto;

public class GetSalt {
	public static ArrayList<SaltDto> getSalt() {
		// 저장된 솔트 꺼내기
		ArrayList<SaltDto> salts = new ArrayList<>();
		
		try {
			File file = new File("c:/java/salt.txt");
	        FileReader file_reader = new FileReader(file);
	        BufferedReader bufReader = new BufferedReader(file_reader);
	        String line = "";
	        
	        while ((line = bufReader.readLine()) != null) {
	          //System.out.println("한줄 : " +line);
	          String[] saltArr = line.split(",");
	          SaltDto dto = new SaltDto(saltArr[0], saltArr[1]);
	          salts.add(dto);
	        }
	        bufReader.close();
			return salts;
		} catch (Exception e) {
			System.out.println(e);
		} return salts;
	}
}

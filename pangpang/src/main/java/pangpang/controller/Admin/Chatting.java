package pangpang.controller.Admin;

import java.util.ArrayList;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import com.fasterxml.jackson.databind.ObjectMapper;

import pangpang.model.Dao.member.MemberDao;
import pangpang.model.Dto.Admin.ClientDto;
import pangpang.model.Dto.Admin.AccessorDto;
import pangpang.model.Dto.Admin.ChatDto;
import pangpang.model.Dto.member.MemberDto;

@ServerEndpoint(value = "/chatting/{mid}")
public class Chatting {
	// *-* 접속한 클라이언트명단[목록] ( 클라이언트소켓 여러개 저장 )
	public static ArrayList< ClientDto > 접속명단 = new ArrayList<>();
	
	// 클라이언트 소켓이 접속했을때의 실행되는 메소드/함수 
	@OnOpen		// session[ 접속한 클라이언트소켓 객체 ] // 서버 엔드포인트의 URL 매개변수[ @PathParam ]  가져오기 
	public void onOpen( Session session , @PathParam("mid") String mid ) throws Exception {
		int member_no = MemberDao.getInstance().getMno(mid);
		MemberDto dto = MemberDao.getInstance().getMember(member_no);
		
		if(dto.getMember_rank()>1) {
			// 접속한 클라이언트소켓 들을 보관 
			ClientDto clientDto = new ClientDto( session , mid , dto.getMember_name(), dto.getMember_no());
			접속명단.add( clientDto  );
		}
		// 연결된 클라이언트 소켓를 모든 접속명단 목록 메시지 보내기 
		onMessage( session, "enter");
		
	}// end  
	
	
	
	@OnClose 	// 클라이언트소켓이 나갔을때
	public void onClose( Session session ) throws Exception {
		// 접속이 끊긴 세션의 dto 찾아서 제외 
		for( ClientDto dto : 접속명단 ) {
			// 회원명단 에서 세션과 접속이 끊긴 세션과 일치하면 
			if( dto.getSession() == session ) { 
				접속명단.remove(dto);	// 접속 명단에서 제외 시키지
				
				// 연결이 끊긴 클라이언트 소켓를 모든 접속명단에게 알림 메시지 보내기 
				// 1. 문자열타입의 JSON형식 직접 작성하기 [ VS ObjectMapper ]
					// { "필드명1" : "데이터" , "필드명2" : "데이터2" }
				String msg = "{\"type\":\"alarm\",\"msgbox\":\""+dto.getMember_id()+"님이 채팅방에 나갔습니다.\"}";
				onMessage(session, msg );
				// 연결이 끊긴 클라이언트 소켓를 모든 접속명단 목록 알림 메시지 보내기 
				onMessage( session , "enter" );
				break;
			}
		}
	}// end 
	
	// 클라이언트 소켓이 메시지를 보냈을때[ 서버가 메시지 받기 ]
	@OnMessage // [ Session[누가] , String[내용물] ]
	public static void onMessage( Session session , String msg ) throws Exception {
		
		// 메시지 받는 프로그램[JS] : JSON 으로 형변환 // * Session 객체를 json형식 으로 변환 불가능
		ObjectMapper mapper = new ObjectMapper();
		String json =null;
		
		// 2.접속명단 알림
		if( msg.equals("enter") ) {
			// 회원명단[이미지,아이디] 포함된 회원리스트 생성 
			ArrayList< AccessorDto > list = new ArrayList<>();
			for( ClientDto dto : 접속명단 ) {
				list.add( new AccessorDto( dto.getSession() , null ) ); // 현재 접속된 회원정보 객체 생성
			}
			json = mapper.writeValueAsString( list );	// 접속자 명단 객체 여러개 
			for( ClientDto dto : 접속명단 ) {
			dto.getSession().getBasicRemote().sendText( json );	
			}
		// 1.메시지 
		}else { 
			// 메시지를 받는 회원번호 
			int chat_tmno = Integer.parseInt( msg.split(",")[0] );
			// 메시지를 보낸 회원번호
			int chat_fmno = Integer.parseInt( msg.split(",")[2] );
			// 메시지 내용 
			String chat_msg = msg.split(",")[1];
			System.out.println(chat_tmno);
			System.out.println(chat_msg);
			for( ClientDto dto : 접속명단 ) {
				// 현재 소켓의 접속된 회원의 회원번호 구하기 
				int chat_tmno2 =  dto.getMember_no();
				ChatDto chatDto = new ChatDto(chat_msg, chat_fmno, chat_tmno);
				json = mapper.writeValueAsString( chatDto );
				if( chat_tmno2 == chat_tmno ) { // 받는 회원번호가 알림명단에 존재하면
					dto.getSession().getBasicRemote().sendText( json );
				}
			}
		}
	}// end 
		
}

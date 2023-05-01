drop database if exists pangpang;
create database pangpang;
use pangpang;

drop table if exists drivecar;
drop table if exists bookcar;
drop table if exists carmanage;
drop table if exists cart;
drop table if exists payment;
drop table if exists orderdetail;
drop table if exists ordermanagement;
drop table if exists stockmanagement;
drop table if exists product;
drop table if exists category;
drop table if exists account;
drop table if exists member;
--  회원페이지 -------------------------------------------------------------------------------

create table member(
	member_no 		int auto_increment primary key,            	-- 회원번호    	기본키 자동입력
	member_name     varchar(20) not null,
	member_id 		varchar(20) not null unique,                -- 회원아이디      빈칸X 중복X
	member_pwd 		varchar(20) not null,                       -- 회원비밀번호    	빈칸X 중복O
	member_email 	varchar(100) not null unique,               -- 회원이메일      빈칸X 중복X
	member_phone 	varchar(20) not null,                       -- 회원전화번호   	빈칸X 중복O
	member_address 	longtext not null,                     		-- 회원주소      	빈칸X 중복O
	member_birth 	date not null,                             	-- 회원생일    	빈칸X 중복O
	member_rank 	int default 1                               -- 회원등급   	빈칸X 중복O
);


create table account(
	account_no 		int auto_increment primary key,             -- 계좌구분번호    	기본키 자동입력
	account_bank 	varchar(20),                                -- 은행명       	빈칸X 중복O
	account_number 	varchar(100),                           	-- 계좌번호      	빈칸X 중복O
	member_no 		int,                                        -- 회원번호      	빈칸X 중복O
	foreign key(member_no) references member(member_no) on delete cascade 		-- 멤버 지우면 같이 삭제 
);


-- 제품관리 ---------------------------------------------------------------------------------
-- 제품 카테고리 테이블
create table category(
   category_no          int   auto_increment primary key,    			-- 카테고리번호 pk
   category_name        varchar(30) not null ,                 			-- 카테고리명
   category_img         varchar(30) not null                  			-- 카테고리대표이미지
);
-- 제품 테이블                                        						-- 보관조건? 재고위치? // 추가 보완 사항?
create table product(               
   product_no           int auto_increment primary key,   	 			   -- 제품번호 pk
   product_name         varchar(30)  not null,                			   -- 제품명
   product_option       varchar(10)  not null,                			   -- 규격/옵션         -- 100g / 500ml   
   product_unit         varchar(10)  not null,                			   -- 단위             -- (개/봉/박스/곽/통/캔)   
   product_img          varchar(100) not null,                			   -- 제품이미지
   product_content      longtext not null,                  			   -- 제품상세설명        -- 원산지 / 보관방법 
   product_price		int not null,									   -- 판매가
   product_discount		int not null,									   -- 최대할인율 
   category_no          int,                               				   -- 카테고리번호 fk
   foreign key (category_no) references category( category_no ) on delete cascade
);
-- 입출고 테이블                                        			-- 제조년월 / 소비기한 / 폐기 예정일은 어떻게? -- 테이블 분리?
create table stockmanagement(
   stockmanagementno        int   auto_increment primary key,    			-- 재고관리번호 pk
   stockmanagementdate      datetime default now(),                			-- 일자
   stockmanagementenddate   datetime,                          				-- 예정 폐기 일자                      
   stockmanagementtype      int  not null,               					-- 구분   ( 입고 / 출고 / 폐기 / 반품 )	-- int 저장이 효율적인가? 
   stockmanagementcompany   varchar(20) not null,                			-- 업체   ( 입고처/출고처/ 폐기업체)       -- 출고처 = 회원번호? 주문번호?   
   stockmanagementamount   	int   not null,                      			-- 수량
   product_price       		int not null,                       			-- 단가    개당 단가                           
   product_no          		int not null,                        			-- 제품번호 fk
   foreign key (product_no) references product( product_no ) on delete cascade
  
);

-- 입고
-- 입고일 제품명 수량 단가 담당직원 거래처 폐기일?제조일?
-- 출고 
-- 출고일 제품명 수량 단가 담당직원 판매처 
-- 폐기
-- 폐기일 제품명 수량 단가 담당직원 폐기처 

-- 장바구니 테이블
create table cart(
	cart_no         			int auto_increment primary key,    		-- 장바구니번호 pk
    cart_amount					int not null,                        	-- 제품수량
	product_no          		int not null,                        	-- 제품코드 fk
	member_no         			int not null,                        	-- 주문회원 fk   
	foreign key (product_no)  references product( product_no ) on delete no action, 
	foreign key (member_no)   references member( member_no ) on delete no action
);
-- 주문 테이블    
create table ordermanagement(
   ordermanagement_no         	int   auto_increment primary key,    	-- 주문번호 pk
   ordermanagement_date       	datetime default now(),              	-- 주문일자      
   ordermanagement_state      	int	  not null,               			-- 주문상태                                   -- 결제확인중/결제확인/배송지연/배송중/배송완료/거래완료/     
   ordermanagement_address    	varchar(100) not null,                	-- 배송주소       
   member_no         			int not null,                        	-- 주문회원 fk     
   foreign key (member_no)   references member( member_no ) on delete no action 
);
-- 주문상세 테이블
create table orderdetail(
   orderdetaildno         	int   auto_increment primary key,   	-- 주문상세번호 pk
   orderdetaildamount      	int   not null,                     	-- 주문수량 
   orderdetaildprice        int   not null,                     	-- 주문단가
   ordermanagement_no      	int   not null,                       	-- 주문번호 fk
   product_no          		int	  not null,                        	-- 제품코드 fk
   foreign key (product_no) references product( product_no ) on delete no action, 
   foreign key (ordermanagement_no)   references ordermanagement( ordermanagement_no ) on delete cascade
);
-- 결제 테이블 
create table payment(
   payment_no         	int   auto_increment primary key,   	-- 결제번호 pk
   payment_date      	datetime default now(),               	-- 결제일자      
   payment_how         	varchar(20) not null,               	-- 결제방법      ( 무통장거래 / 신용카드 / 네이버페이 / 카카오페이 )
   payment_price    	int   not null,                     	-- 결제금액   
   ordermanagement_no   int   ,                              	-- 주문번호 fk 
   foreign key (ordermanagement_no)   references ordermanagement( ordermanagement_no ) on delete cascade
);

-- 차량관리 페이지 -------------------------------------------------------------------------------------------
-- 차량관리
CREATE TABLE carmanage (
	carmanage_no       			int auto_increment primary key,   		-- 차량일련번호 (PK)
	carmanage_number    			varchar(10),             			-- 차량번호
	carmanage_name      			varchar(40),              			-- 차량명
    carmanage_img					varchar(40),              			-- 차량이미지
	carmanage_use_yn   	 			varchar(1),                 		-- 차량사용여부 (배차때 쓰는 여부)
	carmanage_start   				datetime default now(),          	-- 차량등록일자
	carmanage_finish				datetime,							-- 차량폐기일자				
    carmanage_cumulative_mileage 	bigint								-- 누적주행거리
);

-- 배차관리 
create table bookcar(
	bookcar_no 			int auto_increment primary key,           	--  배차일련번호
	bookcar_str_date 	datetime,                              		--  배차시작일자 3/24(2315) 
	bookcar_end_date 	datetime,                            		--  배차종료일자 3/26(2225)
	bookcar_yn      	varchar(1),                                 --  배차승인여부
	carmanage_no 		int,                                        --  차량일련번호
    member_no 		int,                                       		--  회원번호      	빈칸X 중복O
	foreign key(member_no) references member(member_no) on delete cascade, 		-- 멤버 지우면 같이 삭제 
	foreign key (carmanage_no) references carmanage(carmanage_no) --  차량일련번호fk--   foreign key (mno) references member(mno)    --  사용자일련번호fk -- 배차승인여부?
);

-- 운행일지
create table drivecar(
	drivecar_no    			int auto_increment primary key,            	-- 운행일지일련번호 
	drivecar_str_date 		datetime,                        			-- 운행시작일자
	drivecar_end_date 		datetime,                        			-- 운행종료일자
	drivecar_distance 		int,										-- 운행거리
	drivecar_parking 		varchar(50),   								-- 주차위치
	bookcar_no   			int,     									-- 배차일련번호
	foreign key (bookcar_no) references bookcar(bookcar_no)   			-- 배차일련번호  fk
); 


insert into member ( member_id , member_name, member_pwd , member_email , member_phone , member_address , member_birth , member_rank )
values ( 'qwe' , '강호동', '1234' , 'qweqwe@qweqwe.com' , '010-1234-5600' , '경기 안산시 단원구 광덕2로 121' , '2000-01-01' , 3 ); 
insert into member ( member_id , member_name, member_pwd , member_email , member_phone , member_address , member_birth , member_rank )
values ( 'asd' , '김희철', '1234' , 'asdasd@asdasd.com' , '010-1234-5601' , '경기 안산시 단원구 광덕3로 201' , '2000-01-01' , 1 ); 
insert into member ( member_id , member_name, member_pwd , member_email , member_phone , member_address , member_birth , member_rank )
values ( 'zxc' , '서장훈', '1234' , 'zxczxc@zxczxc.com' , '010-1234-5602' , '경기 안산시 상록구 한양대학로 80' , '2000-01-01' , 2 ); 
insert into member ( member_id , member_name, member_pwd , member_email , member_phone , member_address , member_birth , member_rank )
values ( 'qweasd' , '이상민', '1234' , 'qweasd@qweasd.com' , '010-1234-5603' , '경기 안산시 단원구 초지로 128' , '2000-01-01' , 2 ); 
insert into member ( member_id , member_name, member_pwd , member_email , member_phone , member_address , member_birth , member_rank )
values ( 'bongseong' , '유재석',  '1234' , 'bongseong@bongseong.com' , '010-1234-5605' , '경기도 시흥시 장곡로 53번길 10' , '2000-01-01' , 2 );

insert into ordermanagement ( ordermanagement_state , ordermanagement_address , member_no ) values ( 1 , '경기 안산시 단원구 광덕2로 121' , 1 ) ;
insert into ordermanagement ( ordermanagement_state , ordermanagement_address , member_no ) values ( 2 , '경기 안산시 단원구 광덕3로 201' , 2 ) ;
insert into ordermanagement ( ordermanagement_state , ordermanagement_address , member_no ) values ( 3 , '경기 안산시 상록구 한양대학로 80' , 3 ) ;
insert into ordermanagement ( ordermanagement_state , ordermanagement_address , member_no ) values ( 4 , '경기 안산시 단원구 초지로 128' , 4 ) ;
insert into ordermanagement ( ordermanagement_state , ordermanagement_address , member_no ) values ( 2 , '경기도 안산시 단원구 중앙대로 397' , 2 ) ;
insert into ordermanagement ( ordermanagement_state , ordermanagement_address , member_no ) values ( 2 , '경기도 안산시 단원구 동산로 268' , 2 ) ;
insert into ordermanagement ( ordermanagement_state , ordermanagement_address , member_no ) values ( 2 , '경기도 안산시 상록구 석호로3길 8' , 2 ) ;
insert into ordermanagement ( ordermanagement_state , ordermanagement_address , member_no ) values ( 2 , '경기도 안산시 상록구 항가울로 111' , 2 ) ;
insert into ordermanagement ( ordermanagement_state , ordermanagement_address , member_no ) values ( 2 , '경기도 안산시 단원구 적금로 202' , 2 ) ;
insert into ordermanagement ( ordermanagement_state , ordermanagement_address , member_no ) values ( 2 , '경기도 안산시 상록구 해안로 705' , 2 ) ;
insert into ordermanagement ( ordermanagement_state , ordermanagement_address , member_no ) values ( 2 , '경기도 안산시 단원구 대부황금로 1432' , 2 ) ;
insert into ordermanagement ( ordermanagement_state , ordermanagement_address , member_no ) values ( 2 , '경기도 안산시 단원구 중앙대로 397' , 2 ) ;
insert into ordermanagement ( ordermanagement_state , ordermanagement_address , member_no ) values ( 2 , '경기도 안산시 단원구 대부황금로 1901' , 2 ) ;
insert into ordermanagement ( ordermanagement_state , ordermanagement_address , member_no ) values ( 2 , '경기도 안산시 단원구 원선로 31' , 2 ) ;
insert into ordermanagement ( ordermanagement_state , ordermanagement_address , member_no ) values ( 2 , '경기도 안산시 단원구 대부황금로 1488' , 2 ) ;
insert into ordermanagement ( ordermanagement_state , ordermanagement_address , member_no ) values ( 2 , '경기도 안산시 단원구 화랑로 312' , 2 ) ;
insert into ordermanagement ( ordermanagement_state , ordermanagement_address , member_no ) values ( 2 , '경기도 안산시 상록구 본삼로 17' , 2 ) ;
insert into ordermanagement ( ordermanagement_state , ordermanagement_address , member_no ) values ( 2 , '경기도 안산시 상록구 석호로 226' , 2 ) ;
insert into ordermanagement ( ordermanagement_state , ordermanagement_address , member_no ) values ( 2 , '경기도 안산시 상록구 용신로 422' , 2 ) ;
insert into ordermanagement ( ordermanagement_state , ordermanagement_address , member_no ) values ( 2 , '경기도 안산시 단원구 중앙대로 685' , 2 ) ;
insert into ordermanagement ( ordermanagement_state , ordermanagement_address , member_no ) values ( 2 , '경기도 안산시 단원구 첨단로 644' , 2 ) ;
insert into ordermanagement ( ordermanagement_state , ordermanagement_address , member_no ) values ( 2 , '경기도 안산시 상록구 영화5길 1' , 2 ) ;
insert into ordermanagement ( ordermanagement_state , ordermanagement_address , member_no ) values ( 2 , '경기도 안산시 단원구 선부광장로 87' , 2 ) ;

insert into carmanage(carmanage_number,carmanage_name,carmanage_img,carmanage_use_yn,carmanage_start,carmanage_finish)
values('백1234','gv80','gv80.png','N','2010-01-02','2020-06-11');

insert into carmanage(carmanage_number,carmanage_name,carmanage_img,carmanage_use_yn,carmanage_start,carmanage_finish)
values('한1234','k3','k3.png','N','2005-5-11','2022-12-10');

insert into carmanage(carmanage_number,carmanage_name,carmanage_img,carmanage_use_yn,carmanage_start,carmanage_finish)
values('결1234','레이','레이.png','Y','2011-07-11','2020-06-11'); -- 

insert into carmanage(carmanage_number,carmanage_name,carmanage_img,carmanage_use_yn,carmanage_start,carmanage_finish)
values('정1234','모닝','모닝.png','Y','2007-11-11','2020-06-11');

insert into carmanage(carmanage_number,carmanage_name,carmanage_img,carmanage_use_yn,carmanage_start,carmanage_finish)
values('소1234','소넷','소넷.png','Y','2008-02-11','2020-06-11');

insert into carmanage(carmanage_number,carmanage_name,carmanage_img,carmanage_use_yn,carmanage_start,carmanage_finish)
values('연1234','스타리','스타리.png','Y','2012-06-11','2020-06-11');

insert into carmanage(carmanage_number,carmanage_name,carmanage_img,carmanage_use_yn,carmanage_start,carmanage_finish)
values('김1234','렉스턴스포츠','렉스턴스포츠.jpg','Y','2011-07-11','2020-06-11');

insert into carmanage(carmanage_number,carmanage_name,carmanage_img,carmanage_use_yn,carmanage_start,carmanage_finish)
values('지2345','스타리','스타리.png','N','2009-08-11','2022-12-10');

insert into carmanage(carmanage_number,carmanage_name,carmanage_img,carmanage_use_yn,carmanage_start,carmanage_finish)
values('호2345','냉동탑차','냉동탑차.jpg','N','2002-06-11','2022-10-10');

insert into carmanage(carmanage_number,carmanage_name,carmanage_img,carmanage_use_yn,carmanage_start,carmanage_finish)
values('김2345','쏘렌토','쏘렌토.jpg','Y','2005-05-11','2022-12-10');

insert into carmanage(carmanage_number,carmanage_name,carmanage_img,carmanage_use_yn,carmanage_start,carmanage_finish)
values('성2345','쏘렌토2','쏘렌토2.jpg','N','2020-10-11','2022-5-10');

insert into carmanage(carmanage_number,carmanage_name,carmanage_img,carmanage_use_yn,carmanage_start,carmanage_finish)
values('봉2345','아반테','아반테.png','Y','2016-12-11','2020-06-11');

insert into carmanage(carmanage_number,carmanage_name,carmanage_img,carmanage_use_yn,carmanage_start,carmanage_finish)
values('우2345','아슬란','아슬란.png','N','2014-09-11','2020-12-10');

insert into carmanage(carmanage_number,carmanage_name,carmanage_img,carmanage_use_yn,carmanage_start,carmanage_finish)
values('리6789','옛날모닝','옛날모닝.jpg','Y','2018-07-11','2020-06-11');

insert into carmanage(carmanage_number,carmanage_name,carmanage_img,carmanage_use_yn,carmanage_start,carmanage_finish)
values('팀6789','우체국','우체국.jpg','Y','2003-05-11','2020-06-11');

insert into carmanage(carmanage_number,carmanage_name,carmanage_img,carmanage_use_yn,carmanage_start,carmanage_finish)
values('사6789','카니발','카니발.jpg','N','2020-07-11','2022-12-08');

insert into carmanage(carmanage_number,carmanage_name,carmanage_img,carmanage_use_yn,carmanage_start,carmanage_finish)
values('랑6789','칸','칸.jpg','Y','2015-11-11','2020-06-11');

insert into carmanage(carmanage_number,carmanage_name,carmanage_img,carmanage_use_yn,carmanage_start,carmanage_finish)
values('해6789','캐스퍼','캐스퍼.png','Y','2005-10-11','2022-12-10');

insert into carmanage(carmanage_number,carmanage_name,carmanage_img,carmanage_use_yn,carmanage_start,carmanage_finish)
values('♥6789','타호','타호.jpg','N','2005-09-11','2022-12-10');

insert into carmanage(carmanage_number,carmanage_name,carmanage_img,carmanage_use_yn,carmanage_start,carmanage_finish)
values('짱6789','트레일러','트레일러.jpg','Y','2012-06-11','2020-06-11');

insert into carmanage(carmanage_number,carmanage_name,carmanage_img,carmanage_use_yn,carmanage_start,carmanage_finish)
values('짱1234','포터','포터.jpg','Y','2015-02-11','2020-06-11');


-- 멤버 추가
insert into member(member_id,member_name,member_pwd,member_email,member_phone,member_address,member_birth,member_rank) 
values ('qweqwe','유재석','qwe123','qwe@naver.com','010-1111-1111','안산시 단원구 광덕3로 201','2000-03-23',1);
insert into member(member_id,member_name,member_pwd,member_email,member_phone,member_address,member_birth,member_rank) 
values ('bnmbnm','강호동','bnm123','bnm@naver.com','010-2222-2222','안산시 단원구 광덕3로 201','2000-03-23',1);
insert into member(member_id,member_name,member_pwd,member_email,member_phone,member_address,member_birth,member_rank) 
values ('asdads','신동엽','asd123','asd@naver.com','010-3333-3333','안산시 단원구 광덕3로 201','2000-06-06',2);
insert into member(member_id,member_name,member_pwd,member_email,member_phone,member_address,member_birth,member_rank) 
values ('zxczxc','민경훈','zxc123','zcx@naver.com','010-4444-4444','안산시 단원구 광덕3로 201','2000-08-13',2);

-- 카테고리 추가
insert into category(category_name,category_img) values ('과일','과일.png');
insert into category(category_name,category_img) values ('정육/계란','정육.png');
insert into category(category_name,category_img) values ('밀키트','밀키트.png');
insert into category(category_name,category_img) values ('샐러드','샐러드.png');
insert into category(category_name,category_img) values ('수산','수산.png');
insert into category(category_name,category_img) values ('통조림/즉석밥','통조림.png');
insert into category(category_name,category_img) values ('쌀/잡곡','잡곡.png');
insert into category(category_name,category_img) values ('베이커리','베이커리.png');
insert into category(category_name,category_img) values ('우유/유제품','유제품.png');
insert into category(category_name,category_img) values ('간식/떡/빙과','간식.png');
insert into category(category_name,category_img) values ('커피/음료','음료.png');
insert into category(category_name,category_img) values ('양념','양념.png');

select * from category;

-- 제품 추가
insert into product(product_name,product_option,product_unit,product_img,product_content,product_price,product_discount,category_no) 
values ('곰곰 고령 딸기','500g','팩','딸기.png','입과 눈 모두 즐거운 시나노 골드 사과 골든데리셔스와 천추를 교배하여 개발한 시나노 골드 사과입니다.',15000,20,1);
insert into product(product_name,product_option,product_unit,product_img,product_content,product_price,product_discount,category_no) 
values ('곰곰 안동 시나노 골드사과','1.5kg','봉','골드사과.png','입과 눈 모두 즐거운 시나노 골드 사과 골든데리셔스와 천추를 교배하여 개발한 시나노 골드 사과입니다.',15000,20,1);
insert into product(product_name,product_option,product_unit,product_img,product_content,product_price,product_discount,category_no) 
values ('곰곰 칠레산 블루베리','125g','팩','블루베리.png','입과 눈 모두 즐거운 시나노 골드 사과 골든데리셔스와 천추를 교배하여 개발한 시나노 골드 사과입니다.',15000,20,1);
insert into product(product_name,product_option,product_unit,product_img,product_content,product_price,product_discount,category_no) 
values ('곰곰 당도선별 성주참외','1kg','봉','참외.png','건강하게 자란 성주참외는 기분 좋은 시원한 맛과 풍부한 영양으로 입맛을 돋게 합니다.',18000,20,1);
insert into product(product_name,product_option,product_unit,product_img,product_content,product_price,product_discount,category_no)  
values ('곰곰 달콤한 스테비아 대추방울토마토','1kg','봉','방울토마토.png','달달한 스윗토 탄탄한 과육 속 진한 단맛이 매력적인 스윗토예요.',6000,10,1);
insert into product(product_name,product_option,product_unit,product_img,product_content,product_price,product_discount,category_no) 
values ('곰곰 칠레산 씨없는 포도','900g','팩','포도.png','새콤하고 쌉가름한 과즙을 가득 머금은 곰곰 이스라엘 자몽',8000,0,1);
insert into product(product_name,product_option,product_unit,product_img,product_content,product_price,product_discount,category_no) 
values ('곰곰 이스라엘 자몽','2kg','봉','자몽.png','새콤하고 쌉가름한 과즙을 가득 머금은 곰곰 이스라엘 자몽',8000,0,1);
insert into product(product_name,product_option,product_unit,product_img,product_content,product_price,product_discount,category_no) 
values ('곰곰 필리핀 망고','500g','팩','망고.png','입과 눈 모두 즐거운 시나노 골드 사과 골든데리셔스와 천추를 교배하여 개발한 시나노 골드 사과입니다.',15000,20,1);
insert into product(product_name,product_option,product_unit,product_img,product_content,product_price,product_discount,category_no) 
values ('곰곰 그리스산 골드 키위','600g','팩','골드키위.png','건강하게 자란 성주참외는 기분 좋은 시원한 맛과 풍부한 영양으로 입맛을 돋게 합니다.',18000,20,1);
insert into product(product_name,product_option,product_unit,product_img,product_content,product_price,product_discount,category_no) 
values ('곰곰 칠레산 체리','400g','팩','체리.png','건강하게 자란 성주참외는 기분 좋은 시원한 맛과 풍부한 영양으로 입맛을 돋게 합니다.',18000,20,1);
insert into product(product_name,product_option,product_unit,product_img,product_content,product_price,product_discount,category_no)  
values ('곰곰 미국산 레몬','1.8kg','봉','레몬.png','달달한 스윗토 탄탄한 과육 속 진한 단맛이 매력적인 스윗토예요.',6000,10,1);
insert into product(product_name,product_option,product_unit,product_img,product_content,product_price,product_discount,category_no) 
values ('곰곰 멕시코산 아보카도','1kg','팩','아보카도.png','새콤하고 쌉가름한 과즙을 가득 머금은 곰곰 이스라엘 자몽',8000,10,1);

insert into product(product_name,product_option,product_unit,product_img,product_content,product_price,product_discount,category_no) 
values ('곰곰 돌돌말이 대패 삼겹살','1kg','개','대패삼겹.png','새콤하고 쌉가름한 과즙을 가득 머금은 곰곰 이스라엘 자몽',12000,10,2);
insert into product(product_name,product_option,product_unit,product_img,product_content,product_price,product_discount,category_no) 
values ('곰곰 부채살 바로구이','400g','개','부채살.png','새콤하고 쌉가름한 과즙을 가득 머금은 곰곰 이스라엘 자몽',16000,20,2);
insert into product(product_name,product_option,product_unit,product_img,product_content,product_price,product_discount,category_no) 
values ('곰곰 무항생제 신선한 특란','30구','판','특란.png','새콤하고 쌉가름한 과즙을 가득 머금은 곰곰 이스라엘 자몽',8000,20,2);
insert into product(product_name,product_option,product_unit,product_img,product_content,product_price,product_discount,category_no) 
values ('곰곰 맥반석 구운란','30구','판','구운란.png','새콤하고 쌉가름한 과즙을 가득 머금은 곰곰 이스라엘 자몽',8000,10,2);

insert into product(product_name,product_option,product_unit,product_img,product_content,product_price,product_discount,category_no) 
values ('곰곰 바지락술찜','450g','개','바지락술찜.png','새콤하고 쌉가름한 과즙을 가득 머금은 곰곰 이스라엘 자몽',12000,10,3);
insert into product(product_name,product_option,product_unit,product_img,product_content,product_price,product_discount,category_no) 
values ('곰곰 전통순두부찌개+짬뽕순두부찌개','1129g','개','순두부찌개.png','새콤하고 쌉가름한 과즙을 가득 머금은 곰곰 이스라엘 자몽',16000,20,3);
insert into product(product_name,product_option,product_unit,product_img,product_content,product_price,product_discount,category_no) 
values ('곰곰 골든비프 참스테이크 세트','571g','개','비프스테이크.png','새콤하고 쌉가름한 과즙을 가득 머금은 곰곰 이스라엘 자몽',8000,20,3);
insert into product(product_name,product_option,product_unit,product_img,product_content,product_price,product_discount,category_no) 
values ('곰곰 눈꽃치즈 닭갈비','960g','개','닭갈비.png','새콤하고 쌉가름한 과즙을 가득 머금은 곰곰 이스라엘 자몽',8000,10,3);

insert into product(product_name,product_option,product_unit,product_img,product_content,product_price,product_discount,category_no) 
values ('곰곰 텐터치킨보울샐러드','232g','개','보울샐러드.png','새콤하고 쌉가름한 과즙을 가득 머금은 곰곰 이스라엘 자몽',13000,10,4);
insert into product(product_name,product_option,product_unit,product_img,product_content,product_price,product_discount,category_no) 
values ('곰곰 리코타치즈샐러드','165g','개','리코타치즈샐러드.png','새콤하고 쌉가름한 과즙을 가득 머금은 곰곰 이스라엘 자몽',9000,20,4);
insert into product(product_name,product_option,product_unit,product_img,product_content,product_price,product_discount,category_no) 
values ('곰곰 닭가슴살샐러드','165g','개','닭가슴살샐러드.png','새콤하고 쌉가름한 과즙을 가득 머금은 곰곰 이스라엘 자몽',8000,20,4);
insert into product(product_name,product_option,product_unit,product_img,product_content,product_price,product_discount,category_no) 
values ('곰곰 샐러드 6종 세트','165g','개','샐러드6종.png','새콤하고 쌉가름한 과즙을 가득 머금은 곰곰 이스라엘 자몽',8000,10,4);

insert into product(product_name,product_option,product_unit,product_img,product_content,product_price,product_discount,category_no) 
values ('곰곰 국산 생물 홍가리비','1.5kg','개','홍가리비.png','새콤하고 쌉가름한 과즙을 가득 머금은 곰곰 이스라엘 자몽',13000,10,5);
insert into product(product_name,product_option,product_unit,product_img,product_content,product_price,product_discount,category_no) 
values ('곰곰 반건조 군산박대(4마리)','400g','개','박대.png','새콤하고 쌉가름한 과즙을 가득 머금은 곰곰 이스라엘 자몽',9000,20,5);
insert into product(product_name,product_option,product_unit,product_img,product_content,product_price,product_discount,category_no) 
values ('곰곰 국내산 손질오징어','700g','봉','손질오징어.png','새콤하고 쌉가름한 과즙을 가득 머금은 곰곰 이스라엘 자몽',8000,20,5);
insert into product(product_name,product_option,product_unit,product_img,product_content,product_price,product_discount,category_no) 
values ('곰곰 서해안 산지직송 암꽃게','1kg','박스','꽃게.png','새콤하고 쌉가름한 과즙을 가득 머금은 곰곰 이스라엘 자몽',8000,10,5);

insert into product(product_name,product_option,product_unit,product_img,product_content,product_price,product_discount,category_no) 
values ('김치찌개용 꽁치 통조림','400g','개','꽁치통조림.png','새콤하고 쌉가름한 과즙을 가득 머금은 곰곰 이스라엘 자몽',13000,10,6);
insert into product(product_name,product_option,product_unit,product_img,product_content,product_price,product_discount,category_no) 
values ('스위트콘 통조림','165g','개','스위트콘.png','새콤하고 쌉가름한 과즙을 가득 머금은 곰곰 이스라엘 자몽',9000,20,6);
insert into product(product_name,product_option,product_unit,product_img,product_content,product_price,product_discount,category_no) 
values ('황도 슬라이스 통조림','400g','봉','황도.png','새콤하고 쌉가름한 과즙을 가득 머금은 곰곰 이스라엘 자몽',8000,20,6);
insert into product(product_name,product_option,product_unit,product_img,product_content,product_price,product_discount,category_no) 
values ('자연산 골뱅이 통조림','400g','박스','골뱅이.png','새콤하고 쌉가름한 과즙을 가득 머금은 곰곰 이스라엘 자몽',8000,10,6);

select * from product; 

-- 입고 
insert into stockmanagement(stockmanagementdate , stockmanagementenddate , stockmanagementtype , stockmanagementcompany , stockmanagementamount  , product_price ,product_no)
values ('2023-03-25','2023-03-27',1,'GOMGOM',10,4000,1);  
insert into stockmanagement(stockmanagementdate , stockmanagementenddate , stockmanagementtype , stockmanagementcompany , stockmanagementamount  , product_price ,product_no)
values ('2023-03-25','2023-03-27',1,'GOMGOM',20,4000,2);   
insert into stockmanagement(stockmanagementdate , stockmanagementenddate , stockmanagementtype , stockmanagementcompany , stockmanagementamount  , product_price ,product_no)
values ('2023-03-26','2023-04-01',1,'GOMGOM',30,4000,3);   
insert into stockmanagement(stockmanagementdate , stockmanagementenddate , stockmanagementtype , stockmanagementcompany , stockmanagementamount  , product_price ,product_no)
values ('2023-03-26','2023-04-01',1,'GOMGOM',40,5000,4);  
insert into stockmanagement(stockmanagementdate , stockmanagementenddate , stockmanagementtype , stockmanagementcompany , stockmanagementamount  , product_price ,product_no)
values ('2023-03-26','2023-04-01',1,'GOMGOM',50,6000,5);  
insert into stockmanagement(stockmanagementdate , stockmanagementenddate , stockmanagementtype , stockmanagementcompany , stockmanagementamount  , product_price ,product_no)
values ('2023-03-26','2023-04-01',1,'GOMGOM',60,7000,6);  
insert into stockmanagement(stockmanagementdate , stockmanagementenddate , stockmanagementtype , stockmanagementcompany , stockmanagementamount  , product_price ,product_no)
values ('2023-03-26','2023-04-01',1,'GOMGOM',70,4000,7);  
insert into stockmanagement(stockmanagementdate , stockmanagementenddate , stockmanagementtype , stockmanagementcompany , stockmanagementamount  , product_price ,product_no)
values ('2023-03-26','2023-04-01',1,'GOMGOM',0 ,5000,8); 
insert into stockmanagement(stockmanagementdate , stockmanagementenddate , stockmanagementtype , stockmanagementcompany , stockmanagementamount  , product_price ,product_no)
values ('2023-03-26','2023-04-01',1,'GOMGOM',40,5000,9);  
insert into stockmanagement(stockmanagementdate , stockmanagementenddate , stockmanagementtype , stockmanagementcompany , stockmanagementamount  , product_price ,product_no)
values ('2023-03-26','2023-04-01',1,'GOMGOM',50,6000,10);  
insert into stockmanagement(stockmanagementdate , stockmanagementenddate , stockmanagementtype , stockmanagementcompany , stockmanagementamount  , product_price ,product_no)
values ('2023-03-26','2023-04-01',1,'GOMGOM',60,7000,11);  
insert into stockmanagement(stockmanagementdate , stockmanagementenddate , stockmanagementtype , stockmanagementcompany , stockmanagementamount  , product_price ,product_no)
values ('2023-03-26','2023-04-01',1,'GOMGOM',70,4000,12);  
insert into stockmanagement(stockmanagementdate , stockmanagementenddate , stockmanagementtype , stockmanagementcompany , stockmanagementamount  , product_price ,product_no)
values ('2023-03-26','2023-04-01',1,'GOMGOM',0 ,5000,13); 
insert into stockmanagement(stockmanagementdate , stockmanagementenddate , stockmanagementtype , stockmanagementcompany , stockmanagementamount  , product_price ,product_no)
values ('2023-03-26','2023-04-01',1,'GOMGOM',60,7000,14);  
insert into stockmanagement(stockmanagementdate , stockmanagementenddate , stockmanagementtype , stockmanagementcompany , stockmanagementamount  , product_price ,product_no)
values ('2023-03-26','2023-04-01',1,'GOMGOM',70,4000,15);  
insert into stockmanagement(stockmanagementdate , stockmanagementenddate , stockmanagementtype , stockmanagementcompany , stockmanagementamount  , product_price ,product_no)
values ('2023-03-26','2023-04-01',1,'GOMGOM',0 ,5000,16); 
insert into stockmanagement(stockmanagementdate , stockmanagementenddate , stockmanagementtype , stockmanagementcompany , stockmanagementamount  , product_price ,product_no)
values ('2023-03-26','2023-04-01',1,'GOMGOM',70,4000,17);  
insert into stockmanagement(stockmanagementdate , stockmanagementenddate , stockmanagementtype , stockmanagementcompany , stockmanagementamount  , product_price ,product_no)
values ('2023-03-26','2023-04-01',1,'GOMGOM',0 ,5000,18); 
insert into stockmanagement(stockmanagementdate , stockmanagementenddate , stockmanagementtype , stockmanagementcompany , stockmanagementamount  , product_price ,product_no)
values ('2023-03-26','2023-04-01',1,'GOMGOM',60,7000,19);  
insert into stockmanagement(stockmanagementdate , stockmanagementenddate , stockmanagementtype , stockmanagementcompany , stockmanagementamount  , product_price ,product_no)
values ('2023-03-26','2023-04-01',1,'GOMGOM',70,4000,20);  
insert into stockmanagement(stockmanagementdate , stockmanagementenddate , stockmanagementtype , stockmanagementcompany , stockmanagementamount  , product_price ,product_no)
values ('2023-03-26','2023-04-01',1,'GOMGOM',0 ,5000,21);
insert into stockmanagement(stockmanagementdate , stockmanagementenddate , stockmanagementtype , stockmanagementcompany , stockmanagementamount  , product_price ,product_no)
values ('2023-03-26','2023-04-01',1,'GOMGOM',0 ,5000,22); 
insert into stockmanagement(stockmanagementdate , stockmanagementenddate , stockmanagementtype , stockmanagementcompany , stockmanagementamount  , product_price ,product_no)
values ('2023-03-26','2023-04-01',1,'GOMGOM',60,7000,23);  
insert into stockmanagement(stockmanagementdate , stockmanagementenddate , stockmanagementtype , stockmanagementcompany , stockmanagementamount  , product_price ,product_no)
values ('2023-03-26','2023-04-01',1,'GOMGOM',70,4000,24);  
insert into stockmanagement(stockmanagementdate , stockmanagementenddate , stockmanagementtype , stockmanagementcompany , stockmanagementamount  , product_price ,product_no)
values ('2023-03-26','2023-04-01',1,'GOMGOM',0 ,5000,25); 
insert into stockmanagement(stockmanagementdate , stockmanagementenddate , stockmanagementtype , stockmanagementcompany , stockmanagementamount  , product_price ,product_no)
values ('2023-03-26','2023-04-01',1,'GOMGOM',60,7000,26);  
insert into stockmanagement(stockmanagementdate , stockmanagementenddate , stockmanagementtype , stockmanagementcompany , stockmanagementamount  , product_price ,product_no)
values ('2023-03-26','2023-04-01',1,'GOMGOM',70,4000,27);  
insert into stockmanagement(stockmanagementdate , stockmanagementenddate , stockmanagementtype , stockmanagementcompany , stockmanagementamount  , product_price ,product_no)
values ('2023-03-26','2023-04-01',1,'GOMGOM',0 ,5000,28);
insert into stockmanagement(stockmanagementdate , stockmanagementenddate , stockmanagementtype , stockmanagementcompany , stockmanagementamount  , product_price ,product_no)
values ('2023-03-26','2023-04-01',1,'GOMGOM',0 ,5000,29); 
insert into stockmanagement(stockmanagementdate , stockmanagementenddate , stockmanagementtype , stockmanagementcompany , stockmanagementamount  , product_price ,product_no)
values ('2023-03-26','2023-04-01',1,'GOMGOM',60,7000,30);  
insert into stockmanagement(stockmanagementdate , stockmanagementenddate , stockmanagementtype , stockmanagementcompany , stockmanagementamount  , product_price ,product_no)
values ('2023-03-26','2023-04-01',1,'GOMGOM',70,4000,31);  
insert into stockmanagement(stockmanagementdate , stockmanagementenddate , stockmanagementtype , stockmanagementcompany , stockmanagementamount  , product_price ,product_no)
values ('2023-03-26','2023-04-01',1,'GOMGOM',70,4000,32);  
 
-- 출고
insert into stockmanagement(stockmanagementdate ,stockmanagementtype , stockmanagementcompany , stockmanagementamount  , product_price ,product_no)
values ('2023-03-26',2,'GOMGOM',-2,5000,1);  
insert into stockmanagement(stockmanagementdate ,stockmanagementtype , stockmanagementcompany , stockmanagementamount  , product_price ,product_no)
values ('2023-03-26',2,'GOMGOM',-5,6000,2);
insert into stockmanagement(stockmanagementdate ,stockmanagementtype , stockmanagementcompany , stockmanagementamount  , product_price ,product_no)
values ('2023-03-26',2,'GOMGOM',-6,7000,3);


insert into carmanage ( carmanage_number , carmanage_name ,  carmanage_use_yn )
values ( '69우1146' , '말리부' , 'Y' ) ;

insert into bookcar ( bookcar_str_date , bookcar_end_date , bookcar_yn , carmanage_no , member_no )
values ( now() , null , 'Y' , 1 , 5 ) ;
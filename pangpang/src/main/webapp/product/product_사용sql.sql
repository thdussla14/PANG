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
values ('2023-03-26','2023-04-01',1,'GOMGOM',0 ,5000,8); 
-- 출고
insert into stockmanagement(stockmanagementdate ,stockmanagementtype , stockmanagementcompany , stockmanagementamount  , product_price ,product_no)
values ('2023-03-26',2,'GOMGOM',-2,5000,1);  
insert into stockmanagement(stockmanagementdate ,stockmanagementtype , stockmanagementcompany , stockmanagementamount  , product_price ,product_no)
values ('2023-03-26',2,'GOMGOM',-5,6000,2);
insert into stockmanagement(stockmanagementdate ,stockmanagementtype , stockmanagementcompany , stockmanagementamount  , product_price ,product_no)
values ('2023-03-26',2,'GOMGOM',-6,7000,3);
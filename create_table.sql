/*税率テーブル*/
create table T01_TAX(
    tax NUMBER PRIMARY KEY
)


/*履歴テーブル*/
create table HIST(
    HIST_ID NUMBER PRIMARY KEY,
    CUSTOMER_ID NUMBER,
    BUY_DATE DATE,
    DISCOUNT NUMBER,
    TAX NUMBER
);
/*履歴シークエンス*/
create sequence seq_hist nocache;
/*商品テーブル*/
create table goods(
goods_id NUMBER PRIMARY KEY,
goods_name VARCHAR2(30),
goods_name_kana VARCHAR2(100),
price NUMBER,
delete_flg VARCHAR2(1)
);
/*商品シークエンス*/
create sequence seq_goods nocache;
/*セールテーブル*/
create table sale(
sale_id NUMBER PRIMARY KEY,
sale_name VARCHAR2(30),
discount NUMBER,
sale_from DATE,
sale_to DATE,
delete_flg VARCHAR2(1)
);
/*セールシークエンス*/
create sequence seq_sale nocache;

/*お知らせテーブル*/
create table info(
info_id NUMBER PRIMARY KEY,
subject VARCHAR2(20),
contents VARCHAR2(200),
keisai_date DATE,
keisai_from DATE,
keisai_to DATE,
delete_flg VARCHAR2(1)
);
/*お知らせシークエンス*/
create sequence seq_info nocache;

/*権限テーブル*/
create table authority(
authority_id NUMBER PRIMARY KEY,
authority_name VARCHAR2(20),
delete_flg VARCHAR2(1) 
);
/*権限シークエンス*/
create sequence seq_authority nocache;

/*社員テーブル*/
create table employee(
employee_id NUMBER PRIMARY KEY,
employee_pw VARCHAR2(20),
employee_name_sei VARCHAR2(10),
employee_name_mei VARCHAR2(10),
employee_name_sei_kana VARCHAR2(20),
employee_name_mei_kana VARCHAR2(20),
tel_no VARCHAR2(11),
postcode_1 VARCHAR2(3),
postcode_2 VARCHAR2(4),
address VARCHAR2(100),
authority_id NUMBER REFERENCES authority(authority_id),
delete_flg VARCHAR2(1)
);
/*社員シークエンス*/
create sequence seq_employee nocache;

/*顧客テーブル*/
create table customer(
customer_id NUMBER PRIMARY KEY,
customer_name_sei VARCHAR2(10),
customer_name_mei VARCHAR2(10),
customer_name_sei_kana VARCHAR2(10),
customer_name_mei_kana VARCHAR2(10),
tell_no VARCHAR2(4),
postcode_1 VARCHAR2(3),
postcode_2 VARCHAR2(4),
address VARCHAR2(100),
delete_flg VARCHAR2(1)
);
/*顧客シークエンス*/
create sequence seq_customer nocache;
/*顧客がいない場合は顧客ID=0を入れる*/
insert into customer(customer_id) values(0);

/*メッセージテーブル*/
create table message(
id NUMBER PRIMARY KEY,
msg_id VARCHAR2(10),
msg VARCHAR2(50),
delete_flg VARCHAR2(1)
);
/*メッセージシークエンス*/
create sequence seq_id nocache;
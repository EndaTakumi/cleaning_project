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

/*履歴詳細テーブル*/
create table HISTDATAIL(
hist_id NUMBER REFERENCES HIST(hist_id),
goods_id NUMBER REFERENCES GOODS(goods_id),
count NUMBER,
price NUMBER,
PRIMARY KEY(hist_id, goods_id)
);

/*顧客テーブルにレコードを手打ち*/
insert into customer values(seq_customer.nextval, '田中', '一郎', 'タナカ', 'イチロウ', '1111', '111', '1111', '東京都', '0');
insert into customer values(seq_customer.nextval, '松本', '二郎', 'マツモト', 'ジロウ', '2222', '222', '2222', '愛知県', '0');
insert into customer values(seq_customer.nextval, '佐藤', '三郎', 'サトウ', 'サブロウ', '3333', '333', '3333', '京都府', '0');
insert into customer values(seq_customer.nextval, '小林', '四郎', 'コバヤシ', 'シロウ', '4444', '444', '4444', '大阪府', '0');
insert into customer values(seq_customer.nextval, 'ジョン', '万次郎', 'ジョン', 'マンジロウ', '5555', '555', '5555', '沖縄県', '0');

/************************** ここから下12/16更新 ************************/
/*メッセージシークエンス*/
create sequence seq_message nocache;
/*文字数制限を100にする*/
alter table message modify(
msg VARCHAR2(100)
);
/*メッセージテーブルに対して、レコードを手打ち*/
insert into message values(seq_message.nextval, 'MSG-01-001', '必須入力箇所です。', 0);
insert into message values(seq_message.nextval, 'MSG-01-002', '入力規約と異なっています。', 0);
insert into message values(seq_message.nextval, 'MSG-01-003', '登録します。よろしいですか？', 0);
insert into message values(seq_message.nextval, 'MSG-01-004', '削除します。よろしいですか？', 0);
insert into message values(seq_message.nextval, 'MSG-01-005', 'IDかパスワードが異なります。再度入力してください。', 0);
insert into message values(seq_message.nextval, 'MSG-01-006', '正常に登録できました。', 0);
insert into message values(seq_message.nextval, 'MSG-01-007', '登録できませんでした。再度やり直すか、管理者へ連絡してください。', 0);
insert into message values(seq_message.nextval, 'MSG-01-008', '1点のみ選択してください。', 0);
insert into message values(seq_message.nextval, 'MSG-01-009', '1点以上選択してください。', 0);
insert into message values(seq_message.nextval, 'MSG-01-010', '入力不備があります。', 0);
insert into message values(seq_message.nextval, 'MSG-01-011', '範囲指定が異なっています。', 0);
insert into message values(seq_message.nextval, 'MSG-01-012', '期間が他の登録と重複しています。', 0);

/*権限テーブルにレコードを追加*/
insert into authority values(seq_authority.nextval, '管理者', '0');
insert into authority values(seq_authority.nextval, '一般職員', '0');

/*社員テーブルにレコードを追加*/
insert into employee values(seq_employee.nextval, '1111', '中山', '筋肉', 'ナカヤマ', 'キンニク', '11111111111', '111', '1111', '日野市', '1', '0');
insert into employee values(seq_employee.nextval, '2222', '筋肉', 'すぐる', 'キンニク', 'スグル', '22222222222', '222', '2222', 'キン肉星', '2', '0');

/**/
alter table authority rename column authority_id to id;
alter table employee add FOREIGN KEY(authority_id) references authority(id);

/*
セールテーブルと商品テーブルインサート
2021/12/17
*/
insert into info values(SEQ_INFO.nextval, 'セールやるよ', '2021/12/01~2021/12/31の間セールやりまーす', '2021/11/01', '2021/11/01', '2021/12/31', '0');

insert into goods values(SEQ_GOODS.nextval, 'ジャケット', 'ジャケット', 300, '0');
insert into goods values(SEQ_GOODS.nextval, '布団', 'フトン', 500, '0');
insert into goods values(SEQ_GOODS.nextval, 'ダウン', 'ダウン', 300, '0');

commit;
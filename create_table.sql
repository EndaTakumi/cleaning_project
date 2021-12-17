/*�ŗ��e�[�u��*/
create table T01_TAX(
    tax NUMBER PRIMARY KEY
)


/*�����e�[�u��*/
create table HIST(
    HIST_ID NUMBER PRIMARY KEY,
    CUSTOMER_ID NUMBER,
    BUY_DATE DATE,
    DISCOUNT NUMBER,
    TAX NUMBER
);
/*�����V�[�N�G���X*/
create sequence seq_hist nocache;
/*���i�e�[�u��*/
create table goods(
goods_id NUMBER PRIMARY KEY,
goods_name VARCHAR2(30),
goods_name_kana VARCHAR2(100),
price NUMBER,
delete_flg VARCHAR2(1)
);
/*���i�V�[�N�G���X*/
create sequence seq_goods nocache;
/*�Z�[���e�[�u��*/
create table sale(
sale_id NUMBER PRIMARY KEY,
sale_name VARCHAR2(30),
discount NUMBER,
sale_from DATE,
sale_to DATE,
delete_flg VARCHAR2(1)
);
/*�Z�[���V�[�N�G���X*/
create sequence seq_sale nocache;

/*���m�点�e�[�u��*/
create table info(
info_id NUMBER PRIMARY KEY,
subject VARCHAR2(20),
contents VARCHAR2(200),
keisai_date DATE,
keisai_from DATE,
keisai_to DATE,
delete_flg VARCHAR2(1)
);
/*���m�点�V�[�N�G���X*/
create sequence seq_info nocache;

/*�����e�[�u��*/
create table authority(
authority_id NUMBER PRIMARY KEY,
authority_name VARCHAR2(20),
delete_flg VARCHAR2(1) 
);
/*�����V�[�N�G���X*/
create sequence seq_authority nocache;

/*�Ј��e�[�u��*/
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
/*�Ј��V�[�N�G���X*/
create sequence seq_employee nocache;

/*�ڋq�e�[�u��*/
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
/*�ڋq�V�[�N�G���X*/
create sequence seq_customer nocache;
/*�ڋq�����Ȃ��ꍇ�͌ڋqID=0������*/
insert into customer(customer_id) values(0);

/*���b�Z�[�W�e�[�u��*/
create table message(
id NUMBER PRIMARY KEY,
msg_id VARCHAR2(10),
msg VARCHAR2(50),
delete_flg VARCHAR2(1)
);
/*���b�Z�[�W�V�[�N�G���X*/
create sequence seq_id nocache;

/*�����ڍ׃e�[�u��*/
create table HISTDATAIL(
hist_id NUMBER REFERENCES HIST(hist_id),
goods_id NUMBER REFERENCES GOODS(goods_id),
count NUMBER,
price NUMBER,
PRIMARY KEY(hist_id, goods_id)
);

/*�ڋq�e�[�u���Ƀ��R�[�h����ł�*/
insert into customer values(seq_customer.nextval, '�c��', '��Y', '�^�i�J', '�C�`���E', '1111', '111', '1111', '�����s', '0');
insert into customer values(seq_customer.nextval, '���{', '��Y', '�}�c���g', '�W���E', '2222', '222', '2222', '���m��', '0');
insert into customer values(seq_customer.nextval, '����', '�O�Y', '�T�g�E', '�T�u���E', '3333', '333', '3333', '���s�{', '0');
insert into customer values(seq_customer.nextval, '����', '�l�Y', '�R�o���V', '�V���E', '4444', '444', '4444', '���{', '0');
insert into customer values(seq_customer.nextval, '�W����', '�����Y', '�W����', '�}���W���E', '5555', '555', '5555', '���ꌧ', '0');

/************************** �������牺12/16�X�V ************************/
/*���b�Z�[�W�V�[�N�G���X*/
create sequence seq_message nocache;
/*������������100�ɂ���*/
alter table message modify(
msg VARCHAR2(100)
);
/*���b�Z�[�W�e�[�u���ɑ΂��āA���R�[�h����ł�*/
insert into message values(seq_message.nextval, 'MSG-01-001', '�K�{���͉ӏ��ł��B', 0);
insert into message values(seq_message.nextval, 'MSG-01-002', '���͋K��ƈقȂ��Ă��܂��B', 0);
insert into message values(seq_message.nextval, 'MSG-01-003', '�o�^���܂��B��낵���ł����H', 0);
insert into message values(seq_message.nextval, 'MSG-01-004', '�폜���܂��B��낵���ł����H', 0);
insert into message values(seq_message.nextval, 'MSG-01-005', 'ID���p�X���[�h���قȂ�܂��B�ēx���͂��Ă��������B', 0);
insert into message values(seq_message.nextval, 'MSG-01-006', '����ɓo�^�ł��܂����B', 0);
insert into message values(seq_message.nextval, 'MSG-01-007', '�o�^�ł��܂���ł����B�ēx��蒼�����A�Ǘ��҂֘A�����Ă��������B', 0);
insert into message values(seq_message.nextval, 'MSG-01-008', '1�_�̂ݑI�����Ă��������B', 0);
insert into message values(seq_message.nextval, 'MSG-01-009', '1�_�ȏ�I�����Ă��������B', 0);
insert into message values(seq_message.nextval, 'MSG-01-010', '���͕s��������܂��B', 0);
insert into message values(seq_message.nextval, 'MSG-01-011', '�͈͎w�肪�قȂ��Ă��܂��B', 0);
insert into message values(seq_message.nextval, 'MSG-01-012', '���Ԃ����̓o�^�Əd�����Ă��܂��B', 0);

/*�����e�[�u���Ƀ��R�[�h��ǉ�*/
insert into authority values(seq_authority.nextval, '�Ǘ���', '0');
insert into authority values(seq_authority.nextval, '��ʐE��', '0');

/*�Ј��e�[�u���Ƀ��R�[�h��ǉ�*/
insert into employee values(seq_employee.nextval, '1111', '���R', '�ؓ�', '�i�J���}', '�L���j�N', '11111111111', '111', '1111', '����s', '1', '0');
insert into employee values(seq_employee.nextval, '2222', '�ؓ�', '������', '�L���j�N', '�X�O��', '22222222222', '222', '2222', '�L������', '2', '0');

/**/
alter table authority rename column authority_id to id;
alter table employee add FOREIGN KEY(authority_id) references authority(id);

/*
�Z�[���e�[�u���Ə��i�e�[�u���C���T�[�g
2021/12/17
*/
insert into info values(SEQ_INFO.nextval, '�Z�[������', '2021/12/01~2021/12/31�̊ԃZ�[�����܁[��', '2021/11/01', '2021/11/01', '2021/12/31', '0');

insert into goods values(SEQ_GOODS.nextval, '�W���P�b�g', '�W���P�b�g', 300, '0');
insert into goods values(SEQ_GOODS.nextval, '�z�c', '�t�g��', 500, '0');
insert into goods values(SEQ_GOODS.nextval, '�_�E��', '�_�E��', 300, '0');

commit;
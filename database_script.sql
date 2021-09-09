--------------------------------------------------------
--  File created - Wednesday-June-30-2021   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Table AUTOR
--------------------------------------------------------

  CREATE TABLE "BIBLIOTEKA"."AUTOR" 
   (	"IDAUTOR" NUMBER(*,0), 
	"IME" VARCHAR2(20 BYTE), 
	"PRZ" VARCHAR2(20 BYTE), 
	"SLIKA" VARCHAR2(50 BYTE)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table MESTO
--------------------------------------------------------

  CREATE TABLE "BIBLIOTEKA"."MESTO" 
   (	"PTTBROJ" NUMBER(*,0), 
	"NAZIVMESTA" VARCHAR2(30 BYTE)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table POZAJMICA
--------------------------------------------------------

  CREATE TABLE "BIBLIOTEKA"."POZAJMICA" 
   (	"EMAIL" VARCHAR2(30 BYTE), 
	"SIFRAPR" NUMBER(*,0), 
	"DATUMPOZ" DATE, 
	"DATUMVR" DATE, 
	"OSTECENA" NUMBER(1,0), 
	"PRODUZENO" NUMBER(1,0)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table REZERVACIJA
--------------------------------------------------------

  CREATE TABLE "BIBLIOTEKA"."REZERVACIJA" 
   (	"EMAIL" VARCHAR2(30 BYTE), 
	"ISBN" VARCHAR2(20 BYTE), 
	"DATUMZAH" DATE, 
	"SIFRAPR" NUMBER(*,0), 
	"DATUMREZ" DATE
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table OGRANICENJAPOTIPUCLANA
--------------------------------------------------------

  CREATE TABLE "BIBLIOTEKA"."OGRANICENJAPOTIPUCLANA" 
   (	"TIPCLANA" VARCHAR2(15 BYTE), 
	"MAXPOZAJMICA" NUMBER(*,0), 
	"ROKVRACANJA" NUMBER(*,0)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table CLAN
--------------------------------------------------------

  CREATE TABLE "BIBLIOTEKA"."CLAN" 
   (	"EMAIL" VARCHAR2(30 BYTE), 
	"LOZINKA" VARCHAR2(50 BYTE), 
	"IME" VARCHAR2(20 BYTE), 
	"PRZ" VARCHAR2(20 BYTE), 
	"JMBG" VARCHAR2(13 BYTE), 
	"DATUMRODJ" DATE, 
	"ADRESA" VARCHAR2(50 BYTE), 
	"MESTOF" NUMBER(*,0), 
	"TIPCLANA" VARCHAR2(15 BYTE), 
	"DATUMUPLATE" DATE
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table FILIJALA
--------------------------------------------------------

  CREATE TABLE "BIBLIOTEKA"."FILIJALA" 
   (	"NAZIVF" VARCHAR2(40 BYTE), 
	"ADRESA" VARCHAR2(50 BYTE), 
	"NAZIVBIBLIOTEKA" VARCHAR2(40 BYTE), 
	"RADNIDANI" VARCHAR2(15 BYTE), 
	"SUBOTA" VARCHAR2(15 BYTE), 
	"MESTOF" NUMBER(*,0)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table KNJIGA
--------------------------------------------------------

  CREATE TABLE "BIBLIOTEKA"."KNJIGA" 
   (	"ISBN" VARCHAR2(20 BYTE), 
	"NASLOV" VARCHAR2(50 BYTE), 
	"GODIZDAVANJA" VARCHAR2(5 BYTE), 
	"GODSTAMPANJA" VARCHAR2(5 BYTE), 
	"IZDAVAC" VARCHAR2(30 BYTE), 
	"ZANR" VARCHAR2(20 BYTE), 
	"FORMATK" VARCHAR2(20 BYTE), 
	"TIP" VARCHAR2(20 BYTE), 
	"OCENA" NUMBER(5,2), 
	"SLIKA" VARCHAR2(50 BYTE), 
	"KLJUCNERECI" VARCHAR2(100 BYTE), 
	"OPIS" VARCHAR2(1000 BYTE), 
	"OBRISANA" NUMBER(1,0)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table PRIMERAK
--------------------------------------------------------

  CREATE TABLE "BIBLIOTEKA"."PRIMERAK" 
   (	"SIFRAPR" NUMBER(*,0), 
	"SLOBODAN" NUMBER(1,0), 
	"ISBN" VARCHAR2(20 BYTE)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table BIBLIOTEKAR
--------------------------------------------------------

  CREATE TABLE "BIBLIOTEKA"."BIBLIOTEKAR" 
   (	"EMAIL" VARCHAR2(30 BYTE), 
	"LOZINKA" VARCHAR2(50 BYTE), 
	"IME" VARCHAR2(20 BYTE), 
	"PRZ" VARCHAR2(20 BYTE), 
	"JMBG" VARCHAR2(13 BYTE), 
	"DATUMRODJ" DATE, 
	"ADRESA" VARCHAR2(50 BYTE), 
	"MESTOF" NUMBER(*,0), 
	"TIPZAPOSLENOG" VARCHAR2(15 BYTE), 
	"NAZIVFILIJALE" VARCHAR2(30 BYTE)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table ZAHTEVZAREZERVACIJU
--------------------------------------------------------

  CREATE TABLE "BIBLIOTEKA"."ZAHTEVZAREZERVACIJU" 
   (	"EMAIL" VARCHAR2(30 BYTE), 
	"ISBN" VARCHAR2(20 BYTE), 
	"DATUMZAH" DATE
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table RECENZIJA
--------------------------------------------------------

  CREATE TABLE "BIBLIOTEKA"."RECENZIJA" 
   (	"EMAIL" VARCHAR2(30 BYTE), 
	"ISBN" VARCHAR2(20 BYTE), 
	"OCENA" NUMBER(*,0)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table MESTOPRIMERKA
--------------------------------------------------------

  CREATE TABLE "BIBLIOTEKA"."MESTOPRIMERKA" 
   (	"SIFRAPR" NUMBER(*,0), 
	"NAZIVF" VARCHAR2(40 BYTE), 
	"BROJPOLICE" NUMBER(*,0), 
	"BROJREDA" NUMBER(*,0)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table AUTORKNJIGE
--------------------------------------------------------

  CREATE TABLE "BIBLIOTEKA"."AUTORKNJIGE" 
   (	"ISBN" VARCHAR2(20 BYTE), 
	"IDAUTOR" NUMBER(*,0)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table BIBLIOTEKA
--------------------------------------------------------

  CREATE TABLE "BIBLIOTEKA"."BIBLIOTEKA" 
   (	"NAZIVB" VARCHAR2(40 BYTE)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
REM INSERTING into BIBLIOTEKA.AUTOR
SET DEFINE OFF;
Insert into BIBLIOTEKA.AUTOR (IDAUTOR,IME,PRZ,SLIKA) values (1,'Lav','Tolstoj','lavTolstoj.png');
Insert into BIBLIOTEKA.AUTOR (IDAUTOR,IME,PRZ,SLIKA) values (2,'Mark','Tven','markTven.png');
Insert into BIBLIOTEKA.AUTOR (IDAUTOR,IME,PRZ,SLIKA) values (3,'Fjodor','M. Dostojevski','dostojevski.png');
Insert into BIBLIOTEKA.AUTOR (IDAUTOR,IME,PRZ,SLIKA) values (4,'Vilijam','Sekspir','vilS.png');
Insert into BIBLIOTEKA.AUTOR (IDAUTOR,IME,PRZ,SLIKA) values (5,'Anton','Pavlovic Cehov','cehov.png');
Insert into BIBLIOTEKA.AUTOR (IDAUTOR,IME,PRZ,SLIKA) values (6,'Dante','Aligijeri','dante.png');
Insert into BIBLIOTEKA.AUTOR (IDAUTOR,IME,PRZ,SLIKA) values (7,'Ivo','Andric','ivoAndric.png');
Insert into BIBLIOTEKA.AUTOR (IDAUTOR,IME,PRZ,SLIKA) values (8,'Milos','Crnjanski','crnjanski.png');
Insert into BIBLIOTEKA.AUTOR (IDAUTOR,IME,PRZ,SLIKA) values (9,'Carls','Bukovski','bukovski.png');
Insert into BIBLIOTEKA.AUTOR (IDAUTOR,IME,PRZ,SLIKA) values (10,'Borislav','Stankovic','borislavStankovic.png');
Insert into BIBLIOTEKA.AUTOR (IDAUTOR,IME,PRZ,SLIKA) values (11,'Dobrica','Cosic','dobricaCosic.png');
Insert into BIBLIOTEKA.AUTOR (IDAUTOR,IME,PRZ,SLIKA) values (12,'Jovan','Jovanovic Zmaj','zmaj.png');
Insert into BIBLIOTEKA.AUTOR (IDAUTOR,IME,PRZ,SLIKA) values (13,'Desanka','Maksimovic','desankaM.png');
Insert into BIBLIOTEKA.AUTOR (IDAUTOR,IME,PRZ,SLIKA) values (14,'Danilo','Kis','daniloKis.png');
Insert into BIBLIOTEKA.AUTOR (IDAUTOR,IME,PRZ,SLIKA) values (15,'Branislav','Nusic','branislavNusic.png');
Insert into BIBLIOTEKA.AUTOR (IDAUTOR,IME,PRZ,SLIKA) values (16,'Ernest','Hemingvej','hemingvej.png');
Insert into BIBLIOTEKA.AUTOR (IDAUTOR,IME,PRZ,SLIKA) values (17,'Antoan','de Sent Egziperi','antoanDeSent.png');
Insert into BIBLIOTEKA.AUTOR (IDAUTOR,IME,PRZ,SLIKA) values (18,'Vojin','Anicic','vojinA.png');
Insert into BIBLIOTEKA.AUTOR (IDAUTOR,IME,PRZ,SLIKA) values (19,'Javor','Rasajski','javorRasajski.png');
Insert into BIBLIOTEKA.AUTOR (IDAUTOR,IME,PRZ,SLIKA) values (20,'Carol','Dweck','carolDwerck.png');
Insert into BIBLIOTEKA.AUTOR (IDAUTOR,IME,PRZ,SLIKA) values (21,'Viktor','E.Frankl','viktorFrankl.png');
Insert into BIBLIOTEKA.AUTOR (IDAUTOR,IME,PRZ,SLIKA) values (22,'Juval','Noan Harari','yuvalHarari.png');
Insert into BIBLIOTEKA.AUTOR (IDAUTOR,IME,PRZ,SLIKA) values (23,'Robert','Kiosaki','robertKiosaki.png');
Insert into BIBLIOTEKA.AUTOR (IDAUTOR,IME,PRZ,SLIKA) values (24,'Nikolas','Sparks','nikolasSparks.png');
Insert into BIBLIOTEKA.AUTOR (IDAUTOR,IME,PRZ,SLIKA) values (25,'Isidora','Sekulic','isidoraSekulic.png');
REM INSERTING into BIBLIOTEKA.MESTO
SET DEFINE OFF;
Insert into BIBLIOTEKA.MESTO (PTTBROJ,NAZIVMESTA) values (21000,'Novi Sad');
Insert into BIBLIOTEKA.MESTO (PTTBROJ,NAZIVMESTA) values (22400,'Ruma');
Insert into BIBLIOTEKA.MESTO (PTTBROJ,NAZIVMESTA) values (12000,'Pozarevac');
REM INSERTING into BIBLIOTEKA.POZAJMICA
SET DEFINE OFF;
Insert into BIBLIOTEKA.POZAJMICA (EMAIL,SIFRAPR,DATUMPOZ,DATUMVR,OSTECENA,PRODUZENO) values ('zoka@gmail.com',2,to_date('14-JUN-21','DD-MON-RR'),to_date('28-JUN-21','DD-MON-RR'),0,0);
Insert into BIBLIOTEKA.POZAJMICA (EMAIL,SIFRAPR,DATUMPOZ,DATUMVR,OSTECENA,PRODUZENO) values ('nevena@gmail.com',3,to_date('16-JUN-21','DD-MON-RR'),to_date('27-JUN-21','DD-MON-RR'),0,0);
Insert into BIBLIOTEKA.POZAJMICA (EMAIL,SIFRAPR,DATUMPOZ,DATUMVR,OSTECENA,PRODUZENO) values ('miha@gmail.com',4,to_date('25-MAY-21','DD-MON-RR'),to_date('10-JUN-21','DD-MON-RR'),1,0);
Insert into BIBLIOTEKA.POZAJMICA (EMAIL,SIFRAPR,DATUMPOZ,DATUMVR,OSTECENA,PRODUZENO) values ('sofi@gmail.com',5,to_date('10-JUN-21','DD-MON-RR'),to_date('25-JUN-21','DD-MON-RR'),0,0);
Insert into BIBLIOTEKA.POZAJMICA (EMAIL,SIFRAPR,DATUMPOZ,DATUMVR,OSTECENA,PRODUZENO) values ('milan@gmail.com',6,to_date('11-JUN-21','DD-MON-RR'),to_date('02-JUL-21','DD-MON-RR'),0,0);
Insert into BIBLIOTEKA.POZAJMICA (EMAIL,SIFRAPR,DATUMPOZ,DATUMVR,OSTECENA,PRODUZENO) values ('joca@gmail.com',7,to_date('13-JUN-21','DD-MON-RR'),to_date('13-JUL-21','DD-MON-RR'),0,0);
Insert into BIBLIOTEKA.POZAJMICA (EMAIL,SIFRAPR,DATUMPOZ,DATUMVR,OSTECENA,PRODUZENO) values ('zoka@gmail.com',1,to_date('20-JUL-21','DD-MON-RR'),to_date('28-JUN-21','DD-MON-RR'),0,1);
Insert into BIBLIOTEKA.POZAJMICA (EMAIL,SIFRAPR,DATUMPOZ,DATUMVR,OSTECENA,PRODUZENO) values ('nevena@gmail.com',8,to_date('30-JUL-21','DD-MON-RR'),to_date('30-JUN-21','DD-MON-RR'),0,1);
Insert into BIBLIOTEKA.POZAJMICA (EMAIL,SIFRAPR,DATUMPOZ,DATUMVR,OSTECENA,PRODUZENO) values ('nevena@gmail.com',31,to_date('30-JUN-21','DD-MON-RR'),null,null,null);
Insert into BIBLIOTEKA.POZAJMICA (EMAIL,SIFRAPR,DATUMPOZ,DATUMVR,OSTECENA,PRODUZENO) values ('nevena@gmail.com',39,to_date('30-JUN-21','DD-MON-RR'),null,null,null);
Insert into BIBLIOTEKA.POZAJMICA (EMAIL,SIFRAPR,DATUMPOZ,DATUMVR,OSTECENA,PRODUZENO) values ('miha@gmail.com',34,to_date('30-JUN-21','DD-MON-RR'),null,null,null);
Insert into BIBLIOTEKA.POZAJMICA (EMAIL,SIFRAPR,DATUMPOZ,DATUMVR,OSTECENA,PRODUZENO) values ('miha@gmail.com',25,to_date('30-JUN-21','DD-MON-RR'),to_date('30-JUN-21','DD-MON-RR'),1,null);
Insert into BIBLIOTEKA.POZAJMICA (EMAIL,SIFRAPR,DATUMPOZ,DATUMVR,OSTECENA,PRODUZENO) values ('miha@gmail.com',18,to_date('30-JUN-21','DD-MON-RR'),null,null,null);
Insert into BIBLIOTEKA.POZAJMICA (EMAIL,SIFRAPR,DATUMPOZ,DATUMVR,OSTECENA,PRODUZENO) values ('miha@gmail.com',41,to_date('30-JUN-21','DD-MON-RR'),null,null,null);
Insert into BIBLIOTEKA.POZAJMICA (EMAIL,SIFRAPR,DATUMPOZ,DATUMVR,OSTECENA,PRODUZENO) values ('miha@gmail.com',22,to_date('30-JUN-21','DD-MON-RR'),null,null,null);
REM INSERTING into BIBLIOTEKA.REZERVACIJA
SET DEFINE OFF;
Insert into BIBLIOTEKA.REZERVACIJA (EMAIL,ISBN,DATUMZAH,SIFRAPR,DATUMREZ) values ('zoka@gmail.com','978-86-52-12215-8',to_date('30-JUN-21','DD-MON-RR'),39,to_date('30-JUN-21','DD-MON-RR'));
Insert into BIBLIOTEKA.REZERVACIJA (EMAIL,ISBN,DATUMZAH,SIFRAPR,DATUMREZ) values ('joca@gmail.com','978-86-10-01769-4',to_date('14-JUN-21','DD-MON-RR'),10,to_date('30-JUN-21','DD-MON-RR'));
Insert into BIBLIOTEKA.REZERVACIJA (EMAIL,ISBN,DATUMZAH,SIFRAPR,DATUMREZ) values ('nevena@gmail.com','978-86-81-13124-4',to_date('10-JUN-21','DD-MON-RR'),17,to_date('30-JUN-21','DD-MON-RR'));
Insert into BIBLIOTEKA.REZERVACIJA (EMAIL,ISBN,DATUMZAH,SIFRAPR,DATUMREZ) values ('zoka@gmail.com','978-86-75-80306-5',to_date('23-JUN-21','DD-MON-RR'),13,to_date('30-JUN-21','DD-MON-RR'));
Insert into BIBLIOTEKA.REZERVACIJA (EMAIL,ISBN,DATUMZAH,SIFRAPR,DATUMREZ) values ('nevena@gmail.com','978-86-75-80306-5',to_date('30-JUN-21','DD-MON-RR'),13,to_date('30-JUN-21','DD-MON-RR'));
REM INSERTING into BIBLIOTEKA.OGRANICENJAPOTIPUCLANA
SET DEFINE OFF;
Insert into BIBLIOTEKA.OGRANICENJAPOTIPUCLANA (TIPCLANA,MAXPOZAJMICA,ROKVRACANJA) values ('PREDSKOLAC',3,15);
Insert into BIBLIOTEKA.OGRANICENJAPOTIPUCLANA (TIPCLANA,MAXPOZAJMICA,ROKVRACANJA) values ('DJAK',3,15);
Insert into BIBLIOTEKA.OGRANICENJAPOTIPUCLANA (TIPCLANA,MAXPOZAJMICA,ROKVRACANJA) values ('STUDENT',3,15);
Insert into BIBLIOTEKA.OGRANICENJAPOTIPUCLANA (TIPCLANA,MAXPOZAJMICA,ROKVRACANJA) values ('ZAPOSLENI',5,15);
Insert into BIBLIOTEKA.OGRANICENJAPOTIPUCLANA (TIPCLANA,MAXPOZAJMICA,ROKVRACANJA) values ('PENZIONER',3,21);
Insert into BIBLIOTEKA.OGRANICENJAPOTIPUCLANA (TIPCLANA,MAXPOZAJMICA,ROKVRACANJA) values ('POCASNI',10,30);
REM INSERTING into BIBLIOTEKA.CLAN
SET DEFINE OFF;
Insert into BIBLIOTEKA.CLAN (EMAIL,LOZINKA,IME,PRZ,JMBG,DATUMRODJ,ADRESA,MESTOF,TIPCLANA,DATUMUPLATE) values ('zoka@gmail.com','zoka','Zorica','Vukovic','1234567890111',to_date('15-MAR-00','DD-MON-RR'),'Dusana Jerkovica 79',22400,'STUDENT',to_date('25-JUN-21','DD-MON-RR'));
Insert into BIBLIOTEKA.CLAN (EMAIL,LOZINKA,IME,PRZ,JMBG,DATUMRODJ,ADRESA,MESTOF,TIPCLANA,DATUMUPLATE) values ('nevena@gmail.com','krivesuzvezde','Nevena','Prokic','1234567890222',to_date('02-JUL-00','DD-MON-RR'),'Kosovska 30',12000,'POCASNI',to_date('20-JUN-21','DD-MON-RR'));
Insert into BIBLIOTEKA.CLAN (EMAIL,LOZINKA,IME,PRZ,JMBG,DATUMRODJ,ADRESA,MESTOF,TIPCLANA,DATUMUPLATE) values ('miha@gmail.com','zelenovolimtezeleno','Mihal','Sabados','1234567890333',to_date('24-MAY-00','DD-MON-RR'),'Slovacka 1',21000,'ZAPOSLENI',to_date('20-JUN-21','DD-MON-RR'));
Insert into BIBLIOTEKA.CLAN (EMAIL,LOZINKA,IME,PRZ,JMBG,DATUMRODJ,ADRESA,MESTOF,TIPCLANA,DATUMUPLATE) values ('joca@gmail.com','ranoranioc','Jovan','Jovancevic','1234567890444',to_date('07-AUG-00','DD-MON-RR'),'Kralja Petra I 85',21000,'POCASNI',to_date('20-JUN-21','DD-MON-RR'));
Insert into BIBLIOTEKA.CLAN (EMAIL,LOZINKA,IME,PRZ,JMBG,DATUMRODJ,ADRESA,MESTOF,TIPCLANA,DATUMUPLATE) values ('mila@gmail.com','cicimici','Mila','Vulevic','1234567890555',to_date('14-JUN-13','DD-MON-RR'),'Kosovska 1',21000,'PREDSKOLAC',to_date('20-JUN-21','DD-MON-RR'));
Insert into BIBLIOTEKA.CLAN (EMAIL,LOZINKA,IME,PRZ,JMBG,DATUMRODJ,ADRESA,MESTOF,TIPCLANA,DATUMUPLATE) values ('sofi@gmail.com','soficvetak','Sofia','Trifunovic','1234567890666',to_date('05-DEC-06','DD-MON-RR'),'Bosanska 40',21000,'DJAK',to_date('20-JUN-21','DD-MON-RR'));
Insert into BIBLIOTEKA.CLAN (EMAIL,LOZINKA,IME,PRZ,JMBG,DATUMRODJ,ADRESA,MESTOF,TIPCLANA,DATUMUPLATE) values ('milan@gmail.com','milanistoricar','Milan','Martinovic','1234567890777',to_date('12-AUG-59','DD-MON-RR'),'Glavna 19',22400,'PENZIONER',to_date('20-JUN-21','DD-MON-RR'));
Insert into BIBLIOTEKA.CLAN (EMAIL,LOZINKA,IME,PRZ,JMBG,DATUMRODJ,ADRESA,MESTOF,TIPCLANA,DATUMUPLATE) values ('taca@gmail.com','tacamaca','Tamara','Maric','1234567890888',to_date('03-JAN-98','DD-MON-RR'),'Lasla Gala 45',21000,'STUDENT',to_date('20-JUN-21','DD-MON-RR'));
Insert into BIBLIOTEKA.CLAN (EMAIL,LOZINKA,IME,PRZ,JMBG,DATUMRODJ,ADRESA,MESTOF,TIPCLANA,DATUMUPLATE) values ('stefke@gmail.com','ljudskimaksimum','Stefan','Martinovic','1234567890999',to_date('21-JUN-97','DD-MON-RR'),'Deligradska 43',12000,'ZAPOSLENI',to_date('20-JUN-21','DD-MON-RR'));
Insert into BIBLIOTEKA.CLAN (EMAIL,LOZINKA,IME,PRZ,JMBG,DATUMRODJ,ADRESA,MESTOF,TIPCLANA,DATUMUPLATE) values ('pera@gmail.com','pera','Pera','Peric','1234567890000',to_date('02-JUN-93','DD-MON-RR'),'Narodnog fronta 2',21000,'ZAPOSLENI',to_date('20-JUN-21','DD-MON-RR'));
REM INSERTING into BIBLIOTEKA.FILIJALA
SET DEFINE OFF;
Insert into BIBLIOTEKA.FILIJALA (NAZIVF,ADRESA,NAZIVBIBLIOTEKA,RADNIDANI,SUBOTA,MESTOF) values ('Djura Danicic','Dunavska 1','Gradska biblioteka SIMS','08:30-18:00','09:00-12:00',21000);
Insert into BIBLIOTEKA.FILIJALA (NAZIVF,ADRESA,NAZIVBIBLIOTEKA,RADNIDANI,SUBOTA,MESTOF) values ('Anica Savic Rebac','Trg Republike 2','Gradska biblioteka SIMS','09:00-14:00','09:00-14:00',21000);
Insert into BIBLIOTEKA.FILIJALA (NAZIVF,ADRESA,NAZIVBIBLIOTEKA,RADNIDANI,SUBOTA,MESTOF) values ('Stevan Sremac','Narodnih heroja 5','Gradska biblioteka SIMS','07:30-16:00','09:00-12:00',21000);
Insert into BIBLIOTEKA.FILIJALA (NAZIVF,ADRESA,NAZIVBIBLIOTEKA,RADNIDANI,SUBOTA,MESTOF) values ('Danilo Kis','Narodnog fronta 47','Gradska biblioteka SIMS','07:30-20:00','08:00-14:00',21000);
Insert into BIBLIOTEKA.FILIJALA (NAZIVF,ADRESA,NAZIVBIBLIOTEKA,RADNIDANI,SUBOTA,MESTOF) values ('Zarko Zrenjanin','Dusana Danilovica 12','Gradska biblioteka SIMS','07:30-20:00','08:00-14:00',21000);
Insert into BIBLIOTEKA.FILIJALA (NAZIVF,ADRESA,NAZIVBIBLIOTEKA,RADNIDANI,SUBOTA,MESTOF) values ('Petefi Sandor','Jozefa Atile 16','Gradska biblioteka SIMS','09:00-18:00','09:00-12:00',21000);
Insert into BIBLIOTEKA.FILIJALA (NAZIVF,ADRESA,NAZIVBIBLIOTEKA,RADNIDANI,SUBOTA,MESTOF) values ('Kosta Trifkovic','Brace Mogin 2','Gradska biblioteka SIMS','07:30-20:00','08:00-14:00',21000);
Insert into BIBLIOTEKA.FILIJALA (NAZIVF,ADRESA,NAZIVBIBLIOTEKA,RADNIDANI,SUBOTA,MESTOF) values ('Vladimir Nazor','Koste Nadja 1','Gradska biblioteka SIMS','09:00-19:30','09:00-12:00',21000);
Insert into BIBLIOTEKA.FILIJALA (NAZIVF,ADRESA,NAZIVBIBLIOTEKA,RADNIDANI,SUBOTA,MESTOF) values ('Ilija M. Petrovic','Drinska 2','Gradska biblioteka SIMS','08:00-20:00','08:00-13:00',12000);
Insert into BIBLIOTEKA.FILIJALA (NAZIVF,ADRESA,NAZIVBIBLIOTEKA,RADNIDANI,SUBOTA,MESTOF) values ('Anastasije Stojkovic','Glavna 131','Gradska biblioteka SIMS','07:00-19:00','08:00-13:00',22400);
REM INSERTING into BIBLIOTEKA.KNJIGA
SET DEFINE OFF;
Insert into BIBLIOTEKA.KNJIGA (ISBN,NASLOV,GODIZDAVANJA,GODSTAMPANJA,IZDAVAC,ZANR,FORMATK,TIP,OCENA,SLIKA,KLJUCNERECI,OPIS,OBRISANA) values ('978-86-52-12078-9','Koreni','1954.','2015.','Laguna','ISTORIJSKI','13x20cm','OBICNA',0,'koreniDC.png','prerovo porodica sukob politika vikasin djordje','Smeštena u vreme politi?kih promena i previranja srpskog gra?anskog društva s kraja XIX i po?etkom XX veka, pri?a prati živote A?ima Kati?a, ?oveka tradicionalnog kova i radikala, i njegovih sinova Vukašina i ?or?a. Budu?i politi?ar i o?eva nada, Vukašin se vra?a sa studija u Parizu i saopštava da se ženi ?erkom liberala Toši?a, A?imovog politi?kog neprijatelja, i da prelazi u njegovu stranku. A?im ga se odri?e i, besan zbog sukoba sa sinom, poziva meštane u selu na dizanje bune. Drugi sin ?or?e, ugledan i bogat seljak, i njegova supruga Simka u isto vreme imaju bra?ne probleme jer godinama nemaju dece. U strahu od gašenja loze, Simka se odlu?uje na o?ajni?ki potez: da zatrudni sa ?or?evim slugom.

Da li ?e A?im i ?or?e prihvatiti dete kao svoju krv, svoje korene? I da li ?e se Vukašin pomiriti sa ocem ili ?e politika potpuno razoriti porodi?ne odnose?',0);
Insert into BIBLIOTEKA.KNJIGA (ISBN,NASLOV,GODIZDAVANJA,GODSTAMPANJA,IZDAVAC,ZANR,FORMATK,TIP,OCENA,SLIKA,KLJUCNERECI,OPIS,OBRISANA) values ('978-86-52-12490-9','Deobe','1961.','2017.','Laguna','ISTORIJSKI','13x16cm','OBICNA',0,'deobe.png','prerovo porodica drugi deo partizani cetnici','Jedno od najzna?ajnijih dela srpske književnosti posle Drugog svetskog rata, ?osi?eve Deobe su izazvale veliku pažnju i ?italaca i kritike zbog svoje tematskog i formalnog iskoraka u odnosu na tadašnje književne tokove. Naizgled istorijska epopeja o ravnogorskom pokretu u Srbiji, roman je zapravo metafora o podelama u istoriji i tradiciji srpskog naroda, o sudaru revolucije i zlo?ina, nacionalnog mita i južnoslovenskih ideala, partizana i ?etnika, selja?kog i gra?anskog... Osim tematskog preokreta u dotadašnjoj književnosti socijalisti?kog realizma, Deobe su se izdvojile i osobenom kompozicijom, razgranatim unutrašnjim monologom, višeglasjem kao govorom društvenih masa u ulozi glavnog junaka, interpolacijom dokumentarnog materijala, stilskom raznovrsnoš?u i leksi?kim bogatstvom. Ovim romanom ?osi? je 1961. godine postao dobitnik druge Ninove nagrade.',0);
Insert into BIBLIOTEKA.KNJIGA (ISBN,NASLOV,GODIZDAVANJA,GODSTAMPANJA,IZDAVAC,ZANR,FORMATK,TIP,OCENA,SLIKA,KLJUCNERECI,OPIS,OBRISANA) values ('125-46-35-44332-6','Rani jadi','1970.','2018.','Arhipelag','DRAMA','17x21cm','OBICNA',2,'raniJadi.png','zbirka pripovedaka decak andreas verenici jesen macici ','Pri?e velikog pisca, modernog klasika srpske književnosti, o odrastanju i detinjstvu. Pri?e za decu i osetljive. Knjiga za lektiru i za uživanje. “Rani jadi su lirski. Pri?e su sa?injene od prizora iz mojeg detinjstva, slika koje nisu mogle da na?u mesto u romanu Bašta, pepeo. Svaka pri?a je gotovo pesma u prozi. Rani jadi su kratke pri?e, svet vi?en o?ima deteta.” Danilo Kiš (1984)',0);
Insert into BIBLIOTEKA.KNJIGA (ISBN,NASLOV,GODIZDAVANJA,GODSTAMPANJA,IZDAVAC,ZANR,FORMATK,TIP,OCENA,SLIKA,KLJUCNERECI,OPIS,OBRISANA) values ('978-86-52-30005-1','Enciklopedija mrtvih','1983.','2015.','Arhipelag','DRAMA','14x21cm','OBICNA',0,'enciklopedijaM.png','smrt ljubav eros tanatos ','Enciklopedija mrtvih prvi put je objavljena 1983. godine u okviru tadašnjeg izdanja Dela Danila Kiša.
Prevedena na sve ve?e svetske jezike, ova knjiga je ostala trajna inspiracija generacijama ?italaca i tuma?a moderne književnosti. O ovoj Kišovoj knjizi napisana je ?itava jedna biblioteka studija i kriti?kih tekstova.
Novele u knjizi Enciklopedija mrtvih, kako je sam pisac govorio, „u znaku su jedne teme koju bih nazvao metafizi?kom; od speva o Gilgamešu, pitanje smrti jedna je od opsesivnih tema literature“.
Pripovedaju?i o smrti, Kiš u Enciklopedija mrtvih ovu veliku temu prikazuje u razli?itim istorijskim, kulturnim, društvenim, saznajnim i ideološkim kontekstima: od gnosti?kih legendi do modernih vremena, od legendarnih spava?a do svevremene enciklopedije u kojoj se nalaze zapisani životi svih ljudi, od pri?e o predanjima koja ispred puk i o fantaziranjima književnika do povesti o Protokolu Sionskih mudraca.',0);
Insert into BIBLIOTEKA.KNJIGA (ISBN,NASLOV,GODIZDAVANJA,GODSTAMPANJA,IZDAVAC,ZANR,FORMATK,TIP,OCENA,SLIKA,KLJUCNERECI,OPIS,OBRISANA) values ('978-86-52-30002-0','Basta, pepeo','1965.','2016.','Arhipelag','DRAMA','14x21cm','OBICNA',0,'bastaP.png','detinstvo odrastanje otac roman andreas','Najpoznatiji roman Danila Kiša, veliko delo o detinjstvu i odrastanju.
Uzbudljiva slika jednog sveta data u mo?noj lirskoj svetlosti i u suo?enju s najve?im izazovima svog postojanja.

“U romanu Bašta, pepeo radi se o metafori, o strahopoštovanju deteta prema ocu. Otac je uvek veli?ina. To je skoro frojdovski problem: tokom izvesnog perioda, otac predstavlja kralja u odnosu na dete, on je omnipotentan. U romanu Bašta, pepeo hteo sam da razvijem tu metaforu sa idejom da jednog dana još nešto napišem o ocu.”
Danilo Kiš (1984)',0);
Insert into BIBLIOTEKA.KNJIGA (ISBN,NASLOV,GODIZDAVANJA,GODSTAMPANJA,IZDAVAC,ZANR,FORMATK,TIP,OCENA,SLIKA,KLJUCNERECI,OPIS,OBRISANA) values ('978-86-10-00127-3','Ana Karenjina','1877.','2020.','Vulkan izdavastvo','KLASICI','13x20cm','OBICNA',0,'nema.png','roman ljubav prevara voz samoubistvo rusija tragedija ','„Najbolja knjiga svih vremena.“ Washington Post

Sve sre?ne porodice li?e jedna na drugu, svaka nesre?na porodica nesre?na je na svoj na?in.

Roman koji je Fjodor Dostojevski smatrao besprekornim, a Vilijam Fokner nazvao najboljim romanom koji je ikada napisan, Ana Karenjina je monumentalno delo Lava Tolstoja koji daje sveobuhvatan prikaz ruskog društva devetnaestog veka, od aristokratskih salona do seoskih gazdinstava. Uvode?i dva narativna toka, prvi koji prati ljubavnu pri?u izme?u Ane i Vronskog i drugi koji prati odnos izme?u Kiti i Ljevina, Lav Tolstoj ispisuje svevremene stranice o porodici, gubitku, ljubavi, izdaji, veri i prijateljstvu.

„U Ani Karenjini Tolstoj je dao izuzetnu psihološku analizu ljudske duše, vrlo duboko i snažno, s realizmom umetni?kog opisivanja koji dosad nismo imali.“ Fjodor Dostojevski',0);
Insert into BIBLIOTEKA.KNJIGA (ISBN,NASLOV,GODIZDAVANJA,GODSTAMPANJA,IZDAVAC,ZANR,FORMATK,TIP,OCENA,SLIKA,KLJUCNERECI,OPIS,OBRISANA) values ('978-86-10-02436-4','Rat i mir','1998.','2018.','Vulkan izdavastvo','KLASICI','17x24cm','OBICNA',0,'vulkanRat.png','roman rusija mladost brak zivot smrt','Rat i mir je neprevazi?eni klasik u kojem Tolstoj daje sliku ruskog društva u Napoleonovo doba i prati živote tri glavna junaka: Pjera Bezuhova, nezakonitog sina seoskog kneza, koji neo?ekivano dobija pozamašno nasledstvo i titulu; kneza Andreja Bolkonskog, koji napušta porodicu da bi se borio u ratu protiv Napoleona; i Nataše Rostove, prelepe i ?arobne devojke koja za Tolstoja oli?enje savršene žene.',0);
Insert into BIBLIOTEKA.KNJIGA (ISBN,NASLOV,GODIZDAVANJA,GODSTAMPANJA,IZDAVAC,ZANR,FORMATK,TIP,OCENA,SLIKA,KLJUCNERECI,OPIS,OBRISANA) values ('978-86-37-91059-6','Otelo','1604.','2009.','Delfi','KLASICI','17x20cm','OBICNA',0,'nema.png','tragedija rasizam ljubav ljubomora izdaja osveta pokajanje','Slavnu tragediju o mleta?kom crncu Otelu, jedno od najpoznatijih dela pesnika Hamleta i Romea i Julije – preveo je na srpski još po?etkom 20. veka dr Svetislav Stefanovi?, tragi?ni predsednik SKZ iz vremena Drugog svetskog rata. On je napisao i predgovor i komentare. Drama ljubavi i ljubomore još se prikazuje na najboljim evropskim pozornicama.',0);
Insert into BIBLIOTEKA.KNJIGA (ISBN,NASLOV,GODIZDAVANJA,GODSTAMPANJA,IZDAVAC,ZANR,FORMATK,TIP,OCENA,SLIKA,KLJUCNERECI,OPIS,OBRISANA) values ('111-32-44-33033-1','Bozanstvena komedija','1472.','1961.','Dereta','POEZIJA','10x17cm','OBICNA',0,'nema.png','pakao raj cistiliste greh krug sfera poema','Danteova “Božanstvena komedija” koja se sastoji od tri dela - Pakao, ?istilište i Raj - ta „sveta poema" koja je obeležila italijanski XIV vek, prerasla je svoje vreme i postala blago, po mnogima do sada neprevazi?eno, sveg ?ove?anstva. U ovom delu pesnik silazi u tamu ljudskog greha i stiže do svetlosti oslobo?enja od njega, a na svome putu kroz tri zagrobna carstva on obuhvata nebo i zemlju, vreme i ve?nost, božansko i ljudsko. To je traganje ljudske duše za apsolutnom sre?om, slobodom, mirom i kona?nim prosvetljenjem.',0);
Insert into BIBLIOTEKA.KNJIGA (ISBN,NASLOV,GODIZDAVANJA,GODSTAMPANJA,IZDAVAC,ZANR,FORMATK,TIP,OCENA,SLIKA,KLJUCNERECI,OPIS,OBRISANA) values ('978-86-52-12015-4','Seobe','1929.','2015.','Laguna','ISTORIJSKI','17.5x24.5cm','OBICNA',0,'seobe.png','vuk zvezda plavo beskrajni krug arandjel dafina puk utociste','Seobe (1929) istovremeno predstavljaju porodi?ni roman tri lika i nacionalni roman o Srbima u Habzburškoj monarhiji u vreme Marije Terezije. Poeti?nim pripovedanjem bez premca u srpskoj prozi pri?a o sudbinama srpskog oficira Vuka Isakovi?a, njegove žene i njegovog puka tokom vojnog pohoda 1744. godine razgranava se u povest o opštoj tragediji nepripadanja, o lutanju, potrazi za smislom i snovima o uto?ištu.

Odmah po objavljivanju roman dobija nagradu SANU, a tokom decenija postaje jedno od temeljnih dela u svakoj ku?noj biblioteci. Doživeo je brojna strana izdanja, a u Francuskoj je proglašen za inostrani roman godine.',0);
Insert into BIBLIOTEKA.KNJIGA (ISBN,NASLOV,GODIZDAVANJA,GODSTAMPANJA,IZDAVAC,ZANR,FORMATK,TIP,OCENA,SLIKA,KLJUCNERECI,OPIS,OBRISANA) values ('978-86-83-49964-9','Bludni sin','1982.','2008.','L.O.M.','KLASICI','12x21cm','OBICNA',0,'bludni sin.png','odrastanje buntovnistvo mali covek otac vaspitanje',' Roman „Bludni sin“ uvod je u autobiografsku sagu ?. Bukovskog. Naslov originala: „Ham on Rye“ replika je na Selindžerovog „ Lovca u žitu“. Odrastanje i buntovištvo mladog ?oveka je ono što povezuje ova dva romana. Knjiga je posve?ena o?evima...',0);
Insert into BIBLIOTEKA.KNJIGA (ISBN,NASLOV,GODIZDAVANJA,GODSTAMPANJA,IZDAVAC,ZANR,FORMATK,TIP,OCENA,SLIKA,KLJUCNERECI,OPIS,OBRISANA) values ('978-86-83-49965-6','Zene','1978.','2008.','L.O.M.','KLASICI','15x22cm','OBICNA',0,'nema.png','erotika bivsa cerka svadje pomirenja ljubavnici','Najpoznatiji roman Bukovskog zasnovan na autobiografskim ?injenicama iz sedamdesetih godina, nakon otkaza u Pošti koji on duhovito predstavlja kao svoj književni uspon.',0);
Insert into BIBLIOTEKA.KNJIGA (ISBN,NASLOV,GODIZDAVANJA,GODSTAMPANJA,IZDAVAC,ZANR,FORMATK,TIP,OCENA,SLIKA,KLJUCNERECI,OPIS,OBRISANA) values ('860-02-62-03172-3','Kostana','1902.','2012.','Pcelica','KLASICI','13x20cm','OBICNA',0,'kostana.png','lektira promasena ljubav drama vranje romkinja','Dramski komad iz Vranjanskog života. Tema - fatalna ljubav. Delo koje je Jovan Skerli? okarakterisao kao jedno od najlepših, najpoeti?nijih i najdubljih u našoj književnosti svoga vremena.',0);
Insert into BIBLIOTEKA.KNJIGA (ISBN,NASLOV,GODIZDAVANJA,GODSTAMPANJA,IZDAVAC,ZANR,FORMATK,TIP,OCENA,SLIKA,KLJUCNERECI,OPIS,OBRISANA) values ('978-86-76-09718-0','Djulici','1899.','2016.','JRJ','POEZIJA','13x18cm','OBICNA',0,'djulici.png','zena ruza pesme ljubav elegicni ton','Ovaj artikal nema opis.',0);
Insert into BIBLIOTEKA.KNJIGA (ISBN,NASLOV,GODIZDAVANJA,GODSTAMPANJA,IZDAVAC,ZANR,FORMATK,TIP,OCENA,SLIKA,KLJUCNERECI,OPIS,OBRISANA) values ('978-86-17-11339-1','Pacija skola','1899.','2004.','Zavod za udzbenike','DECIJE','13x18cm','OBICNA',0,'pacijaSkola.png','pesmice deca','Ovaj artikal nema opis.',0);
Insert into BIBLIOTEKA.KNJIGA (ISBN,NASLOV,GODIZDAVANJA,GODSTAMPANJA,IZDAVAC,ZANR,FORMATK,TIP,OCENA,SLIKA,KLJUCNERECI,OPIS,OBRISANA) values ('978-86-44-10257-5','Zaba cita novina','1901.','1999.','Draganici','DECIJE','17x22cm','OBICNA',0,'nema.png','lokvanj pesma','Ovaj artikal nema opis.',0);
Insert into BIBLIOTEKA.KNJIGA (ISBN,NASLOV,GODIZDAVANJA,GODSTAMPANJA,IZDAVAC,ZANR,FORMATK,TIP,OCENA,SLIKA,KLJUCNERECI,OPIS,OBRISANA) values ('955-56-37-55355-7','Trazim pomilovanje','1964.','2018.','Gramatik','POEZIJA','12x15cm','OBICNA',0,'nema.png','zbirka pesama ljubavna poezija milost prastanje','Ovaj artikal nema opis.',0);
Insert into BIBLIOTEKA.KNJIGA (ISBN,NASLOV,GODIZDAVANJA,GODSTAMPANJA,IZDAVAC,ZANR,FORMATK,TIP,OCENA,SLIKA,KLJUCNERECI,OPIS,OBRISANA) values ('978-86-60-89157-2','Sumnjivo lice','1924.','2011.','Pcelica','DRAMA','14.5x20.5cm','OBICNA',0,'sumnjivo.png','drama vlast koristoljublje jerotije cinovnik','Popularna Nuši?eva komedija – za decu i za odrasle. U njoj su Jerotije sreski kapetan, An?a njegova žena, Marica njihova k?i, Aleksa Žuni? sreski špijun i – ?oka! „Zbiva se u doba naših oceva, u jednoj pograni?noj palanci“, piše Nuši?, ali i danas u našim životima. ',0);
Insert into BIBLIOTEKA.KNJIGA (ISBN,NASLOV,GODIZDAVANJA,GODSTAMPANJA,IZDAVAC,ZANR,FORMATK,TIP,OCENA,SLIKA,KLJUCNERECI,OPIS,OBRISANA) values ('928-22-62-22147-2','Gospodja ministarka','1929.','2015.','Srpska knjizevna zadruga','DRAMA','12.5x21cm','OBICNA',0,'gospodjaM.png','ministar porodica beograd popovic komedija','Nuši?, najve?i srpski komediograf (1864-1938), napisao je niz velikih pozorišnih dela: Sumnjivo lice, Narodni poslanik, Pokojnik, Protekcija, Dr. Najpopularnija je, ipak, Gospo?a ministarka, u kojoj jedna žena – kako piše dr Slavko Leovac – „ambiciozno nastoji da postane velika gospo?a“. – Kako je niz velikih glumica uvek autenti?no tuma?ilo lik Živke Ministarke, tako je svako novo ?itanje ove komedije – novi smeh i nova, svevremena satira.',0);
Insert into BIBLIOTEKA.KNJIGA (ISBN,NASLOV,GODIZDAVANJA,GODSTAMPANJA,IZDAVAC,ZANR,FORMATK,TIP,OCENA,SLIKA,KLJUCNERECI,OPIS,OBRISANA) values ('978-86-52-12858-7','Homo Deus','2015.','2018.','Laguna','EDUKACIJA','13x20cm','OBICNA',0,'homoDeus.png','zagadjivanje grada zarazne bolesti rat samoubistvo glad ','Svojim jedinstvenim stilom, kojim je op?inio milione ?italaca širom sveta, Harari nam ukazuje da su ljudi uspeli ono što deluje nemogu?e: da zauzdaju glad, zarazne bolesti i rat. Pretvorili su ih iz nerazumljivih i nekontrolisanih sila prirode u izazove kojima je mogu?e upravljati. Prvi put više ljudi umire od prevelikog unosa hrane nego od gladi; više ljudi ode s ovog sveta usled starosti nego zbog zaraznih bolesti; više ljudi po?ini samoubistvo nego što strada u akcijama vojnika, terorista i kriminalaca zajedno…',0);
Insert into BIBLIOTEKA.KNJIGA (ISBN,NASLOV,GODIZDAVANJA,GODSTAMPANJA,IZDAVAC,ZANR,FORMATK,TIP,OCENA,SLIKA,KLJUCNERECI,OPIS,OBRISANA) values ('978-03-45-47232-8','Mindset','2006.','2019.','Laguna','PSIHOLOGIJA','13x19cm','OBICNA',5,'mindset.png','uspeh poverenje inteligencija nacini razmisljanja','After decades of research, world-renowned Stanford University psychologist Carol S. Dweck, Ph.D., discovered a simple but groundbreaking idea: the power of mindset. In this brilliant book, she shows how success in school, work, sports, the arts, and almost every area of human endeavor can be dramatically influenced by how we think about our talents and abilities. People with a fixed mindset—those who believe that abilities are fixed—are less likely to flourish than those with a growth mindset—those who believe that abilities can be developed. Mindset reveals how great parents, teachers, managers, and athletes can put this idea to use to foster outstanding accomplishment.',0);
Insert into BIBLIOTEKA.KNJIGA (ISBN,NASLOV,GODIZDAVANJA,GODSTAMPANJA,IZDAVAC,ZANR,FORMATK,TIP,OCENA,SLIKA,KLJUCNERECI,OPIS,OBRISANA) values ('978-86-77-77233-1','Mens search for meaning','1946.','2008.','Penguin Books','BIOGRAFIJA','12.6x19.8cm','OBICNA',0,'nema.png','smisao zivljenja koncentracioni logor drugi svetski rat teske okolnosti','Psychiatrist Viktor Frankl''s memoir has riveted generations of readers with its descriptions of life in Nazi death camps and its lessons for spiritual survival. Between 1942 and 1945 Frankl labored in four different camps, including Auschwitz, while his parents, brother, and pregnant wife perished. Based on his own experience and the experiences of others he treated later in his practice, Frankl argues that we cannot avoid suffering but we can choose how to cope with it, find meaning in it, and move forward with renewed purpose. Frankl''s theory-known as logotherapy, from the Greek word logos ("meaning")-holds that our primary drive in life is not pleasure, as Freud maintained, but the discovery and pursuit of what we personally find meaningful.',0);
Insert into BIBLIOTEKA.KNJIGA (ISBN,NASLOV,GODIZDAVANJA,GODSTAMPANJA,IZDAVAC,ZANR,FORMATK,TIP,OCENA,SLIKA,KLJUCNERECI,OPIS,OBRISANA) values ('978-86-63-03032-9','Bogat otac siromasan otac','1997.','2018.','Finesa','PSIHOLOGIJA','17x24cm','OBICNA',0,'nema.png','novac finansijski uspeh razmisljanje razvoj','Recepti za finansijski uspeh se ?esto tretiraju kao nedoku?ive tajne za one koji sa novcem ne umeju. Neki o?evi to znaju bolje od drugih, ali ?eš?e ne posedaju takvo znanje da prenesu na svoje potomke, jer im je i samima bilo uskra?eno. Istina je da su nam svima ti recepti vrlo dostupni, ali njihova jednostavnost izaziva nevericu. Sumnjate? Ova knjiga ?e vam na jedan sistemati?an i prakti?an na?in pokazati važnost finansijske pismenosti i kontinuiranog razvoja.

Upoznajte oba oca, oba obrasca razmisljanja i delovanja, i opredelite se za jednog. Nije bitno u kojem ste životnom dobu, niti gde se nalazite, primena Kiosakijevih metoda ?e vas navesti da preispitate sve dosadašnje finansijske poteze, da uve?avate sumu na svom bankovnom ra?unu i ulepšate svoj život. Da prestanete da radite za novac i pustite novac da radi za vas.',0);
Insert into BIBLIOTEKA.KNJIGA (ISBN,NASLOV,GODIZDAVANJA,GODSTAMPANJA,IZDAVAC,ZANR,FORMATK,TIP,OCENA,SLIKA,KLJUCNERECI,OPIS,OBRISANA) values ('978-86-521-3555-4','Ana Karenjina','1877.','2013.','Laguna','KLASICI','14x20cm','OBICNA',3,'anaKarenjinaLaguna.png','roman ljubav prevara voz samoubistvo rusija tragedija ','„Najbolja knjiga svih vremena.“ Washington Post

Sve sre?ne porodice li?e jedna na drugu, svaka nesre?na porodica nesre?na je na svoj na?in.

Roman koji je Fjodor Dostojevski smatrao besprekornim, a Vilijam Fokner nazvao najboljim romanom koji je ikada napisan, Ana Karenjina je monumentalno delo Lava Tolstoja koji daje sveobuhvatan prikaz ruskog društva devetnaestog veka, od aristokratskih salona do seoskih gazdinstava. Uvode?i dva narativna toka, prvi koji prati ljubavnu pri?u izme?u Ane i Vronskog i drugi koji prati odnos izme?u Kiti i Ljevina, Lav Tolstoj ispisuje svevremene stranice o porodici, gubitku, ljubavi, izdaji, veri i prijateljstvu.

„U Ani Karenjini Tolstoj je dao izuzetnu psihološku analizu ljudske duše, vrlo duboko i snažno, s realizmom umetni?kog opisivanja koji dosad nismo imali.“ Fjodor Dostojevski
',0);
Insert into BIBLIOTEKA.KNJIGA (ISBN,NASLOV,GODIZDAVANJA,GODSTAMPANJA,IZDAVAC,ZANR,FORMATK,TIP,OCENA,SLIKA,KLJUCNERECI,OPIS,OBRISANA) values ('911-16-11-10157-3','Ana Karenjina','1998.','2005.','Vulkan izdavastvo','KLASICI','12x20cm','OBICNA',4,'anaKarenjinaVulkan.png','roman ljubav prevara voz samoubistvo rusija tragedija ','„Najbolja knjiga svih vremena.“ Washington Post

Sve sre?ne porodice li?e jedna na drugu, svaka nesre?na porodica nesre?na je na svoj na?in.

Roman koji je Fjodor Dostojevski smatrao besprekornim, a Vilijam Fokner nazvao najboljim romanom koji je ikada napisan, Ana Karenjina je monumentalno delo Lava Tolstoja koji daje sveobuhvatan prikaz ruskog društva devetnaestog veka, od aristokratskih salona do seoskih gazdinstava. Uvode?i dva narativna toka, prvi koji prati ljubavnu pri?u izme?u Ane i Vronskog i drugi koji prati odnos izme?u Kiti i Ljevina, Lav Tolstoj ispisuje svevremene stranice o porodici, gubitku, ljubavi, izdaji, veri i prijateljstvu.

„U Ani Karenjini Tolstoj je dao izuzetnu psihološku analizu ljudske duše, vrlo duboko i snažno, s realizmom umetni?kog opisivanja koji dosad nismo imali.“ Fjodor Dostojevski',0);
Insert into BIBLIOTEKA.KNJIGA (ISBN,NASLOV,GODIZDAVANJA,GODSTAMPANJA,IZDAVAC,ZANR,FORMATK,TIP,OCENA,SLIKA,KLJUCNERECI,OPIS,OBRISANA) values ('128-46-13-16435-4','Rat i mir','1993.','2015.','Prosveta','KLASICI','17x20cm','OBICNA',0,'nema.png','roman rusija mladost brak zivot smrt','Rat i mir je neprevazi?eni klasik u kojem Tolstoj daje sliku ruskog društva u Napoleonovo doba i prati živote tri glavna junaka: Pjera Bezuhova, nezakonitog sina seoskog kneza, koji neo?ekivano dobija pozamašno nasledstvo i titulu; kneza Andreja Bolkonskog, koji napušta porodicu da bi se borio u ratu protiv Napoleona; i Nataše Rostove, prelepe i ?arobne devojke koja za Tolstoja oli?enje savršene žene.',0);
Insert into BIBLIOTEKA.KNJIGA (ISBN,NASLOV,GODIZDAVANJA,GODSTAMPANJA,IZDAVAC,ZANR,FORMATK,TIP,OCENA,SLIKA,KLJUCNERECI,OPIS,OBRISANA) values ('978-86-52-13475-5','Dozivljaji Toma Sojera','1983.','2015.','Laguna','DECIJE','13x20cm','OBICNA',0,'tomSojer.png','roman lektira decak detinstvo reka misispi Beka','– Kažem ja – re?e tetka Pola – nije bio r?avo dete, nego, da se tako izrazim, nestašno. Oka?enjak, lucprda, eto, tako bih mogla re?i, ždrebence jedno. Ako je šta ždrebe krivo, onda je i on!

U ovom uzbudljivom romanu, punom komi?nih epizoda, Mark Tven nam donosi pri?u o odrastanju de?aka Toma na obali reke Misisipi sredinom 19. veka. Tom je u stalnoj potrazi za avanturama i nestašlucima sa svojim najboljim drugovima, iako se zbog toga ?esto na?e u nevolji. Ali kako vreme prolazi, Tom postaje spreman za hrabra i plemenita dela da bi zaštitio devoj?icu u koju je zaljubljen ili spre?io da nevina osoba nastrada.

Slikaju?i portret jednog de?aka, Tven slika i portret detinjstva kao sre?nog i bezbrižnog doba u životu svakog od nas, punog neobi?nih i nepromišljenih poduhvata. Možda su upravo zbog toga i danas, nakon više od jednog veka od objavljivanja, Doživljaji Toma Sojera jedan od najpopularnijih i najvoljenijih romana za decu.
',0);
Insert into BIBLIOTEKA.KNJIGA (ISBN,NASLOV,GODIZDAVANJA,GODSTAMPANJA,IZDAVAC,ZANR,FORMATK,TIP,OCENA,SLIKA,KLJUCNERECI,OPIS,OBRISANA) values ('978-86-52-13239-3','Zlocin i kazna','1866.','2019.','Laguna','KLASICI','13.5x20.5cm','OBICNA',3,'zlocin.png','cilj sredstvo roman lektira zakon krivica pokajanje','Remek-delo ruske književnosti, Zlo?in i kazna svrstalo je Dostojevskog u red najzna?ajnijih svetskih romanopisaca. Oslanjaju?i se na sopstveno iskustvo iz zatvorskih dana, u grozni?avom tonu i na ubedljiv na?in, autor pripoveda pri?u o Raskoljnikovu – siromašnom studentu mu?enom nihilizmom i borbom izme?u dobra i zla. Veruju?i za sebe da je iznad zakona i ube?en u to da viši ciljevi opravdavaju sredstvo, on brutalno ubija lihvarku za koju kaže da je „ništavna, zla, bolesna baba koja nikome nije potrebna... koja ni sama ne zna zašto živi i koja ?e ionako ve? danas-sutra umreti“. Skrhan ose?ajem krivice, on ?e otpo?eti proces pokajanja koji ?e mu korenito promeniti pogled na svet.',0);
Insert into BIBLIOTEKA.KNJIGA (ISBN,NASLOV,GODIZDAVANJA,GODSTAMPANJA,IZDAVAC,ZANR,FORMATK,TIP,OCENA,SLIKA,KLJUCNERECI,OPIS,OBRISANA) values ('860-02-62-03541-7','Hamlet','1603.','1996.','Vulkan izdavastvo','KLASICI','13x20cm','OBICNA',3,'hamlet.png','tragedija kraljevic ludilo vitenberg otac stric ubistvo','Jedna od velikih tragedija možda najve?eg svetskog dramskog pisca, Viljema Šekspira, „Hamlet“ je pri?a o danskom kraljevi?u kome duh njegovog oca, koji no?u pohodi bedeme kraljevskog zamka Elsinor, potvr?uje sumnje da mu je oca ubio stric, koji se ubrzo posle toga oženio majkom Hamletovom i prakti?no uzurpirao presto, koji je trebalo da pripadne Hamletu. Hamlet se duhu obavezuje da ?e oca osvetiti, a zaplet tragedije po?iva na dvojnosti njegovog karaktera i posledi?nim dvoumicama njegovim, i raskoraku izme?u nauma i delanja. Osveta se, na kraju, postiže, ali po tako strahovitu cenu da su njen smisao i svrsishodnost, kao i motivi Hamletovi, jama?no dovedeni u sumnju. „Hamlet“ važi za jednu od „najprestižnijih“ Šekspirovih drama i verovatno je naj?eš?e izvo?ena, a koriš?ena je za bezbrojne filmske i televizijske adaptacije.',0);
Insert into BIBLIOTEKA.KNJIGA (ISBN,NASLOV,GODIZDAVANJA,GODSTAMPANJA,IZDAVAC,ZANR,FORMATK,TIP,OCENA,SLIKA,KLJUCNERECI,OPIS,OBRISANA) values ('978-86-10-01769-4','Romeo i Julija','1605.','2016.','Vulkan izdavastvo','LJUBAVNI','17x20cm','OBICNA',0,'romeo i julija.png','tragedija dve porodice ljubav lektira otrov drama','Romeo i Julija najpoznatija je ljubavna pri?a svih vremena. Doživela je brojne pozorišne adaptacije i filmske ekranizacije, i uticala je na stvaralaštvo mnogih potonjih pisaca. Zauvek aktuelna pri?a o velikoj ljubavi izme?u Romea i Julije, koja teži da obriše sve nepomirljivosti i razlike.

Romeo i Julija pripadaju dvema suprotstavljenim porodicama – Montagi i Kapulet. Me?utim, njih dvoje se zaljubljuju i rešeni su da prevazi?u sve prepreke kako bi bili zajedno.
Radnja se dodatno komplikuje kada Romeo spletom nesre?nih okolnosti ubija Julijinog ro?aka Tibalta i prinu?en je da beži iz grada Verone. Dvoje zaljubljenih tada bivaju prinu?eni da sprovedu jedan rizi?an plan, koji ?e ih odvesti pravo u smrt.
',0);
Insert into BIBLIOTEKA.KNJIGA (ISBN,NASLOV,GODIZDAVANJA,GODSTAMPANJA,IZDAVAC,ZANR,FORMATK,TIP,OCENA,SLIKA,KLJUCNERECI,OPIS,OBRISANA) values ('867-05-82-57086-3','Ujka Vanja','1898.','2019.','Gutenbergova galaksija','DRAMA','15x21cm','OBICNA',0,'ujkaV.png','jelena rusija cerka zena letnjikovac','Zaplet “Ujka Vanje” - A. P. ?ehov (1860-1904) - sazdan je na jednostavnom obrascu posete: profesor Serbrjakov i njegova žena Jelena došli su na imanje profesorove prve žene s namerom da one koji tamo žive nagovore da prodaju imanje i time reše materijalni problem u kojem su se našli posle njegovog odlaska u penziju. Sem toga, na drugom planu taj njihov dolazak proizvodi tri erotske intrige: Vojnicki voli Jelenu, ali ne i ona njega; Sonja voli Astorova, ali ne i on nju; Astorov voli Jelenu koja, iako nije ravnodušna prema njemu, ipak ne može da mu uzvrati ljubavlju. Pošto u drugom i tre?em ?inu propadaju i ljubavne želje i snovi a tako?e i profesorov plan da proda imanje, u ?etvrtom ?inu posetioci mogu još samo da se pozdrave i odu, a doma?i nastavljaju da žive kao ranije. Tako je na oba plana zapleta “Ujka Vanje“- erotskom i materijalnom - ishod neuspeh i propast',0);
Insert into BIBLIOTEKA.KNJIGA (ISBN,NASLOV,GODIZDAVANJA,GODSTAMPANJA,IZDAVAC,ZANR,FORMATK,TIP,OCENA,SLIKA,KLJUCNERECI,OPIS,OBRISANA) values ('978-86-60-89328-5','Hajduci','1933.','2009.','Pcelica','DECIJE','14.5x20.5cm','OBICNA',0,'hajduci.png','jataci decak dunav mile vrabac zika mita trta','– Mogu li ja da odem u hajduke pa da otud, iz planine, napišem pismo na?elniku: „Ako mi profesori poprave beleške, preda?u se vlasti, a ako ne?e da mi poprave, osta?u i dalje hajduk.“ – Budalo jedna – re?i ?e ?eda – pa kad si hajduk, šta ?e ti dobre beleške?

Svakog ?etvrtka i nedelje grupica de?aka okuplja se pored hrastovog stabla na obali Dunava. Tu uživaju u dokolici, igraju se loptom, maštaju i izmišljaju razli?ite pri?e umesto da rade doma?e zadatke. Iz odbojnosti prema školi i obavezama rodi?e se ideja da pobegnu od ku?e i odmetnu se u hajduke. Ova nestašna avantura, puna zgoda i nezgoda, opameti?e mlade hajduke i nasmejati ?itaoce do suza.

Hajduci su jedan od prvih humoristi?kih romana za decu u srpskoj književnosti, a i dan-danas spadaju u naj?itanija i najpopularnija dela kako ovog autora tako i doma?e književnosti za decu.',0);
Insert into BIBLIOTEKA.KNJIGA (ISBN,NASLOV,GODIZDAVANJA,GODSTAMPANJA,IZDAVAC,ZANR,FORMATK,TIP,OCENA,SLIKA,KLJUCNERECI,OPIS,OBRISANA) values ('978-86-52-12885-3','Autobiografija','1924.','2018.','Laguna','BIOGRAFIJA','13x20cm','OBICNA',5,'autobioBN.png','mladost osmeh detinstvo ljubav persa predmeti skola','„Što sam više upoznavao život, i što sam bliže upoznavao ljude, sve sam se sla?e smejao.“

Retki su naši književnici koji su u formi autobiografije pisali o vlastitom životu, a samo je najve?i srpski komediograf u svom životopisu tretirao sebe kao junaka komedije.

?injenice o ro?enju, školskim danima, ženidbi i ulasku u svet odraslih postaju gra?a za urnebesne zaplete, neo?ekivane obrte, komediju situacije, ispunjenu razdraganim, dobronamernim humorom. Stranice posve?ene školovanju, po kojima se Autobiografija najviše pamti, obavezna su lektira za sve generacije. Uživljavaju?i se u ulogu deteta, u svojevrsnom katalogu školskih predmeta, kroz vic, anegdotu i parodiju, Nuši? pruža satiri?nu istoriju nesklada izme?u de?jeg poimanja sveta i krutosti škole, koja u?enika ne sprema za neminovni sudar sa stvarnim svetom. U o?u?enom doživljaju takve stvarnosti prepoznava?e se budu?e generacije, sve do naših dana.',0);
Insert into BIBLIOTEKA.KNJIGA (ISBN,NASLOV,GODIZDAVANJA,GODSTAMPANJA,IZDAVAC,ZANR,FORMATK,TIP,OCENA,SLIKA,KLJUCNERECI,OPIS,OBRISANA) values ('978-86-89-20376-9','Starac i more','1952.','2018.','Dereta','KLASICI','13x20cm','OBICNA',0,'staracmore.png','riba pecanje decak obala riblji skelet ribolovac','Starac i more, najzna?ajnije delo Ernesta Hemingveja, pri?a je o kubanskom ribaru tokom trodnevne borbe za goli život na otvorenom moru, ali i sa svojom ljudskom naravi, satkanom od slabosti, ponosa i strahova, koja veoma lako može postati i njegov najve?i protivnik.



Pred našim ?itaocima je novi prevod starog remek-dela koje jednostavnim, a ipak živim, ubedljivim i preciznim jezikom govori o su?eljavanju pojedinca s neumitnim silama prirode i strašnim životnim ?injenicama, kada se poraz ?ini kao jedini ishod. Podatak da je Starac i more tek jedan od devet romana koje je Nobelov komitet, u svom obrazloženju odluke o dodeljivanju najve?e književne nagrade na svetu, eksplicitno izdvojio iz celokupnog dela jednog dobitnika, dovoljno govori o njegovoj neprolaznoj vrednosti. Hemingvej nam je ovom novelom ostavio kolosalni dokaz svog književnog genija i veli?anstveni doprinos književnosti XX veka.',0);
Insert into BIBLIOTEKA.KNJIGA (ISBN,NASLOV,GODIZDAVANJA,GODSTAMPANJA,IZDAVAC,ZANR,FORMATK,TIP,OCENA,SLIKA,KLJUCNERECI,OPIS,OBRISANA) values ('978-86-52-12215-8','Mali Princ','1943.','2016.','Laguna','DECIJE','13x20cm','OBICNA',5,'maliPrinc.png','ruza planete lektira francuska ','U svom kratkom letu od asteroida B612 do ?udne i njemu strane planete Zemlje mali princ otkriva nekoliko drugih planeta i njihovih stanovnika. Svi su oni uobraženi i samoživi i bez ikakve ljubavi ili razumevanja obavljaju poslove koji malom princu izgledaju kao da su potpuno besmisleni i beskorisni. Iz duhovne pustinje takvih svetova mali princ se spušta na Zemlju, gde naizgled slu?ajno pada u stvarnu pustinju – Saharu. Na toj Zemlji bez ljudi on malo-pomalo spoznaje da su rad, prijateljstvo i ljubav vrednosti bez kojih ?ovek ne može da živi.',0);
Insert into BIBLIOTEKA.KNJIGA (ISBN,NASLOV,GODIZDAVANJA,GODSTAMPANJA,IZDAVAC,ZANR,FORMATK,TIP,OCENA,SLIKA,KLJUCNERECI,OPIS,OBRISANA) values ('978-37-88-76153-5','1000 zasto 1000 zato','1974.','2020.','Akia Mali Princ','EDUKACIJA','21x27cm','ENCIKLOPEDIJA',4,'nema.png','leksikon znanja nauka priroda umestnost sport zdravlje','1000 zašto 1000 zato predstavlja veliki leksikon znanja, ne samo za decu, ve? i za odrasle. Me?u koricama ove enciklopedije prona?i ?ete sve što može zanimati Vas i Vašu decu jer ova sveznaju?a knjiga obra?uje sve oblasti nauke, prirode, istorije, umetnosti, sporta i zabave kroz vekove.',0);
Insert into BIBLIOTEKA.KNJIGA (ISBN,NASLOV,GODIZDAVANJA,GODSTAMPANJA,IZDAVAC,ZANR,FORMATK,TIP,OCENA,SLIKA,KLJUCNERECI,OPIS,OBRISANA) values ('978-86-52-12744-3','Sve ptice Srbije','2005.','2017.','Laguna','EDUKACIJA','16.5x24cm','ENCIKLOPEDIJA',0,'svePtice.png','rasprostranjenost selice stanarice zimski gost ','Ovaj sveobuhvatni vodi? predstavlja kompletnu sliku o divnom svetu ptica Srbije. U njemu je preko 1200 crteža u boji, rezultat višedecenijskog rada akademskog slikara i vrhunskog poznavaoca ptica Javora Rašajskog. Date su i precizne UTM karte za svaku vrstu tako da lako možete videti njihovu rasprostranjenost, doba godine kada su prisutne, kao i njihovu u?estalost. Posebni simboli ozna?avaju da li je prikazana ptica stanarica, selica, zimski gost. Priru?nik se lako pretražuje i može poslužiti kao klju? za prepoznavanje vrsta. Koncipiran je tako da ga mogu koristiti svi – od školaraca do profesionalaca.',0);
Insert into BIBLIOTEKA.KNJIGA (ISBN,NASLOV,GODIZDAVANJA,GODSTAMPANJA,IZDAVAC,ZANR,FORMATK,TIP,OCENA,SLIKA,KLJUCNERECI,OPIS,OBRISANA) values ('978-86-75-80306-5','Na Drini cuprija','1945.','2009.','Nova knjiga','KLASICI','17x21cm','OBICNA',0,'na drini cuprija.png','most roman bosna lektira osmansko carstvo ','„Ja sam na putovanja trošio ne samo novac i vreme nego i snagu živaca i mašte, jer u putovanja treba ura?unati i duge, uglavnom izlišne pripreme u mašti i u stvarnosti... A koliko sam se u tim putovanjima trošio, vidi se najbolje po tome što sam za vreme dva velika rata (1914–1918. i 1941–1944), kada sam bio prisiljen da sedim na jednom mestu, napisao gotovo najve?i deo svojih radova.“ Ivo Andri?
Za svoj celokupni opus Andri? je 1961. godine dobio Nobelovu nagradu za književnost, „za epsku snagu“, kako stoji u obrazloženju o dodeli nagrade, „kojom je oblikovao motive i sudbine iz istorije svoje zemlje.“',0);
Insert into BIBLIOTEKA.KNJIGA (ISBN,NASLOV,GODIZDAVANJA,GODSTAMPANJA,IZDAVAC,ZANR,FORMATK,TIP,OCENA,SLIKA,KLJUCNERECI,OPIS,OBRISANA) values ('978-86-61-05047-3','Travnicka hronika','1945.','2011.','Sezam Book','ISTORIJSKI','14x21cm','OBICNA',0,'sezanTravnicka.png','istorija strani konzul roman',' Istorijski roman "Travni?ka hronika" nastajao je u periodu Drugog svetskog rata zajedno sa još dva velika Andri?eva romana, "Na Drini ?uprija" i "Gospo?ica". Radnja u romanu pokriva ukupno 7 godina stranih konzula u vezirskom gradu Travniku. "Travni?ka hronika" oslikava sudar ?etiri sveta, razli?itog nasle?a, kulture i vere koji zapravo nemaju ni najmanje volje ni želje da se me?usobno razumeju i sažive. U epicentru svega toga je ?etvrti svet - obi?na raja iz tadašnje Bosne koja spaja ove svetove na jedan surov na?in, nikome zapravo nije stalo do ?oveka i do onoga što danas nazivamo ljudskim dostojanstvom. Taj, naoko, visoki svet intriga i spletaka, politi?kih dogovora i mešetarenja je samo još jedan vid circulus vitiosus-a istorije ve?ite potrebe za osvajanjem.',0);
Insert into BIBLIOTEKA.KNJIGA (ISBN,NASLOV,GODIZDAVANJA,GODSTAMPANJA,IZDAVAC,ZANR,FORMATK,TIP,OCENA,SLIKA,KLJUCNERECI,OPIS,OBRISANA) values ('918-86-11-11047-6','Travnicka hronika','1945.','2020.','Vulkan izdavastvo','ISTORIJSKI','21x15cm','OBICNA',4,'vulkanTravnicka.png','istorija strani konzul roman',' Istorijski roman "Travni?ka hronika" nastajao je u periodu Drugog svetskog rata zajedno sa još dva velika Andri?eva romana, "Na Drini ?uprija" i "Gospo?ica". Radnja u romanu pokriva ukupno 7 godina stranih konzula u vezirskom gradu Travniku. "Travni?ka hronika" oslikava sudar ?etiri sveta, razli?itog nasle?a, kulture i vere koji zapravo nemaju ni najmanje volje ni želje da se me?usobno razumeju i sažive. U epicentru svega toga je ?etvrti svet - obi?na raja iz tadašnje Bosne koja spaja ove svetove na jedan surov na?in, nikome zapravo nije stalo do ?oveka i do onoga što danas nazivamo ljudskim dostojanstvom. Taj, naoko, visoki svet intriga i spletaka, politi?kih dogovora i mešetarenja je samo još jedan vid circulus vitiosus-a istorije ve?ite potrebe za osvajanjem.',0);
Insert into BIBLIOTEKA.KNJIGA (ISBN,NASLOV,GODIZDAVANJA,GODSTAMPANJA,IZDAVAC,ZANR,FORMATK,TIP,OCENA,SLIKA,KLJUCNERECI,OPIS,OBRISANA) values ('860-02-62-00465-9','Prokleta avlija','1954.','2008.','Zavod za udzbenike','KLASICI','13x20cm','OBICNA',0,'prokletaA.png','carigradska tamnica policija bosna stambol fra-Petar camil','Carigradski zatvor, nazvan „Prokleta avlija“, sa svojim simboli?ki šarenim svetom, spojio je jednog skromnog, poštenog i nedužno optuženog bosanskog fratra i razo?aranog, životom otrovanog i fikcijom opsednutog turskog bogataša. Svet „Proklete avlije“, koja kroz svoju utrobu propušta sve vrste ljudskih grehova i poroka i ?ija je nepregledna raznolikost pomno nadgledana zastrašuju?im o?ima upravnika Latifage Kara?oza, mesto je sa vrlo upe?atljivim likovima i sudbinama. Ipri?ana smirenim re?enicama koje su izraz istan?anog ume?a pripovedanja, kao i unutrašnjom dinamikom koja obuhvata ?itavo delo, Prokleta avlija je nedvosmisleno jedno od najboljih ostvarenja u celom Andri?evom bogatom književnom opusu.',0);
Insert into BIBLIOTEKA.KNJIGA (ISBN,NASLOV,GODIZDAVANJA,GODSTAMPANJA,IZDAVAC,ZANR,FORMATK,TIP,OCENA,SLIKA,KLJUCNERECI,OPIS,OBRISANA) values ('978-86-81-13124-4','Ex Ponto','1918.','2019.','Zaduzbina Ive Andrica','KLASICI','16x23cm','OBICNA',0,'exPonto.png','stihovi u prozi tamnica melanholija usamljenost progonstvo','U Ex Pontu (1918), ranom Andri?evom delu, nazvanom „razgovori s dušom“ (Niko Bartulovi?), stilizovano je li?no iskustvo u nastojanju da mu se, u lirskoj sentenci, pridoda zna?enje filozofsko-poetske istine, a da pri tom zadrži fabulativno-narativnu osnovu, na osnovu koje bi se mogla rekonstruisati stanja jednog zato?enika. Ta knjiga pesama u prozi razvrstana je u tri ciklusa: prvi ima 26, drugi 25, a tre?i 88 tekstova, uz završni „Epilog“. U strasnom lirskom monologu, pesnik se obra?unava sa sobom, pokušava da u tamni?kim bdenjima razreši unutrašnju dramu i oslobodi se traume izazvane utamni?enjem. Nepravedan pad iza rešetaka u drugi, surov i mu?an svet, gde je žrtva „na suhom ukletom sprudu“, dovodi pesnika u stanje da postavlja važna egzistencijlna pitanja i grozni?avo razmišlja o svetu i mestu pojedinca u njegovim tragi?nim okvirima.',0);
Insert into BIBLIOTEKA.KNJIGA (ISBN,NASLOV,GODIZDAVANJA,GODSTAMPANJA,IZDAVAC,ZANR,FORMATK,TIP,OCENA,SLIKA,KLJUCNERECI,OPIS,OBRISANA) values ('978-86-52-13084-8','Roman o Londonu','1971.','2019.','Laguna','KLASICI','13x20cm','OBICNA',0,'romanOLondonu.png','ruski emigran zena nepripadanje ','Pri?om o sudbini ruskog emigranta i njegove žene u Londonu posle Drugog svetskog rata Crnjanski je uspostavio roman savremen onoliko koliko je aktuelno prinudno izmeštanje ?oveka iz postojanja u kojem je ukorenjen.

Rjepnin i njegova supruga tumaraju gradom koji ih ne poznaje, pokušavaju da postoje u bezna?u nepripadanja. A ve? na po?eku njihove pri?e, pod žrvnjem svesti o tome šta zna?i ne biti niko, ukazuje se jezovit Rjepninov put ka izlazu iz o?aja. Njegova neumitnost odredi?e tok ovog epohalnog romana, opominju?i modernog ?oveka na njegovu sopstvenu bezdomnost u komešanju savremenog doba.',0);
Insert into BIBLIOTEKA.KNJIGA (ISBN,NASLOV,GODIZDAVANJA,GODSTAMPANJA,IZDAVAC,ZANR,FORMATK,TIP,OCENA,SLIKA,KLJUCNERECI,OPIS,OBRISANA) values ('978-86-10-03117-1','Dnevnik o Carnojevicu','1920.','2020.','Vulkan izdavastvo','KLASICI','14.5x20.5cm','OBICNA',0,'dnevnikOC.png','jesen zivot bez smisla petar rajic','Dnevnik o ?arnojevi?u (1921) je roman o mladom intelektualcu koji se našao u vrtlogu Prvog svetskog rata. Crnjanski sprovodi postupak paralelnog pri?anja doživljavanja detinjstva i de?aštva doratnih godina – sa jedne strane, i slika i doživljaja rata – s druge. Pisan u prvom licu i u naglašeno emocionalnom tonu, ovaj roman ostavlja utisak izrazito lirske autobiografske poeme. Prvi roman Miloša Crnjanskog, delo nesvakidašnje forme i sentimenta, ve? posle prvih burnih reakcija kritike, dospelo je u same vrhove srpske književnosti.',0);
Insert into BIBLIOTEKA.KNJIGA (ISBN,NASLOV,GODIZDAVANJA,GODSTAMPANJA,IZDAVAC,ZANR,FORMATK,TIP,OCENA,SLIKA,KLJUCNERECI,OPIS,OBRISANA) values ('978-86-64-57309-2','Ljubav u Toskani','1930.','2020.','Dereta','KLASICI','11x15cm','OBICNA',0,'ljubavUToskani.png','putopis italija ','Ljubav u Toskani Miloša Crnjanskog predstavlja prekretnicu u srpskom putopisu. Lirizam s kojim autor prikazuje predele Toskane doseže granice u kojima se ?italac ose?a kao da je sve to ve? negde video, osetio, dodirnuo... Toliko ljubavi, umetnosti, kulture, predela, boja, mirisa, izazivaju u ?oveku ose?anje koje sva astralna putovanja ?ini stvarnim i opipljivim.

Snagom svog izraza Crnjanski predstavlja Toskanu na dva identi?na plana, ali iz razli?itih uglova, od kojih je jedan direktno uslovljen posmatranjem, dok je drugi oslonjen na kulturnoistorijske ?injenice. Zavodljiva proza ovog putopisa nenametljivo vezuje i uvla?i ?itaoca u svoje skrivene lagume, u kojima se ose?a pravi dah italijanskog vazduha, navode?i ga da poželi da krene stopama Crnjanskog, od predela do predela, od mesta do mesta, ka Firenci, Pizi, Sijeni, Peru?i… Jer jedino se tako i može osetiti Ljubav u Toskani – neprestano ?itaju?i i neprestano putuju?i.',0);
Insert into BIBLIOTEKA.KNJIGA (ISBN,NASLOV,GODIZDAVANJA,GODSTAMPANJA,IZDAVAC,ZANR,FORMATK,TIP,OCENA,SLIKA,KLJUCNERECI,OPIS,OBRISANA) values ('978-86-52-13049-8','Necista krv','1910.','2012.','Laguna','KLASICI','13x20cm','OBICNA',0,'necistaKrv.png','sofka vranje nesreca ljudsko nespokojstvo ljubav ','U pri?i o stradanju ?uvene vranjanske lepotice Sofke, prvi put u našoj književnosti, spoljašnji svet se uspostavlja kao suptilni odraz ?ulne uznemirenosti, intimnih doživljaja, slutnji i podsvesnih zbivanja jedne žene – izuzetne koliko lepotom toliko i snažnom samosveš?u. Prvi put žensko i muško telo, erotske žudnje i seksualna htenja dobijaju prvorazredni književni zna?aj, a likovi žestokog temperamenta, orijentalno egzoti?ni, odre?eni arhai?nim jezikom i kulturom, rastrzani izme?u svojih nagona i društveno nametnutog postojanja, postaju tragi?ne univerzalne figure u kojima prepoznajemo ve?ne protivre?nosti postojanja.',0);
Insert into BIBLIOTEKA.KNJIGA (ISBN,NASLOV,GODIZDAVANJA,GODSTAMPANJA,IZDAVAC,ZANR,FORMATK,TIP,OCENA,SLIKA,KLJUCNERECI,OPIS,OBRISANA) values ('978-86-52-11487-0','Srecnik','2010.','2014.','Laguna','LJUBAVNI','13x20cm','OBICNA',5,'srecnikNS.png','film ljubav drama fotografija zena sreca','Ameri?ki marinac Logan Tibo u Iraku nalazi u pesku fotografiju nasmešene mlade žene; prvi poriv mu je da je baci, ali iz neznanog razloga nosi je sa sobom. Uskoro po?inje da ga prati neo?ekivana sre?a – pobe?uje u pokeru, preživljava okršaj za okršajem, ?ak i onaj najsmrtonosniji u kojem ?e mu poginuti dva prijatelja. Samo njegov najbolji drug Viktor smatra da ima objašnjenje za to: ta fotografija je amajlija i Tibou donosi sre?u.

Po povratku u rodni Kolorado, Tibo ne uspeva da istisne fotografiju iz misli. Uveren da žena sa slike nekako poseduje klju? njegove sudbine, on polazi na dug put kako bi je pronašao, ali ne o?ekuje da ?e Bet, snažna ali i ranjiva žena koju ?e upoznati u Severnoj Karolini, biti upravo ona kojoj se ?itavog života nadao. Izme?u njega i Bet po?inje da se ra?a strastvena ljubav, ali njegova tajna uskoro ?e zapretiti da ih rastavi.',0);
Insert into BIBLIOTEKA.KNJIGA (ISBN,NASLOV,GODIZDAVANJA,GODSTAMPANJA,IZDAVAC,ZANR,FORMATK,TIP,OCENA,SLIKA,KLJUCNERECI,OPIS,OBRISANA) values ('978-86-86-61149-9','Izabrani eseji','2000.','2010.','Akademska knjiga','KLASICI','13x20cm','OBICNA',0,'nema.png','esej publicistika misaonost emocija','Isidora Sekuli? je bila veliki moralista u najplemenitijem smislu re?i. Njeno insistiranje na moralnosti likova, kao i na moralnosti samog sižea nekog dela, bilo je ugra?eno u ono što književnu kritiku ?ini književnoš?u – misaonost i impresiju autora. Lepota njenog iskaza, danas tako retka u našoj savremenoj književnoj kritici, privla?i i današnjeg ?itaoca da ?ita prikaze knjiga koje su odavno prisutne samo u književnoj istoriji i to ?esto na njenoj margini. Njena književna kritika sama je po sebi književni tekst, i to je ono što ?e joj obezbediti ?itaoce i u budu?nosti. Ona je filter kroz koji Isidora Sekuli? želi da obogati intelekt i emociju svojih ?italaca a kroz njih i kulturu srpskog naroda.',0);
REM INSERTING into BIBLIOTEKA.PRIMERAK
SET DEFINE OFF;
Insert into BIBLIOTEKA.PRIMERAK (SIFRAPR,SLOBODAN,ISBN) values (1,1,'978-86-10-00127-3');
Insert into BIBLIOTEKA.PRIMERAK (SIFRAPR,SLOBODAN,ISBN) values (2,1,'978-86-521-3555-4');
Insert into BIBLIOTEKA.PRIMERAK (SIFRAPR,SLOBODAN,ISBN) values (3,1,'911-16-11-10157-3');
Insert into BIBLIOTEKA.PRIMERAK (SIFRAPR,SLOBODAN,ISBN) values (4,0,'978-86-10-02436-4');
Insert into BIBLIOTEKA.PRIMERAK (SIFRAPR,SLOBODAN,ISBN) values (5,0,'128-46-13-16435-4');
Insert into BIBLIOTEKA.PRIMERAK (SIFRAPR,SLOBODAN,ISBN) values (6,0,'978-86-52-13475-5');
Insert into BIBLIOTEKA.PRIMERAK (SIFRAPR,SLOBODAN,ISBN) values (7,0,'978-86-52-13239-3');
Insert into BIBLIOTEKA.PRIMERAK (SIFRAPR,SLOBODAN,ISBN) values (8,1,'860-02-62-03541-7');
Insert into BIBLIOTEKA.PRIMERAK (SIFRAPR,SLOBODAN,ISBN) values (9,1,'978-86-37-91059-6');
Insert into BIBLIOTEKA.PRIMERAK (SIFRAPR,SLOBODAN,ISBN) values (10,1,'978-86-10-01769-4');
Insert into BIBLIOTEKA.PRIMERAK (SIFRAPR,SLOBODAN,ISBN) values (11,1,'867-05-82-57086-3');
Insert into BIBLIOTEKA.PRIMERAK (SIFRAPR,SLOBODAN,ISBN) values (12,0,'111-32-44-33033-1');
Insert into BIBLIOTEKA.PRIMERAK (SIFRAPR,SLOBODAN,ISBN) values (13,1,'978-86-75-80306-5');
Insert into BIBLIOTEKA.PRIMERAK (SIFRAPR,SLOBODAN,ISBN) values (14,1,'978-86-61-05047-3');
Insert into BIBLIOTEKA.PRIMERAK (SIFRAPR,SLOBODAN,ISBN) values (15,1,'918-86-11-11047-6');
Insert into BIBLIOTEKA.PRIMERAK (SIFRAPR,SLOBODAN,ISBN) values (16,1,'860-02-62-00465-9');
Insert into BIBLIOTEKA.PRIMERAK (SIFRAPR,SLOBODAN,ISBN) values (17,0,'978-86-81-13124-4');
Insert into BIBLIOTEKA.PRIMERAK (SIFRAPR,SLOBODAN,ISBN) values (18,0,'978-86-52-12015-4');
Insert into BIBLIOTEKA.PRIMERAK (SIFRAPR,SLOBODAN,ISBN) values (19,1,'978-86-52-13084-8');
Insert into BIBLIOTEKA.PRIMERAK (SIFRAPR,SLOBODAN,ISBN) values (20,1,'978-86-10-03117-1');
Insert into BIBLIOTEKA.PRIMERAK (SIFRAPR,SLOBODAN,ISBN) values (21,1,'978-86-64-57309-2');
Insert into BIBLIOTEKA.PRIMERAK (SIFRAPR,SLOBODAN,ISBN) values (22,0,'978-86-83-49964-9');
Insert into BIBLIOTEKA.PRIMERAK (SIFRAPR,SLOBODAN,ISBN) values (23,1,'978-86-83-49965-6');
Insert into BIBLIOTEKA.PRIMERAK (SIFRAPR,SLOBODAN,ISBN) values (24,1,'978-86-52-13049-8');
Insert into BIBLIOTEKA.PRIMERAK (SIFRAPR,SLOBODAN,ISBN) values (25,1,'860-02-62-03172-3');
Insert into BIBLIOTEKA.PRIMERAK (SIFRAPR,SLOBODAN,ISBN) values (26,0,'978-86-52-12078-9');
Insert into BIBLIOTEKA.PRIMERAK (SIFRAPR,SLOBODAN,ISBN) values (27,1,'978-86-52-12490-9');
Insert into BIBLIOTEKA.PRIMERAK (SIFRAPR,SLOBODAN,ISBN) values (28,1,'978-86-76-09718-0');
Insert into BIBLIOTEKA.PRIMERAK (SIFRAPR,SLOBODAN,ISBN) values (29,1,'978-86-17-11339-1');
Insert into BIBLIOTEKA.PRIMERAK (SIFRAPR,SLOBODAN,ISBN) values (30,1,'955-56-37-55355-7');
Insert into BIBLIOTEKA.PRIMERAK (SIFRAPR,SLOBODAN,ISBN) values (31,0,'125-46-35-44332-6');
Insert into BIBLIOTEKA.PRIMERAK (SIFRAPR,SLOBODAN,ISBN) values (32,1,'978-86-52-30005-1');
Insert into BIBLIOTEKA.PRIMERAK (SIFRAPR,SLOBODAN,ISBN) values (33,1,'978-86-52-30002-0');
Insert into BIBLIOTEKA.PRIMERAK (SIFRAPR,SLOBODAN,ISBN) values (34,0,'978-86-60-89157-2');
Insert into BIBLIOTEKA.PRIMERAK (SIFRAPR,SLOBODAN,ISBN) values (35,1,'928-22-62-22147-2');
Insert into BIBLIOTEKA.PRIMERAK (SIFRAPR,SLOBODAN,ISBN) values (36,1,'978-86-60-89328-5');
Insert into BIBLIOTEKA.PRIMERAK (SIFRAPR,SLOBODAN,ISBN) values (37,1,'978-86-52-12885-3');
Insert into BIBLIOTEKA.PRIMERAK (SIFRAPR,SLOBODAN,ISBN) values (38,1,'978-86-89-20376-9');
Insert into BIBLIOTEKA.PRIMERAK (SIFRAPR,SLOBODAN,ISBN) values (39,0,'978-86-52-12215-8');
Insert into BIBLIOTEKA.PRIMERAK (SIFRAPR,SLOBODAN,ISBN) values (40,1,'978-37-88-76153-5');
Insert into BIBLIOTEKA.PRIMERAK (SIFRAPR,SLOBODAN,ISBN) values (41,0,'978-86-52-12744-3');
Insert into BIBLIOTEKA.PRIMERAK (SIFRAPR,SLOBODAN,ISBN) values (42,1,'978-03-45-47232-8');
Insert into BIBLIOTEKA.PRIMERAK (SIFRAPR,SLOBODAN,ISBN) values (43,1,'978-86-77-77233-1');
Insert into BIBLIOTEKA.PRIMERAK (SIFRAPR,SLOBODAN,ISBN) values (44,1,'978-86-52-12858-7');
Insert into BIBLIOTEKA.PRIMERAK (SIFRAPR,SLOBODAN,ISBN) values (45,1,'978-86-63-03032-9');
Insert into BIBLIOTEKA.PRIMERAK (SIFRAPR,SLOBODAN,ISBN) values (46,1,'978-86-52-11487-0');
Insert into BIBLIOTEKA.PRIMERAK (SIFRAPR,SLOBODAN,ISBN) values (47,1,'955-56-37-55355-7');
Insert into BIBLIOTEKA.PRIMERAK (SIFRAPR,SLOBODAN,ISBN) values (48,1,'955-56-37-55355-7');
Insert into BIBLIOTEKA.PRIMERAK (SIFRAPR,SLOBODAN,ISBN) values (49,1,'955-56-37-55355-7');
Insert into BIBLIOTEKA.PRIMERAK (SIFRAPR,SLOBODAN,ISBN) values (50,1,'111-32-44-33033-1');
Insert into BIBLIOTEKA.PRIMERAK (SIFRAPR,SLOBODAN,ISBN) values (51,1,'978-86-75-80306-5');
Insert into BIBLIOTEKA.PRIMERAK (SIFRAPR,SLOBODAN,ISBN) values (52,1,'978-86-61-05047-3');
Insert into BIBLIOTEKA.PRIMERAK (SIFRAPR,SLOBODAN,ISBN) values (53,1,'918-86-11-11047-6');
Insert into BIBLIOTEKA.PRIMERAK (SIFRAPR,SLOBODAN,ISBN) values (54,1,'860-02-62-00465-9');
Insert into BIBLIOTEKA.PRIMERAK (SIFRAPR,SLOBODAN,ISBN) values (55,1,'978-86-81-13124-4');
Insert into BIBLIOTEKA.PRIMERAK (SIFRAPR,SLOBODAN,ISBN) values (56,1,'978-86-52-12015-4');
Insert into BIBLIOTEKA.PRIMERAK (SIFRAPR,SLOBODAN,ISBN) values (57,1,'978-86-52-13084-8');
Insert into BIBLIOTEKA.PRIMERAK (SIFRAPR,SLOBODAN,ISBN) values (58,1,'978-86-10-03117-1');
Insert into BIBLIOTEKA.PRIMERAK (SIFRAPR,SLOBODAN,ISBN) values (59,1,'978-86-64-57309-2');
Insert into BIBLIOTEKA.PRIMERAK (SIFRAPR,SLOBODAN,ISBN) values (60,1,'978-86-83-49964-9');
Insert into BIBLIOTEKA.PRIMERAK (SIFRAPR,SLOBODAN,ISBN) values (61,1,'978-86-83-49965-6');
Insert into BIBLIOTEKA.PRIMERAK (SIFRAPR,SLOBODAN,ISBN) values (62,1,'978-86-52-13049-8');
Insert into BIBLIOTEKA.PRIMERAK (SIFRAPR,SLOBODAN,ISBN) values (63,1,'860-02-62-03172-3');
Insert into BIBLIOTEKA.PRIMERAK (SIFRAPR,SLOBODAN,ISBN) values (64,1,'978-86-52-12078-9');
Insert into BIBLIOTEKA.PRIMERAK (SIFRAPR,SLOBODAN,ISBN) values (65,1,'978-86-52-12490-9');
Insert into BIBLIOTEKA.PRIMERAK (SIFRAPR,SLOBODAN,ISBN) values (66,1,'978-86-76-09718-0');
Insert into BIBLIOTEKA.PRIMERAK (SIFRAPR,SLOBODAN,ISBN) values (67,1,'978-86-17-11339-1');
Insert into BIBLIOTEKA.PRIMERAK (SIFRAPR,SLOBODAN,ISBN) values (68,1,'955-56-37-55355-7');
Insert into BIBLIOTEKA.PRIMERAK (SIFRAPR,SLOBODAN,ISBN) values (69,1,'125-46-35-44332-6');
Insert into BIBLIOTEKA.PRIMERAK (SIFRAPR,SLOBODAN,ISBN) values (70,1,'978-86-52-30005-1');
Insert into BIBLIOTEKA.PRIMERAK (SIFRAPR,SLOBODAN,ISBN) values (71,1,'978-86-52-30002-0');
Insert into BIBLIOTEKA.PRIMERAK (SIFRAPR,SLOBODAN,ISBN) values (72,1,'978-86-60-89157-2');
Insert into BIBLIOTEKA.PRIMERAK (SIFRAPR,SLOBODAN,ISBN) values (73,1,'928-22-62-22147-2');
Insert into BIBLIOTEKA.PRIMERAK (SIFRAPR,SLOBODAN,ISBN) values (74,1,'978-86-81-13124-4');
Insert into BIBLIOTEKA.PRIMERAK (SIFRAPR,SLOBODAN,ISBN) values (75,1,'978-86-52-12015-4');
Insert into BIBLIOTEKA.PRIMERAK (SIFRAPR,SLOBODAN,ISBN) values (76,1,'978-86-52-13084-8');
Insert into BIBLIOTEKA.PRIMERAK (SIFRAPR,SLOBODAN,ISBN) values (77,1,'978-86-10-03117-1');
Insert into BIBLIOTEKA.PRIMERAK (SIFRAPR,SLOBODAN,ISBN) values (78,1,'978-86-64-57309-2');
Insert into BIBLIOTEKA.PRIMERAK (SIFRAPR,SLOBODAN,ISBN) values (79,1,'978-86-83-49964-9');
Insert into BIBLIOTEKA.PRIMERAK (SIFRAPR,SLOBODAN,ISBN) values (80,1,'978-86-83-49965-6');
Insert into BIBLIOTEKA.PRIMERAK (SIFRAPR,SLOBODAN,ISBN) values (81,1,'978-86-52-13049-8');
Insert into BIBLIOTEKA.PRIMERAK (SIFRAPR,SLOBODAN,ISBN) values (82,1,'860-02-62-03172-3');
Insert into BIBLIOTEKA.PRIMERAK (SIFRAPR,SLOBODAN,ISBN) values (83,1,'860-02-62-03541-7');
Insert into BIBLIOTEKA.PRIMERAK (SIFRAPR,SLOBODAN,ISBN) values (84,1,'978-86-37-91059-6');
Insert into BIBLIOTEKA.PRIMERAK (SIFRAPR,SLOBODAN,ISBN) values (85,1,'978-86-10-01769-4');
Insert into BIBLIOTEKA.PRIMERAK (SIFRAPR,SLOBODAN,ISBN) values (86,1,'867-05-82-57086-3');
Insert into BIBLIOTEKA.PRIMERAK (SIFRAPR,SLOBODAN,ISBN) values (87,1,'111-32-44-33033-1');
Insert into BIBLIOTEKA.PRIMERAK (SIFRAPR,SLOBODAN,ISBN) values (88,1,'978-86-75-80306-5');
Insert into BIBLIOTEKA.PRIMERAK (SIFRAPR,SLOBODAN,ISBN) values (89,1,'978-86-75-80306-5');
Insert into BIBLIOTEKA.PRIMERAK (SIFRAPR,SLOBODAN,ISBN) values (90,1,'978-86-75-80306-5');
REM INSERTING into BIBLIOTEKA.BIBLIOTEKAR
SET DEFINE OFF;
Insert into BIBLIOTEKA.BIBLIOTEKAR (EMAIL,LOZINKA,IME,PRZ,JMBG,DATUMRODJ,ADRESA,MESTOF,TIPZAPOSLENOG,NAZIVFILIJALE) values ('mina@gmail.com','minica','Mina','Petrovic','1234567890211',to_date('23-MAY-76','DD-MON-RR'),'Cede Vasovica 33',12000,'KLASICAN','Djura Danicic');
Insert into BIBLIOTEKA.BIBLIOTEKAR (EMAIL,LOZINKA,IME,PRZ,JMBG,DATUMRODJ,ADRESA,MESTOF,TIPZAPOSLENOG,NAZIVFILIJALE) values ('laki@gmail.com','malilaza','Lazar','Lazovic','1234567890311',to_date('06-JUN-86','DD-MON-RR'),'Novosadskog sajma 02',21000,'KLASICAN','Djura Danicic');
Insert into BIBLIOTEKA.BIBLIOTEKAR (EMAIL,LOZINKA,IME,PRZ,JMBG,DATUMRODJ,ADRESA,MESTOF,TIPZAPOSLENOG,NAZIVFILIJALE) values ('sara@gmail.com','sarica','Sara','Simulija','1234567890411',to_date('05-APR-70','DD-MON-RR'),'Njegoseva 4',21000,'KLASICAN','Djura Danicic');
Insert into BIBLIOTEKA.BIBLIOTEKAR (EMAIL,LOZINKA,IME,PRZ,JMBG,DATUMRODJ,ADRESA,MESTOF,TIPZAPOSLENOG,NAZIVFILIJALE) values ('mira@gmail.com','mirnamira','Mira','Stefanovic','1234567890511',to_date('15-JAN-67','DD-MON-RR'),'Vase Stajica 21',21000,'ADMINISTRATOR','Djura Danicic');
Insert into BIBLIOTEKA.BIBLIOTEKAR (EMAIL,LOZINKA,IME,PRZ,JMBG,DATUMRODJ,ADRESA,MESTOF,TIPZAPOSLENOG,NAZIVFILIJALE) values ('nidza@gmail.com','nikolicaprikolica','Nikola','Markovic','1234567890611',to_date('12-FEB-68','DD-MON-RR'),'Sumadijska 6',21000,'OBRADJIVAC','Djura Danicic');
Insert into BIBLIOTEKA.BIBLIOTEKAR (EMAIL,LOZINKA,IME,PRZ,JMBG,DATUMRODJ,ADRESA,MESTOF,TIPZAPOSLENOG,NAZIVFILIJALE) values ('steva@gmail.com','stevica','Steva','Stevic','1234567890711',to_date('17-AUG-55','DD-MON-RR'),'Partizanska 7',22400,'ADMINISTRATOR','Djura Danicic');
Insert into BIBLIOTEKA.BIBLIOTEKAR (EMAIL,LOZINKA,IME,PRZ,JMBG,DATUMRODJ,ADRESA,MESTOF,TIPZAPOSLENOG,NAZIVFILIJALE) values ('dana@gmail.com','danica','Danijela','Milekic','1234567890811',to_date('30-OCT-88','DD-MON-RR'),'Ivo Lole Ribara 12',12000,'KLASICAN','Djura Danicic');
Insert into BIBLIOTEKA.BIBLIOTEKAR (EMAIL,LOZINKA,IME,PRZ,JMBG,DATUMRODJ,ADRESA,MESTOF,TIPZAPOSLENOG,NAZIVFILIJALE) values ('lara@gmai.com','2CDU95JPYGYFODJABR','Lara','Laric','1234567890987',to_date('03-JUN-03','DD-MON-RR'),'Neka adresa 3',21000,'OBRADJIVAC','Zarko Zrenjanin');
REM INSERTING into BIBLIOTEKA.ZAHTEVZAREZERVACIJU
SET DEFINE OFF;
Insert into BIBLIOTEKA.ZAHTEVZAREZERVACIJU (EMAIL,ISBN,DATUMZAH) values ('miha@gmail.com','978-86-52-12744-3',to_date('05-JUN-21','DD-MON-RR'));
Insert into BIBLIOTEKA.ZAHTEVZAREZERVACIJU (EMAIL,ISBN,DATUMZAH) values ('milan@gmail.com','978-86-52-12490-9',to_date('09-JUN-21','DD-MON-RR'));
Insert into BIBLIOTEKA.ZAHTEVZAREZERVACIJU (EMAIL,ISBN,DATUMZAH) values ('sofi@gmail.com','978-86-60-89328-5',to_date('12-JUN-21','DD-MON-RR'));
Insert into BIBLIOTEKA.ZAHTEVZAREZERVACIJU (EMAIL,ISBN,DATUMZAH) values ('zoka@gmail.com','978-86-10-00127-3',to_date('13-JUN-21','DD-MON-RR'));
Insert into BIBLIOTEKA.ZAHTEVZAREZERVACIJU (EMAIL,ISBN,DATUMZAH) values ('zoka@gmail.com','978-86-10-02436-4',to_date('14-JUN-21','DD-MON-RR'));
Insert into BIBLIOTEKA.ZAHTEVZAREZERVACIJU (EMAIL,ISBN,DATUMZAH) values ('zoka@gmail.com','978-86-52-12215-8',to_date('30-JUN-21','DD-MON-RR'));
REM INSERTING into BIBLIOTEKA.RECENZIJA
SET DEFINE OFF;
Insert into BIBLIOTEKA.RECENZIJA (EMAIL,ISBN,OCENA) values ('nevena@gmail.com','860-02-62-03541-7',3);
Insert into BIBLIOTEKA.RECENZIJA (EMAIL,ISBN,OCENA) values ('zoka@gmail.com','978-86-52-12015-4',0);
Insert into BIBLIOTEKA.RECENZIJA (EMAIL,ISBN,OCENA) values ('zoka@gmail.com','978-86-52-12215-8',5);
Insert into BIBLIOTEKA.RECENZIJA (EMAIL,ISBN,OCENA) values ('nevena@gmail.com','978-86-52-12885-3',5);
Insert into BIBLIOTEKA.RECENZIJA (EMAIL,ISBN,OCENA) values ('miha@gmail.com','978-03-45-47232-8',5);
Insert into BIBLIOTEKA.RECENZIJA (EMAIL,ISBN,OCENA) values ('sofi@gmail.com','125-46-35-44332-6',2);
Insert into BIBLIOTEKA.RECENZIJA (EMAIL,ISBN,OCENA) values ('milan@gmail.com','918-86-11-11047-6',4);
Insert into BIBLIOTEKA.RECENZIJA (EMAIL,ISBN,OCENA) values ('joca@gmail.com','978-86-52-11487-0',5);
Insert into BIBLIOTEKA.RECENZIJA (EMAIL,ISBN,OCENA) values ('zoka@gmail.com','978-86-521-3555-4',3);
Insert into BIBLIOTEKA.RECENZIJA (EMAIL,ISBN,OCENA) values ('zoka@gmail.com','978-37-88-76153-5',4);
Insert into BIBLIOTEKA.RECENZIJA (EMAIL,ISBN,OCENA) values ('nevena@gmail.com','911-16-11-10157-3',4);
Insert into BIBLIOTEKA.RECENZIJA (EMAIL,ISBN,OCENA) values ('joca@gmail.com','978-86-52-13239-3',3);
REM INSERTING into BIBLIOTEKA.MESTOPRIMERKA
SET DEFINE OFF;
Insert into BIBLIOTEKA.MESTOPRIMERKA (SIFRAPR,NAZIVF,BROJPOLICE,BROJREDA) values (1,'Djura Danicic',1,2);
Insert into BIBLIOTEKA.MESTOPRIMERKA (SIFRAPR,NAZIVF,BROJPOLICE,BROJREDA) values (2,'Kosta Trifkovic',2,4);
Insert into BIBLIOTEKA.MESTOPRIMERKA (SIFRAPR,NAZIVF,BROJPOLICE,BROJREDA) values (3,'Stevan Sremac',2,3);
Insert into BIBLIOTEKA.MESTOPRIMERKA (SIFRAPR,NAZIVF,BROJPOLICE,BROJREDA) values (4,'Vladimir Nazor',1,2);
Insert into BIBLIOTEKA.MESTOPRIMERKA (SIFRAPR,NAZIVF,BROJPOLICE,BROJREDA) values (5,'Anastasije Stojkovic',2,3);
Insert into BIBLIOTEKA.MESTOPRIMERKA (SIFRAPR,NAZIVF,BROJPOLICE,BROJREDA) values (6,'Ilija M. Petrovic',1,2);
Insert into BIBLIOTEKA.MESTOPRIMERKA (SIFRAPR,NAZIVF,BROJPOLICE,BROJREDA) values (7,'Ilija M. Petrovic',7,3);
Insert into BIBLIOTEKA.MESTOPRIMERKA (SIFRAPR,NAZIVF,BROJPOLICE,BROJREDA) values (8,'Stevan Sremac',5,3);
Insert into BIBLIOTEKA.MESTOPRIMERKA (SIFRAPR,NAZIVF,BROJPOLICE,BROJREDA) values (9,'Stevan Sremac',1,3);
Insert into BIBLIOTEKA.MESTOPRIMERKA (SIFRAPR,NAZIVF,BROJPOLICE,BROJREDA) values (10,'Stevan Sremac',1,4);
Insert into BIBLIOTEKA.MESTOPRIMERKA (SIFRAPR,NAZIVF,BROJPOLICE,BROJREDA) values (11,'Stevan Sremac',1,6);
Insert into BIBLIOTEKA.MESTOPRIMERKA (SIFRAPR,NAZIVF,BROJPOLICE,BROJREDA) values (12,'Stevan Sremac',1,7);
Insert into BIBLIOTEKA.MESTOPRIMERKA (SIFRAPR,NAZIVF,BROJPOLICE,BROJREDA) values (13,'Stevan Sremac',1,8);
Insert into BIBLIOTEKA.MESTOPRIMERKA (SIFRAPR,NAZIVF,BROJPOLICE,BROJREDA) values (14,'Stevan Sremac',1,9);
Insert into BIBLIOTEKA.MESTOPRIMERKA (SIFRAPR,NAZIVF,BROJPOLICE,BROJREDA) values (15,'Stevan Sremac',1,10);
Insert into BIBLIOTEKA.MESTOPRIMERKA (SIFRAPR,NAZIVF,BROJPOLICE,BROJREDA) values (16,'Stevan Sremac',1,11);
Insert into BIBLIOTEKA.MESTOPRIMERKA (SIFRAPR,NAZIVF,BROJPOLICE,BROJREDA) values (17,'Stevan Sremac',1,12);
Insert into BIBLIOTEKA.MESTOPRIMERKA (SIFRAPR,NAZIVF,BROJPOLICE,BROJREDA) values (18,'Stevan Sremac',1,13);
Insert into BIBLIOTEKA.MESTOPRIMERKA (SIFRAPR,NAZIVF,BROJPOLICE,BROJREDA) values (19,'Stevan Sremac',1,14);
Insert into BIBLIOTEKA.MESTOPRIMERKA (SIFRAPR,NAZIVF,BROJPOLICE,BROJREDA) values (20,'Stevan Sremac',1,15);
Insert into BIBLIOTEKA.MESTOPRIMERKA (SIFRAPR,NAZIVF,BROJPOLICE,BROJREDA) values (21,'Stevan Sremac',2,1);
Insert into BIBLIOTEKA.MESTOPRIMERKA (SIFRAPR,NAZIVF,BROJPOLICE,BROJREDA) values (22,'Stevan Sremac',2,2);
Insert into BIBLIOTEKA.MESTOPRIMERKA (SIFRAPR,NAZIVF,BROJPOLICE,BROJREDA) values (23,'Stevan Sremac',2,3);
Insert into BIBLIOTEKA.MESTOPRIMERKA (SIFRAPR,NAZIVF,BROJPOLICE,BROJREDA) values (24,'Stevan Sremac',2,4);
Insert into BIBLIOTEKA.MESTOPRIMERKA (SIFRAPR,NAZIVF,BROJPOLICE,BROJREDA) values (25,'Stevan Sremac',2,5);
Insert into BIBLIOTEKA.MESTOPRIMERKA (SIFRAPR,NAZIVF,BROJPOLICE,BROJREDA) values (26,'Stevan Sremac',2,6);
Insert into BIBLIOTEKA.MESTOPRIMERKA (SIFRAPR,NAZIVF,BROJPOLICE,BROJREDA) values (27,'Stevan Sremac',2,7);
Insert into BIBLIOTEKA.MESTOPRIMERKA (SIFRAPR,NAZIVF,BROJPOLICE,BROJREDA) values (28,'Stevan Sremac',2,8);
Insert into BIBLIOTEKA.MESTOPRIMERKA (SIFRAPR,NAZIVF,BROJPOLICE,BROJREDA) values (29,'Stevan Sremac',2,9);
Insert into BIBLIOTEKA.MESTOPRIMERKA (SIFRAPR,NAZIVF,BROJPOLICE,BROJREDA) values (30,'Stevan Sremac',2,10);
Insert into BIBLIOTEKA.MESTOPRIMERKA (SIFRAPR,NAZIVF,BROJPOLICE,BROJREDA) values (31,'Stevan Sremac',2,11);
Insert into BIBLIOTEKA.MESTOPRIMERKA (SIFRAPR,NAZIVF,BROJPOLICE,BROJREDA) values (32,'Stevan Sremac',2,12);
Insert into BIBLIOTEKA.MESTOPRIMERKA (SIFRAPR,NAZIVF,BROJPOLICE,BROJREDA) values (33,'Stevan Sremac',2,13);
Insert into BIBLIOTEKA.MESTOPRIMERKA (SIFRAPR,NAZIVF,BROJPOLICE,BROJREDA) values (34,'Stevan Sremac',2,14);
Insert into BIBLIOTEKA.MESTOPRIMERKA (SIFRAPR,NAZIVF,BROJPOLICE,BROJREDA) values (35,'Stevan Sremac',2,15);
Insert into BIBLIOTEKA.MESTOPRIMERKA (SIFRAPR,NAZIVF,BROJPOLICE,BROJREDA) values (36,'Stevan Sremac',3,1);
Insert into BIBLIOTEKA.MESTOPRIMERKA (SIFRAPR,NAZIVF,BROJPOLICE,BROJREDA) values (37,'Stevan Sremac',3,2);
Insert into BIBLIOTEKA.MESTOPRIMERKA (SIFRAPR,NAZIVF,BROJPOLICE,BROJREDA) values (38,'Stevan Sremac',3,3);
Insert into BIBLIOTEKA.MESTOPRIMERKA (SIFRAPR,NAZIVF,BROJPOLICE,BROJREDA) values (39,'Stevan Sremac',3,4);
Insert into BIBLIOTEKA.MESTOPRIMERKA (SIFRAPR,NAZIVF,BROJPOLICE,BROJREDA) values (40,'Stevan Sremac',3,5);
Insert into BIBLIOTEKA.MESTOPRIMERKA (SIFRAPR,NAZIVF,BROJPOLICE,BROJREDA) values (41,'Stevan Sremac',3,6);
Insert into BIBLIOTEKA.MESTOPRIMERKA (SIFRAPR,NAZIVF,BROJPOLICE,BROJREDA) values (42,'Stevan Sremac',3,7);
Insert into BIBLIOTEKA.MESTOPRIMERKA (SIFRAPR,NAZIVF,BROJPOLICE,BROJREDA) values (43,'Stevan Sremac',3,8);
Insert into BIBLIOTEKA.MESTOPRIMERKA (SIFRAPR,NAZIVF,BROJPOLICE,BROJREDA) values (44,'Stevan Sremac',3,9);
Insert into BIBLIOTEKA.MESTOPRIMERKA (SIFRAPR,NAZIVF,BROJPOLICE,BROJREDA) values (45,'Stevan Sremac',3,10);
Insert into BIBLIOTEKA.MESTOPRIMERKA (SIFRAPR,NAZIVF,BROJPOLICE,BROJREDA) values (46,'Stevan Sremac',3,11);
Insert into BIBLIOTEKA.MESTOPRIMERKA (SIFRAPR,NAZIVF,BROJPOLICE,BROJREDA) values (47,'Stevan Sremac',3,12);
Insert into BIBLIOTEKA.MESTOPRIMERKA (SIFRAPR,NAZIVF,BROJPOLICE,BROJREDA) values (48,'Stevan Sremac',3,13);
Insert into BIBLIOTEKA.MESTOPRIMERKA (SIFRAPR,NAZIVF,BROJPOLICE,BROJREDA) values (49,'Stevan Sremac',3,14);
Insert into BIBLIOTEKA.MESTOPRIMERKA (SIFRAPR,NAZIVF,BROJPOLICE,BROJREDA) values (50,'Stevan Sremac',3,15);
Insert into BIBLIOTEKA.MESTOPRIMERKA (SIFRAPR,NAZIVF,BROJPOLICE,BROJREDA) values (51,'Stevan Sremac',4,1);
Insert into BIBLIOTEKA.MESTOPRIMERKA (SIFRAPR,NAZIVF,BROJPOLICE,BROJREDA) values (52,'Stevan Sremac',4,2);
Insert into BIBLIOTEKA.MESTOPRIMERKA (SIFRAPR,NAZIVF,BROJPOLICE,BROJREDA) values (53,'Stevan Sremac',4,3);
Insert into BIBLIOTEKA.MESTOPRIMERKA (SIFRAPR,NAZIVF,BROJPOLICE,BROJREDA) values (54,'Stevan Sremac',4,4);
Insert into BIBLIOTEKA.MESTOPRIMERKA (SIFRAPR,NAZIVF,BROJPOLICE,BROJREDA) values (55,'Stevan Sremac',4,5);
Insert into BIBLIOTEKA.MESTOPRIMERKA (SIFRAPR,NAZIVF,BROJPOLICE,BROJREDA) values (56,'Stevan Sremac',4,6);
Insert into BIBLIOTEKA.MESTOPRIMERKA (SIFRAPR,NAZIVF,BROJPOLICE,BROJREDA) values (57,'Stevan Sremac',4,7);
Insert into BIBLIOTEKA.MESTOPRIMERKA (SIFRAPR,NAZIVF,BROJPOLICE,BROJREDA) values (58,'Stevan Sremac',4,8);
Insert into BIBLIOTEKA.MESTOPRIMERKA (SIFRAPR,NAZIVF,BROJPOLICE,BROJREDA) values (59,'Stevan Sremac',4,9);
Insert into BIBLIOTEKA.MESTOPRIMERKA (SIFRAPR,NAZIVF,BROJPOLICE,BROJREDA) values (60,'Stevan Sremac',4,10);
Insert into BIBLIOTEKA.MESTOPRIMERKA (SIFRAPR,NAZIVF,BROJPOLICE,BROJREDA) values (61,'Stevan Sremac',4,11);
Insert into BIBLIOTEKA.MESTOPRIMERKA (SIFRAPR,NAZIVF,BROJPOLICE,BROJREDA) values (62,'Stevan Sremac',4,12);
Insert into BIBLIOTEKA.MESTOPRIMERKA (SIFRAPR,NAZIVF,BROJPOLICE,BROJREDA) values (63,'Stevan Sremac',4,13);
Insert into BIBLIOTEKA.MESTOPRIMERKA (SIFRAPR,NAZIVF,BROJPOLICE,BROJREDA) values (64,'Stevan Sremac',4,14);
Insert into BIBLIOTEKA.MESTOPRIMERKA (SIFRAPR,NAZIVF,BROJPOLICE,BROJREDA) values (65,'Stevan Sremac',4,15);
Insert into BIBLIOTEKA.MESTOPRIMERKA (SIFRAPR,NAZIVF,BROJPOLICE,BROJREDA) values (66,'Danilo Kis',2,5);
Insert into BIBLIOTEKA.MESTOPRIMERKA (SIFRAPR,NAZIVF,BROJPOLICE,BROJREDA) values (67,'Danilo Kis',2,6);
Insert into BIBLIOTEKA.MESTOPRIMERKA (SIFRAPR,NAZIVF,BROJPOLICE,BROJREDA) values (68,'Danilo Kis',2,7);
Insert into BIBLIOTEKA.MESTOPRIMERKA (SIFRAPR,NAZIVF,BROJPOLICE,BROJREDA) values (69,'Danilo Kis',2,7);
Insert into BIBLIOTEKA.MESTOPRIMERKA (SIFRAPR,NAZIVF,BROJPOLICE,BROJREDA) values (70,'Danilo Kis',2,7);
Insert into BIBLIOTEKA.MESTOPRIMERKA (SIFRAPR,NAZIVF,BROJPOLICE,BROJREDA) values (71,'Danilo Kis',2,7);
Insert into BIBLIOTEKA.MESTOPRIMERKA (SIFRAPR,NAZIVF,BROJPOLICE,BROJREDA) values (72,'Danilo Kis',2,8);
Insert into BIBLIOTEKA.MESTOPRIMERKA (SIFRAPR,NAZIVF,BROJPOLICE,BROJREDA) values (73,'Danilo Kis',2,8);
Insert into BIBLIOTEKA.MESTOPRIMERKA (SIFRAPR,NAZIVF,BROJPOLICE,BROJREDA) values (74,'Danilo Kis',2,8);
Insert into BIBLIOTEKA.MESTOPRIMERKA (SIFRAPR,NAZIVF,BROJPOLICE,BROJREDA) values (75,'Danilo Kis',2,8);
Insert into BIBLIOTEKA.MESTOPRIMERKA (SIFRAPR,NAZIVF,BROJPOLICE,BROJREDA) values (76,'Danilo Kis',2,8);
Insert into BIBLIOTEKA.MESTOPRIMERKA (SIFRAPR,NAZIVF,BROJPOLICE,BROJREDA) values (77,'Danilo Kis',2,5);
Insert into BIBLIOTEKA.MESTOPRIMERKA (SIFRAPR,NAZIVF,BROJPOLICE,BROJREDA) values (78,'Danilo Kis',2,5);
Insert into BIBLIOTEKA.MESTOPRIMERKA (SIFRAPR,NAZIVF,BROJPOLICE,BROJREDA) values (79,'Danilo Kis',2,6);
Insert into BIBLIOTEKA.MESTOPRIMERKA (SIFRAPR,NAZIVF,BROJPOLICE,BROJREDA) values (80,'Danilo Kis',2,6);
Insert into BIBLIOTEKA.MESTOPRIMERKA (SIFRAPR,NAZIVF,BROJPOLICE,BROJREDA) values (81,'Danilo Kis',2,9);
Insert into BIBLIOTEKA.MESTOPRIMERKA (SIFRAPR,NAZIVF,BROJPOLICE,BROJREDA) values (82,'Danilo Kis',2,9);
Insert into BIBLIOTEKA.MESTOPRIMERKA (SIFRAPR,NAZIVF,BROJPOLICE,BROJREDA) values (83,'Danilo Kis',2,9);
Insert into BIBLIOTEKA.MESTOPRIMERKA (SIFRAPR,NAZIVF,BROJPOLICE,BROJREDA) values (84,'Danilo Kis',2,9);
Insert into BIBLIOTEKA.MESTOPRIMERKA (SIFRAPR,NAZIVF,BROJPOLICE,BROJREDA) values (85,'Danilo Kis',2,9);
Insert into BIBLIOTEKA.MESTOPRIMERKA (SIFRAPR,NAZIVF,BROJPOLICE,BROJREDA) values (86,'Danilo Kis',2,9);
Insert into BIBLIOTEKA.MESTOPRIMERKA (SIFRAPR,NAZIVF,BROJPOLICE,BROJREDA) values (87,'Danilo Kis',2,9);
Insert into BIBLIOTEKA.MESTOPRIMERKA (SIFRAPR,NAZIVF,BROJPOLICE,BROJREDA) values (88,'Danilo Kis',2,10);
Insert into BIBLIOTEKA.MESTOPRIMERKA (SIFRAPR,NAZIVF,BROJPOLICE,BROJREDA) values (89,'Danilo Kis',2,10);
Insert into BIBLIOTEKA.MESTOPRIMERKA (SIFRAPR,NAZIVF,BROJPOLICE,BROJREDA) values (90,'Danilo Kis',2,10);
REM INSERTING into BIBLIOTEKA.AUTORKNJIGE
SET DEFINE OFF;
Insert into BIBLIOTEKA.AUTORKNJIGE (ISBN,IDAUTOR) values ('111-32-44-33033-1',6);
Insert into BIBLIOTEKA.AUTORKNJIGE (ISBN,IDAUTOR) values ('125-46-35-44332-6',14);
Insert into BIBLIOTEKA.AUTORKNJIGE (ISBN,IDAUTOR) values ('128-46-13-16435-4',1);
Insert into BIBLIOTEKA.AUTORKNJIGE (ISBN,IDAUTOR) values ('860-02-62-00465-9',7);
Insert into BIBLIOTEKA.AUTORKNJIGE (ISBN,IDAUTOR) values ('860-02-62-03172-3',10);
Insert into BIBLIOTEKA.AUTORKNJIGE (ISBN,IDAUTOR) values ('860-02-62-03541-7',4);
Insert into BIBLIOTEKA.AUTORKNJIGE (ISBN,IDAUTOR) values ('867-05-82-57086-3',5);
Insert into BIBLIOTEKA.AUTORKNJIGE (ISBN,IDAUTOR) values ('911-16-11-10157-3',1);
Insert into BIBLIOTEKA.AUTORKNJIGE (ISBN,IDAUTOR) values ('918-86-11-11047-6',7);
Insert into BIBLIOTEKA.AUTORKNJIGE (ISBN,IDAUTOR) values ('928-22-62-22147-2',15);
Insert into BIBLIOTEKA.AUTORKNJIGE (ISBN,IDAUTOR) values ('955-56-37-55355-7',13);
Insert into BIBLIOTEKA.AUTORKNJIGE (ISBN,IDAUTOR) values ('978-03-45-47232-8',20);
Insert into BIBLIOTEKA.AUTORKNJIGE (ISBN,IDAUTOR) values ('978-37-88-76153-5',18);
Insert into BIBLIOTEKA.AUTORKNJIGE (ISBN,IDAUTOR) values ('978-86-10-00127-3',1);
Insert into BIBLIOTEKA.AUTORKNJIGE (ISBN,IDAUTOR) values ('978-86-10-01769-4',4);
Insert into BIBLIOTEKA.AUTORKNJIGE (ISBN,IDAUTOR) values ('978-86-10-02436-4',1);
Insert into BIBLIOTEKA.AUTORKNJIGE (ISBN,IDAUTOR) values ('978-86-10-03117-1',8);
Insert into BIBLIOTEKA.AUTORKNJIGE (ISBN,IDAUTOR) values ('978-86-17-11339-1',12);
Insert into BIBLIOTEKA.AUTORKNJIGE (ISBN,IDAUTOR) values ('978-86-37-91059-6',4);
Insert into BIBLIOTEKA.AUTORKNJIGE (ISBN,IDAUTOR) values ('978-86-44-10257-5',12);
Insert into BIBLIOTEKA.AUTORKNJIGE (ISBN,IDAUTOR) values ('978-86-52-11487-0',24);
Insert into BIBLIOTEKA.AUTORKNJIGE (ISBN,IDAUTOR) values ('978-86-52-12015-4',8);
Insert into BIBLIOTEKA.AUTORKNJIGE (ISBN,IDAUTOR) values ('978-86-52-12078-9',11);
Insert into BIBLIOTEKA.AUTORKNJIGE (ISBN,IDAUTOR) values ('978-86-52-12215-8',17);
Insert into BIBLIOTEKA.AUTORKNJIGE (ISBN,IDAUTOR) values ('978-86-52-12490-9',11);
Insert into BIBLIOTEKA.AUTORKNJIGE (ISBN,IDAUTOR) values ('978-86-52-12744-3',19);
Insert into BIBLIOTEKA.AUTORKNJIGE (ISBN,IDAUTOR) values ('978-86-52-12858-7',22);
Insert into BIBLIOTEKA.AUTORKNJIGE (ISBN,IDAUTOR) values ('978-86-52-12885-3',15);
Insert into BIBLIOTEKA.AUTORKNJIGE (ISBN,IDAUTOR) values ('978-86-52-13049-8',10);
Insert into BIBLIOTEKA.AUTORKNJIGE (ISBN,IDAUTOR) values ('978-86-52-13084-8',8);
Insert into BIBLIOTEKA.AUTORKNJIGE (ISBN,IDAUTOR) values ('978-86-52-13239-3',3);
Insert into BIBLIOTEKA.AUTORKNJIGE (ISBN,IDAUTOR) values ('978-86-52-13475-5',2);
Insert into BIBLIOTEKA.AUTORKNJIGE (ISBN,IDAUTOR) values ('978-86-52-30002-0',14);
Insert into BIBLIOTEKA.AUTORKNJIGE (ISBN,IDAUTOR) values ('978-86-52-30005-1',14);
Insert into BIBLIOTEKA.AUTORKNJIGE (ISBN,IDAUTOR) values ('978-86-521-3555-4',1);
Insert into BIBLIOTEKA.AUTORKNJIGE (ISBN,IDAUTOR) values ('978-86-60-89157-2',15);
Insert into BIBLIOTEKA.AUTORKNJIGE (ISBN,IDAUTOR) values ('978-86-60-89328-5',15);
Insert into BIBLIOTEKA.AUTORKNJIGE (ISBN,IDAUTOR) values ('978-86-61-05047-3',7);
Insert into BIBLIOTEKA.AUTORKNJIGE (ISBN,IDAUTOR) values ('978-86-63-03032-9',23);
Insert into BIBLIOTEKA.AUTORKNJIGE (ISBN,IDAUTOR) values ('978-86-64-57309-2',8);
Insert into BIBLIOTEKA.AUTORKNJIGE (ISBN,IDAUTOR) values ('978-86-75-80306-5',7);
Insert into BIBLIOTEKA.AUTORKNJIGE (ISBN,IDAUTOR) values ('978-86-76-09718-0',12);
Insert into BIBLIOTEKA.AUTORKNJIGE (ISBN,IDAUTOR) values ('978-86-77-77233-1',21);
Insert into BIBLIOTEKA.AUTORKNJIGE (ISBN,IDAUTOR) values ('978-86-81-13124-4',7);
Insert into BIBLIOTEKA.AUTORKNJIGE (ISBN,IDAUTOR) values ('978-86-83-49964-9',9);
Insert into BIBLIOTEKA.AUTORKNJIGE (ISBN,IDAUTOR) values ('978-86-83-49965-6',9);
Insert into BIBLIOTEKA.AUTORKNJIGE (ISBN,IDAUTOR) values ('978-86-86-61149-9',25);
Insert into BIBLIOTEKA.AUTORKNJIGE (ISBN,IDAUTOR) values ('978-86-89-20376-9',16);
REM INSERTING into BIBLIOTEKA.BIBLIOTEKA
SET DEFINE OFF;
Insert into BIBLIOTEKA.BIBLIOTEKA (NAZIVB) values ('Gradska biblioteka SIMS');
--------------------------------------------------------
--  DDL for Index AUTOR_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "BIBLIOTEKA"."AUTOR_PK" ON "BIBLIOTEKA"."AUTOR" ("IDAUTOR") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index MESTO_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "BIBLIOTEKA"."MESTO_PK" ON "BIBLIOTEKA"."MESTO" ("PTTBROJ") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index PROZAJMICA_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "BIBLIOTEKA"."PROZAJMICA_PK" ON "BIBLIOTEKA"."POZAJMICA" ("EMAIL", "SIFRAPR", "DATUMPOZ") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index REZERVACIJA_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "BIBLIOTEKA"."REZERVACIJA_PK" ON "BIBLIOTEKA"."REZERVACIJA" ("EMAIL", "ISBN", "DATUMZAH", "SIFRAPR") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index EMAIL_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "BIBLIOTEKA"."EMAIL_PK" ON "BIBLIOTEKA"."CLAN" ("EMAIL") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index JMBG_UQ
--------------------------------------------------------

  CREATE UNIQUE INDEX "BIBLIOTEKA"."JMBG_UQ" ON "BIBLIOTEKA"."CLAN" ("JMBG") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index FILIJALA_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "BIBLIOTEKA"."FILIJALA_PK" ON "BIBLIOTEKA"."FILIJALA" ("NAZIVF") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index KNJIGA_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "BIBLIOTEKA"."KNJIGA_PK" ON "BIBLIOTEKA"."KNJIGA" ("ISBN") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index PRIMERAK_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "BIBLIOTEKA"."PRIMERAK_PK" ON "BIBLIOTEKA"."PRIMERAK" ("SIFRAPR") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index EMAIL_BIBLIOTEKAR_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "BIBLIOTEKA"."EMAIL_BIBLIOTEKAR_PK" ON "BIBLIOTEKA"."BIBLIOTEKAR" ("EMAIL") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index JMBG_UNKQ
--------------------------------------------------------

  CREATE UNIQUE INDEX "BIBLIOTEKA"."JMBG_UNKQ" ON "BIBLIOTEKA"."BIBLIOTEKAR" ("JMBG") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index ZAHTEV_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "BIBLIOTEKA"."ZAHTEV_PK" ON "BIBLIOTEKA"."ZAHTEVZAREZERVACIJU" ("EMAIL", "ISBN", "DATUMZAH") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index RECENZIJA_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "BIBLIOTEKA"."RECENZIJA_PK" ON "BIBLIOTEKA"."RECENZIJA" ("EMAIL", "ISBN") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index MESTO_PRIMERKA_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "BIBLIOTEKA"."MESTO_PRIMERKA_PK" ON "BIBLIOTEKA"."MESTOPRIMERKA" ("SIFRAPR", "NAZIVF") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index AUTOR_KNJIGA_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "BIBLIOTEKA"."AUTOR_KNJIGA_PK" ON "BIBLIOTEKA"."AUTORKNJIGE" ("ISBN", "IDAUTOR") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index BIBLIOTEKA_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "BIBLIOTEKA"."BIBLIOTEKA_PK" ON "BIBLIOTEKA"."BIBLIOTEKA" ("NAZIVB") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Trigger RECENZIJA_KNJIGE_TRG
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "BIBLIOTEKA"."RECENZIJA_KNJIGE_TRG" 
AFTER INSERT
ON recenzija
FOR EACH ROW
DECLARE 
    vrednost number(8,2);
    nova_ocena number(8, 2);
BEGIN
    select k.ocena into vrednost from knjiga k where k.isbn = :NEW.isbn;
    if vrednost = 0 then
        nova_ocena :=  (vrednost + :NEW.ocena);
    else
         nova_ocena := (vrednost + :NEW.ocena) / 2;
    end if;

    IF INSERTING THEN
         UPDATE knjiga k SET k.ocena = nova_ocena where :NEW.isbn = k.isbn;
    END IF;
END Recenzija_Knjige_Trg;
/
ALTER TRIGGER "BIBLIOTEKA"."RECENZIJA_KNJIGE_TRG" ENABLE;
--------------------------------------------------------
--  Constraints for Table AUTOR
--------------------------------------------------------

  ALTER TABLE "BIBLIOTEKA"."AUTOR" ADD CONSTRAINT "AUTOR_PK" PRIMARY KEY ("IDAUTOR")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
  ALTER TABLE "BIBLIOTEKA"."AUTOR" MODIFY ("PRZ" NOT NULL ENABLE);
  ALTER TABLE "BIBLIOTEKA"."AUTOR" MODIFY ("IME" NOT NULL ENABLE);
  ALTER TABLE "BIBLIOTEKA"."AUTOR" MODIFY ("IDAUTOR" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table MESTO
--------------------------------------------------------

  ALTER TABLE "BIBLIOTEKA"."MESTO" ADD CONSTRAINT "MESTO_PK" PRIMARY KEY ("PTTBROJ")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
  ALTER TABLE "BIBLIOTEKA"."MESTO" MODIFY ("NAZIVMESTA" NOT NULL ENABLE);
  ALTER TABLE "BIBLIOTEKA"."MESTO" MODIFY ("PTTBROJ" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table POZAJMICA
--------------------------------------------------------

  ALTER TABLE "BIBLIOTEKA"."POZAJMICA" ADD CONSTRAINT "PROZAJMICA_PK" PRIMARY KEY ("EMAIL", "SIFRAPR", "DATUMPOZ")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
  ALTER TABLE "BIBLIOTEKA"."POZAJMICA" ADD CONSTRAINT "CK_POZAJMICA" CHECK (Ostecena IN (1,0)) ENABLE;
  ALTER TABLE "BIBLIOTEKA"."POZAJMICA" MODIFY ("DATUMPOZ" NOT NULL ENABLE);
  ALTER TABLE "BIBLIOTEKA"."POZAJMICA" MODIFY ("SIFRAPR" NOT NULL ENABLE);
  ALTER TABLE "BIBLIOTEKA"."POZAJMICA" MODIFY ("EMAIL" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table REZERVACIJA
--------------------------------------------------------

  ALTER TABLE "BIBLIOTEKA"."REZERVACIJA" ADD CONSTRAINT "REZERVACIJA_PK" PRIMARY KEY ("EMAIL", "ISBN", "DATUMZAH", "SIFRAPR")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
  ALTER TABLE "BIBLIOTEKA"."REZERVACIJA" MODIFY ("DATUMREZ" NOT NULL ENABLE);
  ALTER TABLE "BIBLIOTEKA"."REZERVACIJA" MODIFY ("SIFRAPR" NOT NULL ENABLE);
  ALTER TABLE "BIBLIOTEKA"."REZERVACIJA" MODIFY ("DATUMZAH" NOT NULL ENABLE);
  ALTER TABLE "BIBLIOTEKA"."REZERVACIJA" MODIFY ("ISBN" NOT NULL ENABLE);
  ALTER TABLE "BIBLIOTEKA"."REZERVACIJA" MODIFY ("EMAIL" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table CLAN
--------------------------------------------------------

  ALTER TABLE "BIBLIOTEKA"."CLAN" ADD CONSTRAINT "JMBG_UQ" UNIQUE ("JMBG")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
  ALTER TABLE "BIBLIOTEKA"."CLAN" ADD CONSTRAINT "EMAIL_PK" PRIMARY KEY ("EMAIL")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
  ALTER TABLE "BIBLIOTEKA"."CLAN" ADD CHECK ( TipClana IN ('PREDSKOLAC', 'DJAK', 'STUDENT', 'ZAPOSLENI', 'PENZIONER', 'POCASNI')) ENABLE;
  ALTER TABLE "BIBLIOTEKA"."CLAN" MODIFY ("TIPCLANA" NOT NULL ENABLE);
  ALTER TABLE "BIBLIOTEKA"."CLAN" MODIFY ("MESTOF" NOT NULL ENABLE);
  ALTER TABLE "BIBLIOTEKA"."CLAN" MODIFY ("ADRESA" NOT NULL ENABLE);
  ALTER TABLE "BIBLIOTEKA"."CLAN" MODIFY ("DATUMRODJ" NOT NULL ENABLE);
  ALTER TABLE "BIBLIOTEKA"."CLAN" MODIFY ("JMBG" NOT NULL ENABLE);
  ALTER TABLE "BIBLIOTEKA"."CLAN" MODIFY ("PRZ" NOT NULL ENABLE);
  ALTER TABLE "BIBLIOTEKA"."CLAN" MODIFY ("IME" NOT NULL ENABLE);
  ALTER TABLE "BIBLIOTEKA"."CLAN" MODIFY ("LOZINKA" NOT NULL ENABLE);
  ALTER TABLE "BIBLIOTEKA"."CLAN" MODIFY ("EMAIL" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table FILIJALA
--------------------------------------------------------

  ALTER TABLE "BIBLIOTEKA"."FILIJALA" ADD CONSTRAINT "FILIJALA_PK" PRIMARY KEY ("NAZIVF")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
  ALTER TABLE "BIBLIOTEKA"."FILIJALA" MODIFY ("MESTOF" NOT NULL ENABLE);
  ALTER TABLE "BIBLIOTEKA"."FILIJALA" MODIFY ("SUBOTA" NOT NULL ENABLE);
  ALTER TABLE "BIBLIOTEKA"."FILIJALA" MODIFY ("RADNIDANI" NOT NULL ENABLE);
  ALTER TABLE "BIBLIOTEKA"."FILIJALA" MODIFY ("NAZIVBIBLIOTEKA" NOT NULL ENABLE);
  ALTER TABLE "BIBLIOTEKA"."FILIJALA" MODIFY ("ADRESA" NOT NULL ENABLE);
  ALTER TABLE "BIBLIOTEKA"."FILIJALA" MODIFY ("NAZIVF" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table KNJIGA
--------------------------------------------------------

  ALTER TABLE "BIBLIOTEKA"."KNJIGA" ADD CONSTRAINT "KNJIGA_PK" PRIMARY KEY ("ISBN")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
  ALTER TABLE "BIBLIOTEKA"."KNJIGA" ADD CHECK (Tip IN ('OBICNA','ENCIKLOPEDIJA', 'SLIKOVNICA', 'NOVINE')) ENABLE;
  ALTER TABLE "BIBLIOTEKA"."KNJIGA" ADD CHECK ( Zanr IN ('DRAMA','PSIHOLOGIJA','ZDRAVLJE', 'DETEKTIVSKI', 'NAUCNA FANTASTIKA', 'HOROR', 'PUTOPISI', 
                                        'FANTAZIJA', 'EDUKACIJA', 'DECIJE', 'ISTORIJSKI', 'POEZIJA', 'BIOGRAFIJA', 'KUVARI', 'UMETNOST',
                                        'MOTIVACIJA I INSPIRACIJA', 'LJUBAVNI', 'MARKETING I MENADZEMENT', 'KLASICI', 'SPORT', 'INTERNET I RACUNARI')) ENABLE;
  ALTER TABLE "BIBLIOTEKA"."KNJIGA" MODIFY ("OCENA" NOT NULL ENABLE);
  ALTER TABLE "BIBLIOTEKA"."KNJIGA" MODIFY ("TIP" NOT NULL ENABLE);
  ALTER TABLE "BIBLIOTEKA"."KNJIGA" MODIFY ("ZANR" NOT NULL ENABLE);
  ALTER TABLE "BIBLIOTEKA"."KNJIGA" MODIFY ("IZDAVAC" NOT NULL ENABLE);
  ALTER TABLE "BIBLIOTEKA"."KNJIGA" MODIFY ("GODSTAMPANJA" NOT NULL ENABLE);
  ALTER TABLE "BIBLIOTEKA"."KNJIGA" MODIFY ("GODIZDAVANJA" NOT NULL ENABLE);
  ALTER TABLE "BIBLIOTEKA"."KNJIGA" MODIFY ("NASLOV" NOT NULL ENABLE);
  ALTER TABLE "BIBLIOTEKA"."KNJIGA" MODIFY ("ISBN" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table PRIMERAK
--------------------------------------------------------

  ALTER TABLE "BIBLIOTEKA"."PRIMERAK" ADD CONSTRAINT "PRIMERAK_PK" PRIMARY KEY ("SIFRAPR")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
  ALTER TABLE "BIBLIOTEKA"."PRIMERAK" ADD CONSTRAINT "CK_PRIMERAK" CHECK (Slobodan IN (1,0)) ENABLE;
  ALTER TABLE "BIBLIOTEKA"."PRIMERAK" MODIFY ("ISBN" NOT NULL ENABLE);
  ALTER TABLE "BIBLIOTEKA"."PRIMERAK" MODIFY ("SIFRAPR" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table BIBLIOTEKAR
--------------------------------------------------------

  ALTER TABLE "BIBLIOTEKA"."BIBLIOTEKAR" ADD CONSTRAINT "JMBG_UNKQ" UNIQUE ("JMBG")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
  ALTER TABLE "BIBLIOTEKA"."BIBLIOTEKAR" ADD CONSTRAINT "EMAIL_BIBLIOTEKAR_PK" PRIMARY KEY ("EMAIL")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
  ALTER TABLE "BIBLIOTEKA"."BIBLIOTEKAR" ADD CHECK ( TipZaposlenog IN ('KLASICAN', 'OBRADJIVAC', 'ADMINISTRATOR')) ENABLE;
  ALTER TABLE "BIBLIOTEKA"."BIBLIOTEKAR" MODIFY ("TIPZAPOSLENOG" NOT NULL ENABLE);
  ALTER TABLE "BIBLIOTEKA"."BIBLIOTEKAR" MODIFY ("MESTOF" NOT NULL ENABLE);
  ALTER TABLE "BIBLIOTEKA"."BIBLIOTEKAR" MODIFY ("ADRESA" NOT NULL ENABLE);
  ALTER TABLE "BIBLIOTEKA"."BIBLIOTEKAR" MODIFY ("DATUMRODJ" NOT NULL ENABLE);
  ALTER TABLE "BIBLIOTEKA"."BIBLIOTEKAR" MODIFY ("JMBG" NOT NULL ENABLE);
  ALTER TABLE "BIBLIOTEKA"."BIBLIOTEKAR" MODIFY ("PRZ" NOT NULL ENABLE);
  ALTER TABLE "BIBLIOTEKA"."BIBLIOTEKAR" MODIFY ("IME" NOT NULL ENABLE);
  ALTER TABLE "BIBLIOTEKA"."BIBLIOTEKAR" MODIFY ("LOZINKA" NOT NULL ENABLE);
  ALTER TABLE "BIBLIOTEKA"."BIBLIOTEKAR" MODIFY ("EMAIL" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table ZAHTEVZAREZERVACIJU
--------------------------------------------------------

  ALTER TABLE "BIBLIOTEKA"."ZAHTEVZAREZERVACIJU" ADD CONSTRAINT "ZAHTEV_PK" PRIMARY KEY ("EMAIL", "ISBN", "DATUMZAH")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
  ALTER TABLE "BIBLIOTEKA"."ZAHTEVZAREZERVACIJU" MODIFY ("DATUMZAH" NOT NULL ENABLE);
  ALTER TABLE "BIBLIOTEKA"."ZAHTEVZAREZERVACIJU" MODIFY ("ISBN" NOT NULL ENABLE);
  ALTER TABLE "BIBLIOTEKA"."ZAHTEVZAREZERVACIJU" MODIFY ("EMAIL" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table RECENZIJA
--------------------------------------------------------

  ALTER TABLE "BIBLIOTEKA"."RECENZIJA" ADD CONSTRAINT "RECENZIJA_PK" PRIMARY KEY ("EMAIL", "ISBN")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
  ALTER TABLE "BIBLIOTEKA"."RECENZIJA" MODIFY ("ISBN" NOT NULL ENABLE);
  ALTER TABLE "BIBLIOTEKA"."RECENZIJA" MODIFY ("EMAIL" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table MESTOPRIMERKA
--------------------------------------------------------

  ALTER TABLE "BIBLIOTEKA"."MESTOPRIMERKA" ADD CONSTRAINT "MESTO_PRIMERKA_PK" PRIMARY KEY ("SIFRAPR", "NAZIVF")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
  ALTER TABLE "BIBLIOTEKA"."MESTOPRIMERKA" ADD CONSTRAINT "BR_RED" CHECK (BrojReda > 0) ENABLE;
  ALTER TABLE "BIBLIOTEKA"."MESTOPRIMERKA" ADD CONSTRAINT "BR_POL" CHECK (BrojPolice > 0) ENABLE;
  ALTER TABLE "BIBLIOTEKA"."MESTOPRIMERKA" MODIFY ("NAZIVF" NOT NULL ENABLE);
  ALTER TABLE "BIBLIOTEKA"."MESTOPRIMERKA" MODIFY ("SIFRAPR" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table AUTORKNJIGE
--------------------------------------------------------

  ALTER TABLE "BIBLIOTEKA"."AUTORKNJIGE" ADD CONSTRAINT "AUTOR_KNJIGA_PK" PRIMARY KEY ("ISBN", "IDAUTOR")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
  ALTER TABLE "BIBLIOTEKA"."AUTORKNJIGE" MODIFY ("IDAUTOR" NOT NULL ENABLE);
  ALTER TABLE "BIBLIOTEKA"."AUTORKNJIGE" MODIFY ("ISBN" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table BIBLIOTEKA
--------------------------------------------------------

  ALTER TABLE "BIBLIOTEKA"."BIBLIOTEKA" ADD CONSTRAINT "BIBLIOTEKA_PK" PRIMARY KEY ("NAZIVB")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
  ALTER TABLE "BIBLIOTEKA"."BIBLIOTEKA" MODIFY ("NAZIVB" NOT NULL ENABLE);
--------------------------------------------------------
--  Ref Constraints for Table POZAJMICA
--------------------------------------------------------

  ALTER TABLE "BIBLIOTEKA"."POZAJMICA" ADD CONSTRAINT "EMAIL_POZAJMICA_FK" FOREIGN KEY ("EMAIL")
	  REFERENCES "BIBLIOTEKA"."CLAN" ("EMAIL") ENABLE;
  ALTER TABLE "BIBLIOTEKA"."POZAJMICA" ADD CONSTRAINT "SIFRA_PR_POZAJMICA_FK" FOREIGN KEY ("SIFRAPR")
	  REFERENCES "BIBLIOTEKA"."PRIMERAK" ("SIFRAPR") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table REZERVACIJA
--------------------------------------------------------

  ALTER TABLE "BIBLIOTEKA"."REZERVACIJA" ADD CONSTRAINT "EMAIL_REZERVACIJA_FK" FOREIGN KEY ("EMAIL")
	  REFERENCES "BIBLIOTEKA"."CLAN" ("EMAIL") ENABLE;
  ALTER TABLE "BIBLIOTEKA"."REZERVACIJA" ADD CONSTRAINT "ISBN_REZERVACIJA_FK" FOREIGN KEY ("ISBN")
	  REFERENCES "BIBLIOTEKA"."KNJIGA" ("ISBN") ENABLE;
  ALTER TABLE "BIBLIOTEKA"."REZERVACIJA" ADD CONSTRAINT "SIFRA_PR_REZERVACIJA_FK" FOREIGN KEY ("SIFRAPR")
	  REFERENCES "BIBLIOTEKA"."PRIMERAK" ("SIFRAPR") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table CLAN
--------------------------------------------------------

  ALTER TABLE "BIBLIOTEKA"."CLAN" ADD CONSTRAINT "CLAN_MESTO_FK" FOREIGN KEY ("MESTOF")
	  REFERENCES "BIBLIOTEKA"."MESTO" ("PTTBROJ") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table FILIJALA
--------------------------------------------------------

  ALTER TABLE "BIBLIOTEKA"."FILIJALA" ADD CONSTRAINT "FILIJALA_BIBLIOTEKA_FK" FOREIGN KEY ("NAZIVBIBLIOTEKA")
	  REFERENCES "BIBLIOTEKA"."BIBLIOTEKA" ("NAZIVB") ENABLE;
  ALTER TABLE "BIBLIOTEKA"."FILIJALA" ADD CONSTRAINT "FILIJALA_MESTO_FK" FOREIGN KEY ("MESTOF")
	  REFERENCES "BIBLIOTEKA"."MESTO" ("PTTBROJ") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table PRIMERAK
--------------------------------------------------------

  ALTER TABLE "BIBLIOTEKA"."PRIMERAK" ADD CONSTRAINT "ISBN_PRIMERAK_FK" FOREIGN KEY ("ISBN")
	  REFERENCES "BIBLIOTEKA"."KNJIGA" ("ISBN") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table BIBLIOTEKAR
--------------------------------------------------------

  ALTER TABLE "BIBLIOTEKA"."BIBLIOTEKAR" ADD CONSTRAINT "BIBLIOTEKAR_MESTO_FK" FOREIGN KEY ("MESTOF")
	  REFERENCES "BIBLIOTEKA"."MESTO" ("PTTBROJ") ENABLE;
  ALTER TABLE "BIBLIOTEKA"."BIBLIOTEKAR" ADD CONSTRAINT "NAZIV_FIL_FK" FOREIGN KEY ("NAZIVFILIJALE")
	  REFERENCES "BIBLIOTEKA"."FILIJALA" ("NAZIVF") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table ZAHTEVZAREZERVACIJU
--------------------------------------------------------

  ALTER TABLE "BIBLIOTEKA"."ZAHTEVZAREZERVACIJU" ADD CONSTRAINT "EMAIL_ZAHTEV_FK" FOREIGN KEY ("EMAIL")
	  REFERENCES "BIBLIOTEKA"."CLAN" ("EMAIL") ENABLE;
  ALTER TABLE "BIBLIOTEKA"."ZAHTEVZAREZERVACIJU" ADD CONSTRAINT "ISBN_ZAHTEV_FK" FOREIGN KEY ("ISBN")
	  REFERENCES "BIBLIOTEKA"."KNJIGA" ("ISBN") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table RECENZIJA
--------------------------------------------------------

  ALTER TABLE "BIBLIOTEKA"."RECENZIJA" ADD CONSTRAINT "EMAIL_RECENZIJA_FK" FOREIGN KEY ("EMAIL")
	  REFERENCES "BIBLIOTEKA"."CLAN" ("EMAIL") ENABLE;
  ALTER TABLE "BIBLIOTEKA"."RECENZIJA" ADD CONSTRAINT "ISBN_RECENZIJA_FK" FOREIGN KEY ("ISBN")
	  REFERENCES "BIBLIOTEKA"."KNJIGA" ("ISBN") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table MESTOPRIMERKA
--------------------------------------------------------

  ALTER TABLE "BIBLIOTEKA"."MESTOPRIMERKA" ADD CONSTRAINT "FILIJALA_M_FK" FOREIGN KEY ("NAZIVF")
	  REFERENCES "BIBLIOTEKA"."FILIJALA" ("NAZIVF") ENABLE;
  ALTER TABLE "BIBLIOTEKA"."MESTOPRIMERKA" ADD CONSTRAINT "PRIMERAK_M_FK" FOREIGN KEY ("SIFRAPR")
	  REFERENCES "BIBLIOTEKA"."PRIMERAK" ("SIFRAPR") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table AUTORKNJIGE
--------------------------------------------------------

  ALTER TABLE "BIBLIOTEKA"."AUTORKNJIGE" ADD CONSTRAINT "AUTOR_AK_FK" FOREIGN KEY ("IDAUTOR")
	  REFERENCES "BIBLIOTEKA"."AUTOR" ("IDAUTOR") ENABLE;
  ALTER TABLE "BIBLIOTEKA"."AUTORKNJIGE" ADD CONSTRAINT "ISBN_AK_FK" FOREIGN KEY ("ISBN")
	  REFERENCES "BIBLIOTEKA"."KNJIGA" ("ISBN") ENABLE;

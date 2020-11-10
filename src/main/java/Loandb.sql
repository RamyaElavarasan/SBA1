DROP DATABASE IF EXISTS Loan;

CREATE DATABASE Loan;

USE Loan;

CREATE TABLE LoginUsers(
 	username varchar(20) primary key,
 	password varchar(20) not null
 ); 
 
 
INSERT INTO LoginUsers VALUES
 	('admin','admin');


CREATE TABLE LoanInfo(
    applno varchar(20) primary key,
	purpose TEXT NOT NULL,
    amtrequest DECIMAL NOT NULL,
    doa DATE NOT NULL,
    bstructure VARCHAR(20) NOT NULL,
    bindicator VARCHAR(20) NOT NULL,  
    tindicator VARCHAR(20) NOT NULL,  
    address TEXT NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    mobile CHAR(10) NOT NULL UNIQUE,
    status  VARCHAR(100) NOT NULL
);	

INSERT INTO LoanInfo VALUES
 	('1234567890987654','mortgage','100000','20201104','Individual','sd','yes','Bangalore','ram@gmail.com','9991600343','New');
INSERT INTO LoanInfo VALUES
 	('1234567890987655','mortgage','100000','20201104','Individual','sd','yes','Bangalore','sam@gmail.com','9991600348','New');	

		
CREATE TABLE ApprovedLoan(
  applno VARCHAR(200) REFERENCES loan (applno),
  amotsanctioned DECIMAL NOT NULL,
  loanterm DECIMAL NOT NULL,
  psd DATE NOT NULL,
  lcd DATE NOT NULL,
  emi DECIMAL NOT NULL
);



INSERT INTO ApprovedLoan VALUES
 	('1234567890987654','75000','24','20201104','20251104','1500');
	
commit;

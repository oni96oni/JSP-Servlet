DROP SEQUENCE EMP_BOARDS_SEQ;

CREATE TABLE EMP_BOARDS (
	ID NUMBER NOT NULL,
	TITLE VARCHAR2(100) NOT NULL,
	CONTENT VARCHAR2(2000),
	EMPID NUMBER NOT NULL,
	CREATEDATE DATE DEFAULT(SYSDATE),
	CONSTRAINT EMP_BOARDS_ID_PK PRIMARY KEY (ID),
	CONSTRAINT EMP_BOARDS_EMPID_FK FOREIGN KEY(EMPID) REFERENCES EMPLOYEES(EMPLOYEE_ID)
);

CREATE SEQUENCE EMP_BOARDS_SEQ NOCACHE;

SELECT * FROM EMP_BOARDS;
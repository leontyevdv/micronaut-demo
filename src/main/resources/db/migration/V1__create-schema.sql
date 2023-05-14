create TABLE THING (
  ID INT DEFAULT NEXTVAL('thing_sequence') PRIMARY KEY,
  NAME VARCHAR(255) NOT NULL,
  AMOUNT INT NOT NULL
);

create sequence thing_sequence
  start with 1
  increment by 1
  nocache nocycle;

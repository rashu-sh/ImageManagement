CREATE database testdb;
use testdb;

CREATE TABLE images (   
id char(100) NOT NULL,
name char(100) ,
image longblob,   
PRIMARY KEY (id) 
);


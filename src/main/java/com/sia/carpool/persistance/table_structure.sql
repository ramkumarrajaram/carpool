CREATE DATABASE HACKATHON_LOCAL;

USE HACKATHON_LOCAL;

CREATE TABLE REGISTER_USER (
  MOBILE_NUMBER VARCHAR(20) PRIMARY KEY,
  USER_NAME VARCHAR(255) NOT NULL,
  TOKEN VARCHAR(255) NULL
)

DROP TABLE IF EXISTS SUBSCRIBER_USER;
DROP TABLE IF EXISTS PUBLISHER_USER;

CREATE TABLE PUBLISHER_USER (
    MOBILE_NUMBER VARCHAR(20),
    USER_NAME VARCHAR(255) NOT NULL,
    ORIGIN VARCHAR(255) NOT NULL,
    DESTINATION VARCHAR(255) NOT NULL,
    TRIP_TIME DATETIME NOT NULL,
    NUMBER_OF_SEATS INT NOT NULL,
    CONSTRAINT MOBILE_TRIP_TIME PRIMARY KEY (MOBILE_NUMBER, TRIP_TIME)
);

CREATE TABLE SUBSCRIBER_USER (
    MOBILE_NUMBER VARCHAR(20) PRIMARY KEY,
    USER_NAME VARCHAR(255) NOT NULL,
    PUBLISHER_NUMBER VARCHAR(20) NOT NULL,
    PUBLISHER_TRIP_TIME DATETIME NOT NULL,
    CONSTRAINT `PUBLISHER_TYPE_REFERENCE` FOREIGN KEY (PUBLISHER_NUMBER, PUBLISHER_TRIP_TIME) REFERENCES PUBLISHER_USER (MOBILE_NUMBER, TRIP_TIME) ON DELETE NO ACTION ON UPDATE NO ACTION
);
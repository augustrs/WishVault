CREATE SCHEMA IF NOT EXISTS WISHLIST_SCHEMA;
USE WISHLIST_SCHEMA;
DROP TABLE IF EXISTS IMAGE;
DROP TABLE IF EXISTS WISH;
DROP TABLE IF EXISTS WISHLIST;



CREATE TABLE WISHLIST (
                          LISTID INTEGER AUTO_INCREMENT,
                          WISHERNAME VARCHAR(30),
                          EVENTNAME VARCHAR(30),
                          PRIMARY KEY (LISTID)
);

CREATE TABLE WISH (
                      WISHID INTEGER AUTO_INCREMENT,
                      NAME VARCHAR(30),
                      DESCRIPTION VARCHAR(200),
                      ITEMURL VARCHAR(255),
                      PRICE INTEGER,
                      LISTID INTEGER,
                      PRIMARY KEY (WISHID),
                      FOREIGN KEY (LISTID) REFERENCES WISHLIST(LISTID)
);
CREATE TABLE IMAGE (
                       IMAGEID INTEGER AUTO_INCREMENT,
                       IMAGE VARCHAR(255),
                       WISHID INTEGER,
                       PRIMARY KEY (IMAGEID),
                       FOREIGN KEY (WISHID) REFERENCES WISH(WISHID)

);
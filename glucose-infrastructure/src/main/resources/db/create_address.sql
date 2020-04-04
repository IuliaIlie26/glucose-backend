CREATE TABLE  address (
   address_id  varchar(36) NOT NULL,
   address_line1  varchar(50) NOT NULL,
   address_line2  varchar(50) DEFAULT NULL,
   zipcode  varchar(6) DEFAULT NULL,
   city  varchar(20) NOT NULL,
   region  varchar(20) NOT NULL,
   country  varchar(20) NOT NULL,
  PRIMARY KEY ( address_id )
)
